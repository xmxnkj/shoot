package com.szit.arbitrate.client.service;

import com.hsit.common.service.BaseService;
import com.szit.arbitrate.client.entity.AuthorityGroup;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;
import com.szit.arbitrate.client.entity.query.AuthorityGroupQuery;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: AuthorityGroupService
* @Description:权限组实体业务接口类
* @author yuyb
* @date 2017年3月17日 下午5:23:38
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface AuthorityGroupService extends BaseService<AuthorityGroup, AuthorityGroupQuery>{
	
	public AuthorityGroup getAuthorityGroup(AuthorityGroupEnum authorityGroupEnum);

}
