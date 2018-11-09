package com.szit.arbitrate.api.mediation.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.google.common.collect.Maps;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.utils.JsonFormatUtil;
import com.notnoop.apns.ApnsService;
import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.mediation.bo.in.TClientInBo;
import com.szit.arbitrate.api.mediation.controller.ApiMediationCaseController;
import com.szit.arbitrate.client.dao.impl.ClientDaoImpl;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.client.entity.query.ClientQuery;
import com.szit.arbitrate.client.entity.query.TempClientQuery;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.SmsVerifyRecordService;
import com.szit.arbitrate.client.service.TempClientService;
import com.szit.arbitrate.mediation.dao.MediationCaseDao;
import com.szit.arbitrate.mediation.entity.BasicData;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.entity.MediationCase;
import com.szit.arbitrate.mediation.entity.dto.MediationCaseInfoDto;
import com.szit.arbitrate.mediation.entity.enumvo.CaseStateEnum;
import com.szit.arbitrate.mediation.entity.query.BasicDataQuery;
import com.szit.arbitrate.mediation.entity.query.MediationCaseQuery;
import com.szit.arbitrate.mediation.service.BasicDataService;
import com.szit.arbitrate.mediation.service.MediationAgencyService;
import com.szit.arbitrate.mediation.service.MediationCaseService;


@Component("wapApiMediationCaseController")
public class ApiMediationCaseControllerImpl extends BaseController<MediationCase, MediationCaseQuery> implements ApiMediationCaseController{

	
	Logger logger = LoggerFactory.getLogger(ApiMediationCaseControllerImpl.class);
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private TempClientService tempClientService;
	@Autowired
	private MediationCaseService service;
	@Autowired
	private MediationAgencyService mediationAgencyService;
	@Autowired
	private BasicDataService basicDataService;
	@Autowired
	private MediationCaseDao dao;
	@Resource
	private ClientDaoImpl clientDaoImpl;
	@Override
	public ApiOutParamsVm addTempClient(String caseid, String name, String tel, String address, 
			String identify, boolean ispartb) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		TempClient tempClient = service.addTempClient(caseid, name, tel, address, identify, ispartb);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,tempClient);
	}
	
	@Override
	public ApiOutParamsVm deleteTempClient(String id) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.deleteTempClient(id);
		return ApiTools.bulidOutSucceed("删除成功!");
	}
	
	@Override
	public ApiOutParamsVm updateTempClientTel(String tempclientid, String tel) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		tempClientService.updateTempClientTel(tempclientid, tel);
		return ApiTools.bulidOutSucceed("修改成功!");
	}
	
	@Override
	public ApiOutParamsVm getMediationCaseDetail(String caseid) {
		//获取id
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String, Object> map = Maps.newHashMap();
		//获取组织机构
		MediationCase entity = service.getById(caseid);
		map.put("entity", entity);
		TempClientQuery TempClientQuery = new TempClientQuery();
		TempClientQuery.setCaseId(caseid);
		//按组织机构查找用户
		List<TempClient> list = tempClientService.getEntities(TempClientQuery);
		map.put("list", list);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,map);
	}
	
	@Override
	public ApiOutParamsVm applyMediation(String caseid,String clientid, String mediatetype,String casetype, String caseexplain, 
			String mediatorInfo, String jsonlist) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		if(StringUtils.isEmpty(jsonlist)){
			return ApiTools.bulidOutFail("非法参数,同步数据参数不能为空!");
		}
		JsonFormatUtil.printJson("传入的同步数据", jsonlist);
		JavaType javaType = jsonMapper.contructCollectionType(List.class, TClientInBo.class);
		List<TClientInBo> inbolist = jsonMapper.fromJson(jsonlist, javaType);
		
		service.applyMediation(caseid, clientId, mediatetype, casetype, caseexplain, mediatorInfo, inbolist);
		return ApiTools.bulidOutSucceed("申请成功!");
	}
	
	@Override
	public ApiOutParamsVm acceptMediationCase(String caseid, String mediatorid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.acceptMediationCase(caseid, clientId);
		return ApiTools.bulidOutSucceed("受理成功!");
	}
	
	/**
	 * clientid 当前登录员id
	 * caseid id
	 * mediatorid 被指派的调节员id
	 */
	@Override
	public ApiOutParamsVm allocateMediationCase(String clientid, String caseid,
			String mediatorid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.allocateMediationCase(clientId, caseid, mediatorid);
		HashMap<String,Object> ma = new HashMap<String,Object>();
		Client c = clientService.getById(mediatorid);
		ma.put("mediatorId", mediatorid);
		if(c != null){
			ma.put("mediatorName", c.getIdentifyName());
		}
		ma.put("caseid", caseid);
		return ApiTools.bulidOutSucceed("已受理成功请到管理里面查看!",ma);
	}
	
	@Override
	public ApiOutParamsVm refuseClientMediationApply(String clientid,String caseid, String refusereason) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.refuseClientMediationApplication(clientId, caseid, refusereason);
		return ApiTools.bulidOutSucceed("操作成功!");
	}
	
	@Override
	public ApiOutParamsVm backToUpperLevel(String clientid, String caseid,
			String reason) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.backToUpperLevel(clientId, caseid, reason);
		return ApiTools.bulidOutSucceed("操作成功!");
	}
	
	@Override
	public ApiOutParamsVm getMediationCaseListByClientId(String clientid, String casestate) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		List<MediationCase> list = service.getMediationCaseListByClientId(clientId, casestate);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,list);
	}
	
	public  static ApnsService servicesss;
	
