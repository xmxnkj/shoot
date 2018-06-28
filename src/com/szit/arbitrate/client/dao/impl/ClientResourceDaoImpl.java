package com.szit.arbitrate.client.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.client.dao.ClientResourceDao;
import com.szit.arbitrate.client.entity.ClientResource;
import com.szit.arbitrate.client.entity.query.ClientResourceQuery;

@Repository
public class ClientResourceDaoImpl extends BaseHibernateDaoImpl<ClientResource, ClientResourceQuery> 
	implements ClientResourceDao{
	
	@Override
	public List<QueryParam> buildQueryParams(ClientResourceQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if(StringUtils.isNotEmpty(query.getClientId())){
				qps.add(new QueryParam("clientId", query.getClientId()));
			}
			if(query.getResType()!=null){
				qps.add(new QueryParam("resType", query.getResType()));
			}
			if(StringUtils.isNotEmpty(query.getResuploadfileid())){
				qps.add(new QueryParam("resuploadfileid", query.getResuploadfileid()));
			}
		}
		return qps;
	}

}
