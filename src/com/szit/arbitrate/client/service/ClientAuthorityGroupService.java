package com.szit.arbitrate.client.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.client.entity.ClientAuthorityGroup;
import com.szit.arbitrate.client.entity.enumvo.AuthorityGroupEnum;
import com.szit.arbitrate.client.entity.query.ClientAuthorityGroupQuery;

/**
 * 
* @ProjectName:
* @ClassName: ClientAuthorityGroupService
* @Description:用户权限组映射接口类
* @author Administrator
* @date 2017年3月28日 上午9:17:17
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ClientAuthorityGroupService extends BaseService<ClientAuthorityGroup, ClientAuthorityGroupQuery>{

	/**
	 * 
	* @Title: isClientInAuthorityGroupOrNot 
	* @Description: 确定用户是否属于某个权限组
	* @param @param clientid
	* @param @param authoritygroupname 权限组名
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return boolean 
	* @throws
	 */
	public boolean isClientInAuthorityGroupOrNot(String clientid, String authoritygroupname) throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: setClientAuthorityGroup 
	* @Description: 为用户设置权限组
	* @param @param clientid
	* @param @param groupenum
	* @param @throws BizException
	* @param @throws ErrorException
	* @return String 
	* @throws
	 */
	public String setClientAuthorityGroup(String clientid, AuthorityGroupEnum groupenum) throws BizException,ErrorException;
	
}
