package com.szit.arbitrate.mediation.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;

/**
 * 
* @ClassName: LegalDocLikesQuery  
* @Description: 经典案例点赞查询类类   
* @author   
* @date 2017年7月4日 上午10:49:23  
* @Copyright
* @versions:1.0 
*
 */
public class LegalDocLikesQuery extends EntityQueryParam{
	
	private static final long serialVersionUID = 4669125885304799110L;
	
	private String legalDocId;//文档id
	private String likeClientId;//点赞用户id
	private String likeClientName;//点赞用户姓名
	private String likeClientImage;//点赞用户头像
	private Date likeTime;//点赞时间
	
	public String getLegalDocId() {
		return legalDocId;
	}
	public void setLegalDocId(String legalDocId) {
		this.legalDocId = legalDocId;
	}
	public String getLikeClientId() {
		return likeClientId;
	}
	public void setLikeClientId(String likeClientId) {
		this.likeClientId = likeClientId;
	}
	public String getLikeClientName() {
		return likeClientName;
	}
	public void setLikeClientName(String likeClientName) {
		this.likeClientName = likeClientName;
	}
	public String getLikeClientImage() {
		return likeClientImage;
	}
	public void setLikeClientImage(String likeClientImage) {
		this.likeClientImage = likeClientImage;
	}
	public Date getLikeTime() {
		return likeTime;
	}
	public void setLikeTime(Date likeTime) {
		this.likeTime = likeTime;
	}

}
