package com.szit.arbitrate.api.client.junit;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

/**
 * 
* @ClassName: BulidApiSmsVerifyJunitTest
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:49:42
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class BulidApiSmsVerifyJunitTest extends BaseApiJunitTest{
	
	@Test
	public void toGetSmsCode(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("phone", "13600920209");
		map.put("bizmodel", "findpassword");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "apiSmsVerifyControllerImpl","toGetSmsCode", inbo);
		executeApiTest(apiInVm);
	}
}
