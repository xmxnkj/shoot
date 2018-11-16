package com.szit.arbitrate.api.mediation.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 
* @ClassName: ApiMediationAgencyController
* @author Administrator
* @date 2017年3月23日 下午5:32:34
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ApiMediationAgencyController {
	
	/**
	 * 
	* @Title: getMediationAgencyList 
	* @Description: 获取所有的机构列表
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getMediationAgencyList(String agencyname);
	
	/**
	 * 
	* @Title: getMediationAgencyDetail 
	* @Description: 获取调节机构详情
	* @param @param mediationagencyid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getMediationAgencyDetail(String mediationagencyid);
	
	/**
	 * 
	* @Title: getMediatorDetail 
	* @Description: 根据用户id查询员详情
	* @param @param clientid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getMediatorDetail(String clientid);
	
	/**
	 * 
	* @Title: searchMediationAgencyList 
	* @Description: 根据条件筛选机构列表
	* @param @param address
	* @param @param casetype
	* @param @param agencytype
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm searchMediationAgencyList(String agencytype, String casetype, String agencyname,String lon,String lat);

	/**
	 * 
	* @Title: searchOpenedMediationAgencyList 
	* @Description: 获取已开通线上服务的机构列表，用于调节中心分配
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm searchOpenedMediationAgencyList();
	
	/**
	 * 
	* @Title: searchOpenedMediationAgencyList 
	* @Description: 获取已开通线上服务的机构列表，用于调节中心分配
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm searchAgencyOrMediatorList(String type,String mediatorOrAgency,String key,int page,String lon,String lat);
}
