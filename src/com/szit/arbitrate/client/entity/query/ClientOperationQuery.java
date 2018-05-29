package com.szit.arbitrate.client.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: ClientOperationQuery
* @Description:用户操作查询类
* @author yuyb 
* @date 2017年3月17日 下午5:04:19
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class ClientOperationQuery extends EntityQueryParam{
	
	//操作名称
	private String operationName;

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

}
