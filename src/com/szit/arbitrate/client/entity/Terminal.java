package com.szit.arbitrate.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.szit.arbitrate.client.entity.enumvo.TerminalType;

@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class Terminal extends DomainEntity{
	
	private String clientId;
	private String terminalCode;
	private TerminalType terminalType;
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
