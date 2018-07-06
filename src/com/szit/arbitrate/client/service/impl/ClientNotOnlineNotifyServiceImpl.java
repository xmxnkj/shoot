package com.szit.arbitrate.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.szit.arbitrate.client.dao.ClientNotOnlineNotifyDao;
import com.szit.arbitrate.client.entity.ClientNotOnlineNotify;
import com.szit.arbitrate.client.entity.query.ClientNotOnlineNotifyQuery;
import com.szit.arbitrate.client.service.ClientNotOnlineNotifyService;

@Service
@Transactional
public class ClientNotOnlineNotifyServiceImpl extends BaseServiceImpl<ClientNotOnlineNotify, ClientNotOnlineNotifyQuery>
	implements ClientNotOnlineNotifyService{
	
	@Autowired
	private ClientNotOnlineNotifyDao dao;

	public ClientNotOnlineNotifyDao getDao() {
		return dao;
	}

	public void setDao(ClientNotOnlineNotifyDao dao) {
		this.dao = dao;
	}

	@Override
	public ClientNotOnlineNotify getClientNotOnlineNotifyByClientCase(
			String clientid, String caseid) throws BizException, ErrorException {
		ClientNotOnlineNotifyQuery query= new ClientNotOnlineNotifyQuery();
		query.setCaseId(caseid);
		query.setClientId(clientid);
		ClientNotOnlineNotify entity = this.getEntity(query);
		return entity;
	}
	
}
