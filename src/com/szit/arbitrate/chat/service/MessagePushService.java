package com.szit.arbitrate.chat.service;

import java.util.List;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.chat.entity.MessagePush;
import com.szit.arbitrate.chat.entity.query.MessagePushQuery;

/**
 * 
* @ProjectName:
* @ClassName: MessagePushService
* @Description:消息分发业务接口类
* @author Administrator
* @date 2017年4月18日 下午5:00:44
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface MessagePushService extends AppBaseService<MessagePush, MessagePushQuery>{
	
	/**
	 * 
	* @Title: getAdminChatMessageList 
	* @Description: 获取指定用户id的聊天记录
	* @param @param sendclientid
	* @param @param recclientid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return List<MessagePush> 
	* @throws
	 */
	public List<MessagePush> getAdminChatMessageList(String sendclientid,String recclientid)
			throws BizException, ErrorException;
	
	/**
	 * 
	* @Title: getNotReadMessageCountByRecType 
	* @Description: 获取未读消息数量
	* @param @param requestclientid
	* @param @param goalid
	* @param @param goaltype 类型包括单个会员和聊天室
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return Integer 
	* @throws
	 */
	public long getNotReadMessageCountByRecType(String requestclientid, String goalid, String goaltype)
			throws BizException, ErrorException;
	
	/**
	 * 
	* @Title: readMessage 
	* @Description: 读取消息，将所有消息设置为已读，按goaltype区分聊天室还是个人
	* @param @param clientid
	* @param @param goalid
	* @param @param goaltype
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void readMessage(String clientid, String goalid, String goaltype)throws BizException, ErrorException;
	
	
}
