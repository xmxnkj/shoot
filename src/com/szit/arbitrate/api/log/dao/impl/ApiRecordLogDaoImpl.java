package com.szit.arbitrate.api.log.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.hibernate.HibernateDao;
import com.szit.arbitrate.api.log.dao.ApiRecordLogDao;
import com.szit.arbitrate.api.log.entity.ApiRecordLog;
import com.szit.arbitrate.api.log.entity.query.ApiRecordLogQuery;

/**
 * @ProjectName:xmszit-cowell-module-broadcast
 * @ClassName: ApiRecordLogDaoImpl
 * @Description:API接口日志数据库操作实现类
 * @author XUJC
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
@Repository
public class ApiRecordLogDaoImpl extends HibernateDao<ApiRecordLog, ApiRecordLogQuery> implements
		ApiRecordLogDao {

	@Override
	public List<QueryParam> buildQueryParams(ApiRecordLogQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
     if (!StringUtils.isEmpty(query.getModulecode())) {
	       qps.add(new QueryParam("modulecode", query.getModulecode()));
	     }
           if (!StringUtils.isEmpty(query.getBizcode())) {
	       qps.add(new QueryParam("bizcode", query.getBizcode()));
	     }
           if (!StringUtils.isEmpty(query.getBizmethod())) {
	       qps.add(new QueryParam("bizmethod", query.getBizmethod()));
	     }
           if (!StringUtils.isEmpty(query.getInbo())) {
	       qps.add(new QueryParam("inbo", query.getInbo()));
	     }
           if (!StringUtils.isEmpty(query.getOutbo())) {
	       qps.add(new QueryParam("outbo", query.getOutbo()));
	     }
           if (!StringUtils.isEmpty(query.getErrorcode())) {
	       qps.add(new QueryParam("errorcode", query.getErrorcode()));
	     }
           if (!StringUtils.isEmpty(query.getErrormessage())) {
	       qps.add(new QueryParam("errormessage", query.getErrormessage()));
	     }
              if (query.getCreatetime()!=null) {
	       qps.add(new QueryParam("createtime", query.getCreatetime()));
	    }
   
		}
		return qps;
	}


}