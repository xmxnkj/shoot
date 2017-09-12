package com.szit.arbitrate.news.dao;

import java.util.List;

import com.hsit.common.dao.BaseHibernateDao;
import com.hsit.common.utils.PagingBean;
import com.szit.arbitrate.news.entity.NewsComment;
import com.szit.arbitrate.news.entity.query.NewsCommentQuery;
/**
 * 
 * 	@author Administrator
 * 	@date: 	下午3:52:25
 *	@Descript 	新闻评论数据库操作接口类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
public interface NewsCommentDao extends BaseHibernateDao<NewsComment, NewsCommentQuery>{
	
	public List<NewsComment> getCommentsList(String newsId,PagingBean pagingBean);
	
	public void deleteByNewsId(String newsId);
}
