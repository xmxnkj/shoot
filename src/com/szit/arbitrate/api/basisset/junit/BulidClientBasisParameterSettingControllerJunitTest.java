package com.szit.arbitrate.api.basisset.junit;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

/**
 * 
* @ClassName: BulidClientBasisParameterSettingControllerJunitTest
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:44:35
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class BulidClientBasisParameterSettingControllerJunitTest extends BaseApiJunitTest{
	
	
	@Test
	public void mySettingList(){
			this.setUsercode("1234");
			this.setPwd("123");
			Map<String,Object> map = Maps.newHashMap();
			map.put("parametertype", "messagenotify");
			String inbo = jsonMapper.toJson(map);
			ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.basisset, "clientBasisParameterSettingController","mySettingList", inbo);
			executeApiTest(apiInVm);

	}
}
