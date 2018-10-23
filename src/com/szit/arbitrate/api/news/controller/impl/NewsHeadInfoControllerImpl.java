/**
 * 
 */
package com.szit.arbitrate.api.news.controller.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.utils.HttpSessionContext;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.news.controller.NewsHeadInfoController;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.news.entity.MomentsResources;
import com.szit.arbitrate.news.entity.NewsClickLike;
import com.szit.arbitrate.news.entity.NewsDetails;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.entity.query.NewsClickLikeQuery;
import com.szit.arbitrate.news.entity.query.NewsHeadInfoQuery;
import com.szit.arbitrate.news.service.MomentsResourcesService;
import com.szit.arbitrate.news.service.NewsClickLikeService;
import com.szit.arbitrate.news.service.NewsDetailsService;
import com.szit.arbitrate.news.service.NewsHeadInfoService;
import com.szit.neteasecloud.utils.HtmlCodeToText;

/**
 * 	@author Administrator
 * 	@date: 	上午9:51:48
 *	@Descript 
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@Component("newsHeadInfoController")
public class NewsHeadInfoControllerImpl extends BaseController<NewsHeadInfo, NewsHeadInfoQuery> implements
NewsHeadInfoController {
	
	@Autowired
	private ClientService clientService;
	@Autowired
	public NewsHeadInfoService newsHeadInfoService;
	
	@Autowired
	public NewsClickLikeService newsClickLikeService;
	
	@Autowired
	public NewsDetailsService newsDetailsService;
	
	@Autowired
	public MomentsResourcesService momentsResourcesService;

	@Override
	public ApiOutParamsVm deleteNewsHeadInfo(String newsId) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		
		int result = newsHeadInfoService.delete(newsId);
		
		if(result==0){
			return ApiTools.bulidOutSucceed("为招到指定的新闻,删除失败");
		}
		return ApiTools.bulidOutSucceed("删除成功");
	}
	

	@Override
	public ApiOutParamsVm getNewsHeadInfoListWithPage(String title,Integer pageNum,Integer pageSize) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		pageSize = pageSize==null?0:pageSize;
		title = title==null?"":title;
		Map<String,Object> resultMap = newsHeadInfoService.findNewsByTitleList(title,pageNum,pageSize);
		return ApiTools.bulidOutSucceed("列表加载",resultMap);
	}


	@Override
	public ApiOutParamsVm getNewsHeadInformation(String newsId,String ClientId,String isWeixin) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String,Object> resultMap = newsHeadInfoService.loadNewsInformation(newsId,ClientId);
		//内容文本化
		String contexts = "";
		if(((List<NewsDetails>) resultMap.get("newsDetails")).size()>0){
			NewsDetails newsdetails = ((List<NewsDetails>) resultMap.get("newsDetails")).get(0);
			contexts = newsdetails.getNewsContent();
		}else{
			contexts = ((NewsHeadInfo)resultMap.get("newsHeadInfo")).getNewsContent();
		}
		
		if(isWeixin!=null && !isWeixin.equals("")){
			List<Map<String, Object>> list = HtmlCodeToText.getText(contexts);
			resultMap.put("text", list);
		}
		return ApiTools.bulidOutSucceed("数据加载",resultMap);
	}


	@Override
	public ApiOutParamsVm flushNewHeadInfo(String newsId,String clientId) {
		clientId = clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		NewsHeadInfo newsHeadInfo = newsHeadInfoService.getById(newsId);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("newsHeadInfo", newsHeadInfo);
		if(clientId!=null){
			NewsClickLikeQuery newsClickLikeQuery = new NewsClickLikeQuery();
			newsClickLikeQuery.setNewsid(newsId);
			newsClickLikeQuery.setLikeclientid(clientId);
			NewsClickLike newsClickLike = newsClickLikeService.getEntity(newsClickLikeQuery);
			result.put("isClickLike", newsClickLike!=null?true:false);
		}
		
		return ApiTools.bulidOutSucceed("刷新点",result);
	}


	@Override
	public ApiOutParamsVm hotNewsList() {
		/*String clientId = HttpSessionContext.getHttpSession(this.getRequest(),"clientId");
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}*/
		List<NewsHeadInfo> list = newsHeadInfoService.hotNewsList();
		return ApiTools.bulidOutSucceed("热点",list);
	}


	@Override
	public ApiOutParamsVm lunBo() {
		/*String clientId = HttpSessionContext.getHttpSession(this.getRequest(),"clientId");
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}*/
		List<MomentsResources> list = newsHeadInfoService.lunBo();
		return ApiTools.bulidOutSucceed("轮播图",list);
	}


}