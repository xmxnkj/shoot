package com.szit.arbitrate.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.arbitrate.chat.dao.ChatResourceDao;
import com.szit.arbitrate.chat.entity.ChatResource;
import com.szit.arbitrate.chat.entity.query.ChatResourceQuery;
import com.szit.arbitrate.chat.service.ChatResourceService;

/**
 * 
* @ProjectName:
* @ClassName: ChatResourceServiceImpl
* @Description:聊天资源业务实现类
* @author Administrator
* @date 2017年5月31日 上午11:20:53
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class ChatResourceServiceImpl extends AppBaseServiceImpl<ChatResource, ChatResourceQuery> implements ChatResourceService{

	@Autowired
	private ChatResourceDao dao;

	public ChatResourceDao getDao() {
		return dao;
	}

	public void setDao(ChatResourceDao dao) {
		this.dao = dao;
	}
	
}
