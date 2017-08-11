
package com.szit.arbitrate.pushcentre.factory;

import com.szit.arbitrate.pushcentre.factory.product.IPushDisposeProduct;

/**
 * 
* @ClassName: PushReflectFactory
* @Description:
* @author Administrator
* @date 2017年5月11日 下午5:47:55
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public abstract class PushReflectFactory {
	
	 public abstract <T extends IPushDisposeProduct> T createProduct(Class<T> c); 
}
