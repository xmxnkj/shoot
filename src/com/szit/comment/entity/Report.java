package com.szit.comment.entity;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.FileType;

/**
 * 报料实体
 * @author linzf
 *
 */
public class Report extends DomainEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4023869040456945289L;
	
	
	private String reportUserName;
	private String reportUserId;
	private Date reportTime;
	private String title;
	private String content;
	private Date acceptTime;
	private String replyContent;
	private Date replyTime;
	private ReportStatus reportStatus;
	private Area area;
	private ContentType contentType;
	private Category category;
	private EventType eventType;
	private String defaultFileId;
	private Boolean isPublic;
	private String userImgUrl;
	private FileType defaultFileType;
	private AppUser appUser;
	
	
	private String fileId2;
	private String fileId3;
	private Integer imageAmount;
	
	//回复视频的缩略图
	private String replyImageId;
	//回复视频文件ID或地址
	private String replyVideoId;
	
	
	public String getReplyImageId() {
		return replyImageId;
	}

	public void setReplyImageId(String replyImageId) {
		this.replyImageId = replyImageId;
	}

	public String getReplyVideoId() {
		return replyVideoId;
	}

	public void setReplyVideoId(String replyVideoId) {
		this.replyVideoId = replyVideoId;
	}

	public Integer getImageAmount() {
		return imageAmount!=null?imageAmount:0;
	}

	public void setImageAmount(Integer imageAmount) {
		this.imageAmount = imageAmount;
	}

	public String getFileId2() {
		return fileId2;
	}

	public void setFileId2(String fileId2) {
		this.fileId2 = fileId2;
	}

	public String getFileId3() {
		return fileId3;
	}

	public void setFileId3(String fileId3) {
		this.fileId3 = fileId3;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public FileType getDefaultFileType() {
		return defaultFileType;
	}

	public void setDefaultFileType(FileType defaultFileType) {
		this.defaultFileType = defaultFileType;
	}

	/**
	 * 用户头像地址
	 * @return
	 */
	public String getUserImgUrl() {
		return appUser!=null&&!StringUtils.isEmpty(appUser.getHeadImgUrl())? appUser.getHeadImgUrl() : userImgUrl;
	}

	/**
	 * 用户头像地址
	 * @param userImgUrl
	 */
	public void setUserImgUrl(String userImgUrl) {
		this.userImgUrl = userImgUrl;
	}

	/**
	 * 公开还是私密
	 * @return
	 */
	public Boolean getIsPublic() {
		return isPublic;
	}

	/**
	 * 公开还是私密
	 * @param isPublic
	 */
	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	/**
	 * 报料人姓名，因是做接口故只保存姓名、ID，不单独做对象
	 * @return
	 */
	public String getReportUserName() {
		return appUser!=null?appUser.getShowName(): reportUserName;
	}
	
	/**
	 * 报料人姓名，因是做接口故只保存姓名、ID，不单独做对象
	 * @param reportUserName
	 */
	public void setReportUserName(String reportUserName) {
		this.reportUserName = reportUserName;
	}
	
	/**
	 * 报料人id，因是做接口故只保存姓名、ID，不单独做对象
	 * @return
	 */
	public String getReportUserId() {
		return appUser!=null?appUser.getUserId().toString(): reportUserId;
	}
	
	/**
	 * 报料人id，因是做接口故只保存姓名、ID，不单独做对象
	 * @param reportUserId
	 */
	public void setReportUserId(String reportUserId) {
		this.reportUserId = reportUserId;
	}

	/**
	 * 报料时间
	 * @return
	 */
	public Date getReportTime() {
		return reportTime;
	}

	/**
	 * 报料时间
	 * @param reportTime
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	/**
	 * 标题
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 标题
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 内容
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 内容
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 接收时间
	 * @return
	 */
	public Date getAcceptTime() {
		return acceptTime;
	}

	/**
	 * 接收时间
	 * @param acceptDate
	 */
	public void setAcceptTime(Date acceptTime) {
		this.acceptTime = acceptTime;
	}

	/**
	 * 回复内容
	 * @return
	 */
	public String getReplyContent() {
		return replyContent;
	}

	/**
	 * 回复内容
	 * @param replyContent
	 */
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	/**
	 * 回复日期
	 * @return
	 */
	public Date getReplyTime() {
		return replyTime;
	}

	/**
	 * 回复日期
	 * @param replyDate
	 */
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

	/**
	 * 状态
	 * @return
	 */
	public ReportStatus getReportStatus() {
		return reportStatus;
	}

	/**
	 * 状态
	 * @param reportStatus
	 */
	public void setReportStatus(ReportStatus reportStatus) {
		this.reportStatus = reportStatus;
	}

	/**
	 * 地区
	 * @return
	 */
	public Area getArea() {
		return area;
	}

	/**
	 * 地区
	 * @param area
	 */
	public void setArea(Area area) {
		this.area = area;
	}

	/**
	 * 内容类型
	 * @return
	 */
	public ContentType getContentType() {
		return contentType;
	}

	/**
	 * 内容类型
	 * @param contentType
	 */
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}

	/**
	 * 栏目
	 * @return
	 */
	public Category getCategory() {
		return category;
	}

	/**
	 * 栏目
	 * @param category
	 */
	public void setCategory(Category category) {
		this.category = category;
	}

	/**
	 * 事件类型
	 * @return
	 */
	public EventType getEventType() {
		return eventType;
	}

	/**
	 * 事件类型
	 * @param eventType
	 */
	public void setEventType(EventType eventType) {
		this.eventType = eventType;
	}

	/**
	 * 默认文件ID
	 * @return
	 */
	public String getDefaultFileId() {
		return defaultFileId;
	}

	/**
	 * 默认文件ID
	 * @param defaultFileId
	 */
	public void setDefaultFileId(String defaultFileId) {
		this.defaultFileId = defaultFileId;
	}
	
	
}

