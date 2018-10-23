/**
 * 
 */
package com.szit.arbitrate.api.news.controller.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.news.controller.NewsClickLikeController;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.news.entity.NewsClickLike;
import com.szit.arbitrate.news.entity.query.NewsClickLikeQuery;
import com.szit.arbitrate.news.service.MomentsResourcesService;
import com.szit.arbitrate.news.service.NewsClickLikeService;

/**
 * 	@author Administrator
 * 	@date: 	下午3:58:34
 *	@Descript 
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@Component("newsClickLikeController")
public class NewsClickLikeControllerImpl  extends BaseController<NewsClickLike, NewsClickLikeQuery> 
implements NewsClickLikeController{
	
	@Autowired
	private NewsClickLikeService newsClickLikeService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private MomentsResourcesService momentsResourcesService;

	@Override
	public ApiOutParamsVm saveNewsClickLike(String newsId, String clientId) {
		clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		String clientIp = getRequest().getLocalAddr();
		String result = newsClickLikeService.saveClickLike(newsId, clientId, clientIp);
		return ApiTools.bulidOutSucceed(result);
	}

	@Override
	public ApiOutParamsVm deleteClickLike(String newsId, String clientId) {
		clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		String result = newsClickLikeService.deleteClickLike(newsId, clientId);
		return ApiTools.bulidOutSucceed(result);
	}
}