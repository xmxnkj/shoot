/**
 * 
 */
package com.szit.arbitrate.news.entity.query;

import java.util.Date;

import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.news.entity.NewsHeadInfo;

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
public class MomentsResourcesQuery  extends EntityQueryParam{
	
	private String url;		//路径
	
	private String resuploadfilepath;//资源上传的文件路径 
	
	private Date rescreatedatatime;//创建时间 

	private String link;			//图片链接地址

	private String isLunBo;			//是否是新闻轮播图	0为轮播图   1为新闻内容或新闻主图

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

	public String getResuploadfilepath() {
		return resuploadfilepath;
	}

	public void setResuploadfilepath(String resuploadfilepath) {
		this.resuploadfilepath = resuploadfilepath;
	}

	public Date getRescreatedatatime() {
		return rescreatedatatime;
	}

	public void setRescreatedatatime(Date rescreatedatatime) {
		this.rescreatedatatime = rescreatedatatime;
	}
}