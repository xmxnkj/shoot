package com.szit.arbitrate.mediation.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.utils.PagingBean;
import com.szit.arbitrate.mediation.dao.MediationCaseDao;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.entity.MediationCase;
import com.szit.arbitrate.mediation.entity.MediationProtocol;
import com.szit.arbitrate.mediation.entity.enumvo.CaseStateEnum;
import com.szit.arbitrate.mediation.entity.query.MediationCaseQuery;
import com.szit.arbitrate.mediation.entity.query.MediationProtocolQuery;
import com.szit.arbitrate.mediation.service.BasicDataService;
import com.szit.arbitrate.mediation.service.MediationAgencyService;
import com.szit.arbitrate.mediation.service.MediationCaseService;
import com.szit.arbitrate.mediation.service.MediationProtocolService;

@Namespace("/admin/mediation/mediationcase")
@Controller("mediationCaseAction")
public class MediationCaseAction extends BaseJsonAction<MediationCase, MediationCaseQuery>{

	private static final long serialVersionUID = -8518031235630118626L;
	
	@Autowired
	private MediationCaseService service;
	
	@Autowired
	private MediationProtocolService mediationProtocolService;
	
	@Autowired
	private MediationAgencyService mediationAgencyService;
	
	@Autowired
	private MediationCaseDao dao;
	
	@Autowired
	private BasicDataService basicDataService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	String caseid;
	
	String sort;
	
	String order;
	
	Map<String,String> mapNumber = new HashMap<String,String>();
	
	{
		mapNumber.put("一", "1");
		mapNumber.put("二", "2");
		mapNumber.put("三", "3");
		mapNumber.put("四", "4");
		mapNumber.put("五", "5");
		mapNumber.put("六", "6");
		mapNumber.put("七", "7");
		mapNumber.put("八", "8");
		mapNumber.put("九", "9");
		mapNumber.put("壹", "1");
		mapNumber.put("贰", "2");
		mapNumber.put("叁", "3");
		mapNumber.put("肆", "4");
		mapNumber.put("伍", "5");
		mapNumber.put("陆", "6");
		mapNumber.put("柒", "7");
		mapNumber.put("捌", "8");
		mapNumber.put("玖", "9");
		mapNumber.put("拾", "10");
	}
	
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getCaseid() {
		return caseid;
	}

	public void setCaseid(String caseid) {
		this.caseid = caseid;
	}

	public MediationCaseService getService() {
		return service;
	}

	public void setService(MediationCaseService service) {
		this.service = service;
	}
	
