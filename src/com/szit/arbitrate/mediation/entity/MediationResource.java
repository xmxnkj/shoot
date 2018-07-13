package com.szit.arbitrate.mediation.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;
import com.szit.arbitrate.mediation.entity.enumvo.CaseResTypeEnum;

/**
 * 
* @ProjectName:
* @ClassName: MediationResource
* @Description:证明材料 资源实体类
* @author Administrator
* @date 2017年3月22日 下午4:22:47
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class MediationResource extends DomainEntity{
	
	
	private static final long serialVersionUID = -5825654864758601630L;
	
	private String caseId;//id
	private String clientId;//上传用户id
	private String clientName;
	private String clientImage;
	private CaseResTypeEnum resType;//资源类型
	private Integer resseq; //资源排序序号
	private String resuploadfileid;//资源上传的文件ID 
	private String resuploadfilepath;//资源上传的文件路径 
	private Date rescreatedatatime;//创建时间
	
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public CaseResTypeEnum getResType() {
		return resType;
	}
	public void setResType(CaseResTypeEnum resType) {
		this.resType = resType;
	}
	public Integer getResseq() {
		return resseq;
	}
	public void setResseq(Integer resseq) {
		this.resseq = resseq;
	}
	public String getResuploadfileid() {
		return resuploadfileid;
	}
	public void setResuploadfileid(String resuploadfileid) {
		this.resuploadfileid = resuploadfileid;
	}
	public String getResuploadfilepath() {
		return resuploadfilepath;
	}
	public void setResuploadfilepath(String resuploadfilepath) {
		this.resuploadfilepath = resuploadfilepath;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getRescreatedatatime() {
		return rescreatedatatime;
	}
	public void setRescreatedatatime(Date rescreatedatatime) {
		this.rescreatedatatime = rescreatedatatime;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getClientImage() {
		return clientImage;
	}
	public void setClientImage(String clientImage) {
		this.clientImage = clientImage;
	}

}
