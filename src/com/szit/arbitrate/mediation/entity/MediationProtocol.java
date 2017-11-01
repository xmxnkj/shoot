package com.szit.arbitrate.mediation.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;
import com.szit.arbitrate.mediation.entity.enumvo.ProtocolStateEnum;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: Protocol
* @Description:调解协议书实体类
* @author Administrator
* @date 2017年3月22日 下午3:44:19
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class MediationProtocol extends DomainEntity{

	private static final long serialVersionUID = -1178921271197388683L;
	
	private String caseId;//案件id
	private String title;//标题
	private String serialNumber;//编号
	private String mediatorTel;//调解员电话
	
	private String applyClientId;//申请人id 甲方
	private String identifyName;//姓名
	private boolean gender;//性别
	private Date birth;//生日
	private String identify;//身份证号码
	private String tel;//电话
	private String address;//地址
	private String nation;//民族
	private String profession;//职业
	private ProtocolStateEnum partAState;//甲方是否同意
	private Date partATime;//甲方同意时间
	private String content;//协议正文
	private String controversy;//争议内容
	private String execute;//执行内容
	private String externalDesc;//额外说明
	private boolean finalVersion;//是否终稿
	private String  oralAgree;//口头协议和书面协议 
	
	private String message;//通知消息
	
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getApplyClientId() {
		return applyClientId;
	}
	public void setApplyClientId(String applyClientId) {
		this.applyClientId = applyClientId;
	}
	/*@JsonIgnoreProperties({"account","identifyImgFile1","identifyImgFile2","identifyImgFile3","mediationAgencyId",
		"thirdPartyId","thirdParty","clientType","auditInfo","address","tel","ip","description","skill","clientState"})
	public Client getApplyClient() {
		return applyClient;
	}
	public void setApplyClient(Client applyClient) {
		this.applyClient = applyClient;
	}*/
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getBirth() {
		return birth;
	}
	public void setBirth(Date birth) {
		this.birth = birth;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	
	public ProtocolStateEnum getPartAState() {
		return partAState;
	}
	public void setPartAState(ProtocolStateEnum partAState) {
		this.partAState = partAState;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getPartATime() {
		return partATime;
	}
	public void setPartATime(Date partATime) {
		this.partATime = partATime;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getControversy() {
		return controversy;
	}
	public void setControversy(String controversy) {
		this.controversy = controversy;
	}
	public String getExecute() {
		return execute;
	}
	public void setExecute(String execute) {
		this.execute = execute;
	}
	public String getExternalDesc() {
		return externalDesc;
	}
	public void setExternalDesc(String externalDesc) {
		this.externalDesc = externalDesc;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isFinalVersion() {
		return finalVersion;
	}
	public void setFinalVersion(boolean finalVersion) {
		this.finalVersion = finalVersion;
	}
	public String getMediatorTel() {
		return mediatorTel;
	}
	public void setMediatorTel(String mediatorTel) {
		this.mediatorTel = mediatorTel;
	}
	public String getOralAgree() {
		return oralAgree;
	}
	public void setOralAgree(String oralAgree) {
		this.oralAgree = oralAgree;
	}

}
