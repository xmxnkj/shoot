package com.szit.arbitrate.client.action;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.entity.MediatorApply;
import com.szit.arbitrate.mediation.entity.query.MediatorApplyQuery;
import com.szit.arbitrate.mediation.service.MediatorApplyService;

@Namespace("/admin/client/mediatorapply")
@Controller("mediatorApplyAction")
public class MediatorApplyAction extends BaseJsonAction<MediatorApply, MediatorApplyQuery>{

	@Autowired
	private MediatorApplyService service;
	@Autowired
	private ClientService clientservice;

	public MediatorApplyService getService() {
		return service;
	}

	public void setService(MediatorApplyService service) {
		this.service = service;
	}
	
	
	@Action(value = "saveMediatorApply")
	public void saveMediatorApply(){
		JSONObject json = new JSONObject();
		String id= this.getRequest().getParameter("id");
		String applyClientId= this.getRequest().getParameter("applyClientId");
		String auditState= this.getRequest().getParameter("auditState");
		String mediationAgencyId= this.getRequest().getParameter("mediationAgencyId");
		json = (JSONObject) service.auditMediatorApply(id, applyClientId, auditState, mediationAgencyId);
		
		json.put("success", true);
		json.put("message", "创建成功!");
		json.put("data", "");
		outJson(json.toString());
	}
}