package com.szit.arbitrate.news.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.MD5Util;
import com.hsit.common.actions.BaseJsonAction;
import com.szit.arbitrate.news.entity.NewsDetails;
import com.szit.arbitrate.news.entity.query.NewsDetailsQuery;
import com.szit.arbitrate.news.service.MomentsResourcesService;
import com.szit.arbitrate.news.service.NewsDetailsService;

@Namespace("/admin/news/newsDetails")
@Controller("newsDetailsAction")
public class NewsDetailsAction extends BaseJsonAction<NewsDetails, NewsDetailsQuery>{

	@Autowired
	private NewsDetailsService service;
	
	@Autowired
	private MomentsResourcesService momentsResourcesService;

	public NewsDetailsService getService() {
		return service;
	}
	public void setService(NewsDetailsService service) {
		this.service = service;
	}
	private String newsHeadInfoId;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNewsHeadInfoId() {
		return newsHeadInfoId;
	}
	public void setNewsHeadInfoId(String newsHeadInfoId) {
		this.newsHeadInfoId = newsHeadInfoId;
	}
	
	//查询前
	@Override
	protected void beforeQuery() {
		String newsId = this.getRequest().getParameter("newsId");
		NewsDetailsQuery query = this.getEntityQuery();
		if(StringUtils.isNotEmpty(newsId)){
			query.setNewsId(newsId);
		}
		this.setEntityQuery(query);
	}
	
	//获取内容文本
	@Action(value = "getDetailsContent")
	public void getDetailsContent(){
		JSONObject result = new JSONObject();
		String newsId = this.getRequest().getParameter("newsId");
		NewsDetailsQuery ndq = new NewsDetailsQuery();
		ndq.setNewsId(newsId);
		List<NewsDetails> list = service.getEntities(ndq);
		result.put("success", true);
		result.put("message", "修改成功！！");
		result.put("data", list);
		outJson(result.toString());
	}
	
	//获取内容图片
	@Action(value = "getDetailsImg")
	public void getDetailsImg()throws Exception{
		
		NewsDetails newsDetails = service.getById(id);
		
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("success", true);
		result.put("message", newsDetails.getMomentsResources()!=null?"OK":"NO");
		result.put("data", newsDetails.getMomentsResources());
		this.outJson(jsonMapper.toJson(result));
	}
	
	//获取内容图片
	@Action(value = "saveNewsDetails")
	public void saveNewsDetails(){
		JSONObject result = new JSONObject();
		String id = getRequest().getParameter("id");
		String newsId = getRequest().getParameter("newsId");
		String newsContent = getRequest().getParameter("newsContent");

		NewsDetails nds = service.getById(id);
		if(nds==null){
			//新增
			nds = new NewsDetails();
			nds.setNewsId(newsId);
			result.put("message", nds!=null?"OK":"NO");
			nds.setNewsSeq(1);
		}
		nds.setNewsContent(newsContent);
		service.save(nds);
		result.put("success", true);
		result.put("message", "修改成功！！");
		result.put("data", nds);
		outJson(result.toString());
	}
	
	@Action(value = "deleteNewsDetails")
	public void deleteNewsDetails(){
		JSONObject result = new JSONObject();
		try{
			String id = getId();
			NewsDetails nds = service.getById(id);
			if(nds!=null){
				service.deleteById(nds.getId());
			}
		}catch(Exception e){
			result.put("success", false);
			result.put("message", "删除失败！！");
		}
		result.put("success", true);
		result.put("message", "删除成功！！");
		outJson(result.toString());
	}
	
	//获取内容
		@Action(value = "getNewsDetails")
		public void getNewsDetails(){
			JSONObject result = new JSONObject();
			String newsId = getRequest().getParameter("newsId");
			NewsDetailsQuery newsDetailsQuery = new NewsDetailsQuery();
			newsDetailsQuery.setNewsId(newsId);
			result.put("success", true);
			result.put("message", "修改成功！！");
			result.put("data", service.getEntities(newsDetailsQuery));
			outJson(result.toString());
		}
		
		@Action(
				value="getNewsById", 
				results={
				   @Result(name="success", location="/WEB-INF/jsp/mobile/news/new.jsp")}
			   )
		public String getNews() {
			String newsId = getRequest().getParameter("newsId");
			String clientId = getRequest().getParameter("clientId");
			String type = getRequest().getParameter("type");
			getRequest().setAttribute("newsId", newsId);
			getRequest().setAttribute("clientId", newsId);
			getRequest().setAttribute("type", type);
			return SUCCESS;
		}
		
		public static void main(String[] args) {
			System.out.println(MD5Util.MD5("123456"));
		}
}