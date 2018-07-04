package com.szit.arbitrate.client.service.impl;

import java.util.Date;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.MD5Util;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.szit.arbitrate.client.dao.ClientThirdpartyAccountDao;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.ClientThirdpartyAccount;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;
import com.szit.arbitrate.client.entity.query.ClientThirdpartyAccountQuery;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.ClientThirdpartyAccountService;
import com.szit.arbitrate.client.service.SmsVerifyRecordService;
import com.szit.arbitrate.client.utils.CommonUtil;

@Service
@Transactional
public class ClientThirdpartyAccountServiceImpl extends BaseServiceImpl<ClientThirdpartyAccount, ClientThirdpartyAccountQuery>
	implements ClientThirdpartyAccountService{

	@Autowired
	private SmsVerifyRecordService smsVerifyRecordService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ClientThirdpartyAccountDao dao;

	public ClientThirdpartyAccountDao getDao() {
		return dao;
	}

	public void setDao(ClientThirdpartyAccountDao dao) {
		this.dao = dao;
	}
	
	@Override
	public ClientThirdpartyAccount bindingPhone(String clientid, String phone,
			String verifysmscode, String verifykeyid,String pwd1,String pwd2)
			throws BizException, ErrorException {
		
		/*if(clientService.checkIsAccountRegister(phone)){
			throw new BizException("该手机已被注册过账号不能绑定!");
		}*/
		
		Client client = clientService.getById(clientid);
		if(StringUtils.isNotEmpty(client.getAccount())){
			throw new BizException("您的账号已是手机注册,不能再绑定手机!");
		}
		
		ClientThirdpartyAccountQuery query = new ClientThirdpartyAccountQuery();
		query.setThirdpartytype(ThirdPartyEnum.Phone);
		query.setClientid(clientid);
		if(this.getEntityCount(query)>0){
			throw new BizException("您已绑定过手机!");
		}
		
		Boolean blVok = smsVerifyRecordService.verifySmsCode(verifykeyid, verifysmscode);
		if(!blVok){
			throw new BizException("您输入的短信验码不正确!");
		}
		ClientThirdpartyAccount clientthirdpartyaccount  = new ClientThirdpartyAccount();
		clientthirdpartyaccount.setClientid(clientid);
		clientthirdpartyaccount.setThirdpartynickname("");
		clientthirdpartyaccount.setThirdpartytype(ThirdPartyEnum.Phone);
		clientthirdpartyaccount.setThirdprttyid(phone);
		clientthirdpartyaccount.setBindingdatetime(new Date());
		this.save(clientthirdpartyaccount);
		
		client.setAccount(phone);
		client.setPasswd(MD5Util.MD5(pwd1));
		clientService.save(client);
		
		return clientthirdpartyaccount;
	}
	
	
	@Override
	public ClientThirdpartyAccount bindingThirdparty(String clientid,
			String thirdpartyid, ThirdPartyEnum thirdparty, String thirdpartynickname,
			String thirdprttyheadimageurl) throws BizException, ErrorException {
		
		/*if(clientService.checkIsThirdpartyRegister(thirdpartyid)){
			throw new BizException("该第三方账号已被注册了不能绑定!");
		}*/
		
		
		Client client = clientService.getById(clientid);
		if(StringUtils.isNotEmpty(client.getThirdPartyId())){
			if(client.getThirdPartyId().equals(thirdpartyid)&&client.getThirdParty().equals(thirdparty)){
				throw new BizException("您要绑定的第三账号跟您注册的账号一致,不能绑定!");
			}
		}

		Boolean isBind = dao.isBindingThirdparty(clientid);
		if(isBind){
			throw new BizException("您已绑定过第三方账号!");
		}
		ClientThirdpartyAccount clientthirdpartyaccount  = new ClientThirdpartyAccount();
		clientthirdpartyaccount.setClientid(clientid);
		clientthirdpartyaccount.setThirdpartynickname(thirdpartynickname);
		clientthirdpartyaccount.setThirdpartytype(thirdparty);
		clientthirdpartyaccount.setThirdprttyid(thirdpartyid);
		clientthirdpartyaccount.setThirdprttyheadimageurl(thirdprttyheadimageurl);
		clientthirdpartyaccount.setBindingdatetime(new Date());
		this.save(clientthirdpartyaccount);
		return clientthirdpartyaccount;
	}
	
	
	@Override
	public ClientThirdpartyAccount findThirdpartyAccount(String thirdpartyid,
			ThirdPartyEnum thirdparty) throws BizException, ErrorException {
		ClientThirdpartyAccountQuery query = new ClientThirdpartyAccountQuery();
		query.setThirdprttyid(thirdpartyid);
		query.setThirdpartytype(thirdparty);
		return this.getEntity(query);
	}
	
	@Override
	public String getWechatId(String code) throws BizException, ErrorException {
		String appid = "wx1d12fb4a168a8f99";
		String secret = "0b75af795c53e14045af70b7e4e01070";
		String url = "https://api.weixin.qq.com/sns/jscode2session?grant_type=authorization_code&js_code="
		+code+"&appid="+appid+"&secret="+secret;
		String openid ="";
		JSONObject jsonObject = CommonUtil.httpsRequest(url, "GET", null);
		if (null != jsonObject) {
			openid = jsonObject.getString("openid");
		}
		return openid;
	}
	
}
