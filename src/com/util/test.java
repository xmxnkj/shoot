package com.util;

import java.util.HashMap;

import net.sf.json.JSONObject;

import org.junit.Test;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;

import com.jpush.api.JPushClient;
import com.jpush.api.push.PushResult;
import com.jpush.api.push.model.Message;
import com.jpush.api.push.model.Options;
import com.jpush.api.push.model.Platform;
import com.jpush.api.push.model.PushPayload;
import com.jpush.api.push.model.audience.Audience;
import com.jpush.api.push.model.notification.IosNotification;
import com.jpush.api.push.model.notification.Notification;

public class test {

	@Test
	public void t(){
//		JPushClient ms = new JPushClient("b1da09fdbf1c9bc00f6da36d", "c126305a366e0d5d4bf8875c");
		JPushClient ms = new JPushClient("140900a6a0934e4c435f16e4", "85083fede26be55e89ef9dd5");
//		Request Content - {"platform":["ios"],"audience":{"alias":["101d85590943116ab19"]},"notification":{"ios":{"alert":"syyyss","badge":"5","sound":"happy"}},"message":{"msg_content":"ssss:sss"},"options":{"sendno":1351184772,"apns_production":true}}
		try {
			HashMap<String,String> alert = new HashMap<String,String>();
			alert.put("title", "ddaadd");
			alert.put("body" , "sdddsaa");
			PushResult msgResul = ms.sendPush(PushPayload.newBuilder()
			        .setPlatform(Platform.ios())
//			        .setNotification(Notification.ios({""}))
			        .setNotification(Notification.newBuilder()
			        		
			                .addPlatformNotification(IosNotification.newBuilder()
			                		.setAlert("xxxx")
			                        .setBadge(1)
			                        .setContentAvailable(true)
			                        .setSound("default")
			                        .build())
			                .build())
			         .setMessage(Message.newBuilder().setTitle("sssyy").setMsgContent("xxxcc").build())
			         .setOptions(Options.newBuilder()
			        		 //证书没发布false 发布true
			                 .setApnsProduction(false)
			                 .build())
			         .setAudience(Audience.alias("171976fa8a84a68acd2"))//推送目标
			         .build());
//			PushResult msgResul = ms.sendPush(PushPayload.newBuilder()
//			        .setPlatform(Platform.ios())
//			        .setNotification(Notification.newBuilder()
//			                .addPlatformNotification(IosNotification.newBuilder()
//			                        .setAlert("syyyss")
//			                        .setBadge(5)
//			                        .setSound("happy")
//			                        .build())
//			                .build())
//			         .setMessage(Message.content("axb"))
//			         .setOptions(Options.newBuilder()
//			                 .setApnsProduction(true)
//			                 .build())
//			         .setAudience(Audience.alias("171976fa8a84a6d517c"))//推送目标
//			         .build());
		} catch (APIConnectionException | APIRequestException e) {
			e.printStackTrace();
		}finally{
			ms.close();
		}
	}
	
}
