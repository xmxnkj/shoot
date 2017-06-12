package com.szit.comment.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;

public class AppUserQuery extends EntityQueryParam{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5765990016136704711L;
	
	private String userName;
	private Integer userId;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
