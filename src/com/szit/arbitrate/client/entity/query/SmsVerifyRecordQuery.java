package com.szit.arbitrate.client.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.client.entity.enumvo.SmsBizModelEnum;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: SmsVerifyRecordQuery
* @Description:短信验证实体查询类
* @author yuyb
* @date 2017年3月17日 下午5:06:38
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class SmsVerifyRecordQuery extends EntityQueryParam{
	
	private SmsBizModelEnum bizmodel;//业务模式 
	private String recphone;//手机 
	private String sendcontent;//内容 
	private String smscheckcode;//验证码 
	private String smsapiresult;//SMS结果状态值 
	private Boolean sendstate;//发送成功否 
	private Date senddatetiem;//发送时间 
	
	public SmsBizModelEnum getBizmodel() {
		return bizmodel;
	}
	public void setBizmodel(SmsBizModelEnum bizmodel) {
		this.bizmodel = bizmodel;
	}
	public String getRecphone() {
		return recphone;
	}
	public void setRecphone(String recphone) {
		this.recphone = recphone;
	}
	
	public String getSendcontent() {
		return sendcontent;
	}
	public void setSendcontent(String sendcontent) {
		this.sendcontent = sendcontent;
	}
	public String getSmscheckcode() {
		return smscheckcode;
	}
	public void setSmscheckcode(String smscheckcode) {
		this.smscheckcode = smscheckcode;
	}
	public String getSmsapiresult() {
		return smsapiresult;
	}
	public void setSmsapiresult(String smsapiresult) {
		this.smsapiresult = smsapiresult;
	}
	public Boolean getSendstate() {
		return sendstate;
	}
	public void setSendstate(Boolean sendstate) {
		this.sendstate = sendstate;
	}
	public Date getSenddatetiem() {
		return senddatetiem;
	}
	public void setSenddatetiem(Date senddatetiem) {
		this.senddatetiem = senddatetiem;
	}

}
