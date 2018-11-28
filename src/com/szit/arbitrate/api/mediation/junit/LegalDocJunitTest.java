package com.szit.arbitrate.api.mediation.junit;

import java.util.Date;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.mediation.entity.LegalDoc;
import com.szit.arbitrate.mediation.entity.enumvo.DocTypeEnum;
import com.szit.arbitrate.mediation.service.LegalDocService;

public class LegalDocJunitTest extends BaseApiJunitTest{

	@Autowired
	private LegalDocService service;
	
	@Test
	public void getLegalDocListTest(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("doctype", "Policy");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiLegalDocController","getLegalDocList", inbo);
		this.executeApiTest(apiInVm);
	}
	@Test
	public void getLegalDocDetailById(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("legaldocid", "7083612b-bc82-475a-ae12-cb2f83ac7b16");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiLegalDocController","getLegalDocDetailById", inbo);
		this.executeApiTest(apiInVm);
	}
	@Test
	public void addNewLegalDocLikes(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("legaldocid", "7083612b-bc82-475a-ae12-cb2f83ac7b16");
		map.put("clientid", "ab60b099-f2c7-4807-b3a9-69603b1f1163");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiLegalDocController","addNewLegalDocLikes", inbo);
		this.executeApiTest(apiInVm);
	}
	@Test
	public void addLegalDocMoments(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("legaldocid", "7083612b-bc82-475a-ae12-cb2f83ac7b16");
		map.put("clientid", "ab60b099-f2c7-4807-b3a9-69603b1f1163");
		map.put("momentscontent", "测试");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiLegalDocController","addLegalDocMoments", inbo);
		this.executeApiTest(apiInVm);
	}
	@Test
	public void addLegalDocTest(){
		for(int i = 0; i < 10; i ++){
			LegalDoc entity = new LegalDoc();
			entity.setTitle("测试文档"+i+1);
			entity.setDocType(DocTypeEnum.Policy);
			entity.setPublishUnit("厦门市");
			entity.setPublishTime(new Date());
			service.save(entity);
		}
	}
	
}
