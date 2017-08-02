package com.szit.arbitrate.pushcentre.junit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.pushcentre.service.PushCentreService;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;


public class PushCentreJunit extends BaseApiJunitTest{
	
	@Autowired
	private PushCentreService pushCentreService;
	
	@Test
	public void sendIosMessage(){
		String receiveClientId = "5bf985a8-dcb9-4bf0-a6b0-897841c97aa6";
		pushCentreService.pushUnifyMessage(PushTypeEnum.MediateState, "", false, receiveClientId, "", "测试ios推送", "", 1);
		//PushMessageUtil.getInstance().mqttOrApnsSendMessage("15959017393", "测试ios推送", "", "Normal", 1);
	}

}
