/**
 * File Name: UserAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.uac;

import java.io.File;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;











import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.admin.jdbc.QueryInfo;
import com.hsit.Setting;
import com.hsit.common.MD5Util;
import com.hsit.common.actions.BaseAction;
import com.hsit.common.actions.Struts2BaseAction;
import com.hsit.common.actions.uac.models.UserSetting;
import com.hsit.common.exceptions.ApplicationException;
import com.hsit.common.kfbase.service.ConstSetService;
import com.hsit.common.kfbase.service.ObjectFileService;
import com.hsit.common.service.AppBaseService;
import com.hsit.common.uac.entity.Department;
import com.hsit.common.uac.entity.Position;
import com.hsit.common.uac.entity.PositionGrade;
import com.hsit.common.uac.entity.User;
import com.hsit.common.uac.entity.UserState;
import com.hsit.common.uac.entity.queryparam.PositionGradeQuery;
import com.hsit.common.uac.entity.queryparam.PositionQuery;
import com.hsit.common.uac.entity.queryparam.UserQuery;
import com.hsit.common.uac.service.PositionGradeService;
import com.hsit.common.uac.service.PositionService;
import com.hsit.common.uac.service.UserService;
import com.hsit.common.uac.service.UserServiceImpl;
import com.hsit.common.utils.Converter;
import com.hsit.common.utils.EntityUtils;
import com.hsit.common.utils.JsonMapper;
import com.opensymphony.xwork2.ActionSupport;
import com.szit.arbitrate.api.client.controller.impl.ApiClientControllerImpl;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.mediation.entity.DifferentSubjects;
import com.szit.comment.dao.impl.QnmUserDaoImpl;
import com.szit.comment.entity.QnmUser;

import freemarker.core.ReturnInstruction.Return;

/**
 * @ClassName:UserAction
 * @date 2016-6-15 下午3:35:19
 * 
 */
@Component("userAction")
@Scope("prototype")
public class UserAction extends BaseAction<User, UserQuery> {

	private UserService service;

