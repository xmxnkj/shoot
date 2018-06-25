package com.szit.arbitrate.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;

/**
 * 
* @ClassName: ClientAuthorityGroup
* @author Administrator
* @date 2017年3月20日 下午3:25:28
* @UpdateUser: 
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class ClientAuthorityGroup extends DomainEntity{
	
	private String clientId;//用户id
	private String clientName;//用户姓名
	private Client client;
	private String authorityGroupId;//权限组id
	private AuthorityGroupEnum authorityGroupName;//权限组名
	private AuthorityGroup authorityGroup;
	
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
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
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
	public AuthorityGroup getAuthorityGroup() {
		return authorityGroup;
	}
	public void setAuthorityGroup(AuthorityGroup authorityGroup) {
		this.authorityGroup = authorityGroup;
	}

}
