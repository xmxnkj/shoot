package com.szit.comment.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.comment.entity.ReportStatus;
/**
 * 报料实体查询类
 * @author linzf
 *
 */
public class ReportQuery extends EntityQueryParam{
	private String reportUserId;
	private Date reportTimeLower;
	private Date reportTimeUpper;
	private ReportStatus reportStatus;
	private Date replyTimeLower;
	private Date replyTimeUpper;
	private String areaId;
	private String categoryId;
	private String contentTypeId;
	private String eventTypeId;
	private Boolean emptyArea;
	private String contentKey;
	private String reportUserName;
	private ReportStatus notEqualReportStatus;
	private String key;
	private Boolean isPublic;
	
	
	public Boolean getIsPublic() {
		return isPublic;
	}
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.contentKey = key;
		this.key = key;
	}
	public ReportStatus getNotEqualReportStatus() {
		return notEqualReportStatus;
	}
	public void setNotEqualReportStatus(ReportStatus notEqualReportStatus) {
		this.notEqualReportStatus = notEqualReportStatus;
	}
	public String getReportUserName() {
		return reportUserName;
	}
	public void setReportUserName(String reportUserName) {
		this.reportUserName = reportUserName;
	}
	public String getContentKey() {
		return contentKey;
	}
	public void setContentKey(String contentKey) {
		this.contentKey = contentKey;
	}
	/**
	 * 查询未分区报料
	 * @return
	 */
	public Boolean getEmptyArea() {
		return emptyArea;
	}
	public void setEmptyArea(Boolean emptyArea) {
		this.emptyArea = emptyArea;
	}
	public String getReportUserId() {
		return reportUserId;
	}
	public void setReportUserId(String reportUserId) {
		this.reportUserId = reportUserId;
	}
	public Date getReportTimeLower() {
		return reportTimeLower;
	}
	public void setReportTimeLower(Date reportTimeLower) {
		this.reportTimeLower = reportTimeLower;
	}
	public Date getReportTimeUpper() {
		return reportTimeUpper;
	}
	public void setReportTimeUpper(Date reportTimeUpper) {
		this.reportTimeUpper = reportTimeUpper;
	}
	public ReportStatus getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(ReportStatus reportStatus) {
		this.reportStatus = reportStatus;
	}
	public Date getReplyTimeLower() {
		return replyTimeLower;
	}
	public void setReplyTimeLower(Date replyTimeLower) {
		this.replyTimeLower = replyTimeLower;
	}
	public Date getReplyTimeUpper() {
		return replyTimeUpper;
	}
	public void setReplyTimeUpper(Date replyTimeUpper) {
		this.replyTimeUpper = replyTimeUpper;
	}
	public String getAreaId() {
		return areaId;
	}
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getContentTypeId() {
		return contentTypeId;
	}
	public void setContentTypeId(String contentTypeId) {
		this.contentTypeId = contentTypeId;
	}
	public String getEventTypeId() {
		return eventTypeId;
	}
	public void setEventTypeId(String eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
}
