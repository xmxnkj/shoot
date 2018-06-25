package com.szit.arbitrate.client.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;
import com.szit.arbitrate.client.entity.enumvo.ResTypeEnum;

/**
 * 
* @ClassName: ClientResource
* @Description:用户资源实体类
* @author Administrator
* @date 2017年3月21日 下午2:47:30
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class ClientResource extends DomainEntity{
	
	private String clientId;//用户id
	private ResTypeEnum resType;//资源类型
	private String resoriname;//资源原文件名 
	private String resuploadfileid;//资源上传的文件ID 
	private String resuploadfilepath;//资源上传的文件路径 
	private Date rescreatedatatime;//创建时间
	
	
	public String getResoriname() {
		return resoriname;
	}
	public void setResoriname(String resoriname) {
		this.resoriname = resoriname;
	}
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
	public String getResuploadfilepath() {
		return resuploadfilepath;
	}
	public void setResuploadfilepath(String resuploadfilepath) {
		this.resuploadfilepath = resuploadfilepath;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getRescreatedatatime() {
		return rescreatedatatime;
	}
	public void setRescreatedatatime(Date rescreatedatatime) {
		this.rescreatedatatime = rescreatedatatime;
	}

}
