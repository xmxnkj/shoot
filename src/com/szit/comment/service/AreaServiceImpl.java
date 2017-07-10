package com.szit.comment.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.comment.dao.AreaDao;
import com.szit.comment.entity.Area;
import com.szit.comment.entity.query.AreaQuery;

@Service
public class AreaServiceImpl extends AppBaseServiceImpl<Area, AreaQuery> implements AreaService{
	@Autowired
	private AreaDao dao;
	@Override
	public AreaDao getDao() {
		return dao;
	}
	
	/**
	 * 查询子区域，当parentId为空时，查询根节点
	 * @param parentId
	 * @return
	 */
	public List<Area> getChildAreas(String parentId){
		AreaQuery query = new AreaQuery();
		if (StringUtils.isEmpty(parentId)) {
			query.setIsRoot(true);
		}else{
			query.setParentId(parentId);
		}
		return getEntities(query);
	}
	
}
