package com.szit.arbitrate.api.mediation.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

public interface ApiMediationResourceController {
	
	/**
	 * 
	* @Title: getMediationResourceListByCaseId 
	* @param @param caseid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getMediationResourceListByCaseId(String caseid);
	
	/**
	 * 
	* @Title: saveMediationReource 
	* @param @param clientid
	* @param @param caseid
	* @param @param chatmessageid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm saveMediationReource(String clientid, String caseid, String chatmessageid);

}
