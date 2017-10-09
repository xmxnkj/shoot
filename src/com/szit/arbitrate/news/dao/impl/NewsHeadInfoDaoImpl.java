package com.szit.arbitrate.news.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.news.dao.NewsHeadInfoDao;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.entity.query.NewsHeadInfoQuery;
import com.szit.arbitrate.news.service.MomentsResourcesService;

/**
 * 
 * 	@author chenpj
 * 	@date: 	下午3:48:51
 *	@Descript 	新闻标头数据库操作接口实现类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@Repository
public class NewsHeadInfoDaoImpl extends BaseHibernateDaoImpl<NewsHeadInfo, NewsHeadInfoQuery>
implements NewsHeadInfoDao
{
	@Autowired
	public MomentsResourcesService momentsResourcesService;
	
	@Override
	public List<QueryParam> buildQueryParams(NewsHeadInfoQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (query.getAuthorName()!=null) {
				qps.add(new QueryParam("authorName", query.getAuthorName()));
			}
			if (query.getArticletitle()!=null) {
				qps.add(new QueryParam("articletitle", query.getArticletitle(), ParamCompareType.Like));
			}
			if (query.getIshow()!=null) {
				qps.add(new QueryParam("ishow", query.getIshow()));
			}
		}
		
		return qps;
	}
}