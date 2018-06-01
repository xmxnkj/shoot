package com.szit.arbitrate.client.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;

public class ClientAuthorityGroupQuery extends EntityQueryParam{
	
	private String clientId;//用户id
	private String clientName;//用户姓名
	private String authorityGroupId;//权限组id
	private AuthorityGroupEnum authorityGroupName;//权限组名
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getAuthorityGroupId() {
		return authorityGroupId;
	}
	public void setAuthorityGroupId(String authorityGroupId) {
		this.authorityGroupId = authorityGroupId;
	}
	public AuthorityGroupEnum getAuthorityGroupName() {
		return authorityGroupName;
	}
	public void setAuthorityGroupName(AuthorityGroupEnum authorityGroupName) {
		this.authorityGroupName = authorityGroupName;
	}

}
