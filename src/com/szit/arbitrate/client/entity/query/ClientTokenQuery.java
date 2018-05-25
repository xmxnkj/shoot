package com.szit.arbitrate.client.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.client.entity.enumvo.TerminalType;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: ClientTokenQuery
* @Description:用户用户唯一登录验证
* @author Administrator
* @date 2017年5月17日 下午3:18:27
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class ClientTokenQuery extends EntityQueryParam{
	
	private static final long serialVersionUID = -5051325701584174575L;
	
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

	public TerminalType getTerminalType() {
		return terminalType;
	}

	public void setTerminalType(TerminalType terminalType) {
		this.terminalType = terminalType;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
	
}
