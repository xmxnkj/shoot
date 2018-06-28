package com.szit.arbitrate.client.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.client.dao.AuthorityGroupOperationDao;
import com.szit.arbitrate.client.entity.AuthorityGroupOperation;
import com.szit.arbitrate.client.entity.query.AuthorityGroupOperationQuery;

@Repository
public class AuthorityGroupOperationDaoImpl extends BaseHibernateDaoImpl<AuthorityGroupOperation, AuthorityGroupOperationQuery>
	implements AuthorityGroupOperationDao{
	
	@Override
	public List<QueryParam> buildQueryParams(AuthorityGroupOperationQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getAuthorityGroupId())) {
				qps.add(new QueryParam("authorityGroupId", query.getAuthorityGroupId()));
			}
			if (!StringUtils.isEmpty(query.getClientOperationId())) {
				qps.add(new QueryParam("clientOperationId", query.getClientOperationId()));
			}
			if (!StringUtils.isEmpty(query.getClientId())) {
				qps.add(new QueryParam("clientId", query.getClientId()));
			}
		}
		return qps;
	}

}
