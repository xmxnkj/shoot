package com.szit.arbitrate.api.mediation.controller.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsit.common.exceptions.BizException;
import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.mediation.controller.ApiMediationRecordController;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.entity.MediationRecord;
import com.szit.arbitrate.mediation.entity.query.MediationRecordQuery;
import com.szit.arbitrate.mediation.service.MediationRecordService;

@Component("wapApiMediationRecordController")
public class ApiMediationRecordControllerImpl extends BaseController<MediationRecord, MediationRecordQuery>
implements ApiMediationRecordController{
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private MediationRecordService mediationRecordService;
	
	@Override
	public ApiOutParamsVm getMediationRecord(String mediationRecordId) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		if(StringUtils.isEmpty(mediationRecordId)){
			throw new BizException("参数不能空!");
		}
		MediationRecord mediationRecord = mediationRecordService.getById(mediationRecordId);
		return ApiTools.bulidOutSucceed("数据获取成功!",mediationRecord);
	}
	
	
	@Override
	public ApiOutParamsVm getMediationRecordList(String caseId, String recordtype, int pageNum) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String,Object> resultMap = mediationRecordService.getMediationRecordList(caseId, recordtype, pageNum);
		return ApiTools.bulidOutSucceed("获取成功!",resultMap);
	}

	
	@Override
	public ApiOutParamsVm saveMediationRecord(String caseid,String recordcontent,String involvedperson,
			String time,String address, String joinPerson,String recordtype) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		MediationRecord entity = mediationRecordService.saveMediationRecord(caseid, recordcontent, 
				involvedperson, time, address,joinPerson,recordtype);
		return ApiTools.bulidOutSucceed("保存成功!", entity);
	}

	
	@Override
	public ApiOutParamsVm updateMediationRecord(String mediationrecordid,String recordcontent,
			String involvedperson,String time,String address, String joinPerson,String recordtype) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
			mediationRecordService.modifyMediationRecord(mediationrecordid, recordcontent, involvedperson, 
					time, address, joinPerson, recordtype);
		return ApiTools.bulidOutSucceed("修改成功!");
	}


	@Override
	public ApiOutParamsVm removeMediationRecord(String mediationRecordId) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		mediationRecordService.deleteById(mediationRecordId);
		return ApiTools.bulidOutSucceed("删除成功!");
	}
	
	
}