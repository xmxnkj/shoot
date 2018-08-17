package com.szit.arbitrate.chat.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.chat.dao.ChatResourceDao;
import com.szit.arbitrate.chat.entity.ChatResource;
import com.szit.arbitrate.chat.entity.query.ChatResourceQuery;

/**
 * 
* @ProjectName:
* @ClassName: ChatResourceDaoImpl
* @Description:
* @author Administrator
* @date 2017年5月31日 上午11:14:56
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class ChatResourceDaoImpl extends BaseHibernateDaoImpl<ChatResource, ChatResourceQuery> implements ChatResourceDao{

	@Override
	public List<QueryParam> buildQueryParams(ChatResourceQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getClientId())) {
				qps.add(new QueryParam("clientId", query.getClientId()));
			}
			if (!StringUtils.isEmpty(query.getResuploadfilename())) {
				qps.add(new QueryParam("resuploadfilename", query.getResuploadfilename()));
			}
			if(query.getResType() != null){
				qps.add(new QueryParam("resType", query.getResType()));
			}
		}
		return qps;
	}
	
}
