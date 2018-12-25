package com.szit.arbitrate.api.client.junit;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

/**
 * 
* @ClassName: BuildClientThirdpartyAccountJunitTest
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:49:21
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class BuildClientThirdpartyAccountJunitTest extends BaseApiJunitTest{

	@Test
	public void bindingPhone(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("phone", "18950102365");
		map.put("verifysmscode", "237370");
		map.put("verifykeyid", "e1b006ac-9195-44fe-8844-7ad18263aac5");
		map.put("pwd1", "123456");
		map.put("pwd2", "123456");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientThirdpartyAccountController","bindingPhone", inbo);
		executeApiTest(apiInVm);
	}
	
	@Test
	public void bindingThirdparty_debug(){
		this.setUsercode("123");
		this.setPwd("123");
		String inbo = this.getApiRecordLogForInBo("clientThirdpartyAccountController_721259");
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientThirdpartyAccountController","bindingThirdparty", inbo);
		executeApiTest(apiInVm);
	}
	
	
	@Test
	public void bindingThirdparty(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("thirdpartyid", "18050550014");
		map.put("thirdparty", "QQ");
		map.put("thirdpartynickname", "测试第三方");
		map.put("thirdprttyheadimageurl", "www.163.com");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientThirdpartyAccountController","bindingThirdparty", inbo);
		executeApiTest(apiInVm);
	}
	
	@Test
	public void clientThirdpartyAccountList(){
		Map<String,Object> map = Maps.newHashMap();
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientThirdpartyAccountController","clientThirdpartyAccountList", inbo);
		executeApiTest(apiInVm);
	}
	@Test
	public void clientThirdpartyAccountUnbundling(){
		Map<String,Object> map = Maps.newHashMap();
		String inbo = jsonMapper.toJson(map);
		map.put("thirdpartyid", "18050550014");
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientThirdpartyAccountController","clientThirdpartyAccountUnbundling", inbo);
		executeApiTest(apiInVm);
	}
}
