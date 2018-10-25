package com.szit.arbitrate.basisset.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.basisset.dao.ClientBasisParameterSettingDao;
import com.szit.arbitrate.basisset.entity.ClientBasisParameterSetting;
import com.szit.arbitrate.basisset.entity.query.ClientBasisParameterSettingQuery;

/**
 * @ProjectName:xmszit-cowell-module-broadcast
 * @ClassName: ClientBasisParameterSettingDaoImpl
 * @Description:基础参数设置数据库操作实现类
 * @author XUJC
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
@Repository
public class ClientBasisParameterSettingDaoImpl extends BaseHibernateDaoImpl<ClientBasisParameterSetting, ClientBasisParameterSettingQuery> implements
ClientBasisParameterSettingDao {

	@Override
	public List<QueryParam> buildQueryParams(ClientBasisParameterSettingQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getClientid())) {
				qps.add(new QueryParam("clientid", query.getClientid()));
			}
			if (query.getParametertype()!=null) {
				qps.add(new QueryParam("parametertype", query.getParametertype()));
			}
			if (query.getSeq()!=null) {
				qps.add(new QueryParam("seq", query.getSeq()));
			}
			if (!StringUtils.isEmpty(query.getParameterid())) {
				qps.add(new QueryParam("parameterid", query.getParameterid()));
			}
			if (!StringUtils.isEmpty(query.getParametersettingval())) {
				qps.add(new QueryParam("parametersettingval", query.getParametersettingval()));
			}
			if (query.getSettingdatetime()!=null) {
				qps.add(new QueryParam("settingdatetime", query.getSettingdatetime()));
			}

		}
		return qps;
	}


}