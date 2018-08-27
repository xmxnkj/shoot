package com.szit.arbitrate.basisset.service;

import java.util.List;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.basisset.entity.ClientBasisParameterSetting;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;
import com.szit.arbitrate.basisset.entity.query.ClientBasisParameterSettingQuery;

/**
 * @ProjectName:xmszit-cowell-module-broadcast
 * @ClassName:ClientBasisParameterSettingService
 * @Description:基础参数设置业务逻辑接口类
 * @author XUJC
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */

public interface ClientBasisParameterSettingService extends BaseService<ClientBasisParameterSetting, ClientBasisParameterSettingQuery> {
	/**
	 * 
	* @Title: setting 
	* @Description: 
	* @param @param parametertypenum
	* @param @param parameterid
	* @param @param parameterval
	* @param @param clientId
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @throws
	 */
	public void setting(ParameterTypeEnum parametertypenum,String parameterid,String parameterval,String clientId)throws BizException,ErrorException;

	/**
	 * 
	* @Title: mySettingList 
	* @Description: 取得我的设置列表
	* @param @param clientId
	* @param @param parametertypenum
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return List<ClientBasisParameterSetting> 
	* @throws
	 */
	public List<ClientBasisParameterSetting> mySettingList(String clientId,ParameterTypeEnum parametertypenum)throws BizException,ErrorException;
	/**
	 * 
	* 方法描述:初始化会员数据
	* 创建人: Administrator
	* 创建时间：2017年1月11日
	* @param clientId
	* @throws BizException
	* @throws ErrorException
	 */
	public void initClinetData(String clientId)throws BizException,ErrorException;
	
}