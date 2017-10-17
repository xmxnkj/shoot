package com.szit.arbitrate.news.service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.service.BaseService;
import com.hsit.common.utils.PagingBean;
import com.szit.arbitrate.news.entity.MomentsResources;
import com.szit.arbitrate.news.entity.NewsDetails;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.entity.query.NewsHeadInfoQuery;

/**
 * 
 * 	@author Administrator
 * 	@date: 	下午4:03:35
 *	@Descript 	新闻头部业务逻辑接口类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
public interface NewsHeadInfoService extends BaseService<NewsHeadInfo, NewsHeadInfoQuery>{
	/**
	 * 新闻热点
	 * @return
	 * @throws BizException
	 * @throws ErrorException
	 */
	public List<NewsHeadInfo> hotNewsList()throws BizException,ErrorException;
	
	/**
	 * 新闻列表
	 * @param clientId
	 * @param pageNum
	 * @param pageSize
	 * @return	List<NewsHeadInfo> 
	 * @throws BizException
	 * @throws ErrorException
	 */
	public Map<String,Object> findNewsByTitleList(String title,int pageNum,int pageSize)throws BizException,ErrorException;
		
	/**
	 * 加载新闻的所有内容和评论
	 * @param newsId
	 * @return	NewsHeadInfo
	 * @throws BizException
	 * @throws ErrorException
	 */
	public Map<String,Object> loadNewsInformation(String newsId,String clientId)throws BizException,ErrorException;

	/**
	 * 新闻评论/点赞
	 * @param newId
	 * @param type
	 * @param isAdd
	 * @throws BizException
	 * @throws ErrorException
	 */
	public void incrOrDecr(String newId,String type,boolean isAdd)throws BizException,ErrorException;
	
	/**
	 *  新闻删除
	 * @param newId
	 * @param type
	 * @param isAdd
	 * @throws BizException
	 * @throws ErrorException
	 */
	public int delete(String ID)throws BizException,ErrorException;
	
	/**
	 * 轮播图
	 * @return
	 */
	public List<MomentsResources> lunBo()throws BizException,ErrorException;
}
