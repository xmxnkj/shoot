/**
 * 
 */
package com.szit.arbitrate.api.client.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.szit.arbitrate.api.client.factory.product.IApiClientProduct;


/**
 * 
* @ClassName: ExecuteReflectFactory
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:48:25
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class ExecuteReflectFactory extends ReflectFactory {

	private static final Logger logger = LoggerFactory.getLogger(ExecuteReflectFactory.class);
	
	@Override
	public <T extends IApiClientProduct> T createProduct(Class<T> c) {
		T product = null; 
		try { 
			product = (T)Class.forName(c.getName()).newInstance(); 
		} catch (Exception e) { 
			logger.error("工厂类异常",e);
		} 
		return product; 
	} 

}
