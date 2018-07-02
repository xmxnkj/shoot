package com.szit.arbitrate.mediation.junit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.collect.Maps;
import com.hsit.common.utils.DocUtil;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.query.TempClientQuery;
import com.szit.arbitrate.client.service.TempClientService;
import com.szit.arbitrate.mediation.dao.MediationCaseDao;
import com.szit.arbitrate.mediation.entity.MediationCase;
import com.szit.arbitrate.mediation.entity.dto.MediationCaseExcelDto;
import com.szit.arbitrate.mediation.service.MediationCaseService;

public class MediationCaseServiceJunit extends BaseApiJunitTest{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private MediationCaseService service;
	@Autowired
	private TempClientService tempClientService;
	@Autowired
	private MediationCaseDao mediationCaseDao;
	@Autowired
	private MediationCaseDao dao;
	
	private Logger logger = LoggerFactory.getLogger(MediationCaseServiceJunit.class);
	
	@Test
	public void getListByClientIdAndCaseType(){
		service.getListByClientIdAndCaseType("568c72a3-9198-4f88-9419-c9b909486868", 0);
	}
	@Test
	public void FileToZipByCaseId(){
		String caseid = "dcb5ebcd-9027-4fb0-a566-9850fe2a18cc";
		Map<String, Object> result = service.fileToZipByCaseId(caseid);
		//logger.debug(result);
	}
	@Test
	public void getherTempClient(){
		String tempClientId = "ab9b23ec-3c38-4401-acfa-4dacb35919e3";
		service.gatherTempClient(tempClientId);
	}
	
	@Test
	public void statisticsMediationCaseByClientId(){
		String clientid = "1";
		service.statisticsMediationCaseByClientId(clientid,"","","");
	}
	@Test
	public void testCreateMediationDoc(){
		
		String caseid = "931e0517-82b2-4c1b-b13e-a4770f1a997d";
		MediationCase entity = service.getById(caseid);
		TempClientQuery query = new TempClientQuery();
		query.setCaseId(caseid);
		List<TempClient> tempClientList = tempClientService.getEntities(query);
		
		Map<String, Object> dataMap=new HashMap<String, Object>(); 
		dataMap.put("applyClientName", entity.getApplyClientName());
		if(entity.getApplyClient().isGender()){
        	dataMap.put("gender", "男");
        }else{
        	dataMap.put("gender", "女");
        }
		/*dataMap.put("nation", entity.getApplyClient().getNation());
		dataMap.put("birth", entity.getApplyClient().getBirth());
		dataMap.put("tel", entity.getApplyClient().getTel());
		dataMap.put("address", entity.getApplyClient().getAddress());
		dataMap.put("profession", entity.getApplyClient().getProfession());
		dataMap.put("identify", entity.getApplyClient().getIdentify());
		dataMap.put("caseExplain", entity.getCaseExplain());*/
		
		dataMap.put("nation", "汉");
		dataMap.put("birth", "2017-01-05");
		dataMap.put("tel", "18888888888");
		dataMap.put("address", "111111111");
		dataMap.put("profession", "程序猿");
		dataMap.put("identify", "123456");
		dataMap.put("caseExplain", "加班没有加班费");
		
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		for(int i = 0; i < tempClientList.size(); i ++){
			TempClient tempClient = tempClientList.get(i);
        	Map<String,Object> map = new HashMap<String,Object>(); 
        	map.put("identifyName", tempClient.getIdentifyName()); 
        	map.put("tel", tempClient.getTel()); 
        	map.put("address", tempClient.getAddress()); 
        	map.put("nation", "汉");
        	map.put("birth", tempClient.getBirth()==null?"":tempClient.getBirth());
        	map.put("profession", "程序猿");
        	if(tempClient.isGender()){
        		map.put("gender", "男");
            }else{
            	map.put("gender", "女");
            }
        	list.add(map);
		}
		dataMap.put("list", list);
		
		String url = "uploads/mediation";
        String templateurl = "/com/hsit/common/template";
		DocUtil util = new DocUtil();
		util.createDoc(dataMap, templateurl, "applymediation.ftl", url, "1");
	}
	
