package com.szit.arbitrate.api.mediation.controller.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsit.common.exceptions.BizException;
import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.mediation.controller.ApiMediationResourceController;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.entity.MediationResource;
import com.szit.arbitrate.mediation.entity.query.MediationResourceQuery;
import com.szit.arbitrate.mediation.service.MediationResourceService;

@Component("wapApiMediationResourceController")
public class ApiMediationResourceControllerImpl extends BaseController<MediationResource, MediationResourceQuery>
	implements ApiMediationResourceController{
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private MediationResourceService service;
	
	@Override
	public ApiOutParamsVm getMediationResourceListByCaseId(String caseid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		MediationResourceQuery query = new MediationResourceQuery();
		if(StringUtils.isEmpty(caseid)){
			throw new BizException("参数不能为空!");
		}
		query.setCaseId(caseid);
		List<MediationResource> list = service.getEntities(query);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,list);
	}
	
	@Override
	public ApiOutParamsVm saveMediationReource(String clientid, String caseid,
			String chatmessageid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.saveMediationReource(clientid, caseid, chatmessageid);
		return ApiTools.bulidOutSucceed("保存成功!");
	}

}
