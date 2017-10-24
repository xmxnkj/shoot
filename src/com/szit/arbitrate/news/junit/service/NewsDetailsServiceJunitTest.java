package com.szit.arbitrate.news.junit.service;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.baidubce.util.JsonUtils;
import com.hsit.common.utils.JsonFormatUtil;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.news.entity.NewsDetails;
import com.szit.arbitrate.news.service.NewsDetailsService;

/**
 * 
 * 	@author chenpj
 * 	@date: 	下午4:23:22
 *	@Descript 	新闻内容实体业务测试类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
public class NewsDetailsServiceJunitTest extends BaseApiJunitTest{

	@Autowired
	private NewsDetailsService newsDetailsService;
	
	@Autowired
	private ClientService clientService;
	
	
	private Logger logger = LoggerFactory.getLogger(NewsDetailsServiceJunitTest.class);
	
	@Test
	public void getDetails(){
		
		List<NewsDetails> list = newsDetailsService.findNewsDetailsList("3487663d-7bb7-40e1-8b6d-6f2d2e6c343c", 1, 10);
		
		JsonFormatUtil.printJson("list", list);
		
	}

}
