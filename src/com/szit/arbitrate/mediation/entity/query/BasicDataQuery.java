package com.szit.arbitrate.mediation.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;

public class BasicDataQuery extends EntityQueryParam{
	
	private static final long serialVersionUID = -7104521655695089678L;

	//private String litigationType;//诉讼类型
	private String dataType;//数据类型
	private String dataTypeDesc;//类型说明
	private String parentType;//父类型
	private String dataValue;//数据值
	
	/*public String getLitigationType() {
		return litigationType;
	}
	public void setLitigationType(String litigationType) {
		this.litigationType = litigationType;
	}*/
	
	public String getDataType() {
		return dataType;
	}
	public String getDataTypeDesc() {
		return dataTypeDesc;
	}
	public void setDataTypeDesc(String dataTypeDesc) {
		this.dataTypeDesc = dataTypeDesc;
	}
	public String getParentType() {
		return parentType;
	}
	public void setParentType(String parentType) {
		this.parentType = parentType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataValue() {
		return dataValue;
	}
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
	
}