	@Test
	public void statMediationCaseExcelDto(){
		List<MediationCaseExcelDto> list = new ArrayList<MediationCaseExcelDto>();
		List<Map<String, Object>> hostNumList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> sourceNumList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> typeNumList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> protocolNumList = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = Maps.newHashMap();
		map.put("classfy", "村调委会数");
		map.put("count", 1);
		hostNumList.add(map);
		map = Maps.newHashMap();
		map.put("classfy", "居调委会数");
		map.put("count", 2);
		hostNumList.add(map);
		map = Maps.newHashMap();
		map.put("classfy", "乡镇调委会数");
		map.put("count", 1);
		hostNumList.add(map);
		map = Maps.newHashMap();
		map.put("classfy", "街道调委会数");
		map.put("count", 3);
		hostNumList.add(map);
		map = Maps.newHashMap();
		map.put("classfy", "企事业单位调委会数");
		map.put("count", 0);
		hostNumList.add(map);
		map = Maps.newHashMap();
		map.put("classfy", "社会团体和其他组织调委会数");
		map.put("count", 1);
		hostNumList.add(map);
		
		map = Maps.newHashMap();
		map.put("source", "主动");
		map.put("count", 1);
		sourceNumList.add(map);
		map = Maps.newHashMap();
		map.put("source", "依申请");
		map.put("count", 2);
		sourceNumList.add(map);
		map = Maps.newHashMap();
		map.put("source", "接受委托移送");
		map.put("count", 1);
		sourceNumList.add(map);
		map = Maps.newHashMap();
		map.put("source", "法院委托移送");
		map.put("count", 3);
		sourceNumList.add(map);
		map = Maps.newHashMap();
		map.put("source", "检察院委托移送");
		map.put("count", 0);
		sourceNumList.add(map);
		map = Maps.newHashMap();
		map.put("source", "公安机关委托移送");
		map.put("count", 1);
		sourceNumList.add(map);
		map = Maps.newHashMap();
		map.put("source", "信访部门委托移送");
		map.put("count", 0);
		sourceNumList.add(map);
		map = Maps.newHashMap();
		map.put("source", "其他部门委托移送");
		map.put("count", 1);
		sourceNumList.add(map);
		
		map = Maps.newHashMap();
		map.put("type", "婚姻家庭纠纷");
		map.put("count", 1);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "邻里纠纷");
		map.put("count", 2);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "房屋宅基地纠纷");
		map.put("count", 1);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "合同纠纷");
		map.put("count", 3);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "生产经营纠纷");
		map.put("count", 0);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "损害赔偿纠纷");
		map.put("count", 1);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "山林土地纠纷");
		map.put("count", 0);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "征地拆迁纠纷");
		map.put("count", 1);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "环境污染纠纷");
		map.put("count", 1);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "拖欠农民工工资纠纷");
		map.put("count", 2);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "其他劳动争议纠纷");
		map.put("count", 1);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "旅游纠纷");
		map.put("count", 3);
		typeNumList.add(map);
		map.put("type", "电子商务纠纷");
		map.put("count", 0);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "其他消费纠纷");
		map.put("count", 1);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "医疗纠纷");
		map.put("count", 0);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "道路交通事故纠纷");
		map.put("count", 1);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "物业纠纷");
		map.put("count", 0);
		typeNumList.add(map);
		map = Maps.newHashMap();
		map.put("type", "其他纠纷");
		map.put("count", 1);
		typeNumList.add(map);
		
		map = Maps.newHashMap();
		map.put("protocol", "口头协议");
		map.put("count", 0);
		protocolNumList.add(map);
		map = Maps.newHashMap();
		map.put("protocol", "书面协议");
		map.put("count", 1);
		protocolNumList.add(map);
		
		MediationCaseExcelDto dto1 = new MediationCaseExcelDto();
		dto1.setMoney(new BigDecimal(10));
		dto1.setPeople(new BigDecimal(3));
		dto1.setSpecialNum(new BigDecimal(1));
		dto1.setSuccessNum(new BigDecimal(2));
		dto1.setTotalNum(new BigDecimal(10));
		dto1.setHostNumList(hostNumList);
		dto1.setSourceNumList(sourceNumList);
		dto1.setTypeNumList(typeNumList);
		dto1.setProtocolNumList(protocolNumList);
		
		list.add(dto1);
		//mediationCaseDao.statMediationCaseExcelDto(list);
	}
	
	
	@Test
	public void test(){
		StringBuffer stringBuffer = new StringBuffer("FROM MediationCase where 1=1 ");
		stringBuffer.append(" and applyTime<:applyTime ");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("applyTime", new Date());
		List<MediationCase> list = dao.findHql(stringBuffer.toString(), map);
		System.out.println(list.size());
	}
	
}
