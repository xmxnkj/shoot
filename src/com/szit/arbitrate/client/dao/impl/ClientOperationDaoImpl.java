package com.szit.arbitrate.client.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.client.dao.ClientOperationDao;
import com.szit.arbitrate.client.entity.ClientOperation;
import com.szit.arbitrate.client.entity.query.ClientOperationQuery;

@Repository
public class ClientOperationDaoImpl extends BaseHibernateDaoImpl<ClientOperation, ClientOperationQuery>
	implements ClientOperationDao{
	
	public List<QueryParam> buildQueryParams(ClientOperationQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if(StringUtils.isNotEmpty(query.getOperationName())){
				qps.add(new QueryParam("operationName", query.getOperationName()));
			}
		}
		return qps;
	}
	

}
