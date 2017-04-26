/**   
* @Title: WapErrorException.java
* @Package com.szit.coupons.exceptions
* @Description: TODO
* @author XUJC 
* @date 2017年6月21日 上午10:17:05
* @version V1.0   
*/


package com.hsit.common.exceptions;

/**
 * @ProjectName:sizt-coupons
 * @ClassName: WapErrorException
 * @Description:Api异常错误处理类
 * @author XUJC
 * @date 2017年3月21日 上午10:17:05
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */

public class ErrorException extends RuntimeException {
	

	private static final long serialVersionUID = 9088623090524536762L;

	public ErrorException() {
		super();
	}

	public ErrorException(String message) {
		super(message);
	}

	public ErrorException(Throwable cause) {
		super(cause);
	}

	public ErrorException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
