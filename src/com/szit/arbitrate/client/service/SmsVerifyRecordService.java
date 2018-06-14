package com.szit.arbitrate.client.service;

import java.util.Map;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.client.entity.SmsVerifyRecord;
import com.szit.arbitrate.client.entity.enumvo.SmsBizModelEnum;
import com.szit.arbitrate.client.entity.query.SmsVerifyRecordQuery;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: SmsVerifyRecordService
* @Description:短信验证实体业务接口类
* @author yuyb
* @date 2017年3月17日 下午5:27:24
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface SmsVerifyRecordService extends BaseService<SmsVerifyRecord, SmsVerifyRecordQuery>{
	
	/**
	 * 
	* @Title: sendSmsMessage 
	* @Description: 发送普通通知短信
	* @param @param phone
	* @param @param smsBizModelEnum
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void sendSmsMessage(String phone,SmsBizModelEnum smsBizModelEnum, String smscontent, 
			String params)throws BizException,ErrorException;
	/**
	 * 
	* 方法描述:获取短信验证码
	* 创建人: Administrator
	* 创建时间：2017年12月31日
	* @return
	* @throws BizException
	* @throws ErrorException
	 */
	public Map<String,Object> toGetSmsCode(String phone,SmsBizModelEnum smsBizModelEnum)throws BizException,ErrorException;
	
	/**
	 * 
	* 方法描述:验证短信是否正确
	* 创建人: Administrator
	* 创建时间：2017年12月31日
	* @param phone
	* @param smscode
	* @return
	* @throws BizException
	* @throws ErrorException
	 */
	public Boolean verifySmsCode(String phone,String smscode)throws BizException,ErrorException;

}
