package com.admin.jdbc.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
/**
 * 一对一 的关系配置  一对一默认使用左关联
 * @author Administrator
 *
 */
public @interface OTO {
	
	/**
	 * 该注释为  指定默认的关联关系
	 */
	String associated() default "left join";
	
	/**
	 * 该注释为  指定该实体用于关联的查询的字段
	 */
	String assField() default "id";
	
	/**
	 * 该注释为  指定该实体用于关联的查询的字段是否为当前字段
	 * 默认是
	 */
	boolean heirOwn() default true;

	/**
	 * 该注释为  指定关联的实体用于关联的查询的字段
	 */
	String pointAssField() default "id";
	
}
