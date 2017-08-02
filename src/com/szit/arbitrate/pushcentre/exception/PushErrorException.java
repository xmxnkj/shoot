/**   
* @Title: WapErrorException.java
* @Package com.szit.coupons.exceptions
* @Description: TODO
* @author XUJC 
* @date 2017年6月21日 上午10:17:05
* @version V1.0   
*/


package com.szit.arbitrate.pushcentre.exception;

/**
 * @ClassName: WapErrorException
 * @author XUJC
 * @date 2017年6月21日 上午10:17:05
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */

public class PushErrorException extends RuntimeException {
	
	private static final long serialVersionUID = 9088623090524536762L;

	public PushErrorException() {
		super();
	}

	public PushErrorException(String message) {
		super(message);
	}

	public PushErrorException(Throwable cause) {
		super(cause);
	}

	public PushErrorException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
