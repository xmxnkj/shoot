package com.szit.arbitrate.news.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.sun.security.ntlm.Client;

/**
 * 
 * 	@author chenpj
 * 	@date: 	下午3:42:35
 *	@Descript 	点赞查询实体类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
 
public class NewsClickLikeQuery extends EntityQueryParam{
	
	private String newsid;//新闻ID 
	private String likeclientid;//点赞会员ID 
	private String likeip;//点赞IP 
	private Date likedatetime;//点赞时间 
	
	private Client likeclient;//点赞会员对象

	public String getNewsid() {
		return newsid;
	}

	public void setNewsid(String newsid) {
		this.newsid = newsid;
	}

	public String getLikeclientid() {
		return likeclientid;
	}

	public void setLikeclientid(String likeclientid) {
		this.likeclientid = likeclientid;
	}

	public String getLikeip() {
		return likeip;
	}

	public void setLikeip(String likeip) {
		this.likeip = likeip;
	}

	public Date getLikedatetime() {
		return likedatetime;
	}

	public void setLikedatetime(Date likedatetime) {
		this.likedatetime = likedatetime;
	}

	public Client getLikeclient() {
		return likeclient;
	}

	public void setLikeclient(Client likeclient) {
		this.likeclient = likeclient;
	}
}