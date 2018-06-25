package com.szit.arbitrate.client.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.client.dao.ClientThirdpartyAccountDao;
import com.szit.arbitrate.client.entity.ClientThirdpartyAccount;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;
import com.szit.arbitrate.client.entity.query.ClientThirdpartyAccountQuery;

@Repository
public class ClientThirdpartyAccountDaoImpl extends BaseHibernateDaoImpl<ClientThirdpartyAccount, ClientThirdpartyAccountQuery>
	implements ClientThirdpartyAccountDao{
	
	@Override
	public List<QueryParam> buildQueryParams(ClientThirdpartyAccountQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getClientid())) {
				qps.add(new QueryParam("clientid", query.getClientid()));
			}
			if (!StringUtils.isEmpty(query.getThirdprttyid())) {
				qps.add(new QueryParam("thirdprttyid", query.getThirdprttyid()));
			}
			if (query.getThirdpartytype()!=null) {
				qps.add(new QueryParam("thirdpartytype", query.getThirdpartytype()));
			}
			if (!StringUtils.isEmpty(query.getThirdpartynickname())) {
				qps.add(new QueryParam("thirdpartynickname", query.getThirdpartynickname()));
			}
			if (query.getBindingdatetime()!=null) {
				qps.add(new QueryParam("bindingdatetime", query.getBindingdatetime()));
			}

		}
		return qps;
	}

	@Override
	public Boolean isBindingThirdparty(String clientid) throws ErrorException {
		Map<String,Object> paramMap = Maps.newHashMap();
		List<ThirdPartyEnum> list = Lists.newArrayList();
		list.add(ThirdPartyEnum.Phone);
		list.add(ThirdPartyEnum.Account);
		paramMap.put("THIRDPARTYTYPEPARAM",list );
		paramMap.put("CLIENTIDPARAM",clientid );
		StringBuffer sbHql = new StringBuffer();
		sbHql.append(" select count(*) from ClientThirdpartyAccount where thirdpartytype not in:THIRDPARTYTYPEPARAM");
		sbHql.append(" and clientid=:CLIENTIDPARAM");
		Long counts = this.findHqlCounts(sbHql.toString(), paramMap);
		if(counts>0){
			return true;
		}else{
			return false;
		}
	}

}
