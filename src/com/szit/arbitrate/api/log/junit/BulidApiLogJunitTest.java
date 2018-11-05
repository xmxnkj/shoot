/**   
* @Title: ClientModuleBizFactoryTest.java
* @Package com.szit.cowell.client.factory
* @Description: TODO
* @author XUJC
* @date 2017年10月27日 下午4:30:56
* @version V1.0   
*/


package com.szit.arbitrate.api.log.junit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.utils.JunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.api.log.entity.ApiRecordLog;
import com.szit.arbitrate.api.log.service.ApiRecordLogService;


/**
 * @ProjectName:xmszit-cowell-module-client
 * @ClassName: ClientModuleBizFactoryTest
 * @Description:
 * @author XUJC
 * @date 2017年10月27日 下午4:30:56
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
public class BulidApiLogJunitTest  extends BaseApiJunitTest{

	private Logger logger = LoggerFactory.getLogger(BulidApiLogJunitTest.class);
	
	@Autowired
	private ApiRecordLogService apiRecordLogService;
	//错误代码
	private String errorcode = "querylist_188550";
	
	@Test  
	public void debugErrorCode(){
		try {
			this.setUsercode("1234");
			this.setPwd("123");
			this.wapLogin();
			ApiRecordLog apiLog = apiRecordLogService.findByErrorcode(errorcode);
			ApiInParamsVm  apiInVm = ApiTools.bulidInparam(apiLog.getModulecode(), apiLog.getBizcode(),apiLog.getBizmethod(), apiLog.getInbo());
			//JunitTest.junitTestToInForLiveBroadcast(apiInVm,this.request,null);
			//JunitTest.junitTestToInForClient(apiInVm);
		} catch (Exception e) {
			logger.error("注册单元测试异常错误",e);
		}
	}
	
	@Test  
	public void debugLast(){
		try {
			
			ApiRecordLog apiLog = apiRecordLogService.findLastError();
			ApiInParamsVm  apiInVm = ApiTools.bulidInparam(apiLog.getModulecode(), apiLog.getBizcode(),apiLog.getBizmethod(), apiLog.getInbo());
			JunitTest.junitTestToInForClient(apiInVm);
		} catch (Exception e) {
			logger.error("注册单元测试异常错误",e);
		}
	}
	
	@Test  
	public void readError(){
		try {
			ApiRecordLog apiLog = apiRecordLogService.findByErrorcode(errorcode);
			logger.debug("错误码:{}",apiLog.getErrorcode());
			logger.debug("输入业务参:{}",apiLog.getInbo());
			logger.debug("错误消息:{}",apiLog.getErrormessage());
			logger.debug("错误堆栈:{}",apiLog.getErrorstack());
		} catch (Exception e) {
			logger.error("注册单元测试异常错误",e);
		}
	}
	
	@Test
	public void redModuleError(){
		
	}

	
}
