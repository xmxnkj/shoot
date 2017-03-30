package com.hsit.common.utils;

import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;

/**   
*    
* 类名称：CustomMySQLDialect   
* 类描述：
* 事件记录：
* 创建人：Administrator  
* 创建时间：2017年1月18日 下午5:55:31
* 厦门西牛科技有限公司科技有限公司
* @version 1.0 
*    
*/
public class CustomMySQLDialect extends MySQL5Dialect {
	public CustomMySQLDialect(){ 
		super(); 
		//registerFunction( "", new StandardSQLFunction("",StandardBasicTypes.STRING) );
	} 
}
