package com.szit.arbitrate.mediation.service;

import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.mediation.entity.LegalDocDetail;
import com.szit.arbitrate.mediation.entity.query.LegalDocDetailQuery;

/**
 * 
* @ProjectName:
* @ClassName: LegalDocMomentsService
* @Description:法规文档详情业务接口类
* @author Administrator
* @date 2017年3月28日 下午4:05:58
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface LegalDocDetailService extends AppBaseService<LegalDocDetail, LegalDocDetailQuery>{
	
	/**
	 * 
	* @Title: addLegalDocMoments 
	* @Description: 添加文档评论
	* @param @param clientid
	* @param @param legaldocid
	* @param @param momentscontent
	* @param @param ip
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 *//*
	public void addLegalDocMoments(String clientid, String legaldocid, String momentscontent, String ip)throws BizException, ErrorException;
	
	*//**
	 * 
	* @Title: getLegalDocMomentsList 
	* @Description: 文档评论列表
	* @param @param legaldocid
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 *//*
	public Map<String,Object> getLegalDocMomentsList(String legaldocid,int pageNum)throws BizException, ErrorException;*/
}
