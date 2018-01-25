package com.szit.arbitrate.mediation.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.MD5Util;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseServiceImpl;
import com.hsit.common.utils.BeanCopyUtils;
import com.szit.arbitrate.client.entity.AuthorityGroup;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.ClientAuthorityGroup;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;
import com.szit.arbitrate.client.service.AuthorityGroupOperationService;
import com.szit.arbitrate.client.service.AuthorityGroupService;
import com.szit.arbitrate.client.service.ClientAuthorityGroupService;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.dao.MediatorApplyDao;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.entity.MediatorApply;
import com.szit.arbitrate.mediation.entity.enumvo.ApplyStateEnum;
import com.szit.arbitrate.mediation.entity.query.MediatorApplyQuery;
import com.szit.arbitrate.mediation.service.MediationAgencyService;
import com.szit.arbitrate.mediation.service.MediatorApplyService;
import com.szit.arbitrate.pushcentre.factory.PushMessageDisposeFactory;
import com.szit.arbitrate.pushcentre.service.PushCentreService;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: MediatorApplyServiceImpl
* @Description:调解申请业务实现类
* @author Administrator
* @date 2017年3月23日 上午11:41:26
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class MediatorApplyServiceImpl extends AppBaseServiceImpl<MediatorApply, MediatorApplyQuery>
	implements MediatorApplyService{
	
	@Autowired
	private MediatorApplyDao dao;
	@Autowired
	private ClientService clientService;
	@Autowired
	private MediationAgencyService mediationAgencyService;
	@Autowired
	private PushCentreService pushCentreService;
	@Autowired
	private AuthorityGroupService authorityGroupService;
	@Autowired
	private ClientAuthorityGroupService clientAuthorityGroupService;
	@Autowired
	private AuthorityGroupOperationService authorityGroupOperationService;
	
	public MediatorApplyDao getDao() {
		return dao;
	}

	public void setDao(MediatorApplyDao dao) {
		this.dao = dao;
	}

	@Override
	public MediatorApply saveApplyMediator(String clientId ,String applyReason) throws BizException,ErrorException{
		
		//获取申请人信息
		Client client = clientService.getById(clientId);
		if(client==null){
			throw new BizException("用户不存在!");
		}
		if(client.getClientState() != ClientStateEnum.Certificated){
			throw new BizException("只有实名认证用户才能申请成为调解员!");
		}
		if(client.getMediationAgencyId() != null){
			throw new BizException("已经是调解员!");
		}
		
		if(StringUtils.isEmpty(applyReason)){
			throw new BizException("请输入申请理由!");
		}
		
		MediatorApplyQuery mediatorApplyQuery = new MediatorApplyQuery();
		mediatorApplyQuery.setApplyClientId(clientId);
		mediatorApplyQuery.addOrder("applyTime", true);
		mediatorApplyQuery.setApplyState(ApplyStateEnum.Init);
		MediatorApply mediatorApply = this.getEntity(mediatorApplyQuery);
		//已申请
		if(mediatorApply != null){
			return mediatorApply;
		}
			
		mediatorApply = new MediatorApply();
		mediatorApply.setApplyClientId(clientId);
		mediatorApply.setApplyReason(applyReason);
		mediatorApply.setApplyTime(new Date());
		mediatorApply.setApplyState(ApplyStateEnum.Init);//状态申请中
		this.save(mediatorApply);
		
		return mediatorApply;
	}
	
	@Override
	public MediatorApply getMediatorApply(String clientid) throws BizException,
			ErrorException {
		//获取申请人信息
		Client client = clientService.getById(clientid);
		if(client==null){
			throw new BizException("用户不存在!");
		}
		MediatorApplyQuery mediatorApplyQuery = new MediatorApplyQuery();
		mediatorApplyQuery.setApplyClientId(clientid);
		mediatorApplyQuery.addOrder("applyTime", true);
		MediatorApply mediatorApply = this.getEntity(mediatorApplyQuery);
		return mediatorApply;
	}

	@Override
	public JSONObject auditMediatorApply(String id, String applyClientId,String auditState, String mediationAgencyId) {
		JSONObject json = new JSONObject();
		try {
		//1.改变申请状态
		MediatorApply mediatorApply = this.getById(id);
		if(mediatorApply==null){
			throw new BizException("无效操作");
		}
		mediatorApply.setApplyState(ApplyStateEnum.valueOf(auditState));
		mediatorApply.setAuditTime(new Date());
		this.save(mediatorApply);
		
		if(mediatorApply.getApplyState().equals(ApplyStateEnum.NotPass)){
			json.put("success", true);
			json.put("message", "创建成功!");
			json.put("data", "");
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("receiveClientId", applyClientId);
			map.put("pushAlertMessage", "申请调解员失败");
			PushMessageDisposeFactory.bulidPush(PushTypeEnum.MediatorApply, map);
			return json;
		}
		
		//2.分配调解机构
		
		Client client=clientService.getById(applyClientId);
		if(client==null){
			throw new BizException("用户不存在");
		}
		boolean isRegister = clientService.checkIsAccountRegister(client.getAccount(), "Mediator");
		if(isRegister){
			throw new BizException("该手机号已经被注册调解员!");
		}
		
		MediationAgency mediationAgency = mediationAgencyService.getById(mediationAgencyId);
		if(mediationAgency==null){
			throw new BizException("不存在的机构!");
		}
		
		//新添加一条client 用户类型为调解员
		try {
			Client newClient = (Client) BeanCopyUtils.beanUtilsCopy(client, Client.class, new String[]{"id"});
			newClient.setClientType(ClientTypeEnum.Mediator);
			newClient.setAccount(client.getTel());
			newClient.setPasswd(MD5Util.MD5("123456"));
			newClient.setMediationAgencyId(mediationAgencyId);
			newClient.setAgencyName(mediationAgency.getAgencyName());
			String newClientId = clientService.save(newClient);
			//加入权限组
			AuthorityGroup authorityGroup = authorityGroupService.getAuthorityGroup(AuthorityGroupEnum.Mediator);
			ClientAuthorityGroup clientAuthorityGroup = new ClientAuthorityGroup();
			clientAuthorityGroup.setClientId(newClientId);
			clientAuthorityGroup.setClientName(newClient.getIdentifyName());
			clientAuthorityGroup.setAuthorityGroupId(authorityGroup.getId());
			clientAuthorityGroup.setAuthorityGroupName(AuthorityGroupEnum.Mediator);
			clientAuthorityGroupService.save(clientAuthorityGroup);
			
			String authorityGroupId = authorityGroup.getId();
			authorityGroupOperationService.setClientAuthorityGroupOperation(newClientId, authorityGroupId, "Accpet");
			authorityGroupOperationService.setClientAuthorityGroupOperation(newClientId, authorityGroupId, "OpenMission");
			
			json.put("success", true);
			json.put("message", "创建成功!");
			json.put("data", "");
			//發送通知消息
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("receiveClientId", applyClientId);
			map.put("pushAlertMessage", "您已成为调解员");
			PushMessageDisposeFactory.bulidPush(PushTypeEnum.MediatorApply, map);
			
		} catch (Exception e) {
			throw new BizException("复制异常错误!");
		}
		
	} catch (BizException biz) {
		json.put("success", false);
		json.put("message", biz.getMessage());
		json.put("data", "");
	} catch (ErrorException error) {
		json.put("success", false);
		json.put("message", "异常错误,请联系系统管理员!");
		json.put("data", "");
	}
	return json;
}	
}