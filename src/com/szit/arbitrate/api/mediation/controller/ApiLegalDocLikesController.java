package com.szit.arbitrate.api.mediation.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 
* @ClassName: ApiLegalDocLikesController  
* @author   
* @date 2017年7月4日 下午2:12:43  
* @Copyright
* @versions:1.0 
*
 */
public interface ApiLegalDocLikesController {
	
	/**
	 * 
	* @Title: addLegalDocLikes  
	* @param @param legaldocid
	* @param @param likesclientid
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm addLegalDocLikes(String legaldocid);
	
	/**
	 * 
	* @Title: refreshLegalDoc  
	* @param @param legaldocid
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm refreshLegalDoc(String legaldocid);
	
	/**
	 * 
	* @Title: cancelLegalDocLikes  
	* @param @param legaldocid
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm cancelLegalDocLikes(String legaldocid);

}
