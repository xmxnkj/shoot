package com.szit.arbitrate.mediation.entity;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;

/**
 * 
* @ClassName: LegalDocComments  
* @Description: 经典案例评论实体类  
* @author   
* @date 2017年7月4日 上午10:49:00  
* @Copyright
* @versions:1.0 
*
 */
public class LegalDocComments extends DomainEntity{
	
	private static final long serialVersionUID = -8586595942182131327L;
	
	private String legalDocId;//经典案例id
	private String commentsClientId;//评论用户id
	private String commentsClientName;//评论用户姓名
	private String commentsClientImage;//评论用户头像
	private String commentsContent;//评论内容
	private Date commentsTime;//评论时间
	private boolean audit;//是否审核
	
	public String getLegalDocId() {
		return legalDocId;
	}
	public void setLegalDocId(String legalDocId) {
		this.legalDocId = legalDocId;
	}
	public String getCommentsClientId() {
		return commentsClientId;
	}
	public void setCommentsClientId(String commentsClientId) {
		this.commentsClientId = commentsClientId;
	}
	public String getCommentsClientName() {
		return commentsClientName;
	}
	public void setCommentsClientName(String commentsClientName) {
		this.commentsClientName = commentsClientName;
	}
	public String getCommentsClientImage() {
		return commentsClientImage;
	}
	public void setCommentsClientImage(String commentsClientImage) {
		this.commentsClientImage = commentsClientImage;
	}
	public String getCommentsContent() {
		return commentsContent;
	}
	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getCommentsTime() {
		return commentsTime;
	}
	public void setCommentsTime(Date commentsTime) {
		this.commentsTime = commentsTime;
	}
	public boolean isAudit() {
		return audit;
	}
	public void setAudit(boolean audit) {
		this.audit = audit;
	}

}
