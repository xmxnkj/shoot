package com.szit.comment.entity;

import java.util.Date;

import com.hsit.common.kfbase.entity.DomainEntity;

public class CommentLike extends DomainEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4862965869916200750L;
	
	private String commentId;
	private String reportId;
	private String userId;
	
	private Date likeDate;
	
	
	public Date getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}
	public String getCommentId() {
		return commentId;
	}
	public void setCommentId(String commentId) {
		this.commentId = commentId;
	}
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
