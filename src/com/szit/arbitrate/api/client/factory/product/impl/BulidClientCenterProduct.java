/**   
* @Title: BulidRegisterProduct.java
* @Package com.szit.cowell.client.factory.product.impl
* @Description: TODO
* @author XUJC
* @date 2017年10月27日 下午3:45:45
* @version V1.0   
*/


package com.szit.arbitrate.api.client.factory.product.impl;


import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsit.common.exceptions.ApplicationException;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.utils.JsonMapper;
import com.hsit.common.utils.SpringBeanUtil;
import com.szit.arbitrate.api.client.bo.in.ClientLoginInBo;
import com.szit.arbitrate.api.client.bo.out.ClientLoginOutBo;
import com.szit.arbitrate.api.client.bo.out.MyClientOutBo;
import com.szit.arbitrate.api.client.bo.out.MyLogsOutBo;
import com.szit.arbitrate.api.client.factory.product.IApiClientProduct;
import com.szit.arbitrate.api.common.ApiBoFormTools;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.HttpSessionContext;
import com.szit.arbitrate.api.common.utils.PageUtils;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.common.vm.PageDataOutBoVm;
import com.szit.arbitrate.api.log.entity.ApiRecordLog;
import com.szit.arbitrate.api.log.entity.query.ApiRecordLogQuery;
import com.szit.arbitrate.api.log.service.ApiRecordLogService;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.service.ClientService;


