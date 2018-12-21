package com.szit.arbitrate.api.chat.controller.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.chat.controller.ApiMessagePushController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.chat.entity.MessagePush;
import com.szit.arbitrate.chat.entity.query.MessagePushQuery;
import com.szit.arbitrate.chat.service.MessagePushService;
import com.szit.arbitrate.client.service.ClientService;

@Component("wapApiMessagePushController")
public class ApiMessagePushControllerImpl extends BaseController<MessagePush, MessagePushQuery>
	implements ApiMessagePushController{
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private MessagePushService service;

	
	@Override
	public ApiOutParamsVm getNotReadMessageCountByRecType(
			String requestclientid, String goalid, String goaltype) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		long count = service.getNotReadMessageCountByRecType(clientId, goalid, goaltype);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,count);
	}
	
	@Override
	public ApiOutParamsVm readMessage(String clientid, String goalid,
			String goaltype) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.readMessage(clientId, goalid, goaltype);
		return ApiTools.bulidOutSucceed("操作成功!");
	}
	
}
