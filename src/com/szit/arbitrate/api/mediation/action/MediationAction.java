package com.szit.arbitrate.api.mediation.action;

import org.springframework.stereotype.Controller;

import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.api.base.ApiAction;
import com.szit.arbitrate.api.common.ProjectModuleEnum;

@Controller("apiMediationAction")
public class MediationAction extends ApiAction<DomainEntity, EntityQueryParam>{

	@Override
	public void apiInit() {
       this.setModule(ProjectModuleEnum.mediation);
       this.setApiDeug(true);
	}
	
}
