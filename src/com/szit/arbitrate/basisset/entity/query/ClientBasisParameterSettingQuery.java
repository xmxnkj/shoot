package com.szit.arbitrate.basisset.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;

/**
 * @ProjectName:xmszit-cowell-module-entity
 * @ClassName: ClientBasisParameterSetting
 * @Description: 基础参数设置查询实体类
 * @author XUJC
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
public class ClientBasisParameterSettingQuery  extends EntityQueryParam{
	private String clientid;//会员ID 
	private ParameterTypeEnum parametertype;//参数类型 
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

	
	
	public ParameterTypeEnum getParametertype() {
		return parametertype;
	}
	public void setParametertype(ParameterTypeEnum parametertype) {
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
	public Date getSettingdatetime(){
		return this.settingdatetime;
	}
	public void setSettingdatetime(Date settingdatetime){
		this.settingdatetime = settingdatetime;
	}

}
