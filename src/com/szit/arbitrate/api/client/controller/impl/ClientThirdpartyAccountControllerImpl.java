package com.szit.arbitrate.api.client.controller.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.client.controller.ClientThirdpartyAccountController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.utils.HttpSessionContext;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.client.entity.ClientThirdpartyAccount;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;
import com.szit.arbitrate.client.entity.query.ClientThirdpartyAccountQuery;
import com.szit.arbitrate.client.service.ClientThirdpartyAccountService;

/**
 * 
* @ProjectName:arbitrate2
* @ClassName: ClientThirdpartyAccountControllerImpl
* @Description:
* @author Administrator
* @date 2017年3月20日 上午11:04:43
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Component("clientThirdpartyAccountController")
public class ClientThirdpartyAccountControllerImpl extends BaseController<ClientThirdpartyAccount, ClientThirdpartyAccountQuery>
		implements ClientThirdpartyAccountController {

	@Autowired
	private ClientThirdpartyAccountService clientThirdpartyAccountService;
	
	@Override
	public ApiOutParamsVm bindingPhone(String phone,String verifysmscode,
			                           String verifykeyid,String pwd1,String pwd2) {
		String clientId = HttpSessionContext.getHttpSession(this.getRequest(),"clientId");
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		if(StringUtils.isEmpty(phone)){
			return ApiTools.bulidOutFail("手机号码不能为空!");
		}
		if(StringUtils.isEmpty(phone)){
			return ApiTools.bulidOutFail("短信验证码不能为空!");
		}
		if(StringUtils.isEmpty(phone)){
			return ApiTools.bulidOutFail("短信验证KEY不能为空!");
		}
		if(StringUtils.isEmpty(pwd1)){
			return ApiTools.bulidOutFail("密码1不能为空!");
		}
		if(StringUtils.isEmpty(pwd2)){
			return ApiTools.bulidOutFail("密码2不能为空!");
		}
		if(!pwd1.equals(pwd2)){
			return ApiTools.bulidOutFail("两个密码不一致!");
		}
		ClientThirdpartyAccount clientthirdpartyaccount = clientThirdpartyAccountService.bindingPhone(clientId, phone, 
				                                                                                      verifysmscode,verifykeyid,
				                                                                                      pwd1,pwd2);
		return ApiTools.bulidOutSucceed("绑定手机成功!",clientthirdpartyaccount);
	}

	
	@Override
	public ApiOutParamsVm bindingThirdparty(String thirdpartyid, String thirdparty, 
			                                String thirdpartynickname,String thirdprttyheadimageurl) {
		String clientId = HttpSessionContext.getHttpSession(this.getRequest(),"clientId");
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		if(StringUtils.isEmpty(thirdpartyid)){
			return ApiTools.bulidOutFail("第三方账号ID不能为空!");
		}
		if(StringUtils.isEmpty(thirdparty)){
			return ApiTools.bulidOutFail("第三方账号类型不能为空!");
		}
		if(StringUtils.isEmpty(thirdpartynickname)){
			return ApiTools.bulidOutFail("第三方账号昵称不能为空!");
		}
		if(StringUtils.isEmpty(thirdprttyheadimageurl)){
			return ApiTools.bulidOutFail("第三方账号头像URL不能为空!");
		}
		ThirdPartyEnum thirdpartyEnum = ThirdPartyEnum.valueOf(thirdparty);
		ClientThirdpartyAccount clientthirdpartyaccount = clientThirdpartyAccountService.bindingThirdparty(clientId, thirdpartyid, thirdpartyEnum, thirdpartynickname, thirdprttyheadimageurl);
		return ApiTools.bulidOutSucceed("绑定手机第三方账号成功!",clientthirdpartyaccount);
	}

	@Override
	public ApiOutParamsVm clientThirdpartyAccountList() {
		String clientId = HttpSessionContext.getHttpSession(this.getRequest(),"clientId");
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		ClientThirdpartyAccountQuery query = new ClientThirdpartyAccountQuery();
		query.setClientid(clientId);
		query.addOrder("bindingdatetime", true);
		List<ClientThirdpartyAccount> list = clientThirdpartyAccountService.getEntities(query);
		return ApiTools.bulidOutSucceed(ApiConstant.GET_DATA_SUCCEED,list);
	}


	@Override
	public ApiOutParamsVm clientThirdpartyAccountUnbundling(String thirdpartyid) {
		String clientId = HttpSessionContext.getHttpSession(this.getRequest(),"clientId");
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		ClientThirdpartyAccountQuery query = new ClientThirdpartyAccountQuery();
		query.setClientid(clientId);
		query.setThirdprttyid(thirdpartyid);
		ClientThirdpartyAccount entityTemp = clientThirdpartyAccountService.getEntity(query);
		clientThirdpartyAccountService.deleteById(entityTemp.getId());
		return ApiTools.bulidOutSucceed("解绑手机成功!");
	}

	@Override
	public ApiOutParamsVm getWechatId(String code) {
		String openid = clientThirdpartyAccountService.getWechatId(code);
		return ApiTools.bulidOutSucceed("获取数据成功!", openid);
	}
	
}
