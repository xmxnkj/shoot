package com.szit.arbitrate.pushcentre.factory.product.impl;

import java.util.List;
import java.util.Map;

import com.hsit.common.utils.SpringBeanUtil;
import com.szit.arbitrate.pushcentre.exception.PushBizExcetion;
import com.szit.arbitrate.pushcentre.exception.PushErrorException;
import com.szit.arbitrate.pushcentre.factory.product.IPushDisposeProduct;
import com.szit.arbitrate.pushcentre.service.PushCentreService;
import com.szit.arbitrate.pushcentre.vm.PushContentVm;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

public class BuildNotifyToSignProtocolProduct implements IPushDisposeProduct{
	
private PushCentreService pushCentreService;
	
	@Override
	public void buildPushDispose(Map<String, Object> params ,PushTypeEnum pushTypeEnum)
			throws PushBizExcetion, PushErrorException {
		
		pushCentreService = (PushCentreService)SpringBeanUtil.getBean("pushCentreServiceImpl");
		
		List<String> pushclientlist = null;
		if(params.containsKey("pushclientlist")){
			pushclientlist = (List<String>) params.get("pushclientlist");
		}
		String pushAlertMessage = (String)params.get("pushAlertMessage");
		
		PushContentVm pushcontent = new PushContentVm("1", "系统", 
				"1.jpg", pushTypeEnum, null);
		
		if(pushclientlist != null && pushclientlist.size() > 0){
			for(int i = 0; i < pushclientlist.size(); i ++){
				String pushClientId = pushclientlist.get(i);
				pushCentreService.pushUnifyMessage(pushTypeEnum, "", false, pushClientId,
						"", pushAlertMessage, pushcontent, 1);
			}
		}
	}

}
