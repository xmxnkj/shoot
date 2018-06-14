package com.szit.arbitrate.client.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.query.TempClientQuery;

/**
 * 
* @ProjectName:
* @ClassName: TempClientService
* @Description:虚拟用户业务接口类
* @author Administrator
* @date 2017年3月24日 下午3:27:40
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface TempClientService extends BaseService<TempClient, TempClientQuery>{
	
	/**
	 * 
	* @Title: isCaseClientAllCalled 
	* @param @param caseid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return boolean 
	* @throws
	 */
	public boolean isCaseClientAllCalled(String caseid) throws BizException, ErrorException;
	
	/**
	 * 
	* @Title: updateTempClientTel 
	* @param @param tempclientid
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void updateTempClientTel(String tempclientid, String tel)throws BizException, ErrorException;

}
