package com.szit.arbitrate.mediation.action;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.szit.arbitrate.mediation.entity.LegalDocDetail;
import com.szit.arbitrate.mediation.entity.query.LegalDocDetailQuery;
import com.szit.arbitrate.mediation.service.LegalDocDetailService;

@Namespace("/admin/mediation/legaldocdetail")
@Controller("legalDocDetailAction")
public class LegalDocDetailAction extends BaseJsonAction<LegalDocDetail, LegalDocDetailQuery>{
	
	private static final long serialVersionUID = -7031106040121308381L;
	
	@Autowired
	private LegalDocDetailService service;

	public LegalDocDetailService getService() {
		return service;
	}

	public void setService(LegalDocDetailService service) {
		this.service = service;
	}
	
	private String legaldocid;//文档id

	public String getLegaldocid() {
		return legaldocid;
	}

	public void setLegaldocid(String legaldocid) {
		this.legaldocid = legaldocid;
	}

	@Override
	protected void beforeQuery() {
		LegalDocDetailQuery query = this.getEntityQuery();
		if(StringUtils.isNotEmpty(legaldocid)){
			query.setLegalDocId(legaldocid);
		}
		this.setEntityQuery(query);
	}
	
	private String id;//文档段落id
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Action(value="deleteLegalDetail")
	public void deleteLegalDetail(){
		JSONObject json = new JSONObject();
		try {
			service.deleteById(id);
			json.put("success", true);
			json.put("message", "删除成功!");
			json.put("data", "");
		} catch (Exception e) {
			json.put("success", false);
			json.put("message", e.getMessage());
			json.put("data", "");
		}
		outJson(json.toString());
	}

}
