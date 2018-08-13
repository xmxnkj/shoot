package com.szit.arbitrate.chat.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseServiceImpl;
import com.hsit.common.utils.DateUtils;
import com.hsit.common.utils.PagingBean;
import com.hsit.common.utils.UploadAccResourceUtil;
import com.szit.arbitrate.api.common.utils.PageUtils;
import com.szit.arbitrate.chat.dao.ChatMessageDao;
import com.szit.arbitrate.chat.entity.ChatMessage;
import com.szit.arbitrate.chat.entity.ChatResource;
import com.szit.arbitrate.chat.entity.ChatRoom;
import com.szit.arbitrate.chat.entity.ChatRoomClient;
import com.szit.arbitrate.chat.entity.ConsultClient;
import com.szit.arbitrate.chat.entity.MessagePush;
import com.szit.arbitrate.chat.entity.enumvo.ContentTypeEnum;
import com.szit.arbitrate.chat.entity.enumvo.RecTypeEnum;
import com.szit.arbitrate.chat.entity.enumvo.RoomStateEnum;
import com.szit.arbitrate.chat.entity.query.ChatMessageQuery;
import com.szit.arbitrate.chat.entity.query.ChatResourceQuery;
import com.szit.arbitrate.chat.entity.query.ChatRoomClientQuery;
import com.szit.arbitrate.chat.entity.query.ConsultClientQuery;
import com.szit.arbitrate.chat.entity.query.MessagePushQuery;
import com.szit.arbitrate.chat.service.ChatMessageService;
import com.szit.arbitrate.chat.service.ChatResourceService;
import com.szit.arbitrate.chat.service.ChatRoomClientService;
import com.szit.arbitrate.chat.service.ChatRoomService;
import com.szit.arbitrate.chat.service.ConsultClientService;
import com.szit.arbitrate.chat.service.MessagePushService;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.ClientToken;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.ClientTokenService;
import com.szit.arbitrate.mediation.entity.MediationCase;
import com.szit.arbitrate.mediation.service.MediationCaseService;
import com.szit.arbitrate.pushcentre.factory.PushMessageDisposeFactory;
import com.szit.arbitrate.pushcentre.service.PushCentreService;
import com.szit.arbitrate.pushcentre.vm.PushContentVm;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

