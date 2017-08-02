/**
 * 
 */
package com.szit.arbitrate.pushcentre.exception;

/**
 * 
* @ClassName: WapBizExcetion
* @author XUJC
* @date 2017年6月21日 上午10:15:03
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司.
* @versions:1.0
 */
public class PushBizExcetion extends RuntimeException {

	private static final long serialVersionUID = 6716663249913015509L;

	public PushBizExcetion() {
		super();
	}

	public PushBizExcetion(String message, Throwable cause) {
		super(message, cause);
	}

	public PushBizExcetion(Throwable cause) {
		super(cause);
	}

	public PushBizExcetion(String message) {
		super(message);
	}
}
