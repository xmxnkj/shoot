package com.szit.arbitrate.mediation.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.mediation.dao.MediationResourceDao;
import com.szit.arbitrate.mediation.entity.MediationResource;
import com.szit.arbitrate.mediation.entity.query.MediationResourceQuery;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: MediationResourceDaoImpl
* @Description:调解资源dao实现类
* @author Administrator
* @date 2017年3月23日 上午11:03:50
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class MediationResourceDaoImpl extends BaseHibernateDaoImpl<MediationResource, MediationResourceQuery>
	implements MediationResourceDao{

	@Override
	public List<QueryParam> buildQueryParams(MediationResourceQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getCaseId())) {
				qps.add(new QueryParam("caseId", query.getCaseId()));
			}
			if (!StringUtils.isEmpty(query.getClientId())) {
				qps.add(new QueryParam("clientId", query.getClientId()));
			}
			if (!StringUtils.isEmpty(query.getResuploadfileid())) {
				qps.add(new QueryParam("resuploadfileid", query.getResuploadfileid()));
			}
			if(query.getResType() != null){
				qps.add(new QueryParam("resType",query.getResType()));
			}
		}
		return qps;
	}
	
}
