package com.szit.arbitrate.mediation.service;

import java.util.List;
import java.util.Map;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.api.mediation.bo.in.MediationProtocolInBo;
import com.szit.arbitrate.api.mediation.bo.in.TempClientInBo;
import com.szit.arbitrate.mediation.entity.MediationProtocol;
import com.szit.arbitrate.mediation.entity.query.MediationProtocolQuery;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: MediationProtocolService
* @Description:调解协议业务接口类
* @author yuyb
* @date 2017年3月23日 上午11:31:26
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface MediationProtocolService extends AppBaseService<MediationProtocol, MediationProtocolQuery>{

	/**
	 * 
	* @Title: createMediationProtocol 
	* @Description: 生成调解协议书
	* @param @param tempclientlist
	* @param @param protocolinbo
	* @param @param content
	* @param @param smscontent
	* @param @throws BizException
	* @param @throws ErrorException
	* @return MediationProtocol 
	* @throws
	 */
	public MediationProtocol createMediationProtocol(List<TempClientInBo> tempclientlist, 
			MediationProtocolInBo protocolinbo)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: notifyProtocolToClient 
	* @Description: 协议书通知当事人
	* @param @param protocolid
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void notifyProtocolToClient(String protocolid, String smscontent)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: operateMediationProtocol 
	* @Description: 用户同意或拒绝协议书
	* @param @param clientid
	* @param @param protocolid
	* @param @param type 0表示同意，1表示拒绝
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void operateMediationProtocol(String clientid, String protocolid, Integer type)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: downloadMediationProtocol 
	* @Description: 下载导出协议书doc文档
	* @param @param protocolid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return String 
	* @throws
	 */
	public Map<String, String> downloadMediationProtocol(String protocolid)throws BizException,ErrorException;
	
}