//	public  void initApnService(String id) throws PushErrorException{
//		ApnsService apnsService = null;
//		ApnConfig apnConfig = null;
//		apnConfig = new ApnConfig(System.getProperty("arbitrateweb.root")+"/cers/HaiCangUser_dev.p12","123456",System.getProperty("arbitrateweb.root")+"/cers/HaiCangUser_dis.p12","123456",true);
////		apnConfig = new ApnConfig(System.getProperty("arbitrateweb.root")+"/cers/HaiCangManager_dev.p12","123456",System.getProperty("arbitrateweb.root")+"/cers/HaiCangManager_dis.p12","123456",true);
//		String apnCertificateName = apnConfig.getProdCertification();
//		String apnCertPassword = apnConfig.getProdCertPassword();
//		if (!apnConfig.isSendProductionMessage()) {
//			apnCertificateName = apnConfig.getDevCertification();
//			apnCertPassword = apnConfig.getDevCertPassword();
//		}
//		try {
//			//配置根证书
//			InputStream certFileStream = new FileInputStream(apnCertificateName);// SendExecutorMessageService.class.getResourceAsStream(apnCertificateName);
//			apnsService = APNS.newService()
//	                .withCert(certFileStream, apnCertPassword).withProductionDestination().build();
//	
//		} catch (Exception e) {
//			logger.error("IOS证书初始化失败系统异常错误", e);
//			throw new PushErrorException("IOS证书初始化失败系统异常错误",e);
//		}
//		if(servicesss==null){
//			 servicesss =  APNS.newService()  
//		    .withCert(System.getProperty("arbitrateweb.root")+"/cers/HaiCangManager_dev.p12", "123456")  
//		    .withSandboxDestination()  
//		    .build();  
//		}
//		
//		
//	}
	
	@Override
	public ApiOutParamsVm getListByClientIdAndCaseType(String clientid,
			Integer tabindex) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		List<MediationCaseInfoDto> list = service.getListByClientIdAndCaseType(clientid, tabindex);
		
		Client temp = clientService.getById(clientid);
		List<MediationCaseInfoDto> result = new ArrayList<MediationCaseInfoDto>();
			
		Client client = null;
		if(temp.getClientState()==ClientStateEnum.MediationCenter){
			for(MediationCaseInfoDto mediationCaseInfoDto:list){
				if(StringUtils.isNotEmpty(mediationCaseInfoDto.getMediatorId())){
					client = clientService.getById(mediationCaseInfoDto.getMediatorId());
					MediationAgency mediationAgency = mediationAgencyService.getEntityById(client.getMediationAgencyId());
					if(mediationAgency!=null){
						mediationCaseInfoDto.setMediatorName(client.getIdentifyName());
						mediationCaseInfoDto.setAgencyName(mediationAgency.getAgencyName());
					}
					result.add(mediationCaseInfoDto);
				}else{
					//分发中心
					mediationCaseInfoDto.setMediatorName("");
					mediationCaseInfoDto.setAgencyName("分配中心");
					result.add(mediationCaseInfoDto);
				}
				
			}
		}else{
			for(MediationCaseInfoDto mediationCaseInfoDto:list){
				//查询出调节机构的名字
				client = clientService.getById(mediationCaseInfoDto.getMediatorId());
				if(client!=null){
					Client c = clientService.getById(mediationCaseInfoDto.getMediatorId());
					if(c!=null && temp.getMediationAgencyId().equals(c.getMediationAgencyId())){
						MediationAgency mediationAgency = mediationAgencyService.getEntityById(client.getMediationAgencyId());
						if(mediationAgency!=null){
							mediationCaseInfoDto.setMediatorName(client.getIdentifyName());
							mediationCaseInfoDto.setAgencyName(mediationAgency.getAgencyName());
						}
						result.add(mediationCaseInfoDto);
					}
				}
			}
		}
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,result);
	}
	@Autowired
	private SmsVerifyRecordService smsVerifyRecordService;
	@Override
	public ApiOutParamsVm gatherTempClient(String tempclientid) {
		
		service.gatherTempClient(tempclientid);
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		return ApiTools.bulidOutSucceed("召集成功!");
	}
	
	@Override
	public ApiOutParamsVm operateMediationForSign(String caseid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.operateMediationForSign(caseid);
		return ApiTools.bulidOutSucceed("操作成功!");
	}
	
	@Override
	public ApiOutParamsVm getListForNormalClient(String clientid,
			Integer tabindex) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		List<MediationCaseInfoDto> list = service.getListForNormalClient(clientId, tabindex);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,list);
	}

	@Override
	public ApiOutParamsVm giveupMediation(String clientid, String caseid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.giveupMediation(clientId, caseid);
		return ApiTools.bulidOutSucceed("放弃");
	}
	
	/**
	 * clientid 人id
	 * caseid id
	 * failreason 终止原因
	 */
	@Override
	public ApiOutParamsVm closeMediation(String clientid, String caseid, String failreason) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.closeMediation(clientId, caseid, failreason);
		return ApiTools.bulidOutSucceed("操作成功");
	}

	/**
	 * 获取不同主体类型
	 * @return
	 */
	public ApiOutParamsVm getDifferentSubjects(){
		List<Map<String,Object>> ma = new ArrayList<Map<String,Object>>();
		Map<String,Object> son = new HashMap<String,Object>();
		son.put("id", "0");
		son.put("dataValue", "企事业单位调委会数");
		ma.add(son);
		son = new HashMap<String,Object>();
		son.put("id", "1");
		son.put("dataValue", "社会团体和其他组织调委会数");
		ma.add(son);
		son = new HashMap<String,Object>();
		son.put("id", "2");
		son.put("dataValue", "村调委会数");
		ma.add(son);
		son = new HashMap<String,Object>();
		son.put("id", "3");
		son.put("dataValue", "居调委会数");
		ma.add(son);
		son = new HashMap<String,Object>();
		son.put("id", "4");
		son.put("dataValue", "乡镇调委会数");
		ma.add(son);
		son = new HashMap<String,Object>();
		son.put("id", "5");
		son.put("dataValue", "街道调委会数");
		ma.add(son);
		return ApiTools.bulidOutSucceed("获取不同主体类型!",ma);
	}
	
	@Override
	public ApiOutParamsVm completeMediationCase(String caseid,String mediatetype, String casetype,
			boolean difficult, Integer people, String caseMoney,String protocolForm, String caseSource,Integer differenceSubject) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.completeMediationCase(caseid, mediatetype, casetype, clientId, difficult, people,
				caseMoney, protocolForm, caseSource, differenceSubject);
		return ApiTools.bulidOutSucceed("结案成功");
	}


	@Override
	public ApiOutParamsVm getCaseDistribution(String clientid,String startTime,String endTime,String mediationAgencyId) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		startTime = startTime==null?"":startTime;
		endTime = endTime==null?"":endTime;
		List<Map<String,Object>> li = new ArrayList<Map<String,Object>>();
		
		Map<String,Object> map = service.statisticsMediationCaseByClientId(clientid, startTime, endTime,mediationAgencyId);
		Map<String,Object> mapsuccess = service.statisticsMediationCaseByClientIdSuccess(clientid, startTime, endTime,mediationAgencyId);
		li.add(map);
		li.add(mapsuccess);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,li);
	}
	
	@Override
	public ApiOutParamsVm getBasicDateList(String datatype, String parentid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		BasicDataQuery query = new BasicDataQuery();
		if(StringUtils.isNotEmpty(datatype)){
			query.setDataType(datatype);
		}
		if(StringUtils.isNotEmpty(parentid)){
			query.setParentType(parentid);
		}
		List<BasicData> list = basicDataService.getEntities(query);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,list);
	}
	
	@Override
	public ApiOutParamsVm notifyClientToSignProtocolOffLine(String caseid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.notifyClientToSignProtocolOffLine(caseid);
		return ApiTools.bulidOutSucceed("通知成功!");
	}
	
	@Override
	public ApiOutParamsVm deleteMediationCaseById(String caseid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		MediationCase entity = service.getById(caseid);
		if(entity == null){
			throw new BizException("不存在");
		}
		if(!entity.getCaseState().equals(CaseStateEnum.GiveUp)){
			throw new BizException("已取消的才能删除");
		}
		service.deleteById(caseid);
		return ApiTools.bulidOutSucceed("删除成功!");
	}
	
	@Override
	public ApiOutParamsVm searchMediationCaseByName(String clientid, String casename) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		List<MediationCaseInfoDto> list = service.searchMediationCaseByName(clientId, casename);
		return ApiTools.bulidOutSucceed("获取数据成功!", list);
	}
	
	@Override
	public ApiOutParamsVm fileToZipByCaseId(String caseid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		} 
		Map<String, Object> result = service.fileToZipByCaseId(caseid);
		return ApiTools.bulidOutSucceed("导出数据成功!", result);
	}

	@Override
	public ApiOutParamsVm getClient(String clientid) {
		Client client =  clientService.getById(clientid);
		if(client==null)
			return ApiTools.bulidOutFail("该用户id不存在!");
		return ApiTools.bulidOutSucceed("查找用户成功!", client);
	}


//	@Override
//	public ApiOutParamsVm getMediation() {
//		String clientId = clientService.isLoginSessionOnline(getRequest());
//		if(StringUtils.isEmpty(clientId)){
//			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
//		}
//		HashMap<String,Object> ma = new HashMap<String,Object>();
//		ma.put("identify", "11111111111111111111111");
//		try {
//			List<Client> cc = clientDaoImpl.executeGets(ma, Client.class);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Client cl = clientService.getById(clientId);
//		List<Map<String, Object>> client =  clientService.getClientGroupDivisionClient("3a4a");
//		if(client==null)
//			return ApiTools.bulidOutFail("查询结果为空!");
//		return ApiTools.bulidOutSucceed("查找用户成功!", client);
//	}
}
