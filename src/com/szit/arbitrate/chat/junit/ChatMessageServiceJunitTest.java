package com.szit.arbitrate.chat.junit;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.common.collect.Maps;
import com.hsit.common.utils.JsonFormatUtil;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.chat.dao.MessagePushDao;
import com.szit.arbitrate.chat.entity.MessagePush;
import com.szit.arbitrate.chat.service.ChatMessageService;

public class ChatMessageServiceJunitTest extends BaseApiJunitTest{

	@Autowired
	private ChatMessageService service;
	
	@Autowired
	private MessagePushDao dao;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger logger = LoggerFactory.getLogger(ChatMessageServiceJunitTest.class);
	
	
	@Test
	public void sendMediaMessage(){
		String sendclientid = "1"; 
		String receiveid = "5bd0c6fc-3698-4e6a-9ae8-d10989615ef0";
		String rectype = "Client";
		String sendfiletype = "Text";
		String sendfilename = "123.jpg";
		File sendfile = new File("E:\\temp\\123.jpg");
		service.sendMessage(sendclientid, receiveid, rectype, sendfiletype, sendfilename, "");
	}
	
	@Test
	public void getMessageListByMsgIdForCounts(){
		String chatmessageid = "bf3ed5b8-fd82-4c73-a2b2-f0b6f1f5117f";
		service.getMessageListByMsgIdForCounts(chatmessageid);
	}
	
	@Test
	public void getMessageListByTotal(){
		Map<String, Object> params = Maps.newHashMap();
		//params.put("SENDCLIENTIDPARAM", sendclientid);
		params.put("RECCLIENTIDPARAM", "1");
		
		StringBuffer sbHql = new StringBuffer();
		sbHql.append(" from MessagePush mp where ");
		sbHql.append(" (receiveClientId=:RECCLIENTIDPARAM )");
		sbHql.append(" or ");
		sbHql.append(" (sendClientId=:RECCLIENTIDPARAM) ");
		sbHql.append(" order by pushTime");
		List<MessagePush> list = dao.findHqlToM(sbHql.toString(), params);
		JsonFormatUtil.printJson("list", list);
	}
	
	
}
