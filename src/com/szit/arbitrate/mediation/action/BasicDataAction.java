package com.szit.arbitrate.mediation.action;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.szit.arbitrate.mediation.entity.BasicData;
import com.szit.arbitrate.mediation.entity.query.BasicDataQuery;
import com.szit.arbitrate.mediation.service.BasicDataService;

@Namespace("/admin/mediation/basicdata")
@Controller("basicDataAction")
public class BasicDataAction extends BaseJsonAction<BasicData, BasicDataQuery>{

	@Autowired
	private BasicDataService service;

	public BasicDataService getService() {
		return service;
	}

	public void setService(BasicDataService service) {
		this.service = service;
	}

	@Override
	protected void beforeQuery() {
		BasicDataQuery query = this.getEntityQuery();
		String parenttype = this.getRequest().getParameter("parenttype");
		if(StringUtils.isNotEmpty(parenttype)){
			query.setParentType(parenttype);
		}
		this.setEntityQuery(query);
	}
	
	@Action(value = "getBasicDataList")
	public void getBasicDataList(){
		List<BasicData> list = service.getBasicDataList();
		this.outJson(jsonMapper.toJson(list));
	}
	
	@Action(value = "deleteBasicDataDetail")
	public void deleteBasicDataDetail(){
		JSONObject json = new JSONObject();
		try {
			String entityid = this.getRequest().getParameter("id");
			service.deleteById(entityid);
			json.put("success", true);
			json.put("message", "删除成功!");
			json.put("data", "");
		} catch (Exception e) {
			json.put("success", false);
			json.put("message", "系统异常错误");
			json.put("data", "");
		}
		outJson(json.toString());
	}
	
}
