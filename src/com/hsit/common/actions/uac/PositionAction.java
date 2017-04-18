/**
 * File Name: PositionAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.uac;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hsit.common.uac.dao.PositionDao;
import com.hsit.common.uac.entity.Position;
import com.hsit.common.uac.entity.queryparam.PositionQuery;
import com.hsit.common.uac.service.PositionService;

import com.hsit.common.actions.BaseAction;

/**
 * @ClassName:PositionAction
 * @date 2017-3-28 下午2:41:59
 * 
 */
@Component("positionAction")
@Scope("prototype")
public class PositionAction extends BaseAction<Position, PositionQuery> {
	private PositionService service;

	@Override
	public PositionService getService() {
		return service;
	}

	@Autowired
	public void setService(PositionService service) {
		this.service = service;
	}
	
}
