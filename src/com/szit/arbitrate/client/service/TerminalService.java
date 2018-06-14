package com.szit.arbitrate.client.service;

import java.util.List;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.client.entity.Terminal;
import com.szit.arbitrate.client.entity.enumvo.TerminalType;
import com.szit.arbitrate.client.entity.query.TerminalQuery;

public interface TerminalService extends BaseService<Terminal, TerminalQuery>{
	
	/**
	 * 
	* @Title: findByClinetIdForAndroid 
	* @Description: TODO
	* @param @param clientId
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return Boolean 
	* @throws
	 */
    public Boolean findByClinetIdForAndroid(String clientId)throws BizException,ErrorException;
	
    /**
     * 
    * @Title: findByClinetIdForIOS 
    * @Description: 根据会员ID之IOS
    * @param @param clientId
    * @param @return
    * @param @throws BizException
    * @param @throws ErrorException
    * @return List<Terminal> 
    * @throws
     */
    public List<Terminal> findByClinetIdForIOS(String clientId)throws BizException,ErrorException;
    
    /**
     * 
    * @Title: findByClinetIdAndTypeAnCodeToList 
    * @Description: 根据设备类型会员ID
    * @param @param clientId
    * @param @param terminalType
    * @param @param terminalCode
    * @param @return
    * @param @throws BizException
    * @param @throws ErrorException
    * @return Terminal 
    * @throws
     */
	public Terminal findByClinetIdAndTypeAnCodeToList(String clientId,TerminalType terminalType,String terminalCode)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: findByTerminalCode 
	* @Description: 通过终端代码取得终端对象
	* @param @param terminalCode
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return Terminal 
	* @throws
	 */
	public Terminal findByTerminalCode(String terminalCode)throws BizException,ErrorException;

}
