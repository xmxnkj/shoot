package com.szit.arbitrate.chat.dao;

import java.util.List;

import com.hsit.common.dao.BaseHibernateDao;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.utils.PagingBean;
import com.szit.arbitrate.chat.entity.ChatMessage;
import com.szit.arbitrate.chat.entity.enumvo.RecTypeEnum;
import com.szit.arbitrate.chat.entity.query.ChatMessageQuery;

/**
 * 
* @ProjectName:
* @ClassName: ChatMessageDao
* @Description:聊天消息dao接口类
* @author Administrator
* @date 2017年4月18日 下午4:24:45
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ChatMessageDao extends BaseHibernateDao<ChatMessage, ChatMessageQuery>{
	
	/**
	 * 
	* @Title: getMessageListByClientAndPage 
	* @Description: 根据分页参数 获取用户的聊天记录
	* @param @param sendclientid
	* @param @param recid
	* @param @param rectype
	* @param @param page
	* @param @return
	* @param @throws ErrorException
	* @return List<ChatMessage> 
	* @throwss
	 */
	public List<ChatMessage> getMessageListByClientAndPage(String sendclientid,String rectype,
			String recid,String content, String caseid, PagingBean page) throws ErrorException;
	
	/**
	 * 
	* @Title: getMessageCountByClient 
	* @Description: 查询聊天总记录数
	* @param @param sendclientid
	* @param @param recid
	* @param @return
	* @return int 
	* @throws
	 */
	public long getMessageCountByClient(String sendclientid,String recid, 
			String rectype, String content, String caseid)throws ErrorException;

	/**
	 * 
	* @Title: getMessageListByMsgIdForCounts  
	* @Description: 查询某条消息前后固定数量的消息列表
	* @param @param sendclientid
	* @param @param recid
	* @param @return
	* @param @throws ErrorException    设定文件  
	* @return List<ChatMessage>    返回类型  
	* @throws
	 */
	public List<ChatMessage> getMessageListByMsgIdForCounts(String sendclientid,RecTypeEnum receiveType,
			String recid, String sendTime)throws ErrorException;
	
}
