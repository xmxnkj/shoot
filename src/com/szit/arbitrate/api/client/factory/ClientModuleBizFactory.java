/**
 * 
 */
package com.szit.arbitrate.api.client.factory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.api.client.factory.product.impl.BulidClientCenterProduct;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;



/**
 * 
* @ClassName: ClientModuleBizFactory
* @Description:客户端模块业务执行模块
* @author Administrator
* @date 2017年3月20日 下午2:48:12
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class ClientModuleBizFactory {
	
	private static Logger logger = LoggerFactory.getLogger(ClientModuleBizFactory.class);
	
	public enum BIZMODULEENUM {
		clientcenter
	}

	/**
	 * 
	* @Title: bulidBiz 
	* @Description: 构建业务
	* @param @param apiInVm
	* @param @return
	* @param @throws BaseBizException
	* @param @throws BaseErrorException
	* @return ApiOutParamsVm 
	* @throws
	 */
	public final static ApiOutParamsVm bulidBiz(ApiInParamsVm apiInVm,HttpServletRequest request,HttpServletResponse response) throws BizException,ErrorException{
		try {
			logger.debug("业务模块:{},业务代码:{}",apiInVm.getModule(),apiInVm.getBizcode());
			ExecuteReflectFactory fac = new ExecuteReflectFactory();
			if(StringUtils.isEmpty(apiInVm.getBizcode())){
				return  ApiTools.bulidOutFail("非法参数,[bizcode]参数不能为空,请核查!");
			}
			if(StringUtils.isEmpty(apiInVm.getBizmethod())){
				return  ApiTools.bulidOutFail("非法参数,[bizmethod]参数不能为空,请核查!");
			}
			//会员中心
			logger.debug("BIZMODULEENUM:{},BIZCODE:{}",BIZMODULEENUM.clientcenter.name(),apiInVm.getBizcode().trim());
			if(BIZMODULEENUM.clientcenter.name().trim().equals(apiInVm.getBizcode().trim())){
				return fac.createProduct(BulidClientCenterProduct.class).bulidBiz(apiInVm,request,response);
			}else{
				return  ApiTools.bulidOutFail("非法参数,无业务代码,请核查!");
			}
		} catch (Exception e) {
			throw new ErrorException(e);
		}

	}


	
	
}
