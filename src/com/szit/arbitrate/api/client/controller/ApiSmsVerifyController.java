package com.szit.arbitrate.api.client.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 
* @ClassName: ApiSmsVerifyController
* @Description:API接口短信验证控制接口类
* @author Administrator
* @date 2017年3月20日 下午2:47:23
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ApiSmsVerifyController {
    

	/**
	 * 
	* 方法描述:获取短信验证码
	* 创建人: Administrator
	* 创建时间：2017年2月20日
	* @param phone
	* @param bizmodel  findpassword  clientregister
	* @return
	 */
	public ApiOutParamsVm toGetSmsCode(String phone,String bizmodel);

	
}
