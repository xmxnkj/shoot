package com.szit.arbitrate.api.client.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

public interface ClientAuthorityGroupController {
	
	public ApiOutParamsVm setClientAuthorityGroup(String clientId,String authorityGroupName);

}
