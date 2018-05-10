package com.szit.arbitrate.client.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;
import com.szit.arbitrate.mediation.entity.enumvo.ProtocolStateEnum;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: TempClient
* @Description:临时虚拟会员实体类，用于添加乙方对象显示,召集时与client对象进行关联
* @author Administrator
* @date 2017年3月24日 下午3:10:05
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class TempClient extends DomainEntity{
	
	private String caseId;//案件id
	private String identifyName;//姓名
	private boolean gender;//性别
	private Date birth;//生日
	private String nation;//民族
	private String profession;//职业
	private String identify;//身份证号码
	private String tel;//电话
	private String address;//地址
	private String oralAgree;//口头协议
	private boolean partB = true;//是否是被申述人,true为被申述人，false为第三方
	private boolean gather = false;//是否召集
	
	private String clientId;//关联注册用户id
	//private Client client;//关联注册用户对象
	private ProtocolStateEnum sign;//是否同意签署协议
	
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	
	public String getIdentifyName() {
		return identifyName;
	}
	public void setIdentifyName(String identifyName) {
		this.identifyName = identifyName;
	}
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
	public boolean isPartB() {
		return partB;
	}
	public void setPartB(boolean partB) {
		this.partB = partB;
	}
	
	public boolean isGather() {
		return gather;
	}
	public void setGather(boolean gather) {
		this.gather = gather;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	/*@JsonIgnoreProperties({"account","identifyImgFile1","identifyImgFile2","identifyImgFile3","mediationAgencyId",
		"thirdPartyId","thirdParty","clientType","auditInfo","address","ip","description","skill"})
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}*/
	public ProtocolStateEnum getSign() {
		return sign;
	}
	public void setSign(ProtocolStateEnum sign) {
		this.sign = sign;
	}
	public String getOralAgree() {
		return oralAgree;
	}
	public void setOralAgree(String oralAgree) {
		this.oralAgree = oralAgree;
	}
	
	

}
