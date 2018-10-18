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
public interface NewsCommentController{
	
	
	public ApiOutParamsVm saveNewsComment(String newsId,String clientId,String content);
	
	
	public ApiOutParamsVm getCommentWithPage(String newsId,int pageNum);
	
	
	public ApiOutParamsVm toShow(String commentId,boolean ishow);
	
	
	public ApiOutParamsVm toShowAll(String newId,boolean ishow);
	
	
	public ApiOutParamsVm deleteNewsComment(String newId,String commentid);
}
