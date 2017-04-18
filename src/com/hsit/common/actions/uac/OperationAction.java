/**
 * File Name: OperationAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.uac;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.uac.entity.Operation;
import com.hsit.common.uac.entity.OperationCategory;
import com.hsit.common.uac.entity.queryparam.OperationQuery;
import com.hsit.common.uac.service.OperationCategoryService;
import com.hsit.common.uac.service.OperationService;

/**
 * @ClassName:OperationAction
 * @date 2017-3-4 下午5:19:29
 * 
 */
@Component("operationAction")
@Scope("prototype")
public class OperationAction  extends BaseAction<Operation, OperationQuery>{

	private OperationService service;

	@Override
	public OperationService getService() {
		return service;
	}

	@Autowired
	public void setService(OperationService service) {
		this.service = service;
	}
	
	private OperationCategoryService operationCategoryService;
	
	@Autowired
	public void setOperationCategoryService(
			OperationCategoryService operationCategoryService) {
		this.operationCategoryService = operationCategoryService;
	}

	private List<OperationCategory> categories;
	
	public List<OperationCategory> getCategories() {
		return categories;
	}

	public void setCategories(List<OperationCategory> categories) {
		this.categories = categories;
	}

	/* (non-Javadoc)
	 * @see com.hsit.common.actions.BaseAction#initEditForm()
	 */
	@Override
	protected void initEditForm() {
		categories = operationCategoryService.getEntities(null);
	}
	
	/* (non-Javadoc)
	 * @see com.hsit.common.actions.BaseAction#initAddForm()
	 */
	@Override
	protected void initAddForm() {
		categories = operationCategoryService.getEntities(null);
	}
	
	/* (non-Javadoc)
	 * @see com.hsit.common.actions.BaseAction#getEntityJson(com.hsit.common.kfbase.entity.DomainEntity)
	 */
	@Override
	protected JSONObject getEntityJson(Operation entity) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = super.getEntityJson(entity);
		jsonObject.element("categoryId", entity.getCategoryId());
		return jsonObject;
	}
}
