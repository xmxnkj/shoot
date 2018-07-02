package com.szit.arbitrate.mediation.junit;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsit.common.utils.JsonFormatUtil;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.mediation.entity.MediationRecord;
import com.szit.arbitrate.mediation.service.MediationRecordService;

public class MediationRecordServiceJunit extends BaseApiJunitTest{
	
	@Autowired
	private MediationRecordService mediationRecordService;
	
	//private Logger logger = LoggerFactory.getLogger(ClientServiceJunit.class);
	
	//保存笔录(手动保存)
	@Test
	public void saveMediationRecord(){
	/*
		private String involvedPerson;	//当事人
		private String joinPerson;		//参与人
		private String address;			//地点
		private Date recordTime;		//录入时间
	*/
		String caseId = "3e8d7853-911a-4ee5-96f5-b9e42f09c87b";
		String recordContent = "recordContent内容";
		String involvedPerson = "当事人：CPJ";
		String time = "2017-05-16";
		String recordstate = "Success";
		String recordtype = "SurveyRecord";
		
		try {
			mediationRecordService.saveMediationRecord(caseId, recordContent ,involvedPerson, time, address,recordstate,recordtype);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//查看笔录
	@Test
	public void getMediationRecord(){
		String mediationRecordId = "139cc319-a0ec-4898-850b-26bcaf4918db";
		MediationRecord mediationRecord = mediationRecordService.getById(mediationRecordId);
		JsonFormatUtil.printJson("obj", mediationRecord);
	}
	
	@Test
	public void getMediationRecordList(){
		String caseId = "3e8d7853-911a-4ee5-96f5-b9e42f09c87b";
		String recordtype = "SurveyRecord";
		Map<String,Object> map = mediationRecordService.getMediationRecordList(caseId,recordtype,1);
		JsonFormatUtil.printJson("list", map);
	}
	
	@Test
	public void updateMediationRecordList(){
		String mediationRecordId = "139cc319-a0ec-4898-850b-26bcaf4918db";
		String time = "2017-05-16";
		String involvedPerson = "当事人：CPJ";
		String recordContent = "xxx";
		String address = "你家";
		String recordstate = "Success";
		String recordtype = "SurveyRecord";
		mediationRecordService.modifyMediationRecord(mediationRecordId, recordContent, involvedPerson, time, address,recordstate,recordtype);
	}
}