/**
 * 
* @ProjectName:
* @ClassName: ChatMessageServiceImpl
* @Description:聊天消息业务接口实现类
* @author Administrator
* @date 2017年4月18日 下午4:55:35
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class ChatMessageServiceImpl extends AppBaseServiceImpl<ChatMessage, ChatMessageQuery> implements ChatMessageService{

	@Autowired
	private ChatRoomService chatRoomService;
	@Autowired
	private ChatRoomClientService chatRoomClientService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private MessagePushService messagePushService;
	@Autowired
	private PushCentreService pushCentreService;
	@Autowired
	private ChatResourceService chatResourceService;
	@Autowired
	private ClientTokenService clientTokenService;
	@Autowired
	private ConsultClientService consultClientSerivce;
	@Autowired
	private MediationCaseService mediationCaseService;
	@Autowired
	private ChatMessageDao dao;

	public ChatMessageDao getDao() {
		return dao;
	}

	public void setDao(ChatMessageDao dao) {
		this.dao = dao;
	}
	
	@Override
	public ChatResource uploadMediaResource(String clientId, File uploadFile,String uploadFileFileName, 
			String resUploadFilePath,ContentTypeEnum contenttype) throws BizException, ErrorException {
		String fileid = UploadAccResourceUtil.saveFile(uploadFile,uploadFileFileName,resUploadFilePath, contenttype.toString());
		ChatResource chatResource = new ChatResource();
		chatResource.setClientId(clientId);
		chatResource.setResType(contenttype);
		chatResource.setOriFileName(uploadFileFileName);
		chatResource.setResuploadfilename(fileid);
		chatResource.setResuploadfilepath(resUploadFilePath);
		chatResource.setRescreatedatatime(new Date());
		return chatResource;
	}
	
	
	@Override
	public ChatMessage sendMessage(String sendclientid, String receiveid,String rectype, 
			String contenttype,String content, String caseid) throws BizException, ErrorException {
		if(StringUtils.isEmpty(sendclientid) || StringUtils.isEmpty(receiveid) || StringUtils.isEmpty(content)
				|| StringUtils.isEmpty(rectype) || StringUtils.isEmpty(contenttype)){
			throw new BizException("非法参数,不能为空!");
		}
		Client client = clientService.getById(sendclientid);
		
		ChatResourceQuery chatResourceQuery = new ChatResourceQuery();
		// content 资源上传文件的id
		chatResourceQuery.setResuploadfilename(content);
		
		ChatResource chatResource = chatResourceService.getEntity(chatResourceQuery);
		
		//2.新建消息实体
		ChatMessage entity = new ChatMessage();
		entity.setSendClientId(sendclientid);
		entity.setReceiveId(receiveid);
		entity.setRecType(RecTypeEnum.valueOf(rectype));
		entity.setContentType(ContentTypeEnum.valueOf(contenttype));
	    entity.setContent(content);
	    //OriFileName  资源文件名
		if(chatResource != null && StringUtils.isNotEmpty(chatResource.getOriFileName())){
			entity.setOriFileName(chatResource.getOriFileName());
		}
		if(StringUtils.isNotEmpty(caseid)){
			entity.setCaseId(caseid);
		}
		entity.setSendTime(new Date());
		
		String messageId = this.save(entity);
		//构建推送参数
		Map<String, Object> params = Maps.newHashMap();
		params.put("sendclientid", sendclientid);
		String alertMessage = "您有一条新消息";
		params.put("alertMessage", alertMessage);
		
		//3.消息分发
		//如果接受者类型是 ChatRoom，则receiveid等于聊天室id，将消息分发至聊天室的所有成员
		if(rectype.equals(RecTypeEnum.ChatRoom.toString())){
			ChatRoom chatRoom = chatRoomService.getById(receiveid);
			if(chatRoom == null){
				throw new BizException("聊天室不存在!");
			}
			if(chatRoom.getRoomState().equals(RoomStateEnum.Stop) && !chatRoom.getOpenClientId().equals(sendclientid)){
				throw new BizException("聊天室暂停中，无法发送消息!");
			}
			String caseId = chatRoom.getCaseid();
			MediationCase mediationCase = mediationCaseService.getById(caseId);
			String sendClientDesc = mediationCaseService.getSendClientDescByCaseId(caseId, sendclientid);
			entity.setSendClientDesc(sendClientDesc);
			this.save(entity);
			
			ChatRoomClientQuery chatRoomClientQuery = new ChatRoomClientQuery();
			chatRoomClientQuery.setChatRoomId(receiveid);
			List<ChatRoomClient> list = chatRoomClientService.getEntities(chatRoomClientQuery);
			if(list != null && list.size() > 0){
				for(int i = 0; i < list.size(); i ++){
					ChatRoomClient chatRoomClient = list.get(i);
					String receiveClientId = chatRoomClient.getClientId();
					ClientToken clientToken = clientTokenService.getClientTokenByClientId(receiveClientId);
					if(!receiveClientId.equals(sendclientid) && clientToken != null){//不给发送者自己推送
						MessagePush messagePush = new MessagePush();
						messagePush.setChatMessageId(messageId);
						messagePush.setCaseId(chatRoomClient.getCaseid());
						messagePush.setContent(content);
						messagePush.setContentType(ContentTypeEnum.valueOf(contenttype));
						messagePush.setSendClientId(sendclientid);
						messagePush.setSendClientName(client.getIdentifyName());
						messagePush.setSendClientImage(client.getHeadImgFile());
						messagePush.setReceiveClientId(receiveClientId);
						messagePush.setPushTime(entity.getSendTime());
						messagePush.setChatRoomId(receiveid);
						messagePush.setSendClientDesc(sendClientDesc);
						if(StringUtils.isNotEmpty(caseid)){
							messagePush.setCaseId(caseid);
						}
						messagePushService.save(messagePush);
						params.put("receiveClientId", receiveClientId);
						params.put("messagePush", messagePush);
						params.put("casestate", mediationCase.getCaseState().toString());
						
						PushMessageDisposeFactory.bulidPush(PushTypeEnum.RoomChat, params);
					}
				}
			}
		}else{//说明是发送给个人
			
			if(StringUtils.isNotEmpty(caseid)){
				String sendClientDesc = mediationCaseService.getSendClientDescByCaseId(caseid, sendclientid);
				entity.setSendClientDesc(sendClientDesc);
				this.save(entity);
			}
			
			ConsultClientQuery consultClientQuery = new ConsultClientQuery();
			if(receiveid.equals("1")){//说明是用户咨询管理员，保存用户咨询基本信息
				consultClientQuery.setClientId(sendclientid);
				
			}else if(sendclientid.equals("1")){//说明是管理员回复用户咨询，
				consultClientQuery.setClientId(receiveid);
				
			}
			ConsultClient consultClient = consultClientSerivce.getEntity(consultClientQuery);
			if(receiveid.equals("1") || sendclientid.equals("1")){
				if(consultClient == null){
					consultClient = new ConsultClient();
					consultClient.setClientId(sendclientid);
				}
				if(sendclientid.equals("1")){//说明是管理员回复用户咨询，
					consultClient.setReadflag(true);
				}else
					consultClient.setReadflag(false);
				//重新设置头像
				consultClient.setClientImage(client.getHeadImgFile());
				//重新设置名字
				consultClient.setClientName(client.getIdentifyName());
				consultClient.setConsultTime(new Date());
				consultClient.setSendClientId(sendclientid);
				consultClient.setContent(content);
				consultClient.setContentType(ContentTypeEnum.valueOf(contenttype));
				consultClientSerivce.save(consultClient);
			}
			
			PushTypeEnum pushTypeEnum = null;
			MessagePush messagePush = new MessagePush();
			messagePush.setChatMessageId(messageId);
			messagePush.setContent(content);
			messagePush.setContentType(ContentTypeEnum.valueOf(contenttype));
			messagePush.setSendClientId(sendclientid);
			messagePush.setSendClientName(client.getIdentifyName());
			messagePush.setSendClientImage(client.getHeadImgFile());
			messagePush.setReceiveClientId(receiveid);
			messagePush.setPushTime(entity.getSendTime());
			
			if(StringUtils.isNotEmpty(caseid)){
				String sendClientDesc = mediationCaseService.getSendClientDescByCaseId(caseid, sendclientid);
				messagePush.setSendClientDesc(sendClientDesc);
				messagePush.setCaseId(caseid);
			}
			
			messagePushService.save(messagePush);
			if(rectype.equals(RecTypeEnum.Client.toString())){
				if(sendclientid.equals("1")){
					pushTypeEnum = PushTypeEnum.AdminChat;
				}else{
					pushTypeEnum = PushTypeEnum.OneChat;
				}
			}else if(rectype.equals(RecTypeEnum.Admin.toString())){
				pushTypeEnum = PushTypeEnum.AdminChat;
			}
			params.put("receiveClientId", receiveid);
			params.put("messagePush", messagePush);
			params.put("caseId", caseid);
			PushMessageDisposeFactory.bulidPush(pushTypeEnum, params);
		}
		return entity;
		
	}
	
	@Override
	public void revocatMessage(String clientid,String chatmessageid) throws BizException,
			ErrorException {
		ChatMessage entity = this.getById(chatmessageid);
		if(entity == null){
			throw new BizException("找不到消息!");
		}
		this.deleteById(chatmessageid);
		MessagePushQuery messagePushQuery = new MessagePushQuery();
		messagePushQuery.setChatMessageId(chatmessageid);
		List<MessagePush> list = messagePushService.getEntities(messagePushQuery);
		if(list != null && list.size() > 0){
			for(int i = 0; i < list.size(); i ++){
				MessagePush messagePush = list.get(i);
				messagePushService.deleteById(messagePush.getId());
				PushContentVm pushcontent = new PushContentVm(clientid, "", 
						"", PushTypeEnum.RevocateMessage, messagePush);
				pushCentreService.pushUnifyMessage(PushTypeEnum.RevocateMessage, "", false, messagePush.getReceiveClientId(),
						"", "撤回消息", pushcontent, 1);
			}
		}
	}
	
	@Override
	public List<ChatMessage> getMessageListByClientAndPage(String sendclientid,
			String recid, String rectype, Integer page, String content, String caseid) throws BizException,
			ErrorException {
		if(StringUtils.isEmpty(sendclientid) || StringUtils.isEmpty(recid) 
				|| StringUtils.isEmpty(rectype)){
			throw new BizException("非法参数,不能为空!");
		}
		
		long count = this.getMessageCountByClient(sendclientid, recid, rectype, content, caseid);
		
		List<ChatMessage> list = null;
		PagingBean pagingBean = new PagingBean(page, PageUtils.PAGE_SIZE);
		pagingBean.setTotalItems(new Long(count).intValue());
		pagingBean.setTotalPages();
		pagingBean.setStartIndex();
		pagingBean.setLastIndex();
		list = dao.getMessageListByClientAndPage(sendclientid, rectype, recid, content, caseid, pagingBean);
		return list;
	}
	
	@Override
	public long getMessageCountByClient(String sendclientid, String recid, String rectype, 
			String content, String caseid)
			throws BizException, ErrorException {
		return dao.getMessageCountByClient(sendclientid, recid, rectype, content, caseid);
	}
	
	@Override
	public List<ChatMessage> getMessageListByMsgIdForCounts(String chatmessageid)
			throws BizException, ErrorException {
		
		ChatMessage entity = this.getById(chatmessageid);
		if(entity == null){
			throw new BizException("消息不存在!");
		}
		String sendClientId = entity.getSendClientId();
		String receiveId = entity.getReceiveId();
		RecTypeEnum receiveType = entity.getRecType();
		String sendTime = DateUtils.parseToString(entity.getSendTime(), DateUtils.DATE_YMDHMS_STR);
		
		return dao.getMessageListByMsgIdForCounts(sendClientId, receiveType, receiveId, sendTime);
	}
	
}
