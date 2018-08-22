package com.szit.arbitrate.basisset.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: 基础参数设置类
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:14:52
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class ClientBasisParameterSetting  extends DomainEntity{
	
	private String clientid;//会员ID 
	private ParameterTypeEnum parametertype;//参数类型 
	private SysParameterTable sysparametertable;//系统参数字典对象
	private Integer seq;//参数排序 
	private String parameterid;//参数ID 
	private String parametersettingval;//参数设置值 
	private Date settingdatetime;//设置时间 

	public String getClientid(){
		return this.clientid;
	}
	public void setClientid(String clientid){
		this.clientid = clientid;
	}
	public ParameterTypeEnum getParametertype(){
		return this.parametertype;
	}
	public void setParametertype(ParameterTypeEnum parametertype){
		this.parametertype = parametertype;
	}
	public Integer getSeq(){
		return this.seq;
	}
	public void setSeq(Integer seq){
		this.seq = seq;
	}
	public String getParameterid(){
		return this.parameterid;
	}
	public void setParameterid(String parameterid){
		this.parameterid = parameterid;
	}
	public String getParametersettingval(){
		return this.parametersettingval;
	}
	public void setParametersettingval(String parametersettingval){
		this.parametersettingval = parametersettingval;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getSettingdatetime(){
		return this.settingdatetime;
	}
	public void setSettingdatetime(Date settingdatetime){
		this.settingdatetime = settingdatetime;
	}
	public SysParameterTable getSysparametertable() {
		return sysparametertable;
	}
	public void setSysparametertable(SysParameterTable sysparametertable) {
		this.sysparametertable = sysparametertable;
	}
}
