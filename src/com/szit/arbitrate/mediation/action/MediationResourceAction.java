package com.szit.arbitrate.mediation.action;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.szit.arbitrate.mediation.entity.MediationResource;
import com.szit.arbitrate.mediation.entity.query.MediationResourceQuery;
import com.szit.arbitrate.mediation.service.MediationResourceService;

@Namespace("/admin/mediation/mediationresource")
@Controller("mediationResourceAction")
public class MediationResourceAction extends BaseJsonAction<MediationResource, MediationResourceQuery>{

	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 6524744365937573665L;
	@Autowired
	private MediationResourceService service;

	public MediationResourceService getService() {
		return service;
	}

	public void setService(MediationResourceService service) {
		this.service = service;
	}

	@Override
	protected void beforeQuery() {
		MediationResourceQuery query = this.getEntityQuery();
		String caseid = this.getRequest().getParameter("caseid");
		if(StringUtils.isNotEmpty(caseid)){
			query.setCaseId(caseid);
		}
		this.setEntityQuery(query);
	}
	
}
