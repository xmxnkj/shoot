package com.szit.arbitrate.mediation.service;

import java.util.Map;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.mediation.entity.MediationRecord;
import com.szit.arbitrate.mediation.entity.query.MediationRecordQuery;

/**
 * 
* @ProjectName:
* @ClassName: MediationRecordService
* @Description:笔录业务接口类
* @author Administrator
* @date 2017年3月23日 上午11:34:05
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface MediationRecordService extends AppBaseService<MediationRecord, MediationRecordQuery>{
	/**
	 * 添加记录
	 * @param caseId
	 * @param recordContent
	 * @param mediatorId	员ID
	 * @param involvedPerson	当事人
	 * @param joinPerson	参与人
	 * @param address		地点
	 * @throws BizException
	 * @throws ErrorException
	 */
	public MediationRecord saveMediationRecord(String caseId,
									String recordContent,
									String involvedPerson,
									String time,
									String address, 
									String joinPerson,
									String recordtype) throws BizException, ErrorException;
	
	/**
	 * 
	* @Title: modifyMediationRecord 
	* @Description: 修改笔录
	* @param @param recordid
	* @param @param recordContent
	* @param @param involvedPerson
	* @param @param time
	* @param @param address
	* @param @param recordstate
	* @param @param recordtype
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void modifyMediationRecord(String recordid,String recordContent,
									String involvedPerson,
									String time,
									String address, 
									String joinPerson,
									String recordtype)throws BizException, ErrorException;
	
	/**
	 * 获取笔录列表
	 * @param caseId
	 * @throws BizException
	 * @throws ErrorException
	 */
	public Map<String,Object> getMediationRecordList(String caseId,String recordtype,int pageNum) throws BizException,ErrorException;
}
