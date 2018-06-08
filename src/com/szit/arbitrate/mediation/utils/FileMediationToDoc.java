package com.szit.arbitrate.mediation.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.utils.DocUtil;
import com.hsit.common.utils.JsonMapper;
import com.hsit.common.utils.SpringBeanUtil;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.query.TempClientQuery;
import com.szit.arbitrate.client.service.TempClientService;
import com.szit.arbitrate.mediation.docfactory.DocExecuteReflectFactory;
import com.szit.arbitrate.mediation.docfactory.product.impl.BuildMediationProtocolProduct;
import com.szit.arbitrate.mediation.entity.MediationCase;
import com.szit.arbitrate.mediation.entity.MediationProtocol;
import com.szit.arbitrate.mediation.entity.MediationRecord;
import com.szit.arbitrate.mediation.entity.enumvo.RecordTypeEnum;
import com.szit.arbitrate.mediation.entity.query.MediationProtocolQuery;
import com.szit.arbitrate.mediation.entity.query.MediationRecordQuery;
import com.szit.arbitrate.mediation.service.MediationCaseService;
import com.szit.arbitrate.mediation.service.MediationProtocolService;
import com.szit.arbitrate.mediation.service.MediationRecordService;

public class FileMediationToDoc {

	private MediationCaseService service;
	private TempClientService tempClientService;
	private MediationProtocolService mediationProtocolService;
	private MediationRecordService mediationRecordService;

	public FileMediationToDoc() {
		service = (MediationCaseService) SpringBeanUtil.getBean("mediationCaseServiceImpl");
		tempClientService =  (TempClientService) SpringBeanUtil.getBean("tempClientServiceImpl");
		mediationProtocolService = (MediationProtocolService) SpringBeanUtil.getBean("mediationProtocolServiceImpl");
		mediationRecordService = (MediationRecordService) SpringBeanUtil.getBean("mediationRecordServiceImpl");
	}

	public void fileMediationApplyToDoc(String caseid){
 
		MediationCase entity = service.getById(caseid);
		TempClientQuery query = new TempClientQuery();
		query.setCaseId(caseid);
		List<TempClient> tempClientList = tempClientService.getEntities(query);

		Map<String, Object> dataMap=new HashMap<String, Object>(); 
		dataMap.put("applyClientName", entity.getApplyClientName());
		if(entity.getApplyClient().isGender()){
			dataMap.put("gender", "男");
		}else{
			dataMap.put("gender", "女");
		}
		dataMap.put("nation", entity.getApplyClient().getNation()==null?"":entity.getApplyClient().getNation());
		dataMap.put("birth", entity.getApplyClient().getBirth()==null?"":entity.getApplyClient().getBirth());
		dataMap.put("tel", entity.getApplyClient().getTel()==null?"":entity.getApplyClient().getTel());
		dataMap.put("address", entity.getApplyClient().getAddress()==null?"":entity.getApplyClient().getAddress());
		dataMap.put("profession", entity.getApplyClient().getProfession()==null?"":entity.getApplyClient().getProfession());
		dataMap.put("identify", entity.getApplyClient().getIdentify()==null?"":entity.getApplyClient().getIdentify());
		dataMap.put("caseExplain", entity.getCaseExplain()==null?"":entity.getCaseExplain());

		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(int i = 0; i < tempClientList.size(); i ++){
			TempClient tempClient = tempClientList.get(i);
			Map<String,Object> map = new HashMap<String,Object>(); 
			map.put("identifyName", tempClient.getIdentifyName()==null?"":tempClient.getIdentifyName()); 
			map.put("tel", tempClient.getTel()==null?"":tempClient.getTel()); 
			map.put("address", tempClient.getAddress()==null?"":tempClient.getAddress()); 
			map.put("nation", tempClient.getNation()==null?"":tempClient.getNation());
			map.put("birth", tempClient.getBirth()==null?"":tempClient.getBirth());
			map.put("profession", tempClient.getProfession()==null?"":tempClient.getProfession());
			if(tempClient.isGender()){
				map.put("gender", "男");
			}else{
				map.put("gender", "女");
			}
			list.add(map);
		}
		dataMap.put("list", list);

		String url = "uploads/mediation/"+caseid;
		String templateurl = "/com/hsit/common/template";
		DocUtil util = new DocUtil();
		String filename = entity.getCaseExplain();
		filename = "人民申请书";
		util.createDoc(dataMap, templateurl, "applymediation.ftl", url, filename);
	}

	public void fileMediationProtocolToDoc(String caseid) throws BizException, ErrorException {
		MediationProtocolQuery query = new MediationProtocolQuery();
		query.setCaseId(caseid);
		MediationProtocol mediationProtocol = mediationProtocolService.getEntity(query);
		if(mediationProtocol == null){
			throw new BizException("找不到协议书!");
		}
		//构造导出doc文档参数
		Map<String, Object> docparams = Maps.newHashMap();
		docparams.put("entity", mediationProtocol);
		String url = "uploads/mediation/"+caseid;
		docparams.put("url", url);
		TempClientQuery tempClientQuery = new TempClientQuery();
		tempClientQuery.setCaseId(caseid);
		List<TempClient> list = tempClientService.getEntities(tempClientQuery);
		docparams.put("list", list);
		String template = "mediationprotocol.ftl";//模板 
		docparams.put("template", template);
		if(mediationProtocol.getTitle()==null || "".equals(mediationProtocol.getTitle())){
			docparams.put("filename","人民协议书");//导出的文件名
		}else{
			docparams.put("filename","人民协议书");
		}
		DocExecuteReflectFactory fac = new DocExecuteReflectFactory(docparams);
		fac.createProduct(BuildMediationProtocolProduct.class);
	}

	public void fileMediationRecordToDoc(String caseid) throws BizException, ErrorException{

		MediationRecordQuery query = new MediationRecordQuery();
		query.setCaseId(caseid);
		String template = "";
		String filename = "";
		int i = 1,j = 1;
		List<MediationRecord> list = mediationRecordService.getEntities(query);
		if(list != null && list.size() > 0){
			for(MediationRecord entity : list){
				Map<String, Object> dataMap=new HashMap<String, Object>();  
				if(entity.getRecordTypeEnum().equals(RecordTypeEnum.AskRecord)){
					template = "mediationrecord1.ftl";
					filename = "人民记录"+i;
					i += 1;
				}else if(entity.getRecordTypeEnum().equals(RecordTypeEnum.SurveyRecord)){
					template = "mediationrecord2.ftl"; 
					filename = "人民调查记录"+j;
					j += 1;
				}
				dataMap.put("recordContent", entity.getRecordContent()==null?"":entity.getRecordContent());
				dataMap.put("involvedPerson", entity.getInvolvedPerson()==null?"":entity.getInvolvedPerson());
				dataMap.put("address", entity.getAddress()==null?"":entity.getAddress());
				dataMap.put("joinPerson", entity.getJoinPerson()==null?"":entity.getJoinPerson());
				dataMap.put("recordTime", entity.getRecordTime()==null?"":entity.getRecordTime());
				String url = "uploads/mediation/"+caseid;
				String templateurl = "/com/hsit/common/template";
				DocUtil util = new DocUtil();
				util.createDoc(dataMap, templateurl, template, url, filename);
			}
		}

	}

}
