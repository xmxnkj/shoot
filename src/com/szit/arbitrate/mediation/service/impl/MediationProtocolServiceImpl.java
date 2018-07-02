package com.szit.arbitrate.mediation.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseServiceImpl;
import com.hsit.common.utils.BeanCopyUtils;
import com.hsit.common.utils.DateUtils;
import com.szit.arbitrate.api.mediation.bo.in.MediationProtocolInBo;
import com.szit.arbitrate.api.mediation.bo.in.TempClientInBo;
import com.szit.arbitrate.basisset.entity.SysParameterTable;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;
import com.szit.arbitrate.basisset.service.SysParameterTableService;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.enumvo.CertificateStateEnum;
import com.szit.arbitrate.client.entity.query.TempClientQuery;
import com.szit.arbitrate.client.service.AuthorityGroupOperationService;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.SmsVerifyRecordService;
import com.szit.arbitrate.client.service.TempClientService;
import com.szit.arbitrate.mediation.dao.MediationProtocolDao;
import com.szit.arbitrate.mediation.docfactory.DocExecuteReflectFactory;
import com.szit.arbitrate.mediation.docfactory.product.impl.BuildMediationProtocolProduct;
import com.szit.arbitrate.mediation.entity.MediationCase;
import com.szit.arbitrate.mediation.entity.MediationProtocol;
import com.szit.arbitrate.mediation.entity.enumvo.CaseStateEnum;
import com.szit.arbitrate.mediation.entity.enumvo.ProtocolStateEnum;
import com.szit.arbitrate.mediation.entity.query.MediationProtocolQuery;
import com.szit.arbitrate.mediation.service.MediationCaseService;
import com.szit.arbitrate.mediation.service.MediationProtocolService;
import com.szit.arbitrate.pushcentre.factory.PushMessageDisposeFactory;
import com.szit.arbitrate.pushcentre.service.PushCentreService;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

