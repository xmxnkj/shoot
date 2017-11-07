package com.szit.arbitrate.mediation.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;

/**
 * 
* @ClassName: LegalDocCommentsQuery  
* @Description: 经典案例评论查询类  
* @author   
* @date 2017年7月4日 上午11:04:47  
* @Copyright
* @versions:1.0 
*
 */
public class LegalDocCommentsQuery extends EntityQueryParam{

	private static final long serialVersionUID = 8069920334103969374L;
	
	private String legalDocId;//经典案例id
	private String commentsClientId;//评论用户id
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
	public String getCommentsContent() {
		return commentsContent;
	}
	public void setCommentsContent(String commentsContent) {
		this.commentsContent = commentsContent;
	}
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
