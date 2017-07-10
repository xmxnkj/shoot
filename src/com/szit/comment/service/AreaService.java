package com.szit.comment.service;

import java.util.List;

import com.hsit.common.service.AppBaseService;
import com.szit.comment.entity.Area;
import com.szit.comment.entity.query.AreaQuery;

public interface AreaService extends AppBaseService<Area, AreaQuery>{
	/**
	 * 查询子区域，当parentId为空时，查询根节点
	 * @param parentId
	 * @return
	 */
	public List<Area> getChildAreas(String parentId);
}
