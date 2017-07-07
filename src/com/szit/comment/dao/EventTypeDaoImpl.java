package com.szit.comment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hsit.common.dao.EntityOrder;
import com.hsit.common.dao.hibernate.HibernateDao;
import com.szit.comment.entity.EventType;
import com.szit.comment.entity.query.EventTypeQuery;

/**
 * 事件类型接口类
 * @author linzf
 *
 */
@Repository
public class EventTypeDaoImpl extends HibernateDao<EventType, EventTypeQuery> implements EventTypeDao{
	@Override
	public List<EntityOrder> buildEntityOrders(EventTypeQuery query) {
		
		return super.buildDisplayOrders();
	}
}
