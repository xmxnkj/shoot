package com.szit.comment.entity;

import java.util.Date;

import com.hsit.common.kfbase.entity.DomainEntity;

/**
 * 报料评论实体
 * @author linzf
 *
 */
public class Comment extends DomainEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5458266376304599870L;

	private String reportId;
	private Date commentTime;
	private String commentUserName;
	private String commentUserId;
	private String headImgUrl;
	private String content;
	private Integer likeCount;
	private String position;
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	//对指定的用户表明是否已点赞，不做存储
	private Boolean liked;
	
	public Boolean getLiked() {
		return liked!=null?liked:false;
	}

	public void setLiked(Boolean liked) {
		this.liked = liked;
	}

	public Integer getLikeCount() {
		return likeCount!=null?likeCount:0;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	
	
	/**
	 * 回复人头像
	 * @return
	 */
	public String getHeadImgUrl() {
		return headImgUrl;
	}

	/**
	 * 回复人头像
	 * @param headImgUrl
	 */
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}


	/**
	 * 报料ID
	 * @return
	 */
	public String getReportId() {
		return reportId;
	}
	
	/**
	 * 报料ID
	 * @param reportId
	 */
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	
	/**
	 * 评论时间
	 * @return
	 */
	public Date getCommentTime() {
		return commentTime;
	}

	/**
	 * 评论时间
	 * @param commentTime
	 */
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	/**
	 * 评论人姓名
	 * @return
	 */
	public String getCommentUserName() {
		return commentUserName;
	}
	
	/**
	 * 评论人姓名
	 * @param commentUserName
	 */
	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}
	
	/**
	 * 评论人ID
	 * @return
	 */
	public String getCommentUserId() {
		return commentUserId;
	}
	
	/**
	 * 评论人ID
	 * @param commentUserId
	 */
	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}
	
	/**
	 * 评论内容
	 * @return
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 评论内容
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
}
