package com.szit.arbitrate.client.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hsit.common.MD5Util;
import com.hsit.common.exceptions.ApplicationException;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.szit.arbitrate.client.dao.ClientDao;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.ClientThirdpartyAccount;
import com.szit.arbitrate.client.entity.ClientToken;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;
import com.szit.arbitrate.client.entity.enumvo.CertificateStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientLoginTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.SmsBizModelEnum;
import com.szit.arbitrate.client.entity.enumvo.TerminalType;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;
import com.szit.arbitrate.client.entity.query.ClientQuery;
import com.szit.arbitrate.client.entity.query.ClientTokenQuery;
import com.szit.arbitrate.client.service.AuthorityGroupOperationService;
import com.szit.arbitrate.client.service.ClientAuthorityGroupService;
import com.szit.arbitrate.client.service.ClientResourceService;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.ClientThirdpartyAccountService;
import com.szit.arbitrate.client.service.ClientTokenService;
import com.szit.arbitrate.client.service.SmsVerifyRecordService;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.service.MediationAgencyService;
import com.szit.arbitrate.pushcentre.factory.PushMessageDisposeFactory;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

/**
 * 
* @ProjectName:
* @ClassName: ClientServiceImpl
* @Description:用户业务实现类
* @author Administrator
* @date 2017年3月24日 下午3:29:56
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class ClientServiceImpl extends BaseServiceImpl<Client, ClientQuery> implements ClientService{

	@Autowired
	private ClientDao dao;
	@Autowired
	private SmsVerifyRecordService smsVerifyRecordService;
	@Autowired
	private ClientThirdpartyAccountService clientThirdpartyAccountService;
	@Autowired
	private ClientAuthorityGroupService clientAuthorityGroupService;
	@Autowired 
	private ClientResourceService clientResourceService;
	@Autowired
	private AuthorityGroupOperationService authorityGroupOperationService;
	@Autowired
	private MediationAgencyService mediationAgencyService;
	@Autowired
	private ClientTokenService clientTokenService;

	public ClientDao getDao() {
		return dao;
	}

	public void setDao(ClientDao dao) {
		this.dao = dao;
	}
	
	public void changePwd(String clientId,String oldpwd, String newpwd,String newpwdtwo){
		Client client = this.getById(clientId);
		logger.debug("db pwd:{}",client.getPasswd().trim());
		logger.debug("oldpwd:{}",MD5Util.MD5(oldpwd).trim());
		if(!client.getPasswd().trim().equals(MD5Util.MD5(oldpwd).trim())){
			throw new ApplicationException("旧密码不正确!");
		}
		if(StringUtils.isEmpty(newpwd)){
			throw new ApplicationException("新密码不能为空!");
		}
		if(StringUtils.isEmpty(newpwdtwo)){
			throw new ApplicationException("确认新密码不能为空!");
		}
		if(!newpwd.equals(newpwdtwo)){
			throw new ApplicationException("两次输入密码不一致!");
		}
		client.setPasswd(MD5Util.MD5(newpwd));
		this.save(client);
		
	};
	public Client login(String clienttype,String account, String passwd, String ip,
			String terminaltype, String uuid) {
		if(TerminalType.valueOf(terminaltype) == null){
			throw new BizException("设备类型参数不匹配!");
		}
		if(StringUtils.isEmpty(terminaltype)){
			throw new BizException("设备类型不能为空!");
		}
		if(StringUtils.isEmpty(uuid)){
			throw new BizException("uuid不能为空!");
		}
		if(StringUtils.isEmpty(account)){
			throw new BizException("用户名不能为空!");
		}
		if(StringUtils.isEmpty(passwd)){
			throw new BizException("密码不能为空!");
		}
		Client temp = getClientByAccountAndType(clienttype, account);
		if(temp == null){
			throw new BizException("账号不存在!");
		}
		if(!MD5Util.MD5(passwd).equals(temp.getPasswd())){
			throw new BizException("密码不正确!");
		}
		Client client = getClient(clienttype, account, MD5Util.MD5(passwd));
		client.setIp(ip);
		this.save(client);
		
		//保存设备信息
		ClientTokenQuery clientTokenQuery = new ClientTokenQuery();
		clientTokenQuery.setClientId(client.getId());
		ClientToken clientToken = clientTokenService.getEntity(clientTokenQuery);
		if(clientToken == null){
			clientToken = new ClientToken();
			clientToken.setClientId(client.getId());
		}
		clientToken.setTerminalType(TerminalType.valueOf(terminaltype));
		clientToken.setUuid(uuid);
		clientToken.setLoginTime(new Date());
		clientTokenService.save(clientToken);
		
		return client;
	}
	
	public Client getClientByAccountAndType(String clienttype, String account){
		if(!StringUtils.isEmpty(account)){
			ClientQuery query = new ClientQuery();
			query.setAccount(account);
			query.setClientType(ClientTypeEnum.valueOf(clienttype));
			return getEntity(query);
		}
		
		return null;
	}
	
	public Client getClient(String clienttype,String account, String encrptedPasswd) {
		if (!StringUtils.isEmpty(account) && !StringUtils.isEmpty(encrptedPasswd)) {
			ClientQuery query = new ClientQuery();
			query.setAccount(account);
			query.setPasswd(encrptedPasswd);
			query.setClientType(ClientTypeEnum.valueOf(clienttype));
			return getEntity(query);
		}
		return null;
	}
	
	
	@Override
	public Client registerAccount(String name, String address, String verifycode,
			String account, String clienttype, String password, String password2)
			throws BizException, ErrorException {
		//1.用户注册
		Client clientTemp = getClientByAccountAndType(clienttype,account);
		if (clientTemp!=null) {
			throw new BizException("账号已被注册！");
		}
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(address)){
			throw new BizException("非法参数,姓名住址不能为空!");
		}
		if(StringUtils.isEmpty(verifycode)){
			throw new BizException("非法参数,验证码不能为空!");
		}
		if(!password.equals(password2)){
			throw new BizException("前后密码输入不一致!");
		}
		Boolean isOK = smsVerifyRecordService.verifySmsCode(account, verifycode);
		if(!isOK){
			throw new BizException("亲,您输入的验证码不正确!");
		}
		Client client = new Client();
		client.setIdentifyName(name);
		client.setTel(account);
		client.setAddress(address);
		client.setAccount(account);
		client.setNickName(account);
		client.setPasswd(MD5Util.MD5(password));
		client.setClientState(ClientStateEnum.Normal);
		client.setClientType(ClientTypeEnum.Normal);
		client.setLoginType(ClientLoginTypeEnum.ClientLogin);
		client.setResgitTime(new Date());
		String clientid = this.save(client);
		
		//2.设置该用户的权限组
		String authorityGroupId = clientAuthorityGroupService.setClientAuthorityGroup(clientid, AuthorityGroupEnum.Normal);
		
		//3.为普通注册用户分配可以申请的操作权限
		authorityGroupOperationService.setClientAuthorityGroupOperation(clientid, authorityGroupId, "ApplyMediation");
		
		return client;
	}
	
	
	@Override
	public void forgetPwd(String phone, String verifycode, String account,
			String clienttype,String newpwd,String newpwd2)
			throws BizException, ErrorException {
		if(StringUtils.isEmpty(account)){
			throw new BizException("非法参数,手机号码不能为空!");
		}
		if(StringUtils.isEmpty(verifycode)){
			throw new BizException("非法参数,验证码不能为空!");
		}
		if(StringUtils.isEmpty(newpwd)){
			throw new BizException("非法参数,新密码不能为空!");
		}
		if(!newpwd.equals(newpwd2)){
			throw new BizException("前后密码输入不一致!");
		}
		Boolean isOK = smsVerifyRecordService.verifySmsCode(phone, verifycode);
		if(!isOK){
			throw new BizException("亲,您输入的验证码不正确!");
		}
		Client client = getClientByAccountAndType(clienttype, account);
		if(client == null){
			throw new BizException("账号不存在!");
		}
		client.setPasswd(MD5Util.MD5(newpwd));
		this.save(client);
	}
	
	@Override
	public Client thirdpartyLogin(String thirdpartyid, ThirdPartyEnum thirdParty,String nickName,
			String thirdprttyheadimageurl,String terminaltype, String uuid)throws BizException, ErrorException {
		if(StringUtils.isEmpty(thirdpartyid)){
			throw new BizException("第三方账号不能为空!");
		}
		if(StringUtils.isEmpty(nickName)){
			throw new BizException("第三方账号昵称不能为空!");
		}
		if(thirdParty == null){
			throw new BizException("第三方账号类型不能为空!");
		}
		ClientQuery query = new ClientQuery();
		query.setThirdPartyId(thirdpartyid);
		query.setThirdParty(thirdParty);
		Client client = this.getEntity(query);
		if(client==null){ //等于空，说明是第一次登录，创建一个新的用户client
			client = new Client();
			client.setNickName(nickName);
			String account = "";
			if(thirdpartyid.length() > 13){
				account = thirdpartyid.substring(0, 12);
			}else{
				account = thirdpartyid;
			}
			client.setAccount(account);
			if(StringUtils.isNotEmpty(thirdprttyheadimageurl)){
				client.setHeadImgFile(thirdprttyheadimageurl);
			}
			client.setIdentifyName(nickName);
			client.setThirdPartyId(thirdpartyid);
			client.setThirdParty(thirdParty);
			if(thirdParty.equals(ThirdPartyEnum.WeChatApplet)){
				client.setClientState(ClientStateEnum.Normal);
				client.setClientType(ClientTypeEnum.Normal);
				client.setLoginType(ClientLoginTypeEnum.WeChatApplet);
			}else{
				client.setLoginType(ClientLoginTypeEnum.ThirdParty);
				client.setClientState(ClientStateEnum.Visitor);
				client.setClientType(ClientTypeEnum.Visitor);
			}
			String clientid = this.save(client);
			
			ClientThirdpartyAccount clientthirdpartyaccount = clientThirdpartyAccountService.findThirdpartyAccount(thirdpartyid, thirdParty);
			if(clientthirdpartyaccount==null){//创建一个新的第三方账号实体
				clientthirdpartyaccount  = new ClientThirdpartyAccount();
				clientthirdpartyaccount.setClientid(client.getId());
				clientthirdpartyaccount.setThirdpartynickname(nickName);
				clientthirdpartyaccount.setThirdpartytype(thirdParty);
				clientthirdpartyaccount.setThirdprttyid(thirdpartyid);
				clientthirdpartyaccount.setThirdprttyheadimageurl(thirdprttyheadimageurl);
				clientthirdpartyaccount.setBindingdatetime(new Date());
				clientThirdpartyAccountService.save(clientthirdpartyaccount);
			}
			//设置该用户的权限组
			if(thirdParty.equals(ThirdPartyEnum.WeChatApplet)){
				String authorityGroupId = clientAuthorityGroupService.setClientAuthorityGroup(clientid, AuthorityGroupEnum.Normal);
				//3.为普通注册用户分配可以申请的操作权限
				authorityGroupOperationService.setClientAuthorityGroupOperation(clientid, authorityGroupId, "ApplyMediation");
			}else{
				clientAuthorityGroupService.setClientAuthorityGroup(clientid, AuthorityGroupEnum.Visitor);
			}
		}
		
		//保存设备信息
		ClientTokenQuery clientTokenQuery = new ClientTokenQuery();
		clientTokenQuery.setClientId(client.getId());
		ClientToken clientToken = clientTokenService.getEntity(clientTokenQuery);
		if(clientToken == null){
			clientToken = new ClientToken();
			clientToken.setClientId(client.getId());
		}
		clientToken.setTerminalType(TerminalType.valueOf(terminaltype));
		if(StringUtils.isNotEmpty(uuid)){
			clientToken.setUuid(uuid);
		}
		clientToken.setLoginTime(new Date());
		clientTokenService.save(clientToken);
		
		return client;
	}
	
	@Override
	public Boolean checkIsAccountRegister(String account,String clienttype)
			throws BizException, ErrorException {
		ClientQuery query = new ClientQuery();
		query.setAccount(account);
		query.setClientType(ClientTypeEnum.valueOf(clienttype));
		if(this.getEntityCount(query)>0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public void certificateIdentify(String phone, String verifycode,String clientId, 
			String identifyName,String identify, String address,
			boolean gender,String nation,String birth,String profession,
			String identifyImgFile1, String identifyImgFile2,String identifyImgFile3) throws BizException, ErrorException {
		Client client = this.getById(clientId);
		if(client == null){
			throw new BizException("用户不存在!");
		}
		if(StringUtils.isEmpty(phone)){
			throw new BizException("非法参数,手机号能为空!");
		}
		if(StringUtils.isEmpty(verifycode)){
			throw new BizException("非法参数,验证码不能为空!");
		}
		Boolean isOK = smsVerifyRecordService.verifySmsCode(phone, verifycode);
		if(!isOK){
			throw new BizException("亲,您输入的验证码不正确!");
		}
		client.setIdentify(identify);
		client.setIdentifyName(identifyName);
		
		client.setIdentifyImgFile1(identifyImgFile1);
		client.setIdentifyImgFile2(identifyImgFile2);
		client.setIdentifyImgFile3(identifyImgFile3);
		client.setGender(gender);
		client.setNation(nation);
		client.setProfession(profession);
		client.setBirth(birth);
		client.setAddress(address);
		
		if(StringUtils.isNotEmpty(client.getThirdPartyId())){
			client.setTel(phone);
		}
		//修改用户认证状态
		//client.setClientState(ClientStateEnum.Certificated);
		client.setAuditInfo(CertificateStateEnum.WaitAudit);
		this.save(client);
		
		//设置该用户的权限组
		String authorityGroupId = clientAuthorityGroupService.setClientAuthorityGroup(clientId, AuthorityGroupEnum.Certificated);
		//3.为认证用户分配可以签署协议书的操作权限
		authorityGroupOperationService.setClientAuthorityGroupOperation(clientId, authorityGroupId, "SignProtocol");
	}
	
	@Override
	public void auditCertificateIdentify(String clientid, String oper)
			throws BizException, ErrorException {
		Client client = this.getById(clientid);
		if(client == null){
			throw new BizException("用户不存在!");
		}
		if(client.getAuditInfo()==CertificateStateEnum.Pass){
			throw new BizException("已经审核通过!");
		}
		if(client.getAuditInfo()!=CertificateStateEnum.WaitAudit){
			throw new BizException("无法通过审核!");
		}
		//进行推送
		//进行推送处理告知审核结果 
		Map<String,Object> pushParams = Maps.newHashMap();
		pushParams.put("receiveClientId",clientid);
		pushParams.put("apnsCount", 1);
		String templateid = "";
		JSONArray  paramsarry = new JSONArray();
		JSONArray  phonearry = new JSONArray();
		phonearry.put(client.getTel());
		
		if(oper.equals("0")){//0表示通过
			client.setClientState(ClientStateEnum.Certificated);
			client.setAuditInfo(CertificateStateEnum.Pass);
			String clientstate = client.getClientState().toString();
			//2.设置该用户的权限组
			String authorityGroupId = "";
			if(clientstate.equals(ClientStateEnum.Certificated.toString())){
				authorityGroupId = clientAuthorityGroupService.setClientAuthorityGroup(clientid, AuthorityGroupEnum.Certificated);
				authorityGroupOperationService.setClientAuthorityGroupOperation(clientid, authorityGroupId, "SignProtocol");
			}
			
			pushParams.put("pushAlertMessage", "亲!您的实名认证信息已经审核通过!");
			
			templateid = "3050940";
	        
			
		}else{
			pushParams.put("pushAlertMessage", "您提交的实名未通过，请重新提交!");
			templateid = "3064968";
			client.setAuditInfo(CertificateStateEnum.NotPass);
		}
		PushMessageDisposeFactory.bulidPush(PushTypeEnum.Certificate, pushParams);
		smsVerifyRecordService.sendSmsMessage(phonearry.toString(), SmsBizModelEnum.sms, templateid, paramsarry.toString());
		this.save(client);
	}
	
	@Override
	public List<Client> getClientListByMediationAgencyId(String agencyid)
			throws BizException, ErrorException {
		ClientQuery query = new ClientQuery();
		query.setMediationAgencyId(agencyid);
		return this.getEntities(query);
	}

	@Override
	public void changeNickName(String clientId, String newNickName)
			throws BizException, ErrorException {
		//修改昵称
		Client client = this.getById(clientId);

		client.setNickName(newNickName);

		this.save(client);
		
	}
	
	@Override
	public void createManagerClientByType(String account, String identifyName, String identify, String tel,
			String clienttype, String clientstate, String password,String mediationAgencyId,String skill,String description,String headImgFile) throws BizException, ErrorException {
		
		if(StringUtils.isEmpty(account) || StringUtils.isEmpty(identifyName)
				|| StringUtils.isEmpty(tel)|| StringUtils.isEmpty(clienttype)
				|| StringUtils.isEmpty(clientstate)|| StringUtils.isEmpty(password) || StringUtils.isEmpty(skill) || StringUtils.isEmpty(description)){
			throw new BizException("参数不能为空,请检查!");
		}
		MediationAgency agency = null;
		if(!StringUtils.isEmpty(mediationAgencyId)){
			agency = mediationAgencyService.getById(mediationAgencyId);
			if(agency == null){
				throw new BizException("机构不存在!");
			}
			if(clientstate.equals(ClientStateEnum.MediationAgency.toString()) && agency.getManagerClientId() != null){
				MediationAgency mediationAgency = mediationAgencyService.getById(agency.getManagerClientId());
				if(mediationAgency!=null){
					throw new BizException("该机构已经有管理员!");
				}
			}
		}
		if(clientstate.equals(ClientStateEnum.MediationCenter.toString())){
			ClientQuery query = new ClientQuery();
			query.setClientState(ClientStateEnum.MediationCenter);
			long count = this.getEntityCount(query);
			if(count > 0){
				throw new BizException("中心员已经存在，有且只能有一个中心管理员!");
			}
		}
		//1.用户注册
		Client clientTemp = getClientByAccountAndType(clienttype,account);
		if (clientTemp!=null) {
			throw new BizException("账号已被注!");
		}
		Client client = new Client();
		client.setAccount(account);
		client.setNickName(account);
		client.setPasswd(MD5Util.MD5(password));
		
		client.setClientState(ClientStateEnum.valueOf(clientstate));
		client.setClientType(ClientTypeEnum.valueOf(clienttype));
		client.setIdentify(identify);
		client.setIdentifyName(identifyName);
		client.setTel(tel);
		client.setSkill(skill);
		client.setDescription(description);
		client.setHeadImgFile(headImgFile);
		if(StringUtils.isNotEmpty(mediationAgencyId)){
			if(clientstate.equals(ClientStateEnum.MediationCenter.toString())){
				throw new BizException("中心管理员不能属于任何机构!");
			}
			client.setMediationAgencyId(mediationAgencyId);
			client.setAgencyName(agency.getAgencyName());
		}
		String clientid = this.save(client);
		if(agency != null && clientstate.equals(ClientStateEnum.MediationAgency.toString())){
			agency.setManagerClientId(clientid);
			mediationAgencyService.save(agency);
		}

		//2.设置该用户的权限组
		String authorityGroupId = "";
		if(clientstate.equals(ClientStateEnum.MediationCenter.toString())){
			authorityGroupId = clientAuthorityGroupService.setClientAuthorityGroup(clientid, AuthorityGroupEnum.MediationCenter);
		}else if(clientstate.equals(ClientStateEnum.MediationAgency.toString())){
			authorityGroupId = clientAuthorityGroupService.setClientAuthorityGroup(clientid, AuthorityGroupEnum.MediationAgency);
		}else if(clientstate.equals(ClientStateEnum.Mediator.toString())){
			authorityGroupId = clientAuthorityGroupService.setClientAuthorityGroup(clientid, AuthorityGroupEnum.Mediator);
		}

		//3.为普通注册用户分配操作权限
		if(clientstate.equals(ClientStateEnum.MediationCenter.toString())){
			authorityGroupOperationService.setClientAuthorityGroupOperation(clientid, authorityGroupId, "Accpet");
			authorityGroupOperationService.setClientAuthorityGroupOperation(clientid, authorityGroupId, "Allocate");
			authorityGroupOperationService.setClientAuthorityGroupOperation(clientid, authorityGroupId, "RefuseApply");
		}else if(clientstate.equals(ClientStateEnum.MediationAgency.toString())){
			authorityGroupOperationService.setClientAuthorityGroupOperation(clientid, authorityGroupId, "Accpet");
			authorityGroupOperationService.setClientAuthorityGroupOperation(clientid, authorityGroupId, "Allocate");
			authorityGroupOperationService.setClientAuthorityGroupOperation(clientid, authorityGroupId, "OpenMission");
		}else if(clientstate.equals(ClientStateEnum.Mediator.toString())){
			authorityGroupOperationService.setClientAuthorityGroupOperation(clientid, authorityGroupId, "Accpet");
			authorityGroupOperationService.setClientAuthorityGroupOperation(clientid, authorityGroupId, "OpenMission");
		}
		
	}
	
	@Override
	public boolean checkClientOperationAuthority(String clientid,String operation) throws BizException, ErrorException {
		Client client = this.getById(clientid);
		if(client == null){
			throw new BizException("用户不存在!");
		}
		boolean haveAuthority = authorityGroupOperationService.clientHasAuthorityOrNot(clientid, operation);
		return haveAuthority;
	}
	
	@Override
	public Map<String, Object> statisticsClientRes() throws BizException,
			ErrorException {
		
		return dao.statisticsClientRes();
	}
	
	@Override
	public String isLoginSessionOnline(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String clientId = (String) session.getAttribute("clientId");
		if(StringUtils.isEmpty(clientId)){
			return "";
		}
		Client client = this.getById(clientId);
		String requestSessionId = session.getId();
		if(requestSessionId.equals(client.getSessionId()==null?"":client.getSessionId())){
			return clientId;
		}else{
			return "";
		}
	}
	
}