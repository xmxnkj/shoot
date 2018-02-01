package com.szit.arbitrate.mediation.docfactory.product;

import java.util.Map;

import com.szit.arbitrate.mediation.docfactory.exception.DocBizException;
import com.szit.arbitrate.mediation.docfactory.exception.DocErrorException;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: IDocDisposeProduct
* @Description:导出doc文档车间接口类
* @author Administrator
* @date 2017年5月12日 上午11:14:39
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface IDocDisposeProduct {
	
	/**
	 * 
	* @Title: buildExportDocDispose 
	* @Description: 构建导出doc文档接口类
	* @param @param params
	* @param @throws DocBizException
	* @param @throws DocErrorException
	* @return void 
	* @throws
	 */
	public void buildExportDocDispose(Map<String,Object> params) throws DocBizException, DocErrorException;

}
