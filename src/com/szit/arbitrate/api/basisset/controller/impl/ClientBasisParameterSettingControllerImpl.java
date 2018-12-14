package com.szit.arbitrate.api.basisset.controller.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.basisset.controller.ClientBasisParameterSettingController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.utils.HttpSessionContext;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.basisset.entity.ClientBasisParameterSetting;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;
import com.szit.arbitrate.basisset.entity.query.ClientBasisParameterSettingQuery;
import com.szit.arbitrate.basisset.service.ClientBasisParameterSettingService;

/**
 * 
* @ClassName: ClientBasisParameterSettingControllerImpl
* @Description:基础设置-API控制器实现类
* @author Administrator
* @date 2017年3月20日 下午2:44:01
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Component("clientBasisParameterSettingController")
public class ClientBasisParameterSettingControllerImpl extends BaseController<ClientBasisParameterSetting, ClientBasisParameterSettingQuery>
		implements ClientBasisParameterSettingController {

	@Autowired
	private ClientBasisParameterSettingService clientBasisParameterSettingService; 
	
	@Override
	public ApiOutParamsVm setting(String parametertype, String parameterid,
			String parameterval) {
		String clientId = HttpSessionContext.getHttpSession(this.getRequest(),"clientId");
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		if(StringUtils.isEmpty(parametertype)){
			return ApiTools.bulidOutFail("非法参数,参数类型不能空!");
		}
		if(StringUtils.isEmpty(parameterid)){
			return ApiTools.bulidOutFail("非法参数,参数id不能为空 !");
		}
		if(StringUtils.isEmpty(parameterval)){
			return ApiTools.bulidOutFail("非法参数,参数设置值 不能为空 !");
		}
		ParameterTypeEnum parametertypenum = ParameterTypeEnum.valueOf(parametertype);
		clientBasisParameterSettingService.setting(parametertypenum,parameterid,parameterval,clientId);
		return ApiTools.bulidOutSucceed("设置成功！");
	}

	@Override
	public ApiOutParamsVm mySettingList(String parametertype) {
		String clientId = HttpSessionContext.getHttpSession(this.getRequest(),"clientId");
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		if(StringUtils.isEmpty(parametertype)){
			return ApiTools.bulidOutFail("非法参数,参数类型不能空!");
		}
		ParameterTypeEnum parametertypenum = ParameterTypeEnum.valueOf(parametertype);
		//条件ParameterTypeEnum.parametertype 然后根据类型过滤
		List<ClientBasisParameterSetting> list = clientBasisParameterSettingService.mySettingList(clientId,parametertypenum);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,list);
	}

	
	
}
