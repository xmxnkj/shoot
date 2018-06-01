package com.szit.arbitrate.client.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: AuthorityGroupQuery
* @Description:权限组查询类
* @author yuyb
* @date 2017年3月17日 下午5:04:38
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class AuthorityGroupQuery extends EntityQueryParam{
	
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
