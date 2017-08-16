package com.szit.arbitrate.pushcentre.factory.product.impl;

import java.util.Map;

import com.hsit.common.utils.SpringBeanUtil;
import com.szit.arbitrate.pushcentre.exception.PushBizExcetion;
import com.szit.arbitrate.pushcentre.exception.PushErrorException;
import com.szit.arbitrate.pushcentre.factory.product.IPushDisposeProduct;
import com.szit.arbitrate.pushcentre.service.PushCentreService;
import com.szit.arbitrate.pushcentre.vm.PushContentVm;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

public class BuildCertificateProduct implements IPushDisposeProduct{

	private PushCentreService pushCentreService;
	
	@Override
	public void buildPushDispose(Map<String, Object> params ,PushTypeEnum pushTypeEnum)
			throws PushBizExcetion, PushErrorException {
		pushCentreService = (PushCentreService)SpringBeanUtil.getBean("pushCentreServiceImpl");
		String receiveClientId = (String)params.get("receiveClientId");
		String pushAlertMessage = (String)params.get("pushAlertMessage");
		PushContentVm pushcontent = new PushContentVm("1", "系统", 
				"1.jpg", PushTypeEnum.Certificate, null);
		pushCentreService.pushUnifyMessage(pushTypeEnum, "", false, receiveClientId,
				"", pushAlertMessage, pushcontent, 1);
	}
}