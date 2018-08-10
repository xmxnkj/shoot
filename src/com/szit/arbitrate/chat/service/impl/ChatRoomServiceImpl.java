package com.szit.arbitrate.chat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.arbitrate.basisset.entity.SysParameterTable;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;
import com.szit.arbitrate.basisset.service.SysParameterTableService;
import com.szit.arbitrate.chat.dao.ChatRoomDao;
import com.szit.arbitrate.chat.entity.ChatRoom;
import com.szit.arbitrate.chat.entity.ChatRoomClient;
import com.szit.arbitrate.chat.entity.enumvo.RoomStateEnum;
import com.szit.arbitrate.chat.entity.query.ChatRoomClientQuery;
import com.szit.arbitrate.chat.entity.query.ChatRoomQuery;
import com.szit.arbitrate.chat.service.ChatRoomClientService;
import com.szit.arbitrate.chat.service.ChatRoomService;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.client.entity.enumvo.CertificateStateEnum;
import com.szit.arbitrate.client.entity.query.TempClientQuery;
import com.szit.arbitrate.client.service.AuthorityGroupOperationService;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.TempClientService;
import com.szit.arbitrate.mediation.entity.MediationCase;
import com.szit.arbitrate.mediation.service.MediationCaseService;
import com.szit.arbitrate.pushcentre.factory.PushMessageDisposeFactory;
import com.szit.arbitrate.pushcentre.service.PushCentreService;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

