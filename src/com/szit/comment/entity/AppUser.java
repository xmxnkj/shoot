package com.szit.comment.entity;

import org.apache.commons.lang.StringUtils;

import com.hsit.common.kfbase.entity.DomainEntity;

public class AppUser extends DomainEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4533248465253365314L;
	
	private Integer userId;
	private String userName;
	private String headImgUrl;	
	private String mobile;
	private String nickName;
	private String email;
	private String regip;
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRegip() {
		return regip;
	}
	public void setRegip(String regip) {
		this.regip = regip;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	
	public String getShowName(){
		return !StringUtils.isEmpty(nickName)?nickName:userName;
	}
}
