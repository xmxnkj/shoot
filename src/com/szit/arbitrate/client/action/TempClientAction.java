package com.szit.arbitrate.client.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.query.TempClientQuery;
import com.szit.arbitrate.client.service.TempClientService;

@Namespace("/admin/client/tempclient")
@Controller("tempClientAction")
public class TempClientAction extends BaseJsonAction<TempClient, TempClientQuery>{

	@Autowired
	private TempClientService service;

	public TempClientService getService() {
		return service;
	}

	public void setService(TempClientService service) {
		this.service = service;
	}

	@Override
	protected void beforeQuery() {
		TempClientQuery query = this.getEntityQuery();
		String caseid = this.getRequest().getParameter("caseid");
		if(StringUtils.isNotEmpty(caseid)){
			query.setCaseId(caseid);
		}
		this.setEntityQuery(query);
	}
	
	@Action(value = "getTempClientList")
	public void getTempClientList(){
		TempClientQuery query = new TempClientQuery();
		String caseid = this.getRequest().getParameter("caseid");
		if(StringUtils.isNotEmpty(caseid)){
			query.setCaseId(caseid);
		}
		List<TempClient> list = service.getEntities(query);
		this.outJson(jsonMapper.toJson(list));
	}
	
}
