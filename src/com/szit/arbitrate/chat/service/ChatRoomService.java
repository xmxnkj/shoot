package com.szit.arbitrate.chat.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.chat.entity.ChatRoom;
import com.szit.arbitrate.chat.entity.query.ChatRoomQuery;

/**
 * 
* @ProjectName:
* @ClassName: ChatRoomService
* @Description:会议室业务接口类
* @author Administrator
* @date 2017年4月18日 下午4:58:06
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface ChatRoomService extends AppBaseService<ChatRoom, ChatRoomQuery>{

	/**
	 * 
	* @Title: getChatRoomCountByCaseId 
	* @Description: 获取是否已经开启会议室
	* @param @param caseid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return long 
	* @throws
	 */
	public ChatRoom getChatRoomByCaseId(String caseid)throws BizException, ErrorException;
	/**
	 * 
	* @Title: openChatRoom 
	* @Description: 开启会议室，只有普通员才有该权限
	* @param @param clientid
	* @param @param caseid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return ChatRoom 
	* @throws
	 */
	public ChatRoom openChatRoom(String clientid, String caseid, String roomname)throws BizException, ErrorException;
	
	/**
	 * 
	* @Title: openMediaChatRoom  
	* @Description: 开启语音视频聊天室
	* @param @param clientid
	* @param @param caseid
	* @param @param roomtype 
	* @param @param oper true开启 false关闭
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	public void openMediaChatRoom(String clientid, String caseid, String roomtype, boolean oper)throws BizException, ErrorException;
	
	/**
	 * 
	* @Title: getChatRoomInfo 
	* @Description: 获取聊天室
	* @param @param clientid
	* @param @param caseid
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException
	* @return ChatRoom 
	* @throws
	 */
	public ChatRoom getChatRoomInfo(String clientid, String caseid)throws BizException, ErrorException;
	
	/**
	 * 
	* @Title: pauseChatRoom 
	* @Description: 暂停，即暂停聊天室功能
	* @param @param clientid
	* @param @param roomid
	* @param @param opertype 0暂停1开启
	* @param @throws BizException
	* @param @throws ErrorException
	* @return void 
	* @throws
	 */
	public void pauseChatRoom(String clientid, String roomid, Integer opertype)throws BizException, ErrorException;
	
	
}
