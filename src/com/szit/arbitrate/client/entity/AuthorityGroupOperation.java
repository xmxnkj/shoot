package com.szit.arbitrate.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: AuthorityGroupAction
* @Description:权限组-操作映射实体类
* @author yuyb
* @date 2017年3月17日 下午3:41:50
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class AuthorityGroupOperation extends DomainEntity{
	
	//权限组id
	private String authorityGroupId;
	private String authorityGroupName;
	//操作id
	private String clientOperationId;
	private String clientOperationName;
	//用户id
	private String clientId;
	private String clientName;
	
	public String getAuthorityGroupId() {
		return authorityGroupId;
	}
	public void setAuthorityGroupId(String authorityGroupId) {
		this.authorityGroupId = authorityGroupId;
	}
	
	public String getClientOperationId() {
		return clientOperationId;
	}
	public void setClientOperationId(String clientOperationId) {
		this.clientOperationId = clientOperationId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getAuthorityGroupName() {
		return authorityGroupName;
	}
	public void setAuthorityGroupName(String authorityGroupName) {
		this.authorityGroupName = authorityGroupName;
	}
	public String getClientOperationName() {
		return clientOperationName;
	}
	public void setClientOperationName(String clientOperationName) {
		this.clientOperationName = clientOperationName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
}
