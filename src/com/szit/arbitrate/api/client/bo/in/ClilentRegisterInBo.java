/**   
* @Title: ClilentRegisterBo.java
* @Package com.szit.cowell.client.bo
* @Description: TODO
* @author XUJC
* @date 2017年10月27日 下午7:30:33
* @version V1.0   
*/


package com.szit.arbitrate.api.client.bo.in;

import java.math.BigDecimal;

import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.TerminalType;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;


/**
 * 
* @ClassName: ClilentRegisterInBo
* @Description:会员注册输入参数
* @author Administrator
* @date 2017年3月20日 下午2:45:58
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public class ClilentRegisterInBo {
	
	//昵称
	private String nickName;
	//账号
	private String account;
	private String passwd;
	//第三方登录ID
	private String thirdPartyId;
	//第三方登陆类型 	WeChat,Weibo,QQ
	private ThirdPartyEnum thirdParty;
	//性别 0男 1女
	private Boolean gender;
	//生日
	private String bornDate;
	//身高
	private Integer height;
	//体重
	private Integer weight;
	//会员类型 	Normal普通会员,Trainer教练
	private ClientTypeEnum clientType;
	//所属城市
	private String city;
	//设备的ID号
	private String terminalCode;
	//类型 0-IOS 1-ANDROID_APP 2-ANDROID_DEVICE
	private TerminalType terminalType;
	//设备型号
	private String spec;
	//地址
	private String address;
	
	//经度
	private BigDecimal lon;
	//纬度
	private BigDecimal lat;
	
	//短信验证KEY
	private String verifykeyid;
	//短信验证码
	private String verifycode;
	
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
	public Boolean getGender() {
		return gender;
	}
	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	public String getBornDate() {
		return bornDate;
	}
	public void setBornDate(String bornDate) {
		this.bornDate = bornDate;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public ClientTypeEnum getClientType() {
		return clientType;
	}
	public void setClientType(ClientTypeEnum clientType) {
		this.clientType = clientType;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getTerminalCode() {
		return terminalCode;
	}
	public void setTerminalCode(String terminalCode) {
		this.terminalCode = terminalCode;
	}
	public TerminalType getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(TerminalType terminalType) {
		this.terminalType = terminalType;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getVerifykeyid() {
		return verifykeyid;
	}
	public void setVerifykeyid(String verifykeyid) {
		this.verifykeyid = verifykeyid;
	}
	public String getVerifycode() {
		return verifycode;
	}
	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}
	
	 
	
}
