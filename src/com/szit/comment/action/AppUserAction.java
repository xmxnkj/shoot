package com.szit.comment.action;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.exceptions.ApplicationException;
import com.szit.comment.entity.AppUser;
import com.szit.comment.entity.query.AppUserQuery;
import com.szit.comment.service.AppUserService;

@Controller("appUserAction")
@Scope("prototype")
public class AppUserAction extends BaseAction<AppUser, AppUserQuery>{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1249723391387384714L;
	
	@Autowired
	private AppUserService service;

	@Override
	public AppUserService getService() {
		return service;
	}
	
	@Override
	public void saveJson() throws Exception {
		
		validateKey();
		
		super.saveJson();
	}
	
	private void validateKey(){
		if (StringUtils.isEmpty(key) || !key.equals("61ae5fb23e2f37689dd6917380718388")) {
			throw new ApplicationException("非法使用！");
		}
	}
	
	
	private String key;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

}
