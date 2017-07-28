package com.szit.comment.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.hsit.common.actions.BaseAction;
import com.szit.comment.entity.Reply;
import com.szit.comment.entity.query.ReplyQuery;
import com.szit.comment.service.ReplyService;

@Controller("replyAction")
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply, ReplyQuery>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3954743316793907784L;
	@Autowired
	private ReplyService service;

	@Override
	public ReplyService getService() {
		return service;
	}
}