	@Action(value = "exportMediationCaseToExcel")
	public void exportMediationCaseToExcel() throws Exception{
		
		String begin = this.getRequest().getParameter("beginDate");
		String end = this.getRequest().getParameter("endDate");
		String statmonth = "";
		
		if(begin!=null && !"".equals(begin.trim())){
			statmonth += begin;
			begin = begin+" 00:00:00";
		}
		if(end!=null && !"".equals(end.trim())){
			statmonth += end;
			end = end+" 00:00:00";
		}
		//HashMap<String,HashMap<String,Object>> list = service.statMediationCaseExcelDto(begin,end);
		//获取所有案件分类
		//List<String> typeBranch = basicDataService.getTypeClassify();
		StringBuffer hql = new StringBuffer();
		hql.append("FROM MediationCase where caseState=:caseState");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("caseState", CaseStateEnum.Completed);
		List<MediationCase> list = dao.findHql(hql.toString(), map);
		StringBuilder basePath = new StringBuilder(getServletContext().getRealPath("/")).append("\\content\\template\\调解案件统计.xls");
		
		//单位
		Map<String,Map<Integer,Float>> unitMap = new HashMap<String,Map<Integer,Float>>();
		//数量统计
		Map<Integer,Float> caseMap = new HashMap<Integer,Float>();
		OutputStream bos = null;

			MediationAgency mediationAgency = null;
			for(MediationCase mCase:list){
				mediationAgency = mediationAgencyService.getById(mCase.getMediationAgencyId());
				String unit = mediationAgency.getAgencyName();
				if(unitMap.containsKey(unit)){
					caseMap = unitMap.get(unit);
				}else{
					caseMap = new HashMap<Integer,Float>();
					for(int i=1;i<=41;i++){
						caseMap.put(i, 0f);
					}
					unitMap.put(unit, caseMap);
				}
				
				//案件总数	++
				caseMap.put(1, caseMap.get(1)+1);
				//涉及当事人数
				caseMap.put(2, caseMap.get(2)+mCase.getPeople());
				//成功案件数
				caseMap.put(3, caseMap.get(3)+1);
				//疑难案件
				if(mCase.isDifficult()){
					caseMap.put(4, caseMap.get(4)+1);
				}
				//涉及金额
				caseMap.put(5, caseMap.get(5)+Float.parseFloat(mCase.getCaseMoney()));
				//不同主体调解情况  6-11
				MediationProtocolQuery mediationProtocolQuery = new MediationProtocolQuery();
				mediationProtocolQuery.setCaseId(mCase.getId());
				MediationProtocol mediationProtocol = mediationProtocolService.getEntity(mediationProtocolQuery);
				
				switch(Integer.parseInt(StringUtils.isNotEmpty(mediationProtocol.getExternalDesc())?(mapNumber.containsKey(mediationProtocol.getExternalDesc())?mapNumber.get(mediationProtocol.getExternalDesc()):mediationProtocol.getExternalDesc()):"0")){
					case 0:
						caseMap.put(6, caseMap.get(6)+1);
						break;
					case 1:
						caseMap.put(7, caseMap.get(7)+1);
						break;
					case 2:
						caseMap.put(8, caseMap.get(8)+1);
						break;
					case 3:
						caseMap.put(9, caseMap.get(9)+1);
						break;
					case 4:
						caseMap.put(10, caseMap.get(10)+1);
						break;
					case 5:
						caseMap.put(11, caseMap.get(11)+1);
						break;
					default:
						break;
				}
				//案件来源  12-19
				switch(mCase.getCaseSource().trim()){
					case "信访部门委托移送":
						caseMap.put(12, caseMap.get(12)+1);
						break;
					case "法院委托移送":
						caseMap.put(13, caseMap.get(13)+1);
						break;
					case "主动调解":
						caseMap.put(14, caseMap.get(14)+1);
						break;
					case "公安机关委托移送":
						caseMap.put(15, caseMap.get(15)+1);
						break;
					case "其他部门委托移送":
						caseMap.put(16, caseMap.get(16)+1);
						break;
					case "依申请调解":
						caseMap.put(17, caseMap.get(17)+1);
						break;
					case "检察院委托移送":
						caseMap.put(18, caseMap.get(18)+1);
						break;
					case "接受委托移送调解":
						caseMap.put(19, caseMap.get(19)+1);
						break;
				}
				
				// 案件分类情况   20-38
				switch(mCase.getCaseType().trim()){
					case "其他劳动争议纠纷":
						caseMap.put(20, caseMap.get(21)+1);
						break;
					case "其他消费纠纷":
						caseMap.put(21, caseMap.get(22)+1);
						break;
					case "其他纠纷":
						caseMap.put(22, caseMap.get(23)+1);
						break;
					case "医疗纠纷":
						caseMap.put(23, caseMap.get(24)+1);
						break;
					case "合同纠纷":
						caseMap.put(24, caseMap.get(25)+1);
						break;
					case "婚姻家庭纠纷":
						caseMap.put(25, caseMap.get(26)+1);
						break;
					case "山林土地纠纷":
						caseMap.put(26, caseMap.get(27)+1);
						break;
					case "征地拆迁纠纷":
						caseMap.put(27, caseMap.get(28)+1);
						break;
					case "房屋宅基地纠纷":
						caseMap.put(28, caseMap.get(29)+1);
						break;
					case "拖欠农民工工资纠纷":
						caseMap.put(29, caseMap.get(30)+1);
						break;
					case "损害赔偿纠纷":
						caseMap.put(30, caseMap.get(31)+1);
						break;						
					case "旅游纠纷":
						caseMap.put(31, caseMap.get(32)+1);
						break;
					case "物业纠纷":
						caseMap.put(32, caseMap.get(33)+1);
						break;
					case "环境污染纠纷":
						caseMap.put(33, caseMap.get(34)+1);
						break;
					case "生产经营纠纷":
						caseMap.put(34, caseMap.get(35)+1);
						break;
					case "电子商务纠纷":
						caseMap.put(35, caseMap.get(36)+1);
						break;
					case "道路交通事故纠纷":
						caseMap.put(36, caseMap.get(37)+1);
						break;
					case "邻里纠纷":
						caseMap.put(37, caseMap.get(38)+1);
						break;
				}
				
				//协议形式
				switch(mCase.getProtocolForm().trim()){
					case "口头协议":
						caseMap.put(38, caseMap.get(39)+1);
						break;
					case "书面协议":
						caseMap.put(49, caseMap.get(40)+1);
						break;
				}
			}
			
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(basePath.toString())));
			//读取模板
			HSSFWorkbook wb = new HSSFWorkbook(bis);
			//获取工作表
			HSSFSheet sheet = wb.getSheetAt(0);
			HSSFRow row = null;				
			HSSFCell cell = null;
			
			//行数
			int index = 5;
			for(String name:unitMap.keySet()){
				row = sheet.getRow(index)!=null?sheet.getRow(index):sheet.createRow(index);
				Map<Integer,Float> m = unitMap.get(name);
				
				cell = getCell(row,0);
				cell.setCellValue(name);
				
				for(Integer key:m.keySet()){
					cell = getCell(row,key);
					cell.setCellValue(m.get(key));
				}
				index++;
			}
			//写入流
			getResponse().setHeader("content-disposition", 
					"attachment;filename=" + URLEncoder.encode("调解案件统计", "UTF-8")+".xls"); 
			getResponse().setContentType("application/vnd.ms-excel");
			bos = getResponse().getOutputStream();
			//保存
			wb.write(bos);
			bos.flush();
			bos.close();
	}
	
	Date beginDate;
	Date endDate;
	String mediationAgency;
	String mediatorClient;
	String caseStatus;

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMediationAgency() {
		return mediationAgency;
	}

	public void setMediationAgency(String mediationAgency) {
		this.mediationAgency = mediationAgency;
	}

	public String getMediatorClient() {
		return mediatorClient;
	}

	public void setMediatorClient(String mediatorClient) {
		this.mediatorClient = mediatorClient;
	}

	public String getCaseStatus() {
		return caseStatus;
	}

	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	@Action(value = "getList")
	public void getList(){
		Map<String,Object> result = new HashMap<String,Object>();
		try {
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			Map<String,Object> map = new HashMap<String,Object>();
			PagingBean pagingBean = new PagingBean(1, 50);
			StringBuffer stringBuffer = new StringBuffer("FROM MediationCase where 1=1 ");
			if(beginDate!=null){
				stringBuffer.append(" and applyTime>:beginDate ");
				map.put("beginDate", beginDate);
			}
			
			if(beginDate!=null){
				stringBuffer.append(" and applyTime<:endDate ");
				map.put("endDate", endDate);
			}
			
			if(StringUtils.isNotEmpty(mediationAgency)){
				stringBuffer.append(" and mediationAgencyId=:mediationAgency ");
				map.put("mediationAgency", mediationAgency);
			}
			
			if(StringUtils.isNotEmpty(mediatorClient)){
				stringBuffer.append(" and mediatorClientId=:mediatorClient ");
				map.put("mediatorClient", mediatorClient);
			}
			
			if(StringUtils.isNotEmpty(caseStatus)){
				stringBuffer.append(" and caseState=:caseStatus ");
				map.put("caseStatus", CaseStateEnum.valueOf(caseStatus));
			}
			stringBuffer.append(" order by "+sort+" "+order);
			
			List<MediationCase> list = dao.findHqlToM(stringBuffer.toString(), map, pagingBean);
			for(MediationCase mediationCase:list){
				mediationCase.setComplainant(getCaseClientNameList(mediationCase.getId()));
			}
			result.put("rows", list);
			result.put("total", pagingBean.getTotalItems());
			String jsonResult = jsonMapper.toJson(result);
			outJson(jsonResult);
		} catch (Exception e) {
			outFailJson(e);
		}
	}
	
	List<String> getCaseClientNameList(String caseid){
		final StringBuffer sql  =new StringBuffer();
		sql.append(" SELECT tc.IDENTIFY_NAME AS name");
		sql.append(" FROM cl_tempclient tc");
		sql.append(" where tc.CASE_ID ='").append(caseid).append("'");
		sql.append(" and tc.PARTB=true");
		List<String> list = jdbcTemplate.queryForList(sql.toString(), String.class);
		return list;
	}
	
	//获取结案卷宗
	@Action(value =  "getMediationCaseFile")
	public void getMediationCaseFile(){
		try {
			Map<String,Object> result = service.fileToZipByCaseId(caseid);
			outJson(jsonMapper.toJson(result));
		} catch (Exception e) {
			outFailJson(e);
		}
	}
	
	String clientId; //调节机构管理员ID  或     调解中心管理员ID
	String startTime;
	String endTime;
	String mediationAgencyId;
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getMediationAgencyId() {
		return mediationAgencyId;
	}
	public void setMediationAgencyId(String mediationAgencyId) {
		this.mediationAgencyId = mediationAgencyId;
	}
	
	//获取案件类型统计数据页面
	@Action(value="caseDistribution", 
			results={
		@Result(name="success", location="/WEB-INF/jsp/admin/mediation/mediationcase/caseDistribution.jsp")}
			)
		public String consultingStatistics() {
			return SUCCESS;
		}
	
	//获取案件类型统计数据
	@Action("getCaseDistribution")
	public void getCaseDistribution(){
		try{
			startTime = startTime==null?"":startTime;
			endTime = endTime==null?"":endTime;
			List<Map<String,Object>> li = new ArrayList<Map<String,Object>>();
			
			Map<String,Object> map = service.statisticsMediationCaseByClientId(clientId, startTime, endTime, mediationAgencyId);
			Map<String,Object> mapsuccess = service.statisticsMediationCaseByClientIdSuccess(clientId, startTime, endTime, mediationAgencyId);
			li.add(map);		//在线调解总数
			li.add(mapsuccess);	//调解成功案例
			outJson(jsonMapper.toJson(li));
		} catch (Exception e) {
			outFailJson(e);
		}
	}
	
	public static HSSFCell getCell(HSSFRow row,int index){
		return row.getCell(index)!=null?row.getCell(index):row.createCell(index);
	}
}