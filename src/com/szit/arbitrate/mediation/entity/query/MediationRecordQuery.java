package com.szit.arbitrate.mediation.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.mediation.entity.enumvo.RecordTypeEnum;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: RecordQuery
* @Description:笔录查询类
* @author Administrator
* @date 2017年3月22日 下午5:36:15
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class MediationRecordQuery extends EntityQueryParam{
	
	private String caseId;			//案件id
	private String recordContent;	//笔录内容
	private RecordTypeEnum recordTypeEnum;//记录类型
	
	private String involvedPerson;	//当事人
	private String joinPerson;		//参与人
	private String address;			//地点
	private Date recordTime;		//录入时间
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
	}
	public RecordTypeEnum getRecordTypeEnum() {
		return recordTypeEnum;
	}
	public void setRecordTypeEnum(RecordTypeEnum recordTypeEnum) {
		this.recordTypeEnum = recordTypeEnum;
	}
	
}