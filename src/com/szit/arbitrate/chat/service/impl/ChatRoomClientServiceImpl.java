package com.szit.arbitrate.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.arbitrate.chat.dao.ChatRoomClientDao;
import com.szit.arbitrate.chat.entity.ChatRoomClient;
import com.szit.arbitrate.chat.entity.query.ChatRoomClientQuery;
import com.szit.arbitrate.chat.service.ChatRoomClientService;

/**
 * 
* @ProjectName:
* @ClassName: ChatRoomClientServiceImpl
* @Description:聊天室-用户关联业务实现类
* @author Administrator
* @date 2017年4月20日 下午4:40:42
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class ChatRoomClientServiceImpl extends AppBaseServiceImpl<ChatRoomClient, ChatRoomClientQuery> 
	implements ChatRoomClientService{

	@Autowired
	private ChatRoomClientDao dao;

	public ChatRoomClientDao getDao() {
		return dao;
	}

	public void setDao(ChatRoomClientDao dao) {
		this.dao = dao;
	}
	
}
