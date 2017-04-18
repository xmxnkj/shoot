/**
 * File Name: AceAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.uac;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hsit.common.uac.entity.Ace;
import com.hsit.common.uac.entity.Actor;
import com.hsit.common.uac.entity.queryparam.AceQuery;
import com.hsit.common.uac.service.AceService;

import com.hsit.common.actions.BaseAction;

/**
 * @ClassName:AceAction
 * @date 2017-3-4 下午10:55:53
 * 
 */
@Component("aceAction")
@Scope("prototype")
public class AceAction extends BaseAction<Ace, AceQuery> {
	private AceService service;

	@Override
	public AceService getService() {
		return service;
	}

	@Autowired
	public void setService(AceService service) {
		this.service = service;
	}
	
	private Actor actor;

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
	
	private String operationIds;
	
	public String getOperationIds() {
		return operationIds;
	}

	public void setOperationIds(String operationIds) {
		this.operationIds = operationIds;
	}
	
	/* (non-Javadoc)
	 * @see com.hsit.common.actions.BaseAction#getEntityJson(com.hsit.common.kfbase.entity.DomainEntity)
	 */
	@Override
	protected JSONObject getEntityJson(Ace entity) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = super.getEntityJson(entity);
		
		jsonObject.element("operationId", entity.getOperationId());
		
		return jsonObject;
	}

	public void saveActorOperationsJson(){
		if (actor != null
				&& !StringUtils.isEmpty(actor.getId())
				&& !StringUtils.isEmpty(actor.getActorType())
				) {
			
			service.setActorOperations(actor, operationIds);
			outSuccessJson();
			return;
		}
		outFailJson("必须设置Actor", null);
	}
}
