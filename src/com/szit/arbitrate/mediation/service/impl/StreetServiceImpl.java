package com.szit.arbitrate.mediation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.arbitrate.mediation.dao.StreetDao;
import com.szit.arbitrate.mediation.entity.Street;
import com.szit.arbitrate.mediation.entity.query.StreetQuery;
import com.szit.arbitrate.mediation.service.StreetService;

@Service
@Transactional
public class StreetServiceImpl extends AppBaseServiceImpl<Street, StreetQuery> implements StreetService{
	
	@Autowired
	private StreetDao dao;
	
	public StreetDao getDao() {
		return dao;
	}
}
