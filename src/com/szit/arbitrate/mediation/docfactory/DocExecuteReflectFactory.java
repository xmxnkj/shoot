package com.szit.arbitrate.mediation.docfactory;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.szit.arbitrate.mediation.docfactory.enumvo.DocTypeEnum;
import com.szit.arbitrate.mediation.docfactory.product.IDocDisposeProduct;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: DocExecuteReflectFactory
* @Description:具体工厂类
* @author Administrator
* @date 2017年5月12日 下午2:21:18
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class DocExecuteReflectFactory extends DocReflectFactory{
	
	private Logger logger = LoggerFactory.getLogger(DocExecuteReflectFactory.class);

	private Map<String, Object> params;
	//private DocTypeEnum docTypeEnum;
	
	public DocExecuteReflectFactory(Map<String, Object> params) {
		super();
		this.params = params;
	}
	
	@Override
	public <T extends IDocDisposeProduct> T createProduct(Class<T> c) {
		T product = null;
		try {
			JSONObject json = new JSONObject(params);
			System.out.println(json.toJSONString());
			product = (T) Class.forName(c.getName()).newInstance();
			product.buildExportDocDispose(params);
		} catch (Exception e) {
			logger.error("工厂类异常",e);
		}
		return product;
	}
	

}
