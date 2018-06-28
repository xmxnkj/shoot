package com.szit.arbitrate.mediation.service;

import java.util.List;
import java.util.Map;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.mediation.entity.LegalDoc;
import com.szit.arbitrate.mediation.entity.query.LegalDocQuery;

/**
 * 
* @ProjectName:
* @ClassName: LegalDocService
* @Description:法律文档业务接口类
* @author Administrator
* @date 2017年3月23日 下午1:50:45
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface LegalDocService extends AppBaseService<LegalDoc, LegalDocQuery>{
	
	/**
	 * 
	* @Title: getLegalDocListByDocType 
	* @Description: 根据分类获取法规文档列表
	* @param @param doctype
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return List<LegalDoc> 
	* @throws
	 */
	public List<LegalDoc> getLegalDocListByDocType(String doctype) throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: getLegalDocDetailById 
	* @Description: 获取法规文档详情，包括点赞，评论
	* @param @param legaldocid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return Map<String,Object> 
	* @throws
	 */
	public Map<String, Object> getLegalDocDetailById(String legaldocid) throws BizException,ErrorException;
    
	/**
	 * @Title: savaLegalDoc
	 * @Description: 添加法规文档
	 * @param docType
	 * @param title
	 * @param docContent
	 * @param publishUnit
	 * @throws BizException
	 * @throws ErrorException
	 */
	public void savaLegalDoc(String docId, String title,String docType, String docContent,String publishUnit,
			String publishTime,Integer orderdisplay) throws BizException,ErrorException;
    
	/**
	 * 根据id删除法规文档
	 * @param ID
	 */
	public void deleteLegalDoc(String ID);
	
	/**
	 * 
	* @Title: upAndDownShelve 
	* @Description: 文档上下架
	* @param @param params
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void upAndDownShelve(Map<String, Object> params)throws BizException, ErrorException;
	
	
}
