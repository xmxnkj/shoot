package com.szit.arbitrate.client.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.szit.arbitrate.client.entity.enumvo.CertificateStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientLoginTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientStateEnum;
import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;

/**
 * 用户类，包括普通用户、调解员
 * @author yuyb
 *
 */
@JsonIgnoreProperties({"name","displayOrder","passwd"})
public class Client extends DomainEntity{

	private static final long serialVersionUID = -4404155422700646565L;

	public Client() {
		super();
	}

	public Client(String id) {
		super(id);
		this.setId(id);
	}
	
	//昵称
	private String nickName;
	//账号
	private String account;
	private String passwd;
	//身份证信息
	private String identifyName;//姓名
	private boolean gender;//性别
	private String nation;//民族
	private String birth;//生日
	private String profession;//职业
	
	private String identify;//身份证号
	private String identifyImgFile1;//身份证正面照
	private String identifyImgFile2;//身份证反面照
	private String identifyImgFile3;//手持照
	private CertificateStateEnum auditInfo;//认证审核状态
	private String mediationAgencyId;//属于哪个调解机构
	private String agencyName;//机构名称
	//第三方登录ID
	private String thirdPartyId;
	//第三方登陆类型
	private ThirdPartyEnum thirdParty;
	//头像ID
	private String headImgFile="1.jpg";//1.jpg 默认头像
	//会员类型
	private ClientTypeEnum clientType;
	//地址
	private String address;
	//电话
	private String tel;
	//IP地址
	private String ip;
	//简介
	private String description;
	//擅长领域
	private String skill;
	//用户状态
	private ClientStateEnum clientState;
	//网易云信id
	private String neteaseClientId;
	//用户来源
	private ClientLoginTypeEnum loginType;
	
	private Integer mediatorType;	//调解员类型   1 行政 2司法 3人民

	private Integer showDisplay;	//排序
	
	private Date resgitTime;		//注册时间
	
	public Date getResgitTime() {
		return resgitTime;
	}

	public void setResgitTime(Date resgitTime) {
		this.resgitTime = resgitTime;
	}

	public Integer getShowDisplay() {
		return showDisplay;
	}

	public void setShowDisplay(Integer showDisplay) {
		this.showDisplay = showDisplay;
	}

	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	private String sessionId;//登录会话id

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getNeteaseClientId() {
		return neteaseClientId;
	}
	public void setNeteaseClientId(String neteaseClientId) {
		this.neteaseClientId = neteaseClientId;
	}
	
	public Integer getMediatorType() {
		return mediatorType;
	}

	public void setMediatorType(Integer mediatorType) {
		this.mediatorType = mediatorType;
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
	public String getIdentifyImgFile1() {
		return identifyImgFile1;
	}
	public void setIdentifyImgFile1(String identifyImgFile1) {
		this.identifyImgFile1 = identifyImgFile1;
	}
	public String getIdentifyImgFile2() {
		return identifyImgFile2;
	}
	public void setIdentifyImgFile2(String identifyImgFile2) {
		this.identifyImgFile2 = identifyImgFile2;
	}
	public String getIdentifyImgFile3() {
		return identifyImgFile3;
	}
	public void setIdentifyImgFile3(String identifyImgFile3) {
		this.identifyImgFile3 = identifyImgFile3;
	}
	public CertificateStateEnum getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(CertificateStateEnum auditInfo) {
		this.auditInfo = auditInfo;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
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

	public String getHeadImgFile() {
		return headImgFile;
	}

	public void setHeadImgFile(String headImgFile) {
		this.headImgFile = headImgFile;
	}

	public ClientTypeEnum getClientType() {
		return clientType;
	}

	public void setClientType(ClientTypeEnum clientType) {
		this.clientType = clientType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public ClientStateEnum getClientState() {
		return clientState;
	}

	public void setClientState(ClientStateEnum clientState) {
		this.clientState = clientState;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public ClientLoginTypeEnum getLoginType() {
		return loginType;
	}

	public void setLoginType(ClientLoginTypeEnum loginType) {
		this.loginType = loginType;
	}
}
