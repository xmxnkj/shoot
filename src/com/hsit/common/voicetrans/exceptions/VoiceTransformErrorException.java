/**   
* @Title: WapErrorException.java
* @Package com.szit.coupons.exceptions
* @Description: TODO
* @author XUJC 
* @date 2017年6月21日 上午10:17:05
* @version V1.0   
*/


package com.hsit.common.voicetrans.exceptions;

/**
 * @ClassName: WapErrorException
 * @Description:Wap异常错误处理类
 * @author XUJC
 * @date 2017:年
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */

public class VoiceTransformErrorException extends RuntimeException {
	
	private static final long serialVersionUID = 9088623090524536762L;

	public VoiceTransformErrorException() {
		super();
	}

	public VoiceTransformErrorException(String message) {
		super(message);
	}

	public VoiceTransformErrorException(Throwable cause) {
		super(cause);
	}

	public VoiceTransformErrorException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
