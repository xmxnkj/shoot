package com.szit.comment.service;

import com.hsit.common.service.AppBaseService;
import com.szit.comment.entity.AppUser;
import com.szit.comment.entity.query.AppUserQuery;

public interface AppUserService extends AppBaseService<AppUser, AppUserQuery>{
	public AppUser getAppUser(int userId);
	
	public AppUser getAppUserFromLocalOrRemote(int userId);
}
