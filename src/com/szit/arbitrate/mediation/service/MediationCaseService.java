package com.szit.arbitrate.mediation.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.api.mediation.bo.in.TClientInBo;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.mediation.entity.MediationCase;
import com.szit.arbitrate.mediation.entity.dto.MediationCaseExcelDto;
import com.szit.arbitrate.mediation.entity.dto.MediationCaseInfoDto;
import com.szit.arbitrate.mediation.entity.query.MediationCaseQuery;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: MediationCaseService
* @Description:调解案件业务接口类
* @author Administrator
* @date 2017年3月23日 上午11:28:35
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface MediationCaseService extends AppBaseService<MediationCase, MediationCaseQuery>{
	
	/**
	 * 
	* @Title: applyMediation 
	* @Description: 申请调解
	* @param @param clientid
	* @param @param casetype 案件类型
	* @param @param caseexplain 申述点
	* @param @param mediatorInfo
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void applyMediation(String caseid, String clientid, String mediatetype, String casetype, String caseexplain,
			String mediatorInfo, List<TClientInBo> jsonlist) throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: addTempClient 
	* @Description: 添加案件申述对象
	* @param @param caseId 案件id
	* @param @param identifyName
	* @param @param tel
	* @param @return
	* @return TempClient 
	* @throws
	 */
	public TempClient addTempClient(String caseId, String name, String tel, String address, 
			String identify, boolean ispartb) throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: deleteTempClient 
	* @Description: 删除临时
	* @param @param id
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void deleteTempClient(String id)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: acceptMediationCase 
	* @Description: 接受调解申请
	* @param @param caseid 案件id
	* @param @param mediatorid 调解员id
	* @return void 
	* @throws
	 */
	public void acceptMediationCase(String caseid, String mediatorid)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: allocateMediationCase 
	* @Description: 调解案件分配
	* @param @param caseid
	* @param @param clientid 分配人id
	* @param @param mediatorid 被分配调解id
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void allocateMediationCase(String clientid, String caseid, String mediatorid)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: refuseClientMediationApplication 
	* @Description: 拒绝用户的案件申请
	* @param @param clientid
	* @param @param caseid
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void refuseClientMediationApplication(String clientid, String caseid, String refusereason)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: backToUpperLevel 
	* @Description: 案件退回上一级
	* @param @param client
	* @param @param caseid
	* @param @param reason
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void backToUpperLevel(String clientid, String caseid, String reason)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: getMediationListByClientId 
	* @Description: 根据调解员id以及案件状态获取调解员的所有调解案例
	* @param @param clientid casestate
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return List<MediationCase> 
	* @throws
	 */
	public List<MediationCase> getMediationCaseListByClientId(String clientid, String casestate) throws BizException,ErrorException;

	/**
	 * 
	* @Title: searchMediationCaseByName  
	* @Description: 调解员搜索案件
	* @param @param clientid
	* @param @param casename
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return List<MediationCaseInfoDto>    返回类型  
	* @throws
	 */
	public List<MediationCaseInfoDto> searchMediationCaseByName(String clientid, String casename)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: getListByClientIdAndCaseType 
	* @Description: 根据用户id以及分页索引查询案件列表--适用于调解员
	* @param @param clientid
	* @param @param tabindex
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return List<MediationCase> 
	* @throws
	 */
	public List<MediationCaseInfoDto> getListByClientIdAndCaseType(String clientid, Integer tabindex)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: completeMediationCase 
	* @Description: 结案操作
	* @param @param clientid
	* @param @param caseid
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void completeMediationCase(String caseid,String mediatetype, String casetype,
			String mediatorid, boolean difficult, Integer people, String caseMoney,
			String protocolForm, String caseSource,Integer differenceSubject) throws BizException,ErrorException;
	
	
	/**
	 * 
	* @Title: giveupMediation 
	* @Description: 放弃调解，或者转为线下调解
	* @param @param clientid
	* @param @param caseid
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void giveupMediation(String clientid, String caseid)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: gatherTempClient 
	* @Description: 召集案件的被申请人
	* @param @param tempclientid
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void gatherTempClient(String tempclientid)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: operateMediationForSign 
	* @Description: TODO
	* @param @param caseid
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void operateMediationForSign(String caseid)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: getListForNormalClient 
	* @Description: 普通用户查询调解案件，包括自己申请的和被他人投诉的
	* @param @param clientid
	* @param @param casestate
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return List<MediationCaseInfoDto> 
	* @throws
	 */
	public List<MediationCaseInfoDto> getListForNormalClient(String clientid, Integer tabindex)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: notifyClientToSignProtocolOffLine 
	* @Description: 通知当事人线下签署协议
	* @param @param caseid
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void notifyClientToSignProtocolOffLine(String caseid)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: toMediateOffLine 
	* @Description: 转为线下调解
	* @param @param clientid
	* @param @param caseid
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void closeMediation(String clientid, String caseid, String failreason)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: FileToZipByCaseId  
	* @Description: 导出文件到zip
	* @param @param caseid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return String    返回类型  
	* @throws
	 */
	public Map<String, Object> fileToZipByCaseId(String caseid)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: statisticsMediationCaseByClientId  
	* @Description: 统计调解员的案件分布
	* @param @param clientid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return Map<String,Integer>    返回类型  
	* @throws
	 */
	public Map<String, Object> statisticsMediationCaseByClientId(String clientid,
			String startTime,String endTime,String mediationAgencyId)throws BizException,ErrorException;
	
	
	/**
	 * 
	* @Title: statisticsMediationCaseByClientId  
	* @Description: 统计调解员的案件分布
	* @param @param clientid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return Map<String,Integer>    返回类型  
	* @throws
	 */
	public Map<String, Object> statisticsMediationCaseByClientIdSuccess(String clientid,
			String startTime,String endTime,String mediationAgencyId)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: getSendClientDescByCaseId  
	* @Description: 判断用户的备注
	* @param @param caseid
	* @param @param clientid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return String    返回类型  
	* @throws
	 */
	public String getSendClientDescByCaseId(String caseid, String clientid)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: statMediationCaseExcelDto  
	* @Description:案件数据统计 
	* @param @param statmonth
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return HashMap<String,HashMap<String,Object>>    返回类型  
	* @throws
	 */
	public HashMap<String,HashMap<String,Object>> statMediationCaseExcelDto(String begin,String end)
			throws BizException,ErrorException;


	
}
