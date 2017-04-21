/**
 * File Name: PropertyAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.kfbase;

import java.net.URLDecoder;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.kfbase.entity.Property;
import com.hsit.common.kfbase.queryparam.PropertyQuery;
import com.hsit.common.kfbase.service.PropertyService;

/**
 * @ClassName:PropertyAction
 * @date 2013-7-19 下午3:01:31
 * 
 */
@Component("propertyAction")
@Scope("prototype")
public class PropertyAction extends BaseAction<Property, PropertyQuery>{
	private PropertyService service;

	@Override
	public PropertyService getService() {
		return service;
	}

	@Autowired
	public void setService(PropertyService service) {
		this.service = service;
	}
	
	@Override
	public void validateNameExist() {
		try {
			String name = URLDecoder.decode(getEntity() != null ? getEntity().getName() : "", "UTF-8");
			String objectId = getEntity() != null ? getEntity().getObjectId():"";
			Property property = service.getProperty(objectId, name);
			if(property != null && !StringUtils.isEmpty(property.getId()) && !property.getId().equals(getId()))
				outJson("false");
			else
				outJson("true");
		} catch (Exception e) {
			try {
				outJson("true");
			} catch (Exception e1) {
			}
		}
	}
}
