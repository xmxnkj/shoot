package com.szit.arbitrate.api.client.controller.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.client.controller.ApiSmsVerifyController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.client.entity.SmsVerifyRecord;
import com.szit.arbitrate.client.entity.enumvo.SmsBizModelEnum;
import com.szit.arbitrate.client.entity.query.SmsVerifyRecordQuery;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.SmsVerifyRecordService;

/**   
*    
* 项目名称：XMSZIT-COWELL
* 项目说明：运动APP项目
* 类名称：ApiSmsVerifyControllerImpl   
* 类描述：：API接口短信验证控制实现类
* 事件记录：
* 创建人：Administrator  
* 创建时间：2017年12月31日 上午9:12:25
* 厦门西牛科技有限公司科技有限公司
* @version 1.0 
*    
*/
@Component("apiSmsVerifyControllerImpl")
public class ApiSmsVerifyControllerImpl extends BaseController<SmsVerifyRecord, SmsVerifyRecordQuery>
		implements ApiSmsVerifyController {

	@Autowired
	private ClientService clientService;
	@Autowired
	public SmsVerifyRecordService smsVerifyRecordService;
	
	
	
	@Override
	public ApiOutParamsVm toGetSmsCode(String phone,String bizmodel) {
	    SmsBizModelEnum smsBizModelEnum = SmsBizModelEnum.valueOf(bizmodel);
		Map<String,Object> resultMap = smsVerifyRecordService.toGetSmsCode(phone,smsBizModelEnum);
		return ApiTools.bulidOutSucceed("短信验证码获取成功!",resultMap);
	}
	
	

}
