package com.szit.arbitrate.client.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsit.common.utils.CommonUtil;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.service.ClientService;

public class ClientUtils {
	
	@Autowired
	private ClientService clientService;
	
	public static boolean isLoginSessionTheSame(HttpServletRequest request){
		HttpSession session = request.getSession();
		String clientId = (String) session.getAttribute("clientId");
		HttpSession newSession = null;
		if(CommonUtil.sessionMap.containsKey(clientId)){
			newSession = CommonUtil.sessionMap.get(clientId);
		}
		if(session.getId().equals(newSession.getId())){
			return true;
		}else{
			return false;
		}
	}
	
	public String isLoginSessionOnline(HttpServletRequest request){
		HttpSession session = request.getSession();
		String clientId = (String) session.getAttribute("clientId");
		if(StringUtils.isEmpty(clientId)){
			return "";
		}
		Client client = clientService.getById(clientId);
		String requestSessionId = session.getId();
		if(requestSessionId.equals(client.getSessionId()==null?"":client.getSessionId())){
			return clientId;
		}else{
			return "";
		}
	}

}
