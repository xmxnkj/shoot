package com.szit.comment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hsit.common.dao.EntityOrder;
import com.hsit.common.dao.hibernate.HibernateDao;
import com.szit.comment.entity.Category;
import com.szit.comment.entity.query.CategoryQuery;

/**
 * 报料栏目持久类
 * @author linzf
 *
 */
@Repository
public class CategoryDaoImpl extends HibernateDao<Category, CategoryQuery> implements CategoryDao{
	
	@Override
	public List<EntityOrder> buildEntityOrders(CategoryQuery query) {
		return super.buildDisplayOrders();
	}
}
