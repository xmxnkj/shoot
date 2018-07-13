package com.szit.arbitrate.mediation.entity;

import java.math.BigDecimal;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.mediation.entity.enumvo.AgencyTypeEnum;

/**
 * 
* @ProjectName:
* @ClassName: MediationAgency
* @Description:机构实体类
* @author Administrator
* @date 2017年3月23日 上午10:21:37
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","displayOrder","createTime"})
@Table(name="me_mediation_agency")
public class MediationAgency extends DomainEntity{
	
	private static final long serialVersionUID = -3892793688471801964L;
	
	private AgencyTypeEnum agencyType;//机构类型,包括人民机构，行政机构，司法机构
	private String agencyName;//机构名称
	private String belongsTo;//所属街道 
	private String agencyClassify;//主体分类
	private String address;//地址
	private String tel;//电话
	private String mediationResourceId="3.jpg";//头像资源id
	private String managerClientId;//机构管理员用户id
	private Client managerClient;//机构管理员用户
	private boolean openOrNot=true;//是否开通线上服务
	
	//经度
	private BigDecimal lon;	//
	//纬度
	private BigDecimal lat;
	
	//经度
	private BigDecimal lonBaiDu;	//百度
	//纬度
	private BigDecimal latBaiDu;	//百度
		
	//简介
	//private String description;

	
	public BigDecimal getLonBaiDu() {
		return lonBaiDu;
	}
	public void setLonBaiDu(BigDecimal lonBaiDu) {
		this.lonBaiDu = lonBaiDu;
	}
	public BigDecimal getLatBaiDu() {
		return latBaiDu;
	}
	public void setLatBaiDu(BigDecimal latBaiDu) {
		this.latBaiDu = latBaiDu;
	}
	public String getAgencyClassify() {
		return agencyClassify;
	}
	public void setAgencyClassify(String agencyClassify) {
		this.agencyClassify = agencyClassify;
	}
	public String getBelongsTo() {
		return belongsTo;
	}
	public void setBelongsTo(String belongsTo) {
		this.belongsTo = belongsTo;
	}
	public AgencyTypeEnum getAgencyType() {
		return agencyType;
	}
	public void setAgencyType(AgencyTypeEnum agencyType) {
		this.agencyType = agencyType;
	}
	public String getAgencyName() {
		return agencyName;
	}
	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
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
	public String getMediationResourceId() {
		return mediationResourceId;
	}
	public void setMediationResourceId(String mediationResourceId) {
		this.mediationResourceId = mediationResourceId;
	}
	public String getManagerClientId() {
		return managerClientId;
	}
	public void setManagerClientId(String managerClientId) {
		this.managerClientId = managerClientId;
	}
	@JsonIgnoreProperties({"account","identifyImgFile1","identifyImgFile2","identifyImgFile3","mediationAgencyId",
		"thirdPartyId","thirdParty","clientType","address","tel","auditInfo","ip","description","skill","clientState"})
	public Client getManagerClient() {
		return managerClient;
	}
	public void setManagerClient(Client managerClient) {
		this.managerClient = managerClient;
	}
	public boolean isOpenOrNot() {
		return openOrNot;
	}
	public void setOpenOrNot(boolean openOrNot) {
		this.openOrNot = openOrNot;
	}
	public BigDecimal getLon() {
		return lon;
	}
	public void setLon(BigDecimal lon) {
		this.lon = lon;
	}
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
}