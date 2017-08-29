package com.szit.arbitrate.news.entity.query;

import java.math.BigDecimal;
import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.sun.security.ntlm.Client;
import com.szit.arbitrate.news.entity.enumvo.NewsIshowEnum;

/**
 * @ProjectName:xmszit-cowell-module-entity
 * @ClassName: ChoiceCategory
 * @Description: 新闻查询实体类
 * @author chenpj
 * @date 
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
public class NewsHeadInfoQuery extends EntityQueryParam{
	
	private String authorName;//新闻作者名字 
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	private String articletitle;//文章标题 
	private Date articledatetime;//文章创建时间 
	private Integer likenums;//点赞数 
	private Integer commentnums;//评论数 
	private Integer favoritenums;//收藏数 
	private NewsIshowEnum ishow; //是否显示
	private String simpleDesc;		//简单描述
	private Integer orderdisplay;
	
	public Integer getOrderdisplay() {
		return orderdisplay;
	}
	public void setOrderdisplay(Integer orderdisplay) {
		this.orderdisplay = orderdisplay;
	}
	public String getSimpleDesc() {
		return simpleDesc;
	}
	public void setSimpleDesc(String simpleDesc) {
		this.simpleDesc = simpleDesc;
	}
	
	public NewsIshowEnum getIshow() {
		return ishow;
	}
	public void setIshow(NewsIshowEnum ishow) {
		this.ishow = ishow;
	}
	
	public String getArticletitle() {
		return articletitle;
	}
	public void setArticletitle(String articletitle) {
		this.articletitle = articletitle;
	}
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
	public Integer getFavoritenums() {
		return favoritenums;
	}
	public void setFavoritenums(Integer favoritenums) {
		this.favoritenums = favoritenums;
	}
}