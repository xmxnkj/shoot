package com.szit.arbitrate.api.base;

import java.io.File;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.utils.JsonFormatUtil;
import com.hsit.common.utils.JsonMapper;
import com.hsit.common.utils.SpringBeanUtil;
import com.hsit.common.utils.SystemConfig;
import com.szit.arbitrate.api.client.action.AppClientModuleAction;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.ProjectModuleEnum;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.log.service.ApiRecordLogService;

public abstract class ApiAction <T extends DomainEntity, Q extends EntityQueryParam> {

	
	protected Logger logger = LoggerFactory.getLogger(AppClientModuleAction.class);
	protected static JsonMapper jsonMapper = JsonMapper.getInstance();
	@Autowired
	private ApiRecordLogService apiRecordLogService;
	
	@Autowired
	private SystemConfig systemConfig;
	
	//API接口是否调试 默认false
	private boolean isApiDeug =false;
	
	/**
	 * 
	* @Title: apiInit 
	* @Description: API初始化
	* @param 
	* @return void 
	* @throws
	 */
	public void apiInit(){
		this.setModule(ProjectModuleEnum.unknown);
	}
	
	/**
	 * 
	* @Title: apiExecution 
	* @Description:API业务接口执行
	* @param 
	* @return void 
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public void apiBizExecution(){
		ApiOutParamsVm outVm = null;
		ApiInParamsVm apiInVm = null;
		
		
		String clientid = "";
		if(this.getSession()!=null){
			clientid =  this.getSession().getAttribute("clientId")==null?"":(String)this.getSession().getAttribute("clientId");
		}
		
		try {
			apiInit();
			if(StringUtils.isEmpty(code)){
				throw new BizException("code接收参数不能为空,请认真核查!");	
			}
			apiInVm = new ApiInParamsVm();
			apiInVm.setModule(this.getModule().name());
			apiInVm.setDatasource(inbo);
			String[] sBcodes = code.split("_");
			if(sBcodes==null&sBcodes.length<2){
				throw new BizException("非法参数,code参数符合规则,请认真核查!");	
			}else{
				apiInVm.setBizcode(sBcodes[0]);
				apiInVm.setBizmethod(sBcodes[1]);
			}
			//code 为controllerbean 和 方法
			BaseController<T, Q> controller = (BaseController<T, Q>)SpringBeanUtil.getBean(sBcodes[0]); //(BaseController<T, Q>)getBean(sBcodes[0]);
			if(controller!=null){
				controller.init();
				controller.setInbo(inbo);
				controller.setUpload(upload);
				controller.setUploads(uploads);
				controller.setUploadFileName(uploadFileName);
				controller.setUploadFileNames(uploadFileNames);
				Method method = getMethodClass(controller, sBcodes[1]);
				if(method!=null){
					controller.setMethod(method);
					Object result = method.invoke(controller, controller.buildArgs());
					controller.setResult(result);
					controller.outResultJson();
					if(isApiDeug){//如果debug就记录系统日志表
						apiRecordLogService.saveApiLog(apiInVm, result,clientid);
					}					
				}
				else{
					throw new BizException("API接口不存在,请核对API接口业务方法是否正确!");
				}
			}
			return;
		} catch (BizException biz) {
			logger.debug("API业务接口执行出现业务错误",biz.getMessage());
			String errorcode = apiRecordLogService.saveApiErrorLog(apiInVm,biz.getMessage(),biz,clientid);
			outVm = ApiTools.bulidOutFail(biz.getMessage(),errorcode);
		} catch (ErrorException error) {
			logger.error("API业务接口执行出现异常错误<1>",error);
			String errorcode = apiRecordLogService.saveApiErrorLog(apiInVm,error.getMessage(),error,clientid);
			outVm = ApiTools.bulidOutFail(ApiConstant.API_ERROR_MESSAGE,errorcode);
		} catch (Exception e) {
			logger.error("API业务接口执行出现异常错误<2>",e);
			try {
				InvocationTargetException targetEx = (InvocationTargetException)e;
				Throwable t = targetEx .getTargetException();
				String errorcode = apiRecordLogService.saveApiErrorLog(apiInVm,t.getMessage(),e,clientid);
				if(t instanceof BizException){
					outVm = ApiTools.bulidOutFail(t.getMessage(),errorcode);
				}else{
					outVm = ApiTools.bulidOutFail("API系统接口异常错误,请联系系统管理员!",errorcode);
				}
			} catch (Exception e2) {
				String errorcode = apiRecordLogService.saveApiErrorLog(apiInVm,e2.getMessage(),e2,clientid);
				outVm = ApiTools.bulidOutFail("API系统接口异常错误,请联系系统管理员!",errorcode);
			}
		}
		String outjson = jsonMapper.toJson(outVm);
		JsonFormatUtil.printJson("API业务接口输出JSON",outjson);
		outJson(outjson);
	}
	
	
	private Method getMethodClass(BaseController<T, Q> service, String methodName){
		Method[] methods = service.getClass().getMethods();
		for(int i=0; i<methods.length; i++){
			if(methods[i].getName().equals(methodName)){
				return methods[i];
			}
		}
		return null;
	}
	
	//业务模块
	private ProjectModuleEnum module;
	//业务代码
	private String code;
	//输入参数
    private String inbo;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInbo() {
		return inbo;
	}
	public void setInbo(String inbo) throws UnsupportedEncodingException{
		String sa = URLDecoder.decode(inbo, "UTF-8"); 
		this.inbo = sa;
	}
	public ProjectModuleEnum getModule() {
		return module;
	}
	public void setModule(ProjectModuleEnum module) {
		this.module = module;
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


	/**
	 * 输出json数据
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
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
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

	public boolean isApiDeug() {
		return isApiDeug;
	}

	public void setApiDeug(boolean isApiDeug) {
		if(systemConfig.getGlobaldebug()){
			this.isApiDeug = systemConfig.getIsDebug();
		}else{
			this.isApiDeug =  isApiDeug;
		}
	}
	
	
	
}
