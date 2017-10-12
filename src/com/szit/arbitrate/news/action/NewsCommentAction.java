package com.szit.arbitrate.news.action;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.utils.PageDataBean;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.news.entity.NewsComment;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.entity.enumvo.NewsCommentIshowEnum;
import com.szit.arbitrate.news.entity.query.NewsCommentQuery;
import com.szit.arbitrate.news.service.NewsCommentService;
import com.szit.arbitrate.news.service.NewsHeadInfoService;

@Namespace("/admin/news/newsComments")
@Controller("newsCommentAction")
public class NewsCommentAction extends BaseJsonAction<NewsComment, NewsCommentQuery>{
	
	@Autowired
	private NewsCommentService service;
	@Autowired
	private NewsHeadInfoService newsHeadInfoService;
	@Autowired
	private ClientService clientService;
	private String sort;
	private String order;
	private String newsId;
	
	public String getNewsId() {
		return newsId;
	}
	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	protected NewsCommentService getService() {
		return service;
	}
	public void setService(NewsCommentService service) {
		this.service = service;
	}
	
	
	@Override
	protected void beforeQuery() {
		String newsId = this.getRequest().getParameter("newsId");
		NewsCommentQuery query = this.getEntityQuery();
		if(StringUtils.isNotEmpty(newsId)){
			query.setNewsId(newsId);
		}
		query.setIshow(null);
		this.setEntityQuery(query);
	}
	
	@Action(value = "getComments")
	public void getComments()throws Exception{
		String newsId = this.getRequest().getParameter("newsId");
		Paging  paging  = this.getEntityQuery().getPaging();
		NewsCommentQuery newsCommentQuery = new NewsCommentQuery();
		newsCommentQuery.setNewsId(newsId);
		if(StringUtils.isNotEmpty(sort)){
			if(order.equals("asc")){
				newsCommentQuery.addOrder(sort,false);
			}else{
				newsCommentQuery.addOrder(sort,true);
			}
		}
		newsCommentQuery.setIshow(null);
		newsCommentQuery.setPaging(paging);
		List<NewsComment> list = service.getEntities(newsCommentQuery);
		for(NewsComment nc:list){
			nc.setClient(clientService.getById(nc.getCommentclientid()));
		}
		PageDataBean<NewsComment> pageList = new PageDataBean<NewsComment>(paging,list);
		outJson(jsonMapper.toJson(pageList));
	}
	
	@Action(value = "qualified")
	public void qualified()throws Exception
	{	
		NewsHeadInfo newsHeadInfo = newsHeadInfoService.getById(newsId);
		NewsComment newsComment = service.getById(getId());
		if(newsComment.getIshow()==NewsCommentIshowEnum.NO){
			newsComment.setIshow(NewsCommentIshowEnum.YES);
			newsHeadInfo.setCommentnums(newsHeadInfo.getCommentnums()+1);
		}else{
			newsComment.setIshow(NewsCommentIshowEnum.NO);
			int commentnum = newsHeadInfo.getCommentnums();
			newsHeadInfo.setCommentnums(commentnum==0?0:commentnum-1);
		}
		newsComment.setIsRead(1);
		newsHeadInfoService.save(newsHeadInfo);
		service.save(newsComment);
	}
	
	@Action(value = "comments")
	public void comments()throws Exception{
		String newsId = this.getRequest().getParameter("newsId");
		Paging  paging  = this.getEntityQuery().getPaging();
		Map<String, Object> map = service.getComment(newsId, paging.getPage());
		outJson(jsonMapper.toJson(map));
	}
	
	@Action(value = "rwmcomment")
	public void rwmcomment()throws Exception{
		NewsComment newsComment = service.getById(getEntity().getId());
		//显示的状态 评论数-1
		if(newsComment.getIshow()==NewsCommentIshowEnum.YES){
			//评论数-1
			NewsHeadInfo newsHeadInfo = newsHeadInfoService.getById(newsComment.getNewsId());
			newsHeadInfo.setCommentnums(newsHeadInfo.getCommentnums()-1);
			newsHeadInfoService.save(newsHeadInfo);
		}
		service.deleteById(newsComment.getId());
	}
}