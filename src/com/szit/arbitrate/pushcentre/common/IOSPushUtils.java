/**   
* @Title: IOSPushUtils.java
* @Package com.hsit.common.pushcentre
* @Description: TODO
* @author XUJC 
* @date 2017年12月16日 下午8:24:51
* @version V1.0   
*/


package com.szit.arbitrate.pushcentre.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;

import com.hsit.common.utils.JsonMapper;
import com.jpush.api.JPushClient;
import com.jpush.api.push.PushResult;
import com.jpush.api.push.model.Message;
import com.jpush.api.push.model.Options;
import com.jpush.api.push.model.Platform;
import com.jpush.api.push.model.PushPayload;
import com.jpush.api.push.model.audience.Audience;
import com.jpush.api.push.model.notification.AndroidNotification;
import com.jpush.api.push.model.notification.IosNotification;
import com.jpush.api.push.model.notification.Notification;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import com.szit.arbitrate.pushcentre.exception.PushErrorException;
import com.szit.arbitrate.pushcentre.vm.IosClientTypeEnum;

import net.sf.json.JSONObject;
import net.sf.json.JSONString;



/**
 * @ProjectName:sizt-coupons
 * @ClassName: IOSPushUtils
 * @Description:
 * @author XUJC
 * @date 2017年12月16日 下午8:24:51
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */

public class IOSPushUtils {

	private Logger logger = LoggerFactory.getLogger(IOSPushUtils.class);
	
	private ApnConfig apnConfig;
	
	private  JPushClient meApnsService;//调解员端证书
	
	private  JPushClient usApnsService;//用户端证书
	
	private static IOSPushUtils instance;
	
	//测试环境
	private boolean hjstate = true;

	private IOSPushUtils() {
		
	}
	 
	//获取调解员端根证书
	private  JPushClient getMeApnsService(){
//		if(meApnsService == null){
//			try{
//				meApnsService =  APNS.newService()  
//					    .withCert(System.getProperty("arbitrateweb.root")+"/cers/HaiCangManager_dev.p12", "123456")  
//					    .withSandboxDestination()  
//					    .build();
//				
//			} catch (Exception e) {
//				logger.error("IOS证书初始化失败系统异常错误", e);
//				
//				throw new PushErrorException("IOS证书初始化失败系统异常错误",e);
//			}
//			meApnsService = new JPushClient("b1da09fdbf1c9bc00f6da36d", "c126305a366e0d5d4bf8875c");
//		}
		return new JPushClient("b1da09fdbf1c9bc00f6da36d", "c126305a366e0d5d4bf8875c");
	}
	
	//获取用户端根证书
	private  JPushClient getUsApnsService(){
//		if(usApnsService == null){
//			try{
//				usApnsService =  APNS.newService()  
//					    .withCert(System.getProperty("arbitrateweb.root")+"/cers/HaiCangUser_dev.p12", "123456")  
//					    .withSandboxDestination()  
//					    .build();  
//			} catch (Exception e) {
//				logger.error("IOS证书初始化失败系统异常错误", e);
//				
//				throw new PushErrorException("IOS证书初始化失败系统异常错误",e);
//			}
//			usApnsService = new JPushClient("140900a6a0934e4c435f16e4", "85083fede26be55e89ef9dd5");
//		}
		//return new JPushClient("140900a6a0934e4c435f16e4", "85083fede26be55e89ef9dd5");
		return new JPushClient("e0a649c2bf564842401e5fb4", "8e1c7d58eb91f91ee97af0a4");
	}
	
	public static synchronized IOSPushUtils getInstance() {
		if (instance == null) {
			instance = new IOSPushUtils();
		}
		return instance;
	}
	
	//调试---正是部署要设置false 调试为true
	private boolean isdebug=false;

	public boolean isIsdebug() {
		
		return isdebug;
		
	}

	public void setIsdebug(boolean isdebug) {
		this.isdebug = isdebug;
	}
	
