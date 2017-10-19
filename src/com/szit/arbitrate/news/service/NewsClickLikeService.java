package com.szit.arbitrate.news.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.news.entity.NewsClickLike;
import com.szit.arbitrate.news.entity.query.NewsClickLikeQuery;

/**
 * 
 * 	@author Administrator
 * 	@date: 	下午4:03:17
 *	@Descript 	新闻点赞业务逻辑接口类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
public interface NewsClickLikeService extends BaseService<NewsClickLike, NewsClickLikeQuery>{

	/**
	 * 点赞
	 * @param newsid
	 * @param likeclientid
	 * @param likeip
	 * @throws BizException
	 * @throws ErrorException
	 */
	public String saveClickLike(String newsid,String likeclientid,String likeip) throws BizException,ErrorException;

	/**
	 * 取消赞
	 * @param newsid
	 * @param likeclientid
	 * @throws BizException
	 * @throws ErrorException
	 */
	public String deleteClickLike(String newsid,String likeclientid) throws BizException,ErrorException;
	
	/**
	 * 是否已经点了赞
	 * @param newsid
	 * @param likeclientid
	 * @throws BizException
	 * @throws ErrorException
	 */
	
	public boolean isClickLike(String newsid,String likeclientid) throws BizException,ErrorException;

}
