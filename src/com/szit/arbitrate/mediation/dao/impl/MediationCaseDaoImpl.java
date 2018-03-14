package com.szit.arbitrate.mediation.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.dao.MediationCaseDao;
import com.szit.arbitrate.mediation.entity.MediationCase;
import com.szit.arbitrate.mediation.entity.dto.MediationCaseInfoDto;
import com.szit.arbitrate.mediation.entity.enumvo.CaseAllocationStateEnum;
import com.szit.arbitrate.mediation.entity.enumvo.CaseStateEnum;
import com.szit.arbitrate.mediation.entity.query.MediationCaseQuery;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: CaseDaoImpl
* @Description:案件dao接口实现类
* @author Administrator
* @date 2017年3月23日 上午10:57:29
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class MediationCaseDaoImpl extends BaseHibernateDaoImpl<MediationCase, MediationCaseQuery> 
	implements MediationCaseDao{
	
	@Resource
	private DataSource dataSource;
	
	@Resource
	private ClientService clientService;
	
	private final String select = " SELECT mc.CASE_EXPLAIN AS caseExplain,mc.ID as caseId,"
		+"mc.APPLY_CLIENT_NAME AS applyClientName,mc.CASE_STATE as caseState,mc.APPLY_TIME as applyDate,"
		+ "mc.mediator_client_id as mediatorClientId ,mc.allocation_state as allocationState ,mc.mediator_client_id as mediatorId,"
		+ " cl.IDENTIFY_NAME as mediatorName,mc.MEDIATOR_NAME as resultName,cl.TEL as mediatorTel,cl.agency_name as agencyName";
	private final String al = "mc";
	@Override
	public List<QueryParam> buildQueryParams(MediationCaseQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getMediatorClientId())) {
				qps.add(new QueryParam("mediatorClientId", query.getMediatorClientId()));
			}
			if (!StringUtils.isEmpty(query.getCaseType())) {
				qps.add(new QueryParam("caseType", query.getCaseType()));
			}
			if (!StringUtils.isEmpty(query.getCaseExplain())) {
				qps.add(new QueryParam("caseExplain", query.getCaseExplain(), ParamCompareType.Like));
			}
			if (!StringUtils.isEmpty(query.getMediateType())) {
				qps.add(new QueryParam("mediateType", query.getMediateType()));
			}
			if (query.getCaseState() != null) {
				qps.add(new QueryParam("caseState", query.getCaseState()));
			}
			if (query.getApplyClientId() != null) {
				qps.add(new QueryParam("applyClientId", query.getApplyClientId()));
			}
			if (query.getAllocationState()!= null) {
				qps.add(new QueryParam("allocationState", query.getAllocationState()));
			}
		}
		return qps;
	}
	
