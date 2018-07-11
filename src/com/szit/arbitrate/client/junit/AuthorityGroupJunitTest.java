package com.szit.arbitrate.client.junit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.client.entity.AuthorityGroup;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;
import com.szit.arbitrate.client.service.AuthorityGroupService;

public class AuthorityGroupJunitTest extends BaseApiJunitTest{
	
	@Autowired
	private AuthorityGroupService service;
	
	private Logger logger = LoggerFactory.getLogger(ClientServiceJunit.class);
	
	//修改昵称
	@Test
	public void addAuthorityGroup(){
		AuthorityGroup entity = new AuthorityGroup();
		entity.setAuthorityGroupName(AuthorityGroupEnum.Mediator);
		entity.setAuthorityDescription("员");
		service.save(entity);
	}
}
