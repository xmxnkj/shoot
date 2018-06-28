package com.szit.arbitrate.client.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.client.dao.AuthorityGroupDao;
import com.szit.arbitrate.client.entity.AuthorityGroup;
import com.szit.arbitrate.client.entity.query.AuthorityGroupQuery;

@Repository
public class AuthorityGroupDaoImpl extends BaseHibernateDaoImpl<AuthorityGroup, AuthorityGroupQuery>
	implements AuthorityGroupDao{
	
	@Override
	public List<QueryParam> buildQueryParams(AuthorityGroupQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (query.getAuthorityGroupName()!=null) {
				qps.add(new QueryParam("authorityGroupName", query.getAuthorityGroupName()));
			}
			if (!StringUtils.isEmpty(query.getAuthorityDescription())) {
				qps.add(new QueryParam("authorityDescription", query.getAuthorityDescription()));
			}
		}
		return qps;
	}

}
