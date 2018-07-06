package com.szit.arbitrate.client.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.szit.arbitrate.client.dao.AuthorityGroupOperationDao;
import com.szit.arbitrate.client.entity.AuthorityGroup;
import com.szit.arbitrate.client.entity.AuthorityGroupOperation;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.ClientOperation;
import com.szit.arbitrate.client.entity.query.AuthorityGroupOperationQuery;
import com.szit.arbitrate.client.entity.query.ClientOperationQuery;
import com.szit.arbitrate.client.service.AuthorityGroupOperationService;
import com.szit.arbitrate.client.service.AuthorityGroupService;
import com.szit.arbitrate.client.service.ClientOperationService;
import com.szit.arbitrate.client.service.ClientService;

@Service
@Transactional
public class AuthorityGroupOperationServiceImpl extends BaseServiceImpl<AuthorityGroupOperation, AuthorityGroupOperationQuery>
	implements AuthorityGroupOperationService{

	@Autowired
	private AuthorityGroupService authorityGroupService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ClientOperationService clientOperationService;
	@Autowired
	private AuthorityGroupOperationDao dao;

	public AuthorityGroupOperationDao getDao() {
		return dao;
	}

	public void setDao(AuthorityGroupOperationDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean clientHasAuthorityOrNot(String clientid, String operationname)
			throws BizException, ErrorException {
		// 1.查询获得操作实体id
		ClientOperationQuery clientOperationQuery = new ClientOperationQuery();
		clientOperationQuery.setOperationName(operationname);
		ClientOperation clientOperation = clientOperationService.getEntity(clientOperationQuery);
		if(clientOperation == null){
			throw new BizException("该操作不存在!");
		}
		String clientOperationId = clientOperation.getId();
		
		// 2.确定该用户是否拥有该操作id的权限
		AuthorityGroupOperationQuery query = new AuthorityGroupOperationQuery();
		query.setClientId(clientid);
		query.setClientOperationId(clientOperationId);
		long count = this.getEntityCount(query);
		if(count > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public void setClientAuthorityGroupOperation(String clientid,
			String authoritygroupid, String operationname) throws BizException,
			ErrorException {
		if(StringUtils.isEmpty(clientid) || StringUtils.isEmpty(authoritygroupid) || StringUtils.isEmpty(operationname)){
			throw new BizException("参数不能空!");
		}
		Client client = clientService.getById(clientid);
		ClientOperationQuery clientOperationQuery = new ClientOperationQuery();
		clientOperationQuery.setOperationName(operationname);
		ClientOperation clientOperation = clientOperationService.getEntity(clientOperationQuery);
		if(clientOperation == null){
			throw new BizException("不存在的操作!");
		}
		AuthorityGroup authorityGroup = authorityGroupService.getById(authoritygroupid);
		AuthorityGroupOperation entity = new AuthorityGroupOperation();
		entity.setClientId(clientid);
		entity.setClientName(client.getIdentifyName());
		entity.setAuthorityGroupId(authoritygroupid);
		entity.setAuthorityGroupName(authorityGroup.getAuthorityDescription());
		entity.setClientOperationId(clientOperation.getId());
		entity.setClientOperationName(clientOperation.getOperationInfo());
		this.save(entity);
		
	}
}