package com.szit.arbitrate.news.entity;

import java.util.Date;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.enumvo.ClientTypeEnum;
import com.szit.arbitrate.client.entity.enumvo.ThirdPartyEnum;
import com.szit.arbitrate.news.entity.enumvo.NewsCommentIshowEnum;


/**
 * @ProjectName:xmszit-cowell-module-entity
 * @ClassName: ImagetextArticleCommentReply
 * @Description: 新闻评论回复实体类
 * @author chenpj
 * @date
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","passwd"})
public class NewsComment extends DomainEntity{
	
	private String newsId;//文章ID 
	
	private String commentclientid;//评论者ID 
	
	private String commentcontent;//评论内容
	
	private String commentip;//评论者IP 
	
	private NewsCommentIshowEnum ishow = NewsCommentIshowEnum.NO; //是否显示
	
	private Integer isRead = 0;		//是否已读
	
	private Date commentdatetime;//评论时间
		
	@JsonIgnoreProperties({"account","passwd","identifyName","identify",
							"identifyImgFile1","identifyImgFile2","identifyImgFile3",
							"mediationAgencyId","thirdPartyId","thirdParty","clientType",
							"address","tel","ip","clientState","skill","lat","lon","createTime"})
	private Client client;	//评论者信息
		
	public Integer getIsRead() {
		return isRead;
	}

	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public NewsCommentIshowEnum getIshow() {
		return ishow;
	}

	public void setIshow(NewsCommentIshowEnum ishow) {
		this.ishow = ishow;
	}

	@JsonIgnore
	private NewsHeadInfo newsHeadInfo;//新闻对象

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
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getCommentdatetime() {
		return commentdatetime;
	}

	public void setCommentdatetime(Date commentdatetime) {
		this.commentdatetime = commentdatetime;
	}

	public NewsHeadInfo getNewsHeadInfo() {
		return newsHeadInfo;
	}

	public void setNewsHeadInfo(NewsHeadInfo newsHeadInfo) {
		this.newsHeadInfo = newsHeadInfo;
	}
}