/**   
* @Title: StackTraceUtils.java
* @Package com.szit.cowell.api.common.utils
* @Description: TODO
* @author XUJC
* @date 2017年10月30日 上午11:28:12
* @version V1.0   
*/


package com.szit.arbitrate.api.common.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

/**
 * 
* @ProjectName:
* @ClassName: StackTraceUtils
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:52:35
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public class StackTraceUtils {
	
	/**
     * 将异常堆栈转换为字符串
     * @param aThrowable 异常
     * @return String
     */
	public static String getStackTrace(Throwable aThrowable) {
		final Writer result = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(result);
		aThrowable.printStackTrace(printWriter);
		return result.toString();
	}
}
