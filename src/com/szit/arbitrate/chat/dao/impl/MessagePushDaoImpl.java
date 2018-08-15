package com.szit.arbitrate.chat.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Maps;
import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.utils.DateUtils;
import com.szit.arbitrate.chat.dao.MessagePushDao;
import com.szit.arbitrate.chat.entity.MessagePush;
import com.szit.arbitrate.chat.entity.enumvo.RecTypeEnum;
import com.szit.arbitrate.chat.entity.query.MessagePushQuery;

/**
 * 
* @ProjectName:
* @ClassName: MessagePushDaoImpl
* @Description:消息分发dao接口实现类
* @author Administrator
* @date 2017年4月18日 下午4:30:48
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class MessagePushDaoImpl extends BaseHibernateDaoImpl<MessagePush, MessagePushQuery> implements MessagePushDao{

	
	
	@Override
	public List<QueryParam> buildQueryParams(MessagePushQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getChatMessageId())) {
				qps.add(new QueryParam("chatMessageId", query.getChatMessageId()));
			}
			if (!StringUtils.isEmpty(query.getSendClientId())) {
				qps.add(new QueryParam("sendClientId", query.getSendClientId()));
			}
			if (!StringUtils.isEmpty(query.getChatRoomId())) {
				qps.add(new QueryParam("chatRoomId", query.getChatRoomId()));
			}
			if (!StringUtils.isEmpty(query.getCaseId())) {
				qps.add(new QueryParam("caseId", query.getCaseId()));
			}
			if (!StringUtils.isEmpty(query.getReceiveClientId())) {
				qps.add(new QueryParam("receiveClientId", query.getReceiveClientId()));
			}
			qps.add(new QueryParam("readflag", query.isReadflag()));
		}
		return qps;
	}

	@Override
	public List<MessagePush> getAdminChatMessageList(String sendclientid,
			String recclientid) throws ErrorException {
		
		Map<String, Object> params = Maps.newHashMap();
		params.put("SENDCLIENTIDPARAM", sendclientid);
		params.put("RECCLIENTIDPARAM", recclientid);
		
		//默认3天的数据
		Date nowDt = new Date();
		String gainoverdt = DateUtils.parseToString(nowDt, DateUtils.DATE_YMDHMS_STR);
		String gainbegindt = DateUtils.parseToString(DateUtils.addDate(nowDt, -3), DateUtils.DATE_YYYYMDHM);

		StringBuffer sbHql = new StringBuffer();
		sbHql.append(" from MessagePush mp where 1=1 ");
	/*	sbHql.append(" pushTime >='").append(gainbegindt).append("'");
		sbHql.append(" and pushTime <='").append(gainoverdt).append("'");*/
		sbHql.append(" and (sendClientId=:SENDCLIENTIDPARAM and receiveClientId=:RECCLIENTIDPARAM )");
		sbHql.append(" or ");
		sbHql.append(" (sendClientId=:RECCLIENTIDPARAM and receiveClientId=:SENDCLIENTIDPARAM ) ");
		sbHql.append(" order by pushTime");
		List<MessagePush> list = this.findHqlToM(sbHql.toString(), params);
		return list;
	}
	
	@Override
	public List<MessagePush> getNotReadMessageByRecType(String clientid,
			String goalid, String goaltype, String requesttime,
			String strUptoDate) throws ErrorException {
		Map<String, Object> params = Maps.newHashMap();
		params.put("CLIENTIDPARAM", clientid);
		params.put("GOALIDPARAM", goalid);
		params.put("REQUESTTIMEPARAM", requesttime);
		params.put("STRUPTODATEPARAM", strUptoDate);
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ch_message_push mp");
		sql.append(" WHERE");
		sql.append(" mp.RECEIVE_CLIENT_ID='").append(clientid).append("'");
		sql.append(" AND mp.READ_FLAG=FALSE");
		sql.append(" AND mp.PUSH_TIME >='").append(requesttime).append("'");
		sql.append(" AND mp.PUSH_TIME <='").append(strUptoDate).append("'");
		if(goaltype.equals(RecTypeEnum.ChatRoom.toString())){
			sql.append(" AND mp.CHAT_ROOM_ID ='").append(goalid).append("'");
		}else{
			sql.append(" AND mp.SEND_CLIENT_ID ='").append(goalid).append("'");
		}
		List<MessagePush> list = (List<MessagePush>) this.findSql(sql.toString(), MessagePush.class);
		return list;
	}
	
}