	public  void pushUserMessage(String topicString, String iostoken, String alertmessage,Object customdata,int apnsCount,boolean isIosAndAndroid){
		logger.debug("==============IOS 消息推送  BEGIN=================");
//		System.out.println(topicString);
//		System.out.println("长度"+topicString.length()+customdata.toString().length()+alertmessage.length());
//		logger.debug("IOS 消息推送 token:{},alert消息:{}",topicString,alertmessage);
//		JSONObject obj = new JSONObject();
//		obj.put("customdata",customdata);
//		JSONObject aps = new JSONObject();
//		aps.put("alert",alertmessage);
//		aps.put("sound", "default");
//		aps.put("badge", apnsCount);
//		obj.put("aps", aps);
//		getUsApnsService().push(topicString, obj.toString());  
//		logger.debug("Apns Devices:{}",getUsApnsService().getInactiveDevices());
		JPushClient us = getUsApnsService();
//		Request Content - {"platform":["ios"],"audience":{"alias":["101d85590943116ab19"]},"notification":{"ios":{"alert":"syyyss","badge":"5","sound":"happy"}},"message":{"msg_content":"ssss:sss"},"options":{"sendno":1351184772,"apns_production":true}}
		try {
			if(isIosAndAndroid){
				PushResult msgResul = us.sendPush(PushPayload.newBuilder()
				        .setPlatform(Platform.ios())//平台是安卓与ios
				        .setNotification(Notification.newBuilder()
				                .addPlatformNotification(IosNotification.newBuilder()
				                        .setAlert(alertmessage)
				                        .setBadge(1)
				                        .setSound("default").addExtra("customdata",JSONObject.fromObject(customdata).toString())
				                        .build())
				                .build())
				         .setMessage(Message.content(JSONObject.fromObject(customdata).toString()))
				         .setOptions(Options.newBuilder()
				        		 //发布设置为true 
				                 .setApnsProduction(hjstate)
				                 .build())
				         .setAudience(Audience.alias(topicString))//推送目标
				         .build());
			}else{
				JSONObject obj =new JSONObject();
				obj.put("customdata", JSONObject.fromObject(customdata).toString());
				obj.put("alertmessage", alertmessage);
				PushResult msgResul = us.sendPush(PushPayload.newBuilder()
				        .setPlatform(Platform.android())//平台是安卓与ios
//				        .setNotification(Notification.android(alertmessage))
				         .setMessage(Message.content(JSONObject.fromObject(obj).toString()))
				         .setOptions(Options.newBuilder()
				        		 //发布设置为true 
				                 .setApnsProduction(hjstate)
				                 .build())
				         .setAudience(Audience.alias(topicString))//推送目标
				         .build());
			}
			
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
		}finally{
			us.close();
		}
		logger.debug("==============IOS 消息推送  END=================");
	}
	
	
	public  void pushMeMessage(String topicString, String iostoken, String alertmessage,Object customdata,int apnsCount,boolean isIosAndAndroid){
		logger.debug("==============IOS 消息推送  BEGIN=================");
//		logger.debug("IOS 消息推送 token:{},alert消息:{}",topicString,alertmessage);
//		JSONObject obj = new JSONObject();
//		obj.put("customdata",customdata);
//		JSONObject aps = new JSONObject();
//		aps.put("alert",alertmessage);
//		aps.put("sound", "default");
//		aps.put("badge", apnsCount);
//		obj.put("aps", aps);
//		getMeApnsService().push(topicString, obj.toString());  
//		logger.debug("Apns Devices:{}",getMeApnsService().getInactiveDevices());
		JPushClient ms = null;
		try {
			ms = getMeApnsService();
			if(isIosAndAndroid){
				 PushResult msgResul = ms.sendPush(PushPayload.newBuilder()
					        .setPlatform(Platform.ios())//平台是安卓与ios
					        .setNotification(Notification.newBuilder()
					                .addPlatformNotification(IosNotification.newBuilder()
					                        .setAlert(alertmessage)
					                        .setCategory("ios")
					                        .setContentAvailable(true)
					                        .setBadge(1)
					                        .setSound("default").addExtra("customdata",JSONObject.fromObject(customdata).toString())
					                        .build())
					                .build())
					         .setMessage(Message.content(JSONObject.fromObject(customdata).toString()))
					         .setOptions(Options.newBuilder()
					                 .setApnsProduction(hjstate)
					                 .build())
					         .setAudience(Audience.alias(topicString))//推送目标
					         .build());
			}else{
				JSONObject obj =new JSONObject();
				obj.put("customdata", JSONObject.fromObject(customdata).toString());
				obj.put("alertmessage", alertmessage);
				PushResult msgResul = ms.sendPush(PushPayload.newBuilder()
				        .setPlatform(Platform.android())//平台是安卓与ios
//				        .setNotification(Notification.android(alertmessage, "您有一条新消息", ma))
//				        .setNotification(Notification.newBuilder()
//				                .addPlatformNotification(AndroidNotification.newBuilder()
//				                        .setAlert(alertmessage)
//				                        .setCategory("CATEGORY_SOCIAL")
//				                        .setBigPicPath("path to big picture")
//				                        .setBigText("long text")
//				                        .setBuilderId(1)
//				                        .setCategory("CATEGORY_SOCIAL")
//				                        .setInbox(inbox)
//				                        .setStyle(1)
//				                        .setTitle("您有一条新消息")
//				                        .setPriority(1)
//				                        .build())
//				                .build())
				         .setMessage(Message.content(JSONObject.fromObject(obj).toString()))
				         .setOptions(Options.newBuilder()
				                 .setApnsProduction(hjstate)
				                 .build())
				         .setAudience(Audience.alias(topicString))//推送目标
				         .build());
			}
			
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
		}finally{
			ms.close();
		}
		logger.debug("==============IOS 消息推送  END=================");
	}
	
	
//	public synchronized void pushUserMessage(String topicString, String iostoken, String alertmessage,Object customdata,int apnsCount){
//		logger.debug("==============IOS 消息推送  BEGIN=================");
//		initApnService(iostoken);
//		logger.debug("IOS 消息推送 token:{},alert消息:{}",topicString,alertmessage);
//			if(StringUtils.isNotEmpty(alertmessage)&&customdata!=null){
//				
//				apnsService.push(topicString, 
//					     APNS.newPayload()
//						.alertBody(alertmessage)
//						.badge(apnsCount)
//						.sound("default")
//						//自定义数据
//						.customField("customdata", JsonMapper.getInstance().toJson(customdata))
//						.build());
//			}
//			else if(StringUtils.isNotEmpty(alertmessage)&&customdata==null){
//				apnsService.push(topicString, 
//					     APNS.newPayload()
//						.alertBody(alertmessage)
//						.badge(apnsCount)
//						.sound("default")
//						//.actionKey(message)
//						.build());
//			}
//			else if(StringUtils.isEmpty(alertmessage)&&customdata!=null){
//				apnsService.push(topicString, 
//					     APNS.newPayload()
//						.alertBody(alertmessage)
//						.badge(apnsCount)
//						.sound("default")
//						//自定义数据
//						.customField("customdata", JsonMapper.getInstance().toJson(customdata))
//						.build());
//			}
//
//			
//		}else{
//			throw new PushErrorException("证书服务器异常错误apnsService变量为NULL");
//		}
//		
//		logger.debug("==============IOS 消息推送  END=================");
//	}
	
