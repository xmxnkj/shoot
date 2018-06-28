package com.szit.arbitrate.client.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.client.dao.ClientDao;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.client.entity.query.ClientQuery;

@Repository
public class ClientDaoImpl extends BaseHibernateDaoImpl<Client, ClientQuery> 
	implements ClientDao{
	
	@Override
	public List<QueryParam> buildQueryParams(ClientQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (query.getThirdParty()!=null) {
				qps.add(new QueryParam("thirdParty", query.getThirdParty()));
			}
			if (!StringUtils.isEmpty(query.getNickName())) {
				qps.add(new QueryParam("nickName", query.getNickName()));
			}
			if (!StringUtils.isEmpty(query.getAccount())) {
				qps.add(new QueryParam("account", query.getAccount()));
			}
			if (!StringUtils.isEmpty(query.getThirdPartyId())) {
				qps.add(new QueryParam("thirdPartyId", query.getThirdPartyId()));
			}
			if (!StringUtils.isEmpty(query.getMediationAgencyId())) {
				qps.add(new QueryParam("mediationAgencyId", query.getMediationAgencyId()));
			}
			if (!StringUtils.isEmpty(query.getIdentifyName())) {
				qps.add(new QueryParam("identifyName", query.getIdentifyName()));
			}
			if (!StringUtils.isEmpty(query.getIdentify())) {
				qps.add(new QueryParam("identify", query.getIdentify()));
			}
			if (!StringUtils.isEmpty(query.getTel())) {
				qps.add(new QueryParam("tel", query.getTel()));
			}
			if (query.getClientType() != null) {
				qps.add(new QueryParam("clientType", query.getClientType()));
			}
			if (query.getClientState() != null) {
				qps.add(new QueryParam("clientState", query.getClientState()));
			}
			if (query.getAuditInfo() != null) {
				qps.add(new QueryParam("auditInfo", query.getAuditInfo()));
			}
			if (query.getMediatorType() != null) {
				qps.add(new QueryParam("mediatorType", query.getMediatorType()));
			}
			
			if (query.getResgitTimeEnd() != null) {
				qps.add(new QueryParam("resgitTime", query.getResgitTimeEnd(),ParamCompareType.SmallEqual));
			}
			if (query.getResgitTimeStart() != null) {
				qps.add(new QueryParam("resgitTime", query.getResgitTimeStart(),ParamCompareType.LargeEqual));
			}
		}
		query.addOrder("auditInfo", false);
		return qps;
	}
	
	@Override
	public List<Client> findByAccount(List<String> phoneList)
			throws ErrorException {
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("PHONELISTPARAMS", phoneList);
		StringBuffer sbHql = new StringBuffer();
		sbHql.append(" from Client where 1=1  ");
		if(phoneList!=null&&phoneList.size()>0){
			sbHql.append(" and account in:PHONELISTPARAMS ");
		}
		return this.findHqlToM(sbHql.toString(), paramMap);
	}
	
	@Override
	public Map<String, Object> statisticsClientRes() throws ErrorException {
		
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		StringBuffer sql  =new StringBuffer();
		sql.append(" SELECT LOGIN_TYPE as type,");
		sql.append(" COUNT(*) as count");
		sql.append(" FROM cl_client");
		sql.append(" where CLIENT_TYPE <>'2'");
		sql.append(" GROUP BY LOGIN_TYPE");
		
		List<Map<String, Object>> rows = this.getJdbcTemplate().queryForList(sql.toString());
		resultMap.put("list", rows);
		
		StringBuffer totalclientsql  =new StringBuffer();
		totalclientsql.append(" SELECT COUNT(*)");
		totalclientsql.append(" FROM cl_client");
		totalclientsql.append(" where CLIENT_TYPE <>'2'");
		Integer totalclient = this.findSqlCounts(totalclientsql.toString(),null).intValue();
		resultMap.put("totalclient", totalclient);
		
		//获取总数sql
		StringBuffer totalSql  =new StringBuffer();
		totalSql.append(" SELECT COUNT(*) as total");
		totalSql.append(" FROM ch_consult_client");
		Integer total = this.findSqlCounts(totalSql.toString(),null).intValue();
		resultMap.put("total", total);
		
		return resultMap;
	}

	/**
	 * 查找当前组织之下的所有用户
	 * @param ClientGroupDivision 实体类的code
	 */
	@Override
	public List<Map<String, Object>> getClientGroupDivisionClient(String code) {
//		String name = "cl.ID ,cl.NICK_NAME,cl.ACCOUNT,cl.TEL,cl.PASSWD,cl.IDENTIFY_NAME,cl.IDENTIFY,cl.IDENTIFY_IMG_FILE1,cl.IDENTIFY_IMG_FILE2"
//				+"cl.IDENTIFY_IMG_FILE3,cl.HEAD_IMG_FILE,cl.THIRD_PARTY_ID,cl.THIRD_PARTY,cl.CLIENT_TYPE,cl.CLIENT_STATE,cl.AUDIT_INFO,cl.ADDRESS"
//				+"cl.IP,cl.DESCRIPTION,cl.SKILL,cl.MEDIATION_AGENCY_ID,cl.AGENCY_NAME,cl.NETEASE_CLIENT_ID,cl.LOGIN_TYPE,cl.SESSION_ID,"
//				+"cl.GENDER,cl.NATION,cl.BIRTH,cl.PROFESSION,cl.clientState";
		String select = super.getSelect(Client.class);
		String sql = "select "+select+" from cl_client Client left join client_group_division cgd on Client.CLIENT_STATE = cgd.ID where  cgd.CODE like ? and cgd.CODE > ? "; 
		
		List<Map<String, Object>> rows = this.getJdbcTemplate().queryForList(sql.toString(),new Object[]{code+"%",code});
		
		return rows;
	}
	
}