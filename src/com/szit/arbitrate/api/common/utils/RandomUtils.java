/**   
* @Title: RandomUtils.java
* @Package com.szit.cowell.api.common.utils
* @Description: TODO
* @author XUJC
* @date 2017年10月30日 上午11:32:04
* @version V1.0   
*/


package com.szit.arbitrate.api.common.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 
* @ProjectName:
* @ClassName: RandomUtils
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:52:26
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public class RandomUtils {

	
	public static String getRandomString(String head,int length) { //length表示生成字符串的长度  
	    String base = "0123456789";     
	    Random random = new Random();     
	    StringBuffer sb = new StringBuffer();     
	    for (int i = 0; i < length; i++) {     
	        int number = random.nextInt(base.length());     
	        sb.append(base.charAt(number));     
	    }     
	    return head+"_"+sb.toString();     
	 } 
	
	/** 
     * 生产一个指定长度的随机字符串 
     * @param length 字符串长度 
     * @return 
     */  
    public static String generateRandomString(int length) {  
    	String POSSIBLE_CHARS = "0123456789";
        StringBuilder sb = new StringBuilder(length);  
        SecureRandom random = new SecureRandom();  
        for (int i = 0; i < length; i++) {  
            sb.append(POSSIBLE_CHARS.charAt(random.nextInt(POSSIBLE_CHARS.length())));  
        }  
        return sb.toString();  
    } 
	
}
