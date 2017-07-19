package com.szit.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.comment.dao.EventTypeDao;
import com.szit.comment.entity.EventType;
import com.szit.comment.entity.query.EventTypeQuery;

/**
 * 事件类型逻辑类
 * @author linzf
 *
 */
@Service
public class EventTypeServiceImpl extends AppBaseServiceImpl<EventType, EventTypeQuery> implements EventTypeService{
	@Autowired
	private EventTypeDao dao;

	@Override
	public EventTypeDao getDao() {
		return dao;
	}
}
