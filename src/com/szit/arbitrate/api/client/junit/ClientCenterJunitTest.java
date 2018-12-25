package com.szit.arbitrate.api.client.junit;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

public class ClientCenterJunitTest extends BaseApiJunitTest{
	
	@Test
	public void changeNickName(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("clientId", "54e46bb5-91ce-4663-beb2-db093504248c");
		map.put("newNickName", "臭皮匠");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "wapApiClientController","changeNickName", inbo);
		this.executeApiTest(apiInVm);
	}
	@Test
	public void registerAccountTest(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("verifykeyid", "54e46bb5-91ce-4663-beb2-db093504248c");
		map.put("verifycode", "5568");
		map.put("account", "1234");
		map.put("clienttype", "Normal");
		map.put("password", "1234");
		map.put("password2", "1234");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "wapApiClientController","registerAccount", inbo);
		this.executeApiTest(apiInVm);
	}
	
	

}
