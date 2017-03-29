package com.hsit.common.actions.uac.models;

public class UserSetting {
	private Boolean savePasswd=false;
	
	private Integer savePeriod=0;
	
	public String getSavePasswdSettingName(){
		return "SaveUserPasswd";
	}
	
	public String getSavePeriodSettingName(){
		return "SaveUserPasswdPeriod";
	}

	public Boolean getSavePasswd() {
		return savePasswd;
	}

	public void setSavePasswd(Boolean savePasswd) {
		this.savePasswd = savePasswd;
	}

	public Integer getSavePeriod() {
		return savePeriod;
	}

	public void setSavePeriod(Integer savePeriod) {
		this.savePeriod = savePeriod;
	}
	
}
