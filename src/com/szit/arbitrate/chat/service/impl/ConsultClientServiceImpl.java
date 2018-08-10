package com.szit.arbitrate.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.arbitrate.chat.dao.ConsultClientDao;
import com.szit.arbitrate.chat.entity.ConsultClient;
import com.szit.arbitrate.chat.entity.query.ConsultClientQuery;
import com.szit.arbitrate.chat.service.ConsultClientService;

@Service
@Transactional
public class ConsultClientServiceImpl extends AppBaseServiceImpl<ConsultClient, ConsultClientQuery> implements ConsultClientService{

	@Autowired
	private ConsultClientDao dao;

	public ConsultClientDao getDao() {
		return dao;
	}

	public void setDao(ConsultClientDao dao) {
		this.dao = dao;
	}
	
	
}
