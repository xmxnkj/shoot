package com.szit.arbitrate.pushcentre.factory.product.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.hsit.common.utils.SpringBeanUtil;
import com.szit.arbitrate.pushcentre.exception.PushBizExcetion;
import com.szit.arbitrate.pushcentre.exception.PushErrorException;
import com.szit.arbitrate.pushcentre.factory.product.IPushDisposeProduct;
import com.szit.arbitrate.pushcentre.service.PushCentreService;
import com.szit.arbitrate.pushcentre.vm.PushContentVm;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

public class BuildSystemNotifyProduct implements IPushDisposeProduct{
	
	private PushCentreService pushCentreService;
	
	@Override
	public void buildPushDispose(Map<String, Object> params ,PushTypeEnum pushTypeEnum)
			throws PushBizExcetion, PushErrorException {
		pushCentreService = (PushCentreService)SpringBeanUtil.getBean("pushCentreServiceImpl");
		String pushClientId = "";
		if(params.containsKey("pushClientId")){
			pushClientId = (String)params.get("pushClientId");
		}
		String pushAlertMessage = "";
		if(params.containsKey("pushAlertMessage")){
			pushAlertMessage = (String)params.get("pushAlertMessage");
		}
		String caseId = "";
		if(params.containsKey("caseId")){
			caseId = (String)params.get("caseId");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if(StringUtils.isNotEmpty(caseId)){
			map.put("caseId", caseId);
		}
		PushContentVm pushcontent = new PushContentVm("1", "系统", 
				"1.jpg", pushTypeEnum, map);
		pushCentreService.pushUnifyMessage(pushTypeEnum, "", false, pushClientId,
				"", pushAlertMessage, pushcontent, 1);
	}

}
