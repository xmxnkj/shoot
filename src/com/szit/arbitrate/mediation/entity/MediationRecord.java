package com.szit.arbitrate.mediation.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhm;
import com.hsit.common.utils.CustomDateSerializerYmdhms;
import com.szit.arbitrate.mediation.entity.enumvo.RecordStateEnum;
import com.szit.arbitrate.mediation.entity.enumvo.RecordTypeEnum;

/**
 * 
* @ProjectName:
* @ClassName: Record
* @Description:笔录实体类
* @author Administrator
* @date 2017年3月22日 下午3:59:15
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class MediationRecord extends DomainEntity{
	
	//员录入时间、地点、当事人、参加人和记录

	private static final long serialVersionUID = -1346341624034110458L;
	
	private String caseId;			//id
	private RecordTypeEnum recordTypeEnum;//记录类型
	private String recordContent;	//笔录内容
	private RecordStateEnum recordStateEnum;//状态
	
	private String involvedPerson;	//当事人
	private String joinPerson;		//参与人
	private String address;			//地点
	private Date recordTime;		//录入时间
	
	public RecordTypeEnum getRecordTypeEnum() {
		return recordTypeEnum;
	}
	public void setRecordTypeEnum(RecordTypeEnum recordTypeEnum) {
		this.recordTypeEnum = recordTypeEnum;
	}
	public String getAddress() {
		return address;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhm.class)
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getInvolvedPerson() {
		return involvedPerson;
	}
	public void setInvolvedPerson(String involvedPerson) {
		this.involvedPerson = involvedPerson;
	}
	public String getJoinPerson() {
		return joinPerson;
	}
	public void setJoinPerson(String joinPerson) {
		this.joinPerson = joinPerson;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getRecordContent() {
		return recordContent;
	}
	public void setRecordContent(String recordContent) {
		this.recordContent = recordContent;
	}
	public RecordStateEnum getRecordStateEnum() {
		return recordStateEnum;
	}
	public void setRecordStateEnum(RecordStateEnum recordStateEnum) {
		this.recordStateEnum = recordStateEnum;
	}
	
	
}
