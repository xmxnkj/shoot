package com.szit.arbitrate.mediation.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.mediation.dao.LegalDocDetailDao;
import com.szit.arbitrate.mediation.entity.LegalDocDetail;
import com.szit.arbitrate.mediation.entity.query.LegalDocDetailQuery;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: LegalDocMomentsDaoImpl
* @Description:法规文档详情dao实现类
* @author Administrator
* @date 2017年3月28日 下午4:02:36
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class LegalDocDetailDaoImpl extends BaseHibernateDaoImpl<LegalDocDetail, LegalDocDetailQuery>
	implements LegalDocDetailDao{

	@Override
	public List<QueryParam> buildQueryParams(LegalDocDetailQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getLegalDocId())) {
				qps.add(new QueryParam("legalDocId", query.getLegalDocId()));
			}
		}
		return qps;
	}
	
}
