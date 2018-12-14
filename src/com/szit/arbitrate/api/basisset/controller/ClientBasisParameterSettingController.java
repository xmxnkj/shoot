package com.szit.arbitrate.api.basisset.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: ClientBasisParameterSettingController
* @Description:基础设置-消息通知API控制器接口类
* @author Administrator
* @date 2017年3月20日 下午2:33:39
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ClientBasisParameterSettingController {


	/**
	 * 
	* @Title: setting 
	* @Description: 参数设置
	* @param @param parametertype 参数类型  messagenotify(消息通知) privacysetting(隐私设置)
	* @param @param parameterid
	* @param @param parameterval
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm setting(String parametertype,String parameterid,String parameterval);
	

	/**
	 * 
	* @Title: mySettingList 
	* @Description: 取得我的设置列表
	* @param @param parametertype
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm mySettingList(String parametertype);
	
}
