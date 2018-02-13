package com.szit.arbitrate.mediation.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.mediation.dao.LegalDocDao;
import com.szit.arbitrate.mediation.entity.LegalDoc;
import com.szit.arbitrate.mediation.entity.query.LegalDocQuery;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: LegalDocDaoImpl
* @Description:法律文档dao接口实现类
* @author Administrator
* @date 2017年3月23日 下午1:49:10
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class LegalDocDaoImpl extends BaseHibernateDaoImpl<LegalDoc, LegalDocQuery> implements LegalDocDao{
	
	@Override
	public List<QueryParam> buildQueryParams(LegalDocQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (query.getDocType()!=null) {
				if(query.isClassic()){
					qps.add(new QueryParam("docType", query.getDocType()));
				}else{
					qps.add(new QueryParam("docType", query.getDocType(), ParamCompareType.NotEqual));
				}
			}
			if (query.getClassicCase()!=null){
				qps.add(new QueryParam("classicCase", query.getClassicCase()));
			}
			if (!StringUtils.isEmpty(query.getMediatorClientName())) {
				qps.add(new QueryParam("mediatorClientName", query.getMediatorClientName()));
			}
			if (!StringUtils.isEmpty(query.getMediatorClientId())) {
				qps.add(new QueryParam("mediatorClientId", query.getMediatorClientId()));
			}
			if (!StringUtils.isEmpty(query.getTitle())) {
				qps.add(new QueryParam("title", query.getTitle(),ParamCompareType.Like));
			}
			if (!StringUtils.isEmpty(query.getPublishUnit())) {
				qps.add(new QueryParam("publishUnit", query.getPublishUnit()));
			}			
			if(query.getPublishTime()!=null){
				qps.add(new QueryParam("publishTime", query.getPublishTime(),ParamCompareType.LargeEqual));
			}
			if(query.isDownload()){
				qps.add(new QueryParam("download", query.isDownload()));
			}
			if(query.isDisplay()){
				qps.add(new QueryParam("display", query.isDisplay()));
			}
		}
		query.addOrder("orderdisplay", false);
		return qps;
	}
}