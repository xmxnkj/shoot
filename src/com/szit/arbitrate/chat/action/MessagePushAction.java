package com.szit.arbitrate.chat.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.chat.entity.MessagePush;
import com.szit.arbitrate.chat.entity.query.MessagePushQuery;
import com.szit.arbitrate.chat.service.MessagePushService;

@Namespace("/admin/chat")
@Controller("messagePushAction")
public class MessagePushAction extends BaseJsonAction<MessagePush, MessagePushQuery>{

	@Autowired
	private MessagePushService service;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public MessagePushService getService() {
		return service;
	}

	public void setService(MessagePushService service) {
		this.service = service;
	}
	
	private String sendclientid;
	private String recclientid;
	
	public String getSendclientid() {
		return sendclientid;
	}

	public void setSendclientid(String sendclientid) {
		this.sendclientid = sendclientid;
	}

	public String getRecclientid() {
		return recclientid;
	}

	public void setRecclientid(String recclientid) {
		this.recclientid = recclientid;
	}

	@Action(value="getAdminChatMessageList")
	public void getAdminChatMessageList(){
		JSONObject json = new JSONObject();
		List<MessagePush> list = new ArrayList<MessagePush>();
		try {
			list = service.getAdminChatMessageList(sendclientid, recclientid);
			
			//改变状态
			jdbcTemplate.update("update ch_message_push set READ_FLAG=1 where RECEIVE_CLIENT_ID='"+recclientid+"' and SEND_CLIENT_ID='"+sendclientid+"'");
			
			json.put("success", true);
			json.put("message", "数据获取成功！");
			json.put("data", jsonMapper.toJson(list));
		} catch (BizException biz) {
			json.put("success", false);
			json.put("message", biz.getMessage());
		} catch (ErrorException e) {
			json.put("success", false);
			json.put("message", "系统异常错误:"+e.getMessage());
		}
		outJson(json.toString());
	}
	
	//未读取数量
	@Action(value="getUnReadMsgCounts")
	public void getUnReadMsgCounts(){
		JSONObject json = new JSONObject();
		try {
			String sql = "select count(*) as total,SEND_CLIENT_ID from ch_message_push where RECEIVE_CLIENT_ID='1' and READ_FLAG='0' GROUP BY SEND_CLIENT_ID";
			List<Map<String, Object>>  list = jdbcTemplate.queryForList(sql);
			json.put("success", true);
			json.put("message", "数据获取成功！");
			json.put("data", jsonMapper.toJson(list));
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
