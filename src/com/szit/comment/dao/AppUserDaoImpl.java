package com.szit.comment.dao;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.hibernate.HibernateDao;
import com.szit.comment.entity.AppUser;
import com.szit.comment.entity.query.AppUserQuery;

@Repository
public class AppUserDaoImpl extends HibernateDao<AppUser, AppUserQuery> implements AppUserDao {
	@Override
	public List<QueryParam> buildQueryParams(AppUserQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		
		if (query!=null) {
			if (query.getUserId()!=null) {
				qps.add(new QueryParam("userId", query.getUserId()));
			}
			if (!StringUtils.isEmpty(query.getUserName())) {
				qps.add(new QueryParam("userName", query.getUserName()));
			}
		}
		
		return qps;
	}
}
