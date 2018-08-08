package com.szit.arbitrate.mediation.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.mediation.entity.enumvo.ApplyStateEnum;

/**
 * 
* @ProjectName:
* @ClassName: MediatorApplyQuery
* @Description:申请查询类
* @author Administrator
* @date 2017年3月22日 下午5:33:52
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class MediatorApplyQuery extends EntityQueryParam{
	
	private String applyClientId;//申请人id
	private Client applyClient;//申请人
	private String applyReason;//申请理由
	private Date applyTime;//申请日期
	
	private ApplyStateEnum applyState;//申请状态
	private Date auditTime;//审核时间
	public String getApplyClientId() {
		return applyClientId;
	}
	public void setApplyClientId(String applyClientId) {
		this.applyClientId = applyClientId;
	}
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
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	

}
