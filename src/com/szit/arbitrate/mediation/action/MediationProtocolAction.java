package com.szit.arbitrate.mediation.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Maps;
import com.hsit.common.actions.BaseJsonAction;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.query.TempClientQuery;
import com.szit.arbitrate.client.service.TempClientService;
import com.szit.arbitrate.mediation.entity.MediationProtocol;
import com.szit.arbitrate.mediation.entity.query.MediationProtocolQuery;
import com.szit.arbitrate.mediation.service.MediationProtocolService;

@Namespace("/admin/mediation/mediationprotocol")
@Controller("mediationProtocolAction")
public class MediationProtocolAction extends BaseJsonAction<MediationProtocol, MediationProtocolQuery>{

	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = -6273882623713171453L;
	@Autowired
	private MediationProtocolService service;
	@Autowired
	private TempClientService tempClientService;

	public MediationProtocolService getService() {
		return service;
	}

	public void setService(MediationProtocolService service) {
		this.service = service;
	}

	@Override
	protected void beforeQuery() {
		MediationProtocolQuery query = new MediationProtocolQuery();
		String caseid = this.getRequest().getParameter("caseid");
		if(StringUtils.isNotEmpty(caseid)){
			query.setCaseId(caseid);
		}
		this.setEntityQuery(query);
	}
	
	@Action(value = "getMediationProtocolDetail")
	public void getMediationProtocolDetail(){
		Map<String, Object> map = Maps.newHashMap();
		MediationProtocolQuery query = new MediationProtocolQuery();
		String caseid = this.getRequest().getParameter("caseid");
		if(StringUtils.isNotEmpty(caseid)){
			query.setCaseId(caseid);
		}
		MediationProtocol entity = service.getEntity(query);
		map.put("entity", entity);
		
		TempClientQuery tempClientQuery = new TempClientQuery();
		if(StringUtils.isNotEmpty(caseid)){
			query.setCaseId(caseid);
		}
		List<TempClient> list = tempClientService.getEntities(tempClientQuery);
		map.put("list", list);
		this.outJson(jsonMapper.toJson(map));
	}
	
}
