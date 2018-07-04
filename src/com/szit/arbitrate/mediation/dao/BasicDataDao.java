package com.szit.arbitrate.mediation.dao;

import java.util.List;

import com.hsit.common.dao.BaseHibernateDao;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.mediation.entity.BasicData;
import com.szit.arbitrate.mediation.entity.query.BasicDataQuery;

/**
 * 
* @ProjectName:
* @ClassName: BasicDataDao
* @Description:基础数据dao接口类
* @author Administrator
* @date 2017年5月24日 下午4:05:49
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface BasicDataDao extends BaseHibernateDao<BasicData, BasicDataQuery>{
	
	/**
	 * 
	* @Title: getBasicDataList  
	* @Description: 获取基础数据列表
	* @param @return
	* @param @throws ErrorException    设定文件  
	* @return List<BasicData>    返回类型  
	* @throws
	 */
	public List<BasicData> getBasicDataList() throws ErrorException;

	public List<String> getTypeClassify();

}
