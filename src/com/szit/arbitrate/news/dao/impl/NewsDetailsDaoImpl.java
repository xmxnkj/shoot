package com.szit.arbitrate.news.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.news.dao.NewsDetailsDao;
import com.szit.arbitrate.news.entity.NewsDetails;
import com.szit.arbitrate.news.entity.query.NewsDetailsQuery;

/**
 * 
 * 	@author chenpj
 * 	@date: 	下午3:48:46
 *	@Descript 	新闻内容数据库操作接口实现类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@Repository
public class NewsDetailsDaoImpl extends BaseHibernateDaoImpl<NewsDetails, NewsDetailsQuery>
implements NewsDetailsDao
{


	
	@Override
	public List<QueryParam> buildQueryParams(NewsDetailsQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getNewsId())) {
				qps.add(new QueryParam("newsId", query.getNewsId()));
			}
			if (!StringUtils.isEmpty(query.getId())) {
				qps.add(new QueryParam("id", query.getId()));
			}
		}
		return qps;
	}

	@Override
	public void deleteByNewsId(String newsId) {
		StringBuffer str = new StringBuffer();
		Map<String,Object> map = new HashMap<>();
		str.append("DELETE FROM NewsDetails where newsId=:newsId");
		map.put("newsId", newsId);
		this.executeUpdateHql(str.toString(), map);
	}

}
