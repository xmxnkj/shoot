package com.szit.arbitrate.news.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.dao.Dao;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.hsit.common.utils.PagingBean;
import com.szit.arbitrate.client.dao.ClientDao;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.news.dao.NewsCommentDao;
import com.szit.arbitrate.news.entity.NewsComment;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.entity.enumvo.NewsCommentIshowEnum;
import com.szit.arbitrate.news.entity.query.NewsCommentQuery;
import com.szit.arbitrate.news.service.NewsCommentService;
import com.szit.arbitrate.news.service.NewsDetailsService;
import com.szit.arbitrate.news.service.NewsHeadInfoService;

/**
 * 
 * 	@author chenpj
 * 	@date: 	下午4:05:14
 *	@Descript 	新闻评论业务逻辑接口实现类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@Service
@Transactional
public class NewsCommentServiceImpl extends BaseServiceImpl<NewsComment,NewsCommentQuery>
implements NewsCommentService
{	
	@Autowired
	private NewsCommentDao dao;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private NewsDetailsService newsDetailsService;
	
	@Autowired
	private NewsHeadInfoService newsHeadInfoService;
	
	@Override
	protected Dao<NewsComment, NewsCommentQuery> getDao() {
		return dao;
	}

	@Override
	public String saveComment(String newsId,String clientId,String contents,String commentip) throws BizException, ErrorException {
		//判断用户id是否有效
		
		Client c = clientService.getById(clientId);
		
		NewsHeadInfo newsHeadInfo = newsHeadInfoService.getById(newsId);
		if(c!=null && newsHeadInfo!=null){
			NewsComment nc = new NewsComment();
			nc.setCommentclientid(clientId);//评论者id
			nc.setNewsId(newsId);
			nc.setCommentcontent(contents);
			nc.setCommentip(commentip);
			nc.setCommentdatetime(new Date());
			this.save(nc);
			return "OK";
		}
		return "NO";
	}

	//查询评论以及所有回复
	@Override
	public Map<String,Object> getComment(String newsId,int pageNum) throws BizException,ErrorException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		
		Paging paging = new Paging();
		paging.setPage(pageNum);
		paging.setPageSize(PagingBean.DEFAULT_PAGE_SIZE);
		
		NewsCommentQuery commentQuery = new NewsCommentQuery();
		commentQuery.setNewsId(newsId);
		commentQuery.setIshow(NewsCommentIshowEnum.YES);
		commentQuery.setPaging(paging);
		commentQuery.addOrder("commentdatetime", true);
		List<NewsComment> list = this.getEntities(commentQuery);
		for(NewsComment nc:list){
			nc.setClient(clientService.getById(nc.getCommentclientid()));
		}
		
		resultMap.put("rows", list);
		resultMap.put("total", paging.getRecordCount());
		resultMap.put("pageCount", paging.getPageCount());
		resultMap.put("pageNum", paging.getPage());
		resultMap.put("pageSize", paging.getPageSize());
		return resultMap;
	}

	@Override
	public void deleteComment(String newId,String commentid) throws BizException,
			ErrorException {
		//删除评论
		this.deleteById(commentid);
		//删除评论下的回复
		StringBuffer str = new StringBuffer();
		str.append("DELETE FROM NewsCommentReply WHERE commentid=:commentid");
		Map<String,Object> map = new HashMap();
		map.put("commentid", commentid);
		dao.executeUpdateHql(str.toString(), map);
		//评论数-1
		newsHeadInfoService.incrOrDecr(newId, "commentnums", false);
		
	}
	
	
	@Override
	public void toShow(String commentId,boolean ishow) throws BizException, ErrorException {
		
		StringBuffer str = new StringBuffer();
		str.append("UPDATE NewsComment set ishow=:ishow WHERE ID=:ID");
		Map<String,Object> map = new HashMap();
		map.put("ishow", ishow);
		map.put("ID", commentId);
		dao.executeUpdateHql(str.toString(), map);
		
	}

	@Override
	public void toShowAll(String newsId, boolean ishow) throws BizException,
			ErrorException {
		StringBuffer str = new StringBuffer();
		str.append("UPDATE NewsComment set ishow=:ishow WHERE newsId=:newsId");
		Map<String,Object> map = new HashMap();
		map.put("ishow", ishow);
		map.put("newsId", newsId);
		dao.executeUpdateHql(str.toString(), map);
	}

	@Override
	public List<NewsComment> getCommentAll(String newsId)
			throws BizException, ErrorException {
		NewsCommentQuery newsCommentQuery = new NewsCommentQuery();
		newsCommentQuery.setNewsId(newsId);
		newsCommentQuery.setIshow(null);
		newsCommentQuery.addOrder("commentdatetime", true);
		List<NewsComment> list = this.getEntities(newsCommentQuery);
		for(NewsComment nc:list){
			nc.setClient(clientService.getById(nc.getCommentclientid()));
		}
		return list;
	}

	@Override
	public Map<String, Object> getCommentWithPage(String newsId, int pageNum) throws BizException, ErrorException {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Paging paging = new Paging();
		paging.setPage(pageNum);
		paging.setPageSize(PagingBean.DEFAULT_PAGE_SIZE);
		NewsCommentQuery newsCommentQuery = new NewsCommentQuery();
		newsCommentQuery.setNewsId(newsId);
		newsCommentQuery.setPaging(paging);
		newsCommentQuery.addOrder("commentdatetime", true);
		newsCommentQuery.setIshow(NewsCommentIshowEnum.YES);
		List<NewsComment> list = this.getEntities(newsCommentQuery);
		for(NewsComment nc:list){
			//加载用户信息
			nc.setClient(clientService.getById(nc.getCommentclientid()));
		}
		resultMap.put("rows", list);
		resultMap.put("total", paging.getRecordCount());
		resultMap.put("pageCount", paging.getPageCount());
		return resultMap;
	}

}