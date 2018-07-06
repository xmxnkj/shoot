package com.szit.arbitrate.client.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.service.impl.BaseServiceImpl;
import com.szit.arbitrate.client.dao.AuthorityGroupDao;
import com.szit.arbitrate.client.entity.AuthorityGroup;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;
import com.szit.arbitrate.client.entity.query.AuthorityGroupQuery;
import com.szit.arbitrate.client.service.AuthorityGroupService;

@Service
@Transactional
public class AuthorityGroupServiceImpl extends BaseServiceImpl<AuthorityGroup, AuthorityGroupQuery> implements AuthorityGroupService{

	@Autowired
	private AuthorityGroupDao dao;

	public AuthorityGroupDao getDao() {
		return dao;
	}

	public void setDao(AuthorityGroupDao dao) {
		this.dao = dao;
	}

	@Override
	public AuthorityGroup getAuthorityGroup(AuthorityGroupEnum authorityGroupEnum) {
		AuthorityGroupQuery query = new AuthorityGroupQuery();
		query.setAuthorityGroupName(authorityGroupEnum);
		AuthorityGroup AG = this.getEntity(query);
		return AG;
	}
	
}
