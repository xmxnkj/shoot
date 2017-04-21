/**
 * File Name: ConstSetAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.kfbase;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.kfbase.entity.ConstSet;
import com.hsit.common.kfbase.queryparam.ConstSetQuery;
import com.hsit.common.kfbase.service.ConstSetService;

/**
 * @ClassName:ConstSetAction
 * @date 2017-3-5 下午2:42:48
 * 
 */
@Component("constSetAction")
@Scope("prototype")
public class ConstSetAction extends BaseAction<ConstSet, ConstSetQuery> {
	private ConstSetService service;

	@Override
	public ConstSetService getService() {
		return service;
	}

	@Autowired
	public void setService(ConstSetService service) {
		this.service = service;
	}

	private String settingName;
	
	public String getSettingName() {
		return settingName;
	}

	public void setSettingName(String settingName) {
		this.settingName = settingName;
	}

	private String objectId;

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void saveSetting() {
		if(!StringUtils.isEmpty(settingName)){
			if (!StringUtils.isEmpty(objectId)) {
				service.saveSetting(objectId, settingName, value);
			}else {
				service.saveSetting(settingName, value);
			}
			outSuccessJson();
			return;
		}
		
		outFailJson(null);
	}
	
	public void getSetting() {
		try {
			if (getService() == null) {
				outFailJson(null);
				return;
			}
			if (StringUtils.isEmpty(settingName)) {
				outFailJson(null);
				return;
			}

			String value = "";
			if(!StringUtils.isEmpty(objectId))
				value = service.getObjectSetting(objectId, settingName);
			else {
				value = service.getSetting(settingName);
			}
			
			outJson(value);
			
		} catch (Exception e) {
			outFailJson(e);
		}
	}
}
