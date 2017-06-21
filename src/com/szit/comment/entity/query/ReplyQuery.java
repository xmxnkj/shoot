package com.szit.comment.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;

public class ReplyQuery extends EntityQueryParam{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1727837604792603555L;
	private String reportId;
	private String replyUserId;
	private Date replyDateLower;
	private Date replyDateUpper;
	public String getReportId() {
		return reportId;
	}
	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
	public String getReplyUserId() {
		return replyUserId;
	}
	public void setReplyUserId(String replyUserId) {
		this.replyUserId = replyUserId;
	}
	public Date getReplyDateLower() {
		return replyDateLower;
	}
	public void setReplyDateLower(Date replyDateLower) {
		this.replyDateLower = replyDateLower;
	}
	public Date getReplyDateUpper() {
		return replyDateUpper;
	}
	public void setReplyDateUpper(Date replyDateUpper) {
		this.replyDateUpper = replyDateUpper;
	}
	
}
