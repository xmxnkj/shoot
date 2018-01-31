package com.szit.arbitrate.mediation.junit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.mediation.service.MediationProtocolService;

public class MediationProtocolServiceJunitTest extends BaseApiJunitTest{
	
	@Autowired
	private MediationProtocolService service;
	
	@Test
	public void downloadMediationProtocol(){
		String protocolid = "dba3e14c-a460-4094-bbf7-c49e9f2b27f0";
		service.downloadMediationProtocol(protocolid);
	}

}
