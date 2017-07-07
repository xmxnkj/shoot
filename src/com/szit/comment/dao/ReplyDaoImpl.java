package com.szit.comment.dao;

import com.szit.comment.entity.Reply;
import com.szit.comment.entity.query.ReplyQuery;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.EntityOrder;
import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.hibernate.HibernateDao;

@Repository
public class ReplyDaoImpl extends HibernateDao<Reply, ReplyQuery> implements ReplyDao {
	
	@Override
	public List<QueryParam> buildQueryParams(ReplyQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		
		if (query != null) {
			if (!StringUtils.isEmpty(query.getReportId())) {
				qps.add(new QueryParam("reportId", query.getReportId()));
			}
			if (!StringUtils.isEmpty(query.getReplyUserId())) {
				qps.add(new QueryParam("replyUser.id", query.getReplyUserId()));
			}
			if (query.getReplyDateLower() != null) {
				qps.add(new QueryParam("replyDate", query.getReplyDateLower(), ParamCompareType.LargeEqual));
			}
			if (query.getReplyDateUpper()!=null) {
				qps.add(new QueryParam("replyDate", query.getReplyDateUpper(), ParamCompareType.SmallEqual));
			}
		}
		
		
		return qps;
	}
	
	@Override
	public List<EntityOrder> buildEntityOrders(ReplyQuery query) {
		List<EntityOrder> orders = new ArrayList<>();
		orders.add(new EntityOrder("replyDate", true));
		return orders;
	}
}
