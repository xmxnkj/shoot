package com.szit.arbitrate.client.dao;

import com.hsit.common.dao.BaseHibernateDao;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.client.entity.ClientThirdpartyAccount;
import com.szit.arbitrate.client.entity.query.ClientThirdpartyAccountQuery;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: ClientThirdpartyAccountDao
* @Description:第三方账号登录实体dao接口类
* @author yuyb
* @date 2017年3月17日 下午5:16:04
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ClientThirdpartyAccountDao extends BaseHibernateDao<ClientThirdpartyAccount, ClientThirdpartyAccountQuery>{

	/**
	 * 
	* 方法描述:是否绑定了第三方
	* 创建人: Administrator
	* 创建时间：2017年3月20日
	* @param clinetId
	* @return
	* @throws ErrorException
	 */
	public Boolean isBindingThirdparty(String clientid)throws ErrorException;
	
}
