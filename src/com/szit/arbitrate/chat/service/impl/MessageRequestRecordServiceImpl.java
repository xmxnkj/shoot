package com.szit.arbitrate.chat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.arbitrate.chat.dao.MessageRequestRecordDao;
import com.szit.arbitrate.chat.entity.MessageRequestRecord;
import com.szit.arbitrate.chat.entity.query.MessageRequestRecordQuery;
import com.szit.arbitrate.chat.service.MessageRequestRecordService;

/**
 * 
* @ProjectName:
* @ClassName: MessageRequestRecordServiceImpl
* @Description:历史聊天消息数据请求记录业务实现类
* @author Administrator
* @date 2017年4月18日 下午5:03:55
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class MessageRequestRecordServiceImpl extends AppBaseServiceImpl<MessageRequestRecord, MessageRequestRecordQuery>
	implements MessageRequestRecordService{
	
	@Autowired
	private MessageRequestRecordDao dao;

	public MessageRequestRecordDao getDao() {
		return dao;
	}

	public void setDao(MessageRequestRecordDao dao) {
		this.dao = dao;
	}

}
