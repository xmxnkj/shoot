package com.szit.arbitrate.api.log.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;

/**
 * @ProjectName:xmszit-cowell-module-entity
 * @ClassName: PushRecordLog
 * @Description: 推送日志记录查询实体类
 * @author XUJC
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
public class PushRecordLogQuery  extends EntityQueryParam{
 private String pushmode;// 推送方式 ANDROID/IOS 
 private String pushkeycode;//推送KEY代码 
 private String pushcontent;//推送内容 
 private Integer apnscount;//记录数 
 private Date pushdatetime;//推送时间 

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
 public Date getPushdatetime(){
    return this.pushdatetime;
 }
 public void setPushdatetime(Date pushdatetime){
    this.pushdatetime = pushdatetime;
 }

}
