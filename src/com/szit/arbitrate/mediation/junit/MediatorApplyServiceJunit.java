package com.szit.arbitrate.mediation.junit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.client.junit.ClientServiceJunit;
import com.szit.arbitrate.mediation.service.MediatorApplyService;

public class MediatorApplyServiceJunit extends BaseApiJunitTest{
	
	@Autowired
	private MediatorApplyService mediatorApplyService;
	
	private Logger logger = LoggerFactory.getLogger(ClientServiceJunit.class);
	
	//申请成为员
	@Test
	public void saveMediatorApply(){
		
		String clientId = "54e46bb5-91ce-4663-beb2-db093504248c";
		
		String applyReason = "reason";
		
		mediatorApplyService.saveApplyMediator(clientId, applyReason);
	}

}
