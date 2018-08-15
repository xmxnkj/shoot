package com.szit.arbitrate.chat.junit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.chat.service.ChatRoomService;
import com.szit.arbitrate.client.service.NeteaseClientService;

public class ChatRoomServiceJunitTest extends BaseApiJunitTest{

	@Autowired
	private ChatRoomService service;
	@Autowired
	private NeteaseClientService neteaseClientService;
	
	@Test
	public void openNeteaseCloud(){
		String clientid = "68b1340a-af83-40ce-8961-a30cc3589b6d";
		neteaseClientService.createNeteaseCloudAccount(clientid);
	}
	
}
