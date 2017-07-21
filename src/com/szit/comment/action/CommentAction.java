package com.szit.comment.action;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hsit.Setting;
import com.hsit.common.actions.BaseAction;
import com.hsit.common.exceptions.ApplicationException;
import com.hsit.common.kfbase.entity.Paging;
import com.szit.comment.entity.Comment;
import com.szit.comment.entity.query.CommentQuery;
import com.szit.comment.service.CommentLikeService;
import com.szit.comment.service.CommentService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller("commentAction")
@Scope("prototype")
public class CommentAction extends BaseAction<Comment, CommentQuery>{
	@Autowired
	private CommentService service;

	@Override
	public CommentService getService() {
		return service;
	}
	
	@Override
	protected void initQueryResult() {
		if (!StringUtils.isEmpty(userId)) {
			service.dealLikeState(getEntities(), userId);
		}
	}
	
	@Override
	protected void beforeQuery() {
		if (getEntityQuery()==null) {
			setEntityQuery(new CommentQuery());
		}
		
		if (getEntityQuery().getPaging()==null) {
			getEntityQuery().setPaging(new Paging());
			getEntityQuery().getPaging().setPage(1);
		}
		if (getEntityQuery().getPaging().getPage()==0) {
			getEntityQuery().getPaging().setPageSize(20);
		}
		
	}
	
	public void hotCommentsJson() throws Exception{
		if (getEntityQuery()==null || StringUtils.isEmpty(getEntityQuery().getReportId())) {
			throw new ApplicationException("非法使用！");
		}
		getEntityQuery().setPaging(new Paging());
		getEntityQuery().getPaging().setPageSize(3);
		getEntityQuery().addOrder("likeCount", true);
		List<Comment> comments = service.getEntities(getEntityQuery());
		service.dealLikeState(comments, userId);
		JSONArray jsonArray = convertEntityListToJson(comments);
		outJson(jsonArray);
	}
	
	
	@Override
	protected JSONObject getEntityJson(Comment entity) {
		JSONObject jsonObject = super.getEntityJson(entity);
		
		jsonObject.element("time", formatDateTime(entity.getCommentTime()));
		jsonObject.element("userName", entity.getCommentUserName());
		jsonObject.element("headImgUrl", entity.getHeadImgUrl());
		jsonObject.element("content", entity.getContent());
		jsonObject.element("likeCount", entity.getLikeCount());
		jsonObject.element("position", entity.getPosition());
		jsonObject.element("liked", entity.getLiked());
		
		return jsonObject;
	}
	
	@Autowired
	private CommentLikeService commentLikeService;
	public void likeCommentJson(){
		if(!StringUtils.isEmpty(commentId) && !StringUtils.isEmpty(userId)){
			commentLikeService.addLike(commentId, userId);
			outSuccessJson();
			return;
		}
		outFailJson("非法使用！", null);
	}
	
	private String userId;
	private String commentId;

	public String getCommentId() {
		return commentId;
	}

	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
