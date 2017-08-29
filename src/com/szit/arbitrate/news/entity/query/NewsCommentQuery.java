	package com.szit.arbitrate.news.entity.query;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.sun.security.ntlm.Client;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.entity.enumvo.NewsCommentIshowEnum;

/**
 * @ProjectName:xmszit-cowell-module-entity
 * @ClassName: ChoiceCategory
 * @Description: 评论查询实体类
 * @author chenpj
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
public class NewsCommentQuery extends EntityQueryParam{
	
	private String newsId;//文章ID 
	
	private String commentclientid;//评论者ID 
	
	private String commentcontent;//评论内容
	private String commentip;//评论者IP 
	
	private NewsCommentIshowEnum ishow = NewsCommentIshowEnum.NO; //是否显示

	private Date commentdatetime;//评论时间 

	private Client commentclient;//评论者对象 
	
	@JsonIgnore
	private NewsHeadInfo newsHeadInfo;//新闻对象

	public NewsCommentIshowEnum getIshow() {
		return ishow;
	}

	public void setIshow(NewsCommentIshowEnum ishow) {
		this.ishow = ishow;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getCommentclientid() {
		return commentclientid;
	}

	public void setCommentclientid(String commentclientid) {
		this.commentclientid = commentclientid;
	}

	public String getCommentcontent() {
		return commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}

	public String getCommentip() {
		return commentip;
	}

	public void setCommentip(String commentip) {
		this.commentip = commentip;
	}
	
	public Date getCommentdatetime() {
		return commentdatetime;
	}

	public void setCommentdatetime(Date commentdatetime) {
		this.commentdatetime = commentdatetime;
	}

	public Client getCommentclient() {
		return commentclient;
	}

	public void setCommentclient(Client commentclient) {
		this.commentclient = commentclient;
	}

	public NewsHeadInfo getNewsHeadInfo() {
		return newsHeadInfo;
	}

	public void setNewsHeadInfo(NewsHeadInfo newsHeadInfo) {
		this.newsHeadInfo = newsHeadInfo;
	}
}