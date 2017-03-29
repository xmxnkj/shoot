package com.hsit.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**   
*    
* 类名称：MaskField   
* 类描述：
* 事件记录：
* 创建人：Administrator  
* 创建时间：2017年1月21日 上午11:56:24
* 厦门西牛科技有限公司科技有限公司
* @version 1.0 
*    
*/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@JacksonAnnotationsInside
@JsonSerialize(using = GlobalLanguageSerializer.class)
public @interface GlobalLanguage {
	String globalfield() default "";
}
