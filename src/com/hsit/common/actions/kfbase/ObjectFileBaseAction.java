/**
 * File Name: ObejctFileBaseAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.kfbase;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.kfbase.entity.ObjectFile;
import com.hsit.common.kfbase.service.ObjectFileService;
import com.hsit.common.workflow.actions.runtime.WorkFlowBaseAction;

/**
 * @ClassName:ObejctFileBaseAction
 * @date 2017-3-7 上午9:32:41
 * 
 */
public abstract class ObjectFileBaseAction<T extends DomainEntity, Q extends EntityQueryParam> extends WorkFlowBaseAction<T, Q>{
	
	private List<ObjectFile> objectFiles;
	
	public List<ObjectFile> getObjectFiles() {
		return objectFiles;
	}

	public void setObjectFiles(List<ObjectFile> objectFiles) {
		this.objectFiles = objectFiles;
	}
	
	private ObjectFileService objectFileService;

	public ObjectFileService getObjectFileService() {
		return objectFileService;
	}

	@Autowired
	public void setObjectFileService(ObjectFileService objectFileService) {
		this.objectFileService = objectFileService;
	}
	
	private String objectId;

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	
	private String objectName;

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	protected void initObjectFiles(){
		if (!StringUtils.isEmpty(objectId)) {
			objectFiles = objectFileService.getObjectFiles(objectId, objectName);
		}
	}
}
