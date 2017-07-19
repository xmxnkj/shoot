package com.szit.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.comment.dao.ContentTypeDao;
import com.szit.comment.entity.ContentType;
import com.szit.comment.entity.query.ContentTypeQuery;

/**
 * 内容类型逻辑类
 * @author linzf
 *
 */
@Service
public class ContentTypeServiceImpl extends AppBaseServiceImpl<ContentType, ContentTypeQuery> implements ContentTypeService {
	@Autowired
	private ContentTypeDao dao;

	@Override
	public ContentTypeDao getDao() {
		return dao;
	}
}
