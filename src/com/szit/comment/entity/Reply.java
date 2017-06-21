package com.szit.comment.entity;

import java.util.Date;

import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.uac.entity.User;

public class Reply extends DomainEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5931425475117024109L;
	
	private User replyUser;
	private Date replyDate;
	private String content;
	private String url;
	private String reportId;
	
	
	/**
	 * 关联的报料ID
	 * @return
	 */
	public String getReportId() {
		return reportId;
	}

	/**
	 * 关联的报料ID
	 * @param reportId
	 */
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	/**
	 * 回复人
	 * @return
	 */
	public User getReplyUser() {
		return replyUser;
	}
	
	/**
	 * 回复人
	 * @param replyUser
	 */
	public void setReplyUser(User replyUser) {
		this.replyUser = replyUser;
	}
	
	/**
	 * 回复日期
	 * @return
	 */
	public Date getReplyDate() {
		return replyDate;
	}
	
	/**
	 * 回复日期
	 * @param replyDate
	 */
	public void setReplyDate(Date replyDate) {
		this.replyDate = replyDate;
	}
	
	/**
	 * 回复内容
	 * @return
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * 回复内容
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
	
	/**
	 * url
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * url
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}
