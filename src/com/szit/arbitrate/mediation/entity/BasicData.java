package com.szit.arbitrate.mediation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: BasicData
* @Description:基础数据实体类
* @author Administrator
* @date 2017年5月24日 下午4:19:27
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class BasicData extends DomainEntity{

	private static final long serialVersionUID = 3536182422829383596L;
	
	//private String litigationType;//诉讼类型 Civil民事
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
	public String getDataTypeDesc() {
		return dataTypeDesc;
	}
	public void setDataTypeDesc(String dataTypeDesc) {
		this.dataTypeDesc = dataTypeDesc;
	}
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

}
