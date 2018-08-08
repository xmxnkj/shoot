package com.szit.arbitrate.mediation.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.mediation.entity.enumvo.ProtocolStateEnum;

/**
 * 
* @ProjectName:
* @ClassName: ProtocolQuery
* @Description:协议书查询类
* @author Administrator
* @date 2017年3月22日 下午5:34:51
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class MediationProtocolQuery extends EntityQueryParam{
	
	private String caseId;//id
	private String title;//标题
	
	private String applyClientId;//申请人id 甲方
	private ProtocolStateEnum partAState;//甲方是否同意
	private boolean finalVersion=false;//是否终稿
	
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getApplyClientId() {
		return applyClientId;
	}
	public void setApplyClientId(String applyClientId) {
		this.applyClientId = applyClientId;
	}
	
	public ProtocolStateEnum getPartAState() {
		return partAState;
	}
	public void setPartAState(ProtocolStateEnum partAState) {
		this.partAState = partAState;
	}
	public boolean isFinalVersion() {
		return finalVersion;
	}
	public void setFinalVersion(boolean finalVersion) {
		this.finalVersion = finalVersion;
	}
	

}
