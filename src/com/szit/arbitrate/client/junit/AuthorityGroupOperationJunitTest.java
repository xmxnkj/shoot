package com.szit.arbitrate.client.junit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.client.service.AuthorityGroupOperationService;

public class AuthorityGroupOperationJunitTest extends BaseApiJunitTest{
	
	@Autowired
	private AuthorityGroupOperationService authorityGroupOperationService;
	
	private Logger logger = LoggerFactory.getLogger(ClientServiceJunit.class);
	
	@Test
	public void setClientAuthorityGroupOperation(){
		authorityGroupOperationService.setClientAuthorityGroupOperation("54e46bb5-91ce-4663-beb2-db093504248c", 
																		"7074a71d-2833-4b43-8501-85a77c716b15", 
																		"ApplyMediation");
	}
}
