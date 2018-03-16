package com.szit.arbitrate.mediation.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.utils.JsonFormatUtil;
import com.hsit.common.utils.PageDataBean;
import com.szit.arbitrate.mediation.entity.MediationRecord;
import com.szit.arbitrate.mediation.entity.enumvo.RecordTypeEnum;
import com.szit.arbitrate.mediation.entity.query.MediationRecordQuery;
import com.szit.arbitrate.mediation.service.MediationRecordService;

@Namespace("/admin/mediation/mediationrecord")
@Controller("mediationRecordAction")
public class MediationRecordAction extends BaseJsonAction<MediationRecord, MediationRecordQuery>{

	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = -7904443043775723991L;
	@Autowired
	private MediationRecordService service;

	public MediationRecordService getService() {
		return service;
	}

	public void setService(MediationRecordService service) {
		this.service = service;
	}

	@Override
	protected void beforeQuery() {
		String caseid = this.getRequest().getParameter("caseid");
		MediationRecordQuery query = this.getEntityQuery();
		if(StringUtils.isNotEmpty(caseid)){
			query.setCaseId(caseid);
		}
		this.setEntityQuery(query);
	}
	
	@Action("getRecords")
	public void getRecords(){
		String caseid = getRequest().getParameter("caseId");
		String recordType = getRequest().getParameter("recordType");
		
		MediationRecordQuery mediationRecordQuery = getEntityQuery();
		mediationRecordQuery.setCaseId(caseid);
		mediationRecordQuery.setRecordTypeEnum(RecordTypeEnum.valueOf(recordType));
		mediationRecordQuery.addOrder("recordTime", true);
		
		List<MediationRecord> mediationRecordList = service.getEntities(mediationRecordQuery);
		Paging  paging  = this.getEntityQuery().getPaging();
		PageDataBean<MediationRecord> pageList = new PageDataBean<MediationRecord>(paging,mediationRecordList);
		String jsonResult = jsonMapper.toJson(pageList);
		if(isDebug()){
		    JsonFormatUtil.printJson("分页JSON", jsonResult);
		}
		outJson(jsonResult);
	}
	
}
