package com.szit.arbitrate.api.mediation.junit;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

public class MediatorApplyJunitTest extends BaseApiJunitTest{
	
	@Test
	public void getMediationRecord(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("clientId", "54e46bb5-91ce-4663-beb2-db093504248c");
		map.put("applyReason", "原因");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "wapApiMediatorApplyController","saveApplyMediator", inbo);
		this.executeApiTest(apiInVm);
	}
	
	
	public void getMediationAgencyDetail(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("mediationagencyid", "13c274b3-cbb7-4873-aa95-f3d9fde68f3d");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "wapApiMediationAgencyController","getMediationAgencyDetail", inbo);
		this.executeApiTest(apiInVm);
	}

	
	@Test
	public void searchAgencyOrMediatorList(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("type", "2");
		map.put("mediatorOrAgency", "mediator");
		map.put("key", "");
		map.put("page", 1);
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "wapApiMediationAgencyController","searchAgencyOrMediatorList", inbo);
		this.executeApiTest(apiInVm);
	}

}
