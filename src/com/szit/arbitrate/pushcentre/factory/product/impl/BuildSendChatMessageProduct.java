package com.szit.arbitrate.pushcentre.factory.product.impl;

import java.util.Map;

import com.google.common.collect.Maps;
import com.hsit.common.utils.SpringBeanUtil;
import com.szit.arbitrate.chat.entity.MessagePush;
import com.szit.arbitrate.pushcentre.exception.PushBizExcetion;
import com.szit.arbitrate.pushcentre.exception.PushErrorException;
import com.szit.arbitrate.pushcentre.factory.product.IPushDisposeProduct;
import com.szit.arbitrate.pushcentre.service.PushCentreService;
import com.szit.arbitrate.pushcentre.vm.PushContentVm;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

public class BuildSendChatMessageProduct implements IPushDisposeProduct{

	private PushCentreService pushCentreService;
	
	@Override
	public void buildPushDispose(Map<String, Object> params,
			PushTypeEnum pushTypeEnum) throws PushBizExcetion,
			PushErrorException {
		pushCentreService = (PushCentreService)SpringBeanUtil.getBean("pushCentreServiceImpl");
		String receiveClientId = "";
		if(params.containsKey("receiveClientId")){
			receiveClientId = (String)params.get("receiveClientId");
		}
		String pushAlertMessage = "";
		if(params.containsKey("alertMessage")){
			pushAlertMessage = (String)params.get("alertMessage");
		}
		String caseId = "";
		if(params.containsKey("caseId")){
			caseId = (String)params.get("caseId");
		}
		String casestate = "";
		if(params.containsKey("casestate")){
			casestate = (String)params.get("casestate");
		}
		MessagePush messagePush = null;
		if(params.containsKey("messagePush")){
			messagePush = (MessagePush) params.get("messagePush");
		}
		Map<String, Object> map = Maps.newHashMap();
		map.put("casestate", casestate);
		PushContentVm pushcontent = new PushContentVm(casestate, caseId, 
				"", pushTypeEnum, messagePush);
		pushCentreService.pushUnifyMessage(pushTypeEnum, "", false, receiveClientId,
				"", pushAlertMessage, pushcontent, 1);
	}
	
}
