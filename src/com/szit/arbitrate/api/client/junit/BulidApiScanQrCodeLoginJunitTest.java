package com.szit.arbitrate.api.client.junit;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.pushcentre.common.PushMessageUtil;

/**
 * 
* @ClassName: BulidApiScanQrCodeLoginJunitTest
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:49:29
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class BulidApiScanQrCodeLoginJunitTest extends BaseApiJunitTest{

	@Test
	public void scanQrCodeRequest(){
		PushMessageUtil.getInstance().setIsdebug(true);
		this.setUsercode("1506558307");
		this.setPwd("123456");
		Map<String,Object> map = Maps.newHashMap();
		map.put("uniquenesscode", "1234567890");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "apiScanQrCodeLoginController","scanQrCodeRequest", inbo);
		executeApiTest(apiInVm);
	}
	
	
	@Test
	public void scanQrCodeLogin(){
		this.setIsWapLogin(false);
		Map<String,Object> map = Maps.newHashMap();
		map.put("loginvkeycode", "ebc59a8f-2275-43c2-9e53-00cec5d7d70e");
		//String inbo = jsonMapper.toJson(map);
		String inbo = this.getApiRecordLogForInBo("apiScanQrCodeLoginController_706650");
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "apiScanQrCodeLoginController","scanQrCodeLogin", inbo);
		executeApiTest(apiInVm);
	}
	
}
