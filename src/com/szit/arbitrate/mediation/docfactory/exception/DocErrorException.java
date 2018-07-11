package com.szit.arbitrate.mediation.docfactory.exception;

/**
 * 
* @ProjectName:
* @ClassName: DocErrorException
* @Description:生成doc系统异常处理类
* @author Administrator
* @date 2017年5月12日 上午11:09:28
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class DocErrorException extends RuntimeException{

	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 4767485391664747109L;

	public DocErrorException() {
		super();
	}

	public DocErrorException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocErrorException(String message) {
		super(message);
	}

	public DocErrorException(Throwable cause) {
		super(cause);
	}
	
	

}
