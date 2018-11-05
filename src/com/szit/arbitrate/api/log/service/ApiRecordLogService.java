package com.szit.arbitrate.api.log.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.log.entity.ApiRecordLog;
import com.szit.arbitrate.api.log.entity.query.ApiRecordLogQuery;

/**
 * @ProjectName:xmszit-cowell-module-broadcast
 * @ClassName:ApiRecordLogService
 * @Description:API接口日志业务逻辑接口类
 * @author XUJC
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */

public interface ApiRecordLogService extends BaseService<ApiRecordLog, ApiRecordLogQuery> {

	/**
	 * 
	* @Title: findByErrorcode 
	* @Description: TODO
	* @param @param errorcode
	* @param @return
	* @param @throws BaseBizException
	* @param @throws BaseErrorException
	* @return String 
	* @throws
	 */
	public String findByErrorcodeToInBoJson(String errorcode)throws BizException,ErrorException; 
	
	/**
	 * 
	* @Title: findByErrorcodeToIn 
	* @Description: TODO
	* @param @param errorcode
	* @param @return
	* @param @throws BaseBizException
	* @param @throws BaseErrorException
	* @return ApiRecordLog 
	* @throws
	 */
	public ApiRecordLog findByErrorcode(String errorcode)throws BizException,ErrorException; 
	
	
	
	/**
	 * 
	* @Title: findLastError 
	* @Description: 获取最后一个错误
	* @param @return
	* @param @throws BaseBizException
	* @param @throws BaseErrorException
	* @return ApiRecordLog 
	* @throws
	 */
	public ApiRecordLog findLastError() throws BizException,ErrorException; 
	
	/**
	 * 
	* @Title: saveApiErrorLog 
	* @Description: TODO
	* @param @param inVm
	* @param @param log
	* @param @throws BaseBizException
	* @param @throws BaseErrorException
	* @return void 
	* @throws
	 */
	public String saveApiErrorLog(ApiInParamsVm inVm,String errormessage,Throwable aThrowable)throws BizException,ErrorException; 
	
	
	public String saveApiErrorLog(ApiInParamsVm inVm,String errormessage,Throwable aThrowable,String loginuserid)throws BizException,ErrorException;
	/**
	 * 
	* 方法描述:保存日志
	* 创建人: XUJC
	* 创建时间：2017年12月17日
	* @param inVm
	* @param outbBo
	* @param loginuserid
	* @return
	* @throws BizException
	* @throws ErrorException
	 */
	public String saveApiLog(ApiInParamsVm inVm,Object outbBo,String loginuserid)throws BizException,ErrorException;
	
	
	/**
	 * 
	* 方法描述:保存错误日志
	* 创建人: XUJC
	* 创建时间：2017年12月17日
	* @param inVm
	* @param outVm
	* @param errormessage
	* @param aThrowable
	* @return
	* @throws BizException
	* @throws ErrorException
	 */
	public String saveApiErrorLog(ApiInParamsVm inVm,ApiOutParamsVm outVm,String errormessage,Throwable aThrowable)throws BizException,ErrorException;
}