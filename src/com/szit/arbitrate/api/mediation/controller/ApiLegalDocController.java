package com.szit.arbitrate.api.mediation.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 
* @ClassName: ApiLegalDocController
* @author Administrator
* @date 2017年3月27日 上午9:16:25
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ApiLegalDocController {
	
	/**
	 * 
	* @Title: getLegalDocList 
	* @Description: 按文档类型获取列表
	* @param @param doctype
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getLegalDocList(String doctype, String classiccasetype, 
			String docname, Integer page);
	
	/**
	 * 
	* @Title: getLegalDocDetailById 
	* @Description: 获取法规文档详情，包括点赞，评论
	* @param @param legaldocid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getLegalDocDetailById(String legaldocid,String isWeixin);
	
	/**
	 * 获取文件路径地址
	 * @param legaldocid
	 * @return
	 */
	public ApiOutParamsVm getFilePath(String legaldocid);
}
