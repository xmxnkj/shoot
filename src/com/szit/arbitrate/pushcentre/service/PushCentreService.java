/**   
* @Title: PushCentreService.java
* @Package com.szit.cowell.pushcentre.service
* @Description: TODO
* @author XUJC
* @date 2017年11月1日 下午9:18:49
* @version V1.0   
*/


package com.szit.arbitrate.pushcentre.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

/**
 * @ClassName: PushCentreService
 * @author XUJC
 * @date 2017年
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */

public interface PushCentreService extends BaseService<DomainEntity, EntityQueryParam> {




	/**
	 * 
	* 方法描述: 推送消息
	* 创建人: XUJC
	* 创建时间：2017年12月11日
	* @param pushtype 推送类型
	* @param sendClientId 发送会员ID
	* @param receiveClientId 接收会员ID
	* @param checkCode 检查CODE
	* @param pushAlertMessage 推送提示信息
	* @param pushCustomData 推送自定义信息对象
	* @param apnsCount 推送数
	* @throws BizException
	* @throws ErrorException
	 */
	public void pushUnifyMessage(PushTypeEnum pushtype,String sendClientId,
			                     boolean isSendPush,String receiveClientId,
			                     String checkCode,String pushAlertMessage,
			                     Object pushCustomData,int apnsCount)
			                    throws BizException,ErrorException;
	
	/**
	 * 
	* 方法描述:推送MQTT
	* 创建人: XUJC
	* 创建时间：2017年12月25日
	* @param pushtype
	* @param sendClientId
	* @param isSendPush
	* @param receiveClientId
	* @param checkCode
	* @param pushAlertMessage
	* @param pushCustomData
	* @param apnsCount
	* @throws BizException
	* @throws ErrorException
	 */
	public void pushMqtt(PushTypeEnum pushtype,String sendClientId,
            boolean isSendPush,String receiveClientId,
            String checkCode,String pushAlertMessage,
            Object pushCustomData,int apnsCount)throws BizException,ErrorException;
	
	/**
	 * 
	* 方法描述:MQTT提送
	* 创建人: Administrator
	* 创建时间：2017年12月29日
	* @param pushtype
	* @param sendClientId
	* @param pushTopic
	* @param pushAlertMessage
	* @param pushCustomData
	* @param apnsCount
	 */
	public void pushMqtt(PushTypeEnum pushtype,String sendClientId,
			             String pushTopic,String pushAlertMessage,
                         Object pushCustomData,int apnsCount);
}
