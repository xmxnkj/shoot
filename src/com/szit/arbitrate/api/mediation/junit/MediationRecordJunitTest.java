package com.szit.arbitrate.api.mediation.junit;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

public class MediationRecordJunitTest extends BaseApiJunitTest{
	
	
	@Test
	public void getMediationRecord(){	

		Map<String,Object> map = Maps.newHashMap();
		map.put("mediationRecordId", "139cc319-a0ec-4898-850b-26bcaf4918db");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "wapApiMediationRecordController","getMediationRecord", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void getMediationRecordList(){	

		Map<String,Object> map = Maps.newHashMap();
		map.put("caseId", "3e8d7853-911a-4ee5-96f5-b9e42f09c87b");
		map.put("pageNum", 1);
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationRecordController","getMediationRecordList", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void saveMediationRecord(){	
		//  caseId recordContent involvedPerson joinPerson address

		Map<String,Object> map = Maps.newHashMap();
		map.put("caseId", "15674864154");
		map.put("recordContent", "xxxxxx");
		map.put("involvedPerson", "xxxx");
		map.put("joinPerson", "参与人:老张");
		map.put("address", "xxxx");
		
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationRecordController","saveMediationRecord", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void updateMediationRecord(){
		
		Map<String,Object> map = Maps.newHashMap();
		map.put("mediationRecordId", "d5a019ce-af85-4ed6-8c07-bbdcf2b02944");
		map.put("recordContent", "dddddddddddd");
		map.put("involvedPerson", "cccccc");
		map.put("joinPerson", "aaaaaaa");
		map.put("address", "bbbbbbbbbbbb");
		
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationRecordController","updateMediationRecord", inbo);
		this.executeApiTest(apiInVm);
	}	
	
	@Test
	public void removeMediationRecord(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("mediationRecordId", "b0b0a05d-a76d-4395-9398-b756681a0f1b");
		
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "wapApiMediationRecordController","removeMediationRecord", inbo);
		this.executeApiTest(apiInVm);
	}
}