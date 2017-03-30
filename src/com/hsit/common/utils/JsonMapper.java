
package com.hsit.common.utils;


import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;

/**   
* @Title: JsonMapper.java
* @Package com.hsit.utils
* @Description: TODO
* @author XUJC 
* @date 2017年6月22日 下午4:20:36
* @version V1.0   
*/
public class JsonMapper {

	private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	private static ObjectMapper mapper;
	
	/**
	 * 单实例模式
	 */
	private static JsonMapper instance;

	
	public static synchronized JsonMapper getInstance() {
		if (instance == null) {
			instance = new JsonMapper(Include.ALWAYS);
		}
		return instance;
	}
	


	public JsonMapper(Include include) {
		mapper = new ObjectMapper();
		// 设置输出时包含属性的风格
		if (include != null) {
			mapper.setSerializationInclusion(include);
		}
		// 允许反斜杆等字符
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS,true);
		// 设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		//mapper.configure(SerializationFeature.INDENT_OUTPUT,true);  
	}

	/**
	 * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
	 */
	/*
	public static JsonMapper nonEmptyMapper() {
		return new JsonMapper(Include.NON_EMPTY);
	}
	*/

	/**
	 * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
	 */
	public static JsonMapper nonDefaultMapper() {
		return new JsonMapper(Include.NON_DEFAULT);
	}
	
	/**
	 * 
	* @Title: alwaysMapper 
	* @Description:默认
	* @param @return
	* @return JsonMapper 
	* @throws
	 */
	/*
	public  static JsonMapper alwaysMapper() {
		return new JsonMapper(Include.ALWAYS);
	}
	*/


	/**
	 * Object可以是POJO，也可以是Collection或数组。
	 * 如果对象为Null, 返回"null".
	 * 如果集合为空集合, 返回"[]".
	 */
	public String toJson(Object object) {

		try {
			return mapper.writeValueAsString(object);
		} catch (IOException e) {
			logger.warn("write to json string error:" + object, e);
			return null;
		}
	}

	/**
	 * 反序列化POJO或简单Collection如List<String>.
	 * 
	 * 如果JSON字符串为Null或"null"字符串, 返回Null.
	 * 如果JSON字符串为"[]", 返回空集合.
	 * 
	 * 如需反序列化复杂Collection如List<MyBean>, 请使用fromJson(String, JavaType)
	 * 
	 * @see #fromJson(String, JavaType)
	 */
	public <T> T fromJson(String jsonString, Class<T> clazz) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return mapper.readValue(jsonString, clazz);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}
	
	public Map<String, String> fromJson(String jsonString) throws JSONException{
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}
		JSONObject jsonObject = new JSONObject(jsonString);
		Map<String, String> resultMap = new HashMap<String, String>();
		Iterator iterator = jsonObject.keys();
		String key = null;
		String value = null;

		while (iterator.hasNext()) {

			key = (String) iterator.next();
			value = jsonObject.getString(key);
			resultMap.put(key, value);

		}
		return resultMap;
	}

	/**
	 * 反序列化复杂Collection如List<Bean>, 先使用createCollectionType()或contructMapType()构造类型, 然后调用本函数.
	 * 
	 * @see #createCollectionType(Class, Class...)
	 */
	public <T> T fromJson(String jsonString, JavaType javaType) {
		if (StringUtils.isEmpty(jsonString)) {
			return null;
		}

		try {
			return (T) mapper.readValue(jsonString, javaType);
		} catch (IOException e) {
			logger.warn("parse json string error:" + jsonString, e);
			return null;
		}
	}

	/**
	 * 构造Collection类型.
	 */
	public JavaType contructCollectionType(Class<? extends Collection> collectionClass, Class<?> elementClass) {
		return mapper.getTypeFactory().constructCollectionType(collectionClass, elementClass);
	}

	/**
	 * 构造Map类型.
	 */
	public JavaType contructMapType(Class<? extends Map> mapClass, Class<?> keyClass, Class<?> valueClass) {
		return mapper.getTypeFactory().constructMapType(mapClass, keyClass, valueClass);
	}

	/**
	 * 当JSON里只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
	 */
	public void update(String jsonString, Object object) {
		try {
			mapper.readerForUpdating(object).readValue(jsonString);
		} catch (JsonProcessingException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		} catch (IOException e) {
			logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
		}
	}

	/**
	 * 輸出JSONP格式數據.
	 */
	public String toJsonP(String functionName, Object object) {
		return toJson(new JSONPObject(functionName, object));
	}

	/**
	 * 設定是否使用Enum的toString函數來讀寫Enum,
	 * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
	 * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
	 */
	public void enableEnumUseToString() {
		mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
		mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
	}

	/**
	 * 支持使用Jaxb的Annotation，使得POJO上的annotation不用与Jackson耦合。
	 * 默认会先查找jaxb的annotation，如果找不到再找jackson的。
	 */
	public void enableJaxbAnnotation() {
		JaxbAnnotationModule module = new JaxbAnnotationModule();
		mapper.registerModule(module);
	}

	/**
	 * 取出Mapper做进一步的设置或使用其他序列化API.
	 */
	public ObjectMapper getMapper() {
		return mapper;
	}
	
	/**
	 * 
	* @Title: string2Json 
	* @Description: 转义处理
	* @param @param s
	* @param @return
	* @return String 
	* @throws
	 */
	public static String transferredJsonDispose(String s) {       
	    StringBuffer sb = new StringBuffer ();       
	    for (int i=0; i<s.length(); i++) {
	        char c = s.charAt(i);       
	        switch (c) {       
	        case '\"':       
	            sb.append("\\\"");       
	            break;       
	        case '\\':       
	            sb.append("\\\\");       
	            break;       
	        case '/':       
	            sb.append("\\/");       
	            break;       
	        case '\b':       
	            sb.append("\\b");       
	            break;       
	        case '\f':       
	            sb.append("\\f");       
	            break;       
	        case '\n':       
	            sb.append("\\n");       
	            break;       
	        case '\r':       
	            sb.append("\\r");       
	            break;       
	        case '\t':       
	            sb.append("\\t");       
	            break;       
	        default:       
	            sb.append(c);       
	     } 
	  }
	  return sb.toString();    
	}
	
	/*
	
	/**
	 * 
	* @Title: filter 
	* @Description: 过滤此属性
	* @param @param filterName
	* @param @param properties
	* @return void 
	* @throws
	 */
	/*
	public void filter(String filterName, String... properties) {  
		FilterProvider filterProvider = new SimpleFilterProvider().addFilter(filterName,  
				SimpleBeanPropertyFilter.serializeAllExcept(properties));  
		mapper.setFilters(filterProvider);  
	} 
	*/
	/**
	 * 
	* @Title: filterExcept 
	* @Description: 过滤不想要的
	* @param @param filterName
	* @param @param propertyes
	* @param @return
	* @return FilterProvider 
	* @throws
	 */
	public FilterProvider filterUnwanted(String filterName, String... propertyes) {
		// 过滤不想要的
		FilterProvider filter = new SimpleFilterProvider().addFilter(filterName,
				SimpleBeanPropertyFilter.serializeAllExcept(propertyes));
		return filter;
	}
	
	/**
	 * 
	* @Title: filterOutAllExcept 
	* @Description:// 过滤想要
	* @param @param filterName
	* @param @param propertyes
	* @param @return
	* @return FilterProvider 
	* @throws
	 */
	public FilterProvider filterWant(String filterName, String... propertyes) {
		 FilterProvider filter = new SimpleFilterProvider().addFilter(
		   filterName, SimpleBeanPropertyFilter.filterOutAllExcept(propertyes));
		return filter;
	}

	/**
	 * 
	* @Title: setFilter 
	* @Description: 设置过滤
	* @param @param filterProvider
	* @return void 
	* @throws
	 */
	public void setFilter(FilterProvider filterProvider){
		this.getMapper().setFilters(filterProvider);
	}
	
}
