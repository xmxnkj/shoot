package com.szit.arbitrate.mediation.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.mediation.entity.MediatorApply;
import com.szit.arbitrate.mediation.entity.query.MediatorApplyQuery;

/**
 * 
* @ProjectName:
* @ClassName: MediatorApplyService
* @Description:申请业务接口类
* @author Administrator
* @date 2017年3月23日 上午11:39:10
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface MediatorApplyService extends AppBaseService<MediatorApply, MediatorApplyQuery>{
	
	/**
	 * 申请成为员
	 * @param clientId
	 * @param applyReason
	 * @return
	 */
	public MediatorApply saveApplyMediator(String clientId ,String applyReason)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: getMediatorApply 
	* @Description: 获取员申请信息
	* @param @param clientid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return MediatorApply 
	* @throws
	 */
	public MediatorApply getMediatorApply(String clientid)throws BizException,ErrorException;
	
	/**
	 * 审核员的申請
	 * @param id				申請事件id
	 * @param applyClientId		申請人id
	 * @param auditState		申請狀態
	 * @param mediationAgencyId 調節機構id
	 * @return
	 */
	public Object auditMediatorApply(String id,String applyClientId,String applyState,String mediationAgencyId);
}
