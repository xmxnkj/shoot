package com.szit.arbitrate.news.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.hsit.common.utils.PagingBean;
import com.szit.arbitrate.news.dao.NewsCommentDao;
import com.szit.arbitrate.news.entity.NewsComment;
import com.szit.arbitrate.news.entity.query.NewsCommentQuery;
/**
 * 
 * 	@author chenpj
 * 	@date: 	下午3:48:33
 *	@Descript 	新闻回复数数据库操作接口实现类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@Repository
public class NewsCommentDaoImpl extends BaseHibernateDaoImpl<NewsComment, NewsCommentQuery>
implements  NewsCommentDao
{
	

	@Override
	public List<QueryParam> buildQueryParams(NewsCommentQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getNewsId())) {
				qps.add(new QueryParam("newsId", query.getNewsId()));
			}
			if (query.getIshow()!=null) {
				qps.add(new QueryParam("ishow", query.getIshow()));
			}
			
		}
		return qps;
	}

	@Override
	public List<NewsComment> getCommentsList(String newsId,PagingBean pagingBean) {
		
		StringBuffer str = new StringBuffer();
		str.append("FROM NewsComment where newsId=:newsId and ishow=true order by commentdatetime");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("newsId", newsId);
		List<NewsComment> list = this.findHqlToM(str.toString(), map ,pagingBean);
		
		return list;
	}

	@Override
	public void deleteByNewsId(String newsId) {
		StringBuffer str = new StringBuffer();
		str.append("DELETE FROM NewsComment where newsId=:newsId");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("newsId", newsId);
		this.executeUpdateHql(str.toString(), map);
	}

}