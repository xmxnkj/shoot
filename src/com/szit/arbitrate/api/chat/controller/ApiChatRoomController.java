package com.szit.arbitrate.api.chat.controller;

import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;

public interface ApiChatRoomController {
	
	/**
	 * 
	* @Title: openChatRoom 
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
	* @param @param chatroomid
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm getChatRoomInfoByRoomId(String chatroomid);

	/**
	 * 
	* @Title: pauseChatRoom 
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
	* @param @param clientid
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public ApiOutParamsVm createNeteaseCloudAccount(String clientid);
	
	/**
	 * 
	* @Title: GetNeteaseCloudAccount  
	* @param @param clientid
	* @param @return    设定文件  
	* @return ApiOutParamsVm    返回类型  
	* @throws
	 */
	public ApiOutParamsVm getNeteaseCloudAccount(String clientid);
	
}
