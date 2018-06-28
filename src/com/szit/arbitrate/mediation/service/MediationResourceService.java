package com.szit.arbitrate.mediation.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.mediation.entity.MediationResource;
import com.szit.arbitrate.mediation.entity.query.MediationResourceQuery;

/**
 * 
* @ProjectName:
* @ClassName: MediationResourceService
* @Description:资源业务接口类
* @author yuyb
* @date 2017年3月23日 上午11:36:44
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface MediationResourceService extends AppBaseService<MediationResource, MediationResourceQuery>{

	/**
	 * 
	* @Title: saveMediationReource 
	* @Description: 保存证据资源
	* @param @param clientid
	* @param @param caseid
	* @param @param chatmessageid
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void saveMediationReource(String clientid, String caseid, String chatmessageid) throws BizException,ErrorException;
	
}
