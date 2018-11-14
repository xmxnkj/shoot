package com.szit.arbitrate.api.mediation.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 
* @ClassName: ApiMediationProtocolController
* @author Administrator
* @date 2017年4月1日 上午11:11:50
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ApiMediationProtocolController {

	/**
	 * 
	* @Title: getMediationProtocol 
	* @param @param caseid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getMediationProtocol(String caseid);
	
	/**
	 * 
	* @Title: createMediationProtocol 
	* @Description: 生成协议书
	* @param @param protocoljson 协议书json数据
	* @param @param tempclientjsonlist 当事人json列表数据
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm createMediationProtocol(String entityjson, String jsonlist);
	
	/**
	 * 
	* @Title: operateMediationProtocol 
	* @param @param clientid
	* @param @param protocolid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm operateMediationProtocol(String clientid, String protocolid, Integer type);
	
	/**
	 * 
	* @Title: notifyProtocolToClient 
	* @param @param protocolid
	* @param @param smscontent
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm notifyProtocolToClient(String protocolid, String smscontent);
	
	/**
	 * 
	* @Title: downloadMediationProtocol 
	* @param @param protocolid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm downloadMediationProtocol(String protocolid);
	
}
