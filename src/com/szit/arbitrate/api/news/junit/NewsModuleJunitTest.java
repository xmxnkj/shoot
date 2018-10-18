/**
 * 
 */
package com.szit.arbitrate.api.news.junit;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.news.dao.NewsHeadInfoDao;
import com.szit.arbitrate.news.service.MomentsResourcesService;
import com.szit.arbitrate.news.service.NewsDetailsService;
import com.szit.arbitrate.news.service.NewsHeadInfoService;

/**
 * 	@author Administrator
 * 	@date: 	上午9:45:31
 *	@Descript 
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
public class NewsModuleJunitTest extends BaseApiJunitTest{
	/*头部*/
	@Autowired
	private NewsHeadInfoService newsHeadInfoService;
	
	@Autowired
	private NewsDetailsService newsDetailsService;
	
	@Autowired
	private MomentsResourcesService momentsResourcesService;
	
	@Autowired
	private NewsHeadInfoDao newsHeadInfoDao;
	
	@Test
	public void lunBo(){
		Map<String,Object> map = Maps.newHashMap();
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsHeadInfoController","lunBo", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void deleteNewsHeadInfo(){
		//this.request.getSession().setAttribute("clientId", "3e8d7853-911a-4ee5-96f5-b9e42f09c87a");
		Map<String,Object> map = Maps.newHashMap();
		map.put("newsId", "27f7b0e1-4abd-42d8-a21a-7b87a02a33be");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsHeadInfoController","deleteNewsHeadInfo", inbo);
		this.executeApiTest(apiInVm);
	}
	//获取新闻头部列表(带分页)
	@Test
	public void getNewsHeadInfoListWithPage(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("pageNum", 1);
		//map.put("title", "44");
		//map.put("pageSize", 10);
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsHeadInfoController","getNewsHeadInfoListWithPage", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void hotNewsList(){
		Map<String,Object> map = Maps.newHashMap();
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsHeadInfoController","hotNewsList", inbo);
		this.executeApiTest(apiInVm);
	}
	//
	@Test
	public void getNewsHeadInformation(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("newsId", "1f440062-7114-4158-b441-2b8424e22f71");
		map.put("ClientId", "150c60ea-1c33-4c59-9848-f1ff63e34dbc");
		map.put("isWeixin", "212");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsHeadInfoController","getNewsHeadInformation", inbo);
		this.executeApiTest(apiInVm);
	}
	
	//刷新点赞书和评论数
	@Test
	public void flushNewHeadInfo(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("newsId", "160edd64-4cfa-455e-9efe-982b3f9e6f4c");
		map.put("clientId", "056ff254-979c-4e1d-88d8-baadf2c04b85");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsHeadInfoController","flushNewHeadInfo", inbo);
		this.executeApiTest(apiInVm);
	}

	/*头部*/
	
	/*内容*/
	@Test
	public void saveNewsDetails(){
		//this.request.setAttribute("upload", new File("D://11.jpg"));
		Map<String,Object> map = Maps.newHashMap();
		map.put("newsId", "5f578cd9-41b4-43f8-a1f8-fe7acd7abfcc");
		map.put("contents", "PPPPPPPPPPPPPPPPPPPP");
		map.put("newsSeq", 3);
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsDetailsController","saveNewsDetails", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void getNewsDetails(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("newsId", "024b1d7b-4c1d-40ae-b7d5-8edd30759e73");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsDetailsController","getNewsDetailsById", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void deleteNewsDetails(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("newsId", "5f578cd9-41b4-43f8-a1f8-fe7acd7abfcc");
		map.put("newsdetailsId", "ce87ce00-0302-4194-9c57-320e42d419a1");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsDetailsController","deleteNewsDetailsById", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void saveNewsComments(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("newsId", "049b9a67-f916-485c-8ae1-aa8dc0100f85");
		map.put("clientId", "1");
		map.put("content", "评论内容：LKKKKKKKKKKKKKKKKKKKKKKKKKKKKKK");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsCommentController","saveNewsComment", inbo);
		this.executeApiTest(apiInVm);
	}
	
	
	@Test
	public void getNewsComments(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("newsId", "024b1d7b-4c1d-40ae-b7d5-8edd30759e73");
		map.put("pageNum", 3);
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsCommentController","getCommentWithPage", inbo);
		this.executeApiTest(apiInVm);
	}
	
	//评论授权显示
	@Test
	public void toshow(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("commentId", "0d98c76a-b17c-4ff5-a895-0d3f8dd1f80e");
		map.put("ishow", true);
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsCommentController","toShow", inbo);
		this.executeApiTest(apiInVm);
	}
	//显示新闻下的所有评论
	@Test
	public void toshowAll(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("newId", "5f578cd9-41b4-43f8-a1f8-fe7acd7abfcc");
		map.put("ishow", true);
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsCommentController","toShowAll", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void deleteNewsComments(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("newId", "282c766e-9713-4631-a5f9-a296f6a81366");
		map.put("commentid", "0cadf89e-3b7a-4324-ae59-712d83e8d2fc");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsCommentController","deleteNewsComment", inbo);
		this.executeApiTest(apiInVm);
	}
	
	
	@Test
	public void saveNewsClickLike(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("newsId", "024b1d7b-4c1d-40ae-b7d5-8edd30759e73");
		map.put("clientId", "54e46bb5-91ce-4663-beb2-db093504248c");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsClickLikeController","saveNewsClickLike", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void deleteNewsClickLike(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("newsId", "024b1d7b-4c1d-40ae-b7d5-8edd30759e73");
		map.put("clientId", "3e8d7853-911a-4ee5-96f5-b9e42f09c87a");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.news, "newsClickLikeController","deleteClickLike", inbo);
		this.executeApiTest(apiInVm);
	}
	/*点赞*/
}