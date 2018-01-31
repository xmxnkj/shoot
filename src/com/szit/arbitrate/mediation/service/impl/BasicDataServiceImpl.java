package com.szit.arbitrate.mediation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.arbitrate.mediation.dao.BasicDataDao;
import com.szit.arbitrate.mediation.entity.BasicData;
import com.szit.arbitrate.mediation.entity.query.BasicDataQuery;
import com.szit.arbitrate.mediation.service.BasicDataService;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: BasicDataServiceImpl
* @Description:基础数据
* @author Administrator
* @date 2017年5月24日 下午4:12:58
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class BasicDataServiceImpl extends AppBaseServiceImpl<BasicData, BasicDataQuery> implements BasicDataService{

	@Autowired
	private BasicDataDao dao;

	public BasicDataDao getDao() {
		return dao;
	}

	public void setDao(BasicDataDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<BasicData> getBasicDataList() throws BizException,
			ErrorException {
		return dao.getBasicDataList();
	}

	@Override
	public List<String> getTypeClassify() {
		return dao.getTypeClassify();
	}
	
}
