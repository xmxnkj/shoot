package com.szit.arbitrate.api.mediation.controller.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.google.common.collect.Maps;
import com.hsit.common.utils.JsonFormatUtil;
import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.utils.HttpSessionContext;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.mediation.bo.in.MediationProtocolInBo;
import com.szit.arbitrate.api.mediation.bo.in.TempClientInBo;
import com.szit.arbitrate.api.mediation.controller.ApiMediationProtocolController;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.query.TempClientQuery;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.TempClientService;
import com.szit.arbitrate.mediation.entity.MediationProtocol;
import com.szit.arbitrate.mediation.entity.query.MediationProtocolQuery;
import com.szit.arbitrate.mediation.service.MediationProtocolService;

@Component("wapApiMediationProtocolController")
public class ApiMediationProtocolControllerImpl extends BaseController<MediationProtocol, MediationProtocolQuery>
	implements ApiMediationProtocolController{
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private MediationProtocolService service;
	@Autowired
	private TempClientService tempClientService;
	
	@Override
	public ApiOutParamsVm getMediationProtocol(String caseid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String, Object> map = Maps.newHashMap();
		MediationProtocolQuery query = new MediationProtocolQuery();
		query.setCaseId(caseid);
		MediationProtocol entity = service.getEntity(query);
		if(entity == null){
			return ApiTools.bulidOutSucceed("该还没有协议书!");
		}
		Client client = clientService.getById(entity.getApplyClientId());
		entity.setGender(client.isGender());
		entity.setIdentify(client.getIdentify());
		entity.setIdentifyName(client.getIdentifyName());
		entity.setTel(client.getTel());
		entity.setAddress(client.getAddress());
		entity.setNation(client.getNation());
		entity.setProfession(client.getProfession());
		try {
			if(StringUtils.isNotEmpty(client.getBirth())){
				entity.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse(client.getBirth()));
			}
		} catch (ParseException e) {
			
		}
		entity.setAddress(client.getAddress());
		map.put("entity", entity);
		TempClientQuery TempClientQuery = new TempClientQuery();
		TempClientQuery.setCaseId(caseid);
		TempClientQuery.setPartB(true);
		List<TempClient> list = tempClientService.getEntities(TempClientQuery);
		for(TempClient eClient:list){
			client = clientService.getById(eClient.getClientId());
			if(client==null){
				continue;
			}
			eClient.setIdentify(client.getIdentify());
			eClient.setGender(client.isGender());
			eClient.setIdentifyName(client.getIdentifyName());
			eClient.setTel(client.getTel());
			eClient.setAddress(client.getAddress());
			eClient.setNation(client.getNation());
			eClient.setProfession(client.getProfession());
			try {
				if(StringUtils.isNotEmpty(client.getBirth())){
					eClient.setBirth(new SimpleDateFormat("yyyy-MM-dd").parse(client.getBirth()));
				}
			} catch (ParseException e) {
				
			}
		}
		
		map.put("list", list);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,map);
	}
	
	@Override
	public ApiOutParamsVm createMediationProtocol(String entityjson, String jsonlist) {
		/*String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}*/
		if(StringUtils.isEmpty(entityjson) || StringUtils.isEmpty(jsonlist)){
			return ApiTools.bulidOutFail("非法参数,同步数据参数不能为空!");
		}
		JsonFormatUtil.printJson("传入的协议书数据:", entityjson);
		JsonFormatUtil.printJson("传入的当事人数据:", jsonlist);
		JavaType javaType = jsonMapper.contructCollectionType(List.class, TempClientInBo.class);
		List<TempClientInBo> tempclientlist = jsonMapper.fromJson(jsonlist, javaType);
		MediationProtocolInBo protocolinbo = jsonMapper.fromJson(entityjson, MediationProtocolInBo.class);
		//协议只分口头和书面
		if(protocolinbo.getOralAgree()!=null && protocolinbo.getOralAgree().trim().equals("1")){
			protocolinbo.setOralAgree("口头协议");
		}else{
			protocolinbo.setOralAgree("书面协议");
		}
		return ApiTools.bulidOutSucceed("协议书保存成功!",service.createMediationProtocol(tempclientlist, protocolinbo));
	}
	
	@Override
	public ApiOutParamsVm operateMediationProtocol(String clientid,String protocolid, Integer type) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.operateMediationProtocol(clientId, protocolid, type);
		return ApiTools.bulidOutSucceed("操作成功!");
	}
	
	@Override
	public ApiOutParamsVm notifyProtocolToClient(String protocolid,
			String smscontent) {
		String clientId = HttpSessionContext.getHttpSession(this.getRequest(),"clientId");
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.notifyProtocolToClient(protocolid, smscontent);
		return ApiTools.bulidOutSucceed("操作成功!");
	}
	
	@Override
	public ApiOutParamsVm downloadMediationProtocol(String protocolid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String, String> result = service.downloadMediationProtocol(protocolid);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,result);
	}

}
