/**
 * 
 */
package com.szit.arbitrate.api.client.factory;

import com.szit.arbitrate.api.client.factory.product.IApiClientProduct;



/**
 * 
* @ClassName: ReflectFactory
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:48:36
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public abstract class ReflectFactory {
    public abstract <T extends IApiClientProduct> T createProduct(Class<T> c); 
}

