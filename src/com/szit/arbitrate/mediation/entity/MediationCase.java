package com.szit.arbitrate.mediation.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.mediation.entity.enumvo.CaseAllocationStateEnum;
import com.szit.arbitrate.mediation.entity.enumvo.CaseStateEnum;

/**
 * 
* @ProjectName:
* @ClassName: Case
* @Description:总类,申请书也用这个实体
* @author Administrator
* @date 2017年3月22日 下午3:04:08
* @UpdateUser:
* @UpdateDate:     
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class MediationCase extends DomainEntity{

	private static final long serialVersionUID = 3073898801236771354L;
	
	private String mediateType;//类别
	private String caseType;//类型
	private String caseExplain;//申述点
	private String address;//发生地
	
	private boolean difficult=false;//是否疑难复杂
	private Integer people;//涉及人数
	private String caseMoney;//涉及金额
	private String protocolForm;//协议形式
	private String caseSource;//来源
	
	private String applyClientId;//申请人id
	private Client applyClient;//申请人client
	private String applyClientName;//申请人
	private Date applyTime;//申请时间
	private boolean acceptProtocol=false;//申请人是否同意协议书
	
	private CaseStateEnum caseState;//状态
	private CaseAllocationStateEnum allocationState;//分配状态
	private String refuseReason;//拒绝理由
	private String failReason;//失败理由
	private String mediatorClientId;//人id
	private String mediationAgencyId;//属于机构id
	private String agencyBelongsTo;//属于机构id
	private String agencyClassify;//机构主题分类
	private Date mediateTime;//时间
	private Client mediatorClient;//人
	private String mediatorInfo;//人信息
	private String mediatorName;	//结束人
	
	private List<String> complainant;		//被申诉人
	
	public String getMediatorName() {
		return mediatorName;
	}
	public void setMediatorName(String mediatorName) {
		this.mediatorName = mediatorName;
	}
	private Integer differenceSubject;//不同主体情况
	
	private boolean roomOpen=false;//会议室是否开启过 
	
	public String getAgencyClassify() {
		return agencyClassify;
	}
	public void setAgencyClassify(String agencyClassify) {
		this.agencyClassify = agencyClassify;
	}
	public String getAgencyBelongsTo() {
		return agencyBelongsTo;
	}
	public void setAgencyBelongsTo(String agencyBelongsTo) {
		this.agencyBelongsTo = agencyBelongsTo;
	}
	public String getMediationAgencyId() {
		return mediationAgencyId;
	}
	public void setMediationAgencyId(String mediationAgencyId) {
		this.mediationAgencyId = mediationAgencyId;
	}
	public boolean isDifficult() {
		return difficult;
	}
	public void setDifficult(boolean difficult) {
		this.difficult = difficult;
	}
	public Integer getPeople() {
		return people;
	}
	public void setPeople(Integer people) {
		this.people = people;
	}
	public String getMediatorInfo() {
		return mediatorInfo;
	}
	public void setMediatorInfo(String mediatorInfo) {
		this.mediatorInfo = mediatorInfo;
	}
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCaseMoney() {
		return caseMoney;
	}
	public void setCaseMoney(String caseMoney) {
		this.caseMoney = caseMoney;
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
	@JsonIgnoreProperties({"account","identifyImgFile1","identifyImgFile2","identifyImgFile3","mediationAgencyId",
		"thirdPartyId","thirdParty","clientType","auditInfo","ip","description","skill","clientState"})
	public Client getApplyClient() {
		return applyClient;
	}
	public void setApplyClient(Client applyClient) {
		this.applyClient = applyClient;
	}
	
	public String getApplyClientName() {
		return applyClientName;
	}
	public void setApplyClientName(String applyClientName) {
		this.applyClientName = applyClientName;
	}
	public boolean isAcceptProtocol() {
		return acceptProtocol;
	}
	public void setAcceptProtocol(boolean acceptProtocol) {
		this.acceptProtocol = acceptProtocol;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
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
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}
	public String getFailReason() {
		return failReason;
	}
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	public String getMediatorClientId() {
		return mediatorClientId;
	}
	public void setMediatorClientId(String mediatorClientId) {
		this.mediatorClientId = mediatorClientId;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getMediateTime() {
		return mediateTime;
	}
	public void setMediateTime(Date mediateTime) {
		this.mediateTime = mediateTime;
	}
	@JsonIgnoreProperties({"account","identifyImgFile1","identifyImgFile2","identifyImgFile3","mediationAgencyId",
		"thirdPartyId","thirdParty","clientType","address","auditInfo","tel","ip","description","skill","clientState"})
	public Client getMediatorClient() {
		return mediatorClient;
	}
	public void setMediatorClient(Client mediatorClient) {
		this.mediatorClient = mediatorClient;
	}

	public List<String> getComplainant() {
		return complainant;
	}
	public void setComplainant(List<String> complainant) {
		this.complainant = complainant;
	}
	public boolean isRoomOpen() {
		return roomOpen;
	}
	public void setRoomOpen(boolean roomOpen) {
		this.roomOpen = roomOpen;
	}
	/*public DifferentSubjects getDifferenceSubject() {
		return differenceSubject;
	}
	public void setDifferenceSubject(DifferentSubjects differenceSubject) {
		this.differenceSubject = differenceSubject;
	}*/
	public Integer getDifferenceSubject() {
		return differenceSubject;
	}
	public void setDifferenceSubject(Integer differenceSubject) {
		this.differenceSubject = differenceSubject;
	}
}