package com.szit.arbitrate.chat.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseServiceImpl;
import com.hsit.common.utils.DateUtils;
import com.szit.arbitrate.chat.dao.MessagePushDao;
import com.szit.arbitrate.chat.entity.ConsultClient;
import com.szit.arbitrate.chat.entity.MessagePush;
import com.szit.arbitrate.chat.entity.MessageRequestRecord;
import com.szit.arbitrate.chat.entity.enumvo.RecTypeEnum;
import com.szit.arbitrate.chat.entity.query.ConsultClientQuery;
import com.szit.arbitrate.chat.entity.query.MessagePushQuery;
import com.szit.arbitrate.chat.entity.query.MessageRequestRecordQuery;
import com.szit.arbitrate.chat.service.ConsultClientService;
import com.szit.arbitrate.chat.service.MessagePushService;
import com.szit.arbitrate.chat.service.MessageRequestRecordService;

/**
 * 
* @ProjectName:
* @ClassName: MessagePushServiceImpl
* @Description:消息分发业务接口实现类
* @author Administrator
* @date 2017年4月18日 下午5:02:17
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class MessagePushServiceImpl extends AppBaseServiceImpl<MessagePush, MessagePushQuery> implements MessagePushService{

	@Autowired
	private MessageRequestRecordService messageRequestRecordService;
	@Autowired
	private ConsultClientService consultClientSerivce;
	@Autowired
	private MessagePushDao dao;

	public MessagePushDao getDao() {
		return dao;
	}

	public void setDao(MessagePushDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<MessagePush> getAdminChatMessageList(String sendclientid,
			String recclientid) throws BizException, ErrorException {
		if(StringUtils.isEmpty(recclientid) || StringUtils.isEmpty(recclientid)){
			throw new BizException("参数不能空!");
		}
		return dao.getAdminChatMessageList(sendclientid, recclientid);
	}
	
	@Override
	public long getNotReadMessageCountByRecType(String requestclientid,
			String goalid, String goaltype) throws BizException, ErrorException {
		MessagePushQuery query = new MessagePushQuery();
		query.setReceiveClientId(requestclientid);//ReceiveClientId 接受人id
		query.setReadflag(false);
		long count = 0;
		if(goaltype.equals(RecTypeEnum.ChatRoom.toString())){//说明方式聊天室消息
			query.setChatRoomId(goalid);
		}else{//说明是单对单发送的消息
			query.setSendClientId(goalid);
		}
		count = this.getEntityCount(query);
		return count;
	}
	
	
	@Override
	public void readMessage(String clientid, String goalid, String goaltype)
			throws BizException, ErrorException {
		//1.取得该用户的最近一次请求时间点
		MessageRequestRecordQuery messageRequestRecordQuery = new MessageRequestRecordQuery();
		messageRequestRecordQuery.setRequestClientId(clientid);
		messageRequestRecordQuery.setGoalId(goalid);
		messageRequestRecordQuery.setGoalType(RecTypeEnum.valueOf(goaltype));
		MessageRequestRecord messageRequestRecord = messageRequestRecordService.getEntity(messageRequestRecordQuery);
		
		//2.获取请求起止时间节点
		String requestTime = "";
		if(messageRequestRecord == null){
			requestTime = "2017-5-12 00:00:00";
			messageRequestRecord = new MessageRequestRecord();
			messageRequestRecord.setRequestClientId(clientid);
			messageRequestRecord.setGoalId(goalid);
			messageRequestRecord.setGoalType(RecTypeEnum.valueOf(goaltype));
		}else{
			requestTime = messageRequestRecord.getRequestTime();
		}
		String strUptoDate = DateUtils.parseToString(new Date(), DateUtils.DATE_YMDHMS_STR);
		//3.取得未读消息列表，并全部设置为已读
		List<MessagePush> list = dao.getNotReadMessageByRecType(clientid, goalid, goaltype, requestTime, strUptoDate);
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				MessagePush entity = list.get(i);
				entity.setReadflag(true);
				this.save(entity);
			}
		}
		messageRequestRecord.setRequestTime(strUptoDate);
		messageRequestRecordService.save(messageRequestRecord);
		
		ConsultClientQuery consultClientQuery = new ConsultClientQuery();
		if(clientid.equals("1")){
			consultClientQuery.setClientId(goalid);
		}else if(goalid.equals("1")){
			consultClientQuery.setClientId(clientid);
		}
		ConsultClient consultClient = consultClientSerivce.getEntity(consultClientQuery);
		if(consultClient != null){
			if(!clientid.equals(consultClient.getSendClientId())){
				consultClient.setReadflag(true);
				consultClientSerivce.save(consultClient);
			}
		}
		
	}
	
	
}
