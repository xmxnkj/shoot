package com.szit.arbitrate.client.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Maps;
import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.utils.CommonUtil;
import com.hsit.common.utils.PageDataBean;
import com.hsit.common.utils.PagingBean;
import com.hsit.common.utils.UploadAccResourceUtil;
import com.szit.arbitrate.client.dao.ClientDao;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.enumvo.CertificateStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;
import com.szit.arbitrate.client.entity.query.ClientQuery;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.service.MediationAgencyService;

import net.sf.json.JSONObject;

@Namespace("/admin/client")
@Controller("clientAction")
public class ClientAction extends BaseJsonAction<Client, ClientQuery>{

	@Autowired
	private ClientService service;
	
	@Autowired
	private MediationAgencyService mediationAgencyService;
	
	@Autowired
	private ClientDao dao;
	

	private String sort;
	private String order;
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public ClientService getService() {
		return service;
	}

	public void setService(ClientService service) {
		this.service = service;
	}

	private String clientType;//用户类型
	private String agencyId;//机构id

	public String getAgencyId() {
		return agencyId;
	}

	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		this.clientType = clientType;
	}

	@Override
	protected void beforeQuery() {
		ClientQuery query = this.getEntityQuery();
		if(StringUtils.isNotEmpty(clientType)){
			query.setClientType(ClientTypeEnum.valueOf(clientType));
		}
		if(StringUtils.isNotEmpty(agencyId)){
			query.setMediationAgencyId(agencyId);;
		}
		this.setEntityQuery(query);
	}


	/**
	 * 
	 * @Title: saveMediatorClient 
	 * @Description: 后台创建员用户
	 * @param 
	 * @return void 
	 * @throws
	 */
	@Action(value = "saveMediatorClient")
	public void saveMediatorClient(){
		JSONObject json = new JSONObject();
		try {
			String account = this.getEntity().getAccount();//账号
			String passwd = this.getEntity().getPasswd();//密码
			String identifyName = this.getEntity().getIdentifyName();//姓名
			String identify = this.getEntity().getIdentify();//身份证号
			String tel = this.getEntity().getTel();//电话
			String clientState = this.getRequest().getParameter("entity.clientState");//用户身份
			String mediationAgencyId = this.getEntity().getMediationAgencyId();//机构id
			String skill = this.getEntity().getSkill();		//技巧
			String description = this.getEntity().getDescription();	//简介
			String headImgFile = this.getEntity().getHeadImgFile();
			service.createManagerClientByType(account, identifyName, identify, tel, "Mediator", 
					clientState, passwd, mediationAgencyId , skill , description, headImgFile);
			json.put("success", true);
			json.put("message", "创建成功!");
			json.put("data", "");
		} catch (BizException biz) {
			json.put("success", false);
			json.put("message", biz.getMessage());
			json.put("data", "");
		} catch (ErrorException error) {
			json.put("success", false);
			json.put("message", "异常错误,请联系系统管理员!");
			json.put("data", "");
		}
		outJson(json.toString());
	}
	
	/**
	 * 
	* @Title: auditCertificate 
	* @Description:实名认证审核
	* @param 
	* @return void 
	* @throws
	 */
	@Action(value = "auditCertificate")
	public void auditCertificate(){
		JSONObject json = new JSONObject();
		try {
			String clientid = this.getRequest().getParameter("id");
			String oper = this.getRequest().getParameter("oper");
			service.auditCertificateIdentify(clientid,oper);
			json.put("success", true);
			json.put("message", "操作成功!");
			json.put("data", "");
		} catch (BizException biz) {
			json.put("success", false);
			json.put("message", biz.getMessage());
			json.put("data", "");
		}catch (ErrorException error) {
			json.put("success", false);
			json.put("message", error.getMessage());
			json.put("data", "");
		}
		outJson(json.toString());
	}
	
	@Action(value = "getAgencyClient")
	public void getAgencyClient(){
		JSONObject json = new JSONObject();
		try {
			ClientQuery clientQuery = new ClientQuery();
			clientQuery.setMediationAgencyId(agencyId);
			List<Client> list = service.getEntities(clientQuery);
			json.put("success", true);
			json.put("message", "操作成功!");
			json.put("data", list);
		} catch (BizException biz) {
			json.put("success", false);
			json.put("message", biz.getMessage());
			json.put("data", "");
		}catch (ErrorException error) {
			json.put("success", false);
			json.put("message", error.getMessage());
			json.put("data", "");
		}
		outJson(json.toString());
	}
	
	@Action(value = "editAgencyClientSkill")
	public void editAgencyClientSkill(){
		JSONObject json = new JSONObject();
		try {
			String id = getRequest().getParameter("id");
			String skill = getRequest().getParameter("skill");
			String headImgFile = getRequest().getParameter("headImgFile");
			String description = getRequest().getParameter("description");
			String nickName = getRequest().getParameter("nickName");
			String account = getRequest().getParameter("account");
			String identifyName = getRequest().getParameter("identifyName");
			String identify = getRequest().getParameter("identify");
			String tel = getRequest().getParameter("tel");
			String mediatorType = getRequest().getParameter("mediatorType");
			String showDisplay = getRequest().getParameter("showDisplay");
			Client client = service.getById(id);
			if(client!=null){
				//普通用户
				if(client.getClientState()==ClientStateEnum.Normal){
					client.setSkill(skill);
					client.setHeadImgFile(headImgFile);
					client.setDescription(description);
					client.setTel(tel);
					service.save(client);
				}else if(client.getClientState()==ClientStateEnum.MediationAgency || 
						client.getClientState()==ClientStateEnum.Mediator){
					client.setSkill(skill);
					client.setHeadImgFile(headImgFile);
					client.setDescription(description);
					client.setNickName(nickName);
					client.setAccount(account);
					client.setIdentifyName(identifyName);
					client.setIdentify(identify);
					client.setTel(tel);
					client.setMediatorType(Integer.parseInt(mediatorType));
					client.setShowDisplay(Integer.parseInt(showDisplay));
					service.save(client);
				}
				json.put("success", true);
				json.put("message", "操作成功!");
				json.put("data", "");
			}else{
				json.put("success", false);
				json.put("message", "操作失败!");
				json.put("data", "");
			}
		} catch (BizException biz) {
			json.put("success", false);
			json.put("message", biz.getMessage());
			json.put("data", "");
		}catch (ErrorException error) {
			json.put("success", false);
			json.put("message", error.getMessage());
			json.put("data", "");
		}
		outJson(json.toString());
	}
	
	private String name;
	private File imgFile;
	private String id;//实体id
	private String imgFileFileName; // 上传文件的真实名字
	private String imgFileContentType;
	public File getImgFile() {
		return imgFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	//上传头像
	@Action(value = "headImg")
	public void headImg(){
		Map<String,String> maps = Maps.newHashMap();
		try {
			logger.debug("上传文件:"+imgFile.getAbsolutePath());
			String uploadPrefix = imgFileFileName.substring(imgFileFileName.lastIndexOf(".")+1);//后缀名
			String filename= UUID.randomUUID().toString().replace("-", "")+"."+uploadPrefix;
			String folderPath = "uploads/client";
			logger.debug("上传目录:"+folderPath);
			filename = UploadAccResourceUtil.saveCompressFile(imgFile, imgFileFileName, folderPath ,800 ,600 ,true);			
			maps.put("urlpath",filename);
			maps.put("state","OK");
			outSuccessJson(jsonMapper.toJson(maps));
		} catch (Exception e) {
			logger.error("上传附件异常错误",e);
			this.outFailJson("上传失败", e.getMessage());
		}
	}
	
	
	@Action(
			value="defaultHeadImg", 
			results={
			   @Result(name="success", location="/WEB-INF/jsp/admin/client/defaultHeadImg.jsp")}
		   )
	public String newsInformation() {
		return SUCCESS;
	}
	
	//上传头像
		@Action(value = "defaultImg")
		public void defaultImg(){
			Map<String,String> maps = Maps.newHashMap();
			BufferedInputStream bis = null;
			BufferedOutputStream bos = null;
			try {
				logger.debug("上传文件:"+imgFile.getAbsolutePath());
				String uploadPrefix = imgFileFileName.substring(imgFileFileName.lastIndexOf(".")+1);//后缀名
				String filename= UUID.randomUUID().toString().replace("-", "")+"."+uploadPrefix;
				String folderPath = CommonUtil.getAccResourceUploadFolderPath("")+"uploads/client/"+name;
				logger.debug("上传目录:"+folderPath);
				
				//传入服务器
			
				bis = new BufferedInputStream(new FileInputStream(imgFile));
				bos = new BufferedOutputStream(new FileOutputStream(new File(folderPath)));				
				byte[] bt = new byte[1024];
				long line = 0;
				while((line = bis.read(bt))!=-1){
					bos.write(bt);
					bos.flush();				
					maps.put("urlpath",filename);
					maps.put("state","OK");
				}
				outSuccessJson(jsonMapper.toJson(maps));
			} catch (Exception e) {
				logger.error("上传附件异常错误",e);
				this.outFailJson("上传失败", e.getMessage());
			}finally{
				try {
					if(bis!=null){
						bis.close();
					}
					if(bos!=null){
						bos.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	
	@Action(value="consultingStatistics", 
			results={
		@Result(name="success", location="/WEB-INF/jsp/admin/client/consultingStatistics.jsp")}
			)
		public String consultingStatistics() {
			return SUCCESS;
		}
	
	@Action("getConsultingStatisticsData")
	public void getConsultingStatisticsData(){
		try{
			Map<String, Object> map = service.statisticsClientRes();
			outSuccessJson(jsonMapper.toJson(map));
		}catch(Exception e){
			
		}
	}
	
	//查询所有调节机构管理员
	@Action("getAllMediation")
	public void getAllMediation(){
		try{
			getEntityQuery().getPaging();
			PagingBean pagingBean =new PagingBean(getEntityQuery().getPaging().getPage(), getEntityQuery().getPaging().getPageSize());
			StringBuffer stringBuffer = new StringBuffer();
			stringBuffer.append("From Client where clientState=:clientState1 or clientState=:clientState2");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("clientState1", ClientStateEnum.MediationCenter);
			map.put("clientState2", ClientStateEnum.MediationAgency);
			List<Client> list = dao.findHqlToM(stringBuffer.toString(), map,pagingBean);
			map = new HashMap<String,Object>();
			map.put("rows", list);
			map.put("total", pagingBean.getTotalItems());
			outJson(jsonMapper.toJson(map));
		}catch(Exception e){
			
		}
	}
	
	//查询所有调节机构管理员
		@Action("kill")
		public void kill(){
			Map<String,Object> map = new HashMap<String,Object>();
			try{
				String id = getRequest().getParameter("id");
				if(!StringUtils.isEmpty(id)){
					service.deleteById(id);
					map.put("status", "success");
				}else{
					map.put("status", "fail");
				}
			}catch(Exception e){
				map.put("status", "fail");
			}
			outJson(jsonMapper.toJson(map));
		}
		
		@Action("getClientsDatas")
		public void getClientsDatas(){
			try{
				ClientQuery query = this.getEntityQuery();
				if(StringUtils.isNotEmpty(clientType)){
					query.setClientType(ClientTypeEnum.valueOf(clientType));
				}
				if(StringUtils.isNotEmpty(agencyId)){
					query.setMediationAgencyId(agencyId);;
				}

				if(StringUtils.isNotEmpty(sort)){
					if(order.equals("asc")){
					  query.addOrder(sort,false);
					}else{
					  query.addOrder(sort,true);
					}
				}
				List<Client> list = getService().getEntities(query);
				Paging  paging  = this.getEntityQuery().getPaging();
				PageDataBean<Client> pageList = new PageDataBean<Client>(paging,list);				
				MediationAgency mediationAgency = null;
				for(Client c:list){
					if(StringUtils.isNotEmpty(c.getMediationAgencyId())){
						mediationAgency = mediationAgencyService.getById(c.getMediationAgencyId());
						if(mediationAgency!=null){
							c.setAgencyName(mediationAgency.getAgencyName());
						}
					}
				}
				String jsonResult = jsonMapper.toJson(pageList);
				outJson(jsonResult);
			}catch(Exception e){
			}
		}
		
		
		@Action(value = "exportClients")
		public void getClientOrMediator() throws Exception{
			ClientQuery query = this.getEntityQuery();
			if(StringUtils.isNotEmpty(sort)){
				if(order.equals("asc")){
				  query.addOrder(sort,false);
				}else{
				  query.addOrder(sort,true);
				}
			}
			List<Client> list = getService().getEntities(getEntityQuery());
			
			StringBuilder basePath = new StringBuilder(getServletContext().getRealPath("/")).append("\\export\\exportClient.xls");
			String fileName = basePath.toString();
							
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
							
			//读取模板
			HSSFWorkbook wb = new HSSFWorkbook(bis);
			//获取工作表
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;				
			HSSFCell cell = null;
			Integer index = 0;		//模板第0行开始
			Client temp = null;
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm");

			for(int i = 0;i<list.size();i++){
				
				temp = list.get(i);
				
				row = sheet.getRow(index)!=null?sheet.getRow(index):sheet.createRow(index);
				cell = getCell(row,0);
				cell.setCellValue(i+1);
				
				//姓名 
				row = sheet.getRow(index)!=null?sheet.getRow(index):sheet.createRow(index);
				cell = getCell(row,1);
				cell.setCellValue(temp.getName());
				
				
				//注册时间 
				row = sheet.getRow(index)!=null?sheet.getRow(index):sheet.createRow(index);
				cell = getCell(row,2);
				cell.setCellValue(temp.getResgitTime()!=null?f.format(temp.getResgitTime()):"");
				
				//账号
				row = sheet.getRow(index)!=null?sheet.getRow(index):sheet.createRow(index);
				cell = getCell(row,3);
				cell.setCellValue(temp.getAccount());
				
				//类型
				row = sheet.getRow(index)!=null?sheet.getRow(index):sheet.createRow(index);
				cell = getCell(row,4);
				if(temp.getClientType()==ClientTypeEnum.Normal){
					cell.setCellValue("普通用户");
				}else if(temp.getClientType()==ClientTypeEnum.Mediator){
					cell.setCellValue("员");
				}else if(temp.getClientType()==ClientTypeEnum.Visitor){
					cell.setCellValue("游客");
				}
				
				//地址
				row = sheet.getRow(index)!=null?sheet.getRow(index):sheet.createRow(index);
				cell = getCell(row,5);
				cell.setCellValue(temp.getAddress());
				
				//地址
				row = sheet.getRow(index)!=null?sheet.getRow(index):sheet.createRow(index);
				cell = getCell(row,6);
				if(temp.getAuditInfo()==CertificateStateEnum.NotPass){
					cell.setCellValue("未通过");
				}else if(temp.getAuditInfo()==CertificateStateEnum.Pass){
					cell.setCellValue("通过");
				}else if(temp.getAuditInfo()==CertificateStateEnum.WaitAudit){
					cell.setCellValue("待审核");
				}
				index++;
			}
			
			getResponse().setHeader("content-disposition", 
					"attachment;filename=" + URLEncoder.encode("用户表", "UTF-8")+".xls"); 
			getResponse().setContentType("application/vnd.ms-excel");
			OutputStream bos = getResponse().getOutputStream();
			//保存
			wb.write(bos);
			bos.flush();
			bos.close();
		}

		public static HSSFCell getCell(HSSFRow row,int index){
			return row.getCell(index)!=null?row.getCell(index):row.createCell(index);
		}
}