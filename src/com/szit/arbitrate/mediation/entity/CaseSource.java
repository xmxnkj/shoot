//package com.szit.arbitrate.mediation.entity;
//
//import com.admin.jdbc.SqlWhereType;
//
//public enum CaseSource {
//	
//	XFWTYS("信访部门委托移送","XFWTYS"),
//	
//	FYWTYS("法院委托移送", "FYWTYS"),
//	
//	ZDTJ("主动", "FYWTYS"),
//	
//	GAJGWTYS("公安机关委托移送", "FYWTYS");
//	
//	private String name;
//	
//	private String key;
//	
//	CaseSource(String name, String key) {
//		this.name = name;
//		this.key = key;
//	}
//	
//	public static SqlWhereType getKey(String key) {
//		SqlWhereType s = Enum.valueOf(SqlWhereType.class,key);
//		return s;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public String getKey() {
//		return key;
//	}
//	
//}
