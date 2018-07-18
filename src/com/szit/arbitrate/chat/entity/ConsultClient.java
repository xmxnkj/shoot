package com.szit.arbitrate.chat.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;
import com.szit.arbitrate.chat.entity.enumvo.ContentTypeEnum;

/**
 * 
* @ClassName: ConsultClient  
* @Description: 用户咨询实体类  
* @author   
* @date 2017年6月20日 下午4:25:30  
* @Copyright
* @versions:1.0 
*
 */
@JsonIgnoreProperties({"name","description","sendClientId","displayOrder","createTime"})
public class ConsultClient extends DomainEntity{
	
	private String clientId;//咨询用户id
	private String clientImage;//
	private String clientName;
	
	private Date consultTime;//最新咨询时间
	private ContentTypeEnum contentType;
	private String sendClientId;//最新一条消息发送者id
	private String content;//最新一条消息内容
	private boolean readflag;//最新一条消息是否已读

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientImage() {
		return clientImage;
	}

	public void setClientImage(String clientImage) {
		this.clientImage = clientImage;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getConsultTime() {
		return consultTime;
	}

	public void setConsultTime(Date consultTime) {
		this.consultTime = consultTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isReadflag() {
		return readflag;
	}

	public void setReadflag(boolean readflag) {
		this.readflag = readflag;
	}

	public ContentTypeEnum getContentType() {
		return contentType;
	}

	public void setContentType(ContentTypeEnum contentType) {
		this.contentType = contentType;
	}

	public String getSendClientId() {
		return sendClientId;
	}

	public void setSendClientId(String sendClientId) {
		this.sendClientId = sendClientId;
	}

}
