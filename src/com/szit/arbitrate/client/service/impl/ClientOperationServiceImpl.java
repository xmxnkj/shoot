package com.szit.arbitrate.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.service.impl.BaseServiceImpl;
import com.szit.arbitrate.client.dao.ClientOperationDao;
import com.szit.arbitrate.client.entity.ClientOperation;
import com.szit.arbitrate.client.entity.query.ClientOperationQuery;
import com.szit.arbitrate.client.service.ClientOperationService;

@Service
@Transactional
public class ClientOperationServiceImpl extends BaseServiceImpl<ClientOperation, ClientOperationQuery> implements ClientOperationService{

	@Autowired
	private ClientOperationDao dao;

	public ClientOperationDao getDao() {
		return dao;
	}

	public void setDao(ClientOperationDao dao) {
		this.dao = dao;
	}
	
}
