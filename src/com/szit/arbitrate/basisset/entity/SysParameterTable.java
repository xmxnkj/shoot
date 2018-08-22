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
* @ClassName: 基础参数字典
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:28:33
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
//@JsonSerialize(using = SysParameterTableSerializer.class)
public class SysParameterTable  extends DomainEntity{
	private ParameterTypeEnum parametertype;//参数类型 
	private Integer parameterseq;//参数排序
	private String parametercode;//参数代码 
	private String parametername;//参数名称 
	private String parameterinitvla;//参数初始值 
	private Boolean deleteflag=false;//删除标识 
	private Date buliddatetime;//生成时间 

	public ParameterTypeEnum getParametertype(){
		return this.parametertype;
	}
	public void setParametertype(ParameterTypeEnum parametertype){
		this.parametertype = parametertype;
	}
	public String getParametercode(){
		return this.parametercode;
	}
	public void setParametercode(String parametercode){
		this.parametercode = parametercode;
	}
	
	public String getParametername(){
		return this.parametername;
	}
	public void setParametername(String parametername){
		this.parametername = parametername;
	}
	public String getParameterinitvla(){
		return this.parameterinitvla;
	}
	public void setParameterinitvla(String parameterinitvla){
		this.parameterinitvla = parameterinitvla;
	}
	public Boolean getDeleteflag(){
		return this.deleteflag;
	}
	public void setDeleteflag(Boolean deleteflag){
		this.deleteflag = deleteflag;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getBuliddatetime(){
		return this.buliddatetime;
	}
	public void setBuliddatetime(Date buliddatetime){
		this.buliddatetime = buliddatetime;
	}
	public Integer getParameterseq() {
		return parameterseq;
	}
	public void setParameterseq(Integer parameterseq) {
		this.parameterseq = parameterseq;
	}

}
