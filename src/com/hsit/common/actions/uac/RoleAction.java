/**
 * File Name: RoleAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.uac;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.uac.entity.Role;
import com.hsit.common.uac.entity.queryparam.RoleQuery;
import com.hsit.common.uac.service.RoleService;

/**
 * @ClassName:RoleAction
 * @date 2017-3-28 下午2:39:58
 * 
 */
@Component("roleAction")
@Scope("prototype")
public class RoleAction extends BaseAction<Role, RoleQuery> {
	
	private RoleService service;

	@Override
	public RoleService getService() {
		return service;
	}

	@Autowired
	public void setService(RoleService service) {
		this.service = service;
	}

	
	private String roleId;
	
	private String userIds;
	
	
	
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public void addRoleUsersJson(){
		if(!StringUtils.isEmpty(roleId) && !StringUtils.isEmpty(userIds)){
			service.addRoleUsers(roleId, userIds);
			outSuccessJson();
			return;
		}
		outFailJson("请正确使用！", null);
	}
	
	public void deleteRoleUsersJson() {
		if (!StringUtils.isEmpty(roleId) && !StringUtils.isEmpty(userIds)) {
			service.deleteRoleUsers(roleId, userIds);
			outSuccessJson();
			return;
		}
		outFailJson("请正确使用！", null);
	}
	
}
