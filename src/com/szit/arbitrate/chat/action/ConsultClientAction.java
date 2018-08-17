package com.szit.arbitrate.chat.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.utils.PageDataBean;
import com.szit.arbitrate.chat.entity.ConsultClient;
import com.szit.arbitrate.chat.entity.query.ConsultClientQuery;
import com.szit.arbitrate.chat.service.ConsultClientService;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.service.ClientService;

@Namespace("/admin/chat/consult")
@Controller("consultClientAction")
public class ConsultClientAction extends BaseJsonAction<ConsultClient, ConsultClientQuery>{

	@Autowired
	private ConsultClientService service;
	
	@Autowired
	private ClientService clientService;

	public ConsultClientService getService() {
		return service;
	}

	public void setService(ConsultClientService service) {
		this.service = service;
	}
	
	String order;
	String sort;	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}

	@Action("getChatUser")
	public void getChatUser(){
		try{
			ConsultClientQuery query = getEntityQuery();
			if(StringUtils.isNotEmpty(sort)){
				if(order.equals("asc")){
				  query.addOrder(sort,false);
				}else{
				  query.addOrder(sort,true);
				}
			}
			List<ConsultClient> list = getService().getEntities(query);
			Client c = null;
			for(ConsultClient client:list){
				c = clientService.getById(client.getClientId());
				if(c!=null){
					client.setClientName(c.getIdentifyName());
					client.setClientImage(c.getHeadImgFile());
				}
			}
			Paging  paging  = this.getEntityQuery().getPaging();
			PageDataBean<ConsultClient> pageList = new PageDataBean<ConsultClient>(paging,list);
			String jsonResult = jsonMapper.toJson(pageList);
			outJson(jsonResult);
		}catch(Exception e){
			
		}
	} 
	
}
