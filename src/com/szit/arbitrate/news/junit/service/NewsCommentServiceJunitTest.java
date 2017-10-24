package com.szit.arbitrate.news.junit.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.news.dao.NewsCommentDao;
import com.szit.arbitrate.news.entity.NewsComment;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.entity.enumvo.NewsCommentIshowEnum;
import com.szit.arbitrate.news.service.NewsCommentService;
import com.szit.arbitrate.news.service.NewsDetailsService;
import com.szit.arbitrate.news.service.NewsHeadInfoService;

/**
 * 
 * 	@author chenpj
 * 	@date: 	下午4:24:53
 *	@Descript 	新闻评论业务测试类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
public class NewsCommentServiceJunitTest extends BaseApiJunitTest{

	@Autowired
	private NewsCommentService newsCommentService;
	
	@Autowired
	private NewsCommentDao newsCommentDao;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private NewsDetailsService newsDetailsService;
	
	@Autowired
	private NewsHeadInfoService newsHeadInfoService;
	
	
	
	private Logger logger = LoggerFactory.getLogger(NewsCommentServiceJunitTest.class);
	
	//注册
	@Test
	public void save(){
		List<NewsComment> list = newsCommentService.getEntities(null);
		for(NewsComment nc:list){
			nc.setCommentclientid("1");
			newsCommentService.save(nc);
		}
		//newsCommentService.saveComment("af229e52-94e3-4480-96ee-2f310b9b8861", "54e46bb5-91ce-4663-beb2-db093504248c", "hello","localhost");
	}
	
	@Test
	public void drop(){
		newsCommentService.deleteById("945158e6-2287-4155-8999-5775bafb1072");
	}
	
	@Test
	public void toShow(){
		//newsCommentService.toShow("ed54af56-5da3-4385-bb73-bcbf366f94c4",false);
		
		Client client = clientService.getById("1");
		
		NewsHeadInfo newsHeadInfo = newsHeadInfoService.getById("486d76eb-14fd-4769-b90f-0afde771ff9e");
			for(int i = 1;i<10;i++){
				NewsComment comment = new NewsComment();
				comment.setNewsId(newsHeadInfo.getId());
				comment.setCommentcontent("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX阿斯偶时间阿佛");
				comment.setCommentdatetime(new Date());
				comment.setIshow(NewsCommentIshowEnum.YES);
				comment.setCommentclientid("1");
				newsCommentService.save(comment);
			}
		
	}
	
	@Test
	public void t(){
		
/*	1124fcce-0d9c-4578-a632-0a0a53c8f782
*/	}
}
