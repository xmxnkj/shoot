package com.szit.arbitrate.mediation.action;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.mediation.entity.LegalDocComments;
import com.szit.arbitrate.mediation.entity.MediationCase;
import com.szit.arbitrate.mediation.entity.query.LegalDocCommentsQuery;
import com.szit.arbitrate.mediation.service.LegalDocCommentsService;

@Namespace("/admin/mediation/legaldoccomments")
@Controller("legalDocCommentsAction")
public class LegalDocCommentsAction extends BaseJsonAction<LegalDocComments, LegalDocCommentsQuery>{

	@Autowired
	private LegalDocCommentsService service;

	public LegalDocCommentsService getService() {
		return service;
	}

	public void setService(LegalDocCommentsService service) {
		this.service = service;
	}

	@Override
	protected void beforeQuery() {
		String legaldocid = this.getRequest().getParameter("legaldocid");
		LegalDocCommentsQuery query = this.getEntityQuery();
		query.setLegalDocId(legaldocid);
		this.setEntityQuery(query);
	}
	
	@Action(value = "auditComments")
	public void auditComments(){
		JSONObject json = new JSONObject();
		try {
			String commentsid = this.getRequest().getParameter("id");
			service.auditComments(commentsid);
			json.put("success", true);
			json.put("message", "操作成功!");
			json.put("data", "");
		} catch (BizException biz) {
			json.put("success", false);
			json.put("message", biz.getMessage());
			json.put("data", "");
		}catch (ErrorException error) {
			json.put("success", false);
			json.put("message", error.getMessage());
			json.put("data", "");
		}
		outJson(json.toString());
	}
	
	
	@Action("deleteComment")
	public void deleteComment(){
		JSONObject json = new JSONObject();
		try {
			String id = getRequest().getParameter("id");
			LegalDocComments legalDocComments = service.getById(id);
			if(legalDocComments!=null){
				service.deleteById(id);
			}
			json.put("success", true);
			json.put("message", "操作成功!");
			json.put("data", "");
		} catch (BizException biz) {
			json.put("success", false);
			json.put("message", biz.getMessage());
			json.put("data", "");
		}catch (ErrorException error) {
			json.put("success", false);
			json.put("message", error.getMessage());
			json.put("data", "");
		}
		outJson(json.toString());
	}
}