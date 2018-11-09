package com.szit.arbitrate.api.mediation.controller.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.mediation.controller.ApiMediatorApplyController;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.entity.MediatorApply;
import com.szit.arbitrate.mediation.entity.query.MediatorApplyQuery;
import com.szit.arbitrate.mediation.service.MediatorApplyService;

@Component("wapApiMediatorApplyController")
public class ApiMediatorApplyControllerImpl extends BaseController<MediatorApply, MediatorApplyQuery>
 implements ApiMediatorApplyController{
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private MediatorApplyService mediatorApplyService;

	@Override
	public ApiOutParamsVm saveApplyMediator(String clientId, String applyReason) {
		clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		MediatorApply applyResult = mediatorApplyService.saveApplyMediator(clientId, applyReason);
		return ApiTools.bulidOutSucceed("申请成功!",applyResult);
	}
	
	@Override
	public ApiOutParamsVm getMediatorApply(String clientid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		MediatorApply entity = mediatorApplyService.getMediatorApply(clientId);
		return ApiTools.bulidOutSucceed("获取成功!",entity);
	}
	
	
}
