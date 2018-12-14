package com.szit.arbitrate.api.base;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterNameDiscoverer;

import com.hsit.common.actions.utils.DateFormatter;
import com.hsit.common.actions.utils.Struts2Utils;
import com.hsit.common.exceptions.ApplicationException;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.utils.JsonMapper;
import com.szit.arbitrate.api.client.controller.ApiClientController;
import com.szit.arbitrate.api.client.controller.impl.ApiClientControllerImpl;
import com.szit.arbitrate.api.common.ApiBoFormTools;
import com.szit.arbitrate.client.entity.Client;

public abstract class BaseController<T extends DomainEntity, Q extends EntityQueryParam> {
	
	protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	
    protected static JsonMapper jsonMapper = JsonMapper.getInstance();
	
	private DateFormatter dateFormatter = new DateFormatter();

	private Paging paging;
	private int page = 1;
	
	private boolean isDebug =false;
	
	public BaseController(){
		init();
	}
	
	//输入参数
    private String inbo;
    public void setInbo(String inbo) {
		this.inbo = inbo;
	}
	
	private Method method;
	public void setMethod(Method method) {
		this.method = method;
	}
	private Object result;
	public void setResult(Object result) {
		this.result = result;
	}

	private JSONObject jsonResult;

	public void setJsonResult(JSONObject jsonResult) {
		this.jsonResult = jsonResult;
	}
	public String getInbo() {
		return inbo;
	}

	public Method getMethod() {
		return method;
	}

	public Object getResult() {
		return result;
	}

	public JSONObject getJsonResult() {
		return jsonResult;
	}




	public void outResultJson(){
		if (jsonResult==null) {
			jsonResult = new JSONObject();
			jsonResult.element("resultstate", 0);
		}
		
		if(method.getReturnType().getName().equals("java.util.List")){
			if(result!=null){
				setEntities((List<T>)result);
				JSONArray jsonArray = convertEntityListToJson();
				JSONObject jsonObject = new JSONObject();
				jsonObject.element("rows", jsonArray);
				jsonObject.element("total", getEntityQuery()!=null && getEntityQuery().getPaging()!=null?getEntityQuery().getPaging().getRecordCount():getEntities().size());
				jsonObject.element("pageCount", getEntityQuery()!=null&&getEntityQuery().getPaging()!=null?getEntityQuery().getPaging().getPageCount():1);
				jsonResult.element("outbo", jsonObject);
			}
		}else if(method.getReturnType().getName().equals("void")){
			
		}
		else if(method.getReturnType().getName().equals("com.szit.arbitrate.api.common.vm.ApiOutParamsVm")){
			if(!isDebug){
				outJson(JsonMapper.getInstance().toJson(result));
			}else{
				jsonResult = JSONObject.fromObject(JsonMapper.getInstance().toJson(result));
			}
        	return;
		}
        else if(method.getReturnType().getName().equals("java.lang.String")){
        	outJson((String)result);
        	return;
		}
		else if(method.getReturnType().isPrimitive()){
			if(result!=null){
				JSONObject jsonObject = new JSONObject();
				jsonObject.element("rst", result);
				jsonResult.element("outbo", jsonObject);
			}
		}else if(isMethodReturnEntity()){
			if(result!=null){
				setEntity((T)result);
				jsonResult.element("outbo", convertEntityToJson());
			}
		}
		if(!isDebug){
			outJson(jsonResult.toString());
		}
	
	}
	
