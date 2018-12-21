package com.szit.arbitrate.api.chat.controller.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.hsit.common.exceptions.BizException;
import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.chat.controller.ApiChatRoomController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.chat.entity.ChatRoom;
import com.szit.arbitrate.chat.entity.ChatRoomClient;
import com.szit.arbitrate.chat.entity.query.ChatRoomClientQuery;
import com.szit.arbitrate.chat.entity.query.ChatRoomQuery;
import com.szit.arbitrate.chat.service.ChatRoomClientService;
import com.szit.arbitrate.chat.service.ChatRoomService;
import com.szit.arbitrate.client.entity.NeteaseClient;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.NeteaseClientService;

@Component("wapApiChatRoomController")
public class ApiChatRoomControllerImpl extends BaseController<ChatRoom, ChatRoomQuery> implements ApiChatRoomController{

	@Autowired
	private ClientService clientService;
	@Autowired
	private NeteaseClientService neteaseClientService;
	@Autowired
	private ChatRoomService service;
	@Autowired
	private ChatRoomClientService chatRoomClientService;
	
	@Override
	public ApiOutParamsVm openChatRoom(String clientid, String caseid,
			String roomname) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		ChatRoom entity = service.openChatRoom(clientId, caseid, roomname);
		return ApiTools.bulidOutSucceed("开启成功",entity);
	}
	
	@Override
	public ApiOutParamsVm operMediaChatRoom(String clientid, String caseid,
			String roomtype, boolean oper) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.openMediaChatRoom(clientId, caseid, roomtype, oper);
		return ApiTools.bulidOutSucceed("操作成功");
	}
	
	@Override
	public ApiOutParamsVm getMediaChatRoomState(String roomid, String roomtype) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		if(StringUtils.isEmpty(roomid) || StringUtils.isEmpty(roomtype)){
			throw new BizException("参数不能为空");
		}
		ChatRoom entity = service.getById(roomid);
		if(entity == null){
			throw new BizException("找不到聊天室");
		}
		boolean openstate = false;
		if(roomtype.equals("voice")){
			openstate = entity.isVoiceRoomOpen();
		}else if(roomtype.equals("video")){
			openstate = entity.isVideoRoomOpen();
		}
		return ApiTools.bulidOutSucceed("操作成功",openstate);
	}
	
	@Override
	public ApiOutParamsVm getChatRoomInfo(String clientid, String caseid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String, Object> map = Maps.newHashMap();
		ChatRoom entity = service.getChatRoomInfo(clientId, caseid);
		map.put("entity", entity);
		ChatRoomClientQuery chatRoomClientQuery = new ChatRoomClientQuery();
		chatRoomClientQuery.setCaseid(caseid);
		List<ChatRoomClient> list = chatRoomClientService.getEntities(chatRoomClientQuery);
		map.put("list", list);
		return ApiTools.bulidOutSucceed("获取成功", map);
	}
	
	@Override
	public ApiOutParamsVm getChatRoomInfoByRoomId(String chatroomid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		Map<String, Object> map = Maps.newHashMap();
		ChatRoom entity = service.getById(chatroomid);
		if(entity == null){
			throw new BizException("聊天室不存在!");
		}
		map.put("entity", entity);
		ChatRoomClientQuery chatRoomClientQuery = new ChatRoomClientQuery();
		chatRoomClientQuery.setCaseid(entity.getCaseid());
		List<ChatRoomClient> list = chatRoomClientService.getEntities(chatRoomClientQuery);
		map.put("list", list);
		return null;
	}
	
	@Override
	public ApiOutParamsVm pauseOrContinueChatRoom(String clientid, String roomid, Integer opertype) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.pauseChatRoom(clientId, roomid, opertype);
		return ApiTools.bulidOutSucceed("操作成功");
	}
	
	@Override
	public ApiOutParamsVm createNeteaseCloudAccount(String clientid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		NeteaseClient entity = neteaseClientService.createNeteaseCloudAccount(clientId);
		return ApiTools.bulidOutSucceed("创建成功", entity);
	}
	
	@Override
	public ApiOutParamsVm getNeteaseCloudAccount(String clientid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		NeteaseClient entity = neteaseClientService.GetNeteaseCloudAccount(clientId);
		return ApiTools.bulidOutSucceed("获取数据成功", entity);
	}
	
}
