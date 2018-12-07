package com.szit.arbitrate.api.client.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;


/**   
 *    
 * 项目名称：XMSZIT-COWELL
 * 项目说明：运动APP项目
 * 类名称：ApiClientController   
 * 类描述：
 * 事件记录：
 * 创建人：XUJC  
 * 创建时间：2017年12月27日 下午1:33:30
 * 厦门西牛科技有限公司科技有限公司
 * @version 1.0 
 *    
 */
public interface ApiClientController {

	/**
	 * 
	* 方法描述:编辑会员
	* 创建人: XUJC
	* 创建时间：2017年12月27日
	* @param entity
	* @return
	 */
	/*public ApiOutParamsVm edit(Client entity);*/
	
	/**
	 * 
	* @Title: registerAccount 
	* @Description: 用户注册  验证码key，验证码
	* @param @param account
	* @param @param password，password2
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm registerAccount(String name, String address, String verifycode,
			String account, String clienttype, String password, String password2);
	
	/**
	 * 
	* @Title: login 
	* @Description: 用户登录
	* @param @param account
	* @param @param passwd
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm login(String clienttype, String account, 
			String passwd,String terminaltype, String uuid);
	
	/**
	 * 
	* @Title: exitLogin  
	* @Description: 退出登录
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm exitLogin();
	
	/**
	 * 
	* 方法描述:注册验证
	* 创建人: Administrator
	* 创建时间：2017年2月20日
	* @param verifykeyid
	* @param verifycode
	* @return
	 */
	public ApiOutParamsVm registerSmsVerify(String verifykeyid,String verifycode);
	
	/**
	 * 
	* 方法描述:会员注册 验证账号唯一性
	* 创建人: XUJC
	* 创建时间：2017年12月27日
	* @param entity
	* @return
	 */
	public ApiOutParamsVm validationAccount(String account);
	
	/**
	 * 
	* @Title: certificateIdentify 
	* @Description: 身份实名认证
	* @param @param clientid
	* @param @param identifyname
	* @param @param identify
	* @param @param identifyImgFile1
	* @param @param identifyImgFile2
	* @param @param identifyImgFile3
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm certificateIdentify(String phone, String verifycode,
			String clientid,String identifyname,String identify,String address,
			boolean gender,String nation,String birth,String profession,
			String identifyImgFile1,String identifyImgFile2,String identifyImgFile3);
	
	/**
	 * 
	* 方法描述:我的会员信息
	* 创建人: XUJC
	* 创建时间：2017年12月27日
	* @return
	 */
	public ApiOutParamsVm myClient(String clientid);
	
	/**
	 * 
	* @Title: uploadHeadImg 
	* @Description: 上传资源
	* @param @param clientid
	* @param @param restype
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm uploadHeadImg(String clientid, String restype);
	
	/**
	 * 
	* 方法描述:修改密码
	* 创建人: XUJC
	* 创建时间：2017年12月27日
	* @param oldpwd 旧密码
	* @param newpwd 新密码
	* @param newpwdtwo 新密码
	* @return
	 */
	public ApiOutParamsVm changePwd(String oldpwd,String newpwd,String newpwdtwo);
	
	/**
	 * 
	* 方法描述:忘记密码
	* 创建人: XUJC
	* 创建时间：2017年12月27日
	* @param verifykeyid 短信验证码的KEYID
	* @param verifycode 短信验证码
	* @param password 新密码
	* @return
	 */
	public ApiOutParamsVm forgetPwd(String phone,String verifycode,
			String account,String clienttype,String password,String password2);
	
	
	/**
	 * 
	* 方法描述:第三方登陆
	* 创建人: Administrator
	* 创建时间：2017年12月30日
	* @param thirdpartyid第三方登陆ID nickName 第三方昵称 thirdprttyheadimageurl
	* @param thirdparty第三方登陆类型 字符串:WeChat,Weibo,QQ,
	* @param terminalCode 设备代码
	* @param terminalType 设备类型 	IOS,ANDROID_APP,ANDROID_DEVICE
	* @param spec 设备型号 
	* @return
	 */
	public ApiOutParamsVm thirdpartyLogin(String thirdpartyid,String thirdparty,String nickName,
			String terminaltype, String uuid,String img);
	
	/**
	 * 
	* @Title: getClientListByMediationAgencyId 
	* @Description: 获取机构下属的所有员
	* @param @param agencyid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getClientListByMediationAgencyId(String agencyid);
	
	/**
	 * 用户修改昵称
	 * @param clientId
	 * @param newNickName
	 * @return
	 */
	public ApiOutParamsVm changeNickName(String clientId,String newNickName);
	
	/**
	 * 
	* @Title: getClientState 
	* @Description: 获取用户身份状态
	* @param @param clientid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getClientState(String clientid);
	
	/**
	 * 
	* @Title: checkClientOperationAuthority 
	* @Description: 验证用户的操作权限
	* @param @param clientid
	* @param @param operation
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm checkClientOperationAuthority(String clientid,String operation);
	
	/**
	 * 
	* @Title: setIosAppDebugOrNot  
	* @Description:ios版本用于设定是开发板还是真实版 
	* @param @param debug
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm setIosAppDebugOrNot(boolean debug);
	
	/**
	 * 
	* @Title: statisticsClientRes  
	* @Description: 中心管理员统计用户来源
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm statisticsClientRes();
	
}
