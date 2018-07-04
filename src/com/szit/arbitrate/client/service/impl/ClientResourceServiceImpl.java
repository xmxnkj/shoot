package com.szit.arbitrate.client.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.hsit.common.utils.UploadAccResourceUtil;
import com.szit.arbitrate.chat.entity.ChatRoomClient;
import com.szit.arbitrate.chat.entity.query.ChatRoomClientQuery;
import com.szit.arbitrate.chat.service.ChatRoomClientService;
import com.szit.arbitrate.client.dao.ClientResourceDao;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.ClientResource;
import com.szit.arbitrate.client.entity.enumvo.ResTypeEnum;
import com.szit.arbitrate.client.entity.query.ClientResourceQuery;
import com.szit.arbitrate.client.service.ClientResourceService;
import com.szit.arbitrate.client.service.ClientService;

/**
 * 
* @ProjectName:
* @ClassName: ClientResourceServiceImpl
* @Description:用户资源业务实现类
* @author Administrator
* @date 2017年3月21日 下午2:54:15
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class ClientResourceServiceImpl extends BaseServiceImpl<ClientResource, ClientResourceQuery>
	implements ClientResourceService{
	
	@Autowired
	private ClientResourceDao dao;
	@Autowired
	private ClientService clientService;
	@Autowired
	private  ChatRoomClientService chatRoomClientService;

	public ClientResourceDao getDao() {
		return dao;
	}

	public void setDao(ClientResourceDao dao) {
		this.dao = dao;
	}
	
	@Override
	public String uploadClientResources(String clientId,File uploadFile,
			String uploadFileFileName, String resUploadFilePath,ResTypeEnum resType)
			throws BizException, ErrorException {
		Client client = clientService.getById(clientId);
		if(client == null){
			throw new BizException("用户不存在!");
		}
		String fileid = UploadAccResourceUtil.saveFile(uploadFile,uploadFileFileName,resUploadFilePath, "Image");
		ClientResource entity = new ClientResource();
		entity.setClientId(clientId);
		entity.setResuploadfileid(fileid);
		entity.setResuploadfilepath(resUploadFilePath);
		entity.setResType(resType);
		entity.setRescreatedatatime(new Date());
		this.save(entity);
		
		if(resType.equals(ResTypeEnum.HeadImg)){
			client.setHeadImgFile(fileid);
			clientService.save(client);
			
			ChatRoomClientQuery query = new ChatRoomClientQuery();
			query.setClientId(clientId);
			List<ChatRoomClient> list = chatRoomClientService.getEntities(query);
			if(list != null && list.size() > 0){
				for(ChatRoomClient chatRoomClient : list){
					chatRoomClient.setClientImage(fileid);
					chatRoomClientService.save(chatRoomClient);
				}
			}
			
			
		}
		
		return fileid;
	}

}
