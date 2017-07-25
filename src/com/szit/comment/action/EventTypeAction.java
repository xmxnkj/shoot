package com.szit.comment.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseAction;
import com.szit.comment.entity.EventType;
import com.szit.comment.entity.query.EventTypeQuery;
import com.szit.comment.service.EventTypeService;

/**
 * 事件类型控制器
 * @author linzf
 *
 */
@Controller("eventTypeAction")
@Scope("prototype")
public class EventTypeAction extends BaseAction<EventType, EventTypeQuery>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2771393609720154081L;
	@Autowired
	private EventTypeService service;

	@Override
	public EventTypeService getService() {
		return service;
	}
	
}
