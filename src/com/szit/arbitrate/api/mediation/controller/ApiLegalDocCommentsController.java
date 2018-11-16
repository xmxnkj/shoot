package com.szit.arbitrate.api.mediation.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 
* @ClassName: ApiLegalDocCommentsController  
* @author   
* @date 2017年7月4日 下午2:17:03  
* @Copyright
* @versions:1.0 
*
 */
public interface ApiLegalDocCommentsController {
	
	/**
	 * 
	* @Title: addLegalDocComments  
	* @param @param legaldocid
	* @param @param commentsclientid
	* @param @param commentscontent
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm addLegalDocComments(String legaldocid,String commentscontent);

	/**
	 * 
	* @Title: deleteLegalDocComments  
	* @Description:删除评论 
	* @param @param commentsid
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm deleteLegalDocComments(String commentsid);
	
	/**
	 * 
	* @Title: getCommentsListByDocIdAndClientId  
	* @param @param legaldocid
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm getCommentsListByDocId(String legaldocid, Integer page);
	
}
