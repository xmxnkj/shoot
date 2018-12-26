package com.szit.arbitrate.api.chat.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 
* @ClassName: ApiMessagePushController
* @Description:消息分发API控制层接口类
* @author Administrator
* @date 2017年4月24日 上午9:55:31
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ApiMessagePushController {
	
	
	/**
	 * 
	* @Title: getNotReadMessageCountByRecType 
	* @Description: 获取未读消息数量
	* @param @param requestclientid
	* @param @param goalid
	* @param @param goaltype
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getNotReadMessageCountByRecType(String requestclientid,
			String goalid, String goaltype);
	
	/**
	 * 
	* @Title: readMessage 
	* @Description: 读取消息
	* @param @param clientid
	* @param @param goalid
	* @param @param goaltype
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm readMessage(String clientid, String goalid, String goaltype);

}
