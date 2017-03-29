package com.hsit.common.annotation;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;


/**
 * 
*    
* 类名称：MaskFieldSerializer   
* 类描述：
* 事件记录：
* 创建人：Administrator  
* 创建时间：2017年1月21日 上午11:57:30
* 厦门西牛科技有限公司科技有限公司
* @version 1.0 
*
 */
public class GlobalLanguageSerializer extends JsonSerializer<Object> implements ContextualSerializer{
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String globalfield;

    //必须要保留无参构造方法
    public GlobalLanguageSerializer() {
        this("");
    }

    public GlobalLanguageSerializer(String globalfield) {
        this.globalfield = globalfield;
    }

	@Override
	public void serialize(Object value,JsonGenerator jgen,SerializerProvider provider) throws IOException {
		logger.debug("需要序列化的值:{}",value);
		logger.debug("globalfield的值:{}",globalfield);
		jgen.writeString("******");
	}


	@Override
	public JsonSerializer<?> createContextual(
			SerializerProvider serializerProvider, BeanProperty beanProperty)
			throws JsonMappingException {
		if (beanProperty != null) { // 为空直接跳过
			GlobalLanguage globalLanguage = beanProperty.getAnnotation(GlobalLanguage.class);
			if (globalLanguage == null) {
				globalLanguage = beanProperty.getContextAnnotation(GlobalLanguage.class);
			}
			if (globalLanguage != null) { // 如果能得到注解，就将注解的value传入ImageURLSerialize
				return new GlobalLanguageSerializer(globalLanguage.globalfield());
			}
			return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
		}
		return serializerProvider.findNullValueSerializer(beanProperty);
	}



	

}