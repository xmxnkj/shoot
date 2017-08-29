package com.szit.arbitrate.news.entity.query;

import java.math.BigDecimal;
import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.sun.security.ntlm.Client;

/**
 * @ProjectName:xmszit-cowell-module-entity
 * @ClassName: ChoiceCategory
 * @Description: 新闻查询实体类
 * @author XUJC
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
public class NewsDetailsQuery extends EntityQueryParam{
	
	private String newsId;		//新闻头ID 
	private Integer newsSeq;		//序号 
	private String newsContent;	//新闻内容 
	private boolean isText=true;	//是否为文本
	
	
	public boolean isText() {
		return isText;
	}
	public void setText(boolean isText) {
		this.isText = isText;
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