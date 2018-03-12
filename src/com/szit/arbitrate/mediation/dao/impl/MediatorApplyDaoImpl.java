package com.szit.arbitrate.mediation.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.mediation.dao.MediatorApplyDao;
import com.szit.arbitrate.mediation.entity.MediatorApply;
import com.szit.arbitrate.mediation.entity.query.MediatorApplyQuery;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: MediatorApplyDaoImpl
* @Description:调解申请dao实现类
* @author Administrator
* @date 2017年3月23日 上午11:06:30
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class MediatorApplyDaoImpl extends BaseHibernateDaoImpl<MediatorApply, MediatorApplyQuery> implements MediatorApplyDao{
	@Override
	public List<QueryParam> buildQueryParams(MediatorApplyQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getApplyClientId())) {
				qps.add(new QueryParam("applyClientId", query.getApplyClientId()));
			}			
		}
		return qps;
	}
}
