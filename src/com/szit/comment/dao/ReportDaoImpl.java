package com.szit.comment.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.EntityOrder;
import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.hibernate.HibernateDao;
import com.hsit.common.utils.DateUtil;
import com.szit.comment.entity.Report;
import com.szit.comment.entity.query.ReportQuery;

@Repository
public class ReportDaoImpl extends HibernateDao<Report, ReportQuery> implements ReportDao{
	
	@Override
	public List<QueryParam> buildQueryParams(ReportQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		
		if (query != null) {
			if (!StringUtils.isEmpty(query.getReportUserId())) {
				qps.add(new QueryParam("reportUserId", query.getReportUserId()));
			}
			if (!StringUtils.isEmpty(query.getContentKey())) {
				qps.add(new QueryParam("content", query.getContentKey(), ParamCompareType.Like));
			}
			if (!StringUtils.isEmpty(query.getReportUserName())) {
				//qps.add(new QueryParam("reportUserName", query.getReportUserName(), ParamCompareType.Like));
				qps.add(new QueryParam("appUser.userName", query.getReportUserName(), ParamCompareType.Like));
				//QueryParam qp = new QueryParam();
				//qp.setName(" (reportUserName LIKE '%'||:userName||'%' OR )");
			}
			if (query.getReportTimeLower()!=null) {
				qps.add(new QueryParam("reportTime", DateUtil.getLowerDate(query.getReportTimeLower()), ParamCompareType.LargeEqual));
			}
			if (query.getReportTimeUpper()!=null) {
				qps.add(new QueryParam("reportTime", DateUtil.getUpperDate(query.getReportTimeUpper()), ParamCompareType.SmallEqual));
			}
			if (query.getReplyTimeUpper()!=null) {
				qps.add(new QueryParam("replytTime", DateUtil.getUpperDate(query.getReplyTimeUpper()), ParamCompareType.SmallEqual));
			}
			if (query.getReplyTimeLower() != null) {
				qps.add(new QueryParam("replyTime", DateUtil.getLowerDate(query.getReplyTimeLower()), ParamCompareType.LargeEqual));
			}
			if (query.getReportStatus()!=null) {
				qps.add(new QueryParam("reportStatus", query.getReportStatus()));
			}
			if (query.getEmptyArea()!=null) {
				qps.add(new QueryParam("area", null, ParamCompareType.IsNull));
			}
			if (!StringUtils.isEmpty(query.getAreaId())) {
				qps.add(new QueryParam("area.id", query.getAreaId()));
			}
			if (!StringUtils.isEmpty(query.getCategoryId())) {
				qps.add(new QueryParam("category.id", query.getCategoryId()));
			}
			if (!StringUtils.isEmpty(query.getContentTypeId())) {
				qps.add(new QueryParam("contentType.id", query.getContentTypeId()));
			}
			if (!StringUtils.isEmpty(query.getEventTypeId())) {
				qps.add(new QueryParam("eventType.id", query.getEventTypeId()));
			}
			if (query.getNotEqualReportStatus()!=null) {
				qps.add(new QueryParam("reportStatus", query.getNotEqualReportStatus(), ParamCompareType.NotEqual));
			}
			if (query.getIsPublic()!=null) {
				qps.add(new QueryParam("isPublic", query.getIsPublic()));
			}
		}
		
		
		return qps;
	}
	
	@Override
	public List<EntityOrder> buildEntityOrders(ReportQuery query) {
		List<EntityOrder> orders = new ArrayList<>();
		
		orders.add(new EntityOrder("reportTime", true));
		
		return orders;
	}
}
