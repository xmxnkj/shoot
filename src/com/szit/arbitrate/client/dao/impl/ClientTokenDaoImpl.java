package com.szit.arbitrate.client.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.client.dao.ClientTokenDao;
import com.szit.arbitrate.client.entity.ClientToken;
import com.szit.arbitrate.client.entity.query.ClientTokenQuery;

/**
 * 
* @ClassName: ClientTokenDaoImpl  
* @Description: 用户登录的设备信息dao实现类  
* @author   
* @date 2017年6月6日 上午10:21:20  
* @Copyright
* @versions:1.0 
*
 */
@Repository
public class ClientTokenDaoImpl extends BaseHibernateDaoImpl<ClientToken, ClientTokenQuery> implements ClientTokenDao{

	@Override
	public List<QueryParam> buildQueryParams(ClientTokenQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getClientId())) {
				qps.add(new QueryParam("clientId", query.getClientId()));
			}
			if (!StringUtils.isEmpty(query.getSessionId())) {
				qps.add(new QueryParam("sessionId", query.getSessionId()));
			}
			if (!StringUtils.isEmpty(query.getUuid())) {
				qps.add(new QueryParam("uuid", query.getUuid()));
			}
			if (query.getTerminalType() != null) {
				qps.add(new QueryParam("terminalType", query.getTerminalType()));
			}
		}
		return qps;
	}
	
}
