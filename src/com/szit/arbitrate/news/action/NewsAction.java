package com.szit.arbitrate.news.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.utils.JsonFormatUtil;
import com.hsit.common.utils.PageDataBean;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.entity.enumvo.NewsIshowEnum;
import com.szit.arbitrate.news.entity.query.NewsHeadInfoQuery;
import com.szit.arbitrate.news.service.MomentsResourcesService;
import com.szit.arbitrate.news.service.NewsHeadInfoService;

@Namespace("/admin/news")
@Controller("newsAction")
public class NewsAction extends BaseJsonAction<NewsHeadInfo, NewsHeadInfoQuery>{

	@Autowired
	private NewsHeadInfoService service;
	
	@Autowired
	private MomentsResourcesService momentsResourcesService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public NewsHeadInfoService getService() {
		return service;
	}
	public void setService(NewsHeadInfoService service) {
		this.service = service;
	}
	
	private String id;
	private String sort;
	private String order;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Action(value = "saveNewsHeadInfo")
	public void saveNewsHeadInfo()throws Exception{
		JSONObject result = new JSONObject();
		
		NewsHeadInfo newsHeadInfo = getEntity();		
		NewsHeadInfo nhi = service.getById(newsHeadInfo.getId());
		
		if(nhi==null){
			nhi = new NewsHeadInfo();
			nhi.setLikenums(0);
			nhi.setCommentnums(0);
			nhi.setIshow(NewsIshowEnum.YES);
		}
		nhi.setOrderdisplay(getEntity().getOrderdisplay());
		nhi.setArticledatetime(getEntity().getArticledatetime());
		nhi.setAuthorName(newsHeadInfo.getAuthorName());
		nhi.setArticletitle(newsHeadInfo.getArticletitle());
		nhi.setNewsUnit(newsHeadInfo.getNewsUnit());
		nhi.setSimpleDesc(newsHeadInfo.getSimpleDesc());
		nhi.setNewsContent(newsHeadInfo.getNewsContent());
		service.save(nhi);
		
		result.put("success", true);
		result.put("message", "修改成功！！");
		outJson(result.toString());
	}
	
	@Action(value = "deleteNewsHeadInfo")
	public void deleteNewsHeadInfo()throws Exception{
		service.delete(getId());
	}
	
	@Action(value = "ishow")
	public void ishow()throws Exception{
		NewsHeadInfo nhi = service.getById(id);
		if(nhi.getIshow()==null){
			nhi.setIshow(NewsIshowEnum.NO);
		}
		nhi.setIshow(nhi.getIshow().equals(NewsIshowEnum.YES)?NewsIshowEnum.NO:NewsIshowEnum.YES);
		service.save(nhi);
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("status", "OK");
		this.outJson(jsonMapper.toJson(result));
	}
	
	@Action(value = "getMainImg")
	public void getMainImg()throws Exception{

		JSONObject json = new JSONObject();
		try {
			NewsHeadInfo nhi = service.getById(id);
	
			json.put("success", true);
			json.put("message", nhi.getMomentsResources()!=null?"OK":"NO");
			json.put("data", nhi.getMomentsResources());
		} catch (Exception e) {
			logger.error("执行initAddFormJson异常错误",e);
			json.put("success", false);
			json.put("message", "异常错误,请联系系统管理员!");
			json.put("data", "");
		}
		if(isDebug()){
			JsonFormatUtil.printJson("saveJson结果:",json.toString());
		}
		outJson(json.toString());
	}
	
	public String addPage(){
		 return SUCCESS;
	}
	
	//查询新闻列表
		@Action(value = "getNewsHeadInfoList")
		public void getNewsHeadInfoList()throws Exception{
			Paging  paging  = this.getEntityQuery().getPaging();
			NewsHeadInfoQuery newsHeadInfoQuery = getEntityQuery();
			if(StringUtils.isNotEmpty(sort)){
				if(order.equals("asc")){
					newsHeadInfoQuery.addOrder(sort,false);
				}else{
					newsHeadInfoQuery.addOrder(sort,true);
				}
			}
			newsHeadInfoQuery.setPaging(paging);
			List<NewsHeadInfo> list = service.getEntities(newsHeadInfoQuery);
			for(NewsHeadInfo obj:list){
				int counts = jdbcTemplate.queryForInt("select count(*) from new_comment where isRead<>'1' and NEWS_ID='"+obj.getId()+"'");
				obj.setNewComments(counts);
			}
			PageDataBean<NewsHeadInfo> pageList = new PageDataBean<NewsHeadInfo>(paging,list);
			outJson(jsonMapper.toJson(pageList));
		}
		
		String newsId;
		public String getNewsId() {
			return newsId;
		}
		public void setNewsId(String newsId) {
			this.newsId = newsId;
		}

		@Action(
				value="newsInformation", 
				results={
				   @Result(name="success", location="/WEB-INF/jsp/mobile/news/newsInformation.jsp")}
			   )
		public String newsInformation() {
			
			if(StringUtils.isEmpty(newsId)){
			}else{
				Map<String, Object> newsInformation = service.loadNewsInformation(newsId, "");
				logger.debug("newsId:{}",this.getRequest().getAttribute("newsId").toString());
				JsonFormatUtil.printJson("json", newsInformation);
			}
			return SUCCESS;
		}
		
		@Action(value = "getNewsInformation")
		public void getNewsInformation() {
			String newsId = this.getRequest().getParameter("nid");
			String clientId = this.getRequest().getParameter("clientId");
			Map<String, Object> newsInformation = service.loadNewsInformation(newsId, clientId!=null?clientId:"");
			outJson(jsonMapper.toJson(newsInformation));
		}
		
}