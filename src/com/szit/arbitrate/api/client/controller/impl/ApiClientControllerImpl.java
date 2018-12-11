package com.szit.arbitrate.api.client.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.utils.CommonUtil;
import com.hsit.common.utils.UploadAccResourceUtil;
import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.client.controller.ApiClientController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.utils.HttpSessionContext;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.ResTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;
import com.szit.arbitrate.client.entity.query.ClientQuery;
import com.szit.arbitrate.client.service.ClientResourceService;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.ClientTokenService;
import com.szit.arbitrate.client.service.SmsVerifyRecordService;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.service.MediationAgencyService;
import com.szit.arbitrate.pushcentre.common.IOSPushUtils;
import com.szit.arbitrate.pushcentre.factory.PushMessageDisposeFactory;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

/**
 * 
* @ClassName: ApiClientControllerImpl
* @Description:
* @author Administrator
* @date 2017年5月17日 下午4:09:01
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Component("wapApiClientController")
public class ApiClientControllerImpl extends BaseController<Client, ClientQuery> implements
		ApiClientController {

	@Autowired
	private ClientService clientService;
	@Autowired
	private ClientResourceService clientResourceService;
	@Autowired
	private SmsVerifyRecordService smsVerifyRecordService;
	@Autowired
	private ClientTokenService clientTokenService;
	@Autowired
	private MediationAgencyService mediationAgencyService;
	
	/*@Override
	public ApiOutParamsVm edit(Client entity) {
		String clientId = HttpSessionContext.getHttpSession(this.getRequest(),"clientId");
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		//不管如何后台只认当前登录用户
		File file = this.getUpload();
		if(entity != null){
			entity.setId(clientId);
		}
		
		Client client = clientService.edit(entity,file);
		return ApiTools.bulidOutSucceed("修改成功",client);
	}*/

	@Override
	public ApiOutParamsVm myClient(String clientid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientid)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Client entity = clientService.getById(clientId);
		MediationAgency mediationAgency = mediationAgencyService.getById(entity.getMediationAgencyId());
		if(mediationAgency!=null){
			entity.setAgencyName(mediationAgency.getAgencyName());
		}
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,entity);
	}

	
	@Override
	public ApiOutParamsVm changePwd(String oldpwd, String newpwd,
			String newpwdtwo) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		clientService.changePwd(clientId,oldpwd,newpwd,newpwdtwo);
		return ApiTools.bulidOutSucceed("修改密码成功");
	}

	@Override
	public ApiOutParamsVm forgetPwd(String phone, String verifycode,String account,
			String clienttype,String password,String password2) {
		clientService.forgetPwd(phone,verifycode,account,clienttype,password,password2);
		return ApiTools.bulidOutSucceed("重置密码成功");
	}
	
	@Override
	public ApiOutParamsVm certificateIdentify(String phone, String verifycode,
			String clientid,String identifyname, String identify,String address, 
			boolean gender,String nation,String birth,String profession,
			String identifyImgFile1,String identifyImgFile2, String identifyImgFile3) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		clientService.certificateIdentify(phone, verifycode, clientId, identifyname, 
				identify,address,gender, nation,birth,profession,
				identifyImgFile1, identifyImgFile2, identifyImgFile3);
		
		//推送
		String alertMessage = "有新的身份验证,请登录后台操作!";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pushAlertMessage", alertMessage);
		ClientQuery clientQuery = new ClientQuery();
		clientQuery.setClientState(ClientStateEnum.MediationCenter);
		Client client = clientService.getEntity(clientQuery);
		map.put("receiveClientId", client.getId());
		PushMessageDisposeFactory.bulidPush(PushTypeEnum.Certificate, map);
		return ApiTools.bulidOutSucceed("资料提交成功，请等待管理员审核结果!");
	}

	@Override
	public ApiOutParamsVm uploadHeadImg(String clientid, String restype) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		String resUploadFilePath = UploadAccResourceUtil.CLIENT_RES_UPLOAD_FILEPATH;
		String fileid = clientResourceService.uploadClientResources(clientId, getUpload(), getUploadFileName(), 
				resUploadFilePath, ResTypeEnum.valueOf(restype));
		return ApiTools.bulidOutSucceed("上传成功!",fileid);
	}

	
	@Override
	public ApiOutParamsVm registerAccount(String name, String address,String verifycode, 
			String account, String clienttype,String password, String password2) {
		clientService.registerAccount(name, address, verifycode, account, clienttype, password, password2);
		return ApiTools.bulidOutSucceed("恭喜您!注册成功!");
	}
	
	@Override
	public ApiOutParamsVm login(String clienttype,String account, String passwd, String terminaltype, String uuid) {
		String ip = this.getRequest().getRemoteAddr();
		//验证密码 在用户表保存了ip 用户表压力不够大 新进一张登录表啊  保存了设备信息
		Client client = clientService.login(clienttype, account.trim(), passwd, ip, terminaltype, uuid);
		
		HttpServletRequest request = this.getRequest();
		/*HttpServletResponse response = this.getResponse();
		Cookie c1 = new Cookie("jsessionid",request.getSession().getId());
		response.addCookie(c1);*/
		
		logger.debug("===============保存进session-begin sessidid:"+request.getSession().getId()+"==============================");
		logger.debug("<<<名称:{}登录后后会话ID:{}>>>",CommonUtil.sessionMap);
		logger.debug("<<<名称:{}登录后后会话ID:{}>>>",request.getSession().getId());
		
		client.setSessionId(request.getSession().getId());
		//session
		clientService.save(client);
		
		request.getSession().setAttribute("clientId", client.getId());
		String clientId = HttpSessionContext.getHttpSession(request, "clientId");
		logger.debug("session clientId:{}",clientId);
		//CommonUtil.sessionMap.put(clientId, request.getSession());
		logger.debug("===============保存进session-end sessidid:"+request.getSession().getId()+"==============================");
		MediationAgency mediationAgency = mediationAgencyService.getById(client.getMediationAgencyId());
		if(mediationAgency!=null){
			client.setAgencyName(mediationAgency.getAgencyName());
		}
		return ApiTools.bulidOutSucceed("登录成功!",client);
	}
	
	@Override
	public ApiOutParamsVm exitLogin() {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		HttpServletRequest request = this.getRequest();
		if(request.getSession().getAttribute(clientId) != null){
			request.getSession().removeAttribute(clientId);
		}
		return ApiTools.bulidOutSucceed("退出成功!");
	}
	
	@Override
	public ApiOutParamsVm validationAccount(String account) {
		ClientQuery query = new ClientQuery();
		query.setAccount(account);
		Client clientTemp = clientService.getEntity(query);
		if (clientTemp!=null) {
			return ApiTools.bulidOutFail("账号已被注册！");
		}
		return ApiTools.bulidOutSucceed("账号可用！");
	}
	

	@Override
	public ApiOutParamsVm thirdpartyLogin(String thirdpartyid,String thirdparty,String nickName,
			String terminaltype, String uuid,String img) {
		ThirdPartyEnum thirdpartyEnum = ThirdPartyEnum.valueOf(thirdparty);
		
		Client client = clientService.thirdpartyLogin(thirdpartyid, thirdpartyEnum,nickName,"", terminaltype, uuid);
		//保存第三方图片路径
		client.setHeadImgFile(img);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		Cookie c1 = new Cookie("jsessionid",request.getSession().getId());
		response.addCookie(c1);
		logger.debug("===============保存进session-begin sessidid:"+this.getSession().getId()+"==============================");

		logger.debug("<<<名称:{}登录后后会话ID:{}>>>",request.getSession().getId());
		client.setSessionId(request.getSession().getId());
		
		clientService.save(client);
		this.getSession().setAttribute("clientId", client.getId());
		String clientId = HttpSessionContext.getHttpSession(request, "clientId");
		CommonUtil.sessionMap.put(clientId, request.getSession());
		logger.debug("session clientId:{}",clientId);
		logger.debug("===============保存进session-end sessidid:"+request.getSession().getId()+"==============================");

		
		return ApiTools.bulidOutSucceed("登陆成功",client);
	}


	@Override
	public ApiOutParamsVm registerSmsVerify(String verifykeyid,String verifycode) {
		Boolean isOK = smsVerifyRecordService.verifySmsCode(verifykeyid, verifycode);
		if(!isOK){
			return ApiTools.bulidOutFail("您输入的验证码错误");
		}
		return ApiTools.bulidOutSucceed("验证成功");
	}


	@Override
	public ApiOutParamsVm changeNickName(String clientId, String newNickName) {
		clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		if(newNickName==""){
			return ApiTools.bulidOutSucceed("昵称不能为空");
		}
		
		clientService.changeNickName(clientId, newNickName);
		
		return ApiTools.bulidOutSucceed("昵称修改成功");
	}


	@Override
	public ApiOutParamsVm getClientListByMediationAgencyId(String agencyid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		List<Client> list = clientService.getClientListByMediationAgencyId(agencyid);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED, list);
	}
	
	@Override
	public ApiOutParamsVm getClientState(String clientid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Client client = clientService.getById(clientId);
		if(client == null){
			throw new BizException("用户不存在,请检查!");
		}
		String clientstate = "";
		if(client.getAuditInfo() != null){
			clientstate = client.getAuditInfo().toString();
		}
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED, clientstate);
	}
	
	@Override
	public ApiOutParamsVm checkClientOperationAuthority(String clientid,
			String operation) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		boolean haveAuthority = clientService.checkClientOperationAuthority(clientId, operation);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED, haveAuthority);
	}
	
	@Override
	public ApiOutParamsVm setIosAppDebugOrNot(boolean debug) {
		if(debug){
			IOSPushUtils.getInstance().setIsdebug(true);
		}else{
			IOSPushUtils.getInstance().setIsdebug(false);
		}
		return ApiTools.bulidOutSucceed("操作成功");
	}
	
	@Override
	public ApiOutParamsVm statisticsClientRes() {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String, Object> result = clientService.statisticsClientRes();
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED, result);
	}
	
}
