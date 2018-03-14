package com.szit.arbitrate.mediation.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.mediation.dao.MediationProtocolDao;
import com.szit.arbitrate.mediation.entity.MediationProtocol;
import com.szit.arbitrate.mediation.entity.query.MediationProtocolQuery;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: MediationProtocolDaoImpl
* @Description:调解协议dao实现类
* @author Administrator
* @date 2017年3月23日 上午11:20:00
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class MediationProtocolDaoImpl extends BaseHibernateDaoImpl<MediationProtocol, MediationProtocolQuery>
	implements MediationProtocolDao{

	@Override
	public List<QueryParam> buildQueryParams(MediationProtocolQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getCaseId())) {
				qps.add(new QueryParam("caseId", query.getCaseId()));
			}
			if (query.isFinalVersion()) {
				qps.add(new QueryParam("finalVersion", query.isFinalVersion()));
			}
		}
		return qps;
	}
	
	@Override
	public int getTempClientNotAcceptCount(String caseid)
			throws ErrorException {
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("CASEIDPARAM", caseid);
		StringBuffer sql  =new StringBuffer();
		sql.append(" SELECT COUNT(*) FROM cl_tempclient tc ");
		sql.append(" WHERE tc.CASE_ID =:CASEIDPARAM AND tc.SIGN != '1' and tc.PARTB=true");
		int count = this.findSqlCounts(sql.toString(), paramMap).intValue();
		return count;
	}
	
}
