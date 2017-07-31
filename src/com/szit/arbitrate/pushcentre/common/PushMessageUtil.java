/**
 * 
 */
package com.szit.arbitrate.pushcentre.common;


import org.apache.commons.lang.StringUtils;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.szit.arbitrate.pushcentre.exception.PushErrorException;
import com.szit.arbitrate.pushcentre.vm.IosClientTypeEnum;




/**
 * @ProjectName:server@xm_airport
 * @ClassName: PushMessageUtil
 * @Description:推送工具类
 * @author XUJC
 * @date 2017年7月8日 下午3:11:02
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */

public class PushMessageUtil {

	private Logger logger = LoggerFactory.getLogger(PushMessageUtil.class);
	
	
	//调试---正是部署要设置false 调试为true
	private boolean isdebug = false;
	
	/**
	 * 单实例模式
	 */
	private static PushMessageUtil instance;

	private PushMessageUtil() {
		
	}

	public static synchronized PushMessageUtil getInstance() {
		if (instance == null) {
			instance = new PushMessageUtil();
		}
		return instance;
	}
	
	/**
	 * 判断是否是推送消息或是发送消息
	 * @param topicString
	 * @param content
	 * @param online
	 * @throws MqttException 
	 */
	public void  mqttOrApnsSendMessage(String topicString,String alertmessage,Object customdata,String iostoken,int apnsCount,boolean isIOSSync) {
		try {
			String[] andorios = null;
			if(iostoken!=null){
				andorios = iostoken.split("_");
			}
			//如果andorios>1使用新版本
			if(andorios.length>1){
				if ("IOS".equals(andorios[1])){
					//ios手机推送 json格式解析 apns推送消息
					//IOSPushUtils.getInstance().setIsdebug(isIsdebug());
					if(andorios[0].equals(IosClientTypeEnum.Normal.toString())){//用户端
						IOSPushUtils.getInstance().pushUserMessage(topicString, andorios[0], alertmessage, customdata, apnsCount,true);
					}else if(andorios[0].equals(IosClientTypeEnum.Mediator.toString())){//调解员端
						IOSPushUtils.getInstance().pushMeMessage(topicString, andorios[0], alertmessage, customdata, apnsCount,true);
					}
				} else if("ANDROID".equals(andorios[1])){//android推送
					if(andorios[0].equals(IosClientTypeEnum.Normal.toString())){//用户端
						IOSPushUtils.getInstance().pushUserMessage(topicString, andorios[0], alertmessage, customdata, apnsCount,false);
					}else if(andorios[0].equals(IosClientTypeEnum.Mediator.toString())){//调解员端
						IOSPushUtils.getInstance().pushMeMessage(topicString, andorios[0], alertmessage, customdata, apnsCount,false);
					}
				}
			}else{
				if (StringUtils.isNotEmpty(iostoken)){
					//ios手机推送 json格式解析 apns推送消息
					//IOSPushUtils.getInstance().setIsdebug(isIsdebug());
					if(iostoken.equals(IosClientTypeEnum.Normal.toString())){//用户端
						IOSPushUtils.getInstance().pushUserMessage(topicString, iostoken, alertmessage, customdata, apnsCount,true);
					}else if(iostoken.equals(IosClientTypeEnum.Mediator.toString())){//调解员端
						IOSPushUtils.getInstance().pushMeMessage(topicString, iostoken, alertmessage, customdata, apnsCount,true);
					}
					
				} else {//android推送
					//在线用mqtt发送
					try {
						WSMQTTServerPubAsync.getInstance().sendMessageToClient(topicString,alertmessage,customdata,isIOSSync);
					} catch (MqttException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		} catch (PushErrorException e) {
			logger.error("PushErrorException异常错误",e);
			throw new PushErrorException("推送异常错误",e);
		} 
//		catch (MqttException e) {
//			logger.error("MqttException 异常错误",e);
//			throw new PushErrorException("推送异常错误",e);
//		}
	}
	
	/**
	 * 
	* 方法描述:推送消息
	* 创建人: XUJC
	* 创建时间：2017年12月11日
	* @param topicString
	* @param alertmessage
	* @param customdata
	* @param iostoken
	* @param apnsCount
	* @throws PushErrorException
	 */
	public void  mqttOrApnsSendMessage(String topicString,String alertmessage,Object customdata,String iostoken,int apnsCount) throws PushErrorException {
          this.mqttOrApnsSendMessage(topicString, alertmessage, customdata, iostoken, apnsCount,true);
	}
	

	public boolean isIsdebug() {
		return isdebug;
	}

	public void setIsdebug(boolean isdebug) {
		this.isdebug = isdebug;
	}
	
	
	
}
