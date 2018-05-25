package com.szit.arbitrate.client.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.mediation.entity.enumvo.ProtocolStateEnum;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: TempClientQuery
* @Description:临时用户实体查询类
* @author Administrator
* @date 2017年3月24日 下午3:15:25
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class TempClientQuery extends EntityQueryParam{

	private String caseId;//案件id
	private String identifyName;//姓名
	private String identify;//身份证号码
	private String tel;//电话
	private String address;//地址
	private boolean partB;//是否是乙方
	private String clientId;//关联注册用户id
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
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public ProtocolStateEnum getSign() {
		return sign;
	}
	public void setSign(ProtocolStateEnum sign) {
		this.sign = sign;
	}
	
}
