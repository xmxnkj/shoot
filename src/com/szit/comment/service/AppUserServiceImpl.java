package com.szit.comment.service;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsit.common.exceptions.ApplicationException;
import com.hsit.common.service.AppBaseServiceImpl;
import com.hsit.common.utils.net.HttpUtilityInstance;
import com.szit.comment.dao.AppUserDao;
import com.szit.comment.entity.AppUser;
import com.szit.comment.entity.query.AppUserQuery;

import net.sf.json.JSONObject;

@Service
public class AppUserServiceImpl extends AppBaseServiceImpl<AppUser, AppUserQuery> implements AppUserService {
	@Autowired
	private AppUserDao dao;

	@Override
	public AppUserDao getDao() {
		return dao;
	}

	@Override
	public String save(AppUser appUser) {
		
		if (appUser != null && appUser.getUserId() != null) {
			AppUser old = getAppUser(appUser.getUserId());
			if (old!=null) {
				appUser.setId(appUser.getId());
			}
		}else{
			throw new ApplicationException("非法使用！");
		}
		
		
		return super.save(appUser);
	}
	
	public AppUser getAppUser(int userId){
		AppUserQuery query = new AppUserQuery();
		query.setUserId(userId);
		return getEntity(query);
	}
	
	public AppUser getAppUserFromLocalOrRemote(int userId){
		AppUser appUser = getAppUser(userId);
		if (appUser!=null) {
			return appUser;
		}
		appUser = readFromRemoteServer(userId);
		if (appUser!=null) {
			saveSimple(appUser);
			return appUser;
		}
		return null;
	}
	
	
	private AppUser readFromRemoteServer(int userId){
		String url = "http://yao.cutv.com/plugin.php?id=cutv_shake:api_get_xmysz_user&appkey=61ae5fb23e2f37689dd6917380718388&uid" + userId;
		try {
			String result = HttpUtilityInstance.getUtility().doGet(url);
			JSONObject jsonObject = JSONObject.fromObject(result);
			if (jsonObject!=null && "ok".equals(jsonObject.optString("status"))) {
				JSONObject uo = jsonObject.optJSONObject("data");
				if (uo!=null) {
					AppUser appUser = new AppUser();
					appUser.setUserId(userId);
					appUser.setUserName(uo.optString("username"));
					appUser.setMobile(uo.optString("mobile"));
					appUser.setNickName(uo.optString("mobile"));
					appUser.setHeadImgUrl(uo.optString("avatar"));
					appUser.setEmail(uo.optString("email"));
					return appUser;
				}
			}
			
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
