package com.szit.arbitrate.mediation.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.utils.PageDataBean;
import com.szit.arbitrate.mediation.entity.Street;
import com.szit.arbitrate.mediation.entity.query.StreetQuery;
import com.szit.arbitrate.mediation.service.StreetService;

@Namespace("/admin/mediation/street")
@Controller("streetAction")
public class StreetAction extends BaseJsonAction<Street, StreetQuery>{
	
	@Autowired
	private StreetService service;
	
	public StreetService getService() {
		return service;
	}
	
	@Action(
			value="listJsp", 
			results={
			   @Result(name="success", location="/WEB-INF/jsp/admin/mediation/street/list.jsp")}
		   )
	public String listJsp(){
		return SUCCESS;
	}
	
	//获取所有
	@Action(value="getList")
	public void getList(){
		
		try {
			StreetQuery streetQuery = new StreetQuery();
			
			if(getEntityQuery()!=null){
				streetQuery.setPaging(getEntityQuery().getPaging());
				List<Street> list = service.getEntities(streetQuery);
				Paging  paging  = this.getEntityQuery().getPaging();
				PageDataBean<Street> pageList = new PageDataBean<Street>(paging,list);
				String jsonResult = jsonMapper.toJson(pageList);
				outJson(jsonResult);
			}else{
				List<Street> list = service.getEntities(streetQuery);
				outJson(jsonMapper.toJson(list));
			}
		} catch (Exception e) {
			outFailJson(e);
		}
	}
	
	String id;
	String streetName;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Action(value="updateEntity")
	public void updateEntity(){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			Street street = new Street();
			street.setId(id);
			street.setStreetNname(streetName);
			service.save(street);
			map.put("status", "OK");
		} catch (Exception e) {
			map.put("status", "NO");
		}
		outJson(jsonMapper.toJson(map));
	}
	
	@Action(value="deleteEntity")
	public void deleteEntity(){
		Map<String,Object> map = new HashMap<String,Object>();
		try{
			service.deleteById(getEntity().getId());
			map.put("status", "OK");
		} catch (Exception e) {
			map.put("status", "NO");
		}
		outJson(jsonMapper.toJson(map));
	}
}