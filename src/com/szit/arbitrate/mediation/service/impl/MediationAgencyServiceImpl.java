package com.szit.arbitrate.mediation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.arbitrate.mediation.dao.MediationAgencyDao;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.entity.query.MediationAgencyQuery;
import com.szit.arbitrate.mediation.service.MediationAgencyService;

/**
 * 
* @ProjectName:
* @ClassName: MediationAgencyServiceImpl
* @Description:机构业务接口实现类
* @author Administrator
* @date 2017年3月23日 上午11:27:23
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class MediationAgencyServiceImpl extends AppBaseServiceImpl<MediationAgency, MediationAgencyQuery> implements MediationAgencyService{

	@Autowired
	private MediationAgencyDao dao;

	public MediationAgencyDao getDao() {
		return dao;
	}

	public void setDao(MediationAgencyDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<MediationAgency> searchMediationAgencyList(String agencytype,String casetype, 
			String agencyname) throws BizException,ErrorException {
		
		List<MediationAgency> list = dao.searchMediationAgencyList(agencytype, casetype, agencyname);
		
		return list;
	}
	
	@Override
	public List<MediationAgency> searchOpenedMediationAgencyList()
			throws BizException, ErrorException {
		MediationAgencyQuery query = new MediationAgencyQuery();
		query.setOpenOrNot(true);
		return this.getEntities(query);
	}
	
	@Override
	public MediationAgency getAgencyByManagerClientId(String clientid)
			throws BizException, ErrorException {
		MediationAgencyQuery query = new MediationAgencyQuery();
		query.setManagerClientId(clientid);
		MediationAgency entity = this.getEntity(query);
		return entity;
	}
	
}
