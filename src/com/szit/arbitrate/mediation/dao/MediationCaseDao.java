package com.szit.arbitrate.mediation.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsit.common.dao.BaseHibernateDao;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.mediation.entity.MediationCase;
import com.szit.arbitrate.mediation.entity.dto.MediationCaseExcelDto;
import com.szit.arbitrate.mediation.entity.dto.MediationCaseInfoDto;
import com.szit.arbitrate.mediation.entity.query.MediationCaseQuery;

/**
 * 
* @ProjectName:
* @ClassName: CaseDao
* @Description:实体dao接口类
* @author Administrator
* @date 2017年3月23日 上午10:56:43
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface MediationCaseDao extends BaseHibernateDao<MediationCase, MediationCaseQuery>{

	/**
	 * 
	* @Title: getMediationCaseListByClientState 
	* @Description: 根据员的类型获取 管理的列表数据
	* @param @param clientid
	* @param @param clientStateEnum
	* @param @return
	* @param @throws ErrorException
	* @return List<MediationCase> 
	* @throws
	 */
	public List<MediationCaseInfoDto> getMediationCaseListByClientState(String clientid, 
			ClientStateEnum clientStateEnum,String mediationAgencyId)throws ErrorException;
	
	/**
	 * 
	* @Title: getMediatingCaseListByClientId 
	* @Description: 根据员id获得进行中的列表 包括进行中和暂停中的
	* @param @param clientid
	* @param @return
	* @param @throws ErrorException
	* @return List<MediationCase> 
	* @throws
	 */
	public List<MediationCaseInfoDto> getMediatingCaseListByClientId(String clientid, Integer tabindex,ClientStateEnum clientStateEnum,String mediationAgency)throws ErrorException;
	
	/**
	 * 
	* @Title: searchMediationCaseByName  
	* @Description: 员搜索
	* @param @param clientid
	* @param @param casename
	* @param @return
	* @param @throws BizException    设定文件  
	* @return List<MediationCaseInfoDto>    返回类型  
	* @throws
	 */
	public List<MediationCaseInfoDto> searchMediationCaseByName(String clientid, 
			String casename) throws BizException;			
	
	/**
	 * 
	* @Title: getListForNormalClient 
	* @Description: 普通用户查询，包括自己申请的和被他人投诉的
	* @param @param clientid
	* @param @param casestate
	* @param @return
	* @param @throws ErrorException
	* @return List<MediationCase> 
	* @throws
	 */
	public List<MediationCaseInfoDto> getListForNormalClient(String clientid,
			Integer tabindex) throws ErrorException;
	
	/**
	 * 
	* @Title: statisticsMediationCaseByClientId  
	* @Description: 员分布统计
	* @param @param clientid
	* @param @return
	* @param @throws ErrorException    设定文件  
	* @return Map<String,Integer>    返回类型  
	* @throws
	 */
	public Map<String, Object> statisticsMediationCaseByClientId(String clientid, 
			String clientstate, String agencyid,String startTime,String endTime )throws ErrorException;
	
	/**
	 * 
	* @Title: statisticsMediationCaseByClientId  
	* @Description: 员分布统计按时间和id查询
	* @param @param clientid
	* @param @return
	* @param @throws ErrorException    设定文件  
	* @return Map<String,Integer>    返回类型  
	* @throws
	 */
	public Map<String, Object> statisticsMediationCaseByClientId(String clientid, 
			String clientstate, String agencyid,String startTime,String endTime,String mediationAgencyId)throws ErrorException;
	
	/**
	 * 
	* @Title: statMediationCaseExcelDto  
	* @Description: 数据统计
	* @param @return
	* @param @throws ErrorException    设定文件  
	* @return List<MediationCaseExcelDto>    返回类型  
	* @throws
	 */
	public HashMap<String,HashMap<String,Object>> statMediationCaseExcelDto(String begin,String end)throws ErrorException;

	Map<String, Object> statisticsMediationCaseByClientIdSuccess(String clientid, String clientstate, String agencyid,
			String startTime, String endTime, String mediationAgencyId) throws ErrorException;
	
}
