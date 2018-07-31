package com.szit.arbitrate.chat.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.szit.arbitrate.chat.entity.enumvo.ContentTypeEnum;

/**
 * 
* @ProjectName:
* @ClassName: MessagePush
* @Description:消息分发实体类
* @author Administrator
* @date 2017年4月18日 下午3:35:55
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class MessagePush extends DomainEntity{

	private static final long serialVersionUID = 8339938672332594389L;
	
	private String chatMessageId;//消息id
	private String content;//消息内容
	private ContentTypeEnum contentType;//内容类型
	private String sendClientId;//发送用户id
	private String sendClientName;//发送用户姓名
	private String sendClientImage;//发送用户头像
	private String sendClientDesc;//发送者备注
	private String chatRoomId;//聊天室id
	private String caseId;//id
	private String receiveClientId;//接收用户id
	private boolean readflag = false;//已读未读
	private Date pushTime;
	
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSendClientId() {
		return sendClientId;
	}
	public void setSendClientId(String sendClientId) {
		this.sendClientId = sendClientId;
	}
	public String getSendClientName() {
		return sendClientName;
	}
	public void setSendClientName(String sendClientName) {
		this.sendClientName = sendClientName;
	}
	public String getSendClientImage() {
		return sendClientImage;
	}
	public void setSendClientImage(String sendClientImage) {
		this.sendClientImage = sendClientImage;
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
	public Date getPushTime() {
		return pushTime;
	}
	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	public ContentTypeEnum getContentType() {
		return contentType;
	}
	public void setContentType(ContentTypeEnum contentType) {
		this.contentType = contentType;
	}
	public String getSendClientDesc() {
		return sendClientDesc;
	}
	public void setSendClientDesc(String sendClientDesc) {
		this.sendClientDesc = sendClientDesc;
	}
	
	/*@JsonIgnoreProperties({"account","identifyImgFile1","identifyImgFile2","identifyImgFile3","mediationAgencyId",
		"thirdPartyId","thirdParty","clientType","address","tel","ip","description","skill","clientState"})
	public Client getSendClient() {
		return sendClient;
	}
	public void setSendClient(Client sendClient) {
		this.sendClient = sendClient;
	}
	@JsonIgnoreProperties({"account","identifyImgFile1","identifyImgFile2","identifyImgFile3","mediationAgencyId",
		"thirdPartyId","thirdParty","clientType","address","tel","ip","description","skill","clientState"})
	public Client getReceiveClient() {
		return receiveClient;
	}
	public void setReceiveClient(Client receiveClient) {
		this.receiveClient = receiveClient;
	}*/
	
}
