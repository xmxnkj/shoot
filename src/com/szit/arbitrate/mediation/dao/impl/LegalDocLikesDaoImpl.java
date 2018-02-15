package com.szit.arbitrate.mediation.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.mediation.dao.LegalDocLikesDao;
import com.szit.arbitrate.mediation.entity.LegalDocLikes;
import com.szit.arbitrate.mediation.entity.query.LegalDocLikesQuery;

@Repository
public class LegalDocLikesDaoImpl extends BaseHibernateDaoImpl<LegalDocLikes, LegalDocLikesQuery> 
	implements LegalDocLikesDao{
	
	@Override
	public List<QueryParam> buildQueryParams(LegalDocLikesQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getLegalDocId())) {
				qps.add(new QueryParam("legalDocId", query.getLegalDocId()));
			}
			if (!StringUtils.isEmpty(query.getLikeClientId())) {
				qps.add(new QueryParam("likeClientId", query.getLikeClientId()));
			}
		}
		return qps;
	}

}
