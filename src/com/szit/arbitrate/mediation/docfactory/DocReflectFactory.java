package com.szit.arbitrate.mediation.docfactory;

import com.szit.arbitrate.mediation.docfactory.product.IDocDisposeProduct;

/**
 * 
* @ProjectName:
* @ClassName: DocReflectFactory
* @Description:抽象工厂类
* @author Administrator
* @date 2017年5月12日 下午2:20:52
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public abstract class DocReflectFactory {
	
	public abstract <T extends IDocDisposeProduct> T createProduct(Class<T> c);

}
