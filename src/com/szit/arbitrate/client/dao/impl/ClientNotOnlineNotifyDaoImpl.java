package com.szit.arbitrate.client.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.client.dao.ClientNotOnlineNotifyDao;
import com.szit.arbitrate.client.entity.ClientNotOnlineNotify;
import com.szit.arbitrate.client.entity.query.ClientNotOnlineNotifyQuery;

@Repository
public class ClientNotOnlineNotifyDaoImpl extends BaseHibernateDaoImpl<ClientNotOnlineNotify, ClientNotOnlineNotifyQuery> 
	implements ClientNotOnlineNotifyDao{
	
	@Override
	public List<QueryParam> buildQueryParams(ClientNotOnlineNotifyQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getClientId())) {
				qps.add(new QueryParam("clientId", query.getClientId()));
			}
			if (!StringUtils.isEmpty(query.getClientName())) {
				qps.add(new QueryParam("clientName", query.getClientName()));
			}
			if (query.getGatherTime() != null) {
				qps.add(new QueryParam("gatherTime", query.getGatherTime()));
			}
			if (!StringUtils.isEmpty(query.getCaseId())) {
				qps.add(new QueryParam("caseId", query.getCaseId()));
			}
		}
		return qps;
	}

}