//	@Override
//	public List<MediationCaseInfoDto> getMediationCaseListByClientState(
//			String clientid, ClientStateEnum clientStateEnum,String mediationAgencyId)
//			throws ErrorException {
//
//		Map<String, Object> paramMap = Maps.newHashMap();
//		final StringBuffer hql = new StringBuffer();
//		hql.append(" SELECT mc.CASE_EXPLAIN AS caseExplain,");
//		hql.append(" mc.ID as caseId,");
//		hql.append(" mc.APPLY_CLIENT_NAME AS applyClientName,");
//		hql.append(" mc.CASE_STATE as caseState,");
//		hql.append(" mc.APPLY_TIME as applyDate");
//		hql.append(" FROM me_mediation_case mc");
//		hql.append(" WHERE ");
//		if(clientStateEnum.equals(ClientStateEnum.MediationCenter)){
//			hql.append(" mc.ALLOCATION_STATE in (:allocationState1,:allocationState2)");
//			hql.append(" AND mc.CASE_STATE IN (0,1)");
//			paramMap.put("allocationState1", CaseAllocationStateEnum.MediationCenterNotAccepted.ordinal());
//			paramMap.put("allocationState2", CaseAllocationStateEnum.MediationCenterAccepted.ordinal());
//		}
//		if(clientStateEnum.equals(ClientStateEnum.MediationAgency)){
//			hql.append(" mc.MEDIATOR_CLIENT_ID=:CLIENTIDPARAM and mc.ALLOCATION_STATE in (:allocationState1,:allocationState2)");
//			hql.append(" AND mc.CASE_STATE ='1'");
//			paramMap.put("CLIENTIDPARAM", clientid);
//			paramMap.put("allocationState1", CaseAllocationStateEnum.MediationCenterAllocated.ordinal());
//			paramMap.put("allocationState2", CaseAllocationStateEnum.AgencyManagerAccepted.ordinal());
//		}
//		if(clientStateEnum.equals(ClientStateEnum.Mediator)){
//			hql.append(" mc.MEDIATOR_CLIENT_ID=:CLIENTIDPARAM and mc.ALLOCATION_STATE = :allocationState1");
//			hql.append(" AND mc.CASE_STATE ='1'");
//			paramMap.put("CLIENTIDPARAM", clientid);
//			paramMap.put("allocationState1", CaseAllocationStateEnum.AgencyManagerAllocated.ordinal());
//			paramMap.put("allocationState2", CaseAllocationStateEnum.MediatorAccepted.ordinal());
//		}
//		
//		hql.append(" order by mc.APPLY_TIME desc ");
//		System.out.println(hql.toString());
//		List<MediationCaseInfoDto> list =this.findSqlBeanToList(hql.toString(), MediationCaseInfoDto.class, paramMap);
//		
//		if(list != null && list.size() > 0){
//			for(int i = 0; i < list.size(); i ++){
//				MediationCaseInfoDto dto = list.get(i);
//				String caseid = dto.getCaseId();
//				List<String> namelist = getCaseClientNameList(caseid);
//				dto.setNameList(namelist);
//			}
//		}
//		
//		return list;
//	}
	
	@Override
	public List<MediationCaseInfoDto> getMediationCaseListByClientState(
			String clientid, ClientStateEnum clientStateEnum,String mediationAgencyId)
			throws ErrorException {

		Map<String, Object> paramMap = Maps.newHashMap();
		final StringBuffer hql = new StringBuffer();
//		hql.append(this.select+" FROM me_mediation_case mc left join cl_client cl on mc.mediator_client_id = cl.ID ");
		hql.append(" SELECT mc.CASE_EXPLAIN AS caseExplain,");
		hql.append(" mc.ID as caseId,");
		hql.append(" mc.APPLY_CLIENT_NAME AS applyClientName,");
		hql.append(" mc.CASE_STATE as caseState,");
		hql.append(" mc.APPLY_TIME as applyDate,");
		hql.append(" mc.allocation_state as allocationState,cl.id as mediatorId, cl.tel as mediatorTel,cl.IDENTIFY_NAME as mediatorName ");
		hql.append(" FROM me_mediation_case mc left join cl_client cl on mc.mediator_client_id = cl.id ");
		hql.append(" WHERE 1=1 ");
		if(clientStateEnum.equals(ClientStateEnum.MediationCenter)){
//			hql.append(" mc.ALLOCATION_STATE in (:allocationState1,:allocationState2)");
			//案件状态 Init,//初始化，未受理,申请中	Allocation,//分配中
			hql.append(" AND mc.CASE_STATE IN (0,1)");
//			paramMap.put("allocationState1", CaseAllocationStateEnum.MediationCenterNotAccepted.ordinal());
//			paramMap.put("allocationState2", CaseAllocationStateEnum.MediationCenterAccepted.ordinal());
		}else if(StringUtils.isNotEmpty(mediationAgencyId) ){
			//绑定案件机构id
			hql.append(" AND mc.MEDIATION_AGENCY_ID=:mediationAgencyId ");
			paramMap.put("mediationAgencyId", mediationAgencyId);
		}
		
		if(clientStateEnum.equals(ClientStateEnum.MediationAgency)){
//			hql.append(" mc.MEDIATOR_CLIENT_ID=:CLIENTIDPARAM and mc.ALLOCATION_STATE in (:allocationState1,:allocationState2)");
			//案件状态 Allocation,//分配中
			hql.append("AND mc.CASE_STATE ='1'");
			hql.append("and mc.ALLOCATION_STATE > 1");
//			paramMap.put("CLIENTIDPARAM", clientid);
//			if(mediationAgencyId != null){
//				hql.append("AND mc.MEDIATOR_CLIENT_ID=:MEDIATORCLIENTID ");
//				paramMap.put("MEDIATORCLIENTID", mediationAgencyId);
//			}
				
//			paramMap.put("allocationState1", CaseAllocationStateEnum.MediationCenterAllocated.ordinal());
//			paramMap.put("allocationState2", CaseAllocationStateEnum.AgencyManagerAccepted.ordinal());
		}
		
		if(clientStateEnum.equals(ClientStateEnum.Mediator)){
			hql.append(" AND mc.MEDIATOR_CLIENT_ID=:CLIENTIDPARAM and mc.ALLOCATION_STATE = :allocationState1");
			hql.append(" AND mc.CASE_STATE ='1'");
			paramMap.put("CLIENTIDPARAM", clientid);
			paramMap.put("allocationState1", CaseAllocationStateEnum.AgencyManagerAllocated.ordinal());
			paramMap.put("allocationState2", CaseAllocationStateEnum.MediatorAccepted.ordinal());
		}
		hql.append(" order by mc.APPLY_TIME desc ");
		System.out.println(hql.toString());
		List<MediationCaseInfoDto> list =this.findSqlBeanToList(hql.toString(), MediationCaseInfoDto.class, paramMap);
		
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				MediationCaseInfoDto dto = list.get(i);
				String caseid = dto.getCaseId();
				List<String> namelist = getCaseClientNameList(caseid);
				dto.setNameList(namelist);
			}
		}
		
		return list;
	}
	
	List<String> getCaseClientNameList(String caseid){
		final StringBuffer sql  =new StringBuffer();
		sql.append(" SELECT tc.IDENTIFY_NAME AS name");
		sql.append(" FROM cl_tempclient tc");
		sql.append(" where tc.CASE_ID ='").append(caseid).append("'");
		sql.append(" and tc.PARTB=true");
		List<String> list = this.getJdbcTemplate().queryForList(sql.toString(), String.class);
		return list;
	}
	
	@Override
	public List<MediationCaseInfoDto> getMediatingCaseListByClientId(String clientid, Integer tabindex,ClientStateEnum clientStateEnum,String mediationAgencyId)
			throws ErrorException {		
		Map<String, Object> paramMap = Maps.newHashMap();
		
		final StringBuffer sql = new StringBuffer();
//		sql.append(" SELECT mc.CASE_EXPLAIN AS caseExplain,");
//		sql.append(" mc.ID as caseId,");
//		sql.append(" mc.APPLY_CLIENT_NAME AS applyClientName,");
//		sql.append(" mc.CASE_STATE as caseState,");
//		sql.append(" mc.APPLY_TIME as applyDate");
		sql.append(this.select+" FROM me_mediation_case mc  left join cl_client cl on mc.mediator_client_id = cl.ID");
		sql.append(" WHERE 1=1 ");
//		sql.append(" mc.MEDIATOR_CLIENT_ID=:CLIENTIDPARAM ");
//		paramMap.put("CLIENTIDPARAM", clientid);
		if(tabindex == 1){//当前调解
			sql.append(" AND mc.CASE_STATE IN (2,3,4) ");
		}else if(tabindex == 2){//历史调解
			sql.append(" AND mc.CASE_STATE IN (5,6,7,8) ");
		}
		//如果不是管理员按机构查  是管理员查所有
		if(!clientStateEnum.equals(ClientStateEnum.MediationCenter)){
			if(mediationAgencyId!=null){
				sql.append(" AND mc.MEDIATION_AGENCY_ID=:mediationAgencyId ");
				paramMap.put("mediationAgencyId", mediationAgencyId);
			}
		}
		//如果是调剂员只能查自己
		if(clientStateEnum.equals(ClientStateEnum.Mediator)){
			sql.append(" AND mc.MEDIATOR_CLIENT_ID=:CLIENTIDPARAM ");
			paramMap.put("CLIENTIDPARAM", clientid);
		}
		sql.append(" order by mc.APPLY_TIME desc ");
		
		List<MediationCaseInfoDto> list =this.findSqlBeanToList(sql.toString(), MediationCaseInfoDto.class, paramMap);
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				MediationCaseInfoDto dto = list.get(i);
				String caseid = dto.getCaseId();
				List<String> namelist = getCaseClientNameList(caseid);
				dto.setNameList(namelist);
			}
		}
		
		return list;
	}
	
	@Override
	public List<MediationCaseInfoDto> searchMediationCaseByName(
			String clientid, String casename) throws BizException {
		
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuffer sql  =new StringBuffer();
//		sql.append(" SELECT mc.CASE_EXPLAIN AS caseExplain,");
//		sql.append(" mc.ID as caseId,");
//		sql.append(" mc.APPLY_CLIENT_NAME AS applyClientName,");
//		sql.append(" mc.CASE_STATE as caseState,");
//		sql.append(" mc.APPLY_TIME as applyDate");
		sql.append(this.select+" FROM me_mediation_case mc left join cl_client cl on mc.mediator_client_id = cl.ID");
		sql.append(" WHERE ");
		sql.append(" mc.MEDIATOR_CLIENT_ID='").append(clientid).append("'");
		if(StringUtils.isNotEmpty(casename)){
			sql.append(" and mc.CASE_EXPLAIN like '%").append(casename).append("%'");
		}
		sql.append(" order by mc.APPLY_TIME desc");
		List<MediationCaseInfoDto> list =this.findSqlBeanToList(sql.toString(), MediationCaseInfoDto.class, paramMap);
		
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				MediationCaseInfoDto dto = list.get(i);
				String caseid = dto.getCaseId();
				List<String> namelist = getCaseClientNameList(caseid);
				dto.setNameList(namelist);
			}
		}
		
		return list;
	}
	
	@Override
	public List<MediationCaseInfoDto> getListForNormalClient(String clientid,
			Integer tabindex) throws ErrorException {
		Map<String, Object> paramMap = Maps.newHashMap();
		StringBuffer sql  =new StringBuffer();
		List<MediationCaseInfoDto> list = null;
		if(clientid!=null){
			//用户
			
			sql  =new StringBuffer();

			sql.append(this.select+" FROM me_mediation_case mc  ");
			sql.append(" LEFT JOIN cl_client cl ON cl.ID = mc.APPLY_CLIENT_ID");
			sql.append(" where mc.APPLY_CLIENT_ID ='").append(clientid).append("'");
			if(tabindex == 0){//申请中
				sql.append(" AND mc.CASE_STATE IN (0,1) ");
			}else if(tabindex == 1){//调节中
				sql.append(" AND mc.CASE_STATE IN (2,3,4) ");
			}else if(tabindex == 2){//已关闭
				sql.append(" AND mc.CASE_STATE IN (5,6,7,8) ");
			}
			
			list =this.findSqlBeanToList(sql.toString(), MediationCaseInfoDto.class, paramMap);
			

			//临时用户
			
			sql  =new StringBuffer();
			
			sql.append(this.select+" FROM me_mediation_case mc ");
			sql.append(" LEFT JOIN cl_tempclient tc");
			sql.append(" ON tc.CASE_ID=mc.ID");
			sql.append(" LEFT JOIN cl_client cl ON cl.ID = tc.CLIENT_ID");
			sql.append(" WHERE tc.CLIENT_ID ='").append(clientid).append("'");
			
			if(tabindex == 0){//申请中
				sql.append(" AND mc.CASE_STATE IN (0,1) ");
			}else if(tabindex == 1){//调节中
				sql.append(" AND mc.CASE_STATE IN (2,3,4) ");
			}else if(tabindex == 2){//已关闭
				sql.append(" AND mc.CASE_STATE IN (5,6,7,8) ");
			}
			List<MediationCaseInfoDto> list2 =this.findSqlBeanToList(sql.toString(), MediationCaseInfoDto.class, paramMap);

			list.addAll(list2);
			
			if(list != null && list.size() > 0){
				for(int i = 0; i < list.size(); i ++){
					MediationCaseInfoDto dto = list.get(i);
					String caseid = dto.getCaseId();
					List<String> namelist = getCaseClientNameList(caseid);
					dto.setNameList(namelist);
				}
			}
		}
		return list;
	}
	
	@Override
	public Map<String, Object> statisticsMediationCaseByClientId(String clientid, 
			String clientstate, String agencyid,String startTime,String endTime,String mediationAgencyId) throws ErrorException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Object> query = new ArrayList<Object>();
		StringBuffer sqlparam = new StringBuffer();
		Object[] param = null;
		if(StringUtils.isNotEmpty(startTime.trim())){
			sqlparam.append(" and APPLY_TIME > ? ");
			query.add(startTime);
		}
		if(StringUtils.isNotEmpty(endTime.trim())){
			sqlparam.append(" and APPLY_TIME < ? ");
			query.add(endTime);
		}
		if(StringUtils.isNotEmpty(mediationAgencyId)){
			sqlparam.append(" and MEDIATION_AGENCY_ID = ? ");
			query.add(mediationAgencyId.trim());
		}
		if(clientstate.equals(ClientStateEnum.MediationCenter.toString())){
			param =getParam(query);
			StringBuffer sql = new StringBuffer();
			sql.append("select count(CASE_STATE) as state, max(CASE_STATE) as name from me_mediation_case where 1=1 ");
			sql.append(sqlparam);
			sql.append(" GROUP BY CASE_STATE ");
			List<Map<String, Object>> rows = this.getJdbcTemplate().queryForList(sql.toString(),param);
			int count  = 0;
			for(Map<String, Object> ma : rows){
				count+= Integer.parseInt(ma.get("state").toString());
			}
			resultMap.put("list", rows);
			resultMap.put("total",count);
			return resultMap;
		}else if(clientstate.equals(ClientStateEnum.MediationAgency.toString())){
			String me_mediation_case = "(select CASE_STATE,MEDIATOR_CLIENT_ID from me_mediation_case where 1=1 " + sqlparam.toString()+")";
			
			String cl_client = "(select id from cl_client ";
			if(StringUtils.isNotEmpty(agencyid.trim())){
				cl_client+=" where MEDIATION_AGENCY_ID =  ? ";
				query.add(agencyid);
			}
			cl_client += ")" ;
			StringBuffer sql = new StringBuffer();
			sql.append("select  count(mmc.CASE_STATE) as state, max(mmc.CASE_STATE)  as name from "+me_mediation_case+" mmc " );
			sql.append(" inner join "+ cl_client +" cc on mmc.MEDIATOR_CLIENT_ID = cc.id GROUP BY mmc.CASE_STATE ");
			param =getParam(query);
			List<Map<String, Object>> rows = this.getJdbcTemplate().queryForList(sql.toString(),param);
			int count = 0;
			for(Map<String, Object> ma : rows){
				count+= Integer.parseInt(ma.get("state").toString());
			}
			resultMap.put("list", rows);
			resultMap.put("total", count);
			return resultMap;
		}else{
			if(clientstate.equals(ClientStateEnum.Mediator.toString())){
				sqlparam.append(" and MEDIATOR_CLIENT_ID = ? ");
				query.add(clientid);
			}
			if(StringUtils.isNotEmpty(mediationAgencyId)){
				sqlparam.append(" and MEDIATION_AGENCY_ID = ? ");
				query.add(mediationAgencyId.trim());
			}
			param =getParam(query);
			StringBuffer sql = new StringBuffer();
			sql.append("select count(CASE_STATE) as state, max(CASE_STATE) as name from me_mediation_case where 1=1 ");
			sql.append(sqlparam);
			sql.append(" GROUP BY CASE_STATE ");
			List<Map<String, Object>> rows = this.getJdbcTemplate().queryForList(sql.toString(),param);
			int count  = 0;
			for(Map<String, Object> ma : rows){
				count+= Integer.parseInt(ma.get("state").toString());
			}
			resultMap.put("list", rows);
			resultMap.put("total",count);
			return resultMap;
		}
	}
	
	
	@Override
	public Map<String, Object> statisticsMediationCaseByClientIdSuccess(String clientid, 
			String clientstate, String agencyid,String startTime,String endTime,String mediationAgencyId) throws ErrorException {
		return statisticsMediationCaseByClientId(clientid,clientstate,agencyid,startTime,endTime,mediationAgencyId,true);
	}
	
	public Map<String, Object> statisticsMediationCaseByClientId(String clientid, 
			String clientstate, String agencyid,String startTime,String endTime,String mediationAgencyId,
			Boolean caseState) throws ErrorException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		List<Object> query = new ArrayList<Object>();
		StringBuffer sqlparam = new StringBuffer();
		Object[] param = null;
		if(StringUtils.isNotEmpty(startTime.trim())){
			sqlparam.append(" and APPLY_TIME > ? ");
			query.add(startTime);
		}
		if(StringUtils.isNotEmpty(endTime.trim())){
			sqlparam.append(" and APPLY_TIME < ? ");
			query.add(endTime);
		}
		if(caseState){
			sqlparam.append(" and CASE_STATE = ? ");
			query.add(CaseStateEnum.Completed.ordinal());
		}
		if(StringUtils.isNotEmpty(mediationAgencyId)){
			sqlparam.append(" and MEDIATION_AGENCY_ID = ? ");
			query.add(mediationAgencyId.trim());
		}
		//按调解机构查
		if(clientstate.equals(ClientStateEnum.MediationAgency.toString())){
			String me_mediation_case = "(select CASE_TYPE,MEDIATOR_CLIENT_ID from me_mediation_case where 1=1" + sqlparam.toString()+")";
			
			String cl_client = "(select id from cl_client ";
			if(StringUtils.isNotEmpty(agencyid.trim())){
				cl_client+=" where MEDIATION_AGENCY_ID =  ? ";
				query.add(agencyid);
			}
			cl_client += ")" ;
			StringBuffer sql = new StringBuffer();
			sql.append("select  count(mmc.CASE_TYPE) as state, max(mmc.CASE_TYPE) as name from "+me_mediation_case+" mmc " );
			sql.append(" inner join "+ cl_client +" cc on mmc.MEDIATOR_CLIENT_ID = cc.id GROUP BY mmc.CASE_TYPE ");
			param =getParam(query);
			List<Map<String, Object>> rows = this.getJdbcTemplate().queryForList(sql.toString(),param);
//			int count = this.getJdbcTemplate().queryForObject("select  SUM(m.state) as total from ("+sql.toString()+") m",param,Integer.class);
			int count = 0;
			for(Map<String, Object> ma : rows){
				count+= Integer.parseInt(ma.get("state").toString());
			}
			resultMap.put("list", rows);
			resultMap.put("total", count);
			return resultMap;
		}else{
			if(clientstate.equals(ClientStateEnum.Mediator.toString())){
				sqlparam.append(" and MEDIATOR_CLIENT_ID = ? ");
				query.add(clientid.trim());
			}
			if(StringUtils.isNotEmpty(mediationAgencyId)){
				sqlparam.append(" and MEDIATION_AGENCY_ID = ? ");
				query.add(mediationAgencyId.trim());
			}
			param =getParam(query);
			StringBuffer sql = new StringBuffer();
			sql.append("select count(CASE_TYPE) as state,max(CASE_TYPE) as name from me_mediation_case where 1=1");
			sql.append(sqlparam);
			sql.append("GROUP BY CASE_TYPE");
			List<Map<String, Object>> rows = this.getJdbcTemplate().queryForList(sql.toString(),param);
//			int count  = this.getJdbcTemplate().queryForObject(" select  SUM(m.state) as total from("+sql.toString()+") m",param,Integer.class);
			int count = 0;
			for(Map<String, Object> ma : rows){
				count+= Integer.parseInt(ma.get("state").toString());
			}
			resultMap.put("list", rows);
			resultMap.put("total",count);
			return resultMap;
		}
	}
	
	@Override
	public Map<String, Object> statisticsMediationCaseByClientId(String clientid, 
			String clientstate, String agencyid,String startTime,String endTime ) throws ErrorException {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		//统计状态sql
		StringBuffer sql  =new StringBuffer();
		sql.append(" SELECT CASE_STATE as state,");
		sql.append(" COUNT(*) as count");
		sql.append(" FROM me_mediation_case");
		
		//获取总数sql
		StringBuffer totalSql  =new StringBuffer();
		totalSql.append(" SELECT COUNT(*) as total");
		totalSql.append(" FROM me_mediation_case");
		
		if(clientstate.equals(ClientStateEnum.MediationAgency.toString())){
			
			sql.append(" WHERE MEDIATOR_CLIENT_ID IN");
			sql.append(" (select ID from cl_client where MEDIATION_AGENCY_ID ='").append(agencyid).append("')");
			
			totalSql.append(" WHERE MEDIATOR_CLIENT_ID IN");
			totalSql.append(" (select ID from cl_client where MEDIATION_AGENCY_ID ='").append(agencyid).append("')");
			
		}else if(clientstate.equals(ClientStateEnum.Mediator.toString())){
			
			sql.append(" WHERE MEDIATOR_CLIENT_ID='").append(clientid).append("'");
			
			totalSql.append(" WHERE MEDIATOR_CLIENT_ID='").append(clientid).append("'");
			
		}
		
		if(StringUtils.isNotEmpty(startTime) && StringUtils.isNotEmpty(endTime)){
			
			sql.append(" and date_format(APPLY_TIME,'%Y-%m-%d %h:%i') between '").append(startTime)
			.append("' and '").append(endTime).append("'");
			
			totalSql.append(" and date_format(APPLY_TIME,'%Y-%m-%d %h:%i') between '").append(startTime)
			.append("' and '").append(endTime).append("'");
			
			
		}else if(StringUtils.isNotEmpty(startTime) && StringUtils.isEmpty(endTime)){
			
			sql.append(" and date_format(APPLY_TIME,'%Y-%m-%d %h:%i') > '").append(startTime).append("'");
			
			totalSql.append(" and date_format(APPLY_TIME,'%Y-%m-%d %h:%i') > '").append(startTime).append("'");
			
		}else if(StringUtils.isEmpty(startTime) && StringUtils.isNotEmpty(endTime)){
			
			sql.append(" and date_format(APPLY_TIME,'%Y-%m-%d %h:%i') <'").append(endTime).append("'");
			
			totalSql.append(" and date_format(APPLY_TIME,'%Y-%m-%d %h:%i') <'").append(endTime).append("'");
		}
		
		sql.append(" GROUP BY CASE_STATE");
		
		List<Map<String, Object>> rows = this.getJdbcTemplate().queryForList(sql.toString());
		
		Map<String, Object> map = this.getJdbcTemplate().queryForMap(totalSql.toString());
		
		resultMap.put("list", rows);
		resultMap.put("total", map.containsKey("total")?map.get("total"):0);
		
		return resultMap;
		
	}
	
