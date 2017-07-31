package com.szit.arbitrate.pushcentre.common;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.UUID;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.hsit.common.utils.JsonMapper;




public class WSMQTTServerPubAsync {

	private Logger logger = LoggerFactory.getLogger(WSMQTTServerPubAsync.class);

	
	/**
	 * 单实例模式
	 */
	private static WSMQTTServerPubAsync instance;

	private WSMQTTServerPubAsync() {
		
	}

	public static synchronized WSMQTTServerPubAsync getInstance() {
		if (instance == null) {
			instance = new WSMQTTServerPubAsync();
		}
		return instance;
	}
	
	/**
	 * 
	* @Title: sendMessageToClient 
	* @Description: 
	* @param @param topicString
	* @param @param alertmessage
	* @param @param customdata
	* @param @throws MqttException
	* @return void 
	* @throws
	 */
	public void sendMessageToClient(String topicString, String alertmessage,Object customdata,boolean isIOSSync) throws MqttException {
		if(isIOSSync){
			Map<String,Object> pushMessage = Maps.newHashMap();
			pushMessage.put("alertmessage", alertmessage);
			pushMessage.put("customdata", customdata);
			sendMessageToClient(topicString,JsonMapper.getInstance().toJson(pushMessage));
		}
		else{
			sendMessageToClient(topicString,JsonMapper.getInstance().toJson(customdata));
		}
	}
	
	/**
	 * 向MQTT发送消息
	 * @param topicString
	 * @param content
	 * @throws MqttException
	 */
	private void sendMessageToClient(String topicString, String content) throws MqttException {
		logger.debug("=============Android推送BEGIN=====================");
		logger.debug("MQTT消息 topic:{},内容:{}",topicString,content);
		String topic        = topicString;
		/**
		 * At Most Once (QoS=0)
		 * At Least Once (QoS=1)
		 * Exactly Once (QoS=2)
		 */
        int qos             = 2;
        String broker       = PushSetting.LOCALHOST_IP;
        String clientId     = String.format("%-23.23s",System.getProperty("clientId", (UUID.randomUUID().toString())).trim()).replace('-', '_');
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            MqttClient sampleClient = new MqttClient(broker, clientId, persistence);           
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setCleanSession(true);
            sampleClient.connect(connOpts);
            MqttMessage message = new MqttMessage(content.getBytes(Charset.forName("UTF-8")));
            message.setQos(qos);
            sampleClient.publish(topic, message);
            if (sampleClient.isConnected()) {
            	 sampleClient.disconnect();
            }
    		logger.debug("=============Android推送END=====================");
            
        } catch(MqttException me) {
            logger.error("Android推送异常错误",me);
            throw new MqttException(me);
        }
	}
}
