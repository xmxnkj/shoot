package com.szit.comment.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseAction;
import com.szit.comment.entity.Category;
import com.szit.comment.entity.query.CategoryQuery;
import com.szit.comment.service.CategoryService;

/**
 * 栏目控制器
 * @author linzf
 *
 */
@Controller("categoryAction")
@Scope("prototype")
public class CategoryAction extends BaseAction<Category, CategoryQuery>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8486226023610180793L;
	@Autowired
	private CategoryService service;

	@Override
	public CategoryService getService() {
		return service;
	}
}
