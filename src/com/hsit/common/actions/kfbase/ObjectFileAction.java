/**
 * File Name: ObjectFileAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.kfbase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.kfbase.entity.ObjectFile;
import com.hsit.common.kfbase.queryparam.ObjectFileQuery;
import com.hsit.common.kfbase.service.BaseSetting;
import com.hsit.common.kfbase.service.ObjectFileService;
import com.szit.comment.service.ReportService;

/**
 * @ClassName:ObjectFileAction
 * @date 2017-3-9 上午10:33:41
 * 
 */
@Component("objectFileAction")
@Scope("prototype")
public class ObjectFileAction extends BaseAction<ObjectFile, ObjectFileQuery> {
	private ObjectFileService service;

	@Override
	public ObjectFileService getService() {
		return service;
	}

	@Autowired
	public void setService(ObjectFileService service) {
		this.service = service;
	}
	
	private String fileName;
	
	public String getFileName() {
		if (getEntity() != null && !StringUtils.isEmpty(getEntity().getOriginalName())) {
			try {
				
				fileName = new String(getEntity().getOriginalName().getBytes("GB2312"), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fileName;
	}
	
	
	public static String getFileNameNoEx(String filename) {   
        if ((filename != null) && (filename.length() > 0)) {   
            int dot = filename.lastIndexOf('.');   
            if ((dot >-1) && (dot < (filename.length()))) {   
                return filename.substring(0, dot);   
            }   
        }   
        return filename;   
    }   
	
	public String getSwfFileName() {
		if (getEntity() != null && !StringUtils.isEmpty(getEntity().getOriginalName())) {
			try {
				fileName = getFileNameNoEx(getEntity().getOriginalName()) + ".swf";
				fileName = new String(fileName.getBytes("GB2312"), "ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return fileName;
	}
	
	public InputStream getInputStream() throws Exception {
		try {
			//return new FileInputStream("/ym/apps/labour/uploads/objectfile/"+getId());
			return getServletContext().getResourceAsStream("/uploads/objectfile/" + getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public InputStream getSwfInputStream() throws Exception{
		try {
			return new FileInputStream(BaseSetting.getSwfFilePath(getId()));
			//return getServletContext().getResourceAsStream(BaseSetting.getFilePath(getId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String download() {
		if(!StringUtils.isEmpty(getId())){
			setEntity(getService().getById(getId()));
			return SUCCESS;
		}
		return ERROR;
	}

	private String uploadedFileFileName;
	
	public String getUploadedFileFileName() {
		return uploadedFileFileName;
	}

	public void setUploadedFileFileName(String uploadedFileFileName) {
		this.uploadedFileFileName = uploadedFileFileName;
	}

	private File uploadedFile;
	
	public File getUploadedFile() {
		return uploadedFile;
	}

	public void setUploadedFile(File uploadedFile) {
		this.uploadedFile = uploadedFile;
	}
	
	private Boolean isSuccess;

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	@Autowired
	private ReportService reportService;
	public String upload() throws IOException {
		try{
			if (uploadedFile != null && getEntity() != null) {
				
				reportService.saveReportFile(getEntity().getObjectId(), uploadedFile, uploadedFileFileName, service.parseFileType(uploadedFileFileName));
				
//				if (unique!=null && unique) {
//					getService().deleteObjectFile(getEntity().getObjectId(), getEntity().getObjectName());
//				}
//				
//				String id = getService().saveFile(getEntity(), uploadedFile, uploadedFileFileName);
//				getEntity().setId(id);
			}
			
			isSuccess = true;
		}catch(Exception exception){
			isSuccess = false;
		}
		
		return SUCCESS;
	}
	
	private Boolean unique;
	
	public Boolean getUnique() {
		return unique;
	}

	public void setUnique(Boolean unique) {
		this.unique = unique;
	}

	private File upload;
	
	public File getUpload() {
		return upload;
	}
	
	private String uploadFileName;

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}
	
	private String newsScript;
	
	
	public String getNewsScript() {
		return newsScript;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hsit.common.actions.BaseAction#getEntityJson(com.hsit.common.kfbase.entity.DomainEntity)
	 */
	@Override
	protected JSONObject getEntityJson(ObjectFile entity) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = super.getEntityJson(entity);
		
		jsonObject.element("originalName", entity.getOriginalName());
		jsonObject.element("objectName", entity.getObjectName());
		
		return jsonObject;
	}
	
	@Override
	public void deleteJson() throws Exception{
		JSONObject js = new JSONObject();
		js.element("isSuccess", true);
		js.element("resultDescription", "");
		setEntity(getService().getEntityById(getId()));
		if (getEntity() != null) {
			js.element("id", getEntity().getId());
			js.element("objectId", getEntity().getObjectId());
			js.element("objectName", getEntity().getObjectName());
		}
		getService().deleteById(getId());
		outJson(js.toString());
	}
}
