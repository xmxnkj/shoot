package com.szit.arbitrate.news.junit.service;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.news.service.NewsHeadInfoService;

/**
 * 
 * 	@author chenpj
 * 	@date: 	下午4:26:30
 *	@Descript 	新闻点赞业务测试类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
public class NewsClickLikeServiceJunitTest extends BaseApiJunitTest{

	@Autowired
	private NewsHeadInfoService newsHeadInfoService;
	
	private Logger logger = LoggerFactory.getLogger(NewsClickLikeServiceJunitTest.class);
	
	@Test
	public void save(){

	}
}