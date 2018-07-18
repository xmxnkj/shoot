package com.szit.arbitrate.chat.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.szit.arbitrate.chat.entity.enumvo.ContentTypeEnum;
import com.szit.arbitrate.chat.entity.enumvo.RecTypeEnum;
import com.szit.arbitrate.client.entity.Client;

/**
 * 
* @ProjectName:
* @ClassName: ChatMessage
* @Description:聊天消息实体类
* @author Administrator
* @date 2017年4月18日 上午10:28:41
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class ChatMessage extends DomainEntity{
	
	private String sendClientId;//发送用户id
	private String sendClientDesc;//发送者备注
	private Client sendClient;//发送用户
	private String receiveId;//接受者id
	private String caseId;//id
	private RecTypeEnum recType;//发送对象类型
	private ContentTypeEnum contentType;//内容类型
	private String content;//消息内容
	private String oriFileName;//资源类型的原文件名
	private Date sendTime;//发送时间
	
	public String getSendClientDesc() {
		return sendClientDesc;
	}
	public void setSendClientDesc(String sendClientDesc) {
		this.sendClientDesc = sendClientDesc;
	}
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
	@JsonIgnoreProperties({"account","identifyImgFile1","identifyImgFile2","identifyImgFile3","mediationAgencyId",
		"thirdPartyId","thirdParty","clientType","address","tel","ip","description","skill","clientState"})
	public Client getSendClient() {
		return sendClient;
	}
	public void setSendClient(Client sendClient) {
		this.sendClient = sendClient;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	
	public RecTypeEnum getRecType() {
		return recType;
	}
	public void setRecType(RecTypeEnum recType) {
		this.recType = recType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ContentTypeEnum getContentType() {
		return contentType;
	}
	public void setContentType(ContentTypeEnum contentType) {
		this.contentType = contentType;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getOriFileName() {
		return oriFileName;
	}
	public void setOriFileName(String oriFileName) {
		this.oriFileName = oriFileName;
	}

}
