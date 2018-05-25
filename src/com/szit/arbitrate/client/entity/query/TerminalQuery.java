package com.szit.arbitrate.client.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.client.entity.enumvo.TerminalType;

public class TerminalQuery extends EntityQueryParam{
	
	//用户
	private String clientId;
	//设备的ID号
	private String terminalCode;
	//类型
	private TerminalType terminalType;
	//设备型号
	private String spec;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	public TerminalType getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(TerminalType terminalType) {
		this.terminalType = terminalType;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}

}
