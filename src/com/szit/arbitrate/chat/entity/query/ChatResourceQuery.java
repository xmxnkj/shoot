package com.szit.arbitrate.chat.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.mediation.entity.enumvo.CaseResTypeEnum;

public class ChatResourceQuery extends EntityQueryParam{
	
	private String clientId;//上传用户id
	private CaseResTypeEnum resType;//资源类型
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
	public CaseResTypeEnum getResType() {
		return resType;
	}
	public void setResType(CaseResTypeEnum resType) {
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
