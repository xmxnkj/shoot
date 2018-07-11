package com.szit.arbitrate.client.junit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsit.common.utils.BeanCopyUtils;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.client.dao.ClientDao;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.ClientThirdpartyAccountService;

public class ClientServiceJunit extends BaseApiJunitTest{
	
	@Autowired
	private ClientService service;
	
	@Autowired
	private ClientThirdpartyAccountService clientThirdpartyAccountService;
	
	
	@Autowired
	private ClientDao dao;
	
	private Logger logger = LoggerFactory.getLogger(ClientServiceJunit.class);
	
	@Test
	public void thirdpartyLogin(){
		String thirdpartyid = "18805921191";
		String nickName = "烟波江上";
		String thirdprttyheadimageurl = "";
	//	Client client = service.thirdpartyLogin(thirdpartyid, ThirdPartyEnum.WeChat, nickName, thirdprttyheadimageurl,"","","");
	//	logger.debug(client.getAccount());
	}
	
	//修改昵称
	@Test
	public void changeNickName(){
		String clientId = "54e46bb5-91ce-4663-beb2-db093504248c";
		String newNickName = "皮匠";
		service.changeNickName(clientId, newNickName);
	}
	
	@Test
	public void createManagerClientByType(){
		String account = "18805921193";
		String clienttype = "Mediator";
		String clientstate = "Mediator";
		String password = "123456";
		String identifyName = "111";//
		String identify = "23256256635555";//
		String tel = "12356548995";//
		String mediationAgencyId = "";//
		//service.createManagerClientByType(account, identifyName, identify, tel, clienttype, clientstate, password,mediationAgencyId,"","");
	}
	
	@Test
	public void auditCertificateIdentify(){
		String clientid = "4c5f9913-8c38-4b6b-8822-b66b9e75dd1a"; 
		String oper = "pass";
		service.auditCertificateIdentify(clientid, oper);
	}
	
	@Test
	public void cloneObj(){
		Client client=service.getById("54e46bb5-91ce-4663-beb2-db093504248c");
		try {
			Client c = (Client) BeanCopyUtils.beanUtilsCopy(client, Client.class);
			c.setId(null);
			c.setClientType(ClientTypeEnum.Mediator);
			service.save(c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void statisticsClientRes(){
		service.statisticsClientRes();
	}
	
	@Test
	public void getWechatId(){
		String code = "031T85c01UhTK22VlDd01Crbc01T85cR";
		clientThirdpartyAccountService.getWechatId(code);
	}
	
}
