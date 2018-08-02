package com.szit.arbitrate.chat.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;

/**
 * 
* @ClassName: ConsultClientQuery  
* @Description:咨询用户实体查询类 
* @author   
* @date 2017年6月20日 下午4:35:00  
* @Copyright
* @versions:1.0 
*
 */
public class ConsultClientQuery extends EntityQueryParam{

	private String clientId;//咨询用户id
	private String clientImage;//
	private String clientName;
	
	private Date consultTime;//最新咨询时间

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientImage() {
		return clientImage;
	}

	public void setClientImage(String clientImage) {
		this.clientImage = clientImage;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Date getConsultTime() {
		return consultTime;
	}

	public void setConsultTime(Date consultTime) {
		this.consultTime = consultTime;
	}
	
}
