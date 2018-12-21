package com.szit.arbitrate.api.chat.controller.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.utils.PagingBean;

import com.szit.arbitrate.api.base.BaseController;
import com.szit.arbitrate.api.chat.controller.ApiChatMessageController;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.utils.ApiConstant;
import com.szit.arbitrate.api.common.utils.PageUtils;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;
import com.szit.arbitrate.api.common.vm.PageDataOutBoVm;
import com.szit.arbitrate.chat.entity.ChatMessage;
import com.szit.arbitrate.chat.entity.ChatResource;
import com.szit.arbitrate.chat.entity.ConsultClient;
import com.szit.arbitrate.chat.entity.enumvo.ContentTypeEnum;
import com.szit.arbitrate.chat.entity.query.ChatMessageQuery;
import com.szit.arbitrate.chat.entity.query.ConsultClientQuery;
import com.szit.arbitrate.chat.service.ChatMessageService;
import com.szit.arbitrate.chat.service.ConsultClientService;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.service.ClientService;

@Component("wapApiChatMessageController")
public class ApiChatMessageControllerImpl extends BaseController<ChatMessage, ChatMessageQuery>
	implements ApiChatMessageController{
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private ChatMessageService service;
	@Autowired
	private ConsultClientService consultClientService;
	
	@Override
	public ApiOutParamsVm getMessageListByClientAndPage(String sendclientid,
			String recid, String rectype, Integer page, String content,String caseid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		if(StringUtils.isEmpty(sendclientid) || StringUtils.isEmpty(recid)){
			throw new BizException("非法参数，不能为空!");
		}
		String messContent = content==null?"":content;
		long count = service.getMessageCountByClient(sendclientid, recid, rectype, messContent, caseid);
		List<ChatMessage> list = service.getMessageListByClientAndPage(sendclientid, recid, 
				rectype, page, messContent, caseid);
		PagingBean pagingBean = new PagingBean(page, PageUtils.PAGE_SIZE);
		pagingBean.setTotalItems(new Long(count).intValue());
		pagingBean.setTotalPages();

		PageDataOutBoVm<ChatMessage> pageList = new PageDataOutBoVm<ChatMessage>(pagingBean,list);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,pageList);
	}
	
	@Override
	public ApiOutParamsVm uploadMediaResource(String clientid, String restype) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		ContentTypeEnum contenttype = ContentTypeEnum.valueOf(restype);
		//1.保存多媒体文件
		String resuploadfilepath = "";
		if(contenttype.equals(ContentTypeEnum.File)){
			resuploadfilepath = "uploads\\chat\\file";
		}else if(contenttype.equals(ContentTypeEnum.Image)){
			resuploadfilepath = "uploads\\chat\\image";
		}else if(contenttype.equals(ContentTypeEnum.Video)){
			resuploadfilepath = "uploads\\chat\\video";
		}else if(contenttype.equals(ContentTypeEnum.Voice)){
			resuploadfilepath = "uploads\\chat\\voice";
		}
		ChatResource entity = service.uploadMediaResource(clientId, getUpload(), getUploadFileName(), 
				resuploadfilepath, contenttype);
		return ApiTools.bulidOutSucceed("上传成功!",entity);
	}
	
	@Override
	public ApiOutParamsVm sendMessage(String sendclientid, String receiveid,
			String rectype, String contenttype, String content, String caseid) {
//		String clientId = clientService.isLoginSessionOnline(getRequest());
//		if(StringUtils.isEmpty()){
//			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
//		}
		 
		ChatMessage entity = service.sendMessage(sendclientid, receiveid, 
				rectype, contenttype, content, caseid);
		return ApiTools.bulidOutSucceed("发送成功!", entity);
	}
	
	@Override
	public ApiOutParamsVm revocatMessage(String clientid, String chatmessageid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		service.revocatMessage(clientId,chatmessageid);
		return ApiTools.bulidOutSucceed("撤回成功!");
	}
	
//	public ApiOutParamsVm setMessgaeState(){
//		
//	}
	
	@Override
	public ApiOutParamsVm getConsultClientListByPage(Integer page) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		ConsultClientQuery query = new ConsultClientQuery();
		query.setPage(page!=null?page:0, PageUtils.PAGE_SIZE);
		query.addOrder("consultTime", true);
		List<ConsultClient> list = consultClientService.getEntities(query);
		//设置已读 true已读 false未读
//		if(list!=null && list.size()>0){
//			for(int i=0;i<list.size();i++){
//				if(!list.get(i).isReadflag()){
//					list.get(i).setReadflag(true);
//					consultClientService.save(list.get(i));
//				}
//			}
//		}
		Client client = null;
		for(ConsultClient consultClient:list){
			client = clientService.getById(consultClient.getClientId());
			consultClient.setClientImage(client.getHeadImgFile());
			consultClient.setClientName(client.getIdentifyName());
		}
		PageDataOutBoVm<ConsultClient> pageList = new PageDataOutBoVm<ConsultClient>(query.getPaging(),list);

		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,pageList);
	}
	
	@Override
	public ApiOutParamsVm getMessageListByMsgIdForCounts(String chatmessageid) {
		String clientId = clientService.isLoginSessionOnline(getRequest());
		if(StringUtils.isEmpty(clientId)){
			return ApiTools.bulidOutFail(ApiConstant.ILLEGALITY_PARAM_LOGIN);
		}
		List<ChatMessage> list = service.getMessageListByMsgIdForCounts(chatmessageid);
		return ApiTools.bulidOutSucceed(ApiTools.GETDATASUCCEED,list);
	}

}
