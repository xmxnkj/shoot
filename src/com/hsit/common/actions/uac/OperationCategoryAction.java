/**
 * File Name: OperationCategoryAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.uac;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.uac.entity.OperationCategory;
import com.hsit.common.uac.entity.queryparam.OperationCategoryQuery;
import com.hsit.common.uac.service.OperationCategoryService;

/**
 * @ClassName:OperationCategoryAction
 * @date 2017-3-4 下午5:06:12
 * 
 */
@Component("operationCategoryAction")
@Scope("prototype")
public class OperationCategoryAction extends BaseAction<OperationCategory, OperationCategoryQuery> {
	private OperationCategoryService service;

	@Override
	public OperationCategoryService getService() {
		return service;
	}

	@Autowired
	public void setService(OperationCategoryService service) {
		this.service = service;
	}
	
	/* (non-Javadoc)
	 * @see com.hsit.common.actions.BaseAction#getEntityJson(com.hsit.common.kfbase.entity.DomainEntity)
	 */
	@Override
	protected JSONObject getEntityJson(OperationCategory entity) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = super.getEntityJson(entity);
		jsonObject.element("parentId", entity.getParentId());
		return jsonObject;
	}
	
	private String actorId;
	
	private String actorType;

	public String getActorId() {
		return actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public String getActorType() {
		return actorType;
	}

	public void setActorType(String actorType) {
		this.actorType = actorType;
	}
	
	
}
