package com.szit.arbitrate.mediation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.service.AppBaseServiceImpl;
import com.hsit.common.utils.DateUtils;
import com.hsit.common.utils.PagingBean;
import com.szit.arbitrate.mediation.dao.MediationRecordDao;
import com.szit.arbitrate.mediation.entity.MediationRecord;
import com.szit.arbitrate.mediation.entity.enumvo.RecordStateEnum;
import com.szit.arbitrate.mediation.entity.enumvo.RecordTypeEnum;
import com.szit.arbitrate.mediation.entity.query.MediationRecordQuery;
import com.szit.arbitrate.mediation.service.MediationRecordService;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: MediationRecordServiceImpl
* @Description:调解笔录业务接口实现类
* @author Administrator
* @date 2017年3月23日 上午11:34:52
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class MediationRecordServiceImpl extends AppBaseServiceImpl<MediationRecord, MediationRecordQuery>
	implements MediationRecordService{
	
	@Autowired
	private MediationRecordDao dao;

	public MediationRecordDao getDao() {
		return dao;
	}

	public void setDao(MediationRecordDao dao) {
		this.dao = dao;
	}


	@Override
	public Map<String,Object> getMediationRecordList(String caseId,String recordtype,int pageNum) throws BizException, ErrorException {
		Map<String,Object> resultMap = new HashMap<String,Object>(); 
		
		if(StringUtils.isEmpty(caseId) || StringUtils.isEmpty(recordtype)){
			throw new BizException("参数不能空!");
		}
		
		Paging paging = new Paging();
		paging.setPage(pageNum);
		paging.setPageSize(PagingBean.DEFAULT_PAGE_SIZE);
		
		MediationRecordQuery mediationRecordQuery = new MediationRecordQuery();
		mediationRecordQuery.setCaseId(caseId);
		mediationRecordQuery.setPaging(paging);
		mediationRecordQuery.setRecordTypeEnum(RecordTypeEnum.valueOf(recordtype));
		mediationRecordQuery.addOrder("recordTime", true);
		
		List<MediationRecord> mediationRecordList = getEntities(mediationRecordQuery);
		
		resultMap.put("total", paging.getRecordCount());
		resultMap.put("pageCount", paging.getPageCount());
		resultMap.put("rows", mediationRecordList);
		
		return resultMap;
	}
	
	@Override
	public MediationRecord saveMediationRecord(String caseId,String recordContent,String involvedPerson,
			String time,String address,String joinPerson,String recordtype)
									throws BizException, ErrorException{
		if(StringUtils.isEmpty(caseId) || StringUtils.isEmpty(recordContent) || StringUtils.isEmpty(involvedPerson)
				||StringUtils.isEmpty(time) || StringUtils.isEmpty(address)||StringUtils.isEmpty(recordtype)){
			throw new BizException("参数不能空!");
		}
		MediationRecord mediationRecord = new MediationRecord();
		
		mediationRecord.setCaseId(caseId);
		mediationRecord.setRecordContent(recordContent);
		mediationRecord.setInvolvedPerson(involvedPerson);
		mediationRecord.setAddress(address);
		mediationRecord.setRecordTime(DateUtils.parseToDate(time, DateUtils.DATE_YMDHM_STR));
		if(!StringUtils.isEmpty(joinPerson)){
			mediationRecord.setJoinPerson(joinPerson);
		}
		mediationRecord.setRecordTypeEnum(RecordTypeEnum.valueOf(recordtype));
		this.save(mediationRecord);
		return mediationRecord;
	}
	
	@Override
	public void modifyMediationRecord(String recordid, String recordContent,
			String involvedPerson, String time, String address,
			String joinPerson, String recordtype) throws BizException,
			ErrorException {
		MediationRecord mediationRecord = this.getById(recordid);
		if(mediationRecord == null){
			throw new BizException("笔录不存在!");
		}
		if(StringUtils.isEmpty(recordContent) || StringUtils.isEmpty(involvedPerson) || StringUtils.isEmpty(recordtype)
				||StringUtils.isEmpty(time) || StringUtils.isEmpty(address)){
			throw new BizException("参数不能空!");
		}
		mediationRecord.setRecordContent(recordContent);
		mediationRecord.setInvolvedPerson(involvedPerson);
		mediationRecord.setAddress(address);
		mediationRecord.setRecordTime(DateUtils.parseToDate(time, DateUtils.DATE_YMD_STR));
		if(!StringUtils.isEmpty(joinPerson)){
			mediationRecord.setJoinPerson(joinPerson);
		}
		mediationRecord.setRecordTypeEnum(RecordTypeEnum.valueOf(recordtype));
		this.save(mediationRecord);
	}
	
}