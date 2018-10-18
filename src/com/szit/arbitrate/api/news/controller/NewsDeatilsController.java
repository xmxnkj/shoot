/**
 * 
 */
package com.szit.arbitrate.api.news.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 	@author Administrator
 * 	@date: 	下午1:51:29
 *	@Descript 
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
public interface NewsDeatilsController {
	
	public ApiOutParamsVm saveNewsDetails(String newsId,String content,int newsSeq);
	
	public ApiOutParamsVm getNewsDetailsById(String newsId);
	
	public ApiOutParamsVm deleteNewsDetailsById(String newsId,String newsdetailsId);
	
}
