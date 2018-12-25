package com.szit.arbitrate.api.client.junit;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;

public class ClientAuthorityGroupJuntTest extends BaseApiJunitTest{
	
	//添加权限
		@Test
		public void setClientAuthorityGroup(){
			
			Map<String,Object> map = Maps.newHashMap();
			map.put("clientId", "54e46bb5-91ce-4663-beb2-db093504248c");
			map.put("authorityGroupName", AuthorityGroupEnum.Mediator);
			
			String inbo = jsonMapper.toJson(map);
			ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "apiClientAuthorityGroupController","setClientAuthorityGroup", inbo);
			this.executeApiTest(apiInVm);
			
		}

}
