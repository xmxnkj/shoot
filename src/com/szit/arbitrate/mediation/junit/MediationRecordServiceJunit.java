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
	
	//保存案件笔录(手动保存)
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
		String address = "厦门海沧";
		String recordstate = "Success";
		String recordtype = "SurveyRecord";
		
		try {
			mediationRecordService.saveMediationRecord(caseId, recordContent ,involvedPerson, time, address,recordstate,recordtype);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//查看案件笔录
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
	
	//修改调解笔录状态
	@Test
	public void updateMediationRecordList(){
		String mediationRecordId = "139cc319-a0ec-4898-850b-26bcaf4918db";
		String time = "2017-05-16";
		String involvedPerson = "当事人：CPJ";
		String recordContent = "调解员从【人民调解记录】按钮进入后，添加 会提示生成《人民调解调查记录》和《人民调解记录》，选择后调解员录入时间、地点、当事人、参加人和调解记录；调解员从调解室的【询问笔录】快捷键进入时，系统自动录入当前时间、地点（默认海沧调解在线APP）、当事人、参加人（支持编辑、添加、修改），调解记录由调解员录入。调解/调查记录的内容为问答式，调解员录入时点击 则系统自动插入问：答：，内容由调解员录入";
		String address = "你家";
		String recordstate = "Success";
		String recordtype = "SurveyRecord";
		mediationRecordService.modifyMediationRecord(mediationRecordId, recordContent, involvedPerson, time, address,recordstate,recordtype);
	}
}