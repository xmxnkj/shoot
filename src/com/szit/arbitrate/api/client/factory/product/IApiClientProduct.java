/**   
* @Title: IApiClientProduct.java
* @Package com.szit.cowell.client.factory.product
* @Description: TODO
* @author XUJC
* @date 2017年10月27日 下午3:14:12
* @version V1.0   
*/


package com.szit.arbitrate.api.client.factory.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;


/**
 * 
* @ClassName: IApiClientProduct
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:48:47
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public interface IApiClientProduct {

	/**
	 * 
	* @Title: bulidBiz 
	* @Description:构建业务
	* @param @param inVm
	* @param @param request
	* @param @param response
	* @param @return
	* @param @throws BaseBizException
	* @param @throws BaseErrorException
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm bulidBiz(ApiInParamsVm inVm,HttpServletRequest request,HttpServletResponse response)throws BizException,ErrorException;
	
}
