package com.szit.arbitrate.client.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.client.dao.NeteaseClientDao;
import com.szit.arbitrate.client.entity.NeteaseClient;
import com.szit.arbitrate.client.entity.query.NeteaseClientQuery;

/**
 * 
* @ClassName: NeteaseClientDaoImpl  
* @Description:   网易云信账号dao接口实现类
* @author   
* @date 2017年6月2日 上午10:03:40  
* @Copyright
* @versions:1.0 
*
 */
@Repository
public class NeteaseClientDaoImpl extends BaseHibernateDaoImpl<NeteaseClient, NeteaseClientQuery> implements NeteaseClientDao{

	@Override
	public List<QueryParam> buildQueryParams(NeteaseClientQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getClientId())) {
				qps.add(new QueryParam("clientId", query.getClientId()));
			}
			if (!StringUtils.isEmpty(query.getAccid())) {
				qps.add(new QueryParam("accid", query.getAccid()));
			}
			if (!StringUtils.isEmpty(query.getName())) {
				qps.add(new QueryParam("name", query.getName()));
			}
			if (!StringUtils.isEmpty(query.getToken())) {
				qps.add(new QueryParam("token", query.getToken()));
			}
		}
		return qps;
	}
	
}
