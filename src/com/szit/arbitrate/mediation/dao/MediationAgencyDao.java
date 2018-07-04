package com.szit.arbitrate.mediation.dao;

import java.util.List;

import com.hsit.common.dao.BaseHibernateDao;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.entity.query.MediationAgencyQuery;

public interface MediationAgencyDao extends BaseHibernateDao<MediationAgency, MediationAgencyQuery>{

	/**
	 * 
	* @Title: searchMediationAgencyList 
	* @Description: 根据不同条件赛选机构列表
	* @param @param paramMap
	* @param @return
	* @param @throws ErrorException
	* @return List<MediationAgency> 
	* @throws
	 */
	public List<MediationAgency> searchMediationAgencyList(String agencytype, String casetype, String agencyname)throws ErrorException;

	/**
	 * 通过员id获取对应的机构
	 * @param mediationId
	 * @return
	 * @throws ErrorException
	 */
	public MediationAgency getMediationAgency(String mediationId)throws Exception;
	
}
