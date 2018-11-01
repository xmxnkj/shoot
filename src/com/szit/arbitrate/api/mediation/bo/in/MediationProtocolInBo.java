package com.szit.arbitrate.api.mediation.bo.in;


public class MediationProtocolInBo {
	
	private String id;
	private String title;//标题
	private String serialNumber;//编号
	private String mediatorTel;//电话
	private String caseId;//id
	private String identifyName;//姓名
	private boolean gender;//性别
	private String birth;//生日
	private String address;
	private String identify;//身份证号码
	private String tel;//电话
	private String nation;//民族
	private String profession;//职业
	private String content;//协议正文
	private String controversy;//内容
	private String execute;//执行内容
	private String externalDesc;//额外说明
	private String  oralAgree;//协议内容 
	
	public String getMediatorTel() {
		return mediatorTel;
	}
	public void setMediatorTel(String mediatorTel) {
		this.mediatorTel = mediatorTel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public boolean isGender() {
		return gender;
	}
	public void setGender(boolean gender) {
		this.gender = gender;
	}
	
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getIdentifyName() {
		return identifyName;
	}
	public void setIdentifyName(String identifyName) {
		this.identifyName = identifyName;
	}
	public String getOralAgree() {
		return oralAgree;
	}
	public void setOralAgree(String oralAgree) {
		this.oralAgree = oralAgree;
	}

}
