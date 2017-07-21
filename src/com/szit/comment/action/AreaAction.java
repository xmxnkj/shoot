package com.szit.comment.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseAction;
import com.szit.comment.entity.Area;
import com.szit.comment.entity.query.AreaQuery;
import com.szit.comment.service.AreaService;

import net.sf.json.JSONObject;

@Controller("areaAction")
@Scope("prototype")
public class AreaAction extends BaseAction<Area, AreaQuery>{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3493617890080786513L;
	
	@Autowired
	private AreaService service;
	@Override
	public AreaService getService() {
		return service;
	}
	
	@Override
	protected JSONObject getEntityJson(Area entity) {
		JSONObject jsonObject = super.getEntityJson(entity);
		
		jsonObject.element("parentId", entity.getParentId());
		jsonObject.element("lon", entity.getLon()!=null?entity.getLon():-1000);
		jsonObject.element("lat", entity.getLat()!=null?entity.getLat():-1000);
		
		return jsonObject;
	}
	
	public void getChildAreas(){
		
		setEntities(service.getChildAreas(getEntityQuery()!=null?getEntityQuery().getParentId():null));
		
		outJson(convertEntityListToJson());
	}
	
	
}
