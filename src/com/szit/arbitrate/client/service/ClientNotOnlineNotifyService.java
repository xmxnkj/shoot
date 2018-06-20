package com.szit.arbitrate.client.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.client.entity.ClientNotOnlineNotify;
import com.szit.arbitrate.client.entity.query.ClientNotOnlineNotifyQuery;

public interface ClientNotOnlineNotifyService extends BaseService<ClientNotOnlineNotify, ClientNotOnlineNotifyQuery>{

	/**
	 * 
	* @Title: getClientNotOnlineNotifyByClientCase  
	* @Description: 
	* @param @param clientid
	* @param @param caseid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return ClientNotOnlineNotify    返回类型  
	* @throws
	 */
	public ClientNotOnlineNotify getClientNotOnlineNotifyByClientCase(String clientid, String caseid)
			throws BizException,ErrorException;
	
}
