package com.szit.arbitrate.chat.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.utils.PagingBean;
import com.szit.arbitrate.chat.dao.ChatMessageDao;
import com.szit.arbitrate.chat.entity.ChatMessage;
import com.szit.arbitrate.chat.entity.enumvo.RecTypeEnum;
import com.szit.arbitrate.chat.entity.query.ChatMessageQuery;

/**
 * 
* @ProjectName:
* @ClassName: ChatMessageDaoImpl
* @Description:聊天消息dao接口实现类
* @author Administrator
* @date 2017年4月18日 下午4:26:04
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class ChatMessageDaoImpl extends BaseHibernateDaoImpl<ChatMessage, ChatMessageQuery> implements ChatMessageDao{

	@Override
	public List<QueryParam> buildQueryParams(ChatMessageQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getSendClientId())) {
				qps.add(new QueryParam("sendClientId", query.getSendClientId()));
			}
			if (!StringUtils.isEmpty(query.getReceiveId())) {
				qps.add(new QueryParam("receiveId", query.getReceiveId()));
			}
			if (!StringUtils.isEmpty(query.getCaseId())) {
				qps.add(new QueryParam("caseId", query.getCaseId()));
			}
			if (!StringUtils.isEmpty(query.getContent())) {
				qps.add(new QueryParam("content", query.getContent(), ParamCompareType.Like));
			}
		}
		return qps;
	}
	
	
	@Override
	public List<ChatMessage> getMessageListByClientAndPage(String sendclientid,String rectype,
			String recid, String content, String caseid, PagingBean page) throws ErrorException {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ch_chat_message cm ");
		if(rectype.equals(RecTypeEnum.ChatRoom.toString())){
			sql.append(" WHERE cm.RECEIVE_ID='").append(recid).append("'");
		}else{
			sql.append(" WHERE ((cm.SEND_CLIENT_ID ='").append(sendclientid).append("'");
			sql.append(" AND cm.RECEIVE_ID='").append(recid).append("')");
			sql.append(" OR (cm.RECEIVE_ID='").append(sendclientid).append("'");
			sql.append(" AND cm.SEND_CLIENT_ID='").append(recid).append("'))");
		}
		if(StringUtils.isNotEmpty(content)){
			sql.append(" and cm.CONTENT_TYPE ='0'");
			sql.append(" and cm.CONTENT like '%").append(content).append("%'");
			sql.append(" and cm.CONTENT not regexp '[|]'");
		}
		if(StringUtils.isNotEmpty(caseid)){
			sql.append(" and cm.CASE_ID ='").append(caseid).append("'");
		}else{
			sql.append(" and cm.CASE_ID is null");
		}
		sql.append(" order by cm.SEND_TIME desc");
		
		//List<ChatMessage> list = this.findSqlBeanToList(sql.toString(), ChatMessage.class, page, params);
		List<ChatMessage> list = this.findSql(sql.toString(), ChatMessage.class, page);
		
		return list;
	}
	
	@Override
	public long getMessageCountByClient(String sendclientid, String recid, 
			String rectype, String content, String caseid) throws ErrorException{
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT count(*) as count FROM ch_chat_message cm ");
		if(rectype.equals(RecTypeEnum.ChatRoom.toString())){
			sql.append(" WHERE cm.RECEIVE_ID='").append(recid).append("'");
		}else{
			sql.append(" WHERE ((cm.SEND_CLIENT_ID ='").append(sendclientid).append("'");
			sql.append(" AND cm.RECEIVE_ID='").append(recid).append("')");
			sql.append(" OR (cm.RECEIVE_ID='").append(sendclientid).append("'");
			sql.append(" AND cm.SEND_CLIENT_ID='").append(recid).append("'))");
		}
		if(StringUtils.isNotEmpty(content)){
			sql.append(" and cm.CONTENT_TYPE ='0'");
			sql.append(" and cm.CONTENT like '%").append(content).append("%'");
			sql.append(" and cm.CONTENT not regexp '[|]'");
		}
		if(StringUtils.isNotEmpty(caseid)){
			sql.append(" and cm.CASE_ID ='").append(caseid).append("'");
		}else{
			sql.append(" and cm.CASE_ID is null");
		}
		//int count = this.findSqlCounts(sql.toString(),null).intValue();
		long count = 0;
		Map<String, Object> result = this.getJdbcTemplate().queryForMap(sql.toString());
		if(result.containsKey("count")){
			count = (long) result.get("count");
		}
		return count;
	}
	
	@Override
	public List<ChatMessage> getMessageListByMsgIdForCounts(
			String sendclientid, RecTypeEnum receiveType,String recid, String sendTime)
			throws ErrorException {
		
		StringBuffer sql = new StringBuffer();
		sql.append(" SELECT * FROM ch_chat_message");
		sql.append(" WHERE ");
		if(receiveType.equals(RecTypeEnum.ChatRoom)){
			sql.append(" RECEIVE_ID='").append(recid).append("'");
		}else{
			sql.append(" ((SEND_CLIENT_ID ='").append(sendclientid).append("'");
			sql.append(" AND RECEIVE_ID='").append(recid).append("')");
			sql.append(" OR (RECEIVE_ID='").append(sendclientid).append("'");
			sql.append(" AND SEND_CLIENT_ID='").append(recid).append("'))");
		}
		//sql.append(" and date_format(SEND_TIME,'%Y-%m-%d %h:%i') >'").append(sendTime).append("'");
		sql.append(" and SEND_TIME >='").append(sendTime).append("'");
		sql.append(" order by SEND_TIME desc limit 20");
		List<ChatMessage> list = (List<ChatMessage>) this.findSql(sql.toString(), ChatMessage.class);
		return list;
	}
	
}
