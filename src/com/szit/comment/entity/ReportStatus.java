package com.szit.comment.entity;

public enum ReportStatus {
	/**
	 * 提交未处理
	 */
	Submit("未审核"),
	
	/**
	 * 已接收
	 */
	Accept("已审核未回复"),
	
	/**
	 * 已处理
	 */
	Dealed("已回复");
	
	private ReportStatus(String value){
		this.value = value;
	}
	
	private String value;

	public String getValue() {
		return value;
	}
}
