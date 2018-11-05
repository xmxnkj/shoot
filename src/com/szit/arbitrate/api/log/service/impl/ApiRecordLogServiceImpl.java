package com.szit.arbitrate.api.log.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.hsit.common.utils.JsonFormatUtil;
import com.hsit.common.utils.JsonMapper;
import com.szit.arbitrate.api.common.utils.RandomUtils;
import com.szit.arbitrate.api.common.utils.StackTraceUtils;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.log.dao.ApiRecordLogDao;
import com.szit.arbitrate.api.log.entity.ApiRecordLog;
import com.szit.arbitrate.api.log.entity.query.ApiRecordLogQuery;
import com.szit.arbitrate.api.log.service.ApiRecordLogService;
/**
 * @ProjectName:xmszit-cowell-module-broadcast
 * @ClassName: ApiRecordLogServiceImpl
 * @Description:API接口日志业务逻辑实现类
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
public class ApiRecordLogServiceImpl extends BaseServiceImpl<ApiRecordLog,ApiRecordLogQuery>
		implements ApiRecordLogService {
	
	@Autowired
	private ApiRecordLogDao dao;
	public ApiRecordLogDao getDao() {
		return dao;
	}
	public void setDao(ApiRecordLogDao dao) {
		this.dao = dao;
	}
	
	@Override
	public String saveApiErrorLog(ApiInParamsVm inVm,String errormessage,Throwable aThrowable)
			throws BizException, ErrorException {
		String bizcode = "unknown";
		if(inVm!=null){
			bizcode = inVm.getBizcode();
		}
		try {
			String errorcode = RandomUtils.getRandomString(bizcode,6);
			ApiRecordLog apirecordlog = new ApiRecordLog();
			apirecordlog.setModulecode(inVm.getModule());
			apirecordlog.setBizcode(inVm.getBizcode());
			apirecordlog.setBizmethod(inVm.getBizmethod());
			apirecordlog.setErrorcode(errorcode);
			apirecordlog.setErrormessage(errormessage);
			String formatinbo = JsonFormatUtil.formatJson(inVm.getDatasource());
			apirecordlog.setInbo(inVm.getDatasource());
			apirecordlog.setFormatinbo(formatinbo);
			String errorstack = StackTraceUtils.getStackTrace(aThrowable);
			apirecordlog.setErrorstack(errorstack);
			apirecordlog.setCreatetime(new Date());
			apirecordlog.setErrorflag("Y");
			this.save(apirecordlog);
			return errorcode;
		} catch (Exception e) {
			throw new ErrorException(e);
		}

	}
	
	@Override
	public String saveApiErrorLog(ApiInParamsVm inVm,String errormessage,Throwable aThrowable,String loginuserid)
			throws BizException, ErrorException {
		String bizcode = "unknown";
		if(inVm!=null){
			bizcode = inVm.getBizcode();
		}
		try {
			String errorcode = RandomUtils.getRandomString(bizcode,6);
			ApiRecordLog apirecordlog = new ApiRecordLog();
			apirecordlog.setModulecode(inVm.getModule());
			apirecordlog.setBizcode(inVm.getBizcode());
			apirecordlog.setBizmethod(inVm.getBizmethod());
			apirecordlog.setErrorcode(errorcode);
			apirecordlog.setErrormessage(errormessage);
			String formatinbo = JsonFormatUtil.formatJson(inVm.getDatasource());
			apirecordlog.setInbo(inVm.getDatasource());
			apirecordlog.setFormatinbo(formatinbo);
			String errorstack = StackTraceUtils.getStackTrace(aThrowable);
			apirecordlog.setErrorstack(errorstack);
			apirecordlog.setCreatetime(new Date());
			apirecordlog.setErrorflag("Y");
			apirecordlog.setLoginuserid(loginuserid);
			this.save(apirecordlog);
			return errorcode;
		} catch (Exception e) {
			throw new ErrorException(e);
		}

	}
	
	@Override
	public String saveApiErrorLog(ApiInParamsVm inVm,ApiOutParamsVm outVm,String errormessage,Throwable aThrowable)
			throws BizException, ErrorException {
		String errorcode = RandomUtils.getRandomString(inVm.getBizcode(),6);
		ApiRecordLog apirecordlog = new ApiRecordLog();
		apirecordlog.setModulecode(inVm.getModule());
		apirecordlog.setBizcode(inVm.getBizcode());
		apirecordlog.setBizmethod(inVm.getBizmethod());
		apirecordlog.setErrorcode(errorcode);
		apirecordlog.setErrormessage(errormessage);
		String formatinbo = JsonFormatUtil.formatJson(inVm.getDatasource());
		apirecordlog.setInbo(inVm.getDatasource());
		apirecordlog.setFormatinbo(formatinbo);
		String errorstack = StackTraceUtils.getStackTrace(aThrowable);
		apirecordlog.setErrorstack(errorstack);
		apirecordlog.setCreatetime(new Date());
		apirecordlog.setErrorflag("Y");
		this.save(apirecordlog);
		return errorcode;
	}
	
	@Override
	public String saveApiLog(ApiInParamsVm inVm, Object outbBo,String loginuserid)
			throws BizException, ErrorException {
		String apicode = RandomUtils.getRandomString(inVm.getBizcode(),6);
		ApiRecordLog apirecordlog = new ApiRecordLog();
		apirecordlog.setModulecode(inVm.getModule());
		apirecordlog.setBizcode(inVm.getBizcode());
		apirecordlog.setBizmethod(inVm.getBizmethod());
		apirecordlog.setLoginuserid(loginuserid);
		apirecordlog.setErrorcode(apicode);
		String formatinbo = JsonFormatUtil.formatJson(inVm.getDatasource());
		apirecordlog.setInbo(inVm.getDatasource());
		apirecordlog.setFormatinbo(formatinbo);
		apirecordlog.setCreatetime(new Date());
		apirecordlog.setErrorflag("N");
		apirecordlog.setOutbo(JsonFormatUtil.formatJson(JsonMapper.getInstance().toJson(outbBo)));
		this.save(apirecordlog);
		return apicode;
	}
	
	@Override
	public String findByErrorcodeToInBoJson(String errorcode)
			throws BizException, ErrorException {
		return findByErrorcode(errorcode).getInbo();
	}
	@Override
	public ApiRecordLog findByErrorcode(String errorcode)
			throws BizException, ErrorException {
		ApiRecordLogQuery query  = new ApiRecordLogQuery();
		query.setErrorcode(errorcode);
		return this.getEntity(query);
	}
	@Override
	public ApiRecordLog findLastError() throws BizException,
			ErrorException {
		ApiRecordLogQuery query  = new ApiRecordLogQuery();
		query.addOrder("createtime", true);
		List<ApiRecordLog> list =  this.getEntities(query);
		return list.get(0);
	}

	
	
}