package com.szit.arbitrate.api.base;

import java.util.List;

import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.utils.JsonMapper;

public class JsonConverter<T extends DomainEntity>{
	
	private static JsonMapper jsonMapper = JsonMapper.getInstance();
	
	public String parseListEntity(T entity){
		return jsonMapper.toJson(entity);
	}
	
	public String parseEntityDetail(T entity){
		return jsonMapper.toJson(entity);
	}
	
	public String praseEntityList(List<T> entities){
		return jsonMapper.toJson(entities);
	}
}
