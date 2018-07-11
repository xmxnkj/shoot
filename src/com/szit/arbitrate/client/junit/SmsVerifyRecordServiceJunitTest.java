package com.szit.arbitrate.client.junit;

import java.util.Map;

import org.json.JSONArray;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.client.entity.enumvo.SmsBizModelEnum;
import com.szit.arbitrate.client.service.SmsVerifyRecordService;

public class SmsVerifyRecordServiceJunitTest extends BaseApiJunitTest{
	
	@Autowired
	private SmsVerifyRecordService service;
	
	private Logger logger = LoggerFactory.getLogger(SmsVerifyRecordServiceJunitTest.class);
	
	@Test
	public void getSmsRecord(){
		String phone = "18805921191";
		Map<String, Object> map = service.toGetSmsCode(phone, SmsBizModelEnum.clientregister);
		logger.debug("smsvkeyid:", (String)map.get("smsvkeyid"));
		logger.debug("smscode:", (String)map.get("smscode"));
	}
	
	@Test
	public void verifySmsCode(){
		String phone = "18805921191";
		String smscode = "3036";
		service.verifySmsCode(phone, smscode);
	}
	
	@Test
	public void sendSmsMessage(){
		String templateid = "3063461";
		JSONArray  paramsarry = new JSONArray();
		paramsarry.put("111");
		paramsarry.put("222");
		paramsarry.put("www.baidu.com");
		paramsarry.put("18050550014");
		
		JSONArray  phonearry = new JSONArray() ;
		phonearry.put("18805921191");
        service.sendSmsMessage(phonearry.toString(), SmsBizModelEnum.sms, templateid, paramsarry.toString());
	}

}
