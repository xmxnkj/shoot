package com.szit.arbitrate.news.service;

import java.util.List;
import java.util.Map;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.news.entity.NewsComment;
import com.szit.arbitrate.news.entity.query.NewsCommentQuery;

/**
 * 
 * 	@author Administrator
 * 	@date: 	下午4:03:24
 *	@Descript 	新闻评论业务逻辑接口类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
public interface NewsCommentService extends BaseService<NewsComment, NewsCommentQuery>{

	/**
	 * 添加评论
	 * @param newId
	 * @param clientId
	 * @param contents
	 * @param commentip
	 * @throws BizException
	 * @throws ErrorException
	 */
	public String saveComment(String newId,String clientId,String contents,String commentip) throws BizException,ErrorException;

	/**
	 * 删除评论
	 * @param newId
	 * @param commentid
	 * @throws BizException
	 * @throws ErrorException
	 */
	public void deleteComment(String newId,String commentid) throws BizException,ErrorException;

	/**
	 * 查评论(分页)
	 * @param newsId
	 * @return
	 * @throws BizException
	 * @throws ErrorException
	 */
	public Map<String,Object> getComment(String newsId,int pageNum) throws BizException,ErrorException;
	
	/**
	 * 所有评论
	 * @param newsId
	 * @return
	 * @throws BizException
	 * @throws ErrorException
	 */
	public List<NewsComment> getCommentAll(String newsId) throws BizException,ErrorException;
	
	
	
	public Map<String,Object> getCommentWithPage(String newsId,int pageNum) throws BizException,ErrorException;
	
	/**
	 * 单条评论可见化
	 * @param commentId
	 * @param	ishow	true  可见           false 不可见 
	 * @throws BizException
	 * @throws ErrorException
	 */
	public void toShow(String commentId,boolean ishow)throws BizException,ErrorException;
	
	/**
	 * 新闻的评论可见/隐藏
	 * @param commentId
	 * @param ishow
	 * @throws BizException
	 * @throws ErrorException
	 */
	public void toShowAll(String newsId,boolean ishow)throws BizException,ErrorException;
}