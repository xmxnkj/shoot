package com.szit.arbitrate.mediation.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hsit.common.MD5Util;
import com.hsit.common.dao.Dao;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseServiceImpl;
import com.hsit.common.utils.CommonUtil;
import com.hsit.common.utils.DateUtils;
import com.hsit.common.utils.FileToZip;
import com.szit.arbitrate.api.mediation.bo.in.TClientInBo;
import com.szit.arbitrate.basisset.entity.SysParameterTable;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;
import com.szit.arbitrate.basisset.service.SysParameterTableService;
import com.szit.arbitrate.chat.entity.ChatRoom;
import com.szit.arbitrate.chat.entity.ChatRoomClient;
import com.szit.arbitrate.chat.entity.query.ChatRoomClientQuery;
import com.szit.arbitrate.chat.service.ChatRoomClientService;
import com.szit.arbitrate.chat.service.ChatRoomService;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.ClientNotOnlineNotify;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.enumvo.CertificateStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientLoginTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.SmsBizModelEnum;
import com.szit.arbitrate.client.entity.query.ClientQuery;
import com.szit.arbitrate.client.entity.query.TempClientQuery;
import com.szit.arbitrate.client.service.AuthorityGroupOperationService;
import com.szit.arbitrate.client.service.AuthorityGroupService;
import com.szit.arbitrate.client.service.ClientAuthorityGroupService;
import com.szit.arbitrate.client.service.ClientNotOnlineNotifyService;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.SmsVerifyRecordService;
import com.szit.arbitrate.client.service.TempClientService;
import com.szit.arbitrate.mediation.dao.MediationCaseDao;
import com.szit.arbitrate.mediation.entity.DifferentSubjects;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.entity.MediationCase;
import com.szit.arbitrate.mediation.entity.dto.MediationCaseExcelDto;
import com.szit.arbitrate.mediation.entity.dto.MediationCaseInfoDto;
import com.szit.arbitrate.mediation.entity.enumvo.CaseAllocationStateEnum;
import com.szit.arbitrate.mediation.entity.enumvo.CaseStateEnum;
import com.szit.arbitrate.mediation.entity.enumvo.ProtocolStateEnum;
import com.szit.arbitrate.mediation.entity.query.MediationAgencyQuery;
import com.szit.arbitrate.mediation.entity.query.MediationCaseQuery;
import com.szit.arbitrate.mediation.service.MediationAgencyService;
import com.szit.arbitrate.mediation.service.MediationCaseService;
import com.szit.arbitrate.mediation.service.MediationProtocolService;
import com.szit.arbitrate.mediation.service.MediationRecordService;
import com.szit.arbitrate.mediation.utils.ComparatorMediationCaseInfoDto;
import com.szit.arbitrate.mediation.utils.FileMediationToDoc;
import com.szit.arbitrate.mediation.utils.HanyuPinyinHelper;
import com.szit.arbitrate.pushcentre.factory.PushMessageDisposeFactory;
import com.szit.arbitrate.pushcentre.service.PushCentreService;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: MediationCaseServiceImpl
* @Description:调解案件业务接口实现类
* @author yuyb
* @date 2017年3月23日 上午11:30:24
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class MediationCaseServiceImpl extends AppBaseServiceImpl<MediationCase, MediationCaseQuery> implements MediationCaseService{

	@Autowired
	private SmsVerifyRecordService smsVerifyRecordService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private AuthorityGroupOperationService authorityGroupOperationService;
	@Autowired
	private MediationAgencyService mediationAgencyService;
	@Autowired
	private ClientAuthorityGroupService clientAuthorityGroupService;
	@Autowired
	private TempClientService tempClientService;
	@Autowired
	private MediationCaseDao mediationCaseDao;
	@Autowired
	private AuthorityGroupService authorityGroupService;
	@Autowired
	private ChatRoomService chatRoomService;
	@Autowired
	private ChatRoomClientService chatRoomClientService;
	@Autowired
	private MediationProtocolService mediationProtocolService;
	@Autowired
	private MediationRecordService mediationRecordService;
	@Autowired
	private PushCentreService pushCentreService;
	@Autowired
	private ClientNotOnlineNotifyService clientNotOnlineNotifyService;
	@Autowired
	private SysParameterTableService sysParameterTableService;

	@Override
	protected Dao<MediationCase, MediationCaseQuery> getDao() {
		return mediationCaseDao;
	}


	@Override
	public void applyMediation(String caseid,String clientid, String mediatetype, String casetype, 
			String caseexplain, String mediatorInfo, List<TClientInBo> jsonlist)throws BizException, ErrorException {
		
		if(StringUtils.isEmpty(mediatetype) || StringUtils.isEmpty(casetype) || StringUtils.isEmpty(caseexplain)){
			throw new BizException("参数不能为空!");
		}
		
		//2.获得用户对象
		Client client = clientService.getById(clientid);
		if(client == null){
			throw new BizException("用户不存在!");
		}
		SysParameterTable sysparametertable = 
				sysParameterTableService.getSysParameterTable(ParameterTypeEnum.certificateswitch, "applyMediationSwitch");
		//开启认证验证
		if(sysparametertable != null && sysparametertable.getParameterinitvla().equals("1")){
			if(!client.getAuditInfo().equals(CertificateStateEnum.Pass)){
				throw new BizException("你没有该操作权限,请先实名认证!");
			}
		}
		//3.新增调解案件
		MediationCase entity = this.getById(caseid);
		if(entity == null){
			entity = new MediationCase();
		}
		entity.setApplyClientId(clientid);
		entity.setApplyClientName(client.getIdentifyName());
		entity.setCaseType(casetype);
		entity.setCaseExplain(caseexplain);
		if(StringUtils.isNotEmpty(mediatorInfo)){//空的时候表示没有指定调解员
			entity.setMediatorInfo(mediatorInfo);
		}
		if(StringUtils.isNotEmpty(entity.getRefuseReason())){
			entity.setRefuseReason(null);
		}
		entity.setApplyTime(new Date());
		entity.setMediateType(mediatetype);
		entity.setCaseState(CaseStateEnum.Init);
		entity.setAllocationState(CaseAllocationStateEnum.MediationCenterNotAccepted);
		String caseId = this.saveSimple(entity);
		
		//4.保存案件当事人信息
		if(jsonlist != null && jsonlist.size() > 0){
			for(int i = 0; i < jsonlist.size(); i ++){
				TClientInBo tClientInBo = jsonlist.get(i);
				TempClient tempClient = tempClientService.getById(tClientInBo.getId());
				if(tempClient == null){
					tempClient = new TempClient();
				}
				tempClient.setCaseId(caseId);
				tempClient.setIdentifyName(tClientInBo.getName());
				tempClient.setTel(tClientInBo.getTel());
				tempClient.setAddress(tClientInBo.getAddress());
				tempClient.setSign(ProtocolStateEnum.Init);
				tempClientService.save(tempClient);
			}
		}
		
		//推送
		String pushClientId = "1";
		String alertMessage = "有一个新的用户调解申请,请及时处理";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pushAlertMessage", alertMessage);
		map.put("pushClientId", pushClientId);
		map.put("caseId", caseId);
		PushMessageDisposeFactory.bulidPush(PushTypeEnum.MediateState, map);
		
	}

	
	@Override
	public TempClient addTempClient(String caseId, String name,String tel, String address, 
			String identify, boolean ispartb) {
		MediationCase mediationCase = this.getById(caseId);
		if(mediationCase == null){
			throw new BizException("案件不存在!");
		}
		if(mediationCase.getApplyClient().getTel().equals(tel)){
			throw new BizException("申述人电话不能为自己!");
		}
		TempClientQuery query = new TempClientQuery();
		query.setTel(tel);
		query.setCaseId(caseId);
		TempClient tempClient = tempClientService.getEntity(query);
		if(tempClient == null){
			tempClient = new TempClient();
		}
		tempClient.setCaseId(caseId);
		tempClient.setIdentifyName(name);
		tempClient.setIdentify(identify);
		tempClient.setSign(ProtocolStateEnum.Init);
		tempClient.setPartB(ispartb);
		if(StringUtils.isNotEmpty(tel)){
			tempClient.setTel(tel);
		}
		if(StringUtils.isNotEmpty(address)){
			tempClient.setAddress(address);
		}
		tempClientService.save(tempClient);
		return tempClient;
	}
	
	@Override
	public void deleteTempClient(String id) throws BizException, ErrorException {
		TempClient tempClient = tempClientService.getById(id);
		if(tempClient == null){
			throw new BizException("当事人不存在!");
		}
		tempClientService.deleteById(id);
		if(StringUtils.isNotEmpty(tempClient.getClientId())){
			ChatRoomClientQuery chatRoomClientQuery = new ChatRoomClientQuery();
			chatRoomClientQuery.setClientId(tempClient.getClientId());
			chatRoomClientQuery.setCaseid(tempClient.getCaseId());
			ChatRoomClient chatRoomClient = chatRoomClientService.getEntity(chatRoomClientQuery);
			if(chatRoomClient != null){
				chatRoomClientService.deleteById(chatRoomClient.getId());
			}
		}
	}

	/** 
	 * 案件受理用
	 * caseid 案件id
	 * mediatorid 受理人id
	 */
	@Override
	public synchronized void acceptMediationCase(String caseid, String mediatorid) {
		
		//1.查询用户权限，确定该用户是否能发起申请
		Client client = clientService.getById(mediatorid);
		if(client == null){
			throw new BizException("用户不存在!");
		}
		
		if(!client.getClientType().equals(ClientTypeEnum.Mediator)){
			throw new BizException("你没有该操作权限，无法受理!");
		}
		//2.受理案件
		MediationCase entity = this.getById(caseid);
		if(entity == null){
			throw new BizException("案件不存在!");
		}
		//2.1判断用户是否属于调解中心管理员
		boolean authoritygroup = clientAuthorityGroupService.isClientInAuthorityGroupOrNot(mediatorid, "MediationCenter");
		//如果受理者属于中心管理员权限组，且案件分配状态属于调解中心未受理，说明是中心管理员在受理案件，案件状态改为分配中，分配状态到MediationCenter
		if(authoritygroup && entity.getAllocationState().equals(CaseAllocationStateEnum.MediationCenterNotAccepted)){
			entity.setCaseState(CaseStateEnum.Allocation);
			entity.setAllocationState(CaseAllocationStateEnum.MediationCenterAccepted);
		}
		authoritygroup = clientAuthorityGroupService.isClientInAuthorityGroupOrNot(mediatorid, "MediationAgency");
		//2.2如果受理者属于机构管理员权限组，且案件分配状态属于调解中心已分配，说明是机构管理员在受理案件，案件状态还是分配中，分配状态到AgencyManager
		if(authoritygroup && entity.getAllocationState().equals(CaseAllocationStateEnum.MediationCenterAllocated)){
			entity.setCaseState(CaseStateEnum.Allocation);
			entity.setAllocationState(CaseAllocationStateEnum.AgencyManagerAccepted);
		}
		authoritygroup = clientAuthorityGroupService.isClientInAuthorityGroupOrNot(mediatorid, "Mediator");
		//2.3如果受理者属于普通调解员权限组，且案件分配状态属于调解机构管理员已分配，说明是普通调解员在受理案件，案件状态变成已受理调节中，分配状态到Mediator
		if(authoritygroup && entity.getAllocationState().equals(CaseAllocationStateEnum.AgencyManagerAllocated)){
			
			entity.setCaseState(CaseStateEnum.Mediating);
			entity.setAllocationState(CaseAllocationStateEnum.MediatorAccepted);
			entity.setMediatorClientId(mediatorid);
			MediationAgency agency;
			try {
				agency = mediationAgencyService.getDao().getMediationAgency(mediatorid);
				if(agency != null){
					entity.setMediationAgencyId(agency.getId());
					entity.setAgencyBelongsTo(agency.getBelongsTo());
					entity.setAgencyClassify(agency.getAgencyClassify());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//推送
			String pushClientId = entity.getApplyClientId();
			String alertMessage = "您于"+entity.getApplyTime()+"申请的案件,已被受理,请前往登录app进行调解";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pushAlertMessage", alertMessage);
			map.put("pushClientId", pushClientId);
			map.put("caseId", caseid);
			PushMessageDisposeFactory.bulidPush(PushTypeEnum.MediateState, map);
		}
		this.saveSimple(entity);
	}
	
	/**
	 * 分配案件
	 * clientid 分配人id
	 * caseid 案件id
	 * mediatorid 受理人id
	 */
	@Override
	public synchronized void allocateMediationCase(String clientid,String caseid, String mediatorid)
			throws BizException, ErrorException {
		//1.查询用户权限，确定该用户是否能发起申请
		Client client = clientService.getById(clientid);
		if(client == null){
			throw new BizException("用户不存在!");
		}
		boolean haveAuthority = authorityGroupOperationService.clientHasAuthorityOrNot(clientid, "Allocate");
		if(!haveAuthority){
			throw new BizException("你没有该操作权限!");
		}
		
		//2.根据用户id确认用户属于哪个权限组，确定是调解中心分配还是调解机构管理员在分配，开始案件分配
		MediationCase entity = this.getById(caseid);
		if(entity == null){
			throw new BizException("案件不存在!");
		}
		String pushClientId = "";
		//2.1判断用户是否属于调解中心管理员
		//如果用户是调解中心管理员，且案件分类状态为调解中心已受理，则可以分配，分配对象为调解机构
		if(client.getClientState().equals(ClientStateEnum.MediationCenter) 
				&& entity.getAllocationState().equals(CaseAllocationStateEnum.MediationCenterAccepted)){
			MediationAgency agency = mediationAgencyService.getById(mediatorid);
			entity.setAllocationState(CaseAllocationStateEnum.MediationCenterAllocated);
			
			ClientQuery cQ = new ClientQuery();
			cQ.setMediationAgencyId(agency.getId());
			cQ.setClientState(ClientStateEnum.MediationAgency);
			Client c = clientService.getEntity(cQ);
			entity.setMediatorClientId(c.getId());
			entity.setMediationAgencyId(agency.getId());
			entity.setCaseState(CaseStateEnum.Allocation);//分配中
			pushClientId = agency.getManagerClientId();
		}
		//2.2如果用户是调解机构管理员，且案件分类状态为调解机构已受理，则可以分配，分配对象为普通调解员
		if(client.getClientState().equals(ClientStateEnum.MediationAgency) 
				&& entity.getAllocationState().equals(CaseAllocationStateEnum.AgencyManagerAccepted)){
			if(clientid.equals(mediatorid)){//如果机构管理员分配给自己，案件直接进入进行中
				entity.setCaseState(CaseStateEnum.Mediating);
				entity.setAllocationState(CaseAllocationStateEnum.MediatorAccepted);
				MediationAgency agency;
				try {
					agency = mediationAgencyService.getDao().getMediationAgency(mediatorid);
					if(agency != null){
						entity.setMediationAgencyId(agency.getId());
						entity.setAgencyBelongsTo(agency.getBelongsTo());
						entity.setAgencyClassify(agency.getAgencyClassify());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else{
				entity.setAllocationState(CaseAllocationStateEnum.AgencyManagerAllocated);
			}
			entity.setMediatorClientId(mediatorid);
			pushClientId = mediatorid;
		}
		if(StringUtils.isNotEmpty(entity.getRefuseReason())){
			entity.setRefuseReason(null);
		}
		this.save(entity);
		String alertMessage = "您有一个新的被指派调解案件,请及时处理!";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pushAlertMessage", alertMessage);
		map.put("pushClientId", pushClientId);
		map.put("caseId", caseid);
		PushMessageDisposeFactory.bulidPush(PushTypeEnum.MediateState, map);
		
	}
	
	@Override
	public void refuseClientMediationApplication(String clientid, String caseid, String refusereason)
			throws BizException, ErrorException {
		//1.查询用户权限，确定该用户是否能发起
		Client client = clientService.getById(clientid);
		if(client == null){
			throw new BizException("用户不存在!");
		}
		boolean haveAuthority = authorityGroupOperationService.clientHasAuthorityOrNot(clientid, "RefuseApply");
		if(!haveAuthority){
			throw new BizException("你没有该操作权限!");
		}
		
		//2.
		MediationCase entity = this.getById(caseid);
		if(entity == null){
			throw new BizException("案件不存在!");
		}
		entity.setRefuseReason(refusereason);
		entity.setAllocationState(CaseAllocationStateEnum.Refuse);
		entity.setCaseState(CaseStateEnum.Refused);
		this.save(entity);
		
		//3.推送
		String pushClientId = entity.getApplyClientId();
		String alertMessage = "系统拒绝了您申请的调解："+entity.getCaseExplain()+"，拒绝理由："+refusereason;
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pushAlertMessage", alertMessage);
		map.put("pushClientId", pushClientId);
		map.put("caseId", caseid);
		PushMessageDisposeFactory.bulidPush(PushTypeEnum.RefuseMediation, map);
		
	}
	
	@Override
	public void backToUpperLevel(String clientid, String caseid, String reason)
			throws BizException, ErrorException {
		Client client = clientService.getById(clientid);
		if(client == null){
			throw new BizException("用户不存在!");
		}
		MediationCase entity = this.getById(caseid);
		if(entity == null){
			throw new BizException("案件不存在!");
		}
		String pushClientId = "";
		//1.如果用户是机构管理员，则案件退回到调解中心管理员
		if(client.getClientState().equals(ClientStateEnum.MediationAgency)
				&& entity.getCaseState().equals(CaseStateEnum.Allocation)){
			//案件分配状态变回：调解中心已受理
			entity.setAllocationState(CaseAllocationStateEnum.MediationCenterAccepted);
			//填写退回理由
			if(StringUtils.isNotEmpty(reason)){
				entity.setRefuseReason(reason);
			}
			//修改状态,退给调解中心管理员
			entity.setMediatorClientId("1");
			pushClientId = "1";
		}
		//2.如果用户是普通调解员，则案件退回到调解机构管理员
		if(client.getClientState().equals(ClientStateEnum.Mediator)
				&& entity.getCaseState().equals(CaseStateEnum.Allocation)){
			//案件分配状态变回：调解中心已受理
			entity.setAllocationState(CaseAllocationStateEnum.AgencyManagerAccepted);
			//填写退回理由
			if(StringUtils.isNotEmpty(reason)){
				entity.setRefuseReason(reason);
			}
			
			//退给调解机构管理员
			MediationAgency mediationAgency = mediationAgencyService.getById(client.getMediationAgencyId());
			if(mediationAgency==null){
				throw new BizException("机构不存在");
			}
			entity.setMediatorClientId(mediationAgency.getManagerClientId());
			pushClientId = mediationAgency.getManagerClientId();
		}
		
		this.save(entity);
		
		String alertMessage = "您指派给!"+client.getIdentifyName()+"的调解案件被退回了，请重新指派!";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pushAlertMessage", alertMessage);
		map.put("pushClientId", pushClientId);
		map.put("caseId", caseid);
		PushMessageDisposeFactory.bulidPush(PushTypeEnum.MediateState, map);
		
	}
	
	@Override
	public List<MediationCase> getMediationCaseListByClientId(String clientid, String casestate)
			throws BizException, ErrorException {
		
		MediationCaseQuery query = new MediationCaseQuery();
		query.setMediatorClientId(clientid);
		query.setCaseState(CaseStateEnum.valueOf(casestate));
		query.addOrder("applyTime", true);
		List<MediationCase> list = this.getEntities(query);
		return list;
		
	}
	
	@Override
	public List<MediationCaseInfoDto> searchMediationCaseByName(
			String clientid, String casename) throws BizException,
			ErrorException {
		if(StringUtils.isEmpty(clientid)){
			throw new BizException("用户id参数不能空!");
		}
		List<MediationCaseInfoDto> list = mediationCaseDao.searchMediationCaseByName(clientid, casename);
		return list;
	}
	
	@Override
	public List<MediationCaseInfoDto> getListByClientIdAndCaseType(String clientid,
			Integer tabindex) throws BizException, ErrorException {
		
		List<MediationCaseInfoDto> list = null;
		Client client = clientService.getById(clientid);
		if(client == null){
			throw new BizException("用户不存在!");
		}
		ClientStateEnum clientStateEnum = client.getClientState();
		MediationAgency mediationAgency = null;
		if(!clientStateEnum.equals(ClientStateEnum.MediationCenter)){
			//获取用户组织机构信息 存在为机构管理员
			try {
				mediationAgency = mediationAgencyService.getDao().getMediationAgency(clientid);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//1.查询[调解管理]列表,根据案件分配状态
		if(tabindex==0){
			list = mediationCaseDao.getMediationCaseListByClientState(clientid, clientStateEnum,mediationAgency == null ? null : mediationAgency.getId());
		}else{
			list = mediationCaseDao.getMediatingCaseListByClientId(clientid,tabindex,clientStateEnum,mediationAgency == null ? null : mediationAgency.getId());
		}
		return list;
		
	}
	
	@Override
	public void completeMediationCase(String caseid, String mediatetype, String casetype, 
			String mediatorid, boolean difficult, Integer people, String caseMoney,
			String protocolForm, String caseSource,Integer differenceSubject) throws BizException,
			ErrorException {
		if(StringUtils.isEmpty(mediatorid) || people == null || caseMoney == null
				|| StringUtils.isEmpty(protocolForm) || StringUtils.isEmpty(caseSource)){
			throw new BizException("参数不能为空!");
		}
		MediationCase entity = this.getById(caseid);
		if(entity == null){
			throw new BizException("案件不存在!");
		}
		if(!entity.getCaseState().equals(CaseStateEnum.WaitSign)){
			throw new BizException("案件状态不对，不能结案!");
		}
		if(!entity.getMediatorClientId().equals(mediatorid)){
			throw new BizException("你不是案件调解员，无权结案!");
		}
		entity.setMediateType(mediatetype);
		entity.setCaseType(casetype);
		entity.setCaseState(CaseStateEnum.Completed);
		entity.setDifficult(difficult);
		entity.setPeople(people);
		entity.setCaseMoney(caseMoney);
		entity.setProtocolForm(protocolForm);
		entity.setCaseSource(caseSource);
		Client client = clientService.getById(mediatorid);
		if(client!=null){
			entity.setMediatorName(client.getIdentifyName());
		}
		entity.setDifferenceSubject(differenceSubject);
		this.save(entity);
	}
	
	
	@Override
	public void giveupMediation(String clientid, String caseid)
			throws BizException, ErrorException {
		//转为线下调解，视为放弃调解，案件状态改为已关闭
		
		MediationCase entity = this.getById(caseid);
		if(entity == null){
			throw new BizException("案件不存在!");
		}
		if(!entity.getApplyClientId().equals(clientid)){
			throw new BizException("不是申请人无权限放弃调解!");
		}
		entity.setCaseState(CaseStateEnum.GiveUp);
		this.save(entity);
	}
	
	@Override
	public synchronized void gatherTempClient(String tempclientid) throws BizException,
			ErrorException {
		TempClient tempClient = tempClientService.getById(tempclientid);
		if(tempClient == null){
			throw new BizException("被申请人不存在!");
		}
		String caseid = tempClient.getCaseId();
		MediationCase entity = this.getById(caseid);
		String agencyName = entity.getMediatorClient().getAgencyName();
		//1.查询该被申请人对应的手机号有没有被注册用户
		ClientQuery clientQuery = new ClientQuery();
		clientQuery.setAccount(tempClient.getTel());
		clientQuery.setClientType(ClientTypeEnum.Normal);
		Client client = clientService.getEntity(clientQuery);
		String clientId = "";
		if(client != null){
			clientId = client.getId();
			client.setTel(tempClient.getTel());
			clientService.save(client);
		}else{
			//2.创建新用户
			client = new Client();
			client.setAccount(tempClient.getTel());
			client.setPasswd(MD5Util.MD5("123456"));
			client.setTel(tempClient.getTel());
			client.setIdentifyName(tempClient.getIdentifyName());
			client.setIdentify(tempClient.getIdentify());
			client.setAddress(tempClient.getAddress());
			client.setClientType(ClientTypeEnum.Normal);
			client.setClientState(ClientStateEnum.Normal);
			client.setLoginType(ClientLoginTypeEnum.AutoCreate);
			clientId = clientService.save(client);
		}
		//3.关联用户id
		tempClient.setClientId(clientId);
		tempClient.setGather(true);
		tempClient.setIdentifyName(client.getIdentifyName());
		tempClientService.save(tempClient);
		//4.如果案件聊天室已经开启，则将召集的用户加入聊天室
		ChatRoom chatRoom = chatRoomService.getChatRoomByCaseId(tempClient.getCaseId());
		if(chatRoom != null){
			ChatRoomClientQuery chatRoomClientQuery = new ChatRoomClientQuery();
			chatRoomClientQuery.setCaseid(tempClient.getCaseId());
			chatRoomClientQuery.setChatRoomId(chatRoom.getId());
			chatRoomClientQuery.setClientId(clientId);
			ChatRoomClient chatRoomClient = chatRoomClientService.getEntity(chatRoomClientQuery);
			if(chatRoomClient == null){
				chatRoomClient = new ChatRoomClient();
				chatRoomClient.setCaseid(tempClient.getCaseId());
				chatRoomClient.setChatRoomId(chatRoom.getId());
				chatRoomClient.setClientId(clientId);
				chatRoomClient.setClientName(client.getIdentifyName());
				chatRoomClient.setClientImage(client.getHeadImgFile());
				if(tempClient.isPartB()){
					chatRoomClient.setClientDesc("被申述人");
				}else{
					chatRoomClient.setClientDesc("第三方");
				}
				chatRoomClientService.save(chatRoomClient);
			}
		}
		
		//5.加入待通知列表
		ClientNotOnlineNotify clientNotOnlineNotify = 
				clientNotOnlineNotifyService.getClientNotOnlineNotifyByClientCase(clientId, caseid);
		if(clientNotOnlineNotify == null){
			clientNotOnlineNotify = new ClientNotOnlineNotify();
			clientNotOnlineNotify.setCaseId(caseid);
			clientNotOnlineNotify.setCaseExplain(entity.getCaseExplain());
			clientNotOnlineNotify.setClientId(clientId);
			clientNotOnlineNotify.setClientName(client.getIdentifyName());
			clientNotOnlineNotify.setMediatorClientId(entity.getMediatorClientId());
			clientNotOnlineNotify.setGatherTime(new Date());
			clientNotOnlineNotifyService.save(clientNotOnlineNotify);
		}
		
		//6.发送短信
		String templateid = "3054951";//网易短信模板id
		String bssr = null;
		//如果是第三方获取被申诉人的名字
		if(tempClient.isPartB()){
			bssr = tempClient.getIdentifyName();
		}else{
			TempClientQuery tq = new TempClientQuery();
			tq.setCaseId(caseid);
			tq.setPartB(true);
			TempClient t = tempClientService.getEntity(tq);
			bssr = t.getIdentifyName();
		}
		Client c  = clientService.getById(entity.getMediatorClientId());
		String para1 = entity.getApplyClientName()+"与"+ bssr+"的"+entity.getCaseType();
//		String para2 = tempClient.getIdentifyName()+"您好";
		String para3 = c.getIdentifyName();
		String para4 = tempClient.getTel();
		JSONArray  paramsarry = new JSONArray();
		paramsarry.put(para1);
		paramsarry.put("我们的");
		paramsarry.put(para3);
		paramsarry.put(para4);
		JSONArray  phonearry = new JSONArray();
		phonearry.put(para4);
//		JSONArray  paramsarry = new JSONArray();
//		paramsarry.put(entity.getCaseExplain());
//		paramsarry.put(agencyName);
//		paramsarry.put(entity.getMediatorClient().getIdentifyName());
//		paramsarry.put(tempClient.getTel());
//		关于 “（申诉人与被申诉人的xx）”纠纷案件，“ xx你好，”“调解员” 调解员邀请您参与在线调解，请登录海沧调解在线参与调解，账号为您的手机号：“”，初始密码为：123456。
		smsVerifyRecordService.sendSmsMessage(phonearry.toString(), SmsBizModelEnum.sms, templateid, paramsarry.toString());
	}
	
	@Override
	public void operateMediationForSign(String caseid) throws BizException,
			ErrorException {
		MediationCase entity = this.getById(caseid);
		if(entity == null){
			throw new BizException("案件不存在!");
		}
		if(!entity.getCaseState().equals(CaseStateEnum.Mediating)){
			throw new BizException("案件状态不对，不能操作!");
		}
		entity.setCaseState(CaseStateEnum.WaitSign);
		this.save(entity);
	}
	
	@Override
	public List<MediationCaseInfoDto> getListForNormalClient(String clientid,
			Integer tabindex) throws BizException, ErrorException {
		List<MediationCaseInfoDto> list = mediationCaseDao.getListForNormalClient(clientid, tabindex);
		Collections.sort(list, new ComparatorMediationCaseInfoDto());
		return list;
	}
	
	@Override
	public void notifyClientToSignProtocolOffLine(String caseid)
			throws BizException, ErrorException {
		
		MediationCase entity = this.getById(caseid);
		if(entity == null){
			throw new BizException("案件不存在!");
		}
		entity.setCaseState(CaseStateEnum.WaitSign);
		this.save(entity);
		
		String alertMessage = "协议书已经确认，请及时前往签署。";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pushAlertMessage", alertMessage);
		List<String> pushclientlist = new ArrayList<String>();
		pushclientlist.add(entity.getApplyClientId());
		
		TempClientQuery tempClientQuery = new TempClientQuery();
		tempClientQuery.setCaseId(caseid);
		tempClientQuery.setPartB(true);
		List<TempClient> list = tempClientService.getEntities(tempClientQuery);
		if(list != null && list.size() > 0){//当事人已经同意协议书的全部返回初始化状态
			for(int i = 0; i < list.size(); i ++){
				TempClient temp = list.get(i);
				String pushClientId = temp.getClientId();
				pushclientlist.add(pushClientId);
			}
		}
		map.put("pushclientlist", pushclientlist);
		PushMessageDisposeFactory.bulidPush(PushTypeEnum.NotifyToSignProtocol, map);
	}
	
	@Override
	public void closeMediation(String clientid, String caseid, String failreason)
			throws BizException, ErrorException {
		if(StringUtils.isEmpty(failreason)){
			throw new BizException("失败理由不能为空!");
		}
		MediationCase entity = this.getById(caseid);
		if(entity == null){
			throw new BizException("案件不存在!");
		}
		if(!clientid.equals(entity.getMediatorClientId())){
			throw new BizException("没有操作权限!");
		}
		entity.setFailReason(failreason);
		entity.setCaseState(CaseStateEnum.Closed);
		Client client = clientService.getById(clientid);
		if(client!=null){
			entity.setMediatorName(client.getIdentifyName());
		}
		
		this.save(entity);
		TempClientQuery TempClientQuery = new TempClientQuery();
		TempClientQuery.setCaseId(caseid);
		List<TempClient> li = tempClientService.getEntities(TempClientQuery);
		
		if(li!=null && li.size()>0){
			for(int i=0 ; i<li.size();i++){
				TempClient t = li.get(i);
				if(t.isPartB()==true){
					JSONArray  paramsarry = new JSONArray();
					paramsarry.put(t.getIdentifyName());//被申述名字
					paramsarry.put(entity.getApplyClientName()); //申诉人
					paramsarry.put(entity.getCaseType());//案件类型
					paramsarry.put(failreason);//原因
					this.sendSmsMessage(t.getTel(),paramsarry);
				}
			}
		}
	}
	 
	/**
	 * 
	 * @param phone
	 * @param paramsarry 
	 * @throws InterruptedException 
	 */
	public void sendSmsMessage(String phone,JSONArray paramsarry){
		//默认3次
		sendSmsMessage(phone,paramsarry,3);
	}
	
	/**
	 * 
	 * @param phone
	 * @param paramsarry 
	 * @param i 初始值0
	 * @throws InterruptedException 
	 */
	public void sendSmsMessage(String phone,JSONArray paramsarry,int i) {
		String templateid = "3114054";
		try{
			JSONArray phoneJson = new JSONArray();
			phoneJson.put(phone);
			smsVerifyRecordService.sendSmsMessage(phoneJson.toString(),SmsBizModelEnum.sms, templateid, paramsarry.toString());
		}catch (BizException e) {
			if(i<3)//发送失败或出现异常递归3次
				sendSmsMessage(phone,paramsarry,++i);
		}
	}
	
	/**
	 * 按组织机构查
	 * @param clientid
	 * @param startTime
	 * @param endTime
	 * @param mediationAgencyId
	 * @return
	 * @throws BizException
	 * @throws ErrorException
	 */
	@Override
	public Map<String, Object> statisticsMediationCaseByClientId(
			String clientid,String startTime,String endTime,String mediationAgencyId) throws BizException, ErrorException {
		Client client = clientService.getById(clientid);
		if(client == null){
			throw new BizException("找不到用户!");
		}
		String clientstate = client.getClientState().toString();
		String agencyid = "";
		if(!clientstate.equals(ClientStateEnum.MediationCenter.toString())){
			agencyid = client.getMediationAgencyId();
		}
		Map<String, Object> result = mediationCaseDao.statisticsMediationCaseByClientId(clientid, 
				clientstate, agencyid, startTime, endTime,mediationAgencyId);
		return result;
	}
	
	@Override
	public String getSendClientDescByCaseId(String caseid, String clientid)
			throws BizException, ErrorException {
		MediationCase entity = this.getById(caseid);
		if(entity == null){
			throw new BizException("案件不存在!");
		}
		if(clientid.equals(entity.getApplyClientId())){
			return "申述人";
		}
		if(clientid.equals(entity.getMediatorClientId())){
			return "调解员";
		}
		TempClientQuery tempClientQuery = new TempClientQuery();
		tempClientQuery.setCaseId(caseid);
		List<TempClient> list = tempClientService.getEntities(tempClientQuery);
		if(list != null && list.size() > 0){
			for(TempClient tempClient : list){
				if(StringUtils.isNotEmpty(tempClient.getClientId())){
					if(clientid.equals(tempClient.getClientId())){
						if(tempClient.isPartB())
							return "被申述人";
						else
							return "第三方";
					}
				}
			}
		}
		return "未知";
	}
	
	@Override
	public Map<String, Object> fileToZipByCaseId(String caseid) throws BizException,
			ErrorException {
		 
		Map<String, Object> result = Maps.newHashMap();
		
		MediationCase entity = this.getById(caseid);
		if(entity == null){
			throw new BizException("案件不存在!");
		}
		String url = "uploads/mediation/"+caseid;
		//1. 导出案件申请书
		new FileMediationToDoc().fileMediationApplyToDoc(caseid);
		//2. 导出协议书
		new FileMediationToDoc().fileMediationProtocolToDoc(caseid);
		//3. 导出笔录
		new FileMediationToDoc().fileMediationRecordToDoc(caseid);
		
		HanyuPinyinHelper hanyuPinyinHelper = new HanyuPinyinHelper();
		//4.加入压缩包zip
		//String zipurl = "D:\\Program Files\\apache-tomcat-7.0.72\\apache-tomcat-7.0.72\\arbitratewebapps\\arbitrate\\"+"uploads\\mediation\\"+caseid;
		String zipurl = CommonUtil.getAccResourceUploadFolderPath(url); 
		String applyTime = DateUtils.parseToString(entity.getApplyTime(), DateUtils.DATE_YMD_STR);
		FileToZip.fileToZip(zipurl, zipurl, hanyuPinyinHelper.toHanyuPinyin(entity.getApplyClientName())+"_"+applyTime);
		result.put("clientname", entity.getApplyClientName());
		result.put("caseexplain", entity.getCaseExplain());
		result.put("url", url+"/"+hanyuPinyinHelper.toHanyuPinyin(entity.getApplyClientName())+"_"+applyTime+".zip");
		return result;
	}
	
	@Override
	public HashMap<String,HashMap<String,Object>> statMediationCaseExcelDto(
			String begin,String end) throws BizException, ErrorException {
		return mediationCaseDao.statMediationCaseExcelDto(begin,end);
	}

	/**
	 * 按组织机构查成功案例
	 * @param clientid
	 * @param startTime
	 * @param endTime
	 * @param mediationAgencyId
	 * @return
	 * @throws BizException
	 * @throws ErrorException
	 */
	@Override
	public Map<String, Object> statisticsMediationCaseByClientIdSuccess(String clientid, String startTime,
			String endTime, String mediationAgencyId) throws BizException, ErrorException {
		Client client = clientService.getById(clientid);
		if(client == null){
			throw new BizException("找不到用户!");
		}
		String clientstate = client.getClientState().toString();
		String agencyid = "";
		if(!clientstate.equals(ClientStateEnum.MediationCenter.toString())){
			agencyid = client.getMediationAgencyId();
		}
		Map<String, Object> result = mediationCaseDao.statisticsMediationCaseByClientIdSuccess(clientid, 
				clientstate, agencyid, startTime, endTime,mediationAgencyId);
		return result;
	}


}