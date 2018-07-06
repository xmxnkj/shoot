package com.szit.arbitrate.mediation.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.springframework.stereotype.Repository;

import com.admin.jdbc.QueryInfo;
import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.mediation.dao.MediationAgencyDao;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.entity.enumvo.AgencyTypeEnum;
import com.szit.arbitrate.mediation.entity.query.MediationAgencyQuery;

/**
 * 
* @ProjectName:
* @ClassName: MediationAgencyDaoImpl
* @Description:机构dao实现类
* @author Administrator
* @date 2017年3月23日 上午10:59:40
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class MediationAgencyDaoImpl extends BaseHibernateDaoImpl<MediationAgency, MediationAgencyQuery> implements MediationAgencyDao{

	@Override
	public List<QueryParam> buildQueryParams(MediationAgencyQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (query.getAgencyName()!=null) {
				qps.add(new QueryParam("agencyName", query.getAgencyName(), ParamCompareType.Like));
			}
			if (query.getAddress()!=null) {
				qps.add(new QueryParam("address", query.getAddress(), ParamCompareType.Like));
			}
			if (query.getAgencyType()!=null) {
				qps.add(new QueryParam("agencyType", query.getAgencyType()));
			}
		}
		return qps;
	}
	
	@Override
	public List<MediationAgency> searchMediationAgencyList(String agencytype,String casetype, 
			String agencyname) throws ErrorException {
		
		
		StringBuffer sql  =new StringBuffer();
		sql.append(" SELECT * FROM me_mediation_agency ma");
		sql.append(" WHERE ma.OPEN_OR_NOT =TRUE");
		if(StringUtils.isNotEmpty(casetype)){
			sql.append(" and ma.agency_Type='").append(AgencyTypeEnum.valueOf(casetype).ordinal()).append("'");
		}
		if(StringUtils.isNotEmpty(agencyname)){
			sql.append(" and ma.AGENCY_NAME LIKE '%").append(agencyname).append("%'");
		}
		sql.append(" order by ma.agency_Type");
		List<MediationAgency> list = (List<MediationAgency>) this.findSql(sql.toString(), MediationAgency.class);
		//List<MediationAgency> list = this.getJdbcTemplate().queryForList(sql.toString(), MediationAgency.class);
		return list;
	}
	
	
	/**
	 * 通过员id获取对应的机构
	 * @param mediationId
	 * @return
	 * @throws Exception 
	 */
	@Override
	public MediationAgency getMediationAgency(String mediationId) throws Exception {
		QueryInfo qi = new QueryInfo();
		qi.getSearch().put("managerClientId", mediationId);
		//只查这些字段
		qi.setMeSelect(" MediationAgency.Id,MediationAgency.agency_type,MediationAgency.agency_name,"
				+ "MediationAgency.manager_client_id,MediationAgency.open_or_not");
		MediationAgency mediation =  (MediationAgency) this.getEntity(qi, MediationAgency.class);
		return mediation;
	}
	
}
