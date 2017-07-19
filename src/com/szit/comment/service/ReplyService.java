package com.szit.comment.service;

import java.util.List;

import com.hsit.common.service.AppBaseService;
import com.szit.comment.entity.Reply;
import com.szit.comment.entity.query.ReplyQuery;

public interface ReplyService extends AppBaseService<Reply, ReplyQuery>{
	/**
	 * 查询某个报料的回复
	 * @param reportId
	 * @return
	 */
	public List<Reply> getReplies(String reportId);
}
