package com.szit.arbitrate.client.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: ClientThirdpartyAccountQuery
* @Description:第三方登录实体查询类
* @author yuyb
* @date 2017年3月17日 下午5:03:31
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class ClientThirdpartyAccountQuery extends EntityQueryParam{
	
	private String clientid;//会员ID 
	private String thirdprttyid;//第三方账号ID 
	private ThirdPartyEnum thirdpartytype;//第三方账号类型 
	private String thirdpartynickname;//第三方账号昵称 
	private Date bindingdatetime;//绑定时间 

	public String getClientid(){
		return this.clientid;
	}
	public void setClientid(String clientid){
		this.clientid = clientid;
	}
	public String getThirdprttyid(){
		return this.thirdprttyid;
	}
	public void setThirdprttyid(String thirdprttyid){
		this.thirdprttyid = thirdprttyid;
	}
	public ThirdPartyEnum getThirdpartytype(){
		return this.thirdpartytype;
	}
	public void setThirdpartytype(ThirdPartyEnum thirdpartytype){
		this.thirdpartytype = thirdpartytype;
	}
	public String getThirdpartynickname(){
		return this.thirdpartynickname;
	}
	public void setThirdpartynickname(String thirdpartynickname){
		this.thirdpartynickname = thirdpartynickname;
	}
	public Date getBindingdatetime(){
		return this.bindingdatetime;
	}
	public void setBindingdatetime(Date bindingdatetime){
		this.bindingdatetime = bindingdatetime;
	}

}
