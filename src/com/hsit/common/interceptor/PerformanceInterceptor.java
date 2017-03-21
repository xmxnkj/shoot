/**
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.interceptor;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsit.common.actions.DomainAction;
import com.hsit.common.actions.Struts2BaseAction;
import com.hsit.common.exceptions.ApplicationException;
import com.hsit.common.uac.entity.User;
import com.hsit.common.uac.service.AceOperationService;
import com.hsit.common.uac.service.UserService;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.config.entities.ActionConfig;
import com.opensymphony.xwork2.interceptor.ExceptionMappingInterceptor;
import com.opensymphony.xwork2.util.ValueStack;


/**
 * @ClassName:PerformanceInterceptor
 * @date 2017-3-8 上午10:04:37
 * 
 */
public class PerformanceInterceptor extends ExceptionMappingInterceptor{
	
	
	private Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);
	
	private AceOperationService aceOperationService;
	
	@Autowired
	public void setAceOperationService(AceOperationService aceOperationService) {
		this.aceOperationService = aceOperationService;
	}

//	private static LogService logService;
//	
//	private static void initLogService() {
//		if(logService==null)
//			PerformanceInterceptor.logService = (LogService)ApplicationContextUtils.getBean("logService");
//	}

	private String message;
	
	private boolean isAdmin(String account){
		return "admin".equals(account);
	}
	
	
	private String urlPath;
	public boolean validateUrlAce(ActionConfig config){
		
		if (isAdmin(getLoginUserAccount())) {
			return true;
		}
		
		//HttpServletRequest request = ServletActionContext.getRequest();
		//String path = request.getServletPath();
		//String queryString = request.getQueryString();
		urlPath = getContextUrl(); //path + "?" + (!StringUtils.isEmpty(queryString)?queryString:"");
		
		//是否不需要登录
		if (isPublicUrl(urlPath)) {
			return true;
		}
		
		//是否不需要登录（手机用）
		if (isPhoneUrl(urlPath)) {
			return true;
		}
		
		//判断用户是否已登录
		if (getLoginUser()==null) {
			return false;
		}
		
		//判断是否不需要权限
		if (isNoAceUrl(urlPath)) {
			return true;
		}
		
		//判断是否不需要权限(手机)
		if (isNoAceUrlPhone(urlPath)) {
			return true;
		}
		
		//测试阶段去掉，正式时启用
		if (!aceOperationService.userHasActionAce(getLoginUserId(), config.getName(), config.getPackageName())) {
			throw new ApplicationException("用户没有权限使用本功能！");
		}
		if(!aceOperationService.userHasUrlAce(getLoginUserId(), urlPath)){
			throw new ApplicationException("用户没有权限使用本功能！");
		}
		
		return true;
	}
	
	/**
	 * 不需要权限
	 * @param url
	 * @return
	 */
	private boolean isNoAceUrl(String url){
		if (!StringUtils.isEmpty(url)) {
			return url.startsWith("/sys/index.shtml")
					|| url.startsWith("/sys/main.shtml")
					|| url.startsWith("/apps/");
		}
		return true;
	}
	
	/**
	 * 不需要权限(手机)
	 * @param url
	 * @return
	 */
	private boolean isNoAceUrlPhone(String url) {
		if (!StringUtils.isEmpty(url)) {
			
		}
		return true;
	}
	
	/**
	 * 不需要登录
	 * @param url
	 * @return
	 */
	private boolean isPublicUrl(String url){
		if (!StringUtils.isEmpty(url)) {
			return url.startsWith("/uac/user/login.shtml") 
					|| url.startsWith("/uac/user/loginjson.shtml")
					|| url.startsWith("/uac/user/userlogin.shtml")
					|| url.startsWith("/uac/user/logout")
					|| url.startsWith("/app/")
					|| url.startsWith("/kfbase/objectfile/download.shtml")
					|| url.startsWith("/app/client/api.shtml")
					|| url.startsWith("/app/client/reg.shtml")
					
				    || url.startsWith("/mall/orderspay/alipaycallback.shtml")
				    || url.startsWith("/livebroadcast/pay/alipaylbdealcallback.shtml")
				    
				    || url.startsWith("/mall/orderspay/mallwechatpaynotifycallback.shtml")
				    || url.startsWith("/livebroadcast/pay/lbwechatpaynotifycallback.shtml")
				    || url.startsWith("/sys/test.shtml")//测试接口
					;
		}
		return false;
	}
	
	/**
	 * 不需要登录(手机用)
	 * @param url
	 * @return
	 */
	private boolean isPhoneUrl(String url) {
		
		if (!StringUtils.isEmpty(url)) {
			
			
		}
		return false;
	}
	
	private String getLoginUserId(){
		return getLoginUser() != null ? getLoginUser().getId() : "";
	}
	
	private String getLoginUserName(){
		return getLoginUser() != null ? getLoginUser().getName():"";
	}
	
	private String getLoginUserDefaultIp(){
		return getLoginUser() != null ? getLoginUser().getDefaultLoginIp():"";
	}
	
	private String getLoginUserAccount(){
		return getLoginUser()!=null?getLoginUser().getLoginAccount():"";
	}
	
	@Autowired
	private UserService userService;
	private User getLoginUser(){
		User user = (User)ServletActionContext.getRequest().getSession().getAttribute("user");
		
		if (user == null) {
			Cookie cookie = getCookieByName("loginAccount");
			if (cookie != null) {
				String loginAccount = cookie.getValue();
				cookie = getCookieByName("loginPasswd");
				if (cookie != null) {
					String passwd = cookie.getValue();
					if (!StringUtils.isEmpty(loginAccount) && !StringUtils.isEmpty(passwd)) {
						user = userService.login(loginAccount, passwd);
						if (user != null) {
							ServletActionContext.getRequest().getSession().setAttribute("user", user);
						}
					}
				}
				
			}
		}
		
		return user;
	}
	
	private Cookie getCookieByName(String paramString) {
		Cookie[] arrayOfCookie1 = ServletActionContext.getRequest().getCookies();
		if(arrayOfCookie1 != null){
			for (Cookie localCookie : arrayOfCookie1)
				if (localCookie.getName().equals(paramString))
					return localCookie;
		}
		return null;
	}

	public String intercept(ActionInvocation invocation) throws Exception{
		
		Log log = LogFactory.getLog(PerformanceInterceptor.class);
		
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("UTF-8");
		String result = "";
		String methodName = invocation.getInvocationContext().getName();
		String classMethodName = "";
		ValueStack valueStack = invocation.getInvocationContext().getValueStack();
		String ua = request.getHeader("user-agent").toLowerCase();  // 判断是否是微信过来的url地址
		
		//System.out.println("user-agent:" + getRequest().getHeader("user-agent"));
		
		try{
			ActionConfig config = invocation.getProxy().getConfig();
			classMethodName = config.getMethodName();
			
			Object o = invocation.getAction();
			if (o instanceof  Struts2BaseAction) {
				Struts2BaseAction baseAction = (Struts2BaseAction)o;
				baseAction.setActionName(config.getName());
				baseAction.setMethodName(classMethodName);
			}
			
			Map<String,Object> parameters = invocation.getInvocationContext().getParameters(); 
			if(logger.isDebugEnabled()){
			logger.debug("======================Web请求-["+config.getName()+"] begin=============================");
				String ip = request.getRemoteAddr(); 
				String sessionid = request.getSession().getId();
				logger.debug("请求--操作者IP:{}",ip);
				logger.debug("请求--sessionid:{}",sessionid);
				logger.debug("请求--struts2配置Action类名:{}",config.getClassName());
				logger.debug("请求--struts2配置方法名:{}",config.getName());
				logger.debug("请求--类的执行方法方法名:{}",classMethodName);
				
				
				if (parameters != null) { 
					Set set = parameters.entrySet(); 
					Iterator iterator = null; 
					if (set != null) { 
						iterator = set.iterator(); 
					} 
					while (iterator.hasNext()) { 
						Map.Entry entry =(Map.Entry)iterator.next();
						if(!entry.getKey().equals("upload")){
							logger.debug("请求--参数key:[{}], value:{}",entry.getKey(),entry.getValue());
						}
					} 
				} 
				logger.debug("======================Web请求-["+config.getName()+"] end=============================");
			}			
			
			
			if(!validateUrlAce(config)){
				log.info("return to login");
			    return "login";
			}
			
			
			urlPath = getContextUrl();
			log.info("url Path1:" + urlPath);
			
			result = invocation.invoke();
			
			log.info("action result:" + result);
			
		}catch(Exception e){
			logger.error("拦截器异常错误",e);
			log.info(ExceptionUtils.getMessage(e));
			if (methodName.toLowerCase().contains("json") 
					|| methodName.toLowerCase().equals("save")
					|| classMethodName.toLowerCase().contains("json")
					) {
				//ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				HttpServletResponse response=ServletActionContext.getResponse();
				response.setContentType("text; charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = null;
				try {
					out = ServletActionContext.getResponse().getWriter();
					
					JSONObject jsonObject = new JSONObject();
					jsonObject.element("isSuccess", false);
					if (e instanceof ApplicationException) {
						jsonObject.element("resultDescription", e.getMessage());
					}else {
						jsonObject.element("resultDescription", "系统内部错误，请联系管理！");
					}
					out.write(jsonObject.toString());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				return null;
			}else {
				if(e instanceof ApplicationException){
					message = e.getMessage();
				}else{
					message="系统内部错误，请联系管理员！";
				}
				Object o = invocation.getAction();
				if (o instanceof DomainAction) {
					DomainAction action = (DomainAction)o;
					action.setException(e);
				}
				valueStack.set("message", message);
				return "appException";
			}
		}
		
		return result;
	}
	
	private boolean addLog(ActionConfig config, ServletRequest request, boolean forceAdd) {
		if (getLoginUser()!=null || forceAdd) {
			if(!config.getPackageName().equalsIgnoreCase("performance-logs-log")){
				try{
//					initLogService();
//					logService.addLog(config.getPackageName(), 
//							config.getName(), 
//							getLoginUserId(), 
//							getLoginUserName(), 
//							request.getRemoteAddr(), 
//							getLoginUserDefaultIp(), 
//							ServletActionContext.getRequest().getSession().getId());				
				}catch(Exception exception){
					
				}
			}
			return true;
		}
		return false;
	}
	
	
	private HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	
	private HttpSession getSession(){
		return getRequest().getSession();
	}
	
	
	
	private String getCurrentUrl(){
		HttpServletRequest request = getRequest();
		return "http://" + request.getServerName() //服务器地址  
        + (request.getServerPort()!=80?":"+request.getServerPort():"")           //端口号  
        + request.getContextPath()      //项目名称  
        + request.getServletPath()      //请求页面或其他地址  
        + (!StringUtils.isEmpty(request.getQueryString())?("?"+request.getQueryString()):""); //参数  
	}
	
	private String getContextUrl(){
		HttpServletRequest request = getRequest();
		return request.getServletPath()      //请求页面或其他地址  
        + (!StringUtils.isEmpty(request.getQueryString())?("?"+request.getQueryString()):""); //参数  
	}
	
	private String urlEncode(String url){
		try {
			return URLEncoder.encode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	
	
}
