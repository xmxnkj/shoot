/**
 * 
 */
package com.szit.arbitrate.api.news.controller.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.news.controller.NewsCommentController;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.news.entity.NewsComment;
import com.szit.arbitrate.news.entity.query.NewsCommentQuery;
import com.szit.arbitrate.news.service.NewsCommentService;

/**
 * 	@author Administrator
 * 	@date: 	下午2:56:38
 *	@Descript 
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@Component("newsCommentController")
public class NewsCommentControllerImpl extends BaseController<NewsComment, NewsCommentQuery>
implements  NewsCommentController
{

	@Autowired
	private ClientService clientService;
	@Autowired
	public NewsCommentService newsCommentService;
	
	@Override
	public ApiOutParamsVm saveNewsComment(String newsId,String clientId,String content) {
		clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		String commentip = getRequest().getRemoteAddr();
		String result = newsCommentService.saveComment(newsId, clientId, content, commentip);
		return ApiTools.bulidOutSucceed(result.equals("OK")?"已添加":"请登录");
	}

	
	@Override
	public ApiOutParamsVm getCommentWithPage(String newsId,int pageNum) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String ,Object> resultMap= newsCommentService.getCommentWithPage(newsId,pageNum);
		return ApiTools.bulidOutSucceed("评论已加载",resultMap);
	}


	@Override
	public ApiOutParamsVm toShow(String commentId, boolean ishow) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		newsCommentService.toShow(commentId, ishow);
		String result = "已设为隐藏";
		if(ishow){
			result = "已设为可见";
		}
		return ApiTools.bulidOutSucceed(result);
	}
	
	@Override
	public ApiOutParamsVm toShowAll(String newId, boolean ishow) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		newsCommentService.toShowAll(newId, ishow);
		String result = "已设为隐藏";
		if(ishow){
			result = "已设为可见";
		}
		return ApiTools.bulidOutSucceed(result);
	}

	@Override
	public ApiOutParamsVm deleteNewsComment(String newId, String commentid) {
		String clientId = clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		newsCommentService.deleteComment(newId, commentid);
		return ApiTools.bulidOutSucceed("已删除");
	}
}