/**
 * 
* @ProjectName:
* @ClassName: ChatRoomServiceImpl
* @Description:会议室业务接口实现类
* @author Administrator
* @date 2017年4月18日 下午4:58:45
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class ChatRoomServiceImpl extends AppBaseServiceImpl<ChatRoom, ChatRoomQuery> implements ChatRoomService{

	@Autowired
	private ChatRoomClientService chatRoomClientService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private MediationCaseService mediationCaseService;
	@Autowired
	private TempClientService tempClientService;
	@Autowired
	private AuthorityGroupOperationService authorityGroupOperationService;
	@Autowired
	private PushCentreService pushCentreService;
	@Autowired
	private SysParameterTableService sysParameterTableService;
	@Autowired
	private ChatRoomDao dao;

	public ChatRoomDao getDao() {
		return dao;
	}

	public void setDao(ChatRoomDao dao) {
		this.dao = dao;
	}
	
	@Override
	public ChatRoom getChatRoomByCaseId(String caseid) throws BizException,
			ErrorException {
		ChatRoomQuery query = new ChatRoomQuery();
		query.setCaseid(caseid);
		ChatRoom chatRoom = this.getEntity(query);
		return chatRoom;
	}
	
	@Override
	public synchronized ChatRoom openChatRoom(String clientid, String caseid, String roomname)
			throws BizException, ErrorException {
		
		Client client = clientService.getById(clientid);
		if(client == null){
			throw new BizException("员不存在!");
		}
		boolean isAllCalled = tempClientService.isCaseClientAllCalled(caseid);
		if(!isAllCalled){
			throw new BizException("当事人未全部召集，无法开启会议室!");
		}
		MediationCase mediationCase = mediationCaseService.getById(caseid);
		if(!mediationCase.getMediatorClientId().equals(clientid)){
			throw new BizException("你没有该操作权限，无法申请!");
		}
		mediationCase.setMediateTime(new Date());
		mediationCase.setRoomOpen(true);
		mediationCaseService.save(mediationCase);
		
		//2.查询该是否已经有聊天室,每个对应一个聊天室
		ChatRoom chatRoom = getChatRoomByCaseId(caseid);
		if(chatRoom != null){
			throw new BizException("该聊天室已开启!");
		}
		//3.创建聊天室
		chatRoom = new ChatRoom();
		chatRoom.setCaseid(caseid);
		chatRoom.setRoomName(roomname);
		chatRoom.setOpenClientId(clientid);
		chatRoom.setOpenTime(new Date());
		chatRoom.setNotice("请保持理智沟通!");
		chatRoom.setRoomState(RoomStateEnum.Open);
		String chatRoomId = this.save(chatRoom);
		
		//4.强所有按键当事人加入聊天室用户表
		//4.1 将员加入聊天室
		ChatRoomClient chatRoomClient = new ChatRoomClient();
		chatRoomClient.setCaseid(caseid);
		chatRoomClient.setChatRoomId(chatRoomId);
		chatRoomClient.setClientId(clientid);
		chatRoomClient.setClientName(client.getIdentifyName());
		chatRoomClient.setClientImage(client.getHeadImgFile());
		chatRoomClient.setClientDesc("员");
		chatRoomClientService.save(chatRoomClient);
		//4.2 将申请人加入聊天室
		chatRoomClient = new ChatRoomClient();
		chatRoomClient.setCaseid(caseid);
		chatRoomClient.setChatRoomId(chatRoomId);
		chatRoomClient.setClientId(mediationCase.getApplyClientId());
		chatRoomClient.setClientName(mediationCase.getApplyClient().getIdentifyName());
		chatRoomClient.setClientImage(mediationCase.getApplyClient().getHeadImgFile());
		chatRoomClient.setClientDesc("申述人");
		chatRoomClientService.save(chatRoomClient);
		//4.3 将所有被申述人加入聊天室
		TempClientQuery tempClientQuery = new TempClientQuery();
		tempClientQuery.setCaseId(caseid);
		List<TempClient> list = tempClientService.getEntities(tempClientQuery);
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				TempClient tempClient = list.get(i);
				if(tempClient.getClientId() != null){
					Client temp = clientService.getById(tempClient.getClientId());
					if(temp != null){
						chatRoomClient = new ChatRoomClient();
						chatRoomClient.setCaseid(caseid);
						chatRoomClient.setChatRoomId(chatRoomId);
						chatRoomClient.setClientId(tempClient.getClientId());
						chatRoomClient.setClientName(temp.getIdentifyName());
						chatRoomClient.setClientImage(temp.getHeadImgFile());
						if(tempClient.isPartB()){
							chatRoomClient.setClientDesc("被申述人");
						}else{
							chatRoomClient.setClientDesc("第三方");
						}
						chatRoomClientService.save(chatRoomClient);
					}
				}
			}
		}
		
		return chatRoom;
	}
	
	@Override
	public ChatRoom getChatRoomInfo(String clientid, String caseid)
			throws BizException, ErrorException {
		if(StringUtils.isEmpty(caseid) || StringUtils.isEmpty(clientid)){
			throw new BizException("参数不能为空!");
		}
		MediationCase mediationCase = mediationCaseService.getById(caseid);
		if(mediationCase == null){
			throw new BizException("不存在!");
		}
		Client client = clientService.getById(clientid);
		SysParameterTable sysparametertable = 
				sysParameterTableService.getSysParameterTable(ParameterTypeEnum.certificateswitch, "enterChatRoomSwitch");
		//开启认证验证
		if(sysparametertable != null && sysparametertable.getParameterinitvla().equals("1")){
			if(!clientid.equals(mediationCase.getMediatorClientId()) && 
					!client.getAuditInfo().equals(CertificateStateEnum.Pass)){
				throw new BizException("无法进入聊天室,请先实名认证!");
			}
		}
		ChatRoomQuery chatRoomQuery = new ChatRoomQuery();
		chatRoomQuery.setCaseid(caseid);
		ChatRoom entity = this.getEntity(chatRoomQuery);
		if(entity == null){
			throw new BizException("聊天室未开启!");
		}
		return entity;
	}
	
	@Override
	public void openMediaChatRoom(String clientid, String caseid,
			String roomtype, boolean oper) throws BizException, ErrorException {
		Client client = clientService.getById(clientid);
		if(client == null){
			throw new BizException("员不存在!");
		}
		MediationCase mediationCase = mediationCaseService.getById(caseid);
		if(!mediationCase.getMediatorClientId().equals(clientid)){
			throw new BizException("你没有该操作权限!");
		}
		//2.查询该是否已经有聊天室,每个对应一个聊天室
		ChatRoom chatRoom = getChatRoomByCaseId(caseid);
		if(chatRoom == null){
			throw new BizException("该聊天室未开启!");
		}
		String alertMessage = "";
		PushTypeEnum pushTypeEnum = null;
		if(roomtype.equals("voice")){
			if(oper){
				alertMessage = "语音聊天室已开启!";
				pushTypeEnum = PushTypeEnum.OpenNeteaseChat;
				chatRoom.setVoiceRoomOpen(true);
			}else{
				alertMessage = "语音聊天室已关闭!";
				pushTypeEnum = PushTypeEnum.CloseNeteaseChat;
				chatRoom.setVoiceRoomOpen(false);
			}
		}else if(roomtype.equals("video")){
			if(oper){
				alertMessage = "音视频聊天室已开启!";
				pushTypeEnum = PushTypeEnum.OpenNeteaseChat;
				chatRoom.setVideoRoomOpen(true);
			}else{
				alertMessage = "音视频聊天室已关闭!";
				pushTypeEnum = PushTypeEnum.CloseNeteaseChat;
				chatRoom.setVideoRoomOpen(false); 
			}
		}
		this.save(chatRoom);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pushAlertMessage", alertMessage);
		map.put("caseid", caseid);
		map.put("chatroomid", chatRoom.getId());
		map.put("caseexplain", mediationCase.getCaseExplain());
		map.put("mediatorclientid", clientid);
		List<String> pushclientlist = new ArrayList<String>();
		
		ChatRoomClientQuery chatRoomClientQuery = new ChatRoomClientQuery();
		chatRoomClientQuery.setCaseid(caseid);
		List<ChatRoomClient> list = chatRoomClientService.getEntities(chatRoomClientQuery);
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				ChatRoomClient chatRoomClient = list.get(i);
				if(!chatRoomClient.getClientId().equals(clientid)){
					//推送
					String pushClientId = chatRoomClient.getClientId();
					pushclientlist.add(pushClientId);
					
				}
			}
		}
		map.put("pushclientlist", pushclientlist);
		PushMessageDisposeFactory.bulidPush(pushTypeEnum, map);
	}
	
	@Override
	public void pauseChatRoom(String clientid, String roomid, Integer opertype)throws BizException, ErrorException {
		if(StringUtils.isEmpty(clientid) || StringUtils.isEmpty(roomid) || opertype == null){
			throw new BizException("非法参数,不能为空!");
		}
		ChatRoom chatRoom = this.getById(roomid);
		if(chatRoom == null){
			throw new BizException("聊天室不存在!");
		}
		if(!clientid.equals(chatRoom.getOpenClientId())){
			throw new BizException("你没有操作权限!");
		}
		PushTypeEnum pushTypeEnum = null;
		String alertMessage = "";
		if(opertype == 0){//暂停操作
			if(chatRoom.getRoomState() == RoomStateEnum.Stop){
				throw new BizException("已经暂停!");
			}
			chatRoom.setRoomState(RoomStateEnum.Stop);
			chatRoom.setVideoRoomOpen(false);
			pushTypeEnum = PushTypeEnum.PauseChatRoom;
			alertMessage = "聊天室暂停成功!";
		}else if(opertype == 1){//开启操作
			if(chatRoom.getRoomState() == RoomStateEnum.Open){
				throw new BizException("已经开启!");
			}
			chatRoom.setRoomState(RoomStateEnum.Open);
			pushTypeEnum = PushTypeEnum.OpenChatRoom;
			alertMessage = "聊天室开启成功!";
		}
		this.save(chatRoom);
		
		//1.推送通知
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pushAlertMessage", alertMessage);
		String mediatorid = clientid;
		map.put("mediatorid", mediatorid);
		String chatroomstate = chatRoom.getRoomState().toString();
		map.put("chatroomstate", chatroomstate);
		
		List<String> pushclientlist = new ArrayList<String>();
		
		
		//推送
		ChatRoomClientQuery chatRoomClientQuery = new ChatRoomClientQuery();
		chatRoomClientQuery.setCaseid(chatRoom.getCaseid());
		List<ChatRoomClient> list = chatRoomClientService.getEntities(chatRoomClientQuery);
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				ChatRoomClient chatRoomClient = list.get(i);
				if(!chatRoomClient.getClientId().equals(clientid)){
					String pushClientId = chatRoomClient.getClientId();
					pushclientlist.add(pushClientId);
				}
			}
		}
		map.put("pushclientlist", pushclientlist);
		PushMessageDisposeFactory.bulidPush(pushTypeEnum, map);
	}
	
	
}
 