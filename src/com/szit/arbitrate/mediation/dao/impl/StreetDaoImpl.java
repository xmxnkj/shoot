package com.szit.arbitrate.mediation.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.mediation.dao.StreetDao;
import com.szit.arbitrate.mediation.entity.Street;
import com.szit.arbitrate.mediation.entity.query.StreetQuery;

@Repository
public class StreetDaoImpl extends BaseHibernateDaoImpl<Street, StreetQuery>
implements StreetDao{
	

	@Override
	public List<QueryParam> buildQueryParams(StreetQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getStreetNname())) {
				qps.add(new QueryParam("streetNname", query.getStreetNname()));
			}			
		}
		return qps;
	}
}
