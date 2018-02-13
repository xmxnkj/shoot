package com.szit.arbitrate.mediation.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.mediation.dao.BasicDataDao;
import com.szit.arbitrate.mediation.entity.BasicData;
import com.szit.arbitrate.mediation.entity.query.BasicDataQuery;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: BasicDataDaoImpl
* @Description:基础数据dao实现类
* @author Administrator
* @date 2017年5月24日 下午4:08:43
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class BasicDataDaoImpl extends BaseHibernateDaoImpl<BasicData, BasicDataQuery> implements BasicDataDao{
	
	@Override
	public List<QueryParam> buildQueryParams(BasicDataQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			/*if(StringUtils.isNotEmpty(query.getLitigationType())){
				qps.add(new QueryParam("litigationType", query.getLitigationType()));
			}*/
			if(StringUtils.isNotEmpty(query.getDataType())){
				qps.add(new QueryParam("dataType", query.getDataType()));
			}
			if(StringUtils.isNotEmpty(query.getDataTypeDesc())){
				qps.add(new QueryParam("dataTypeDesc", query.getDataTypeDesc()));
			}
			if(StringUtils.isNotEmpty(query.getParentType())){
				qps.add(new QueryParam("parentType", query.getParentType()));
			}
			if(StringUtils.isNotEmpty(query.getDataValue())){
				qps.add(new QueryParam("dataValue", query.getDataValue()));
			}
		}
		return qps;
	}

	@Override
	public List<BasicData> getBasicDataList() throws ErrorException {
		StringBuffer sql  =new StringBuffer();
		sql.append(" SELECT * FROM me_basic_data bd");
		sql.append(" WHERE bd.PARENT_TYPE is null");
		sql.append(" order by bd.DATA_TYPE");
		List<BasicData> list = (List<BasicData>) this.findSql(sql.toString(), BasicData.class);
		return list;
	}

	@Override
	public List<String> getTypeClassify() {
		StringBuffer sql  =new StringBuffer();
		sql.append("SELECT DATA_VALUE as type FROM me_basic_data WHERE DATA_TYPE ='CivilCaseType' GROUP BY DATA_VALUE");
		List<String> list = (List<String>) this.getJdbcTemplate().queryForList(sql.toString(), String.class);
		return list;
	}
	
}
