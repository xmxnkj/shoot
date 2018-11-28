package com.szit.arbitrate.api.mediation.junit;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

public class MediationProtocolJunitTest extends BaseApiJunitTest{
	
	@Test
	public void createMediationProtocol(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("protocoltitle", "xxxx");
		map.put("caseid", "9a144bde-8124-4cff-987b-7e2a8e01a640");
		map.put("content", "xxx");
		map.put("smscontent", "xxxxx!");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationProtocolController","createMediationProtocol", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void getMediationProtocol(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("caseid", "f2ea1061-a099-4106-a9fa-1c3a2ff10f0c");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationProtocolController","getMediationProtocol", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void operateMediationProtocol(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("clientid", "54e46bb5-91ce-4663-beb2-db093504248c");
		map.put("protocolid", "44a4a02b-b6bf-4e06-b132-4b654da644fa");
		map.put("type", 0);
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationProtocolController","operateMediationProtocol", inbo);
		this.executeApiTest(apiInVm);
	}

}
