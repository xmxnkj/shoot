package com.szit.arbitrate.api.chat.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

public interface ApiChatRoomController {
	
	/**
	 * 
	* @Title: openChatRoom 
	* @Description: 开启聊天室
	* @param @param clientid
	* @param @param caseid
	* @param @param roomname
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm openChatRoom(String clientid, String caseid, String roomname);
	
	/**
	 * 
	* @Title: ApiOutParamsVm  
	* @Description: 操作多媒体聊天室
	* @param @param clientid
	* @param @param caseid
	* @param @param roomtype
	* @param @param oper    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	public ApiOutParamsVm operMediaChatRoom(String clientid, String caseid,String roomtype, boolean oper);
	
	/**
	 * 
	* @Title: getMediaChatRoomState  
	* @Description: 获取语音视频聊天室开启套状态
	* @param @param roomid
	* @param @param roomtype
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm getMediaChatRoomState(String roomid, String roomtype);
	
	/**
	 * 
	* @Title: getChatRoomInfo 
	* @Description: 获取用户所属聊天室
	* @param @param clientid
	* @param @param caseid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm getChatRoomInfo(String clientid, String caseid);
	
	/**
	 * 
	* @Title: getChatRoomInfoByRoomId  
	* @Description: 根据聊天室id获取聊天室
	* @param @param chatroomid
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm getChatRoomInfoByRoomId(String chatroomid);

	/**
	 * 
	* @Title: pauseChatRoom 
	* @Description: 暂停聊天室
	* @param @param clientid
	* @param @param roomid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm pauseOrContinueChatRoom(String clientid, String roomid, Integer opertype);
	
	/**
	 * 
	* @Title: createNeteaseCloudAccount 
	* @Description: 创建网易云通信ID
	* @param @param clientid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm createNeteaseCloudAccount(String clientid);
	
	/**
	 * 
	* @Title: GetNeteaseCloudAccount  
	* @Description: 获取网易云信账号信息
	* @param @param clientid
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm getNeteaseCloudAccount(String clientid);
	
}