/**
 * 
* @ProjectName:
* @ClassName: MediationProtocolServiceImpl
* @Description:协议业务接口实现类
* @author Administrator
* @date 2017年3月23日 上午11:32:46
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class MediationProtocolServiceImpl extends AppBaseServiceImpl<MediationProtocol, MediationProtocolQuery>
	implements MediationProtocolService{

	@Autowired
	private AuthorityGroupOperationService authorityGroupOperationService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private TempClientService tempClientService;
	@Autowired
	private MediationCaseService mediationCaseService;
	@Autowired
	private SmsVerifyRecordService smsVerifyRecordService;
	@Autowired
	private PushCentreService pushCentreService;
	@Autowired
	private SysParameterTableService sysParameterTableService;
	@Autowired
	private MediationProtocolDao dao;

	public MediationProtocolDao getDao() {
		return dao;
	}

	public void setDao(MediationProtocolDao dao) {
		this.dao = dao;
	}
	/**
	 * 判断参数为null 返回一个空格
	 */
	public static Object getblank(Object obj){
		return obj==null ? " " : obj;
	}
	
	@Override
	public MediationProtocol  createMediationProtocol(List<TempClientInBo> tempclientlist, 
			MediationProtocolInBo protocolinbo) throws BizException,ErrorException {
		if(tempclientlist == null || tempclientlist.size() == 0 || protocolinbo == null){
			throw new BizException("参数不能为空，请输入!");
		}
		MediationCase mediationCase = mediationCaseService.getById(protocolinbo.getCaseId());
		if(mediationCase == null){
			throw new BizException("不存在!");
		}
		//1. 查询此是否有协议书存在
		MediationProtocolQuery query = new MediationProtocolQuery();
		query.setCaseId(protocolinbo.getCaseId());
		MediationProtocol entity = this.getEntity(query);
		
		if(entity == null){
			String[] array = new String[]{"birth"};
			try {
				entity = (MediationProtocol)BeanCopyUtils.beanUtilsCopy(protocolinbo, MediationProtocol.class, array);
			} catch (Exception e) {
				throw new ErrorException("异常错误!");
			}
			entity.setApplyClientId(mediationCase.getApplyClientId());
			entity.setPartAState(ProtocolStateEnum.Init);
			entity.setBirth(DateUtils.parseToDate(protocolinbo.getBirth(), DateUtils.DATE_YMD_STR));
			
		}else{
			
			entity.setApplyClientId(mediationCase.getApplyClientId());
			if(StringUtils.isNotEmpty(protocolinbo.getBirth())){
				entity.setBirth(DateUtils.parseToDate(protocolinbo.getBirth(), DateUtils.DATE_YMD_STR));
			}
			entity.setProfession(protocolinbo.getProfession()==null?" ":protocolinbo.getProfession());;
			entity.setTitle(protocolinbo.getTitle()==null?" ":protocolinbo.getTitle());
			entity.setSerialNumber(protocolinbo.getSerialNumber()==null?" ":protocolinbo.getSerialNumber());
			entity.setAddress(protocolinbo.getAddress()==null?" ":protocolinbo.getAddress());
			entity.setApplyClientId(mediationCase.getApplyClientId());
			entity.setCaseId(mediationCase.getId());
			entity.setContent(protocolinbo.getContent()==null?" ":protocolinbo.getContent());
			entity.setControversy(protocolinbo.getControversy()==null?" ":protocolinbo.getControversy());
			entity.setExecute(protocolinbo.getExecute()==null?" ":protocolinbo.getExecute());
			entity.setExternalDesc(protocolinbo.getExternalDesc()==null?" ":protocolinbo.getExternalDesc());
			entity.setGender(protocolinbo.isGender()==true?true:protocolinbo.isGender());
			entity.setIdentify(protocolinbo.getIdentify()==null?" ":protocolinbo.getIdentify());
			entity.setIdentifyName(protocolinbo.getIdentifyName()==null?" ":protocolinbo.getIdentifyName());
			entity.setNation(protocolinbo.getNation()==null?" ":protocolinbo.getNation());
			entity.setTel(protocolinbo.getTel()==null?" ":protocolinbo.getTel());
			entity.setMediatorTel(protocolinbo.getMediatorTel()==null?" ":protocolinbo.getMediatorTel());
			entity.setOralAgree(getblank(protocolinbo.getOralAgree()).toString());//口头协议 或 书面协议
		}
		this.save(entity);
		
		for(int i = 0; i < tempclientlist.size(); i ++){
			TempClientInBo tempClientInBo = tempclientlist.get(i);
			TempClient tempClient = tempClientService.getById(tempClientInBo.getId());
			if(tempClient == null){
				tempClient = new TempClient();
			}
			tempClient.setAddress(tempClientInBo.getAddress()==null?" ":tempClientInBo.getAddress());
			if(StringUtils.isNotEmpty(tempClientInBo.getBirth())){
				tempClient.setBirth(DateUtils.parseToDate(tempClientInBo.getBirth(), DateUtils.DATE_YMD_STR));
			}
			tempClient.setGender(tempClientInBo.isGender()==true?true:tempClientInBo.isGender());
			tempClient.setIdentify(tempClientInBo.getIdentify()==null?" ":tempClientInBo.getIdentify());
			tempClient.setNation(tempClientInBo.getNation()==null?" ":tempClientInBo.getNation());
			tempClient.setIdentifyName(tempClientInBo.getName()==null?" ":tempClientInBo.getName());
			tempClient.setTel(tempClientInBo.getTel()==null?" ":tempClientInBo.getTel());
			tempClient.setProfession(tempClientInBo.getProfession()==null?" ":tempClientInBo.getProfession());
			tempClient.setAddress(tempClientInBo.getAddress()==null?" ":tempClientInBo.getAddress());
			tempClient.setIdentify(tempClientInBo.getIdentify()==null?" ":tempClientInBo.getIdentify());
			tempClientService.save(tempClient);
		}
		return  entity;
	}
	
	@Override
	public void notifyProtocolToClient(String protocolid, String smscontent) throws BizException,
			ErrorException {
		MediationProtocol entity = this.getById(protocolid);
		if(entity == null){
			throw new BizException("协议书不存在!"); 
		}
		//申述人是否实名
		//Client client = clientService.getById(entity.getApplyClientId());
		/*if(client.getAuditInfo()!=null && !client.getAuditInfo().equals(CertificateStateEnum.Pass)){
			throw new BizException("申述人未进行实名认证,无法发送协议书!");
		}*/
		String caseId = entity.getCaseId();
		MediationCase mediationCase = mediationCaseService.getById(caseId);
		if(mediationCase == null){
			throw new BizException("不存在!"); 
		}
		
		//1.推送通知
		String alertMessage = "您申请的协议书已生成，请注意查收!";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pushAlertMessage", alertMessage);
		String caseid = entity.getCaseId();
		map.put("caseid", caseid);
		String pushClientId = entity.getApplyClientId();
		List<String> pushclientlist = new ArrayList<String>();
		pushclientlist.add(pushClientId);
		
		TempClientQuery tempClientQuery = new TempClientQuery();
		tempClientQuery.setCaseId(caseId);
		List<TempClient> list = tempClientService.getEntities(tempClientQuery);
		if(list != null && list.size() > 0){
			for(TempClient tempClient : list){
				Client temp = clientService.getById(tempClient.getClientId());
				if(temp == null){
					throw new BizException("被申述人未召集,无法发送协议书!");
				}else{
					String recClientId = temp.getId();
					pushclientlist.add(recClientId);
				}
			}
		}
		map.put("pushclientlist", pushclientlist);
		PushMessageDisposeFactory.bulidPush(PushTypeEnum.MediateProtocol, map);
		entity.setFinalVersion(true);
		this.save(entity);
	}
	
	@Override
	public void operateMediationProtocol(String clientid, String protocolid,
			Integer type) throws BizException, ErrorException {
		Client client = clientService.getById(clientid);
		if(client == null){
			throw new BizException("用户不存在!");
		}
		SysParameterTable sysparametertable = 
				sysParameterTableService.getSysParameterTable(ParameterTypeEnum.certificateswitch, "signProtocolSwitch");
		//开启认证验证
		if(sysparametertable != null && sysparametertable.getParameterinitvla().equals("1")){
			if(!client.getAuditInfo().equals(CertificateStateEnum.Pass)){
				throw new BizException("你没有该操作权限,请先实名认证!");
			}
		}
		MediationProtocol entity = this.getById(protocolid);
		String caseId = entity.getCaseId();
		MediationCase mediationCase = mediationCaseService.getById(caseId);
		
		String alertMessage = "您参与的:"+mediationCase.getCaseExplain()+",所有当事人都已同意协议书，请及时跟进!";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pushAlertMessage", alertMessage);
		map.put("caseId", caseId);
		String pushClientId = mediationCase.getMediatorClientId();
		map.put("pushClientId", pushClientId);
		
		TempClientQuery tempClientQuery = new TempClientQuery();
		if(type == 0){//0表示接受协议书
			if(entity.getApplyClientId().equals(clientid)){//说明接受者是申述人甲方
				if(entity.getPartAState().equals(ProtocolStateEnum.Accepted)){
					throw new BizException("你已经同意了协议书，不用再同意!");
				}
				entity.setPartAState(ProtocolStateEnum.Accepted);
				entity.setPartATime(new Date());
				
				mediationCase.setAcceptProtocol(true);//甲方同意协议书
				
				int count = dao.getTempClientNotAcceptCount(caseId);
				if(count == 0){//说明没有同意的人数为0
					mediationCase.setCaseState(CaseStateEnum.WaitComplete);
					PushMessageDisposeFactory.bulidPush(PushTypeEnum.NotifyMediatorProtocol, map);
				}
			}else{//说明是乙方某个人在同意
				tempClientQuery.setCaseId(caseId);
				tempClientQuery.setClientId(clientid);
				TempClient tempClient = tempClientService.getEntity(tempClientQuery);
				if(tempClient.getSign().equals(ProtocolStateEnum.Accepted)){
					throw new BizException("你已经同意了协议书，不用再同意!");
				}
				tempClient.setSign(ProtocolStateEnum.Accepted);
				tempClientService.save(tempClient);
				
				
				//查询所有乙方是否同意，同意就变为待结案状态
				int count = dao.getTempClientNotAcceptCount(caseId);
				if(count == 0 && entity.getPartAState().equals(ProtocolStateEnum.Accepted)){//说明没有同意的人数为0
					mediationCase.setCaseState(CaseStateEnum.WaitComplete);
					PushMessageDisposeFactory.bulidPush(PushTypeEnum.NotifyMediatorProtocol, map);
				}
			}
		}
		
		if(type == 1){//1说明拒绝协议书,状态变回中
			entity.setPartAState(ProtocolStateEnum.Init);
			tempClientQuery.setCaseId(caseId);
			List<TempClient> list = tempClientService.getEntities(tempClientQuery);
			if(list != null && list.size() > 0){//当事人已经同意协议书的全部返回初始化状态
				for(int i = 0; i < list.size(); i ++){
					TempClient temp = list.get(i);
					if(temp.getSign().equals(ProtocolStateEnum.Accepted)){
						temp.setSign(ProtocolStateEnum.Init);
						tempClientService.save(temp);
					}
				}
			}
			mediationCase.setAcceptProtocol(false);
			entity.setFinalVersion(false);
		}
		this.save(entity);
		mediationCaseService.save(mediationCase);
		
	}
	
	@Override
	public Map<String, String> downloadMediationProtocol(String protocolid)
			throws BizException, ErrorException {
		MediationProtocol entity = this.getById(protocolid);
		if(entity == null){
			throw new BizException("协议书不存在!");
		}
		//构造导出doc文档参数
		Map<String, Object> docparams = Maps.newHashMap();
		docparams.put("entity", entity);
		String url = "uploads/mediation/protocol";
		docparams.put("url", url);
		TempClientQuery tempClientQuery = new TempClientQuery();
		tempClientQuery.setCaseId(entity.getCaseId());
		List<TempClient> list = tempClientService.getEntities(tempClientQuery);
		docparams.put("list", list);
		String template = "protocol.ftl";//模板
		docparams.put("template", template);
		docparams.put("filename", protocolid);//导出的文件名
		DocExecuteReflectFactory fac = new DocExecuteReflectFactory(docparams);
		fac.createProduct(BuildMediationProtocolProduct.class);
		
		Map<String, String> result = Maps.newHashMap();
		result.put("url", url);
		result.put("filename", protocolid+".doc");
		return result;
	}
	
}
