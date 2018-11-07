/**   
* @Title: HttpSessionContext.java
* @Package com.szit.cowell.api.common.utils
* @Description: TODO
* @author XUJC
* @date 2017年11月2日 下午6:37:39
* @version V1.0   
*/


package com.szit.arbitrate.api.common.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
* @ClassName: HttpSessionContext
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:51:32
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public class HttpSessionContext {

	public static final String APPLANGUAGE="APPLANGUAGE";
	
	/**
	 * 
	* @Title: getHttpSession 
	* @Description: 获取HttpSession
	* @param @param request
	* @param @return
	* @return HttpSession 
	* @throws
	 */
	public static HttpSession getHttpSession(HttpServletRequest request){
		return request.getSession();
	}
	
	/**
	 * 
	* @Title: getHttpSession 
	* @Description: 获取HttpSession
	* @param @param request
	* @param @param attributeName
	* @param @return
	* @return String 
	* @throws
	 */
	public static String getHttpSession(HttpServletRequest request,String attributeName){
		return request.getSession().getAttribute(attributeName)==null?"":(String)request.getSession().getAttribute(attributeName);
	}
	
	/**
	 * 
	* @Title: setHttpSessionVal 
	* @Description: 
	* @param @param request
	* @param @param name
	* @param @param val
	* @return void 
	* @throws
	 */
	public static void setHttpSessionVal(HttpServletRequest request,String name,String val){
		  request.getSession().setAttribute(name,val);
	}
	
}
