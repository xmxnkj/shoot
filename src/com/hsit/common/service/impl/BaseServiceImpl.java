/**   
* @Title: BaseServiceImpl.java
* @Package com.hsit.common.service.impl
* @Description: TODO
* @author XUJC
* @date 2017年11月30日 上午9:33:23
* @version V1.0   
*/


package com.hsit.common.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsit.common.dao.Dao;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.DomainObject;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.service.AppBaseServiceImpl;
import com.hsit.common.service.BaseService;

/**
 * @ProjectName:cowell
 * @ClassName: BaseServiceImpl
 * @Description:
 * @author XUJC
 * @date 2017年11月30日 上午9:33:23
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */

public abstract class BaseServiceImpl<T extends DomainObject, Q extends EntityQueryParam> extends AppBaseServiceImpl<T,Q>  implements BaseService<T,Q> {
	protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected  HttpServletRequest request;
	
	@Override
	public void setHttpServletRequest(HttpServletRequest request)
			throws BizException, ErrorException {
		this.request = request;
	}

	@Override
	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}


	
}
