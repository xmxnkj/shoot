package com.szit.arbitrate.mediation.dao;

import com.hsit.common.dao.BaseHibernateDao;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.mediation.entity.MediationProtocol;
import com.szit.arbitrate.mediation.entity.query.MediationProtocolQuery;

public interface MediationProtocolDao extends BaseHibernateDao<MediationProtocol, MediationProtocolQuery>{
	
	/**
	 * 
	* @Title: getTempClientNotAcceptCount 
	* @Description: 获取被申述人未同意协议书的人数
	* @param @param caseid
	* @param @return
	* @param @throws ErrorException
	* @return long 
	* @throws
	 */
	public int getTempClientNotAcceptCount(String caseid) throws ErrorException;

}
