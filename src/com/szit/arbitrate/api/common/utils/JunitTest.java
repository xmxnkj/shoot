package com.szit.arbitrate.api.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.utils.JsonFormatUtil;
import com.hsit.common.utils.JsonMapper;
import com.szit.arbitrate.api.client.factory.ClientModuleBizFactory;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 
* @ClassName: JunitTest
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:51:55
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public class JunitTest {
	
	private Logger logger = LoggerFactory.getLogger(ApiTools.class);
	private static JsonMapper jsonMapper = JsonMapper.getInstance();
	
	/**
	 * 
	* @Title: junitTestToIn 
	* @Description: 单元测试输入参-会员模块
	* @param @param apiInVm
	* @param @throws BaseErrorException
	* @return void 
	* @throws
	 */
	public static void junitTestToInForClient(ApiInParamsVm  apiInVm)throws ErrorException{
		JsonFormatUtil.printJson("输入参数:", jsonMapper.toJson(apiInVm));
		ApiOutParamsVm apiOutVm = ClientModuleBizFactory.bulidBiz(apiInVm,null,null);
		JsonFormatUtil.printJson("返回参数:", jsonMapper.toJson(apiOutVm));
	}
	
}
