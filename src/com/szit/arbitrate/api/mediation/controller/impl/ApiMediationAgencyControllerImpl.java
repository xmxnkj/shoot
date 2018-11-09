package com.szit.arbitrate.api.mediation.controller.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.hsit.common.utils.PagingBean;
import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.mediation.controller.ApiMediationAgencyController;
import com.szit.arbitrate.client.dao.ClientDao;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;
import com.szit.arbitrate.client.entity.query.ClientQuery;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.dao.MediationAgencyDao;
import com.szit.arbitrate.mediation.entity.LegalDoc;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.entity.enumvo.AgencyTypeEnum;
import com.szit.arbitrate.mediation.entity.query.LegalDocQuery;
import com.szit.arbitrate.mediation.entity.query.MediationAgencyQuery;
import com.szit.arbitrate.mediation.service.LegalDocService;
import com.szit.arbitrate.mediation.service.MediationAgencyService;
import com.szit.arbitrate.mediation.service.MediationCaseService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Component("wapApiMediationAgencyController")
public class ApiMediationAgencyControllerImpl extends BaseController<MediationAgency, MediationAgencyQuery>
	implements ApiMediationAgencyController{
	
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private MediationAgencyService service;
	@Autowired
	private MediationAgencyDao dao;
	@Autowired
	private ClientDao clientDao;
	@Autowired
	private MediationCaseService mediationCaseService;
	@Autowired
	private LegalDocService legalDocService;

	/**  获取调节机构列表     */
	@Override
	public ApiOutParamsVm getMediationAgencyList(String agencyname) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		MediationAgencyQuery query = this.getEntityQuery();
		if(StringUtils.isNotEmpty(agencyname)){
			query.setAgencyName(agencyname);
		}
		List<MediationAgency> list = service.getEntities(query);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,list);
	}

	/**  获取调节机构详情     */
	@Override
	public ApiOutParamsVm getMediationAgencyDetail(String mediationagencyid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String, Object> resultMap = Maps.newHashMap();
		
		//查询机构
		MediationAgencyQuery query = new MediationAgencyQuery();
		query.setId(mediationagencyid);
		MediationAgency entity = service.getEntity(query);
		resultMap.put("entity", entity);
		
		//查询机构所属的所有员
		ClientQuery clientquery = new ClientQuery();
		clientquery.setMediationAgencyId(mediationagencyid);
		clientquery.addOrder("showDisplay", false);
		List<Client> list = clientService.getEntities(clientquery);
		Client temp = null;
		for(int i=0;i<list.size();i++){
			if(i>0 && list.get(i).getClientState()==ClientStateEnum.MediationAgency){
				//与第一位交换
				temp = list.get(0);
				list.set(0, list.get(i));
				list.set(i, temp);
			}
		}
		resultMap.put("list", list);
		
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,resultMap);
	}
	
	/**  获取员及其案例列表     */
	@Override
	public ApiOutParamsVm getMediatorDetail(String clientid) {
		Map<String, Object> resultMap = Maps.newHashMap();
		
		Client client = clientService.getById(clientid);
		resultMap.put("client", client);
		LegalDocQuery legalDocQuery = new LegalDocQuery();
		legalDocQuery.setClassic(true);
		legalDocQuery.setMediatorClientId(clientid);
		List<LegalDoc> list = legalDocService.getEntities(legalDocQuery);
		resultMap.put("list", list);
		
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,resultMap);
	}
	
	@Override
	public ApiOutParamsVm searchMediationAgencyList(String agencytype, 
													String casetype, 
													String agencyname,
													String lon,
													String lat) {
		/*String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}*/
		List<MediationAgency> list = service.searchMediationAgencyList(agencytype, casetype, agencyname);

		//排序
		if(StringUtils.isNotEmpty(lon) && StringUtils.isNotEmpty(lat)){

			double disti = 0;
			double distj = 0;
			
			Float flat = Float.parseFloat(lat);
			Float flon = Float.parseFloat(lon);
			
			for(int i = 0;i<list.size();i++){
				for(int j = i;j<list.size();j++){
					disti = GetDistance(list.get(i).getLon().doubleValue(), list.get(i).getLat().doubleValue(), flon, flat);					
					distj = GetDistance(list.get(j).getLon().doubleValue(), list.get(j).getLat().doubleValue(), flon, flat);
					if(disti > distj){
						MediationAgency temp  = list.get(j);		
						list.set(j, list.get(i));
						list.set(i, temp);
					}
				}
			}
		}
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,list);
	}

	@Override
	public ApiOutParamsVm searchOpenedMediationAgencyList() {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		List<MediationAgency> list = service.searchOpenedMediationAgencyList();
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,list);
	}

	@Override
	public ApiOutParamsVm searchAgencyOrMediatorList(String type,
													 String mediatorOrAgency, 
													 String key, 
													 int page,
													 String lon,
													 String lat) {
		Map<String,Object> result = new HashMap<String,Object>();
		PagingBean pagingBean = new PagingBean(page,PagingBean.DEFAULT_PAGE_SIZE);
		if(mediatorOrAgency.equals("agency")){
			Map<String,Object> query = new HashMap<String,Object>();
			query.put("agencyType", AgencyTypeEnum.CivilCaseType);	//默认
			switch(type){
				case "1":	//人民
					query.put("agencyType", AgencyTypeEnum.CivilCaseType);
					break;
				case "2":	//行政
					query.put("agencyType", AgencyTypeEnum.AdministrationCaseType);
					break;
				case "3":	//司法
					query.put("agencyType", AgencyTypeEnum.JudicialCaseType);
					break;
			}
			query.put("agencyName", "%"+key+"%");
			//调节机构
			List<MediationAgency> list = dao.findHqlToM("FROM MediationAgency where agencyType=:agencyType and agencyName like :agencyName", query);
			//排序
			if(StringUtils.isNotEmpty(lon) && StringUtils.isNotEmpty(lat)){

				double disti = 0;
				double distj = 0;
				
				Float flat = Float.parseFloat(lat);
				Float flon = Float.parseFloat(lon);
				
				for(int i = 0;i<list.size();i++){
					for(int j = i;j<list.size();j++){
						disti = GetDistance(list.get(i).getLon().doubleValue(), list.get(i).getLat().doubleValue(), flon, flat);					
						distj = GetDistance(list.get(j).getLon().doubleValue(), list.get(j).getLat().doubleValue(), flon, flat);
						if(disti > distj){
							MediationAgency temp  = list.get(j);		
							list.set(j, list.get(i));
							list.set(i, temp);
						}
					}
				}
			}
			result.put("rows", list);
			result.put("pageTotal", pagingBean.getTotalPages());
			result.put("recordTotal", pagingBean.getTotalItems());
		}
		if(mediatorOrAgency.equals("mediator")){
			Map<String,Object> query = new HashMap<String,Object>();
			query.put("clientType", ClientTypeEnum.Mediator);
			query.put("clientState1", ClientStateEnum.Mediator);
			query.put("clientState2", ClientStateEnum.MediationAgency);
			query.put("mediatorType", Integer.parseInt(type));
			query.put("identifyName", "%"+key+"%");
			List<Client> list = clientDao.findHqlToM("FROM Client where clientType=:clientType and (clientState=:clientState1 or clientState=:clientState2)  and mediatorType=:mediatorType and identifyName like :identifyName order by showDisplay", query, pagingBean);
			result.put("rows", list);
			result.put("pageTotal", pagingBean.getTotalPages());
			result.put("recordTotal", pagingBean.getTotalItems());
		}
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,result);
	}	
	
	private static final  double EARTH_RADIUS = 6378137;//赤道半径
	private static double rad(double d){
	    return d * Math.PI / 180.0;
	}
	public static double GetDistance(double lon1,double lat1,double lon2, double lat2) {
	    double radLat1 = rad(lat1);
	    double radLat2 = rad(lat2);
	    double a = radLat1 - radLat2;
	    double b = rad(lon1) - rad(lon2);
	    double s = 2 *Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2)+Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2))); 
	    s = s * EARTH_RADIUS;    
	   return s;//单位米
	}

	
	public static void main(String[] args) {
		
		System.out.println(Math.pow(8,2));
		
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 0;i<10000;i++){
			list.add((int) (Math.random()*1000000000));
		}
		
		long start = System.currentTimeMillis();
		//冒泡
		for(int i=0;i<list.size();i++){			
			for(int j=0;j<list.size();j++){
				if(list.get(i)<list.get(j)){
					Integer temp = list.get(i);					
					list.set(i, list.get(j));
					list.set(j, temp);
				}
			}
		}
		
		long end = System.currentTimeMillis();
		System.out.println("冒泡："+(end-start));
		
		start = System.currentTimeMillis();
		Integer min = 0;
		for(int i=0;i<list.size();i++){			
			for(int j=i;j<list.size();j++){
				if(list.get(i)>list.get(j)){
					min = list.get(j);		
					list.set(j, list.get(i));
					list.set(i, min);
				}
			}
		}
		
		end = System.currentTimeMillis();
		System.out.println("插入："+(end-start));
		
		for(int i=0;i<list.size();i++){		
			System.out.print(list.get(i)+",");
		}
		
	}
}