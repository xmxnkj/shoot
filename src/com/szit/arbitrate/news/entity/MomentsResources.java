/**
 * 
 */
package com.szit.arbitrate.news.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.CustomDateSerializerYmdhms;
import com.szit.arbitrate.news.entity.enumvo.MomentsResourceTypeEnum;

/**
 * 	@author Administrator
 * 	@date: 	下午4:59:41
 *	@Descript 
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@JsonIgnoreProperties({"name","description","displayOrder","passwd"})
public class MomentsResources  extends DomainEntity{

	public final static String newsDir = "uploads/news";
	
	private String url;				//路径
	
	private Date rescreatedatatime;	//创建时间 
	
	private String link;				//图片链接地址
	
	private String isLunBo;
	
	private String title;			//轮播标题

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsLunBo() {
		return isLunBo;
	}

	public void setIsLunBo(String isLunBo) {
		this.isLunBo = isLunBo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@JsonSerialize(using = CustomDateSerializerYmdhms.class)
	public Date getRescreatedatatime() {
		return rescreatedatatime;
	}

	public void setRescreatedatatime(Date rescreatedatatime) {
		this.rescreatedatatime = rescreatedatatime;
	}
}