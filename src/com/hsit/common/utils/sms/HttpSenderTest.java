package com.hsit.common.utils.sms;

/**   
*    
* 类名称：HttpSenderTest   
* 类描述：
* 事件记录：
* 创建人：Administrator  
* 创建时间：2017年
* 厦门西牛科技有限公司科技有限公司
* @version 1.0 
*    
*/
public class HttpSenderTest {
	public static void main(String[] args) {

		String url = "http://sms.253.com/msg/";// 应用地址
		String un = "N5634157";// 账号
		String pw = "a.123456";// 密码
		String phone = "13600920209";// 手机号码，多个号码使用","分割
		String msg = "【253云通讯】您好，你的验证码是123456";// 短信内容
		String rd = "1";// 是否需要状态报告，需要1，不需要0
		String ex = null;// 扩展码
		try {
			String returnString = HttpSender.batchSend(url, un, pw, phone, msg, rd, ex);
			System.out.println(returnString);
			// TODO 处理返回值,参见HTTP协议文档
		} catch (Exception e) {
			// TODO 处理异常
			e.printStackTrace();
		}
	}
}
