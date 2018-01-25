package com.szit.arbitrate.mediation.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.arbitrate.chat.entity.ChatMessage;
import com.szit.arbitrate.chat.entity.enumvo.ContentTypeEnum;
import com.szit.arbitrate.chat.service.ChatMessageService;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.dao.MediationResourceDao;
import com.szit.arbitrate.mediation.entity.MediationResource;
import com.szit.arbitrate.mediation.entity.enumvo.CaseResTypeEnum;
import com.szit.arbitrate.mediation.entity.query.MediationResourceQuery;
import com.szit.arbitrate.mediation.service.MediationResourceService;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: MediationResourceServiceImpl
* @Description:调解资源业务实现类
* @author Administrator
* @date 2017年3月23日 上午11:38:22
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class MediationResourceServiceImpl extends AppBaseServiceImpl<MediationResource, MediationResourceQuery>
	implements MediationResourceService{
	
	@Autowired
	private MediationResourceDao dao;
	@Autowired
	private ChatMessageService chatMessageService;
	@Autowired
	private ClientService clientService;

	public MediationResourceDao getDao() {
		return dao;
	}

	public void setDao(MediationResourceDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void saveMediationReource(String clientid, String caseid, String chatmessageid) throws BizException,
			ErrorException {
		ChatMessage chatMessage = chatMessageService.getById(chatmessageid);
		if(chatMessage == null){
			throw new BizException("找不到资源!");
		}
		Client client = clientService.getById(clientid);
		MediationResourceQuery query = new MediationResourceQuery();
		query.setResuploadfileid(chatMessage.getContent());
		MediationResource entity = this.getEntity(query);
		if(entity != null){
			throw new BizException("重复添加!");
		}
		entity = new MediationResource();
		entity.setCaseId(caseid);
		entity.setClientId(clientid);
		entity.setClientName(client.getIdentifyName());
		entity.setClientImage(client.getHeadImgFile());
		entity.setResuploadfileid(chatMessage.getContent());
		entity.setResType(CaseResTypeEnum.valueOf(chatMessage.getContentType().toString()));
		entity.setRescreatedatatime(new Date());
		
		if(chatMessage.getContentType().equals(ContentTypeEnum.File)){
			entity.setResuploadfilepath("uploads/chat/file");
		}
		else if(chatMessage.getContentType().equals(ContentTypeEnum.Image)){
			entity.setResuploadfilepath("uploads/chat/image");
		}
		else if(chatMessage.getContentType().equals(ContentTypeEnum.Video)){
			entity.setResuploadfilepath("uploads/chat/video");
		}
		else if(chatMessage.getContentType().equals(ContentTypeEnum.Voice)){
			entity.setResuploadfilepath("uploads/chat/voice");
		}
		this.save(entity);
		
	}

}
