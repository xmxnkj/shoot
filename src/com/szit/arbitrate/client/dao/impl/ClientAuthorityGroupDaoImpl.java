package com.szit.arbitrate.client.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.client.dao.ClientAuthorityGroupDao;
import com.szit.arbitrate.client.entity.ClientAuthorityGroup;
import com.szit.arbitrate.client.entity.query.ClientAuthorityGroupQuery;

@Repository
public class ClientAuthorityGroupDaoImpl extends BaseHibernateDaoImpl<ClientAuthorityGroup, ClientAuthorityGroupQuery>
	implements ClientAuthorityGroupDao{
	
	@Override
	public List<QueryParam> buildQueryParams(ClientAuthorityGroupQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getClientId())) {
				qps.add(new QueryParam("clientId", query.getClientId()));
			}
			if (!StringUtils.isEmpty(query.getClientName())) {
				qps.add(new QueryParam("clientName", query.getClientName()));
			}
			if (!StringUtils.isEmpty(query.getAuthorityGroupId())) {
				qps.add(new QueryParam("authorityGroupId", query.getAuthorityGroupId()));
			}
			if (query.getAuthorityGroupName()!=null) {
				qps.add(new QueryParam("authorityGroupName", query.getAuthorityGroupName()));
			}
		}
		return qps;
	}

}
