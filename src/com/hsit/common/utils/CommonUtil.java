package com.hsit.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

/**
 * @ProjectName:server@xm_airport
 * @ClassName: CommonUtil
 * @Description:通用工具类
 * @author XUJC
 * @date 2017年
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */

public class CommonUtil {
	
	public static Map<String, HttpSession> sessionMap = new HashMap<String, HttpSession>();
	

	public static final String getAccResourceUploadFolderPath(String catalog){
		return System.getProperty("arbitrateweb.root")+catalog;
	}
	
	/**
	 * 
	* 方法描述:国际化语言类型是否是英语
	* 创建人: Administrator
	* 创建时间：2017年2月4日
	* @return
	 */
	/*public static final boolean isLanguageTypeForEnglish(){
		LanguageTypeEnum languagetypeenum = HttpSessionContext.getSessionLanguageTypeEnum();
		if(languagetypeenum!=null){
		    if(languagetypeenum.equals(LanguageTypeEnum.english)){
		    	return true;
		    }else{
		    	return false;
		    }
		}else{
			return false;
		}
	}*/


	public static int getAge(Date birthDay) throws Exception { 
		//获取当前系统时间
		Calendar cal = Calendar.getInstance(); 
		//如果出生日期大于当前时间，则抛出异常
		if (cal.before(birthDay)) { 
			throw new IllegalArgumentException( 
					"The birthDay is before Now.It's unbelievable!"); 
		} 
		//取出系统当前时间的年、月、日部分
		int yearNow = cal.get(Calendar.YEAR); 
		int monthNow = cal.get(Calendar.MONTH); 
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); 

		cal.setTime(birthDay); 
		int yearBirth = cal.get(Calendar.YEAR); 
		int monthBirth = cal.get(Calendar.MONTH); 
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH); 
		int age = yearNow - yearBirth; 
		if (monthNow <= monthBirth) { 
			if (monthNow == monthBirth) { 
				if (dayOfMonthNow < dayOfMonthBirth) age--; 
			}else{ 
				age--; 
			} 
		} 
		return age; 
	}
	
	public static void main(String[] args) {

		try {
			int age = CommonUtil.getAge(DateUtils.parseToDate("1980-10", DateUtils.DATE_YMG_STR));
			System.out.println(age);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
