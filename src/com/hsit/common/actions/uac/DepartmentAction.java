/**
 * File Name: DepartmentAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.uac;

import java.net.URLDecoder;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hsit.common.uac.entity.Department;
import com.hsit.common.uac.entity.User;
import com.hsit.common.uac.entity.queryparam.DepartmentQuery;
import com.hsit.common.uac.service.DepartmentService;
import com.hsit.common.utils.EntityUtils;

import com.hsit.common.actions.BaseAction;

/**
 * @ClassName:DepartmentAction
 * @date 2017-3-18 上午10:53:19
 * 
 */
@Component("departmentAction")
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department, DepartmentQuery>{
	private DepartmentService service;

	@Override
	public DepartmentService getService() {
		return service;
	}

	@Autowired
	public void setService(DepartmentService service) {
		this.service = service;
	}
	
	
	private String deputyLeaderIds;
	private String deputyLeaderNames;
	private String partChargeLeaderIds;
	private String partChargeLeaderNames;
	
	public String getDeputyLeaderIds() {
		return deputyLeaderIds;
	}

	public void setDeputyLeaderIds(String deputyLeaderIds) {
		this.deputyLeaderIds = deputyLeaderIds;
	}

	public String getDeputyLeaderNames() {
		return deputyLeaderNames;
	}

	public void setDeputyLeaderNames(String deputyLeaderNames) {
		this.deputyLeaderNames = deputyLeaderNames;
	}

	public String getPartChargeLeaderIds() {
		return partChargeLeaderIds;
	}

	public void setPartChargeLeaderIds(String partChargeLeaderIds) {
		this.partChargeLeaderIds = partChargeLeaderIds;
	}

	public String getPartChargeLeaderNames() {
		return partChargeLeaderNames;
	}

	public void setPartChargeLeaderNames(String partChargeLeaderNames) {
		this.partChargeLeaderNames = partChargeLeaderNames;
	}

	@Override
	public void validateNameExist() {
		try {
			String name = URLDecoder.decode(getEntity() != null ? getEntity().getName() : "", "UTF-8");
			String parentId = getEntity() != null? getEntity().getParentId() : "";
			Department et = service.getDepartment(parentId, name);
			if(et != null && !StringUtils.isEmpty(et.getId()) && !et.getId().equals(getId()))
				outJson("false");
			else
				outJson("true");
		} catch (Exception e) {
			try {
				outJson("true");
			} catch (Exception e1) {
			}
		}
	}
	
	@Override
	protected void initEditForm() {
		if (getEntity()!=null && !StringUtils.isEmpty(getEntity().getId())) {
			List<User> deputyUsers = service.getDeparmentDeputyLeaders(getEntity().getId());
			List<User> partChargeUsers = service.getDepartmentPartChargeLeaders(getEntity().getId());
			deputyLeaderIds = EntityUtils.getEntityIdStrings(deputyUsers);
			deputyLeaderNames=EntityUtils.getEntityNameStrings(deputyUsers);
			partChargeLeaderIds=EntityUtils.getEntityIdStrings(partChargeUsers);
			partChargeLeaderNames=EntityUtils.getEntityNameStrings(partChargeUsers);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hsit.common.actions.BaseAction#afterEntitySaved()
	 */
	@Override
	protected void afterEntitySaved() {
		if (getEntity()!=null && !StringUtils.isEmpty(getEntity().getId())) {
			service.setDeparmentPartChargeLeader(getEntity().getId(), EntityUtils.getIdStringList(partChargeLeaderIds));
			service.setDepartmentDeputyLeaders(getEntity().getId(), EntityUtils.getIdStringList(deputyLeaderIds));
		}
	}
	
	@Override
	protected JSONObject convertEntityToJson() {
		return getDepartmentJson(getEntity());
	}

	private JSONObject getDepartmentJson(Department glt) {
		JSONObject js = new JSONObject();

		if (glt != null) {
			js.element("id", glt.getId());
			js.element("name", glt.getName());
			js.element("parentId", glt.getParentId());
			js.element("description", glt.getDescription());
			js.element("displayOrder", glt.getDisplayOrder());
		}
		return js;
	}

	@Override
	protected JSONArray convertEntityListToJson() {
		JSONArray jsArray = new JSONArray();

		if (getEntities() != null) {
			for (Department glt : getEntities()) {
				jsArray.add(getDepartmentJson(glt));
			}
		}

		return jsArray;
	}
	
	
	public void getAssessDepartmentsJson(){
		DepartmentQuery query = new DepartmentQuery();
		query.setNeedAssess(true);
		setEntities(service.getEntities(query));
		outJson(convertEntityListToJson());
	}
}
