package com.szit.arbitrate.client.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;
import com.szit.arbitrate.client.entity.query.ClientQuery;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: ClientService
* @Description:用户实体业务接口
* @author yuyb
* @date 2017年3月17日 下午5:22:13
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ClientService extends BaseService<Client, ClientQuery>{
	
	/**
	 * 
	* @Title: changePwd 
	* @Description: 修改密码
	* @param @param clientId
	* @return void 
	* @throws
	 */
	public void changePwd(String clientId,String oldpwd, String newpwd,String newpwdtwo)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: forgetPwd 
	* @Description: 忘记密码
	* @param @param verifykeyid
	* @param @param verifycode
	* @param @param newpwd
	* @param @param clienttype
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void forgetPwd(String phone,String verifycode,String account,String clienttype,
			String newpwd,String newpwd2)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: login 
	* @Description: 用户登录
	* @param @param clienttype用户类型
	* @param @param account
	* @param @param passwd
	* @param @return
	* @return Client 
	* @throws
	 */
	public Client login(String clienttype, String account, String passwd, String ip,
			String terminaltype, String uuid);
	
	
	/**
	 * 
	* 方法描述:第三方登陆
	* 创建人: Administrator
	* 创建时间：2017年3月20日
	* @param thirdpartyid 第三方登陆ID
	* @param thirdParty 第三方登陆类型
	* @return
	* @throws BizException
	* @throws ErrorException
	 */
	public Client thirdpartyLogin(String thirdpartyid,ThirdPartyEnum thirdParty,String nickName,
			String thirdprttyheadimageurl,String terminaltype, String uuid)throws BizException,ErrorException;

	
	
	/**
	 * 
	* @Title: registerAccount 
	* @Description: 用户注册
	* @param @param verifykeyid
	* @param @param verifycode
	* @param @param account
	* @param @param password
	* @param @param password2
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return Client 
	* @throws
	 */
	public Client registerAccount(String name, String address, String verifycode, String account,
			String clienttype, String password, String password2)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: certificateIdentify 
	* @Description: 实名认证
	* @param @param clientid
	* @param @param identifyname
	* @param @param identify
	* @param @param identifyImgFile1
	* @param @param identifyImgFile2
	* @param @param identifyImgFile3
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void certificateIdentify(String phone, String verifycode,String clientid,
			String identifyname,String identify,String address,
			boolean gender,String nation,String birth,String profession,
			String identifyImgFile1,String identifyImgFile2,String identifyImgFile3)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: auditCertificateIdentify 
	* @Description: 审核实名认证信息
	* @param @param clientid
	* @param @param oper
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void auditCertificateIdentify(String clientid, String oper)throws BizException,ErrorException;
	
	
	/**
	 * 
	* @Title: getClientByAccountAndType 
	* @Description: 根据账号和会员类型取得会员对象
	* @param @param clienttype
	* @param @param account
	* @param @return
	* @return Client 
	* @throws
	 */
	public Client getClientByAccountAndType(String clienttype, String account);
	
	
	/**
	 * 
	* @Title: getClient 
	* @Description: 根据用户账号，密码。用户类型取得用户对象
	* @param @param clienttype
	* @param @param account
	* @param @param encrptedPasswd
	* @param @return
	* @return Client 
	* @throws
	 */
	public Client getClient(String clienttype, String account, String encrptedPasswd);
	
	
	/**
	 * 
	* 方法描述:检查账号是否注册过
	* 创建人: Administrator
	* 创建时间：2017年3月20日
	* @param account
	* @return
	* @throws BizException
	* @throws ErrorException
	 */
	public Boolean checkIsAccountRegister(String account,String clienttype)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: getClientListByMediationAgencyId 
	* @Description: 查询机构下属的所有员
	* @param @param agencyid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return List<Client> 
	* @throws
	 */
	public List<Client> getClientListByMediationAgencyId(String agencyid)throws BizException,ErrorException;
	
	/**
	 * 修改昵称
	 * @param clientId
	 * @param newNickName
	 * @throws BizException
	 * @throws ErrorException
	 */
	public void changeNickName(String clientId,String newNickName)throws BizException,ErrorException;

	/**
	 * 
	* @Title: createManagerClientByType 
	* @Description: 创建管理员账号，主要用于测试以及后台创建管理员
	* @param @param account
	* @param @param clienttype
	* @param @param password
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void createManagerClientByType(String account, String identifyName, String identify, String tel,
			String clienttype, String clientstate, String password,String mediationAgencyId,String skill,String description,String headImgFile) throws BizException,ErrorException;

	/**
	 * 
	* @Title: checkClientOperationAuthority 
	* @Description: 验证用户操作权限
	* @param @param clientid
	* @param @param operation
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return boolean 
	* @throws
	 */
	public boolean checkClientOperationAuthority(String clientid, String operation)throws BizException,ErrorException;

	/**
	 * 
	* @Title: statisticsClientRes  
	* @Description:中心管理员统计用户来源信息 
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return Map<String,Object>    返回类型  
	* @throws
	 */
	public Map<String, Object> statisticsClientRes()throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: isLoginSessionOnline  
	* @Description: 判断登录唯一性
	* @param @param request
	* @param @return    设定文件  
	* @return String    返回类型  
	* @throws
	 */
	public String isLoginSessionOnline(HttpServletRequest request);
	
}
