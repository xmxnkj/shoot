/**
 * 
 */
package com.hsit.common.voicetrans.exceptions;

/**
 * 
* @ClassName: WapBizExcetion
* @Description:App接口业务异常处理类
* @author XUJC
* @date 2017年
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司.
* @versions:1.0
 */
public class VoiceTransformBizException extends RuntimeException {

	private static final long serialVersionUID = 6716663249913015509L;

	public VoiceTransformBizException() {
		super();
	}

	public VoiceTransformBizException(String message, Throwable cause) {
		super(message, cause);
	}

	public VoiceTransformBizException(Throwable cause) {
		super(cause);
	}

	public VoiceTransformBizException(String message) {
		super(message);
	}
}
