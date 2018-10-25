package com.szit.arbitrate.basisset.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.szit.arbitrate.basisset.dao.ClientBasisParameterSettingDao;
import com.szit.arbitrate.basisset.entity.ClientBasisParameterSetting;
import com.szit.arbitrate.basisset.entity.SysParameterTable;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;
import com.szit.arbitrate.basisset.entity.query.ClientBasisParameterSettingQuery;
import com.szit.arbitrate.basisset.entity.query.SysParameterTableQuery;
import com.szit.arbitrate.basisset.service.ClientBasisParameterSettingService;
import com.szit.arbitrate.basisset.service.SysParameterTableService;
/**
 * @ProjectName:xmszit-cowell-module-broadcast
 * @ClassName: ClientBasisParameterSettingServiceImpl
 * @Description:基础参数设置业务逻辑实现类
 * @author XUJC
 * @date
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
@Service
@Transactional
public class ClientBasisParameterSettingServiceImpl extends BaseServiceImpl<ClientBasisParameterSetting,ClientBasisParameterSettingQuery>
		implements ClientBasisParameterSettingService {
	
	@Autowired
	private ClientBasisParameterSettingDao dao;

	@Autowired
	private SysParameterTableService sysParameterTableService;
	
	public ClientBasisParameterSettingDao getDao() {
		return dao;
	}
	
	public void setDao(ClientBasisParameterSettingDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void initClinetData(String clientId)
			throws BizException, ErrorException {
		//判断一下这个会员是否已经初始化了,有初始化就不做了
		ClientBasisParameterSettingQuery query = new ClientBasisParameterSettingQuery();
		query.setClientid(clientId);
		long counts = this.getEntityCount(query);
		if(counts<=0){
			SysParameterTableQuery queryParamTable = new SysParameterTableQuery();
			//排序一下
			queryParamTable.addOrder("parameterseq", false);
			List<SysParameterTable> paramTableList = sysParameterTableService.getEntities(queryParamTable);
			ClientBasisParameterSetting clientbasisparametersetting = null;
			for (SysParameterTable sysParameterTable : paramTableList) {
				clientbasisparametersetting = new ClientBasisParameterSetting();
				clientbasisparametersetting.setParametertype(sysParameterTable.getParametertype());
				clientbasisparametersetting.setSeq(sysParameterTable.getParameterseq());
				clientbasisparametersetting.setParameterid(sysParameterTable.getId());
				clientbasisparametersetting.setClientid(clientId);
				clientbasisparametersetting.setParametersettingval(sysParameterTable.getParameterinitvla());
				clientbasisparametersetting.setSettingdatetime(sysParameterTable.getBuliddatetime());
				this.save(clientbasisparametersetting);
			}
		}
	}

	@Override
	public List<ClientBasisParameterSetting> mySettingList(String clientId,
			ParameterTypeEnum parametertypenum) throws BizException,
			ErrorException {
		ClientBasisParameterSettingQuery queryTemp = new ClientBasisParameterSettingQuery();
		queryTemp.setClientid(clientId);
		queryTemp.addOrder("seq", true);
		queryTemp.setParametertype(parametertypenum);
		return this.getEntities(queryTemp);
	}

	@Override
	public void setting(
			ParameterTypeEnum parametertypenum, String parameterid,
			String parameterval, String clientId) throws BizException,
			ErrorException {
		ClientBasisParameterSettingQuery cbpQuery = new ClientBasisParameterSettingQuery();
		cbpQuery.setParameterid(parameterid);
		cbpQuery.setParametertype(parametertypenum);
		cbpQuery.setClientid(clientId);
		ClientBasisParameterSetting entityTemp = this.getEntity(cbpQuery);
		entityTemp.setParametersettingval(parameterval);
		this.save(entityTemp);

	}
	
	
}