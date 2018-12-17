package com.szit.arbitrate.api.basisset.junit;

import java.util.Map;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

/**
 * 
* @ClassName: BulidAppLanguageSettingJunitTest
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:44:21
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class BulidAppLanguageSettingJunitTest extends BaseApiJunitTest{
	
	@Test
	public void setttinLanguage(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("language", "chinese");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.basisset, "appLanguageSettingController","setttinLanguage", inbo);
		executeApiTest(apiInVm);
		
	}
	@Test
	public void myAppLanguage(){
		Map<String,Object> map = Maps.newHashMap();
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.basisset, "appLanguageSettingController","myAppLanguage", inbo);
		executeApiTest(apiInVm);
		
	}
}
