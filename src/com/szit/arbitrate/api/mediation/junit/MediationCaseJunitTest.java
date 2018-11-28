package com.szit.arbitrate.api.mediation.junit;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.client.service.ClientAuthorityGroupService;
public class MediationCaseJunitTest extends BaseApiJunitTest{
	
	@Autowired
	private ClientAuthorityGroupService clientAuthorityGroupService;
	
	@Test
	public void addTempClient(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("caseId", "");
		map.put("identifyName", "123");
		map.put("tel", "18805921191");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","addTempClient", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void deleteTempClient(){
		String inbo = this.getApiRecordLogForInBo("wapApiMediationCaseController_685631");
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","deleteTempClient", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void applyMediation(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("clientid", "54e46bb5-91ce-4663-beb2-db093504248c");
		map.put("caseid", "");
		map.put("casetype", "xxx");
		map.put("caseexplain", "上班不给钱2");
		map.put("address", "xxxx");
		map.put("type", 2);
		map.put("id", "54e46bb5-91ce-4663-beb2-db093504248c");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","applyMediation", inbo);
		this.executeApiTest(apiInVm);
	}
	
	//中心/机构 接收 申请
	@Test
	public void acceptMediationCase(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("caseid", "9a144bde-8124-4cff-987b-7e2a8e01a640");
		map.put("mediatorid", "7d64d0f1-e01a-473a-ab42-7bea7cbcdf6e");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","acceptMediationCase", inbo);
		this.executeApiTest(apiInVm);
	}
	@Test
	public void allocateMediationCase(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("clientid", "5f17fd6d-6fb9-4126-b28d-28f91315cc65");
		map.put("caseid", "9a144bde-8124-4cff-987b-7e2a8e01a640");
		map.put("mediatorid", "7d64d0f1-e01a-473a-ab42-7bea7cbcdf6e");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","allocateMediationCase", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void refuseMediationCase(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("clientid", "54e46bb5-91ce-4663-beb2-db093504248c");
		map.put("caseid", "9a144bde-8124-4cff-987b-7e2a8e01a640");
		map.put("refusereason", "WLGCC");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","refuseClientMediationApply", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void backToUpperLevel(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("clientid", "54e46bb5-91ce-4663-beb2-db093504248c");
		map.put("caseid", "9a144bde-8124-4cff-987b-7e2a8e01a640");
		map.put("reason", "WLGCC");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","backToUpperLevel", inbo);
		this.executeApiTest(apiInVm);
	}
	
	//根据用户id获取列表
	@Test
	public void getMediationCaseListByClientId(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("clientid", "726073f8-01fb-4853-b85f-0750ddc9a551");
		map.put("casestate", "Init");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","getMediationCaseListByClientId", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void getListByClientIdAndCaseType(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("clientid", "1");
		//map.put("clientid", "1");
		map.put("tabindex", 0);
		String inbo = jsonMapper.toJson(map);
		//String inbo = this.getApiRecordLogForInBo("wapApiMediationCaseController_899305");
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","getListByClientIdAndCaseType", inbo);
		this.executeApiTest(apiInVm);
	}
	@Test
	public void completeMediationCase(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("clientid", "7d64d0f1-e01a-473a-ab42-7bea7cbcdf6e");
		map.put("caseid", "9a144bde-8124-4cff-987b-7e2a8e01a640");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","completeMediationCase", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void giveupMediation(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("clientid", "54e46bb5-91ce-4663-beb2-db093504248c");
		map.put("caseid", "efb0486f-a5bb-4f2c-aa5b-5f6a5872f333");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","giveupMediation", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void getCaseDistribution(){
		Map<String,Object> map = Maps.newHashMap();
		map.put("clientid", "1");
		map.put("startTime", "2010-05-04 15:27:13");
		map.put("endTime", "2018-05-04 15:27:13");
		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","getCaseDistribution", inbo);
		this.executeApiTest(apiInVm);
	}
	@Test
	public void gather(){
		String inbo = this.getApiRecordLogForInBo("wapApiMediationCaseController_649403");
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationCaseController","gatherTempClient", inbo);
		this.executeApiTest(apiInVm);
	}
	
	@Test
	public void T1(){
		Map<String,Object> map = Maps.newHashMap();

		String inbo = jsonMapper.toJson(map);
		ApiInParamsVm  apiInVm = ApiTools.bulidInparam(ApiTools.MODULECODE.mediation, "wapApiMediationAgencyController","getDifferentSubjects", inbo);
		this.executeApiTest(apiInVm);
	}
}