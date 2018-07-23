package com.szit.arbitrate.chat.dao;

import java.util.List;

import com.hsit.common.dao.BaseHibernateDao;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.chat.entity.MessagePush;
import com.szit.arbitrate.chat.entity.query.MessagePushQuery;

/**
 * 
* @ProjectName:
* @ClassName: MessagePushDao
* @Description:消息分发dao接口类
* @author Administrator
* @date 2017年4月18日 下午4:29:42
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface MessagePushDao extends BaseHibernateDao<MessagePush, MessagePushQuery>{

	
	/**
	 * 
	* @Title: getAdminChatMessageList 
	* @Description: 获取指定用户id的聊天记录
	* @param @param sendclientid
	* @param @param recclientid
	* @param @return
	* @param @throws ErrorException
	* @return List<MessagePush> 
	* @throws
	 */
	public List<MessagePush> getAdminChatMessageList(String sendclientid, String recclientid)throws ErrorException;
	
	/**
	 * 
	* @Title: getNotReadMessageByRecType 
	* @Description: 获取未读消息记录
	* @param @param clientid
	* @param @param goalid
	* @param @param goaltype
	* @param @param requesttime
	* @param @param strUptoDate
	* @param @return
	* @param @throws ErrorException
	* @return List<MessagePush> 
	* @throws
	 */
	public List<MessagePush> getNotReadMessageByRecType(String clientid, String goalid, String goaltype,
			String requesttime, String strUptoDate)throws ErrorException;
	
}
