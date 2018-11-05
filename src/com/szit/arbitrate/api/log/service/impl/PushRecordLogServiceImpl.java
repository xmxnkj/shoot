package com.szit.arbitrate.api.log.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.arbitrate.api.log.dao.PushRecordLogDao;
import com.szit.arbitrate.api.log.entity.PushRecordLog;
import com.szit.arbitrate.api.log.entity.query.PushRecordLogQuery;
import com.szit.arbitrate.api.log.service.PushRecordLogService;
/**
 * @ProjectName:xmszit-cowell-module-broadcast
 * @ClassName: PushRecordLogServiceImpl
 * @Description:推送日志记录业务逻辑实现类
 * @author XUJC
 * @date
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
@Service
@Transactional
public class PushRecordLogServiceImpl extends AppBaseServiceImpl<PushRecordLog,PushRecordLogQuery>
		implements PushRecordLogService {
	
	@Autowired
	private PushRecordLogDao dao;
	public PushRecordLogDao getDao() {
		return dao;
	}
	public void setDao(PushRecordLogDao dao) {
		this.dao = dao;
	}
	
	
}