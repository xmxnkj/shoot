
package com.szit.arbitrate.api.client.bo.in;

import com.szit.arbitrate.client.entity.enumvo.TerminalType;


/**
 * 
* @ClassName: ClientLoginInBo
* @Description:会员登陆
* @author Administrator
* @date 2017年3月20日 下午2:45:41
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public class ClientLoginInBo {

	private String account;
	private String passwd;
	private String logintype;
	//设备的ID号
	private String terminalCode;
	//类型 0-IOS 1-ANDROID_APP 2-ANDROID_DEVICE
	private TerminalType terminalType;
	//设备型号
	private String spec;
	
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
	public String getLogintype() {
		return logintype;
	}
	public void setLogintype(String logintype) {
		this.logintype = logintype;
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

	
}
