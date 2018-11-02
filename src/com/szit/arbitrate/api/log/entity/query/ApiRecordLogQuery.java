package com.szit.arbitrate.api.log.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;

/**
 * @ProjectName:xmszit-cowell-module-entity
 * @ClassName: ApiRecordLog
 * @Description: API接口日志查询实体类
 * @author XUJC
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
public class ApiRecordLogQuery  extends EntityQueryParam{
 private String modulecode;//模块代码 
 private String bizcode;//业务代码 
 private String bizmethod;//业务方法 
 private String inbo;//输入业务对象 
 private String outbo;//输出业务对象 
 private String errorcode;//错误代码 
 private String errormessage;//错误信息 
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
 public Date getCreatetime(){
    return this.createtime;
 }
 public void setCreatetime(Date createtime){
    this.createtime = createtime;
 }

}
