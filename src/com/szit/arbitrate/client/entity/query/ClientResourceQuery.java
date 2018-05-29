package com.szit.arbitrate.client.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.client.entity.enumvo.ResTypeEnum;

public class ClientResourceQuery extends EntityQueryParam{
	
	private String clientId;//用户id
	private ResTypeEnum resType;//资源类型
	private String resuploadfileid;//资源上传的文件ID
	
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public ResTypeEnum getResType() {
		return resType;
	}
	public void setResType(ResTypeEnum resType) {
		this.resType = resType;
	}
	public String getResuploadfileid() {
		return resuploadfileid;
	}
	public void setResuploadfileid(String resuploadfileid) {
		this.resuploadfileid = resuploadfileid;
	}

}
