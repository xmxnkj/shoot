package com.szit.arbitrate.news.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.dao.Dao;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.hsit.common.utils.CompressPicUtil;
import com.hsit.common.utils.PagingBean;
import com.szit.arbitrate.news.dao.MomentsResourcesDao;
import com.szit.arbitrate.news.dao.NewsDetailsDao;
import com.szit.arbitrate.news.entity.MomentsResources;
import com.szit.arbitrate.news.entity.NewsDetails;
import com.szit.arbitrate.news.entity.query.NewsDetailsQuery;
import com.szit.arbitrate.news.service.MomentsResourcesService;
import com.szit.arbitrate.news.service.NewsDetailsService;

/**
 * 
 * 	@author chenpj
 * 	@date: 	下午4:05:19
 *	@Descript 	新闻内容业务逻辑接口实现类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@Service
@Transactional
public class NewsDetailsServiceImpl extends BaseServiceImpl<NewsDetails,NewsDetailsQuery>
implements NewsDetailsService
{	
	@Autowired
	private NewsDetailsDao dao;
	
	@Autowired
	private MomentsResourcesDao momentsResourcesDao;
	
	@Autowired
	private MomentsResourcesService momentsResourcesService;

	@Override
	protected Dao<NewsDetails, NewsDetailsQuery> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}
	
	@Override
	public String saveNewsDetails(String newsId, String contents, int newsSeq, File imageFile)
			throws BizException, ErrorException {
		NewsDetails nd = new NewsDetails();
		nd.setNewsContent(contents);
		nd.setNewsId(newsId);
		nd.setNewsSeq(newsSeq);
		
		MomentsResources mrs = new MomentsResources();
		mrs.setRescreatedatatime(new Date());
		mrs.setUrl("");
		
		//序号
		this.save(nd);
		
		if(imageFile!=null){
			String catalog = "D:\\news\\Image\\";
			//测试用的伪上传
			if(System.getProperty("arbitrateweb.root")==null){
				File newFile = new File(catalog);
				//
				if(!newFile.exists()&& !newFile.isDirectory()){
		        	newFile.mkdir();//创建目录
		        }
				
				//后缀
				String uploadPrefix = imageFile.getName().substring(imageFile.getName().lastIndexOf(".")+1);
				String filename= UUID.randomUUID().toString().replace("-", "")+"."+uploadPrefix;
				//伪上传
				CompressPicUtil.getInstance().compressPic(imageFile , catalog, filename, 200, 200, true);
				//上传路径
				mrs.setUrl(catalog+filename);
				momentsResourcesService.save(mrs);
			}
		}
		return nd.getId();
	}

	//分页
	@Override
	public List<NewsDetails> findNewsDetailsList(String newsId,
			int pageNum,int pageSize) throws BizException, ErrorException {
		
		NewsDetailsQuery newsDetailsQuery = new NewsDetailsQuery();
		newsDetailsQuery.setNewsId(newsId);
		newsDetailsQuery.addOrder("newsSeq", false);
		newsDetailsQuery.setPage(pageNum, pageSize);
		List<NewsDetails> list = this.getEntities(newsDetailsQuery);
		for(NewsDetails ndl:list){
			ndl.setMomentsResources(momentsResourcesService.getMomentsResourcesByNewsDetailsId(ndl.getId()));
		}
		return list;
	}
	
	//无分页
		@Override
		public List<NewsDetails> findNewsDetailsList(String newsId) throws BizException, ErrorException {

			NewsDetailsQuery newsDetailsQuery = new NewsDetailsQuery();
			newsDetailsQuery.setNewsId(newsId);
			newsDetailsQuery.addOrder("newsSeq", false);
			List<NewsDetails> list = this.getEntities(newsDetailsQuery);
			return list;
		}
		
		@Override
		public Map<String,Object> findNewsDetailsList(String newsId,int pageNum) throws BizException, ErrorException {
			Paging paging = new Paging();
			paging.setPage(pageNum);
			paging.setPageSize(PagingBean.DEFAULT_PAGE_SIZE);
			
			NewsDetailsQuery newsDetailsQuery = new NewsDetailsQuery();
			newsDetailsQuery.setNewsId(newsId);
			newsDetailsQuery.setPaging(paging);
			newsDetailsQuery.addOrder("newsSeq", false);
			List<NewsDetails> list = this.getEntities(newsDetailsQuery);
			for(NewsDetails ndl:list){
				ndl.setMomentsResources(momentsResourcesService.getMomentsResourcesByNewsDetailsId(ndl.getId()));
			}
			Map<String,Object> map = new HashMap<>();
			map.put("rows", list);
			map.put("total", paging.getRecordCount());
			map.put("pageNum", paging.getPage());
			return map;
		}
		
	//删除
		@Override
		public void deleteNewsDetails(String newsId, String newsdetailsId)throws BizException, ErrorException {
			
			//删除内容
			NewsDetailsQuery newsDetailsQuery = new NewsDetailsQuery();
			newsDetailsQuery.setNewsId(newsId);
			newsDetailsQuery.setId(newsdetailsId);
			NewsDetails newsDetails = this.getEntity(newsDetailsQuery);
			this.deleteById(newsdetailsId);
		}
}