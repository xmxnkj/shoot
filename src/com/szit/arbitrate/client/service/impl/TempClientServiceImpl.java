package com.szit.arbitrate.client.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.szit.arbitrate.client.dao.TempClientDao;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.query.TempClientQuery;
import com.szit.arbitrate.client.service.TempClientService;

/**
 * 
* @ProjectName:
* @ClassName: TempClientServiceImpl
* @Description:虚拟用户业务实现类
* @author Administrator
* @date 2017年3月24日 下午3:29:37
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class TempClientServiceImpl extends BaseServiceImpl<TempClient, TempClientQuery> implements TempClientService{

	@Autowired
	private TempClientDao dao;

	public TempClientDao getDao() {
		return dao;
	}

	public void setDao(TempClientDao dao) {
		this.dao = dao;
	}
	
	@Override
	public boolean isCaseClientAllCalled(String caseid) throws BizException,
			ErrorException {
		boolean ok = dao.isCaseClientAllCalled(caseid);
		return ok;
	}
	
	@Override
	public void updateTempClientTel(String tempclientid, String tel) throws BizException,
			ErrorException {
		if(StringUtils.isEmpty(tel)){
			throw new BizException("电话号码不能空!");
		}
		TempClient entity = this.getById(tempclientid);
		if(entity == null){
			throw new BizException("找不到当事人!");
		}
		entity.setTel(tel);
		this.save(entity);
	}
	
}
