package com.szit.arbitrate.api.client.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 
* @ClassName: ClientThirdpartyAccountController
* @Description:会员第三方登陆控制器接口类
* @author Administrator
* @date 2017年3月20日 下午2:47:37
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ClientThirdpartyAccountController {


	

	/**
	 * 
	* 方法描述:会员账号绑定手机
	* 创建人: Administrator
	* 创建时间：2017年1月12日
	* @param phone 手机号码
	* @param verifysmscode 手机验证码
	* @param verifykeyid 手机验证码KEY
	* @param pwd1 密码1
	* @param pwd2 密码2
	* @return
	 */
	public ApiOutParamsVm bindingPhone(String phone,String verifysmscode,String verifykeyid,String pwd1,String pwd2);
	
	/**
	 * 
	* 方法描述:会员账号绑定第三方
	* 创建人: Administrator
	* 创建时间：2017年1月12日
	* @param clientid
	* @param thirdpartyid
	* @param thirdparty
	* @param thirdpartynickname
	* @param thirdprttyheadimageurl
	* @return
	 */
	public ApiOutParamsVm bindingThirdparty(String thirdpartyid, String thirdparty,
			                                String thirdpartynickname,String thirdprttyheadimageurl);
	
	/**
	 * 
	* 方法描述:会员第三方账号列表
	* 创建人: Administrator
	* 创建时间：2017年1月12日
	* @return
	 */
	public ApiOutParamsVm clientThirdpartyAccountList();
	
	/**
	 * 
	* 方法描述:会员第三方账号解绑
	* 创建人:LIUJL
	* 创建时间：2017年1月12日
	* @return
	 */
	public ApiOutParamsVm clientThirdpartyAccountUnbundling(String thirdpartyid);

	/**
	 * 
	* @Title: getWechatId  
	* @Description: 获取微信用户openID
	* @param @param code
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm getWechatId(String code);
	
}
