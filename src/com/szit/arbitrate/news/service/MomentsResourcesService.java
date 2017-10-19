package com.szit.arbitrate.news.service;

import java.io.File;
import java.util.List;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.hsit.common.utils.PagingBean;
import com.szit.arbitrate.news.entity.MomentsResources;
import com.szit.arbitrate.news.entity.NewsDetails;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.entity.query.MomentsResourcesQuery;

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
public interface MomentsResourcesService extends BaseService<MomentsResources,  MomentsResourcesQuery>{
	
	public MomentsResources saveResources(File imageFile, String fileName, String catalog, String httpHomeUrl,int width, int height, boolean gp);
	
	public MomentsResources getMomentsResourcesByNewsDetailsId(String newsDetailsId);
	
	public MomentsResources getMomentsResourcesByNewsHeadInfoId(String newsHeadInfoId);

}
