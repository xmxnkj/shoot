package com.szit.comment.entity.query;

import java.util.Date;
import java.util.List;

import com.hsit.common.kfbase.entity.EntityQueryParam;

public class CommentLikeQuery extends EntityQueryParam {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2768980270811911058L;
	private String reportId;
	private String commentId;
	private String userId;
	private List<String> commentIds;
	
	
	
	public List<String> getCommentIds() {
		return commentIds;
	}
	public void setCommentIds(List<String> commentIds) {
		this.commentIds = commentIds;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
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
