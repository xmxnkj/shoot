package com.szit.arbitrate.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.szit.arbitrate.client.dao.ClientTokenDao;
import com.szit.arbitrate.client.entity.ClientToken;
import com.szit.arbitrate.client.entity.query.ClientTokenQuery;
import com.szit.arbitrate.client.service.ClientTokenService;

/**
 * 
* @ClassName: ClientTokenServiceImpl  
* @Description: 用户登录设备信息业务实现类  
* @author   
* @date 2017年6月6日 上午10:29:40  
* @Copyright
* @versions:1.0 
*
 */
@Service
@Transactional
public class ClientTokenServiceImpl extends BaseServiceImpl<ClientToken, ClientTokenQuery> implements ClientTokenService{

	@Autowired
	private ClientTokenDao dao;

	public ClientTokenDao getDao() {
		return dao;
	}

	public void setDao(ClientTokenDao dao) {
		this.dao = dao;
	}
	
	@Override
	public ClientToken getClientTokenByClientId(String clientid)
			throws BizException, ErrorException {
		//保存设备信息
		ClientTokenQuery clientTokenQuery = new ClientTokenQuery();
		clientTokenQuery.setClientId(clientid);
		ClientToken clientToken = this.getEntity(clientTokenQuery);
		return clientToken;
	}
	
}
