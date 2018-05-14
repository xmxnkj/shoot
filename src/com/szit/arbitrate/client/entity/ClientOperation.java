package com.szit.arbitrate.client.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: Operation
* @Description:权限组操作实体类
* @author yuyb
* @date 2017年3月17日 下午3:52:09
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class ClientOperation extends DomainEntity{
	
	//操作名称
	private String operationName;
	//操作描述
	private String operationInfo;
	
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getOperationInfo() {
		return operationInfo;
	}
	public void setOperationInfo(String operationInfo) {
		this.operationInfo = operationInfo;
	}
	
}
