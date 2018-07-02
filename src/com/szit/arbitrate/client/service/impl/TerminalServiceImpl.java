package com.szit.arbitrate.client.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.szit.arbitrate.client.dao.TerminalDao;
import com.szit.arbitrate.client.entity.Terminal;
import com.szit.arbitrate.client.entity.enumvo.TerminalType;
import com.szit.arbitrate.client.entity.query.TerminalQuery;
import com.szit.arbitrate.client.service.TerminalService;

@Service
@Transactional
public class TerminalServiceImpl extends BaseServiceImpl<Terminal, TerminalQuery> implements TerminalService{
	
	@Autowired
	private TerminalDao dao;

	public TerminalDao getDao() {
		return dao;
	}

	public void setDao(TerminalDao dao) {
		this.dao = dao;
	}
	
	@Override
	public Terminal findByClinetIdAndTypeAnCodeToList(String clientId,
			TerminalType terminalType,String terminalCode) throws BizException,
			ErrorException {
		if(StringUtils.isEmpty(clientId)){
			throw new BizException("会员ID不能为空!");
		}
		try {
			TerminalQuery query = new TerminalQuery();
			query.setClientId(clientId);
			query.setTerminalType(terminalType);
			//注释目前一个账号只有一个Android和IOS的设备
			//query.setTerminalCode(terminalCode);
			return this.getEntity(query);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
	@Override
	public Terminal findByTerminalCode(String terminalCode)
			throws BizException, ErrorException {
		if(StringUtils.isEmpty(terminalCode)){
			throw new BizException("terminalCode不能为空!");
		}
		try {
			TerminalQuery query = new TerminalQuery();
			query.setTerminalCode(terminalCode);
			return this.getEntity(query);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
	
	@Override
	public Boolean findByClinetIdForAndroid(String clientId)
			throws BizException, ErrorException {
		if(StringUtils.isEmpty(clientId)){
			throw new BizException("clientId不能为空!");
		}
		try {
			TerminalQuery query = new TerminalQuery();
			query.setClientId(clientId);
			query.setTerminalType(TerminalType.ANDROID);
			long counts = this.getEntityCount(query);
			if(counts>0){
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
	@Override
	public List<Terminal> findByClinetIdForIOS(String clientId)
			throws BizException, ErrorException {
		
		if(StringUtils.isEmpty(clientId)){
			throw new BizException("clientId不能为空!");
		}
		try {
			TerminalQuery query = new TerminalQuery();
			query.setClientId(clientId);
			query.setTerminalType(TerminalType.IOS);
            return this.getEntities(query);
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}

}
