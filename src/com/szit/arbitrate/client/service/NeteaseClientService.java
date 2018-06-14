package com.szit.arbitrate.client.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.client.entity.NeteaseClient;
import com.szit.arbitrate.client.entity.query.NeteaseClientQuery;

/**
 * 
* @ClassName: NeteaseClientService  
* @author   
* @date 2017年6月2日 上午10:07:39  
* @Copyright
* @versions:1.0 
*
 */
public interface NeteaseClientService extends BaseService<NeteaseClient, NeteaseClientQuery>{
	
	/**
	 * 
	* @Title: openNeteaseCloud 
	* @Description: 开启网易视频通信
	* @param @param clientid
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public NeteaseClient createNeteaseCloudAccount(String clientid)throws BizException, ErrorException;
	
	/**
	 * 
	* @Title: GetNeteaseCloudAccount  
	* @param @param clientid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return NeteaseClient    返回类型  
	* @throws
	 */
	public NeteaseClient GetNeteaseCloudAccount(String clientid)throws BizException, ErrorException;

}
