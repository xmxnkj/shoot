package com.szit.comment.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseAction;
import com.szit.comment.entity.ContentType;
import com.szit.comment.entity.query.ContentTypeQuery;
import com.szit.comment.service.ContentTypeService;

/**
 * 内容类型控制器
 * @author linzf
 *
 */
@Controller("contentTypeAction")
@Scope("prototype")
public class ContentTypeAction extends BaseAction<ContentType, ContentTypeQuery>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2330856708289495829L;
	@Autowired
	private ContentTypeService service;

	@Override
	public ContentTypeService getService() {
		return service;
	}
}
