package com.szit.arbitrate.client.dao;

import java.util.List;
import java.util.Map;

import com.hsit.common.dao.BaseHibernateDao;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.client.entity.query.ClientQuery;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: ClientDao
* @Description:用户dao接口
* @author Administrator
* @date 2017年3月17日 下午4:44:41
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ClientDao extends BaseHibernateDao<Client, ClientQuery>{
	
	/**
	 * 
	* 方法描述:通过账号(手机)查询
	* 创建人: yuyb
	* 创建时间：2017年3月20日
	* @param phoneList
	* @return
	* @throws ErrorException
	 */
	public List<Client> findByAccount(List<String> phoneList) throws ErrorException;
	
	/**
	 * 
	* @Title: statisticsClientRes  
	* @Description: 中心管理员统计用户来源信息
	* @param @return
	* @param @throws ErrorException    设定文件  
	* @return Map<String,Object>    返回类型  
	* @throws
	 */
	public Map<String, Object> statisticsClientRes()throws ErrorException;

	/**
	 * 查找当前组织之下的所有用户
	 * @param ClientGroupDivision 实体类的code
	 */
	public List<Map<String, Object>> getClientGroupDivisionClient(String code);
}
