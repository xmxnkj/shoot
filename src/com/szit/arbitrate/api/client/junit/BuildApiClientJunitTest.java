package com.szit.arbitrate.api.client.junit;

import java.util.Map;
import java.util.UUID;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

public class BuildApiClientJunitTest extends BaseApiJunitTest{
	
	@Test
	public void thirdpartlogin(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("thirdpartyid", "11111");
		map.put("thirdparty", "WeChat");
		map.put("nickName", "Normal");
		String inbo = jsonMapper.toJson(map);
		//ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientcenter","mylog", datasource);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "wapApiClientController","thirdpartyLogin", inbo);
		executeApiTest(apiInVm);
	}
	
	@Test
	public void getUUidTest(){
		for(int i = 0; i < 10; i ++){
			String id = UUID.randomUUID().toString();
			logger.debug(id);
		}
	}

}
