package com.szit.arbitrate.client.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.client.entity.ClientThirdpartyAccount;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;
import com.szit.arbitrate.client.entity.query.ClientThirdpartyAccountQuery;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: ClientThirdpartyAccountService
* @Description:第三方登录实体业务接口类
* @author yuyb
* @date 2017年3月17日 下午5:26:27
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ClientThirdpartyAccountService extends BaseService<ClientThirdpartyAccount, ClientThirdpartyAccountQuery>{

	/**
	 * 
	* @Title: bindingPhone 
	* @Description: TODO
	* @param @param clientid
	* @param @param phone
	* @param @param verifysmscode
	* @param @param verifykeyid
	* @param @param pwd1
	* @param @param pwd2
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return ClientThirdpartyAccount 
	* @throws
	 */
	public ClientThirdpartyAccount bindingPhone(String clientid, String phone,
			                                    String verifysmscode,String verifykeyid,
			                                    String pwd1,String pwd2)throws BizException,ErrorException;

	/**
	 * 
	* @Title: bindingThirdparty 
	* @Description: TODO
	* @param @param clientid
	* @param @param thirdpartyid
	* @param @param thirdparty
	* @param @param thirdpartynickname
	* @param @param thirdprttyheadimageurl
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return ClientThirdpartyAccount 
	* @throws
	 */
	public ClientThirdpartyAccount bindingThirdparty(String clientid,String thirdpartyid, 
			                                         ThirdPartyEnum thirdparty, String thirdpartynickname,
			                                         String thirdprttyheadimageurl)throws BizException,ErrorException;
	
	
	/**
	 * 
	* @Title: findThirdpartyAccount 
	* @Description: TODO
	* @param @param thirdpartyid
	* @param @param thirdparty
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return ClientThirdpartyAccount 
	* @throws
	 */
	public ClientThirdpartyAccount findThirdpartyAccount(String thirdpartyid,ThirdPartyEnum thirdparty)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: getWechatId  
	* @Description: 获取用户的微信openID
	* @param @param code
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return String    返回类型  
	* @throws
	 */
	public String getWechatId(String code)throws BizException,ErrorException;
	
}
