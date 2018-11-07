/**   
* @Title: DateTimeConverter.java
* @Package com.szit.cowell.api.common.utils
* @Description: TODO
* @author XUJC
* @date 2017年10月31日 下午1:36:16
* @version V1.0   
*/


package com.szit.arbitrate.api.common.utils;


import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Locale;
 
import org.apache.commons.beanutils.Converter;
 
/**
 * 
* @ClassName: DateTimeConverter
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:51:07
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@SuppressWarnings({ "rawtypes" })
public class DateTimeConverter implements Converter {
 
    private static final String DATE      = "yyyy-MM-dd";
    private static final String DATETIME  = "yyyy-MM-dd HH:mm:ss";
    private static final String TIMESTAMP = "yyyy-MM-dd HH:mm:ss.SSS";
 
    @Override
    public Object convert(Class type, Object value) {
        return toDate(type, value);
    }
 
    public static Object toDate(Class type, Object value) {
        if (value == null || "".equals(value))
            return null;
        if (value instanceof String) {
            String dateValue = value.toString().trim();
            int length = dateValue.length();
            if (type.equals(java.util.Date.class)) {
                try {
                    DateFormat formatter = null;
                    if (length <= 10) {
                        formatter = new SimpleDateFormat(DATE, new DateFormatSymbols(Locale.CHINA));
                        return formatter.parse(dateValue);
                    }
                    if (length <= 19) {
                        formatter = new SimpleDateFormat(DATETIME, new DateFormatSymbols(Locale.CHINA));
                        return formatter.parse(dateValue);
                    }
                    if (length <= 23) {
                        formatter = new SimpleDateFormat(TIMESTAMP, new DateFormatSymbols(Locale.CHINA));
                        return formatter.parse(dateValue);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }
}