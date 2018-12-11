package com.szit.arbitrate.api.client.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.client.controller.ClientAuthorityGroupController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.client.entity.ClientAuthorityGroup;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;
import com.szit.arbitrate.client.entity.query.ClientAuthorityGroupQuery;
import com.szit.arbitrate.client.service.ClientAuthorityGroupService;

@Component("apiClientAuthorityGroupController")
public class ClientAuthorityGroupControllerImpl extends BaseController<ClientAuthorityGroup, ClientAuthorityGroupQuery>
implements ClientAuthorityGroupController{
	
	@Autowired
	ClientAuthorityGroupService clientAuthorityGroupService;
	
	@Override
	public ApiOutParamsVm setClientAuthorityGroup(String clientId,String authorityGroupName) {
		System.out.println(clientId+"   "+authorityGroupName);
		clientAuthorityGroupService.setClientAuthorityGroup(clientId, AuthorityGroupEnum.valueOf(authorityGroupName));
		return ApiTools.bulidOutSucceed("新增权限");
	}
}