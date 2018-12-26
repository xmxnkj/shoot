package com.szit.arbitrate.api.chat.junit;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

public class BuildApiChatMessageJunitTest extends BaseApiJunitTest {
	
	@Test
	public void sendMessage(){
		Map<String,Object> map = Maps.newHashMap();
		String inbo = this.getApiRecordLogForInBo("wapApiChatMessageController_173828");
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.chat, "wapApiChatMessageController","sendMessage", inbo);
		executeApiTest(apiInVm);
	}

}
