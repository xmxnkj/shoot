package com.szit.arbitrate.api.mediation.controller.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.utils.PageUtils;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.common.vm.PageDataOutBoVm;
import com.szit.arbitrate.api.mediation.controller.ApiLegalDocCommentsController;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.entity.LegalDocComments;
import com.szit.arbitrate.mediation.entity.query.LegalDocCommentsQuery;
import com.szit.arbitrate.mediation.service.LegalDocCommentsService;

@Component("wapApiLegalDocCommentsControllerImpl")
public class ApiLegalDocCommentsControllerImpl extends BaseController<LegalDocComments, LegalDocCommentsQuery>
	implements ApiLegalDocCommentsController{
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private LegalDocCommentsService service;
	
	@Override
	public ApiOutParamsVm addLegalDocComments(String legaldocid, String commentscontent) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.addLegalDocComments(legaldocid, clientId, commentscontent);
		return ApiTools.bulidOutSucceed("添加成功,等待管理员审核通过后方可显示!");
	}

	@Override
	public ApiOutParamsVm deleteLegalDocComments(String commentsid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		LegalDocCommentsQuery query = new LegalDocCommentsQuery();
		query.setCommentsClientId(clientId);
		query.setId(commentsid);
		LegalDocComments entity = service.getEntity(query);
		if(entity != null){
			service.deleteById(commentsid);
		}
		return ApiTools.bulidOutSucceed("删除成功!");
	}
	
	@Override
	public ApiOutParamsVm getCommentsListByDocId(String legaldocid, Integer page) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		LegalDocCommentsQuery query = new LegalDocCommentsQuery();
		query.setLegalDocId(legaldocid);
		query.setPage(page!=null?page:0, PageUtils.PAGE_SIZE);
		query.setAudit(true);
		List<LegalDocComments> list = service.getEntities(query);
		
		PageDataOutBoVm<LegalDocComments> pageList = new PageDataOutBoVm<LegalDocComments>(query.getPaging(),list);
		return ApiTools.bulidOutSucceed("获取数据成功!", pageList);
	}
	
}
