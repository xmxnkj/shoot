package com.szit.arbitrate.client.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.client.dao.TempClientDao;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.query.TempClientQuery;

/**
 * 
* @ProjectName:
* @ClassName: TempClientDaoImpl
* @Description:虚拟用户dao实现类
* @author Administrator
* @date 2017年3月24日 下午3:26:34
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class TempClientDaoImpl extends BaseHibernateDaoImpl<TempClient, TempClientQuery> implements TempClientDao{

	@Override
	public List<QueryParam> buildQueryParams(TempClientQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (query.getCaseId()!=null) {
				qps.add(new QueryParam("caseId", query.getCaseId()));
			}
			if (query.getClientId()!=null) {
				qps.add(new QueryParam("clientId", query.getClientId()));
			}
			if (query.getIdentifyName()!=null) {
				qps.add(new QueryParam("identifyName", query.getIdentifyName()));
			}
			if (query.getIdentify()!=null) {
				qps.add(new QueryParam("identify", query.getIdentify()));
			}
			if (query.getTel()!=null) {
				qps.add(new QueryParam("tel", query.getTel()));
			}
			if (query.getSign()!=null) {
				qps.add(new QueryParam("sign", query.getSign()));
			}
			if(query.isPartB()){
				qps.add(new QueryParam("partB", query.isPartB()));
			}
		}
		return qps;
	}
	
	@Override
	public boolean isCaseClientAllCalled(String caseid) throws ErrorException {
		
		Map<String,Object> paramMap = Maps.newHashMap();
		paramMap.put("CASEIDPARAMS", caseid);
		StringBuffer sbsql = new StringBuffer();
		sbsql.append(" SELECT COUNT(*) FROM cl_tempclient ");
		sbsql.append(" WHERE CASE_ID =:CASEIDPARAMS");
		sbsql.append(" AND CLIENT_ID IS NULL");
		Integer count = this.findSqlCounts(sbsql.toString(), paramMap).intValue();
		if(count > 0){//说明有未召集的用户
			return false;
		}
		return true;
	}
	
}
