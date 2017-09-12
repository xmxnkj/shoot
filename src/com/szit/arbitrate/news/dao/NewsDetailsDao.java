package com.szit.arbitrate.news.dao;

import com.hsit.common.dao.BaseHibernateDao;
import com.szit.arbitrate.news.entity.NewsDetails;
import com.szit.arbitrate.news.entity.query.NewsDetailsQuery;

/**
 * 
 * 	@author chenpj
 * 	@date: 	下午3:59:04
 *	@Descript 	新闻详细类 数据库操作接口类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
public interface NewsDetailsDao extends BaseHibernateDao<NewsDetails, NewsDetailsQuery>{

	public void deleteByNewsId(String newsId);
}
