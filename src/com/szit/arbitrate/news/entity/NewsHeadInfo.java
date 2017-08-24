package com.szit.arbitrate.news.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmd;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.news.entity.enumvo.NewsIshowEnum;


/**
 * @ProjectName:xmszit-cowell-module-entity
 * @ClassName: Article
 * @Description: 新闻头头实体类
 * @author chenpj
 * @date
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","passwd","client"})
public class NewsHeadInfo extends DomainEntity{
	
	private String authorName;//新闻作者名字 
	private String articletitle;	//新闻标题

	private Date articledatetime;//新闻创建时间 
	private Integer likenums = 0;	//点赞数 
	private Integer commentnums = 0;//评论数 
	private String newsUnit;	//新闻单位
	private String simpleDesc;		//简单描述
	private String newsContent;	//新闻内容 

	private NewsIshowEnum ishow; //是否显示
	
	private MomentsResources momentsResources;	//主图对象
	
	private Integer newComments;		//新评论数
	
	private Integer orderdisplay;
	
	public Integer getOrderdisplay() {
		return orderdisplay;
	}
	public void setOrderdisplay(Integer orderdisplay) {
		this.orderdisplay = orderdisplay;
	}
	public Integer getNewComments() {
		return newComments;
	}
	public void setNewComments(Integer newComments) {
		this.newComments = newComments;
	}
	public MomentsResources getMomentsResources() {
		return momentsResources;
	}
	public void setMomentsResources(MomentsResources momentsResources) {
		this.momentsResources = momentsResources;
	}
	public String getNewsUnit() {
		return newsUnit;
	}
	public void setNewsUnit(String newsUnit) {
		this.newsUnit = newsUnit;
	}
	public NewsIshowEnum getIshow() {
		return ishow;
	}
	public void setIshow(NewsIshowEnum ishow) {
		this.ishow = ishow;
	}
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getSimpleDesc() {
		return simpleDesc;
	}
	public void setSimpleDesc(String simpleDesc) {
		this.simpleDesc = simpleDesc;
	}
	public String getArticletitle() {
		return articletitle;
	}
	public void setArticletitle(String articletitle) {
		this.articletitle = articletitle;
	}
	
	@JsonSerialize(using = CustomDateSerializerYmd.class)
	public Date getArticledatetime() {
		return articledatetime;
	}
	public void setArticledatetime(Date articledatetime) {
		this.articledatetime = articledatetime;
	}
	
	public Integer getLikenums() {
		return likenums;
	}
	public void setLikenums(Integer likenums) {
		this.likenums = likenums;
	}
	public Integer getCommentnums() {
		return commentnums;
	}
	public void setCommentnums(Integer commentnums) {
		this.commentnums = commentnums;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
}