package com.szit.comment.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.EntityOrder;
import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.hibernate.HibernateDao;
import com.szit.comment.entity.Comment;
import com.szit.comment.entity.query.CommentQuery;

@Repository
public class CommentDaoImpl extends HibernateDao<Comment, CommentQuery> implements CommentDao{
	
	@Override
	public List<QueryParam> buildQueryParams(CommentQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		
		if (query != null) {
			if (!StringUtils.isEmpty(query.getReportId())) {
				qps.add(new QueryParam("reportId", query.getReportId()));
			}
		}
		
		
		return qps;
	}
	
	@Override
	public List<EntityOrder> buildEntityOrders(CommentQuery query) {
		
		if (query!=null && query.getOrders()!=null && query.getOrders().size()>0) {
			return query.getOrders();
		}
		
		List<EntityOrder> orders = new ArrayList<>();
		
		orders.add(new EntityOrder("commentTime", true));
		
		return orders;
	}
}
