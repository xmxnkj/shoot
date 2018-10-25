package com.szit.arbitrate.basisset.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.basisset.dao.SysParameterTableDao;
import com.szit.arbitrate.basisset.entity.SysParameterTable;
import com.szit.arbitrate.basisset.entity.query.SysParameterTableQuery;

/**
 * @ProjectName:xmszit-cowell-module-broadcast
 * @ClassName: SysParameterTableDaoImpl
 * @Description:基础-系统参数字典数据库操作实现类
 * @author XUJC
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
@Repository
public class SysParameterTableDaoImpl extends BaseHibernateDaoImpl<SysParameterTable, SysParameterTableQuery> implements
SysParameterTableDao {

	@Override
	public List<QueryParam> buildQueryParams(SysParameterTableQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (query.getParametertype()!=null) {
				qps.add(new QueryParam("parametertype", query.getParametertype()));
			}
			if (!StringUtils.isEmpty(query.getParametercode())) {
				qps.add(new QueryParam("parametercode", query.getParametercode()));
			}
			if (!StringUtils.isEmpty(query.getParametername())) {
				qps.add(new QueryParam("parametername", query.getParametername()));
			}
			if (!StringUtils.isEmpty(query.getParameterinitvla())) {
				qps.add(new QueryParam("parameterinitvla", query.getParameterinitvla()));
			}
			if (query.getDeleteflag()!=null) {
				qps.add(new QueryParam("deleteflag", query.getDeleteflag()));
			}
			if (query.getBuliddatetime()!=null) {
				qps.add(new QueryParam("buliddatetime", query.getBuliddatetime()));
			}
		}
		return qps;
	}


}