/**
 * 
* @ClassName: BulidClientCenterProduct
* @Description:会员中心功能
* @author Administrator
* @date 2017年3月20日 下午2:49:03
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public class BulidClientCenterProduct implements IApiClientProduct {

	private Logger logger = LoggerFactory.getLogger(BulidClientCenterProduct.class);
	private static JsonMapper jsonMapper = JsonMapper.getInstance();
	
	private ClientService clientService;
	
	private ApiRecordLogService apiRecordLogService;
	
	private int rows =10;
	
	public enum BIZMETHODENUM {
		register,login,myclient,mylog
	}

	@Override
	public ApiOutParamsVm bulidBiz(ApiInParamsVm inVm,HttpServletRequest request,HttpServletResponse response) throws BizException,
			ErrorException {
		ApiOutParamsVm outVm = null;
		try {
			outVm = new ApiOutParamsVm();
			clientService = (ClientService)SpringBeanUtil.getBean("clientServiceImpl");
			apiRecordLogService = (ApiRecordLogService)SpringBeanUtil.getBean("apiRecordLogServiceImpl");
			//注册
//			if(BIZMETHODENUM.register.name().equals(inVm.getBizmethod())){
//				ClilentRegisterInBo clientInBo =  ApiBoFormTools.formInBo(inVm, ClilentRegisterInBo.class);
//				Client client = (Client)ApiBoFormTools.formCopyOutToBo(clientInBo,Client.class,new String[] {"bornDate"});
//				client.setBornDate(DateUtils.parseToDate(clientInBo.getBornDate(), DateUtils.DATE_YMG_STR));
//				Terminal terminal = new Terminal();
//				terminal.setTerminalCode(clientInBo.getTerminalCode());
//				clientService.register(client,terminal, null);
//				outVm = ApiTools.bulidOutSucceed("恭喜!注册成功!");
//			}
			//登陆
		   /*if(BIZMETHODENUM.login.name().equals(inVm.getBizmethod())){
				ClientLoginInBo loginInBo = ApiBoFormTools.formInBo(inVm, ClientLoginInBo.class);
				Client client = clientService.login(loginInBo.getAccount(), loginInBo.getPasswd(),
						                            loginInBo.getLogintype(),loginInBo.getTerminalCode(),
						                            loginInBo.getTerminalType(),loginInBo.getSpec());
				
				
				logger.debug("===============保存进session-begin sessidid:"+request.getSession().getId()+"==============================");
				
				logger.debug("<<<名称:{}登录后后会话ID:{}>>>",client.getNickName(),request.getSession().getId());
				
				String localeUserinfo = SpringBeanUtil.getAppContext().getMessage("userinfo",null,Locale.US); 
				logger.debug("localeUserinfo:{}",localeUserinfo);
				
				
				//创建一个SESSION PK池对象
				LanguageTypeEnum languagetype  =  null;
				if(StringUtils.isEmpty(loginInBo.getLanguage())){//如果空默认中文
					languagetype = LanguageTypeEnum.chinese;
				}else{
					languagetype  = LanguageTypeEnum.valueOf(loginInBo.getLanguage());
				}
				//保存语言session
				HttpSessionContext.setHttpSessionByLanguage(request, languagetype);
				
				
				request.getSession().setAttribute("client", client);
				request.getSession().setAttribute("clientId", client.getId());
				request.getSession().setAttribute("account",client.getAccount());
				request.getSession().setAttribute("nickName",client.getNickName());
				if(StringUtils.isNotEmpty(loginInBo.getTerminalCode())){
					request.getSession().setAttribute("terminalCode",loginInBo.getTerminalCode());
				}else{
					throw new BizException("请检查终端码,不能为空!");
				}
				if(loginInBo.getTerminalType()!=null){
					request.getSession().setAttribute("terminalType",loginInBo.getTerminalType().name());
				}
				else{
					throw new BizException("请检查终端类型,不能为空!");
				}
				if(StringUtils.isNotEmpty(loginInBo.getSpec())){
					request.getSession().setAttribute("spec",loginInBo.getSpec());
				}
				String clientId = HttpSessionContext.getHttpSession(request, "clientId");
				String account = HttpSessionContext.getHttpSession(request, "account");
				logger.debug("session clientId:{}",clientId);
				logger.debug("session account:{}",account);
				logger.debug("===============保存进session-end sessidid:"+request.getSession().getId()+"==============================");

				
				ClientLoginOutBo outBoJson = (ClientLoginOutBo)ApiBoFormTools.formCopyOutToBo(client,ClientLoginOutBo.class);
				outVm = ApiTools.bulidOutSucceed("登录成功!",outBoJson);
			}
			else if(BIZMETHODENUM.myclient.name().equals(inVm.getBizmethod())){//我的会员信息
				Map<String,Object> mapParams = ApiBoFormTools.formInBoToMap(inVm);
				logger.debug("传入BO业务对象:{}",mapParams);
				Client client =  clientService.getClientByAccount(mapParams.get("account").toString());
				MyClientOutBo outBoJson = (MyClientOutBo)ApiBoFormTools.formCopyOutToBo(client,MyClientOutBo.class);
				outVm = ApiTools.bulidOutSucceed("获取数据成功!",outBoJson);
			}
			else if(BIZMETHODENUM.mylog.name().equals(inVm.getBizmethod())){//测试分页日志接口分页
				Map<String,Object> mapParams = ApiBoFormTools.formInBoToMap(inVm);
				int page = (Integer)mapParams.get("page");
				ApiRecordLogQuery query = PageUtils.setPages(rows, page, ApiRecordLogQuery.class);
				List<ApiRecordLog> list = apiRecordLogService.getEntities(query);
				List<MyLogsOutBo> outBoList = ApiBoFormTools.formCopyToBoList(list, MyLogsOutBo.class);
				PageDataOutBoVm<MyLogsOutBo> pageData = new PageDataOutBoVm<MyLogsOutBo>(query.getPaging(),outBoList);
				outVm = ApiTools.bulidOutSucceed("获取数据成功!",pageData);
			}
			else{
				outVm = ApiTools.bulidOutFail("非法参数,业务方法不存在,请核查!");
			}*/
			return outVm;
		} catch (BizException biz) {
			String errorcode = apiRecordLogService.saveApiErrorLog(inVm,biz.getMessage(),biz);
			outVm = ApiTools.bulidOutFail(biz.getMessage(),errorcode);
		} catch (ApplicationException apperoor) {
			logger.error("会员中心功能系统异常错误",apperoor);
			String errorcode = apiRecordLogService.saveApiErrorLog(inVm,apperoor.getMessage(),apperoor);
			outVm = ApiTools.bulidOutFail(apperoor.getMessage(),errorcode);
		}
		catch (ErrorException error) {
			logger.error("会员中心功能系统异常错误",error);
			String errorcode = apiRecordLogService.saveApiErrorLog(inVm,error.getMessage(),error);
			outVm = ApiTools.bulidOutFail("API系统接口异常错误<1>,请联系系统管理员!",errorcode);
		} catch (Exception e) {
			logger.error("会员中心功能系统异常错误",e);
			String errorcode = apiRecordLogService.saveApiErrorLog(inVm,e.getMessage(),e);
			outVm = ApiTools.bulidOutFail("API系统接口异常错误<2>,请联系系统管理员!",errorcode);
		}
        return outVm;
	}

}
