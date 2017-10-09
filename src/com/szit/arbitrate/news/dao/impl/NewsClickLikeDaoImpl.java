package com.szit.arbitrate.news.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.news.dao.NewsClickLikeDao;
import com.szit.arbitrate.news.entity.NewsClickLike;
import com.szit.arbitrate.news.entity.query.NewsClickLikeQuery;
/**
 * 
 * 	@author chenpj
 * 	@date: 	下午3:47:21
 *	@Descript 	新闻点赞数据库操作接口类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@Repository
public class NewsClickLikeDaoImpl extends BaseHibernateDaoImpl<NewsClickLike, NewsClickLikeQuery>
implements   NewsClickLikeDao
{

	@Override
	public List<QueryParam> buildQueryParams(NewsClickLikeQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getNewsid())) {
				qps.add(new QueryParam("newsid", query.getNewsid()));
			}
			if (query.getLikeclientid()!=null) {
				qps.add(new QueryParam("likeclientid", query.getLikeclientid()));
			}
		}
		return qps;
	}


}