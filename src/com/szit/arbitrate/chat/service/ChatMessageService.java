package com.szit.arbitrate.chat.service;

import java.io.File;
import java.util.List;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.chat.entity.ChatMessage;
import com.szit.arbitrate.chat.entity.ChatResource;
import com.szit.arbitrate.chat.entity.enumvo.ContentTypeEnum;
import com.szit.arbitrate.chat.entity.query.ChatMessageQuery;

/**
 * 
* @ProjectName:
* @ClassName: ChatMessageService
* @Description:聊天消息业务接口类
* @author Administrator
* @date 2017年4月18日 下午4:54:17
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ChatMessageService extends AppBaseService<ChatMessage, ChatMessageQuery>{
	
	/**
	 * 
	* @Title: uploadMediaResource 
	* @Description: TODO
	* @param @param clientId
	* @param @param uploadFile
	* @param @param uploadFileFileName
	* @param @param resUploadFilePath
	* @param @param contenttype
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return String 
	* @throws
	 */
	public ChatResource uploadMediaResource(String clientId,File uploadFile,String uploadFileFileName,
			String resUploadFilePath,ContentTypeEnum contenttype)throws BizException,ErrorException;

	
	/**
	 * 
	* @Title: sendMessage 
	* @Description: 发送多媒体消息
	* @param @param sendclientid
	* @param @param receiveid
	* @param @param contenttype
	* @param @param rectype
	* @param @param content
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public ChatMessage sendMessage(String sendclientid, String receiveid, String rectype,
			String contenttype, String content, String caseid)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: revocatMessage  
	* @Description: 撤回消息
	* @param @param chatmessageid
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	public void revocatMessage(String clientid,String chatmessageid)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: getMessageListByClientAndPage 
	* @Description: 根据分页参数 获取用户的聊天记录
	* @param @param sendclientid
	* @param @param recclientid
	* @param @param page
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return List<MessagePush> 
	* @throws
	 */
	public List<ChatMessage> getMessageListByClientAndPage(String sendclientid,String recid,String rectype,
			Integer page, String content, String caseid)throws BizException, ErrorException;
	
	/**
	 * 
	* @Title: getMessageCountByClient 
	* @Description: 获取聊天总记录数
	* @param @param sendclientid
	* @param @param recid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return int 
	* @throws
	 */
	public long getMessageCountByClient(String sendclientid,String recid, String rectype, 
			String content, String caseid)throws BizException,ErrorException;
	
	/**
	 * 
	* @Title: getMessageListByMsgIdForCounts  
	* @Description: 获取某跳条聊天记录前后各10条消息
	* @param @param chatmessageid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return List<ChatMessage>    返回类型  
	* @throws
	 */
	public List<ChatMessage> getMessageListByMsgIdForCounts(String chatmessageid)throws BizException,ErrorException;
	
}
