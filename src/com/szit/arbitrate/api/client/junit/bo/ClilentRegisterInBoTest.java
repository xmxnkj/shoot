/**   
* @Title: ClilentRegisterBo.java
* @Package com.szit.cowell.client.bo
* @Description: TODO
* @author XUJC
* @date 2017年10月27日 下午7:30:33
* @version V1.0   
*/


package com.szit.arbitrate.api.client.junit.bo;


/**
 * 
* @ClassName: ClilentRegisterInBoTest
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:50:18
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public class ClilentRegisterInBoTest {
	
	//昵称
	private String nickName;
	//账号
	private String account;
	private String passwd;
	//第三方登录ID
	private String thirdPartyId;
	//第三方登陆类型 	WeChat,Weibo,QQ
	private String thirdParty;
	//性别 0男 1女
	private Integer gender;
	//生日
	private String bornDate;
	//身高
	private Integer height;
	//体重
	private Integer weight;
	//会员类型 	Normal普通会员,Trainer教练
	private String clientType;
	//所属城市
	private String city;
	//设备的ID号
	private String terminalCode;
	//类型 0-IOS 1-ANDROID_APP 2-ANDROID_DEVICE
	private String terminalType;
	//设备型号
	private String spec;
	
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
	public String getThirdParty() {
		return thirdParty;
	}
	public void setThirdParty(String thirdParty) {
		this.thirdParty = thirdParty;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
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
	public String getClientType() {
		return clientType;
	}
	public void setClientType(String clientType) {
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
	public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	
	 
	
}
