package com.szit.arbitrate.client.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.client.entity.enumvo.CertificateStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: ClientQuery
* @Description:用户查询类
* @author yuyb
* @date 2017年3月17日 下午5:04:00
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class ClientQuery extends EntityQueryParam{
	
	//昵称
	private String nickName;
	//账号
	private String account;
	private String passwd;
	
	//会员类型
	private ClientTypeEnum clientType;
	
	private String mediationAgencyId;//属于哪个调解机构
	//第三方登录ID
	private String thirdPartyId;
	//第三方登陆类型
	private ThirdPartyEnum thirdParty;
	
	//身份证信息
	private String identifyName;//姓名
	private String identify;//身份证号
	//电话
	private String tel;
	private ClientStateEnum clientState;
	
	private CertificateStateEnum auditInfo;//认证审核状态

	private Integer mediatorType;	//调解员类型   1 行政 2司法 3人民
	
	private Integer showDisplay;	//排序
	
	private Date resgitTimeStart;
	
	private Date resgitTimeEnd;
	
	public Date getResgitTimeStart() {
		return resgitTimeStart;
	}
	public void setResgitTimeStart(Date resgitTimeStart) {
		this.resgitTimeStart = resgitTimeStart;
	}
	public Date getResgitTimeEnd() {
		return resgitTimeEnd;
	}
	public void setResgitTimeEnd(Date resgitTimeEnd) {
		this.resgitTimeEnd = resgitTimeEnd;
	}
	public Integer getMediatorType() {
		return mediatorType;
	}
	public void setMediatorType(Integer mediatorType) {
		this.mediatorType = mediatorType;
	}
	public CertificateStateEnum getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(CertificateStateEnum auditInfo) {
		this.auditInfo = auditInfo;
	}
	public ClientStateEnum getClientState() {
		return clientState;
	}
	public void setClientState(ClientStateEnum clientState) {
		this.clientState = clientState;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	public ClientTypeEnum getClientType() {
		return clientType;
	}
	public void setClientType(ClientTypeEnum clientType) {
		this.clientType = clientType;
	}
	
	public String getMediationAgencyId() {
		return mediationAgencyId;
	}
	public void setMediationAgencyId(String mediationAgencyId) {
		this.mediationAgencyId = mediationAgencyId;
	}
	public String getThirdPartyId() {
		return thirdPartyId;
	}
	public void setThirdPartyId(String thirdPartyId) {
		this.thirdPartyId = thirdPartyId;
	}
	
	public ThirdPartyEnum getThirdParty() {
		return thirdParty;
	}
	public void setThirdParty(ThirdPartyEnum thirdParty) {
		this.thirdParty = thirdParty;
	}
	public String getIdentifyName() {
		return identifyName;
	}
	public void setIdentifyName(String identifyName) {
		this.identifyName = identifyName;
	}
	public String getIdentify() {
		return identify;
	}
	public void setIdentify(String identify) {
		this.identify = identify;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Integer getShowDisplay() {
		return showDisplay;
	}
	public void setShowDisplay(Integer showDisplay) {
		this.showDisplay = showDisplay;
	}

}
