package com.szit.arbitrate.api.chat.action;

import org.springframework.stereotype.Controller;

import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.api.base.ApiAction;
import com.szit.arbitrate.api.common.ProjectModuleEnum;

@Controller("apiChatAction")
public class ChatAction extends ApiAction<DomainEntity, EntityQueryParam>{

	@Override
	public void apiInit() {
		this.setModule(ProjectModuleEnum.chat);
	       this.setApiDeug(true);
	}
	
}
