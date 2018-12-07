package com.szit.arbitrate.api.client.action;

import org.springframework.stereotype.Controller;

import com.szit.arbitrate.api.base.ApiAction;
import com.szit.arbitrate.api.common.ProjectModuleEnum;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.query.ClientQuery;

@Controller("apiClientAction")
public class ClientAction extends ApiAction<Client, ClientQuery>{

	@Override
	public void apiInit() {
       this.setModule(ProjectModuleEnum.client);
       this.setApiDeug(true);
	}

}
