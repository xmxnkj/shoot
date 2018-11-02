package com.szit.arbitrate.api.log.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.hibernate.HibernateDao;
import com.szit.arbitrate.api.log.dao.PushRecordLogDao;
import com.szit.arbitrate.api.log.entity.PushRecordLog;
import com.szit.arbitrate.api.log.entity.query.PushRecordLogQuery;

/**
 * @ProjectName:xmszit-cowell-module-broadcast
 * @ClassName: PushRecordLogDaoImpl
 * @Description:推送日志记录数据库操作实现类
 * @author XUJC
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
@Repository
public class PushRecordLogDaoImpl extends HibernateDao<PushRecordLog, PushRecordLogQuery> implements
		PushRecordLogDao {

	@Override
	public List<QueryParam> buildQueryParams(PushRecordLogQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
     if (!StringUtils.isEmpty(query.getPushmode())) {
	       qps.add(new QueryParam("pushmode", query.getPushmode()));
	     }
           if (!StringUtils.isEmpty(query.getPushkeycode())) {
	       qps.add(new QueryParam("pushkeycode", query.getPushkeycode()));
	     }
           if (!StringUtils.isEmpty(query.getPushcontent())) {
	       qps.add(new QueryParam("pushcontent", query.getPushcontent()));
	     }
              if (query.getApnscount()!=null) {
	       qps.add(new QueryParam("apnscount", query.getApnscount()));
	    }
           if (query.getPushdatetime()!=null) {
	       qps.add(new QueryParam("pushdatetime", query.getPushdatetime()));
	    }
   
		}
		return qps;
	}


}