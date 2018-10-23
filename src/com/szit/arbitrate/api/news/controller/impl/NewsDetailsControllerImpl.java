/**
 * 
 */
package com.szit.arbitrate.api.news.controller.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.news.controller.NewsDeatilsController;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.news.entity.NewsDetails;
import com.szit.arbitrate.news.entity.query.NewsDetailsQuery;
import com.szit.arbitrate.news.service.MomentsResourcesService;
import com.szit.arbitrate.news.service.NewsDetailsService;
import com.szit.arbitrate.news.service.NewsHeadInfoService;

/**
 * 	@author Administrator
 * 	@date: 	下午1:51:59
 *	@Descript 
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@Component("newsDetailsController")
public class NewsDetailsControllerImpl extends BaseController<NewsDetails, NewsDetailsQuery> 
implements NewsDeatilsController{

	@Autowired
	private ClientService clientService;
	@Autowired
	public NewsDetailsService newsDetailsService;
	
	@Autowired
	public NewsHeadInfoService newsHeadInfoService;
	
	@Autowired
	public MomentsResourcesService momentsResourcesService;
	
	@Override
	public ApiOutParamsVm saveNewsDetails(String newsId, String contents,int newsSeq) {
		File f = (File) getRequest().getAttribute("upload");
		//加入文本内容
		newsDetailsService.saveNewsDetails(newsId, contents ,newsSeq ,f);
		if(f==null){
			return ApiTools.bulidOutSucceed("内容添加（不带图）");
		}
		return ApiTools.bulidOutSucceed("内容添加");
	}
	
	
	@Override
	public ApiOutParamsVm getNewsDetailsById(String newsId) {
		List<NewsDetails> list = newsDetailsService.findNewsDetailsList(newsId);
		if(list!=null){
			return ApiTools.bulidOutSucceed("内容加载",list);
		}
		return ApiTools.bulidOutSucceed("无内容");
	}


	@Override
	public ApiOutParamsVm deleteNewsDetailsById(String newsId,String newsdetailsId) {
		newsDetailsService.deleteNewsDetails(newsId, newsdetailsId);

		return ApiTools.bulidOutSucceed("内容已移除");

	}
}