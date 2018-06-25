package com.szit.arbitrate.client.entity;

import com.hsit.common.kfbase.entity.DomainEntity;

public class NeteaseClient extends DomainEntity{
	
	private static final long serialVersionUID = -4661684027315337682L;
	
	private String clientId;//我们的用户id
	private String accid;//
	private String name;//昵称
	private String token;//口令
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getAccid() {
		return accid;
	}
	public void setAccid(String accid) {
		this.accid = accid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
