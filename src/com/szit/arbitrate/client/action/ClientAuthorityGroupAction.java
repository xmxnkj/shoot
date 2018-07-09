package com.szit.arbitrate.client.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.szit.arbitrate.client.entity.ClientAuthorityGroup;
import com.szit.arbitrate.client.entity.query.ClientAuthorityGroupQuery;
import com.szit.arbitrate.client.service.ClientAuthorityGroupService;

@Namespace("/admin/client/clientauthoritygroup")
@Controller("clientAuthorityGroupAction")
public class ClientAuthorityGroupAction extends BaseJsonAction<ClientAuthorityGroup, ClientAuthorityGroupQuery>{

	@Autowired
	private ClientAuthorityGroupService service;

	public ClientAuthorityGroupService getService() {
		return service;
	}

	public void setService(ClientAuthorityGroupService service) {
		this.service = service;
	}
	
}
