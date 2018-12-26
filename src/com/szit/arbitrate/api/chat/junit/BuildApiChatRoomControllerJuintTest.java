package com.szit.arbitrate.api.chat.junit;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

public class BuildApiChatRoomControllerJuintTest extends BaseApiJunitTest{
	
	@Test
	public void pauseOrContinueChatRoom(){
		Map<String,Object> map = Maps.newHashMap();
		String inbo = this.getApiRecordLogForInBo("wapApiChatRoomController_180545");
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.chat, "wapApiChatRoomController","pauseOrContinueChatRoom", inbo);
		executeApiTest(apiInVm);
	}

}
