package com.szit.arbitrate.mediation.junit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.hsit.common.utils.DocUtil;
import com.hsit.common.utils.JsonFormatUtil;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.mediation.entity.LegalDoc;
import com.szit.arbitrate.mediation.entity.LegalDocDetail;
import com.szit.arbitrate.mediation.entity.query.LegalDocDetailQuery;
import com.szit.arbitrate.mediation.service.LegalDocDetailService;
import com.szit.arbitrate.mediation.service.LegalDocService;

public class LegalDocServiceJunit extends BaseApiJunitTest{
	
	@Autowired
	private LegalDocService legalDocService;
	@Autowired
	private LegalDocDetailService legalDocDetailService;
	
	//private Logger logger = LoggerFactory.getLogger(ClientServiceJunit.class);
	
	//根据分类获取法规文档列表
	@Test
	public void getLegalDocListByDocType(){
		String  doctype="0";
		List<LegalDoc> list=legalDocService.getLegalDocListByDocType(doctype);
		JsonFormatUtil.printJson("list",list);
	}
	
	//根据分类获取法规文档列表
	@Test
	public void getLegalDocDetailById(){
		String  legaldocid="b3a4b84a-e1f7-4745-80a0-407aa654cacf";
		
		JsonFormatUtil.printJson("list",legalDocService.getLegalDocDetailById(legaldocid));
	}
	
	//删除法规文档
	@Test
	public void deleteLegalDoc(){

		legalDocService.deleteLegalDoc("9117bb0a-3f3f-48de-860f-f313b4630506");

	}

	@Test
	public void testCreateDoc(){
		String legaldocid = "563dfe27-1786-4fa2-b095-26a5f3f8e349";
		LegalDoc entity = legalDocService.getById(legaldocid);
		LegalDocDetailQuery query = new LegalDocDetailQuery();
		query.setLegalDocId(legaldocid);
		List<LegalDocDetail> detaillist = legalDocDetailService.getEntities(query);
		
		Map<String, Object> dataMap=new HashMap<String, Object>();   
        dataMap.put("title", entity.getTitle());
        dataMap.put("publishUnit", entity.getPublishUnit());
        dataMap.put("publishTime", entity.getPublishTime());
        dataMap.put("content", entity.getContent());
        String url = "uploads/mediation/legaldoc";
        String templateurl = "/com/hsit/common/template/legaldoc";
		DocUtil util = new DocUtil();
		util.createDoc(dataMap, templateurl, "legaldoc.ftl", url, legaldocid);
	}
	
	@Test
	public void upAndDownShelve(){
		Map<String, Object> params = Maps.newHashMap();
		params.put("legaldocid", "356c78fc-04a2-4394-bf12-38baf233bf20");
		params.put("oper", "1");
		legalDocService.upAndDownShelve(params);
	}
	@Test
	public void testDoc(){
		Map<String, Object> dataMap=new HashMap<String, Object>();   
        dataMap.put("name", "666666666666666666");
        String url = "uploads/mediation/legaldoc";
        String templateurl = "/com/hsit/common/template";
		DocUtil util = new DocUtil();
		util.createDoc(dataMap, templateurl, "test.ftl", url, "test");
	}

}
