package com.szit.arbitrate.chat.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.chat.entity.enumvo.RecTypeEnum;

/**
 * 
* @ProjectName:
* @ClassName: MessageRequestRecordQuery
* @Description:历史聊天消息数据请求记录查询类
* @author Administrator
* @date 2017年4月18日 下午4:20:48
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class MessageRequestRecordQuery extends EntityQueryParam{

	//请求用户ID
	private String requestClientId;
	//请求时间
	private String requestTime;
	//目标类型
	private RecTypeEnum goalType;
	//目标id
	private String goalId;
	
	public String getRequestClientId() {
		return requestClientId;
	}
	public void setRequestClientId(String requestClientId) {
		this.requestClientId = requestClientId;
	}
	public String getRequestTime() {
		return requestTime;
	}
	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;
	}
	public RecTypeEnum getGoalType() {
		return goalType;
	}
	public void setGoalType(RecTypeEnum goalType) {
		this.goalType = goalType;
	}
	public String getGoalId() {
		return goalId;
	}
	public void setGoalId(String goalId) {
		this.goalId = goalId;
	}
	
}
