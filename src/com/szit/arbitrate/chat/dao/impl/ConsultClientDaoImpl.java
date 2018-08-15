package com.szit.arbitrate.chat.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.chat.dao.ConsultClientDao;
import com.szit.arbitrate.chat.entity.ConsultClient;
import com.szit.arbitrate.chat.entity.query.ConsultClientQuery;

/**
 * 
* @ClassName: ConsultClientDaoImpl  
* @Description: 用户咨询dao实现类  
* @author   
* @date 2017年6月20日 下午4:52:55  
* @Copyright
* @versions:1.0 
*
 */
@Repository
public class ConsultClientDaoImpl extends BaseHibernateDaoImpl<ConsultClient, ConsultClientQuery> implements ConsultClientDao{

	@Override
	public List<QueryParam> buildQueryParams(ConsultClientQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getClientId())) {
				qps.add(new QueryParam("clientId", query.getClientId()));
			}
			if (query.getConsultTime() != null) {
				qps.add(new QueryParam("consultTime", query.getConsultTime()));
			}
		}
		return qps;
	}
	
}
