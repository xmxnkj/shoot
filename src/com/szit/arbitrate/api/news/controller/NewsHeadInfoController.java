/**
 * 
 */
package com.szit.arbitrate.api.news.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 	@author Administrator
 * 	@date: 	上午9:49:44
 *	@Descript 
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */

public interface NewsHeadInfoController {
	
	public ApiOutParamsVm deleteNewsHeadInfo(String newsId);
	
	
	public ApiOutParamsVm getNewsHeadInfoListWithPage(String title,Integer pageNum,Integer pageSize);

	public ApiOutParamsVm getNewsHeadInformation(String newsId,String ClientId,String isWeixin);
	
	
	public ApiOutParamsVm flushNewHeadInfo(String newsId,String clientId);
	
	public ApiOutParamsVm hotNewsList();
	
	public ApiOutParamsVm lunBo();
}
