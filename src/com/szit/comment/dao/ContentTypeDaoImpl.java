package com.szit.comment.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.hsit.common.dao.EntityOrder;
import com.hsit.common.dao.hibernate.HibernateDao;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.comment.entity.ContentType;
import com.szit.comment.entity.query.ContentTypeQuery;

/**
 * 内容类型查询类
 * @author linzf
 *
 */
@Repository
public class ContentTypeDaoImpl extends HibernateDao<ContentType, ContentTypeQuery> implements ContentTypeDao{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4962407725365174694L;

	@Override
	public List<EntityOrder> buildEntityOrders(ContentTypeQuery query) {

		return super.buildDisplayOrders();
	}
}
