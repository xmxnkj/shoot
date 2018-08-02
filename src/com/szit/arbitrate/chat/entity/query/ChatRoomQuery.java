package com.szit.arbitrate.chat.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;

/**
 * 
* @ProjectName:
* @ClassName: ChatRoomQuery
* @Description:会议室查询类
* @author Administrator
* @date 2017年4月18日 下午4:19:24
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class ChatRoomQuery extends EntityQueryParam{
	
	private String caseid;//id
	private String roomName;//房间名称
	private String openClientId;//开启用户id
	private Date openTime;//开启时间
	
	public String getCaseid() {
		return caseid;
	}
	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getOpenClientId() {
		return openClientId;
	}
	public void setOpenClientId(String openClientId) {
		this.openClientId = openClientId;
	}
	public Date getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

}
