package com.szit.arbitrate.chat.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;

/**
 * 
* @ProjectName:
* @ClassName: ChatRoomClientQuery
* @Description:会议室-用户关联实体查询类
* @author Administrator
* @date 2017年4月20日 下午4:28:59
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class ChatRoomClientQuery extends EntityQueryParam{

	private String caseid;//id
	private String chatRoomId;//会议室房间id
	private String clientId;//用户id
	private boolean equal = true;//是否等于,辅助用
	
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
	public boolean isEqual() {
		return equal;
	}
	public void setEqual(boolean equal) {
		this.equal = equal;
	}
	
}
