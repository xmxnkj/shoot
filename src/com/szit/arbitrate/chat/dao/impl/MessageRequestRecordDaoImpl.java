package com.szit.arbitrate.chat.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.chat.dao.MessageRequestRecordDao;
import com.szit.arbitrate.chat.entity.MessageRequestRecord;
import com.szit.arbitrate.chat.entity.query.MessageRequestRecordQuery;

/**
 * 
* @ProjectName:
* @ClassName: MessageRequestRecordDaoImpl
* @Description:历史聊天消息数据dao接口实现类
* @author Administrator
* @date 2017年4月18日 下午4:32:22
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class MessageRequestRecordDaoImpl extends BaseHibernateDaoImpl<MessageRequestRecord, MessageRequestRecordQuery>
	implements MessageRequestRecordDao{

	@Override
	public List<QueryParam> buildQueryParams(MessageRequestRecordQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getRequestClientId())) {
				qps.add(new QueryParam("requestClientId", query.getRequestClientId()));
			}
			if (!StringUtils.isEmpty(query.getRequestTime())) {
				qps.add(new QueryParam("requestTime", query.getRequestTime(), ParamCompareType.LargeEqual));
			}
			if (!StringUtils.isEmpty(query.getGoalId())) {
				qps.add(new QueryParam("goalId", query.getGoalId()));
			}
			if(query.getGoalType() != null){
				qps.add(new QueryParam("goalType", query.getGoalType()));
			}
		}
		return qps;
	}
	
}
