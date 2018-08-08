package com.szit.arbitrate.mediation.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.client.entity.enumvo.ResTypeEnum;

/**
 * 
* @ProjectName:
* @ClassName: MediationResourceQuery
* @Description:资源查询类
* @author Administrator
* @date 2017年3月22日 下午5:33:07
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class MediationResourceQuery extends EntityQueryParam{
	
	private String caseId;//id
	private String clientId;//上传用户id
	private ResTypeEnum resType;//资源类型
	private String resuploadfileid;//资源上传的文件ID
	
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public ResTypeEnum getResType() {
		return resType;
	}
	public void setResType(ResTypeEnum resType) {
		this.resType = resType;
	}
	public String getResuploadfileid() {
		return resuploadfileid;
	}
	public void setResuploadfileid(String resuploadfileid) {
		this.resuploadfileid = resuploadfileid;
	}

}
