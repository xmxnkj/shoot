package com.szit.arbitrate.news.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;

/**
 * @ProjectName:xmszit-cowell-module-entity
 * @ClassName: ArticleDetails
 * @Description: 新闻详情实体类
 * @author chenpj
 * @date	
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
@JsonIgnoreProperties({"name","description","displayOrder","passwd"})
public class NewsDetails extends DomainEntity{

	private String newsId;		//新闻头ID 
	private Integer newsSeq=1;		//序号 
	private String newsContent;	//新闻内容 
	
	MomentsResources momentsResources;	//图片对象
	
	public MomentsResources getMomentsResources() {
		return momentsResources;
	}
	public void setMomentsResources(MomentsResources momentsResources) {
		this.momentsResources = momentsResources;
	}
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public Integer getNewsSeq() {
		return newsSeq;
	}
	public void setNewsSeq(Integer newsSeq) {
		this.newsSeq = newsSeq;
	}
	public String getNewsContent() {
		return newsContent;
	}
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
}