/**
 * File Name: PositionGradeAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.uac;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.uac.entity.PositionGrade;
import com.hsit.common.uac.entity.queryparam.PositionGradeQuery;
import com.hsit.common.uac.service.PositionGradeService;

/**
 * @ClassName:PositionGradeAction
 * @date 2017-3-28 下午3:16:10
 * 
 */
@Component("positionGradeAction")
@Scope("prototype")
public class PositionGradeAction extends BaseAction<PositionGrade, PositionGradeQuery> {
	private PositionGradeService service;

	@Override
	public PositionGradeService getService() {
		return service;
	}

	@Autowired
	public void setService(PositionGradeService service) {
		this.service = service;
	}
	
}
