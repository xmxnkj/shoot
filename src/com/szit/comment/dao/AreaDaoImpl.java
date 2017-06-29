package com.szit.comment.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.EntityOrder;
import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.hibernate.HibernateDao;
import com.szit.comment.entity.Area;
import com.szit.comment.entity.query.AreaQuery;

/**
 * 区域持久类
 * @author linzf
 *
 */
@Repository
public class AreaDaoImpl extends HibernateDao<Area, AreaQuery> implements AreaDao{
	/**
	 * 查询条件
	 */
	@Override
	public List<QueryParam> buildQueryParams(AreaQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		
		if (query != null) {
			if (!StringUtils.isEmpty(query.getParentId())) {
				qps.add(new QueryParam("parentId", query.getParentId()));
			}
			if (query.getIsRoot() != null && query.getIsRoot().booleanValue()) {
				qps.add(new QueryParam("parentId", null, ParamCompareType.IsNull));
			}
		}
		
		
		return qps;
	}
	
	/**
	 * 排序
	 */
	@Override
	public List<EntityOrder> buildEntityOrders(AreaQuery query) {
		
		if (query != null && query.getOrders()!=null) {
			return query.getOrders();
		}
		
		List<EntityOrder> orders = new ArrayList<>();
		
		orders.add(new EntityOrder("displayOrder"));
		//orders.add(new EntityOrder("parentId"));
		orders.add(new EntityOrder("code"));
		
		return orders;
	}
}
