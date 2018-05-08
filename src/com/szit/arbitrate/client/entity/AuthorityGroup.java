package com.szit.arbitrate.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;

/**
 * 
 * 
* @ProjectName:arbitrate
* @ClassName: AuthorityGroup
* @Description:权限组实体类
* @author yuyb
* @date 2017年3月17日 下午3:29:49
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class AuthorityGroup extends DomainEntity{
	
	//权限组名称
	private AuthorityGroupEnum authorityGroupName;
	//权限描述
	private String authorityDescription;
	
	public AuthorityGroupEnum getAuthorityGroupName() {
		return authorityGroupName;
	}
	public void setAuthorityGroupName(AuthorityGroupEnum authorityGroupName) {
		this.authorityGroupName = authorityGroupName;
	}
	
	public String getAuthorityDescription() {
		return authorityDescription;
	}
	public void setAuthorityDescription(String authorityDescription) {
		this.authorityDescription = authorityDescription;
	}
	
}
