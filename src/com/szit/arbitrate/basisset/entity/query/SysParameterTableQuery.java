package com.szit.arbitrate.basisset.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;

/**
 * @ProjectName:xmszit-cowell-module-entity
 * @ClassName: SysParameterTable
 * @Description: 基础-系统参数字典查询实体类
 * @author XUJC
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
public class SysParameterTableQuery  extends EntityQueryParam{
	private ParameterTypeEnum parametertype;//参数类型 
	private String parametercode;//参数代码 
	private String parametername;//参数名称 
	private String parameterinitvla;//参数初始值 
	private Boolean deleteflag;//删除标识 
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
	public Date getBuliddatetime(){
		return this.buliddatetime;
	}
	public void setBuliddatetime(Date buliddatetime){
		this.buliddatetime = buliddatetime;
	}

}
