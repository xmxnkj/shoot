package com.szit.arbitrate.chat.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.szit.arbitrate.chat.entity.enumvo.RoomStateEnum;

/**
 * 
* @ProjectName:
* @ClassName: ChatRoom
* @Description:会议室实体类
* @author Administrator
* @date 2017年4月18日 下午4:11:49
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class ChatRoom extends DomainEntity{
	
	private String caseid;//id
	private String roomName;//房间名称
	private String openClientId;//开启用户id
	private Date openTime;//开启时间
	private String notice;//公告
	private RoomStateEnum roomState;//会议室状态
	private boolean videoRoomOpen=false;//视频聊天室是否开启
	private boolean voiceRoomOpen=false;//语音聊天室是否开启
	
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
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public RoomStateEnum getRoomState() {
		return roomState;
	}
	public void setRoomState(RoomStateEnum roomState) {
		this.roomState = roomState;
	}
	public boolean isVideoRoomOpen() {
		return videoRoomOpen;
	}
	public void setVideoRoomOpen(boolean videoRoomOpen) {
		this.videoRoomOpen = videoRoomOpen;
	}
	public boolean isVoiceRoomOpen() {
		return voiceRoomOpen;
	}
	public void setVoiceRoomOpen(boolean voiceRoomOpen) {
		this.voiceRoomOpen = voiceRoomOpen;
	}

}
