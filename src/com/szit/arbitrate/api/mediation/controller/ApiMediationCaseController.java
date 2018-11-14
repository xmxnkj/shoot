package com.szit.arbitrate.api.mediation.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

/**
 * 
* @ClassName: ApiMediationCaseController
* @author Administrator
* @date 2017年3月28日 上午11:20:45
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ApiMediationCaseController {
	
	/**
	 * 
	* @Title: addTempClient 
	* @Description: 申请添加申述对象  对象可能不存在，所以创建临时对象
	* @param @param caseId
	* @param @param name
	* @param @param tel
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm addTempClient(String caseid, String name, String tel, String address, 
			String identify, boolean ispartb);
	
	/**
	 * 
	* @Title: deleteTempClient 
	* @Description: 删除当事人
	* @param @param id
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm deleteTempClient(String id);
	
	/**
	 * 
	* @Title: updateTempClientTel 
	* @Description: 修改当事人电话
	* @param @param tempclientid
	* @param @param tel
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm updateTempClientTel(String tempclientid, String tel);
	
	/**
	 * 
	* @Title: getMediationCaseDetail 
	* @Description: 获取详情
	* @param @param caseid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getMediationCaseDetail(String caseid);
	
	
	/**
	 * 
	* @Title: applyMediation 
	* @Description: 申请
	* @param @param clientid
	* @param @param jsonlist 当事人json数据
	* @param @param casetype
	* @param @param caseexplain
	* @param @param id
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm applyMediation(String caseid,String clientid, String mediatetype, String casetype,
			String caseexplain, String mediatorInfo, String jsonlist);

	/**
	 * 
	* @Title: acceptMediationCase 
	* @Description: 受理接口，中心管理员，机构管理员，普通员公用一个接口
	* @param @param caseid
	* @param @param mediatorid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm acceptMediationCase(String caseid, String mediatorid);
	
	/**
	 * 
	* @Title: allocateMediationCase 
	* @Description: 分配
	* @param @param clientid 分配者id
	* @param @param caseid id
	* @param @param mediatorid 分配对象id
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm allocateMediationCase(String clientid,String caseid, String mediatorid);
	
	/**
	 * 
	* @Title: refuseClientMediationApplication 
	* @Description: 拒绝-用于中心管理员
	* @param @param clientid
	* @param @param caseid
	* @param @param refusereason
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm refuseClientMediationApply(String clientid, String caseid, String refusereason);
	
	/**
	 * 
	* @Title: backToUpperLevel 
	* @Description: 退回上一级-员用
	* @param @param clientid
	* @param @param caseid
	* @param @param reason
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm backToUpperLevel(String clientid, String caseid, String reason);
	
	/**
	 * 
	* @Title: getMediationCaseListByClientId 
	* @Description: 根据用户id获取列表
	* @param @param clientid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getMediationCaseListByClientId(String clientid, String casestate);
	
	/**
	 * 
	* @Title: getListByClientIdAndCaseType 
	* @Description: 根据员用户id以及分页索引查询列表  tabindex包括管理、进行中、待结案、待签署、历史
	* @param @param clientid
	* @param @param tabindex
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getListByClientIdAndCaseType(String clientid,Integer tabindex);
	
	
	/**
	 * 
	* @Title: gatherTempClient 
	* @Description: 召集被申请人
	* @param @param tempclientid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm gatherTempClient(String tempclientid);
	
	/**
	 * 
	* @Title: operateMediationForSign 
	* @Description: 操作状态为待签署
	* @param @param caseid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm operateMediationForSign(String caseid);
	
	/**
	 * 
	* @Title: getListForNormalClient 
	* @Description: 普通用户查询，包括自己申请的和被他人投诉的
	* @param @param clientid
	* @param @param casestate
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getListForNormalClient(String clientid,Integer tabindex);
	/**
	 * giveupMediation
	 * @Description: 放弃调节
	 * @param clientid
	 * @param caseid
	 * @return
	 */
	public ApiOutParamsVm giveupMediation(String clientid, String caseid);
	
	/**
	 * @Description: 转为线下调节
	 * @param clientid
	 * @param caseid
	 * @return
	 */
	public ApiOutParamsVm closeMediation(String clientid, String caseid, String failreason);
	
	/**
	 * completeMediationCase
	 * @Description: 结案
	 * @param clientid
	 * @param caseid
	 * @return
	 */
	public ApiOutParamsVm completeMediationCase(String caseid,String mediatetype, String casetype,
			boolean difficult, Integer people, String caseMoney,String protocolForm, String caseSource,Integer differenceSubject);
	
	/**
	 * 获取员状态列表
	 * @param clientid
	 * @param mediationAgencyId id
	 * @return
	 */
	public ApiOutParamsVm getCaseDistribution(String clientid,String startTime,String endTime,String mediationAgencyId);
	
	/**
	 * 
	* @Title: getBasicDateList 
	* @Description: 获取基础数据
	* @param @param datatype
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getBasicDateList(String datatype, String parentid);
	
	/**
	 * 
	* @Title: notifyClientToSignProtocolOffLine 
	* @Description: 通知当事人线下签署协议
	* @param @param caseid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm notifyClientToSignProtocolOffLine(String caseid);
	
	/**
	 * 
	* @Title: deleteMediationCaseById 
	* @Description: 删除
	* @param @param caseid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm deleteMediationCaseById(String caseid);
	
	/**
	 * 
	* @Title: searchMediationCaseByName  
	* @Description: 搜索关键字
	* @param @param casename
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm searchMediationCaseByName(String clientid,String casename);
	
	/**
	 * 
	* @Title: fileToZipByCaseId  
	* @Description: 一键导出申请书、协议书、笔录doc文档并压缩zip
	* @param @param caseid
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm fileToZipByCaseId(String caseid);
	
	/**
	* @Title: 查找用户信息
	* @param @param Client_id
	* @param @return    Client
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm getClient(String caseid);
	
}
