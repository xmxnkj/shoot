package com.szit.arbitrate.chat.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;

/**
 * 
* @ProjectName:
* @ClassName: ChatMessageQuery
* @Description:聊天消息查询类
* @author Administrator
* @date 2017年4月18日 下午4:16:23
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class ChatMessageQuery extends EntityQueryParam{

	private String sendClientId;//发送用户id
	private String receiveId;//接受者id
	private String caseId;//id
	private String content;//消息内容
	private Date sendTime;//发送时间
	
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getSendClientId() {
		return sendClientId;
	}
	public void setSendClientId(String sendClientId) {
		this.sendClientId = sendClientId;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
