package com.szit.arbitrate.mediation.docfactory.exception;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: DocBizException
* @Description:导出doc文档业务异常处理类
* @author Administrator
* @date 2017年5月12日 上午11:08:21
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class DocBizException extends RuntimeException  {

	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 2359572888150189785L;

	public DocBizException() {
		super();
	}

	public DocBizException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocBizException(String message) {
		super(message);
	}

	public DocBizException(Throwable cause) {
		super(cause);
	}
	
	

}
