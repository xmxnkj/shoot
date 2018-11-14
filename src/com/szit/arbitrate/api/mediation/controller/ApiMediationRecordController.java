package com.szit.arbitrate.api.mediation.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

public interface ApiMediationRecordController {
	
	/**
	 * @param caseId
	 * @return
	 */
	public ApiOutParamsVm getMediationRecord(String mediationRecordId);
	
	/**
	 * @param caseId
	 * @param pageNum
	 * @param recordtype 记录类型
	 * @return
	 */
	public ApiOutParamsVm getMediationRecordList(String caseId,String recordtype, int pageNum);
	
	/**
	 * @param caseId
	 * @param recordContent
	 * @param involvedPerson
	 * @param joinPerson
	 * @param address
	 * @return
	 */
	public ApiOutParamsVm saveMediationRecord(String caseid,String recordcontent, String involvedperson,
			String time,String address, String joinPerson,String recordtype);
	/**
	 * @param mediationRecordId
	 * @param joinPerson
	 * @return
	 */
	public ApiOutParamsVm updateMediationRecord(String mediationrecordid,String recordcontent,String involvedperson,
			String time,String address, String joinPerson,String recordtype);
	
	/**
	 * @param mediationRecordId
	 * @return
	 */
	public ApiOutParamsVm removeMediationRecord(String mediationRecordId);
	
}
