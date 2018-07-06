package com.szit.arbitrate.client.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.szit.arbitrate.client.dao.ClientAuthorityGroupDao;
import com.szit.arbitrate.client.entity.AuthorityGroup;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.ClientAuthorityGroup;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;
import com.szit.arbitrate.client.entity.query.AuthorityGroupQuery;
import com.szit.arbitrate.client.entity.query.ClientAuthorityGroupQuery;
import com.szit.arbitrate.client.service.AuthorityGroupService;
import com.szit.arbitrate.client.service.ClientAuthorityGroupService;
import com.szit.arbitrate.client.service.ClientService;

@Service
@Transactional
public class ClientAuthorityGroupServiceImpl extends BaseServiceImpl<ClientAuthorityGroup, ClientAuthorityGroupQuery>
	implements ClientAuthorityGroupService{
	
	@Autowired
	private AuthorityGroupService authorityGroupService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ClientAuthorityGroupDao dao;

	public ClientAuthorityGroupDao getDao() {
		return dao;
	}

	public void setDao(ClientAuthorityGroupDao dao) {
		this.dao = dao;
	}

	@Override
	public boolean isClientInAuthorityGroupOrNot(String clientid,
			String authoritygroupname) throws BizException, ErrorException {
		ClientAuthorityGroupQuery query = new ClientAuthorityGroupQuery();
		query.setClientId(clientid);
		query.setAuthorityGroupName(AuthorityGroupEnum.valueOf(authoritygroupname));
		long count = this.getEntityCount(query);
		if(count > 0){
			return true;
		}
		return false;
	}
	
	@Override
	public String setClientAuthorityGroup(String clientid,AuthorityGroupEnum groupenum) throws BizException, ErrorException {
		//1.取得用户对象
		Client client = clientService.getById(clientid);
		//2.设置该用户的权限
		ClientAuthorityGroup clientGroup = new ClientAuthorityGroup();
		clientGroup.setClientId(client.getId());
		if(StringUtils.isNotEmpty(client.getIdentifyName())){
			clientGroup.setClientName(client.getIdentifyName());
		}
		AuthorityGroupQuery authorityGroupQuery = new AuthorityGroupQuery();
		//权限组设为Normal普通用户
		authorityGroupQuery.setAuthorityGroupName(groupenum);
		AuthorityGroup authorityGroup = authorityGroupService.getEntity(authorityGroupQuery);
		if(authorityGroup != null){
			clientGroup.setAuthorityGroupId(authorityGroup.getId());
			clientGroup.setAuthorityGroupName(authorityGroup.getAuthorityGroupName());
		}
		this.save(clientGroup);
		
		return authorityGroup.getId();
	}

}
