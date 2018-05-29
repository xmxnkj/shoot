package com.szit.arbitrate.client.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;

/**
 * 
* @ClassName: ClientNotOnlineNotifyQuery  
* @Description:召集被申述人未回复通知实体查询类   
* @author   
* @date 2017年6月16日 下午2:57:49  
* @Copyright
* @versions:1.0 
*
 */
public class ClientNotOnlineNotifyQuery extends EntityQueryParam{
	
	private String clientId;//用户id
	private String clientName;//用户姓名
	private Date gatherTime;//召集时间
	
	private String caseId;//案件id
	private String caseExplain;//案件申述点
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public Date getGatherTime() {
		return gatherTime;
	}
	public void setGatherTime(Date gatherTime) {
		this.gatherTime = gatherTime;
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
	public void setCaseExplain(String caseExplain) {
		this.caseExplain = caseExplain;
	}

}
