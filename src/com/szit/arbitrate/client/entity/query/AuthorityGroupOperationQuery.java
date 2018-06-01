package com.szit.arbitrate.client.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: AuthorityGroupOperationQuery
* @Description:权限组操作查询类
* @author yuyb
* @date 2017年3月17日 下午5:05:03
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class AuthorityGroupOperationQuery extends EntityQueryParam{
	
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
