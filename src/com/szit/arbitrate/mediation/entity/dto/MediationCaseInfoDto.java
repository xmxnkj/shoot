package com.szit.arbitrate.mediation.entity.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.utils.CustomDateSerializerYmd;
import com.szit.arbitrate.mediation.entity.enumvo.CaseAllocationStateEnum;

public class MediationCaseInfoDto implements Serializable{
	
	private static final long serialVersionUID = -297201753999851129L;
	
	private String caseId;//按键id
	private String caseExplain;//申述点
	private String applyClientName;//申述人
	private Integer caseState;//案件状态
	private Date applyDate;//申请时间
	private List<String> nameList;//申述对象列表
	
	private String mediatorId;//调解员name
	
	private String mediatorName;//调解员id
	
	private String mediatorTel;//调解员电话
	
	private Integer allocationState;//案件状态
	
	private String  mediatorClientId;//调解人id
	
	private String agencyName;//调解员机构名称
	
	private String resultName;
	
	public String getResultName() {
		return resultName;
	}
	public void setResultName(String resultName) {
		this.resultName = resultName;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getCaseExplain() {
		return caseExplain;
	}
	
	public Integer getAllocationState() {
		return allocationState;
	}
	public void setAllocationState(Integer allocationState) {
		this.allocationState = allocationState;
	}
	
	public String getMediatorClientId() {
		return mediatorClientId;
	}
	public void setMediatorClientId(String mediatorClientId) {
		this.mediatorClientId = mediatorClientId;
	}
	public void setCaseExplain(String caseExplain) {
		this.caseExplain = caseExplain;
	}
	public String getApplyClientName() {
		return applyClientName;
	}
	public void setApplyClientName(String applyClientName) {
		this.applyClientName = applyClientName;
	}
	public List<String> getNameList() {
		return nameList;
	}
	public void setNameList(List<String> nameList) {
		this.nameList = nameList;
	}
	public Integer getCaseState() {
		return caseState;
	}
	public void setCaseState(Integer caseState) {
		this.caseState = caseState;
	}
	@JsonSerialize(using = CustomDateSerializerYmd.class)
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getMediatorName() {
		return mediatorName;
	}
	public void setMediatorName(String mediatorName) {
		this.mediatorName = mediatorName;
	}
	public String getMediatorId() {
		return mediatorId;
	}
	public void setMediatorId(String mediatorId) {
		this.mediatorId = mediatorId;
	}
	public String getMediatorTel() {
		return mediatorTel;
	}
	public void setMediatorTel(String mediatorTel) {
		this.mediatorTel = mediatorTel;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}
	
}
