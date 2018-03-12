package com.szit.arbitrate.mediation.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.mediation.dao.MediationRecordDao;
import com.szit.arbitrate.mediation.entity.MediationRecord;
import com.szit.arbitrate.mediation.entity.query.MediationRecordQuery;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: MediationRecordDaoImpl
* @Description:调解笔录dao实现类
* @author Administrator
* @date 2017年3月23日 上午11:22:53
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class MediationRecordDaoImpl extends BaseHibernateDaoImpl<MediationRecord, MediationRecordQuery>
	implements MediationRecordDao{

	@Override
	public List<QueryParam> buildQueryParams(MediationRecordQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getCaseId())) {
				qps.add(new QueryParam("caseId", query.getCaseId()));
			}
			if(query.getRecordTypeEnum() != null){
				qps.add(new QueryParam("recordTypeEnum", query.getRecordTypeEnum()));
			}
		}
		return qps;
	}
	
}
