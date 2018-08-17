package com.szit.arbitrate.chat.action;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.chat.entity.ChatMessage;
import com.szit.arbitrate.chat.entity.query.ChatMessageQuery;
import com.szit.arbitrate.chat.service.ChatMessageService;

@Namespace("/admin/chat/chatmessage")
@Controller("chatMessageAction")
public class ChatMessageAction extends BaseJsonAction<ChatMessage, ChatMessageQuery>{

	@Autowired
	private ChatMessageService service;

	public ChatMessageService getService() {
		return service;
	}

	public void setService(ChatMessageService service) {
		this.service = service;
	}
	
	
	private String recclientid;//接收用户id
	private String content;//消息内容
	
	public String getRecclientid() {
		return recclientid;
	}
	public void setRecclientid(String recclientid) {
		this.recclientid = recclientid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	@Action(value="SendMessage")
	public void SendMessage(){
		JSONObject json = new JSONObject();
		try {
			service.sendMessage("1", recclientid, "Admin", "Text", content, "");
			json.put("success", true);
			json.put("message", "数据获取成功！");
			json.put("data", "");
		} catch (BizException biz) {
			json.put("success", false);
			json.put("message", biz.getMessage());
		} catch (ErrorException e) {
			json.put("success", false);
			json.put("message", "系统异常错误:"+e.getMessage());
		}
		outJson(json.toString());
	}
	
}
