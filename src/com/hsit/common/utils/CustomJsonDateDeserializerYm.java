package com.hsit.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.hp.hpl.sparta.ParseException;

/**   
 *    
 * 类名称：CustomJsonDateDeserializer   
 * 类描述：
 * 事件记录：
 * 创建人：XUJC  
 * 创建时间：2017年12月10日 下午6:31:48
 * 厦门西牛科技有限公司科技有限公司
 * @version 1.0 
 *    
 */
public class CustomJsonDateDeserializerYm extends JsonDeserializer<Date> {

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
                String date = jp.getText();
					try {
						return format.parse(date);
					} catch (java.text.ParseException e) {
						return null;
					}
	}

}