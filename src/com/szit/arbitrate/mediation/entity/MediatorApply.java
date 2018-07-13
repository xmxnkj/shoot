package com.szit.arbitrate.mediation.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhm;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.mediation.entity.enumvo.ApplyStateEnum;

/**
 * 
* @ProjectName:
* @ClassName: MediatorApply
* @Description:申请成为员实体类
* @author Administrator
* @date 2017年3月22日 下午5:21:32
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class MediatorApply extends DomainEntity{
	
	private static final long serialVersionUID = -401722552236088009L;
	
	private String applyClientId;//申请人id
	private Client applyClient;//申请人
	private String applyReason;//申请理由
	private Date applyTime;//申请日期
	
	private ApplyStateEnum applyState;//申请状态
	private Date auditTime;//审核时间
	private String notifyMsg;//通知内容
	
	public String getApplyClientId() {
		return applyClientId;
	}
	public void setApplyClientId(String applyClientId) {
		this.applyClientId = applyClientId;
	}
	@JsonIgnoreProperties({"account","identifyImgFile1","identifyImgFile2","identifyImgFile3","auditInfo","mediationAgencyId",
		"thirdPartyId","thirdParty","clientType","address","tel","ip","description","skill","clientState"})
	public Client getApplyClient() {
		return applyClient;
	}
	public void setApplyClient(Client applyClient) {
		this.applyClient = applyClient;
	}
	public String getApplyReason() {
		return applyReason;
	}
	public void setApplyReason(String applyReason) {
		this.applyReason = applyReason;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhm.class)
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public ApplyStateEnum getApplyState() {
		return applyState;
	}
	public void setApplyState(ApplyStateEnum applyState) {
		this.applyState = applyState;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhm.class)
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getNotifyMsg() {
		return notifyMsg;
	}
	public void setNotifyMsg(String notifyMsg) {
		this.notifyMsg = notifyMsg;
	}
	
	
}