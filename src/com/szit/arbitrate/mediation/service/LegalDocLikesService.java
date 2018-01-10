package com.szit.arbitrate.mediation.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.mediation.entity.LegalDocLikes;
import com.szit.arbitrate.mediation.entity.query.LegalDocLikesQuery;

/**
 * 
* @ClassName: LegalDocLikesService  
* @Description:经典案例点赞业务接口   
* @author   
* @date 2017年7月4日 上午10:58:16  
* @Copyright
* @versions:1.0 
*
 */
public interface LegalDocLikesService extends AppBaseService<LegalDocLikes, LegalDocLikesQuery>{
	
	/**
	 * 
	* @Title: addLegalDocLikes  
	* @Description: 添加经典案例点赞
	* @param @param legaldocid
	* @param @param likesclientid
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	public void addLegalDocLikes(String legaldocid, String likesclientid)throws BizException,ErrorException;

	/**
	 * 
	* @Title: cancelLegalDocLikes  
	* @Description: 取消点赞
	* @param @param legaldocid
	* @param @param likesclientid
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	public void cancelLegalDocLikes(String legaldocid, String likesclientid)throws BizException,ErrorException;
	
}
