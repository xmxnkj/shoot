package com.szit.arbitrate.api.mediation.controller.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsit.common.exceptions.BizException;
import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.utils.PageUtils;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.common.vm.PageDataOutBoVm;
import com.szit.arbitrate.api.mediation.controller.ApiLegalDocController;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.entity.LegalDoc;
import com.szit.arbitrate.mediation.entity.enumvo.ClassicCaseTypeEnum;
import com.szit.arbitrate.mediation.entity.enumvo.DocTypeEnum;
import com.szit.arbitrate.mediation.entity.query.LegalDocQuery;
import com.szit.arbitrate.mediation.service.LegalDocDetailService;
import com.szit.arbitrate.mediation.service.LegalDocService;
import com.szit.neteasecloud.utils.HtmlCodeToText;

@Component("wapApiLegalDocController")
public class ApiLegalDocControllerImpl extends BaseController<LegalDoc, LegalDocQuery> implements ApiLegalDocController{

	@Autowired
	private ClientService clientService;
	@Autowired
	private LegalDocService service;
	@Autowired
	private LegalDocDetailService legalDocDetailService;
	
	@Override
	public ApiOutParamsVm getLegalDocList(String doctype, String classiccasetype,
			String docname, Integer page) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		LegalDocQuery query = new LegalDocQuery();
		if(StringUtils.isNotEmpty(docname)){
			query.setTitle(docname);
		}
		if(StringUtils.isNotEmpty(classiccasetype)){
			query.setClassicCase(ClassicCaseTypeEnum.valueOf(classiccasetype));
		}
		if(StringUtils.isEmpty(doctype)){
			throw new BizException("类型不能为空!");
		}
		query.setDisplay(true);
		query.setDocType(DocTypeEnum.valueOf(doctype));
		query.setPage(page!=null?page:0, PageUtils.PAGE_SIZE);
		query.addOrder("publishTime", true);
		List<LegalDoc> list = service.getEntities(query);
		
		PageDataOutBoVm<LegalDoc> pageList = new PageDataOutBoVm<LegalDoc>(query.getPaging(),list);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,pageList);
	}

	@Override
	public ApiOutParamsVm getLegalDocDetailById(String legaldocid,String isWeixin) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String,Object> resultMap = new HashMap<String,Object>();
		LegalDoc entity = service.getById(legaldocid);
		if(isWeixin!=null && !isWeixin.equals("")){
			List<Map<String, Object>> list = HtmlCodeToText.getText(entity.getContent());
			resultMap.put("text", list);
			resultMap.put("obj", entity);
			return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,resultMap);
		}
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,entity);
	}

	@Override
	public ApiOutParamsVm getFilePath(String legaldocid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		if(legaldocid!=null && !legaldocid.equals("")){
			//LegalDoc legalDoc = service.getById(legaldocid);
			String folderPath = "uploads/mediation/legaldoc/";
			String uploadPath = ServletActionContext.getServletContext().getRealPath(folderPath);
			//File toFile = new File(uploadPath, legalDoc.getFilePath());
			if(new File(uploadPath).exists()){
				map.put("result", "该附件存在");
				map.put("path", uploadPath);
			}else{
				map.put("result", "该附件不存在");
			}
		}else{
			map.put("result", "文案id不能为空");
		}
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,map);
	}
}