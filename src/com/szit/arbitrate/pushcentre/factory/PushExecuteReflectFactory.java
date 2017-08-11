package com.szit.arbitrate.pushcentre.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.szit.arbitrate.pushcentre.factory.product.IPushDisposeProduct;


/**
 * 
* @ClassName: PushExecuteReflectFactory
* @Description:
* @author Administrator
* @date 2017年5月11日 下午5:46:53
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class PushExecuteReflectFactory extends PushReflectFactory{

	private Logger logger = LoggerFactory.getLogger(PushExecuteReflectFactory.class);
	
	@Override
	public <T extends IPushDisposeProduct> T createProduct(Class<T> c) {
		T product = null; 
		try { 
			product = (T)Class.forName(c.getName()).newInstance(); 
		} catch (Exception e) { 
			logger.error("工厂类异常",e);
		} 
		return product; 
	} 
}
