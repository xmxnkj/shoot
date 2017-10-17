package com.szit.arbitrate.news.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.service.BaseService;
import com.hsit.common.utils.PagingBean;
import com.szit.arbitrate.news.entity.NewsDetails;
import com.szit.arbitrate.news.entity.query.NewsDetailsQuery;

/**
 * 
 * 	@author Administrator
 * 	@date: 	下午4:03:30
 *	@Descript 	新闻内容业务逻辑接口类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
public interface NewsDetailsService extends BaseService<NewsDetails, NewsDetailsQuery>{
	
	/**
	 * 获取文章的明细内容带分页
	 * @param newsId
	 * @param pageNum
	 * @param pageSize
	 * @return		List<NewsDetails>
	 * @throws BizException
	 * @throws ErrorException
	 */
	public List<NewsDetails> findNewsDetailsList(String newsId,int pageNum,int pageSize)throws BizException,ErrorException;

	/**
	 * 获取文章的所有内容
	 * @param newsId
	 * @return	List<NewsDetails>
	 * @throws BizException
	 * @throws ErrorException
	 */
	public List<NewsDetails> findNewsDetailsList(String newsId)throws BizException,ErrorException;

	/**
	 * 添加文章内容
	 * @param newsId
	 * @param contents
	 * @throws BizException
	 * @throws ErrorException
	 */
	public String saveNewsDetails(String newsId,String contents,  int newsSeq, File imageFile)throws BizException,ErrorException;
	
	
	/**
	 * 删除新闻内容
	 * @param newsId  新闻头部ID
	 * @param detailsId	新闻内容ID
	 * @return
	 * @throws BizException
	 * @throws ErrorException
	 */
	public void deleteNewsDetails(String newsId,String newsdetailsId)throws BizException,ErrorException;
	
	public Map<String,Object> findNewsDetailsList(String newsId,int pageNum)throws BizException,ErrorException;
}