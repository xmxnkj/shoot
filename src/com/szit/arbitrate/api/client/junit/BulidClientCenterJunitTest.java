/**   
* @Title: ClientModuleBizFactoryTest.java
* @Package com.szit.cowell.client.factory
* @Description: TODO
* @author XUJC
* @date 2017年10月27日 下午4:30:56
* @version V1.0   
*/


package com.szit.arbitrate.api.client.junit;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Maps;
import com.hsit.common.utils.JsonFormatUtil;
import com.hsit.common.utils.JsonMapper;
import com.szit.arbitrate.api.client.bo.in.ClientLoginInBo;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.JunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.api.log.service.ApiRecordLogService;
import com.szit.arbitrate.client.entity.enumvo.TerminalType;


/**
 * 
* @ClassName: BulidClientCenterJunitTest
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:49:57
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContextJunitTest.xml"})  
public class BulidClientCenterJunitTest{

	private Logger logger = LoggerFactory.getLogger(BulidClientCenterJunitTest.class);
	
	@Autowired
	private ApiRecordLogService apiRecordLogService;
	
	private static JsonMapper jsonMapper = JsonMapper.getInstance();
	
	@Test  
	public void register(){
		
		try {
			/*
			ClilentRegisterInBoTest clientBo = new ClilentRegisterInBoTest();
			clientBo.setAccount("13630920209");
			clientBo.setPasswd("123456");
			clientBo.setClientType("Trainer");
		    String strBornDate = DateUtils.parseToString(new Date(), DateUtils.DATE_YYYYMDHM);
			clientBo.setBornDate(strBornDate);
			clientBo.setTerminalType("Account");
			clientBo.setTerminalType("ANDROID_APP");
			clientBo.setClientType("Normal");
			String datasource = jsonMapper.toJson(clientBo);
			*/
			
	        String inbo = apiRecordLogService.findByErrorcodeToInBoJson("clientcenter_019934");
			
			
			JsonFormatUtil.printJson("数据源:", inbo);
			ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientcenter","register", inbo);
			JunitTest.junitTestToInForClient(apiInVm);
		} catch (Exception e) {
			logger.error("注册单元测试异常错误",e);
		}
		

	}
	
	@Test
	public void loginForU(){
		ClientLoginInBo clientlogininbo = new ClientLoginInBo();
		clientlogininbo.setAccount("15060401169");
		clientlogininbo.setPasswd("111111");
		clientlogininbo.setLogintype("U");
		clientlogininbo.setTerminalType(TerminalType.ANDROID);
		clientlogininbo.setTerminalCode("222222222222");
		String datasource = jsonMapper.toJson(clientlogininbo);
		logger.debug("inbo:{}",datasource);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientcenter","login", datasource);
        JunitTest.junitTestToInForClient(apiInVm);
	}
	
	@Test
	public void loginForT(){
		ClientLoginInBo clientlogininbo = new ClientLoginInBo();
		clientlogininbo.setAccount("13600920209");
		clientlogininbo.setLogintype("T");
		String datasource = jsonMapper.toJson(clientlogininbo);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientcenter","login", datasource);
        JunitTest.junitTestToInForClient(apiInVm);
	}
	
	@Test
	public void myclient(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("account", "15060401169");
		String datasource = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientcenter","myclient", datasource);
        JunitTest.junitTestToInForClient(apiInVm);
	}
	
	@Test
	public void mylog(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("page", 3);
		String datasource = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientcenter","mylog", datasource);
        JunitTest.junitTestToInForClient(apiInVm);
	}
	
	@Test
	public void thirdpartlogin(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("thirdpartyid", "");
		map.put("thirdparty", "");
		map.put("nickName", "Normal");
		String inbo = jsonMapper.toJson(map);
		//ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientcenter","mylog", datasource);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "wapApiClientController","thirdpartyLogin", inbo);
		JunitTest.junitTestToInForClient(apiInVm);
	}
	
}
