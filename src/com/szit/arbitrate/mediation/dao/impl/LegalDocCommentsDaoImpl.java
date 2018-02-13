package com.szit.arbitrate.mediation.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.mediation.dao.LegalDocCommentsDao;
import com.szit.arbitrate.mediation.entity.LegalDocComments;
import com.szit.arbitrate.mediation.entity.query.LegalDocCommentsQuery;

@Repository
public class LegalDocCommentsDaoImpl extends BaseHibernateDaoImpl<LegalDocComments, LegalDocCommentsQuery> 
	implements LegalDocCommentsDao{
	
	@Override
	public List<QueryParam> buildQueryParams(LegalDocCommentsQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getLegalDocId())) {
				qps.add(new QueryParam("legalDocId", query.getLegalDocId()));
			}
			if (!StringUtils.isEmpty(query.getCommentsClientId())) {
				qps.add(new QueryParam("commentsClientId", query.getCommentsClientId()));
			}
			if(query.isAudit()){
				qps.add(new QueryParam("audit", query.isAudit()));
			}
		}
		return qps;
	}

}
