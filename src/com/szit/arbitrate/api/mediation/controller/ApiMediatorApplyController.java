package com.szit.arbitrate.api.mediation.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

public interface ApiMediatorApplyController {
	
	/**
	 * 
	* @Title: saveApplyMediator 
	* @param @param clientId
	* @param @param applyReason
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm saveApplyMediator(String clientId ,String applyReason);
	
	/**
	 * 
	* @Title: getMediatorApply 
	* @param @param clientid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getMediatorApply(String clientid);

}
