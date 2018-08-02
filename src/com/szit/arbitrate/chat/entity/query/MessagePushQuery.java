package com.szit.arbitrate.chat.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;

/**
 * 
* @ProjectName:
* @ClassName: MessagePushQuery
* @Description:消息分发查询类
* @author Administrator
* @date 2017年4月18日 下午4:19:40
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class MessagePushQuery extends EntityQueryParam{
	
	private String chatMessageId;//消息id
	private String sendClientId;//发送用户id
	private String chatRoomId;//聊天室id
	private String caseId;//id
	private String receiveClientId;//接收用户id
	private boolean readflag = false;//已读未读
	
	
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getChatMessageId() {
		return chatMessageId;
	}
	public void setChatMessageId(String chatMessageId) {
		this.chatMessageId = chatMessageId;
	}
	public String getSendClientId() {
		return sendClientId;
	}
	public void setSendClientId(String sendClientId) {
		this.sendClientId = sendClientId;
	}
	public String getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
	public String getReceiveClientId() {
		return receiveClientId;
	}
	public void setReceiveClientId(String receiveClientId) {
		this.receiveClientId = receiveClientId;
	}
	public boolean isReadflag() {
		return readflag;
	}
	public void setReadflag(boolean readflag) {
		this.readflag = readflag;
	}

}