	@Resource
	private QnmUserDaoImpl qnmUserDaoImpl;
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.hsit.common.actions.BaseAction#getService()
	 */
	@Override
	protected AppBaseService getService() {
		return service;
	}

	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}
	
	private PositionService positionService;

	@Autowired
	public void setPositionService(PositionService positionService) {
		this.positionService = positionService;
	}
	
	private PositionGradeService positionGradeService;

	@Autowired
	public void setPositionGradeService(PositionGradeService positionGradeService) {
		this.positionGradeService = positionGradeService;
	}

	private List<Position> positions;
	
	public List<Position> getPositions() {
		return positions;
	}

	public void setPositions(List<Position> positions) {
		this.positions = positions;
	}
	
	private List<PositionGrade> positionGrades;

	public List<PositionGrade> getPositionGrades() {
		return positionGrades;
	}

	public void setPositionGrades(List<PositionGrade> positionGrades) {
		this.positionGrades = positionGrades;
	}

	@Override
	protected void initAddForm() {
		initPositions();
		setEntity(new User());
		
		
		//临时更新数据
//		List<User> users = service.getEntities(null);
//		for (User user : users) {
//			if(user.getLeader() != null){
//				List<String> leaders = new ArrayList<>();
//				leaders.add(user.getLeader().getId());
//				service.setUserLeaders(user.getId(), leaders);
//				user.setLeaderName(user.getLeader().getName());
//				service.saveSimple(user);
//			}
//		}
	}
	
	@Override
	protected void initEditForm(){
		initPositions();
		if(getEntity()!=null && !StringUtils.isEmpty(getEntity().getId())){
			List<User> leaders = service.getUserLeaders(getEntity().getId());
			userLeaderIds = EntityUtils.getEntityIdStrings(leaders);
			userLeaderNames = EntityUtils.getEntityNameStrings(leaders);
			
			List<User> deputyLeaders = service.getUserDeputyLeaders(getEntity().getId());
			userDeputyLeaderIds = EntityUtils.getEntityIdStrings(deputyLeaders);
			userDeputyLeaderNames = EntityUtils.getEntityNameStrings(deputyLeaders);
			
			List<User> relaUsers = service.getUserRelaUsers(getEntity().getId());
			relaUserIds= EntityUtils.getEntityIdStrings(relaUsers);
			relaUserNames=EntityUtils.getEntityNameStrings(relaUsers);
		}
	}
	
	private void initPositions(){
		positions = positionService.getEntities(new PositionQuery());
		positionGrades = positionGradeService.getEntities(new PositionGradeQuery());
	}

	@Override
	protected JSONObject convertEntityToJson() {
		return getDepartmentJson(getEntity());
	}

	
	/* (non-Javadoc)
	 * @see com.hsit.common.actions.BaseAction#initEntityForEdit()
	 */
	@Override
	protected void initEntityForEdit() {
		if (getEntity() != null) {
			getEntity().setLeaderName(userLeaderNames);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.hsit.common.actions.BaseAction#afterEntitySaved()
	 */
	@Override
	protected void afterEntitySaved() {
		if (getEntity()!=null && !StringUtils.isEmpty(getEntity().getId())) {
			service.setUserLeaders(getEntity().getId(), EntityUtils.getIdStringList(userLeaderIds));
			service.setUserDeputyLeaders(getEntity().getId(), EntityUtils.getIdStringList(userDeputyLeaderIds));
			service.setUserRelaUsers(getEntity().getId(), EntityUtils.getIdStringList(relaUserIds));
		}
	}
	
	private JSONObject getDepartmentJson(User glt) {
		JSONObject js = new JSONObject();

		if (glt != null) {
			js.element("id", glt.getId());
			js.element("name", glt.getName());
			js.element("contact", glt.getContact());
			js.element("description", glt.getDescription());
			js.element("displayOrder", glt.getDisplayOrder());
			js.element("loginAccount", glt.getLoginAccount());
			js.element("email", glt.getEmail());
			js.element("gender", glt.isGender());
			js.element("bornDate", formatDate(glt.getBornDate()));
			js.element("department", glt.getDepartment() != null ? glt
					.getDepartment().getName() : "");
			js.element("departmentId", glt.getDepartment() != null ? glt
					.getDepartment().getId() : "");
			js.element("userState", glt.getUserState() != null ? glt
					.getUserState().ordinal() : 0);
			//js.element("leader", glt.getLeader()!=null?glt.getLeader().getName():"");
			js.element("leader", glt.getLeaderName());
			js.element("positionName", glt.getPosition()!=null?glt.getPosition().getName():"");
		}
		return js;
	}

	@Override
	protected JSONArray convertEntityListToJson() {
		JSONArray jsArray = new JSONArray();

		if (getEntities() != null) {
			for (User glt : getEntities()) {
				jsArray.add(getDepartmentJson(glt));
			}
		}

		return jsArray;
	}
	
	@Override
	public String showEditView() {
		User entity = (User) getService().getEntityById(getId());
		return super.showEditView();
	}

	public void validateAccountExist() {
		try {
			String account = getEntity() != null ? getEntity().getLoginAccount() : "";
			User et = service.getUser(account);
			if(et != null && !StringUtils.isEmpty(et.getId()) && !et.getId().equals(getId()))
				outJson("false");
			else
				outJson("true");
		} catch (Exception e) {
			try {
				e.printStackTrace();
				outJson("true");
			} catch (Exception e1) {
			}
		}
	}	

	public String showLogin(){
		User user = getLoginUser();
		if (user != null) {
			return "main";
		}
		return SUCCESS;
	}
	
	private User cookieUser;
	
	@Override
	public User getLoginUser() {
		QnmUser user = (QnmUser) getSession().getAttribute("user");
		if (user==null) {
			//账号为空返回登录页面
			if(super.getEntity() ==null || super.getEntity().getLoginAccount() == null || "".equals(super.getEntity().getLoginAccount())){
				return null;
			}
			//密码为空返回登录页面
			if(super.getEntity() ==null || super.getEntity().getLoginPasswd()==null || "".equals(super.getEntity().getLoginPasswd())){
				return null;
			}
//			user = service.login(super.getEntity().getLoginAccount(), MD5Util.MD5(super.getEntity().getLoginPasswd()));
			QueryInfo qi = new QueryInfo();
			qi.setMeSelect("QnmUser.login_account,QnmUser.user_state,QnmUser.login_passwd");
			qi.getSearch().put("loginAccount", super.getEntity().getLoginAccount());
		 	try {
				user = qnmUserDaoImpl.getEntity(qi, QnmUser.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (user != null) {
				 if( user.getUserState() == UserState.Normal && !StringUtils.isEmpty(user.getLoginPasswd()) && user.getLoginPasswd().equals(MD5Util.MD5(super.getEntity().getLoginPasswd()))){
					setLoginUser(user);
					getUserHelper().setSession(getSession());
				 }else if(user.getUserState() != UserState.Normal)
			            throw new ApplicationException("\u5E10\u53F7\u5DF2\u88AB\u9501\u5B9A\uFF0C\u8BF7\u8054\u7CFB\u7BA1\u7406\u5458\uFF01");
			}
		}
		return user;
	}
	public User getCookieUser() {
		Cookie cookie = getCookieByName("loginAccount");
		if(cookie != null){
			String loginAccount = cookie.getValue();
			if(!StringUtils.isEmpty(loginAccount)){
				cookieUser = new User();
				cookieUser.setLoginAccount(loginAccount);
				cookie = getCookieByName("loginPasswd");
				if (cookie != null) {
					cookieUser.setLoginPasswd(cookie.getValue());
				}
			}
			
		}
		
		return cookieUser;
	}
	
	
	public String login() {
		User user = getLoginUser();
		if (user != null) {
			return SUCCESS;
		}
		
		if(getEntity() != null){
			if (doLogin()) {
				return SUCCESS;
			}
		}
		loginFail=true;
		return ERROR;
	}
	
	private String FailCountAttributeName = "FailCount"; 
	private int addFailCount(){
		int failCount=getFailCount()+1;
		getSession().setAttribute(FailCountAttributeName, failCount);
		return failCount;
	}
	
	public int getFailCount(){
		int failCount = 0;
		Object obj = getSession().getAttribute(FailCountAttributeName);
		if (obj != null) {
			failCount = (int)obj;
		}
		return failCount;
	}
	
	private boolean loginFail = false;
	

	public boolean isLoginFail() {
		return loginFail;
	}

	private int failCount;
	private int failCountSetting;
	
	public int getFailCountSetting() {
		return failCountSetting;
	}
	
	private int leftCount;

	public int getLeftCount() {
		return leftCount;
	}

	private boolean doLogin(){
		//try{
			User user = service.login(getEntity().getLoginAccount(), getEntity().getLoginPasswd());
			if (user != null) {
				clearSession();
				setLoginUser(user);
				getUserHelper().setSession(getSession());
			}else {
				failCount = getFailCount();
				failCountSetting = Converter.toInt(Setting.getSetting(Setting.FAIL_COUNT_SETTING_NAME, null));
				if(failCount<failCountSetting){
					failCount = addFailCount();
					if (failCount>=failCountSetting) {
						service.lockUser(getEntity().getLoginAccount());
					}
				}
				leftCount = failCountSetting-failCount;
			}
			return user != null;
//		}catch(ApplicationException e1){
//			e1.printStackTrace();
//			setAppException(e1);
//		}
		//return false;
	}
	
	
	
	public void loginJson(){
		if (getEntity()!=null) {
			if(doLogin()){
				loadUserSetting();
				if (userSetting.getSavePasswd()!=null && userSetting.getSavePasswd().booleanValue()) {
					Cookie cookie = new Cookie("loginAccount", getLoginUser().getLoginAccount());
					int days = 1;
					if (userSetting.getSavePeriod() != null) {
						days = userSetting.getSavePeriod();
					}
					int second = days * 24 * 3600;
					cookie.setMaxAge(second);
					cookie.setPath("/");
					getResponse().addCookie(cookie);
					cookie = new Cookie("loginPasswd", getEntity().getLoginPasswd());
					cookie.setMaxAge(second);
					cookie.setPath("/");
					getResponse().addCookie(cookie);
				}
								
				JSONObject jsonObject = new JSONObject();
				jsonObject.element("success", true);
				jsonObject.element("userName", getLoginUser().getName());
				
				outJson(jsonObject);
				//outJson("success");
				return;
			}
		}
		outJson("fail");
	}
		
	public String logout() {
		
		clearSession();
		
		clearCookie();
		
		return SUCCESS;
	}
	
	private void clearCookie(){
		Cookie cookie = new Cookie("loginAccount", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		addCookie(cookie);
		cookie = new Cookie("loginPasswd", "");
		cookie.setMaxAge(0);
		cookie.setPath("/");
		addCookie(cookie);
	}
	
	public void logoutjson(){
		clearSession();
		clearCookie();
	}
	
	private void clearSession(){
		Enumeration<String> keys = getSession().getAttributeNames();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			getSession().removeAttribute(key);
		}
		getSession().removeAttribute("user");
	}
	
	
	public void savePasswd(){
		if (!StringUtils.isEmpty(oldPasswd)
				&& !StringUtils.isEmpty(passwd)
				) {
			service.setPasswd(getLoginUserId(), oldPasswd, passwd);
			outSuccessJson();
		}
	}
	
	private String oldPasswd;
	private String passwd;
	private String passwdConfirm;
	
	public String getOldPasswd() {
		return oldPasswd;
	}

	public void setOldPasswd(String oldPasswd) {
		this.oldPasswd = oldPasswd;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getPasswdConfirm() {
		return passwdConfirm;
	}

	public void setPasswdConfirm(String passwdConfirm) {
		this.passwdConfirm = passwdConfirm;
	}

	private Boolean isSelectUser = true;

	private Boolean isSelectRole = true;
	
	private Boolean isSelectPosition = true;
	
	private Boolean isSelectActivityUserSelector = true;
	
	private Boolean isSelectDepartmentActor = true;
	
	
	public Boolean getIsSelectDepartmentActor() {
		return isSelectDepartmentActor;
	}

	public void setIsSelectDepartmentActor(Boolean isSelectDepartmentActor) {
		this.isSelectDepartmentActor = isSelectDepartmentActor;
	}

	public Boolean getIsSelectUser() {
		return isSelectUser;
	}

	public void setIsSelectUser(Boolean isSelectUser) {
		this.isSelectUser = isSelectUser;
	}

	public Boolean getIsSelectRole() {
		return isSelectRole;
	}

	public void setIsSelectRole(Boolean isSelectRole) {
		this.isSelectRole = isSelectRole;
	}

	public Boolean getIsSelectPosition() {
		return isSelectPosition;
	}

	public void setIsSelectPosition(Boolean isSelectPosition) {
		this.isSelectPosition = isSelectPosition;
	}

	public Boolean getIsSelectActivityUserSelector() {
		return isSelectActivityUserSelector;
	}

	public void setIsSelectActivityUserSelector(Boolean isSelectActivityUserSelector) {
		this.isSelectActivityUserSelector = isSelectActivityUserSelector;
	}
	
	
	private String userLeaderIds;
	private String userLeaderNames;
	private String userDeputyLeaderNames;
	private String userDeputyLeaderIds;
	private String relaUserNames;
	private String relaUserIds;
	
	public String getUserDeputyLeaderNames() {
		return userDeputyLeaderNames;
	}

	public void setUserDeputyLeaderNames(String userDeputyLeaderNames) {
		this.userDeputyLeaderNames = userDeputyLeaderNames;
	}

	public String getUserDeputyLeaderIds() {
		return userDeputyLeaderIds;
	}

	public void setUserDeputyLeaderIds(String userDeputyLeaderIds) {
		this.userDeputyLeaderIds = userDeputyLeaderIds;
	}

	public String getRelaUserNames() {
		return relaUserNames;
	}

	public void setRelaUserNames(String relaUserNames) {
		this.relaUserNames = relaUserNames;
	}

	public String getRelaUserIds() {
		return relaUserIds;
	}

	public void setRelaUserIds(String relaUserIds) {
		this.relaUserIds = relaUserIds;
	}

	public String getUserLeaderNames() {
		return userLeaderNames;
	}

	public void setUserLeaderNames(String userLeaderNames) {
		this.userLeaderNames = userLeaderNames;
	}

	public String getUserLeaderIds() {
		return userLeaderIds;
	}

	public void setUserLeaderIds(String userLeaderIds) {
		this.userLeaderIds = userLeaderIds;
	}
	
	public String showChangeLeader() {
		if (getEntity()!=null && !StringUtils.isEmpty(getEntity().getId())) {
			setEntity(service.getEntityById(getEntity().getId()));
		}
		return SUCCESS;
	}
	
	public void changeLeader() {
		if(getEntity()!=null 
				&& !StringUtils.isEmpty(getEntity().getId())
				&& !StringUtils.isEmpty(userLeaderIds)
				){
			service.changeUserLeader(getEntity().getId(), userLeaderIds);
			outSuccessJson();
			return;
		}
		outFailJson("更改失败！", null);
	}
	
	public String showUserInfo(){
		currentUser = getLoginUser();
		
		return SUCCESS;
	}
	
	public void saveUserInfoJson() {
		
		if (currentUser != null && getLoginUser() != null) {
			User user = getLoginUser();
			user.setGender(currentUser.isGender());
			user.setBornDate(currentUser.getBornDate());
			user.setAddress(currentUser.getAddress());
			user.setIdCard(currentUser.getIdCard());
			user.setLoginPasswd("");
			service.save(user);
			outSuccessJson();
			return;
		}
		outFailJson("非法使用", null);
	}
	
	private User currentUser;
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
	@Autowired
	private ObjectFileService objectFileService;
	public String upload(){
		try{
			if(uploadedFile != null){
				String nid = objectFileService.saveFile(getLoginUserId(), uploadedFile, uploadFileName);
				setId(nid);
				User user = getLoginUser();
				user.setPicId(nid);
				user.setLoginPasswd("");
				service.save(user);
			}
			
			isSuccess = true;
		}catch(Exception exception){
			exception.printStackTrace();
			isSuccess = false;
		}
		
		return SUCCESS;
	}
	
	private Boolean isSuccess;

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	
	
private String uploadedFileFileName;
	
	public String getUploadedFileFileName() {
		return uploadedFileFileName;
	}

	public void setUploadedFileFileName(String uploadedFileFileName) {
		this.uploadedFileFileName = uploadedFileFileName;
	}

	private File uploadedFile;
	
	public File getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
private File upload;
	
	public File getUpload() {
		return upload;
	}
	
	private String uploadFileName;

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	
	
	public String showSetPasswd(){
		loadUserSetting();
		return SUCCESS;
	}
	
	@Autowired
	private ConstSetService constSetService;
	public void saveUserSetting(){
		if (userSetting != null) {
			constSetService.saveSetting(getLoginUserId(), userSetting.getSavePasswdSettingName(), userSetting.getSavePasswd()!=null?String.valueOf(userSetting.getSavePasswd()): "false");
			constSetService.saveSetting(getLoginUserId(), userSetting.getSavePeriodSettingName(), userSetting.getSavePeriod()!=null?String.valueOf(userSetting.getSavePeriod()):"0");
			outSuccessJson();
			return;
		}
		outFailJson("非法使用！", null);
	}
	
	private void loadUserSetting(){
		userSetting = new UserSetting();
		userSetting.setSavePasswd(Converter.toBoolean(constSetService.getObjectSetting(getLoginUserId(), userSetting.getSavePasswdSettingName())));
		userSetting.setSavePeriod(Converter.toInt(constSetService.getObjectSetting(getLoginUserId(), userSetting.getSavePeriodSettingName())));
	}
	
	
	private UserSetting userSetting;

	public UserSetting getUserSetting() {
		return userSetting;
	}

	public void setUserSetting(UserSetting userSetting) {
		this.userSetting = userSetting;
	}

}
