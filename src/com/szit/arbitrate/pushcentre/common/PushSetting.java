/**
 * 
 */
package com.szit.arbitrate.pushcentre.common;

/**
 * @ProjectName:server@xm_airport
 * @ClassName: PushSetting
 * @Description:
 * @author XUJC
 * @date 2017年7月8日 下午3:15:03
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */


public class PushSetting {
	
	
    //MQTT服务器IP端口
	public static String LOCALHOST_IP = "tcp://127.0.0.1:1883";	
//	public static String BROKER_IP = "tcp://39.108.184.195:3395";	
	//public static String BROKER_IP = "tcp://192.168.0.99:1883";
	
	//IOS推送服务器
	public final static String devCertification = System.getProperty("arbitrateweb.root")+"/cer/media_user_Dev_push.p12";
	public final static String mediatordevCertification = System.getProperty("arbitrateweb.root")+"/cer/mediator_push_dev.p12";
	public final static String devCertPassword="1";
	//正式地址
	public final static String prodCertification = System.getProperty("arbitrateweb.root")+"/cer/media_user_Dis_push.p12";
	public final static String mediatorprodCertification = System.getProperty("arbitrateweb.root")+"/cer/mediator_push_Dis.p12";
	public final static String prodCertPassword="1";
	
	
	//本地单元测试用
	public final static String devCertificationJunitTest = "d:\\cer\\media_user_Dev_push.p12";//用户端
	public final static String mediatorCertificationJunitTest = "d:\\cer\\mediator_push_dev.p12";//员端
	
	public final static String prodCertificationJunitTest = "d:\\cer\\media_user_Dis_push.p12";//用户端
	public final static String mediatorprodCertificationJunitTest = "d:\\cer\\mediator_push_Dis.p12";//员端
	
	
	//true 正式  false 测试
	public static boolean sendProductionMessage=true;
	
}
