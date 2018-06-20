package com.szit.arbitrate.client.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.client.entity.AuthorityGroupOperation;
import com.szit.arbitrate.client.entity.query.AuthorityGroupOperationQuery;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: AuthorityGroupOperationService
* @Description:权限组操作实体业务接口类
* @author yuyb
* @date 2017年3月17日 下午5:24:40
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface AuthorityGroupOperationService extends BaseService<AuthorityGroupOperation, AuthorityGroupOperationQuery>{

	/**
	 * 
	* @Title: clientHasAuthorityOrNot 
	* @Description: 根据用户id以及操作名确定用户是否有该操作权限
	* @param @param clientid
	* @param @param operationname
	* @param @return true表示有权限，false没有
	* @param @throws BizException
	* @param @throws ErrorException
	* @return boolean 
	* @throws
	 */
	public boolean clientHasAuthorityOrNot(String clientid, String operationname) throws BizException, ErrorException;
	
	/**
	 * 
	* @Title: setClientAuthorityGroupOperation 
	* @Description: 位用户分配具体操作权限
	* @param @param clientid
	* @param @param authoritygroupid
	* @param @param operationname
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void setClientAuthorityGroupOperation(String clientid, String authoritygroupid, 
			String operationname)throws BizException, ErrorException;
	
}
