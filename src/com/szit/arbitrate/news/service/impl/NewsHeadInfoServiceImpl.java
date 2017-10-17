package com.szit.arbitrate.news.service.impl;

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
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.news.dao.NewsCommentDao;
import com.szit.arbitrate.news.dao.NewsDetailsDao;
import com.szit.arbitrate.news.dao.NewsHeadInfoDao;
import com.szit.arbitrate.news.entity.MomentsResources;
import com.szit.arbitrate.news.entity.NewsDetails;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.entity.enumvo.NewsIshowEnum;
import com.szit.arbitrate.news.entity.query.MomentsResourcesQuery;
import com.szit.arbitrate.news.entity.query.NewsHeadInfoQuery;
import com.szit.arbitrate.news.service.MomentsResourcesService;
import com.szit.arbitrate.news.service.NewsClickLikeService;
import com.szit.arbitrate.news.service.NewsCommentService;
import com.szit.arbitrate.news.service.NewsDetailsService;
import com.szit.arbitrate.news.service.NewsHeadInfoService;

/**
 * 
 * 	@author chenpj
 * 	@date: 	下午4:05:24
 *	@Descript 	新闻头部业务逻辑接口实现类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@Service
@Transactional
public class NewsHeadInfoServiceImpl extends BaseServiceImpl<NewsHeadInfo,NewsHeadInfoQuery>
implements NewsHeadInfoService
{	
	@Autowired
	private NewsHeadInfoDao dao;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private NewsDetailsService newsDetailsService;
	
	@Autowired
	private NewsDetailsDao newsDetailsDao;
	
	@Autowired
	private NewsCommentService newsCommentService;
	
	@Autowired
	private NewsCommentDao newsCommentDao;

	@Autowired
	private MomentsResourcesService momentsResourcesService;
	
	@Autowired
	private NewsClickLikeService newsClickLikeService;
	
	@Override
	protected Dao<NewsHeadInfo, NewsHeadInfoQuery> getDao() {
		return dao;
	}
	
	
	//删除新闻 以及相关内容
	@Override
	public int delete(String ID)throws BizException, ErrorException {
		//删除主图

		NewsHeadInfo nhi = this.getById(ID);
		int result = 1;
		if(nhi==null){
			result = 0;
			return result;
		}

		//删除新闻头部
		this.deleteById(ID);

		//删除内容
		newsDetailsDao.deleteByNewsId(ID);
		
		//删除评论
		newsCommentDao.deleteByNewsId(ID);
		
		return result;
	}
	
	//根据辩题模糊查找新闻
	@Override
	public Map<String,Object> findNewsByTitleList(String title,int pageNum,int pageSize) throws BizException, ErrorException {
		Map<String,Object> resultMap = new HashMap<>();
		//分页内容
		Paging page = new Paging();
		page.setPage(pageNum);
		page.setPageSize(pageSize==0?PagingBean.DEFAULT_PAGE_SIZE:pageSize);
		//
		NewsHeadInfoQuery newsHeadInfoQuery = new NewsHeadInfoQuery();
		newsHeadInfoQuery.setArticletitle(title);
		newsHeadInfoQuery.addOrder("orderdisplay", false);
		newsHeadInfoQuery.setIshow(NewsIshowEnum.YES);
		newsHeadInfoQuery.setPaging(page);
		List<NewsHeadInfo> list = this.getEntities(newsHeadInfoQuery);
		
		resultMap.put("rows", list);
		resultMap.put("total", page.getRecordCount());
		resultMap.put("pageCount", page.getPageCount());
		return resultMap;
	}
	
	//单条新闻所有内容
	@Override
	public Map<String,Object> loadNewsInformation(String newsId,String clientId) throws BizException,
			ErrorException {
		Map<String ,Object> resultMap = new HashMap<>();
		
		NewsHeadInfo nhi = this.getById(newsId);

		//判断是否已经点赞
		resultMap.put("clickLiked", newsClickLikeService.isClickLike(newsId, clientId));

		List<NewsDetails> newsdetailsList = newsDetailsService.findNewsDetailsList(newsId);//加载新闻内容+图片
				
		Map<String,Object> newsCommonsList = newsCommentService.getComment(newsId,1);//加载评论
		
		resultMap.put("newsHeadInfo", nhi);
		resultMap.put("newsDetails", newsdetailsList);
		resultMap.put("newsComments", newsCommonsList);
		
		return resultMap;
	}
	
	//点赞的删除和添加
	/**
	 * newId 	新闻id
	 * type	 	点赞字段
	 * isAdd	点赞 true  取消点赞 false
	 */
	@Override
	public void incrOrDecr(String newId, String type, boolean isAdd)
			throws BizException, ErrorException {
		
		NewsHeadInfo newsHeadInfo = this.getById(newId);
	
		newsHeadInfo.setLikenums(isAdd?newsHeadInfo.getLikenums()+1:newsHeadInfo.getLikenums()-1);
	
		this.save(newsHeadInfo);
	}
	
	//热点
	@Override
	public List<NewsHeadInfo> hotNewsList() throws BizException, ErrorException {
		Paging page = new Paging();
		page.setPage(1);
		page.setPageSize(5);
		NewsHeadInfoQuery newsHeadInfoQuery = new NewsHeadInfoQuery();
		newsHeadInfoQuery.addOrder("orderdisplay", false);
		newsHeadInfoQuery.setIshow(NewsIshowEnum.YES);
		newsHeadInfoQuery.setPaging(page);
		List<NewsHeadInfo> list = this.getEntities(newsHeadInfoQuery);
		return list;
	}

	@Override
	public List<MomentsResources> lunBo() throws BizException, ErrorException {
		MomentsResourcesQuery momentsResourcesQuery = new MomentsResourcesQuery();
		momentsResourcesQuery.setIsLunBo("0");
		List<MomentsResources> list = momentsResourcesService.getEntities(momentsResourcesQuery);
		return list;
	}
}