package com.szit.arbitrate.mediation.service;

import java.util.List;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.mediation.dao.MediationAgencyDao;
import com.szit.arbitrate.mediation.entity.MediationAgency;
import com.szit.arbitrate.mediation.entity.query.MediationAgencyQuery;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: MediationAgencyService
* @Description:调解机构业务接口类
* @author yuyb
* @date 2017年3月23日 上午11:24:30
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface MediationAgencyService extends AppBaseService<MediationAgency, MediationAgencyQuery>{
	
	/**
	 * 
	* @Title: searchMediationAgencyList 
	* @Description: 根据条件筛选调解机构列表
	* @param @param address
	* @param @param casetype
	* @param @param agencytype
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return List<MediationAgency> 
	* @throws
	 */
	public List<MediationAgency> searchMediationAgencyList(String agencytype, String casetype, String agencyname) throws BizException,ErrorException;

	/**
	 * 
	* @Title: searchOpenedMediationAgencyList 
	* @Description: 查询已开通线上服务的，可分配案件的机构列表
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return List<MediationAgency> 
	* @throws
	 */
	public List<MediationAgency> searchOpenedMediationAgencyList()throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: getAgencyByManagerClientId  
	* @Description: 根据管理员id获取机构s
	* @param @param clientid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return MediationAgency    返回类型  
	* @throws
	 */
	public MediationAgency getAgencyByManagerClientId(String clientid)throws BizException,ErrorException;
	
	public MediationAgencyDao getDao();
	
}
