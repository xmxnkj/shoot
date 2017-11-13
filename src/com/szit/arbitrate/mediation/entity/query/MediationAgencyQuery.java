package com.szit.arbitrate.mediation.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.mediation.entity.enumvo.AgencyTypeEnum;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: MediationAgencyQuery
* @Description:调解机构查询类
* @author Administrator
* @date 2017年3月23日 上午10:47:17
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class MediationAgencyQuery extends EntityQueryParam{
	
	private AgencyTypeEnum agencyType;
	private String agencyName;//机构名称
	private String managerClientId;//机构管理员用户id
	private boolean openOrNot;//是否开通线上调解服务
	private String address;//地址
	private String tel;//电话
	
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
	public String getManagerClientId() {
		return managerClientId;
	}
	public void setManagerClientId(String managerClientId) {
		this.managerClientId = managerClientId;
	}
	
	public boolean isOpenOrNot() {
		return openOrNot;
	}
	public void setOpenOrNot(boolean openOrNot) {
		this.openOrNot = openOrNot;
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

}
