package com.szit.arbitrate.api.log.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

/**
 * @ProjectName:xmszit-cowell-module-entity
 * @ClassName: PushRecordLog
 * @Description: 推送日志记录实体类
 * @author XUJC
 * @date
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
public class PushRecordLog  extends DomainEntity{

	private PushTypeEnum pushtype;//推送类型
	private String pushmode;// 推送方式 ANDROID/IOS 
	private String pushkeycode;//推送KEY代码 
	private String pushcontent;//推送内容 
	private Integer apnscount;//记录数 
	private Date pushdatetime;//推送时间 
	private String remark;//备注
    
	public String getPushmode(){
		return this.pushmode;
	}
	public void setPushmode(String pushmode){
		this.pushmode = pushmode;
	}
	public String getPushkeycode(){
		return this.pushkeycode;
	}
	public void setPushkeycode(String pushkeycode){
		this.pushkeycode = pushkeycode;
	}
	public String getPushcontent(){
		return this.pushcontent;
	}
	public void setPushcontent(String pushcontent){
		this.pushcontent = pushcontent;
	}
	public Integer getApnscount(){
		return this.apnscount;
	}
	public void setApnscount(Integer apnscount){
		this.apnscount = apnscount;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getPushdatetime(){
		return this.pushdatetime;
	}
	public void setPushdatetime(Date pushdatetime){
		this.pushdatetime = pushdatetime;
	}
	public PushTypeEnum getPushtype() {
		return pushtype;
	}
	public void setPushtype(PushTypeEnum pushtype) {
		this.pushtype = pushtype;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	
}
