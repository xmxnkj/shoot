package com.szit.arbitrate.api.log.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.szit.arbitrate.api.log.entity.ApiRecordLog;
import com.szit.arbitrate.api.log.entity.query.ApiRecordLogQuery;
import com.szit.arbitrate.api.log.service.ApiRecordLogService;

/**   
*    
* 项目名称：XMSZIT-COWELL
* 项目说明：运动APP项目
* 类名称：ApiRecordLogAction   
* 类描述：
* 事件记录：
* 创建人：Administrator  
* 创建时间：2017年1月16日 上午10:32:47
* 厦门西牛科技有限公司科技有限公司
* @version 1.0 
*    
*/
@Controller("apiRecordLogAction")
public class ApiRecordLogAction extends BaseJsonAction<ApiRecordLog, ApiRecordLogQuery> {

	private static final long serialVersionUID = -655490745704348129L;
	
	@Autowired
	private ApiRecordLogService service;

	public ApiRecordLogService getService() {
		return service;
	}

	public void setService(ApiRecordLogService service) {
		this.service = service;
	}
	
	
	
	
	
	
}
