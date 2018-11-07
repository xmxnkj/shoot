/**   
* @Title: BaseApiJunitTest.java
* @Package com.szit.cowell.api.common.utils
* @Description: TODO
* @author XUJC
* @date 2017年11月3日 下午2:46:36
* @version V1.0   
*/


package com.szit.arbitrate.api.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.ServletRequestEvent;
import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.web.context.request.RequestContextListener;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.utils.JsonFormatUtil;
import com.hsit.common.utils.JsonMapper;
import com.hsit.common.utils.SpringBeanUtil;
import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.client.bo.in.ClientLoginInBo;
import com.szit.arbitrate.api.client.factory.ClientModuleBizFactory;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.log.entity.ApiRecordLog;
import com.szit.arbitrate.api.log.service.ApiRecordLogService;
import com.szit.arbitrate.client.entity.enumvo.TerminalType;

/**
 * 
* @ClassName: BaseApiJunitTest
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:50:58
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration({"classpath:applicationContextJunitTest.xml"})  
//@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)  
//@Transactional  
@TransactionConfiguration(defaultRollback=false)
public class BaseApiJunitTest {
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected static JsonMapper jsonMapper = JsonMapper.getInstance();
	
	protected  HttpServletRequest request = null;
	
	protected String usercode="1234";
	protected String pwd="123";
	protected String logintype="U";
	
	private Boolean isWapLogin = true;
	
	@Autowired
	private ApiRecordLogService apiRecordLogService;
	
	private  ApiOutParamsVm outVm = null;
	
	@Before 
	public void before(){ 
		try {
			RequestContextListener listener = new RequestContextListener(); 
			MockServletContext context = new MockServletContext(); 
		    request = new MockHttpServletRequest(context); 
			listener.requestInitialized(new ServletRequestEvent(context, request));
			
			//ServletActionContext contextAction = new ServletActionContext();
			
		} catch (Exception e) {
			logger.error("before方法异常错误",e);
		}

	}
	
	/**
	 * 
	* @Title: executeApiTest 
	* @Description: 执行API测试
	* @param @param apiInVm
	* @return void 
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public void executeApiTest(ApiInParamsVm  apiInVm){
		
		try {
			if(this.getIsWapLogin()){
				//wapLogin();
			}
			BaseController<DomainEntity, EntityQueryParam> controller = (BaseController<DomainEntity, EntityQueryParam>)SpringBeanUtil.getBean(apiInVm.getBizcode());
			if(controller!=null){
				controller.setRequest(request);
				controller.setDebug(true);
				controller.init();
				controller.setInbo(apiInVm.getDatasource());
				Method method = getMethodClass(controller,apiInVm.getBizmethod());
				if(method!=null){
					controller.setMethod(method);
					Object result = method.invoke(controller, controller.buildArgs());
					controller.setResult(result);
					controller.outResultJson();
					JsonFormatUtil.printJson("API接口执行成功!返回参数:",controller.getJsonResult().toString());
					if(result instanceof ApiOutParamsVm){
						outVm = (ApiOutParamsVm)result;
					}
					return;
				}else{
					outVm = ApiTools.bulidOutFail("API接口不存在,请核对API接口业务方法是否正确!");
				}
			}
		} catch (BizException biz) {
			logger.debug("API业务接口执行出现业务错误",biz.getMessage());
			outVm = ApiTools.bulidOutFail(biz.getMessage());
			
		} catch (ErrorException error) {
			logger.error("API业务接口执行出现异常错误<1>",error);
			outVm = ApiTools.bulidOutFail("API系统接口异常错误,请联系系统管理员!");
			
		} catch (Exception e) {
			logger.error("API业务接口执行出现异常错误<2>",e);
			try {
				InvocationTargetException targetEx = (InvocationTargetException)e;
				Throwable t = targetEx .getTargetException();
				if(t instanceof BizException){
					outVm = ApiTools.bulidOutFail(t.getMessage());
				}else{
					outVm = ApiTools.bulidOutFail("API系统接口异常错误,请联系系统管理员!");
				}
			} catch (Exception e2) {
				outVm = ApiTools.bulidOutFail("API系统接口异常错误,请联系系统管理员!");
			}
		}
		String outjson = jsonMapper.toJson(outVm);
		JsonFormatUtil.printJson("API业务接口输出JSON",outjson);
	}
	
	private Method getMethodClass(BaseController<DomainEntity, EntityQueryParam> service, String methodName){
		Method[] methods = service.getClass().getMethods();
		for(int i=0; i<methods.length; i++){
			if(methods[i].getName().equals(methodName)){
				return methods[i];
			}
		}
		return null;
	}
	
	public void wapLogin(){
		ClientLoginInBo clientlogininbo = new ClientLoginInBo();
		clientlogininbo.setAccount(usercode);
		clientlogininbo.setPasswd(pwd);
		clientlogininbo.setLogintype("U");
		clientlogininbo.setTerminalType(TerminalType.ANDROID);
		clientlogininbo.setTerminalCode("f1ea5089f4944eb2c5f496ce180e9f5dfb42aa9028bbcf39ed99164374cd8a44");
		String datasource = jsonMapper.toJson(clientlogininbo);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.client, "clientcenter","login", datasource);
		ApiOutParamsVm apiOutVm = ClientModuleBizFactory.bulidBiz(apiInVm,request,null);
		JsonFormatUtil.printJson("登录后返回参数:", jsonMapper.toJson(apiOutVm));
	}

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getLogintype() {
		return logintype;
	}

	public void setLogintype(String logintype) {
		this.logintype = logintype;
	}

	public ApiRecordLogService getApiRecordLogService() {
		return apiRecordLogService;
	}

	public void setApiRecordLogService(ApiRecordLogService apiRecordLogService) {
		this.apiRecordLogService = apiRecordLogService;
	}
	
	/**
	 * 
	* 方法描述:取得Inbo
	* 创建人: XUJC
	* 创建时间：2017年12月10日
	* @param errorcode
	* @return
	 */
	public String getApiRecordLogForInBo(String errorcode){
		ApiRecordLog apiLog = apiRecordLogService.findByErrorcode(errorcode);
		return apiLog.getInbo();
	}

	public ApiOutParamsVm getOutVm() {
		return outVm;
	}

	public void setOutVm(ApiOutParamsVm outVm) {
		this.outVm = outVm;
	}

	public Boolean getIsWapLogin() {
		return isWapLogin;
	}

	public void setIsWapLogin(Boolean isWapLogin) {
		this.isWapLogin = isWapLogin;
	}
	
	
	
}
