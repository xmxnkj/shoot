package com.szit.arbitrate.client.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.szit.arbitrate.client.entity.AuthorityGroup;
import com.szit.arbitrate.client.entity.query.AuthorityGroupQuery;
import com.szit.arbitrate.client.service.AuthorityGroupService;

@Namespace("/admin/client/authority")
@Controller("authorityGroupAction")
public class AuthorityGroupAction extends BaseJsonAction<AuthorityGroup, AuthorityGroupQuery>{

	@Autowired
	private AuthorityGroupService service;

	public AuthorityGroupService getService() {
		return service;
	}

	public void setService(AuthorityGroupService service) {
		this.service = service;
	}
	
	@Action(value = "getAuthorityGroups")
	public void getAuthorityGroups(){
		AuthorityGroupQuery query = this.getEntityQuery();
		//List<AuthorityGroup> list = service.getEntities(query);
		//this.outJson(jsonMapper.toJson(list));
		setEntities(service.getEntities(query));
		outJson(convertEntityListToJson());
	}
	
	private JSONObject getAuthorityGroupJson(AuthorityGroup ag) {
		JSONObject js = new JSONObject();

		if (ag != null) {
			js.element("id", ag.getId());
			js.element("name", ag.getAuthorityDescription());
			js.element("description", ag.getDescription());
			js.element("displayOrder", ag.getDisplayOrder());
		}
		return js;
	}

	@Override
	protected JSONArray convertEntityListToJson() {
		JSONArray jsArray = new JSONArray();

		if (getEntities() != null) {
			for (AuthorityGroup glt : getEntities()) {
				jsArray.add(getAuthorityGroupJson(glt));
			}
		}

		return jsArray;
	}
	
}
