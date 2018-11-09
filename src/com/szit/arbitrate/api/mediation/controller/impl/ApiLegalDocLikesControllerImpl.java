package com.szit.arbitrate.api.mediation.controller.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.mediation.controller.ApiLegalDocLikesController;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.entity.LegalDoc;
import com.szit.arbitrate.mediation.entity.LegalDocLikes;
import com.szit.arbitrate.mediation.entity.query.LegalDocLikesQuery;
import com.szit.arbitrate.mediation.service.LegalDocLikesService;
import com.szit.arbitrate.mediation.service.LegalDocService;

@Component("wapApiLegalDocLikesControllerImpl")
public class ApiLegalDocLikesControllerImpl extends BaseController<LegalDocLikes, LegalDocLikesQuery> 
	implements ApiLegalDocLikesController{

	@Autowired
	private ClientService clientService;
	@Autowired
	private LegalDocLikesService service;
	@Autowired
	private LegalDocService legalDocService;
	
	@Override
	public ApiOutParamsVm addLegalDocLikes(String legaldocid) {
		String likesclientid = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(likesclientid)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.addLegalDocLikes(legaldocid, likesclientid);
		return ApiTools.bulidOutSucceed("添加成功!");
	}
	
	@Override
	public ApiOutParamsVm refreshLegalDoc(String legaldocid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		LegalDoc entity = legalDocService.getById(legaldocid);
		resultMap.put("entity", entity);
		LegalDocLikesQuery query = new LegalDocLikesQuery();
		query.setLegalDocId(legaldocid);
		query.setLikeClientId(clientId);
		LegalDocLikes legalDocLikes = service.getEntity(query);
		resultMap.put("isClickLike", legalDocLikes!=null?true:false);
		return ApiTools.bulidOutSucceed("操作成功",resultMap);
	}
	
	@Override
	public ApiOutParamsVm cancelLegalDocLikes(String legaldocid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.cancelLegalDocLikes(legaldocid, clientId);
		return ApiTools.bulidOutSucceed("取消成功!");
	}
	
}
