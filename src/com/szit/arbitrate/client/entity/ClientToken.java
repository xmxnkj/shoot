package com.szit.arbitrate.client.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.szit.arbitrate.client.entity.enumvo.TerminalType;

/**
 * 
* @ClassName: ClientToken
* @Description:用户用户唯一登录验证
* @author Administrator
* @date 2017年5月17日 下午3:19:57
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class ClientToken extends DomainEntity{

	private static final long serialVersionUID = -6139049651664981605L;
	
	private String clientId;//用户id
	private String sessionId;//
	private String uuid;//设备码uuid
	private TerminalType terminalType;//类型
	
	private boolean online;//是否登录
	private Date loginTime;//登录时间
	
	public Date getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	public TerminalType getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(TerminalType terminalType) {
		this.terminalType = terminalType;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
