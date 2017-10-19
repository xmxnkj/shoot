package com.szit.arbitrate.news.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sun.print.resources.serviceui;

import com.hsit.common.dao.Dao;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.mysql.jdbc.log.Log;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.news.dao.NewsClickLikeDao;
import com.szit.arbitrate.news.entity.NewsClickLike;
import com.szit.arbitrate.news.entity.query.NewsClickLikeQuery;
import com.szit.arbitrate.news.service.NewsClickLikeService;
import com.szit.arbitrate.news.service.NewsDetailsService;
import com.szit.arbitrate.news.service.NewsHeadInfoService;

/**
 * 
 * 	@author chenpj
 * 	@date: 	下午4:04:52
 *	@Descript 	新闻点赞业务逻辑接口实现类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */

@Service
@Transactional
public class NewsClickLikeServiceImpl extends BaseServiceImpl<NewsClickLike,NewsClickLikeQuery>
implements NewsClickLikeService
{	
	@Autowired
	private NewsClickLikeDao dao;
	
	public NewsClickLikeDao getDao() {
		return dao;
	}

	public void setDao(NewsClickLikeDao dao) {
		this.dao = dao;
	}


	@Autowired
	private ClientService clientService;
	
	@Autowired
	private NewsHeadInfoService newsHeadInfoService;

	
	//点赞
	/**
	 * newsid 			新闻id
	 * likeclientid		点赞用户id
	 * likeip			点赞用户ip
	 * @see com.szit.arbitrate.news.service.NewsClickLikeService#saveClickLike(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public String saveClickLike(String newsid,String likeclientid,String likeip) throws BizException, ErrorException {
		String result = "点赞成功";

		if(isClickLike(newsid, likeclientid)){
			result = "您点过赞了";
			return result;
		}
		//点赞
		NewsClickLike ncl = new NewsClickLike();
		ncl.setNewsid(newsid);
		ncl.setLikedatetime(new Date());
		ncl.setLikeclientid(likeclientid);
		ncl.setLikeip(likeip);
		this.save(ncl);
		
		//新闻点赞数+1
		newsHeadInfoService.incrOrDecr(newsid, "likenums", true);
		return result;
	}

	@Override
	public String deleteClickLike(String newsid, String likeclientid) throws BizException, ErrorException {
		String result = "";
		NewsClickLikeQuery newsClickLikeQuery = new NewsClickLikeQuery();
		newsClickLikeQuery.setNewsid(newsid);
		newsClickLikeQuery.setLikeclientid(likeclientid);
		NewsClickLike ncl = this.getEntity(newsClickLikeQuery);
		if(ncl!=null){
			//新闻点赞数-1
			result="您已经取消赞了";
			this.deleteById(ncl.getId());
			newsHeadInfoService.incrOrDecr(newsid, "likenums", false);
		}else{
			result="您并没有点赞";
		}
		return result;
	}
	//是否已经点过赞
	@Override
	public boolean isClickLike(String newsid, String likeclientid)
			throws BizException, ErrorException {
		if(likeclientid==null || likeclientid.equals("")){
			return false;
		}
		if(newsid==null || newsid.equals("")){
			return false;
		}
		
		if(clientService.getById(likeclientid)==null){
			return false;
		}
		NewsClickLikeQuery newsClickLikeQuery = new NewsClickLikeQuery();
		newsClickLikeQuery.setNewsid(newsid);
		newsClickLikeQuery.setLikeclientid(likeclientid);
		NewsClickLike ncl = this.getEntity(newsClickLikeQuery);
		return ncl!=null?true:false;
	}
}