	private boolean isMethodReturnEntity(){
		Class<?> cls = method.getReturnType();
		if (cls.getName().equals("com.hsit.common.kfbase.entity.DomainEntity")) {
			return true;
		}
		cls = cls.getSuperclass();
		while(cls!=null){
			if (cls.getName().equals("com.hsit.common.kfbase.entity.DomainEntity")) {
				return true;
			}
			cls = cls.getSuperclass();
		}
		return false;
		
	}
	
	
	@Autowired
	private ParameterNameDiscoverer parameterNameDiscoverer;
	@SuppressWarnings({ "unchecked", "unused" })
	public Object[] buildArgs(){
		
		String[] names = parameterNameDiscoverer.getParameterNames(method);
		Class<?>[] types = method.getParameterTypes();
		Object[] args = new Object[method.getParameterTypes().length];
		for(int i=0; i<args.length; i++){
			args[i]=null;
		}
		
		if(!StringUtils.isEmpty(inbo)){
			JSONObject jsonObject = JSONObject.fromObject(inbo);
			if(jsonObject!=null){
				for(int i=0; i<names.length; i++){
					if (jsonObject.containsKey(names[i])) {
						if (names[i].equals("query")) {
							Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass()
									.getGenericSuperclass()).getActualTypeArguments()[1];
							try {
								args[i] = ApiBoFormTools.formCopyOutToBo(jsonObject.optJSONObject("query"), clazz);
							} catch (Exception e) {
								logger.error("buildArgs error:",e);
							}
						}else if (names[i].equals("entity")) {
							Class<T> clazz = (Class<T>) ((ParameterizedType) this.getClass()
									          .getGenericSuperclass()).getActualTypeArguments()[0];
							try {
								args[i] = ApiBoFormTools.formInBo(jsonObject.optJSONObject("entity").toString(), clazz);
							} catch (Exception e) {
								logger.error("buildArgs error:",e);
							}
						}
						else if (names[i].equals("jsonlist")) {
							args[i] = jsonObject.opt(names[i]).toString();
						}
						else if (names[i].equals("entityjson")) {
							args[i] = jsonObject.opt(names[i]).toString();
						}
						else {
							args[i]= jsonObject.opt(names[i]);
						}
					}
				}
			}
		}
		return args;
	}
	
	
	
	
	
	public Client getLoginClient(){
		return (Client)getSession().getAttribute("client");
	}
	
	
	
	
	private String id;
	
	/**
	 * 
	 * @return
	 */
	public String getId() {
		return id;
	}

	private T entity;

	public T getEntity() {
		return entity;
	}

	public void setEntity(T entity) {
		this.entity = entity;
	}

	private Q entityQuery;

	public Q getEntityQuery() {
		return entityQuery;
	}

	public void setEntityQuery(Q entityQuery) {
		this.entityQuery = entityQuery;
	}

	private List<T> entities;

	public List<T> getEntities() {
		return entities;
	}

	public void setEntities(List<T> entities) {
		this.entities = entities;
	}

	/**
	 * 
	 * @param id
	 *            ;
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	
	
	
	private File upload;
	private String uploadFileName;
	private List<File> uploads;
	private List<String> uploadFileNames;
	
	
	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public List<File> getUploads() {
		return uploads;
	}

	public void setUploads(List<File> uploads) {
		this.uploads = uploads;
	}

	public List<String> getUploadFileNames() {
		return uploadFileNames;
	}

	public void setUploadFileNames(List<String> uploadFileNames) {
		this.uploadFileNames = uploadFileNames;
	}

	protected JSONObject convertEntityToJson() {
		return getEntityJson(entity);
	}

	protected JSONObject getEntityJson(T entity){
		JSONObject js = new JSONObject();

		if (entity != null) {
			js.element("id", entity.getId());
			js.element("name", entity.getName());
			js.element("description", entity.getDescription());
			js.element("displayOrder", entity.getDisplayOrder());
		}
		return js;
	}
	
	protected JSONArray convertEntityListToJson() {
		return convertEntityListToJson(entities);
	}
	
	protected JSONArray convertEntityListToJson(List<T> objs) {
		JSONArray jsonArray = new JSONArray();
		
		if (objs != null) {
			for(T obj : objs){
				jsonArray.add(getEntityJson(obj));
			}
		}
		
		return jsonArray;
	}

	
	
	
	
	protected void outFailJson(Exception e){
		if (e != null) {
			e.printStackTrace();
			if (e instanceof ApplicationException)
				outFailJson(e.getMessage(), null);
			else
				outFailJson("系统内部错误，请联系管理员", null);
		}else {
			outFailJson("系统内部错误，请联系管理员", null);
		}
	}
	
	protected void outFailJson(String msg, String ext) {
		try {
			JSONObject js = new JSONObject();
			js.element("isSuccess", false);
			if(!StringUtils.isEmpty(msg)){
				js.element("resultDescription", msg);
			}else{
				js.element("resultDescription", "系统内部错误，请联系管理员");
			}
			
			outJson(js.toString());
		} 
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	protected void outSuccessJson() {
		try {
			JSONObject js = new JSONObject();
			js.element("isSuccess", true);
			js.element("resultDescription", "");
			outJson(js.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void outSuccessJson(String info) {
		try {
			JSONObject js = new JSONObject();
			js.element("isSuccess", true);
			js.element("resultDescription", info);
			if(getEntity() != null)
				js.element("id", getEntity().getId());
			outJson(js.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 输出json数据
	 * 
	 * @param json
	 * @throws Exception
	 */
	protected void outJson(String json) {
		getResponse().setContentType("text; charset=UTF-8");
		getResponse().setCharacterEncoding("UTF-8");
		PrintWriter out = null;

		try {
			out = getResponse().getWriter();
			out.write(json);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null)
				out.close();
		}
	}
	
	private HttpServletRequest request = null;
	
	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletRequest getRequest() {
		logger.debug("reqeust请求:{}",request);
		if(!isDebug){
			logger.debug("从struts2取得request");
			request = ServletActionContext.getRequest();
		}
		logger.debug("从单元测试类取得request");
		return request;
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public ServletContext getServletContext() {
		return ServletActionContext.getServletContext();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public boolean isPostRequest() {
		return getRequest().getMethod().equals("POST");
	}

	public boolean isGetRequest() {
		return getRequest().getMethod().equals("GET");
	}

	public String getContextUri(String paramString) {
		if (!paramString.startsWith("/"))
			throw new ApplicationException(
					"The parameter 'uri' must start with '/'.");
		return getRequest().getContextPath() + paramString;
	}
	
	/**
	 * 
	* 方法描述:取得http路径
	* 创建人: XUJC
	* 创建时间：2017年12月9日
	* @param catalog 目录
	* @return
	 */
	public String getHttpHomeUrl(String catalog){
		String path = request.getContextPath();
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/"+catalog.replace("\\", "//");
	}

	public String getContextUri(String paramString1, String paramString2) {
		return getRequest().getContextPath()
				+ Struts2Utils.getActionUri(paramString1, paramString2);
	}
	
	protected void addCookie(Cookie paramCookie) {
		getResponse().addCookie(paramCookie);
	}

	protected void addCookie(String paramString1, String paramString2) {
		getResponse().addCookie(new Cookie(paramString1, paramString2));
	}

	protected Cookie getCookieByName(String paramString) {
		Cookie[] arrayOfCookie1 = getRequest().getCookies();
		for (Cookie localCookie : arrayOfCookie1)
			if (localCookie.getName().equals(paramString))
				return localCookie;
		return null;
	}

	// 日期时间相关

	public Date getCurrentDate() {
		return new Date();
	}

	public String formatDate(Date paramDate) {
		if (paramDate != null)
			return this.dateFormatter.formatDate(paramDate);
		return "";
	}

	public String formatTime(Date paramDate) {
		if (paramDate != null)
			return this.dateFormatter.formatTime(paramDate);
		return "";
	}

	public String formatDateTime(Date paramDate) {
		if (paramDate != null)
			return this.dateFormatter.formatDateTime(paramDate);
		return "";
	}

	private DecimalFormat decimalFormat = new DecimalFormat("#.##");
	
	public String formatNumber(float number){
		return decimalFormat.format(number);
	}
	
	public Date parseDate(String paramString) {
		try {
			if (!StringUtils.isEmpty(paramString))
				return this.dateFormatter.parseDate(paramString);			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public Date parseDateTime(String paramString) {
		try {
			if (!StringUtils.isEmpty(paramString))
				return this.dateFormatter.parseTime(paramString);			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	public Date parseTime(String paramString) {
		if (!StringUtils.isEmpty(paramString))
			return this.dateFormatter.parseTime(paramString);
		return null;
	}

	public String getDatePattern() {
		return this.dateFormatter.getDatePattern();
	}

	public String getDateTimePattern() {
		return this.dateFormatter.getDateTimePattern();
	}

	public String getTimePattern() {
		return this.dateFormatter.getTimePattern();
	}

	public boolean isEmpty(String paramString) {
		return StringUtils.isEmpty(paramString);
	}

	// 分页相关

	public Paging getPaging() {
		return this.paging;
	}

	public void setPaging(Paging paramPaging) {
		this.paging = paramPaging;
	}

	public int getPage() {
		return this.page;
	}

	public void setPage(int paramInt) {
		this.page = paramInt;
		if (this.paging != null)
			this.paging.setPage(paramInt);
	}

	public void init() {
		this.paging = new Paging();
		this.paging.setPageSize(getDefaultPageSize());
		jsonResult=null;
		inbo=null;
		method=null;
		result=null;
	}

	protected int getDefaultPageSize() {
		return 15;
	}
	
	public boolean floatEqual(float v1, float v2) {
		return Math.abs(v1-v2)<0.00001;
	}
	
	protected String urlDecode(String text) {
		if (!StringUtils.isEmpty(text)) {
			try {
				return URLDecoder.decode(text, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}
	
	
	protected String urlEncode(String text) {
		if (!StringUtils.isEmpty(text)) {
			try {
				return URLEncoder.encode(text, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "";
	}

	public boolean isDebug() {
		return isDebug;
	}

	public void setDebug(boolean isDebug) {
		this.isDebug = isDebug;
	}
	
	
	
}
