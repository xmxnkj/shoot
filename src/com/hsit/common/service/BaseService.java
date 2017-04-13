/**   
* @Title: BaseService.java
* @Package com.hsit.common.service
* @Description: TODO
* @author XUJC
* @date 2017年11月30日 上午9:32:48
* @version V1.0   
*/


package com.hsit.common.service;

import javax.servlet.http.HttpServletRequest;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.DomainObject;
import com.hsit.common.kfbase.entity.EntityQueryParam;


/**
 * @ProjectName:cowell
 * @ClassName: BaseService
 * @Description:
 * @author XUJC
 * @date 2017年11月30日 上午9:32:48
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */

public abstract interface BaseService<T extends DomainObject, Q extends EntityQueryParam> extends AppBaseService<T, Q>{

	/**
	 * 
	* 方法描述:设置Request
	* 创建人: Administrator
	* 创建时间：2017年1月19日
	* @param request
	* @throws BizException
	* @throws ErrorException
	 */
	public void setHttpServletRequest(HttpServletRequest request)throws BizException,ErrorException;
	
	public HttpServletRequest getRequest();
}
