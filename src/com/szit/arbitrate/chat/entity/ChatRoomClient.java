package com.szit.arbitrate.chat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;

/**
 * 
* @ProjectName:
* @ClassName: ChatRoomClient
* @Description:会议室-用户关联实体类
* @author Administrator
* @date 2017年4月20日 下午4:27:16
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class ChatRoomClient extends DomainEntity{

	private String caseid;//id
	private String chatRoomId;//会议室房间id
	private String clientId;//用户id
	private String clientName;
	private String clientImage;
	private String clientDesc;//发送者备注
	
	public String getClientDesc() {
		return clientDesc;
	}
	public void setClientDesc(String clientDesc) {
		this.clientDesc = clientDesc;
	}
	public String getCaseid() {
		return caseid;
	}
	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}
	public String getChatRoomId() {
		return chatRoomId;
	}
	public void setChatRoomId(String chatRoomId) {
		this.chatRoomId = chatRoomId;
	}
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
	public String getClientImage() {
		return clientImage;
	}
	public void setClientImage(String clientImage) {
		this.clientImage = clientImage;
	}
	
}
