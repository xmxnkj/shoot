package com.szit.arbitrate.mediation.action;

import java.io.File;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.utils.CommonUtil;
import com.hsit.common.utils.CompressPicUtil;
import com.hsit.common.utils.UploadAccResourceUtil;
import com.hsit.common.vm.ComboBoxVm;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.entity.enumvo.AgencyTypeEnum;
import com.szit.arbitrate.mediation.entity.query.MediationAgencyQuery;
import com.szit.arbitrate.mediation.service.MediationAgencyService;

import net.sf.json.JSONObject;

@Namespace("/admin/mediation/mediationagency")
@Controller("mediationAgencyAction")
public class MediationAgencyAction extends BaseJsonAction<MediationAgency, MediationAgencyQuery>{

	private static final long serialVersionUID = 2343938451055458911L;
	
	@Autowired
	private MediationAgencyService service;

	public MediationAgencyService getService() {
		return service;
	}

	public void setService(MediationAgencyService service) {
		this.service = service;
	}
	
	/**
	    * 
	   * @Title: getMediationAgencyComboJson 
	   * @Description: 取得调解机构下拉
	   * @param 
	   * @return void 
	   * @throws
	    */
	@Action(value = "getMediationAgencyComboJson")
	public void getMediationAgencyComboJson(){
		MediationAgencyQuery query = new MediationAgencyQuery();
		List<MediationAgency> list = service.getEntities(query);
		List<ComboBoxVm> comboxList = Lists.newArrayList();
		if(list!=null&&list.size()>0){
			for(int i = 0; i < list.size(); i ++){
				MediationAgency entity = list.get(i);
				ComboBoxVm coboxgoods = new ComboBoxVm(entity.getId(), entity.getAgencyName());
				comboxList.add(coboxgoods);
			}
		}
		this.outJson(jsonMapper.toJson(comboxList));
	}
	
	@Action(value = "getEditMediationAgency")
	public void getEditMediationAgency(){
		JSONObject jsonObject = new JSONObject();
		String id = getRequest().getParameter("id");
		String managerClientId = getRequest().getParameter("managerClientId");
		String mediationResourceId = getRequest().getParameter("mediationResourceId");
		String agencyType = getRequest().getParameter("agencyType");
		String agencyName = getRequest().getParameter("agencyName");
		String tel = getRequest().getParameter("tel");
		String belongsTo = getRequest().getParameter("belongsTo");
		String agencyClassify = getRequest().getParameter("agencyClassify");
		String address = getRequest().getParameter("address");
		String lon = getRequest().getParameter("lon");
		String lat = getRequest().getParameter("lat");
		String lonBaiDu = getRequest().getParameter("lonBaiDu");
		String latBaiDu = getRequest().getParameter("latBaiDu");
		String description = getRequest().getParameter("description");
		
		try{
			MediationAgency mediationAgency = service.getById(id);
			if(mediationAgency!=null){
				mediationAgency.setManagerClientId(managerClientId);
				mediationAgency.setMediationResourceId(mediationResourceId);
				mediationAgency.setAgencyType(AgencyTypeEnum.valueOf(agencyType));
				mediationAgency.setAgencyName(agencyName);
				mediationAgency.setTel(tel);
				mediationAgency.setBelongsTo(belongsTo);
				mediationAgency.setAgencyClassify(agencyClassify);
				mediationAgency.setAddress(address);
				mediationAgency.setLon(new BigDecimal(lon));
				mediationAgency.setLat(new BigDecimal(lat));
				mediationAgency.setLonBaiDu(new BigDecimal(lonBaiDu));
				mediationAgency.setLatBaiDu(new BigDecimal(latBaiDu));
				mediationAgency.setDescription(description);
				service.save(mediationAgency);
				jsonObject.put("status", "success");
			}else{
				mediationAgency = new MediationAgency();
				mediationAgency.setManagerClientId(managerClientId);
				mediationAgency.setMediationResourceId(mediationResourceId);
				mediationAgency.setAgencyType(AgencyTypeEnum.valueOf(agencyType));
				mediationAgency.setAgencyName(agencyName);
				mediationAgency.setTel(tel);
				mediationAgency.setBelongsTo(belongsTo);
				mediationAgency.setAgencyClassify(agencyClassify);
				mediationAgency.setAddress(address);
				mediationAgency.setLon(new BigDecimal(lon));
				mediationAgency.setLat(new BigDecimal(lat));
				mediationAgency.setLonBaiDu(new BigDecimal(lonBaiDu));
				mediationAgency.setLatBaiDu(new BigDecimal(latBaiDu));
				mediationAgency.setDescription(description);
				service.save(mediationAgency);
				jsonObject.put("status", "success");
			}
		}catch(Exception e){
			jsonObject.put("status", "fail");
		}
		outJson(jsonMapper.toJson(jsonObject));
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
				String name = UUID.randomUUID().toString().replace("-", "");
				String filename= name+"."+uploadPrefix;
				String folderPath = "uploads/mediation";
				logger.debug("上传目录:"+folderPath);
				//小图
				filename = UploadAccResourceUtil.saveCompressFile(imgFile, imgFileFileName, folderPath ,1280 ,768 ,true);			
				CompressPicUtil.getInstance().compressPic(imgFile, CommonUtil.getAccResourceUploadFolderPath(folderPath), filename.substring(0,filename.lastIndexOf("."))+"_max."+uploadPrefix, 1280, 200, true);
				maps.put("urlpath",filename);
				maps.put("state","OK");
				outSuccessJson(jsonMapper.toJson(maps));
			} catch (Exception e) {
				logger.error("上传附件异常错误",e);
				this.outFailJson("上传失败", e.getMessage());
			}
		}
		
		//删除机构
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
}