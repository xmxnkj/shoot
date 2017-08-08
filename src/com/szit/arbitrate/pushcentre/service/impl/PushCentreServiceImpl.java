/**   
* @Title: PushCentreServiceImpl.java
* @Package com.szit.cowell.pushcentre.service.impl
* @Description: TODO
* @author XUJC
* @date 2017年11月1日 下午9:29:27
* @version V1.0   
*/


package com.szit.arbitrate.pushcentre.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.dao.Dao;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.hsit.common.utils.JsonMapper;
import com.szit.arbitrate.api.log.entity.PushRecordLog;
import com.szit.arbitrate.api.log.service.PushRecordLogService;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.ClientToken;
import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.TerminalType;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.ClientTokenService;
import com.szit.arbitrate.client.service.TerminalService;
import com.szit.arbitrate.pushcentre.common.PushMessageUtil;
import com.szit.arbitrate.pushcentre.service.PushCentreService;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

/**
 * @ClassName: PushCentreServiceImpl
 * @Description:
 * @author XUJC
 * @date 2017年
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
@Service
@Transactional
public class PushCentreServiceImpl extends BaseServiceImpl<DomainEntity, EntityQueryParam> implements
		PushCentreService {
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private PushRecordLogService pushRecordLogService;
	@Autowired
	private TerminalService terminalService;
	@Autowired
	private ClientTokenService clientTokenService;
	
	@Override
	protected Dao<DomainEntity, EntityQueryParam> getDao() {
		return null;
	}


	@Override
	public void pushUnifyMessage(PushTypeEnum pushtype,String sendClientId,
			                     boolean isSendPush,String receiveClientId,
                                 String checkCode,String pushAlertMessage,
                                 Object pushCustomData,int apnsCount)throws BizException,ErrorException {
		if(StringUtils.isEmpty(receiveClientId)){
			throw new BizException("接收会员ID不能为空");
		}
		/*if(isSendPush){//发送者也推送
			this.findTerminalPushByClientId(sendClientId, pushAlertMessage, pushCustomData, pushtype, apnsCount);
		}*/
		//给接收的人推送一条信息
		this.findTerminalPushByClientId(receiveClientId, pushAlertMessage, pushCustomData, pushtype, apnsCount);
	}
	
	@Override
	public void pushMqtt(PushTypeEnum pushtype, String sendClientId,
			             boolean isSendPush, String receiveClientId, String checkCode,
			             String pushAlertMessage, Object pushCustomData, int apnsCount)
			                   throws BizException, ErrorException {
		if(StringUtils.isEmpty(receiveClientId)){
			throw new BizException("接收会员ID不能为空");
		}
		if(isSendPush){//发送者也推送
			this.findTerminalPushByClientIdForAndroid(sendClientId, pushAlertMessage, pushCustomData, pushtype, apnsCount);
		}
		//给接收的人推送一条信息
		this.findTerminalPushByClientIdForAndroid(receiveClientId, pushAlertMessage, pushCustomData, pushtype, apnsCount); 
	}

	
	@Override
	public void pushMqtt(PushTypeEnum pushtype, String sendClientId,
			String pushTopic, String pushAlertMessage, Object pushCustomData,
			int apnsCount) {
		Client client = clientService.getById(sendClientId);
		PushMessageUtil.getInstance().mqttOrApnsSendMessage(pushTopic, pushAlertMessage,pushCustomData,null, apnsCount);
		PushRecordLog pushrecordlog = new PushRecordLog();
		pushrecordlog.setPushtype(pushtype);
		pushrecordlog.setPushmode("ANDRID MQTT");
		pushrecordlog.setPushkeycode(pushTopic);
		pushrecordlog.setApnscount(apnsCount);
		pushrecordlog.setPushcontent(JsonMapper.getInstance().toJson(pushCustomData));
		pushrecordlog.setPushdatetime(new Date());
		pushrecordlog.setRemark("会员:"+client.getNickName()+",进行扫二维码登陆进行MQTT推送");
	    pushRecordLogService.save(pushrecordlog);
	}
	/**
	 * 
	* 方法描述:android设备
	* 创建人: XUJC
	* 创建时间：2017年12月25日
	* @param pushClinetId
	* @param pushAlertMessage
	* @param pushCustomData
	* @param pushtype
	* @param apnsCount
	 */
	private void  findTerminalPushByClientIdForAndroid(String pushClinetId,String pushAlertMessage,
			Object pushCustomData,PushTypeEnum pushtype,
			int apnsCount){
		PushRecordLog pushrecordlog = null;
		Client client = clientService.getById(pushClinetId);
		PushMessageUtil.getInstance().mqttOrApnsSendMessage(client.getAccount(), pushAlertMessage,pushCustomData,null, apnsCount);
		pushrecordlog = new PushRecordLog();
		pushrecordlog.setPushtype(pushtype);
		pushrecordlog.setPushmode("ANDRID MQTT");
		pushrecordlog.setPushkeycode(client.getAccount());
		pushrecordlog.setApnscount(apnsCount);
		pushrecordlog.setPushcontent(JsonMapper.getInstance().toJson(pushCustomData));
		pushrecordlog.setPushdatetime(new Date());
		pushRecordLogService.save(pushrecordlog);
	
	}	
	
	
	/**
	 * 
	* 方法描述:查询设备进行推送通用方法
	* 创建人: XUJC
	* 创建时间：2017年12月16日
	* @param pushClinetId
	* @param pushAlertMessage
	* @param pushCustomData
	* @param pushtype
	* @param apnsCount
	 */
	private void  findTerminalPushByClientId(String pushClinetId,String pushAlertMessage,
                                             Object pushCustomData,PushTypeEnum pushtype,
                                             int apnsCount){
		
		
		Client client = clientService.getById(pushClinetId);
		ClientToken clientToken = clientTokenService.getClientTokenByClientId(pushClinetId);
		if(clientToken != null){
			
			PushRecordLog pushrecordlog = new PushRecordLog();
			pushrecordlog.setPushtype(pushtype);
			pushrecordlog.setApnscount(apnsCount);
			pushrecordlog.setPushcontent(JsonMapper.getInstance().toJson(pushCustomData));
			pushrecordlog.setPushdatetime(new Date());
			pushrecordlog.setRemark(client.getId());
			String iostoken = "";
			
			if(clientToken.getTerminalType().equals(TerminalType.ANDROID)){
				if(client.getClientType().equals(ClientTypeEnum.Mediator)){
					iostoken="Mediator_ANDROID";
				}else{
					iostoken="Normal_ANDROID";
				}
				PushMessageUtil.getInstance().mqttOrApnsSendMessage(clientToken.getUuid(), pushAlertMessage,pushCustomData,iostoken, apnsCount);
				pushrecordlog.setPushmode("ANDRID MQTT");
				pushrecordlog.setPushkeycode(client.getAccount());
			}else if(clientToken.getTerminalType().equals(TerminalType.IOS)){
				if(client.getClientType().equals(ClientTypeEnum.Mediator)){
					iostoken="Mediator_IOS";
				}else{
					iostoken="Normal_IOS";
				}
				PushMessageUtil.getInstance().mqttOrApnsSendMessage(clientToken.getUuid(), pushAlertMessage,pushCustomData,iostoken, apnsCount);
				//pushrecordlog.setPushkeycode(terminal.getTerminalCode());
				pushrecordlog.setPushkeycode(client.getAccount()+"_"+client.getClientType().toString());
				pushrecordlog.setPushmode("IOS");
			}
			
			pushRecordLogService.save(pushrecordlog);
			
		}
	}

}
