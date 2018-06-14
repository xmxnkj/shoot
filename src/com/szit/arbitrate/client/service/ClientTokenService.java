package com.szit.arbitrate.client.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.client.entity.ClientToken;
import com.szit.arbitrate.client.entity.query.ClientTokenQuery;

/**
 * 
* @ClassName: ClientTokenService  
* @Description: 用户登录设备信息业务接口类  
* @author   
* @date 2017年6月6日 上午10:28:30  
* @Copyright
* @versions:1.0 
*
 */
public interface ClientTokenService extends BaseService<ClientToken, ClientTokenQuery>{
	
	/**
	 * 
	* @Title: getClientTokenByClientId  
	* @Description: 根据用户id获取设备登录信息
	* @param @param clientid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return ClientToken    返回类型  
	* @throws
	 */
	public ClientToken getClientTokenByClientId(String clientid)throws BizException, ErrorException;

}
