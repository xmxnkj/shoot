/**
 * 
 */
package com.hsit.common.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


/**
 * @author XUJC
 * 日期工具类
 */
public class DateUtils {
	
	public static final String DATE_YM_STR="yyyyMM";
	public static final String DATE_YMDHMS_STR = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_YMD_STR = "yyyy-MM-dd";
	public static final String DATE_YMDHMS_SORT_STR = "yyMMddHHmmss";
	public static final String DATE_MM_STR="MM";
	public static final String DATE_YYYY_STR="yyyy";
	public static final String DATE_YMDHM_STR = "yyyy-MM-dd HH:mm";
	public static final String DATE_YMDHMSS_SORT_STR = "yyyyMMddHHmmssSS";
	public static final String DATE_MD_XG="MM-dd";
	public static final String DATE_HM_XG="HH:mm";
	public static final String DATE_YMDHMSS_STR_CC = "yyyy-MM-dd HH:mm:ss:SS";
	public static final String DATE_YMDHMSS_STR_DCC = "yyyy-MM-dd HH:mm:ss.SS";
	public static final String DATE_YMDHMSS_STR_TZ = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"; 
	public static final String DATE_YYMDHM_STR = "yy-MM-dd HH:mm";
	public static final String DATE_YYYYMDHM = "yyyy-MM-dd";
	public static final String DATE_YMG_STR = "yyyy-MM";
	
	public static final String DATE_MD_CHINA = "MM月dd日";
	
	public static final String DATE_YMDHMS_YYMMDDHHMM_STR = "yyMMddHHmm";
	
	//2017-12-19T13:00:07Z
	public static final String DATE_YMDHMS_YYYYMMDDHHMM_STR_T = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	
	
	
	public static String getDateNowToString(String pattern){
		return parseToString(new Date(),pattern);
	}
	
	/**
	 * 
	* 方法描述:相减分钟数
	* 创建人: XUJC
	* 创建时间：2017年12月19日
	* @param begindt
	* @param enddt
	* @return
	 */
	public static long diffToMinutes(Date begindt,Date enddt){
		long diff = begindt.getTime() - enddt.getTime();//这样得到的差值是微秒级别  
		long minutes = diff/(1000* 60);;
		return minutes;
	}

	
	public static Date parseToDateForGMT(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			df.setTimeZone(TimeZone.getTimeZone("GMT"));
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date parseToDate(String strDate, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			return df.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	* @Title: parseToString 
	* @Description: 格式化
	* @param @param date
	* @param @param pattern
	* @param @return
	* @return String 
	* @throws
	 */
	public static String parseToString(Date date, String pattern) {
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		try {
			if(date!=null){
				return df.format(date);
			}else{
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 
	* @Title: addDateOneDay 
	* @Description: 日期添加 c.add(Calendar.DATE, -1); //日期减1天  c.add(Calendar.DATE, 1); //日期加1天
	* @param @param date
	* @param @param addNum
	* @param @return
	* @return Date 
	* @throws
	 */
	public static Date addDate(Date date,int addNum) {
		if (null == date) {
			return date;
		}
		Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE,addNum);
        date = c.getTime();
        return date;
	}

	public static String timestampParseToString(Timestamp ts, String pattern){
		DateFormat sdf = new SimpleDateFormat(pattern);
		try {
			//方法一
			String tsStr = sdf.format(ts);
            return tsStr;
		} catch (Exception e) {
			return "";
		}
	}
	
	/**
	 * 
	* @Title: compareDate 
	* @Description: 日期对比
	* @param @param date1
	* @param @param date2
	* @param @param pattern
	* @param @return dt1 在dt2前==1 dt1在dt2后==-1
	* @param @throws WapErrorException
	* @return int 
	* @throws
	 */
    public static int compareDate(String date1, String date2,String pattern)throws Exception {
		DateFormat df = new SimpleDateFormat(pattern);
		try {
			Date dt1 = df.parse(date1);
			Date dt2 = df.parse(date2);
			if (dt1.getTime() > dt2.getTime()) {
				//dt1 在dt2前
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				//dt1在dt2后
				return -1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			throw e;
		}
    }
	
	
	
}
