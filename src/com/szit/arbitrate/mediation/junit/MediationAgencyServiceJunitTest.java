package com.szit.arbitrate.mediation.junit;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.service.MediationAgencyService;

public class MediationAgencyServiceJunitTest extends BaseApiJunitTest{

	@Autowired
	private MediationAgencyService service;
	
	private Logger logger = LoggerFactory.getLogger(MediationAgencyServiceJunitTest.class);
	
	@Test
	public void searchMediationAgencyList(){
		String casetype = "AdministrationCaseType";
		String agencytype = "LitigationType";
		String agencyname = "";
		List<MediationAgency> list = service.searchMediationAgencyList(agencytype, casetype, agencyname);
		
		logger.debug("记录数：", list.size());
	}
	
}
