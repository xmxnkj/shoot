package com.szit.arbitrate.mediation.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.mediation.entity.LegalDocComments;
import com.szit.arbitrate.mediation.entity.query.LegalDocCommentsQuery;

/**
 * 
* @ClassName: LegalDocCommentsService  
* @Description: 经典案例评论业务接口  
* @author   
* @date 2017年7月4日 上午11:11:48  
* @Copyright
* @versions:1.0 
*
 */
public interface LegalDocCommentsService extends AppBaseService<LegalDocComments, LegalDocCommentsQuery>{

	/**
	 * 
	* @Title: addLegalDocComments  
	* @Description:添加经典案例评论 
	* @param @param legaldocid
	* @param @param commentsclientid
	* @param @param commentscontent
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	public void addLegalDocComments(String legaldocid, String commentsclientid, String commentscontent)
			throws BizException, ErrorException;
	
	/**
	 * 
	* @Title: auditComments  
	* @Description:审核评论 
	* @param @param commentsid
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	public void auditComments(String commentsid)throws BizException, ErrorException;
	
}
