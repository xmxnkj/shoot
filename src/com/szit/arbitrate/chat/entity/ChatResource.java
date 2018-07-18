package com.szit.arbitrate.chat.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.szit.arbitrate.chat.entity.enumvo.ContentTypeEnum;

/**
 * 
* @ProjectName:
* @ClassName: ChatResource
* @Description:聊天资源
* @author Administrator
* @date 2017年5月31日 上午10:56:28
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class ChatResource extends DomainEntity{
	
	private static final long serialVersionUID = -4930053467890261586L;
	
	private String clientId;//上传用户id
	private ContentTypeEnum resType;//资源类型
	private String oriFileName;//资源原文件名 
	private String resuploadfilename;//资源上传的文件ID 
	private String resuploadfilepath;//资源上传的文件路径 
	private Date rescreatedatatime;//创建时间
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public ContentTypeEnum getResType() {
		return resType;
	}
	public void setResType(ContentTypeEnum resType) {
		this.resType = resType;
	}
	public String getOriFileName() {
		return oriFileName;
	}
	public void setOriFileName(String oriFileName) {
		this.oriFileName = oriFileName;
	}
	public String getResuploadfilename() {
		return resuploadfilename;
	}
	public void setResuploadfilename(String resuploadfilename) {
		this.resuploadfilename = resuploadfilename;
	}
	public String getResuploadfilepath() {
		return resuploadfilepath;
	}
	public void setResuploadfilepath(String resuploadfilepath) {
		this.resuploadfilepath = resuploadfilepath;
	}
	public Date getRescreatedatatime() {
		return rescreatedatatime;
	}
	public void setRescreatedatatime(Date rescreatedatatime) {
		this.rescreatedatatime = rescreatedatatime;
	}
	
}
