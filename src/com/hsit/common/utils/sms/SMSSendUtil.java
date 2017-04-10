package com.hsit.common.utils.sms;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.utils.JsonMapper;
import com.szit.neteasecloud.config.NeteaseCloudConfig;
import com.szit.neteasecloud.utils.CheckSumBuilder;

/**   
*    
* 类名称：SMSSendUtil   
* 类描述：短信发送工具类
* 事件记录：
* 创建人：Administrator  
* 创建时间：2017年
* 厦门西牛科技有限公司科技有限公司
* @version 1.0 
*    
*/
public class SMSSendUtil {
   
	protected  Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final static String smsurl = "http://sms.253.com/msg/";// 应用地址
	private final static String smsun = "N5634157";// 账号
	private final static String smspw = "hkvJEXzftid290";// 密码(目前不知道问下杨总)
	
	private final static String appKey = NeteaseCloudConfig.appKey;
	private final static String appSecret = NeteaseCloudConfig.appSecret;
    
	
	
	/**
	 * 单实例模式
	 */
	private static SMSSendUtil instance;

	private SMSSendUtil() {
		
	}

	public static synchronized SMSSendUtil getInstance() {
		if (instance == null) {
			instance = new SMSSendUtil();
		}
		return instance;
	}
	
	/**
	 * 
	* 方法描述:发送短信
	* 创建人: Administrator
	* 创建时间：2017年12月31日
	* @param sendPhone
	* @return
	* @throws ErrorException
	 */
	public Map<String,String> sendSMS(String sendPhone,String sendMsg)throws BizException, ErrorException{
		
		String phone = sendPhone;// 手机号码，多个号码使用","分割
		String msg = sendMsg;// 短信内容
		String rd = "1";// 是否需要状态报告，需要1，不需要0
		String ex = null;// 扩展码
		try {
			String returnString = HttpSender.batchSend(smsurl, smsun, smspw, phone, msg, rd, ex);
		    logger.debug("SMS返回值:{}",returnString);
			String[] resultArrays = returnString.split(",");
		    Map<String,String> resultMap = Maps.newHashMap();
		    String smsresult = resultArrays[1];
		    String[] result = smsresult.split("\n");
		    if(result.length==2){
		        resultMap.put("smsresultcode", result[0]);
			    resultMap.put("smsmessageid", result[1]);
		    }
		    if(result.length==1){
		    	resultMap.put("smsresultcode", result[0]);
		        resultMap.put("smsmessageid", "");
		    }
		    return resultMap;
		} catch (Exception e) {
			throw new ErrorException(e);
		}
	}
	
	/**
	 * 
	* @Title: sendSmsMessage 
	* @Description: 网易云信-发送短信
	* @param @param sendPhone
	* @param @param url
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return Map<String,String> 
	* @throws
	 */
	public Map<String,String> sendSmsMessage(String url, List<NameValuePair> nvps)throws BizException, ErrorException{
		
		String curTime = String.valueOf((new Date()).getTime() / 1000L);
	    String strRandom = CheckSumBuilder.buildRandom(4) + "";
	    String nonce = curTime.substring(8, curTime.length())+strRandom;
	    String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码
	    
	    CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		
		// 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        
        
        try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

			// 执行请求
			CloseableHttpResponse response = httpclient.execute(httpPost);
			String result = EntityUtils.toString(response.getEntity(), "GBK");
			response.close();

			// 打印执行结果
			logger.debug("SMS返回值:{}",result);
			Map<String, String> resultMap = JsonMapper.getInstance().fromJson(result);
			
			return resultMap;
		} catch (ParseException | IOException | JSONException e) {
			throw new BizException(e);
		}
	    
	}
	
	public static void main(String[] args) {
		 Map<String,String> resultMap =SMSSendUtil.getInstance().sendSMS("15959285933", "您的验证码:[123456]");
	     System.out.println(resultMap);
	}
	
	
}
