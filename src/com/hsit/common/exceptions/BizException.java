/**
 * 
 */
package com.hsit.common.exceptions;

/**
 * 
* @ProjectName:sizt-coupons
* @ClassName: WapBizExcetion
* @Description:Api接口业务异常处理类
* @author XUJC
* @date 2017年6月21日 上午10:15:03
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司.
* @versions:1.0
 */
public class BizException extends RuntimeException {

	private static final long serialVersionUID = 6716663249913015509L;

	public BizException() {
		super();
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(Throwable cause) {
		super(cause);
	}

	public BizException(String message) {
		super(message);
	}
}
