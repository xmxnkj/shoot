package com.szit.arbitrate.mediation.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.mediation.entity.enumvo.CaseAllocationStateEnum;
import com.szit.arbitrate.mediation.entity.enumvo.CaseStateEnum;

public class MediationCaseQuery extends EntityQueryParam{
	
	private String mediateType;//调解类别
	private String caseType;//案件类型
	private String caseExplain;//申述点
	private String protocolForm;//协议形式
	private String caseSource;//案件来源
	
	private String applyClientId;//申请人id
	private String applyClientName;//申请人姓名
	private Date applyTime;//申请时间
	
	private CaseStateEnum caseState;//案件状态
	private CaseAllocationStateEnum allocationState;//案件分配状态
	private String mediatorClientId;//调解人id
	
	public String getMediateType() {
		return mediateType;
	}
	public void setMediateType(String mediateType) {
		this.mediateType = mediateType;
	}
	public String getCaseType() {
		return caseType;
	}
	public void setCaseType(String caseType) {
		this.caseType = caseType;
	}
	
	public String getCaseExplain() {
		return caseExplain;
	}
	public void setCaseExplain(String caseExplain) {
		this.caseExplain = caseExplain;
	}
	public String getProtocolForm() {
		return protocolForm;
	}
	public void setProtocolForm(String protocolForm) {
		this.protocolForm = protocolForm;
	}
	public String getCaseSource() {
		return caseSource;
	}
	public void setCaseSource(String caseSource) {
		this.caseSource = caseSource;
	}
	public String getApplyClientId() {
		return applyClientId;
	}
	public void setApplyClientId(String applyClientId) {
		this.applyClientId = applyClientId;
	}
	
	
	public String getApplyClientName() {
		return applyClientName;
	}
	public void setApplyClientName(String applyClientName) {
		this.applyClientName = applyClientName;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public CaseStateEnum getCaseState() {
		return caseState;
	}
	public void setCaseState(CaseStateEnum caseState) {
		this.caseState = caseState;
	}
	
	public CaseAllocationStateEnum getAllocationState() {
		return allocationState;
	}
	public void setAllocationState(CaseAllocationStateEnum allocationState) {
		this.allocationState = allocationState;
	}
	public String getMediatorClientId() {
		return mediatorClientId;
	}
	public void setMediatorClientId(String mediatorClientId) {
		this.mediatorClientId = mediatorClientId;
	}

}