	/**
	 * 根证书获取
	 */
//	public  void initApnService(String iostoken) throws PushErrorException{
//		if (apnConfig == null) {
//			if(isdebug){
//				if(iostoken.equals(IosClientTypeEnum.Normal.toString())){//用户端
//					apnConfig = new ApnConfig(PushSetting.devCertificationJunitTest,PushSetting.devCertPassword,PushSetting.prodCertificationJunitTest,PushSetting.prodCertPassword,false);
//				}else if(iostoken.equals(IosClientTypeEnum.Mediator.toString())){//调解员端
//					apnConfig = new ApnConfig(PushSetting.mediatorCertificationJunitTest,PushSetting.devCertPassword,PushSetting.mediatorprodCertificationJunitTest,PushSetting.prodCertPassword,false);
//				}
//			}else{
//				if(iostoken.equals(IosClientTypeEnum.Normal.toString())){//用户端
//					apnConfig = new ApnConfig(PushSetting.devCertification,PushSetting.devCertPassword,PushSetting.prodCertification,PushSetting.prodCertPassword,true);
//				}else if(iostoken.equals(IosClientTypeEnum.Mediator.toString())){//调解员端
//					apnConfig = new ApnConfig(PushSetting.mediatordevCertification,PushSetting.devCertPassword,PushSetting.mediatorprodCertification,PushSetting.prodCertPassword,true);
//				}
//			}
//		}
//		String apnCertificateName = apnConfig.getProdCertification();
//		String apnCertPassword = apnConfig.getProdCertPassword();
//		if (!apnConfig.isSendProductionMessage()) {
//			apnCertificateName = apnConfig.getDevCertification();
//			apnCertPassword = apnConfig.getDevCertPassword();
//		}
//		try {
//			//配置根证书
//			InputStream certFileStream = new FileInputStream(apnCertificateName);// SendExecutorMessageService.class.getResourceAsStream(apnCertificateName);
//			apnsService = APNS.newService()
//	                .withCert(certFileStream, apnCertPassword)
//	                .withAppleDestination(apnConfig.isSendProductionMessage())
//	                .build();
//	
//		} catch (Exception e) {
//			logger.error("IOS证书初始化失败系统异常错误", e);
//			
//			throw new PushErrorException("IOS证书初始化失败系统异常错误",e);
//		}
//	}
//
//	public ApnsService getApnsService() {
//		return apnsService;
//	}
//
//	public void setApnsService(ApnsService apnsService) {
//		this.apnsService = apnsService;
//	}
	
	
}