//	@Override
//	public List<MediationCaseExcelDto> statMediationCaseExcelDto(String year,String month) throws ErrorException {
//		
//		String statmonth = "";
//		if(StringUtils.isEmpty(month)){
//			statmonth = year;
//		}else{
//			statmonth = year+"-"+month;
//		}
//		
//		List<MediationCaseExcelDto> list = new ArrayList<MediationCaseExcelDto>();
//		
//		//按街道管理员分组
//		StringBuffer streetsql  =new StringBuffer();
//		streetsql.append(" SELECT ma.BELONGS_TO as street");
//		streetsql.append(" FROM me_mediation_agency ma");
//		streetsql.append(" GROUP BY ma.BELONGS_TO");
//		List<String> streetlist = this.getJdbcTemplate().queryForList(streetsql.toString(), String.class);
//		
//		if(streetlist != null && streetlist.size() > 0){
//			for(int i = 0; i < streetlist.size(); i ++){
//				String street = streetlist.get(i);
//				StringBuffer sql  =new StringBuffer();
//				sql.append(" SELECT "); 
//				sql.append(" CONVERT(COUNT(*),DECIMAL) AS totalNum, ");
//				sql.append(" IFNULL(CONVERT(SUM(mc.PEOPLE),DECIMAL),0) AS people, ");
//				sql.append(" CONVERT(( ");
//				sql.append(" SELECT COUNT(*) FROM me_mediation_case WHERE AGENCY_BELONGS_TO ='").append(street).append("'"); 
//				sql.append(" AND CASE_STATE='7' ");
//				sql.append(" ),DECIMAL)AS successNum, ");
//				sql.append(" CONVERT(( ");
//				sql.append(" SELECT COUNT(*) FROM me_mediation_case WHERE AGENCY_BELONGS_TO ='").append(street).append("'");
//				sql.append(" AND DIFFICULT=TRUE ");
//				sql.append(" ),DECIMAL)AS specialNum, ");
//				sql.append(" IFNULL(( ");
//				sql.append(" SELECT SUM(CONVERT(CASE_MONEY,DECIMAL)) FROM me_mediation_case ");
//				sql.append(" WHERE AGENCY_BELONGS_TO ='").append(street).append("'");
//				sql.append(" ),0)AS money ");
//
//				sql.append(" FROM me_mediation_case mc ");
//				sql.append(" WHERE mc.AGENCY_BELONGS_TO ='").append(street).append("'");
//				sql.append(" and mc.CASE_STATE IN (5,7)");
//				if(StringUtils.isEmpty(month)){
//					sql.append(" and date_format(APPLY_TIME,'%Y') ='").append(statmonth).append("'");
//				}else{
//					sql.append(" and date_format(APPLY_TIME,'%Y-%m') ='").append(statmonth).append("'");
//				}
//				
//				MediationCaseExcelDto dto = this.findSqlBeanToUniqueResult(sql.toString(), 
//						MediationCaseExcelDto.class, null);
////				"SELECT COUNT(CASE_TYPE), MAX(CASE_TYPE),AGENCY_BELONGS_TO FROM me_mediation_case GROUP  BY AGENCY_BELONGS_TO,CASE_TYPE"
//				List<Map<String, Object>> hostNumList = this.getHostMediationCaseNumList(street,month, statmonth);
//				List<Map<String, Object>> sourceNumList = this.getSourceMediationCaseNumList(street,month, statmonth);
//				List<Map<String, Object>> typeNumList = this.getTypeMediationCaseNumList(street,month, statmonth);
//				List<Map<String, Object>> protocolNumList = this.getProtocolMediationCaseNumList(street,month, statmonth);
//				
//				dto.setStreet(street);
//				dto.setHostNumList(hostNumList);
//				dto.setSourceNumList(sourceNumList);
//				dto.setTypeNumList(typeNumList);
//				dto.setProtocolNumList(protocolNumList);
//				
//				list.add(dto);
//			}
//		}
//		
//		return list;
//	}
	@Override
	public HashMap<String,HashMap<String,Object>> statMediationCaseExcelDto(String begin,String end){
		//按街道管理员分组 获取所有街道
		StringBuffer streetsql  =new StringBuffer();
		streetsql.append(" SELECT ma.BELONGS_TO as street");
		streetsql.append(" FROM me_mediation_agency ma");
		streetsql.append(" GROUP BY ma.BELONGS_TO");
		List<String> streetlist = this.getJdbcTemplate().queryForList(streetsql.toString(), String.class);
		
		List<Map<String, Object>> li = getMe_Mediation_Case(begin, end);
		
		HashMap<String,HashMap<String,Object>> ma = new HashMap<String,HashMap<String,Object>>();
		
		initMa(ma,streetlist);
		
		for(Map<String,Object> liMap : li){
			HashMap<String,Object> test = null;
			for(String s: streetlist){
				if(s.equals(getKey(liMap.get("jiedao").toString()))){
					test = ma.get(getKey(liMap.get("jiedao").toString()));
					break;
				}
			}
			//尼玛数据乱填根本没这个街道把
			if(test==null)
				continue;
			//街道
			test.put("jiedao",liMap.get("jiedao").toString().trim());
			//涉案人数
			test.put("ajsjrs", getInt(test.get("ajsjrs")) + getInt(liMap.get("ajsjrs")));		
			//案件总调解数
			test.put("tiaojzs", getInt(test.get("tiaoj"))+ getInt(liMap.get("val")));		
			//调节成功
			test.put("cgla",getInt(test.get("cgla"))+ getInt(liMap.get("cgla")));		
			//疑难复杂案件
			test.put("ynfzaj", getInt(test.get("ajsjrs"))+ getInt(liMap.get("ynfzaj")));		
			//涉案金额
			test.put("ajsjje",getInt(test.get("ajsjje"))+ getInt(liMap.get("ajsjje")));		
			//信访
			test.put("xinfan", getInt(test.get("xinfan"))+ getInt(liMap.get("xinfan")));		
			//法院调解
			test.put("fayuan", getInt(test.get("fayuan"))+ getInt(liMap.get("fayuan")));		
			//主动调解
			test.put("zhudong", getInt(test.get("zhudong"))+ getInt(liMap.get("zhudong")));		
			
			test.put("gongan",getInt(test.get("gongan"))+ getInt(liMap.get("gongan")));		
			
			test.put("qita", getInt(test.get("qita"))+ getInt(liMap.get("qita")));		
			
			test.put("yishen", getInt(test.get("yishen"))+ getInt(liMap.get("yishen")));		
			
			test.put("jiancha", getInt(test.get("jiancha"))+ getInt(liMap.get("jiancha")));		
			//接受委托
			test.put("jiesou", getInt(test.get("jiesou"))+ getInt(liMap.get("jiesou")));		
			//口头协议
			test.put("ktxy", getInt(test.get("ktxy"))+ getInt(liMap.get("ktxy")));
			//书面协议
			test.put("smxy", getInt(test.get("smxy"))+ getInt(liMap.get("smxy")));
			
			//企事业单位委员调解案件数
			test.put("QSYTJAJS", getInt(test.get("QSYTJAJS")) + getInt(liMap.get("QSYTJAJS")));		
			
			//街道委员会调解案件数
			test.put("JDWYHTJAJS", getInt(test.get("JDWYHTJAJS")) + getInt(liMap.get("JDWYHTJAJS")));		
			
			getMap(test,liMap.get("name").toString(),getInt(liMap.get("val")));
		}
		return ma;
	}
	
	
	/**
	 * 初始化参数ma
	 * @param ma 
	 * @param name 累加的key
	 * @param num 需要统计的数量
	 */
	public void getMap(HashMap<String,Object> ma,String name,int num){
		name = name.trim();
		if(ma.get(name) == null)
			ma.put(name, num);
		else
			ma.put(name, getInt(ma.get(name).toString())+num);
	}
	
	
	/**
	 * string转int
	 */
	public int getInt(Object src){
		int i = 0;
		if(src != null)
			i = Integer.parseInt(src.toString());
		return i;
	}
	
	/**
	 * 初始化参数ma
	 * @param ma
	 * @param streetlist
	 */
	public void initMa(HashMap<String,HashMap<String,Object>> ma,List<String> streetlist){
		for(String key:streetlist){
			ma.put(getKey(key), new HashMap<String,Object>());
		}
	}
	
	public String getKey(String key){
//		String k =null;
//		if(key.length()>3){
//			k=key.substring(0,3);
//			return k.t;
//		}
//		else
			return key.trim();
	}
	
	
	/**
	 * 统计案件分类 按照下面
	 * 案件类型 caseType
	 * 调节案件总数
	 * difficult=false;//是否疑难复杂案件
	 * people;//案件涉及人数
	 * caseState;//案件状态 枚举  Completed,//调解员点击结案，已结案  7
	 * protocolForm //协议形式
	 * CASE_MONEY //涉案金额
	 * CASE_SOURCE //案件来源
	 * @return
	 * @throws SQLException 
	 */ 
	public List<Map<String, Object>> getMe_Mediation_Case(final String begin,final String end){
//		Object[] obj = null;
//		//中文转义不出 匹配不了查询条件
//		String sql = "SELECT "
//		+ "COUNT(CASE_TYPE) as val, "
//		+ "MAX(CASE_TYPE) as name, " //案件分类
//		+" sum(people) as ajsjrs, "//涉案人数
//		+" case when CASE_STATE = 7  then SUM(1) else SUM(0) end as cgla, "//是否调节成功
//		+" sum(CASE_MONEY) as ajsjje, "//涉案金额
//		+" case when difficult = 1  then SUM(1) else SUM(0) end as ynfzaj," //是否疑难复杂案件
//		
//		+" case when PROTOCOL_FORM = 0  then SUM(1)  else SUM(0) end as ktxy, "//口头协议
//		
//		+" case when PROTOCOL_FORM = 1  then SUM(1)  else SUM(0) end as smxy, " //书面协议
//
//		+" case when CASE_SOURCE = '信访部门委托移送'  then SUM(1)  else SUM(0) end as xinfan ,"	//来源
//
//		+" case when CASE_SOURCE = '法院委托移送'  then SUM(1)  else SUM(0) end as fayuan, "//来源
//
//		+" case when CASE_SOURCE = '主动调解'  then SUM(1)  else SUM(0) end as zhudong ," //来源
//
//		+" case when CASE_SOURCE = '公安机关委托移送'  then SUM(1)  else SUM(0) end as gongan ," //来源
//
//		+" case when CASE_SOURCE = '其他部门委托移送'  then SUM(1)  else SUM(0) end as qita ," //来源
//
//		+" case when CASE_SOURCE = '依申请调解'  then SUM(1)  else SUM(0) end as yishen ," //来源
//		
//		+" case when CASE_SOURCE = '检察院委托移送'  then SUM(1)  else SUM(0) end as jiancha ,"//来源
//		
//		+" case when CASE_SOURCE = '接受委托移送调解'  then SUM(1)  else SUM(0) end as jiesou ," //来源
//
//		+" AGENCY_BELONGS_TO as jiedao "
//		
//		+" FROM me_mediation_case  WHERE 1=1 ";//排除 AGENCY_BELONGS_TO  街道为空的统计
//		if(begin != null){
//			sql+=	" and APPLY_TIME > ? ";
//			obj =new Object[]{begin}; 
//		}
//		if(end!=null){
//			sql+= "and APPLY_TIME < ? ";
//			obj =new Object[]{end}; 
//		}
//		sql += "and  AGENCY_BELONGS_TO iS not NULL GROUP  BY AGENCY_BELONGS_TO,CASE_TYPE,CASE_STATE ";
//		if(begin != null && end!=null)
//			obj =new Object[]{begin,end}; 
//		List<Map<String, Object>> li =   this.getJdbcTemplate().queryForList(sql,obj);
		//调用存储过程
		Connection con =null;
		List<Map<String, Object>> li= new ArrayList<Map<String, Object>>();
		try {
			 con = dataSource.getConnection();  
	         String sql = "{call getTJHC(?,?)}";  
	         CallableStatement cs = con.prepareCall(sql);
	         cs.setString(1, begin);
	         cs.setString(2, end);
	         boolean hadResults = cs.execute();  
	         int i=0;  
	         while (hadResults) {
	        	 System.out.println("result No:----"+(++i));  
	        	 ResultSet rs = cs.getResultSet();  
	        	 while (rs != null && rs.next()) {
		              Map<String,Object> rowMap = new HashMap<String,Object>();   
		              rowMap.put("val", rs.getString("val"));   
		              rowMap.put("name", rs.getString("name")); 
		              rowMap.put("ajsjrs", rs.getString("ajsjrs"));
		              rowMap.put("cgla", rs.getString("cgla"));
		              rowMap.put("ynfzaj", rs.getString("ynfzaj"));
		              rowMap.put("ajsjje", rs.getString("ajsjje"));
		              rowMap.put("ktxy", rs.getString("ktxy"));
		              rowMap.put("smxy", rs.getString("smxy"));
		              rowMap.put("xinfan", rs.getString("xinfan"));
		              rowMap.put("fayuan", rs.getString("fayuan"));
		              rowMap.put("zhudong", rs.getString("zhudong"));
		              rowMap.put("gongan", rs.getString("gongan"));
		              rowMap.put("qita", rs.getString("qita"));
		              rowMap.put("yishen", rs.getString("yishen"));
		              rowMap.put("jiancha", rs.getString("jiancha"));
		              rowMap.put("jiesou", rs.getString("jiesou"));
		              rowMap.put("jiedao", rs.getString("jiedao"));
		              rowMap.put("QSYTJAJS", rs.getString("QSYTJAJS"));   
		              rowMap.put("JDWYHTJAJS", rs.getString("JDWYHTJAJS"));   
		              li.add(rowMap); 
	        	 }  
	        	 hadResults = cs.getMoreResults(); //检查是否存在更多结果集  
	       }  
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return li;
	}
	
	
	public List<Map<String, Object>> getHostMediationCaseNumList(String street,String month, String statmonth){
		
		StringBuffer agencyClassifysql  =new StringBuffer();
		agencyClassifysql.append(" SELECT ma.AGENCY_CLASSIFY as classfy");
		agencyClassifysql.append(" FROM me_mediation_agency ma");
		agencyClassifysql.append(" GROUP BY ma.AGENCY_CLASSIFY");
		List<String> classfylist = this.getJdbcTemplate().queryForList(agencyClassifysql.toString(), String.class);
		
		
		StringBuffer sql  =new StringBuffer();
		sql.append(" SELECT AGENCY_CLASSIFY as classfy,");
		sql.append(" COUNT(*) as count");
		sql.append(" FROM me_mediation_case");
		sql.append(" WHERE AGENCY_BELONGS_TO ='").append(street).append("'");
		sql.append(" and CASE_STATE IN (5,7)");
		if(StringUtils.isEmpty(month)){
			sql.append(" and date_format(APPLY_TIME,'%Y') ='").append(statmonth).append("'");
		}else{
			sql.append(" and date_format(APPLY_TIME,'%Y-%m') ='").append(statmonth).append("'");
		}
		sql.append(" group by AGENCY_CLASSIFY");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		
		if(classfylist != null && classfylist.size() > 0){
			Map<String, Object> data = Maps.newHashMap();
			for(int i = 0; i < classfylist.size(); i ++){
				String agencyClassify = classfylist.get(i);
				if(list != null && list.size() > 0){
					for(Map<String, Object> map: list){
						if(!map.containsKey(agencyClassify)){
							data = Maps.newHashMap();
							data.put("classfy", agencyClassify);
							data.put("count", 0);
						}
					}
				}else{
					list = new ArrayList<Map<String, Object>>();
					data = Maps.newHashMap();
					data.put("classfy", agencyClassify);
					data.put("count", 0);
				}
				list.add(data);
			}
		}
		
		return list;
	}
	public List<Map<String, Object>> getSourceMediationCaseNumList(String street,String month, String statmonth){
		
		StringBuffer casesourcesql  =new StringBuffer();
		casesourcesql.append(" SELECT DATA_VALUE as source");
		casesourcesql.append(" FROM me_basic_data");
		casesourcesql.append(" where DATA_TYPE ='CaseSource'");
		List<String> casesourcelist = this.getJdbcTemplate().queryForList(casesourcesql.toString(), String.class);
		
		StringBuffer sql  =new StringBuffer();
		sql.append(" SELECT CASE_SOURCE as source,");
		sql.append(" COUNT(*) as count");
		sql.append(" FROM me_mediation_case");
		sql.append(" WHERE AGENCY_BELONGS_TO ='").append(street).append("'");
		sql.append(" and CASE_STATE IN (5,7)");
		if(StringUtils.isEmpty(month)){
			sql.append(" and date_format(APPLY_TIME,'%Y') ='").append(statmonth).append("'");
		}else{
			sql.append(" and date_format(APPLY_TIME,'%Y-%m') ='").append(statmonth).append("'");
		}
		sql.append(" group by CASE_SOURCE");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		
		if(casesourcelist != null && casesourcelist.size() > 0){
			Map<String, Object> data = Maps.newHashMap();
			for(int i = 0; i < casesourcelist.size(); i ++){
				String casesource = casesourcelist.get(i);
				if(list != null && list.size() > 0){
					for(Map<String, Object> map: list){
						if(!map.containsKey(casesource)){
							data = Maps.newHashMap();
							data.put("source", casesource);
							data.put("count", 0);
						}
					}
				}else{
					list = new ArrayList<Map<String, Object>>();
					data = Maps.newHashMap();
					data.put("source", casesource);
					data.put("count", 0);
				}
				list.add(data);
			}
		}
		
		return list;
	}
	public List<Map<String, Object>> getTypeMediationCaseNumList(String street,String month, String statmonth){
		
		StringBuffer casetypesql  =new StringBuffer();
		casetypesql.append(" SELECT DATA_VALUE as type");
		casetypesql.append(" FROM me_basic_data");
		casetypesql.append(" where DATA_TYPE ='CivilCaseType'");
		List<String> casetypelist = this.getJdbcTemplate().queryForList(casetypesql.toString(), String.class);
		
		
		StringBuffer sql  = new StringBuffer();
		sql.append(" SELECT CASE_TYPE as type,");
		sql.append(" COUNT(*) as count");
		sql.append(" FROM me_mediation_case");
		sql.append(" WHERE AGENCY_BELONGS_TO ='").append(street).append("'");
		sql.append(" and CASE_STATE IN (5,7)");
		if(StringUtils.isEmpty(month)){
			sql.append(" and date_format(APPLY_TIME,'%Y') ='").append(statmonth).append("'");
		}else{
			sql.append(" and date_format(APPLY_TIME,'%Y-%m') ='").append(statmonth).append("'");
		}
		sql.append(" group by CASE_TYPE");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		
		if(casetypelist != null && casetypelist.size() > 0){
			Map<String, Object> data = Maps.newHashMap();
			for(int i = 0; i < casetypelist.size(); i ++){
				String casetype = casetypelist.get(i);
				if(list != null && list.size() > 0){
					for(Map<String, Object> map: list){
						if(!map.containsKey(casetype)){
							data = Maps.newHashMap();
							data.put("type", casetype);
							data.put("count", 0);
						}
					}
				}else{
					list = new ArrayList<Map<String, Object>>();
					data = Maps.newHashMap();
					data.put("type", casetype);
					data.put("count", 0);
				}
				list.add(data);
			}
		}
		
		return list;
	}
	public List<Map<String, Object>> getProtocolMediationCaseNumList(String street,String month, String statmonth){
		
		StringBuffer protocolsql  =new StringBuffer();
		protocolsql.append(" SELECT DATA_VALUE as protocol");
		protocolsql.append(" FROM me_basic_data");
		protocolsql.append(" where DATA_TYPE ='ProtocolForm'");
		List<String> protocollist = this.getJdbcTemplate().queryForList(protocolsql.toString(), String.class);
		
		StringBuffer sql  =new StringBuffer();
		sql.append(" SELECT PROTOCOL_FORM as protocol,");
		sql.append(" COUNT(*) as count");
		sql.append(" FROM me_mediation_case");
		sql.append(" WHERE AGENCY_BELONGS_TO ='").append(street).append("'");
		sql.append(" and CASE_STATE IN (5,7)");
		if(StringUtils.isEmpty(month)){
			sql.append(" and date_format(APPLY_TIME,'%Y') ='").append(statmonth).append("'");
		}else{
			sql.append(" and date_format(APPLY_TIME,'%Y-%m') ='").append(statmonth).append("'");
		}
		sql.append(" group by PROTOCOL_FORM");
		List<Map<String, Object>> list = this.getJdbcTemplate().queryForList(sql.toString());
		
		if(protocollist != null && protocollist.size() > 0){
			Map<String, Object> data = Maps.newHashMap();
			for(int i = 0; i < protocollist.size(); i ++){
				String protocol = protocollist.get(i);
				if(list != null && list.size() > 0){
					for(Map<String, Object> map: list){
						if(!map.containsKey(protocol)){
							data = Maps.newHashMap();
							data.put("protocol", protocol);
							data.put("count", 0);
						}
					}
				}else{
					list = new ArrayList<Map<String, Object>>();
					data = Maps.newHashMap();
					data.put("protocol", protocol);
					data.put("count", 0);
				}
				list.add(data);
			}
		}
		
		return list;
	}
	
}