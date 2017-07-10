package com.szit.comment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.comment.dao.CategoryDao;
import com.szit.comment.entity.Category;
import com.szit.comment.entity.query.CategoryQuery;

/**
 * 栏目逻辑类
 * @author linzf
 *
 */
@Service
public class CategoryServiceImpl extends AppBaseServiceImpl<Category, CategoryQuery> implements CategoryService{
	@Autowired
	private CategoryDao dao;

	@Override
	public CategoryDao getDao() {
		return dao;
	}
}
