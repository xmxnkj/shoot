package com.szit.comment.entity.query;

import org.apache.commons.lang.StringUtils;

import com.hsit.common.kfbase.entity.EntityQueryParam;

public class AreaQuery extends EntityQueryParam{
	private String parentId;

	private Boolean isRoot;
	
	public String getParentId() {
		return !StringUtils.isEmpty(parentId) ? parentId : null;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public Boolean getIsRoot() {
		return isRoot;
	}

	public void setIsRoot(Boolean isRoot) {
		this.isRoot = isRoot;
	}
}
