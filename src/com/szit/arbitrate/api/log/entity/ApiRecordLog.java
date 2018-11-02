package com.szit.arbitrate.api.log.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: ApiRecordLog
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:09:22
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class ApiRecordLog  extends DomainEntity{
	
	private String modulecode;//模块代码 
	private String bizcode;//业务代码 
	private String bizmethod;//业务方法 
    private String loginuserid;//登陆用户ID
	private String inbo;//输入业务对象 
	private String formatinbo;//格式化输入业务对象查看 
	private String outbo;//输出业务对象 
	private String errorflag;//是否异常错误
	private String errorcode;//错误代码 
	private String errormessage;//错误信息 
	private String errorstack;//错误堆栈 
	private Date createtime;// 

	public String getModulecode(){
		return this.modulecode;
	}
	public void setModulecode(String modulecode){
		this.modulecode = modulecode;
	}
	public String getBizcode(){
		return this.bizcode;
	}
	public void setBizcode(String bizcode){
		this.bizcode = bizcode;
	}
	public String getBizmethod(){
		return this.bizmethod;
	}
	public void setBizmethod(String bizmethod){
		this.bizmethod = bizmethod;
	}
	public String getInbo(){
		return this.inbo;
	}
	public void setInbo(String inbo){
		this.inbo = inbo;
	}
	public String getFormatinbo(){
		return this.formatinbo;
	}
	public void setFormatinbo(String formatinbo){
		this.formatinbo = formatinbo;
	}
	public String getOutbo(){
		return this.outbo;
	}
	public void setOutbo(String outbo){
		this.outbo = outbo;
	}
	public String getErrorcode(){
		return this.errorcode;
	}
	public void setErrorcode(String errorcode){
		this.errorcode = errorcode;
	}
	public String getErrormessage(){
		return this.errormessage;
	}
	public void setErrormessage(String errormessage){
		this.errormessage = errormessage;
	}
	public String getErrorstack(){
		return this.errorstack;
	}
	public void setErrorstack(String errorstack){
		this.errorstack = errorstack;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getCreatetime(){
		return this.createtime;
	}
	public void setCreatetime(Date createtime){
		this.createtime = createtime;
	}

	public String getErrorflag() {
		return errorflag;
	}
	public void setErrorflag(String errorflag) {
		this.errorflag = errorflag;
	}
	public String getLoginuserid() {
		return loginuserid;
	}
	public void setLoginuserid(String loginuserid) {
		this.loginuserid = loginuserid;
	}

	
}
