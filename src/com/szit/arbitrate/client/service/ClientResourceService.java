package com.szit.arbitrate.client.service;

import java.io.File;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.client.entity.ClientResource;
import com.szit.arbitrate.client.entity.enumvo.ResTypeEnum;
import com.szit.arbitrate.client.entity.query.ClientResourceQuery;

public interface ClientResourceService extends BaseService<ClientResource, ClientResourceQuery>{
	
	/**
	 * 
	* @Title: uploadGoodsResources 
	* @Description: 上传资源
	* @param @param uploadFile，uploadFileFileName
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return String 
	* @throws
	 */
	public String uploadClientResources(String clientId,File uploadFile,
			String uploadFileFileName,String resUploadFilePath,ResTypeEnum resType)throws BizException,ErrorException;

}
