package com.szit.arbitrate.client.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.client.dao.TerminalDao;
import com.szit.arbitrate.client.entity.Terminal;
import com.szit.arbitrate.client.entity.query.TerminalQuery;

@Repository
public class TerminalDaoImpl extends BaseHibernateDaoImpl<Terminal, TerminalQuery> implements TerminalDao{
	
	@Override
	public List<QueryParam> buildQueryParams(TerminalQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (query.getTerminalType()!=null) {
				qps.add(new QueryParam("terminalType", query.getTerminalType()));
			}
			if (!StringUtils.isEmpty(query.getClientId())) {
				qps.add(new QueryParam("clientId", query.getClientId()));
			}
			if (!StringUtils.isEmpty(query.getTerminalCode())) {
				qps.add(new QueryParam("terminalCode", query.getTerminalCode()));
			}
			if (!StringUtils.isEmpty(query.getSpec())) {
				qps.add(new QueryParam("spec", query.getSpec()));
			}
		}
		return qps;
	}

}
