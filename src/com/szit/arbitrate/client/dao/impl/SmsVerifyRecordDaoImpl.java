package com.szit.arbitrate.client.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.client.dao.SmsVerifyRecordDao;
import com.szit.arbitrate.client.entity.SmsVerifyRecord;
import com.szit.arbitrate.client.entity.query.SmsVerifyRecordQuery;

@Repository
public class SmsVerifyRecordDaoImpl extends BaseHibernateDaoImpl<SmsVerifyRecord, SmsVerifyRecordQuery>
	implements SmsVerifyRecordDao{
	
	@Override
	public List<QueryParam> buildQueryParams(SmsVerifyRecordQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (query.getBizmodel()!=null) {
				qps.add(new QueryParam("bizmodel", query.getBizmodel()));
			}
			if (!StringUtils.isEmpty(query.getRecphone())) {
				qps.add(new QueryParam("recphone", query.getRecphone()));
			}
			if (!StringUtils.isEmpty(query.getSendcontent())) {
				qps.add(new QueryParam("sendcontent", query.getSendcontent()));
			}
			if (!StringUtils.isEmpty(query.getSmscheckcode())) {
				qps.add(new QueryParam("smscheckcode", query.getSmscheckcode()));
			}
			if (!StringUtils.isEmpty(query.getSmsapiresult())) {
				qps.add(new QueryParam("smsapiresult", query.getSmsapiresult()));
			}
			if (query.getSendstate()!=null) {
				qps.add(new QueryParam("sendstate", query.getSendstate()));
			}
			if (query.getSenddatetiem()!=null) {
				qps.add(new QueryParam("senddatetiem", query.getSenddatetiem()));
			}

		}
		return qps;
	}

}
