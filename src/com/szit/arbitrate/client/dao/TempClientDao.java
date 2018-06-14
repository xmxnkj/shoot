package com.szit.arbitrate.client.dao;

import com.hsit.common.dao.BaseHibernateDao;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.query.TempClientQuery;

/**
 * 
* @ProjectName:
* @ClassName: TempClientDao
* @Description:虚拟用户dao接口类
* @author Administrator
* @date 2017年3月24日 下午3:24:55
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface TempClientDao extends BaseHibernateDao<TempClient, TempClientQuery>{
	
	/**
	 * 
	* @Title: isCaseClientAllCalled 
	* @Description: 判断所有当事人是否召集
	* @param @param caseid
	* @param @return
	* @param @throws ErrorException
	* @return boolean 
	* @throws
	 */
	public boolean isCaseClientAllCalled(String caseid) throws ErrorException;

}
