package com.szit.arbitrate.mediation.entity;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONArray;




public enum DifferentSubjects {
	 
	QSYTJAJS("企事业单位委员调解案件数","QSYTJAJS"),
	
	JDWYHTJAJS("街道委员会调解案件数", "JDWYHTJAJS");
	
	private String name;
	
	private String key;
	
	DifferentSubjects(String name, String key) {
		this.name = name;
		this.key = key;
	}
	
//	@Override
//	public String toString(){
//		return this.name;
//	}
	
	public static List<String> valuesToString(){
		 List<String> jsonArray = new ArrayList();
	        for (DifferentSubjects e : DifferentSubjects.values()) {
	            jsonArray.add(e.getName());
	        }
		return jsonArray;
	}

	public String getName() {
		return name;
	}

	public String getKey() {
		return key;
	}
	
}
