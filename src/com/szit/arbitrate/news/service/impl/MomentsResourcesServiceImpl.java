package com.szit.arbitrate.news.service.impl;

import java.io.File;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.hsit.common.utils.UploadAccResourceUtil;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.news.dao.MomentsResourcesDao;
import com.szit.arbitrate.news.entity.MomentsResources;
import com.szit.arbitrate.news.entity.query.MomentsResourcesQuery;
import com.szit.arbitrate.news.service.MomentsResourcesService;
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
public class MomentsResourcesServiceImpl extends BaseServiceImpl<MomentsResources,MomentsResourcesQuery>
implements MomentsResourcesService
{	
	@Autowired
	private MomentsResourcesDao dao;

	public MomentsResourcesDao getDao() {
		return dao;
	}

	@Autowired
	private ClientService clientService;
	
	@Autowired
	private NewsHeadInfoService newsHeadInfoService;
	
	
	public MomentsResources saveResources(File imageFile, String fileName, String catalog, String httpHomeUrl,
			int width, int height, boolean gp)
			throws BizException, ErrorException {
		if(imageFile!=null){
			MomentsResources momentsResources = new MomentsResources();
			String fileid =  UploadAccResourceUtil.saveCompressFile(imageFile, fileName, catalog, width, height, gp);
			momentsResources.setUrl(fileid);
			momentsResources.setRescreatedatatime(new Date());
			return momentsResources;
		}else{
			throw new BizException("上传文件对象为NULL");
		}
	}
	
	public MomentsResources saveResources(File file, String fileName,String catalog,String httpHomeUrl) throws BizException, ErrorException {
		if(file!=null){
			MomentsResources momentsResources = new MomentsResources();
			String fileid =  UploadAccResourceUtil.saveFile(file, fileName, catalog, "Image");
			momentsResources.setRescreatedatatime(new Date());
			String keyId = this.save(momentsResources);
			MomentsResources entity = dao.load(keyId);
			return entity;
		}else{
			throw new BizException("上传文件对象为NULL");
		}
	}

	@Override
	public MomentsResources getMomentsResourcesByNewsDetailsId(String newsDetailsId) {
		MomentsResourcesQuery momentsResourcesQuery = new MomentsResourcesQuery();
		return this.getEntity(momentsResourcesQuery);
	}

	@Override
	public MomentsResources getMomentsResourcesByNewsHeadInfoId(String newsHeadInfoId) {
		MomentsResourcesQuery momentsResourcesQuery = new MomentsResourcesQuery();
		return this.getEntity(momentsResourcesQuery);
	}
}