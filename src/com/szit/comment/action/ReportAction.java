package com.szit.comment.action;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfig;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;
import org.apache.xmlrpc.client.XmlRpcClientRequestImpl;
import org.apache.xmlrpc.client.XmlRpcCommonsTransportFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.kfbase.entity.FileType;
import com.hsit.common.kfbase.entity.ObjectFile;
import com.hsit.common.kfbase.service.ObjectFileService;
import com.hsit.common.utils.net.HttpUtilityInstance;
import com.opensymphony.xwork2.inject.util.Function;
import com.szit.comment.entity.Area;
import com.szit.comment.entity.Category;
import com.szit.comment.entity.ContentType;
import com.szit.comment.entity.EventType;
import com.szit.comment.entity.Reply;
import com.szit.comment.entity.Report;
import com.szit.comment.entity.ReportStatus;
import com.szit.comment.entity.query.ReportQuery;
import com.szit.comment.service.AreaService;
import com.szit.comment.service.CategoryService;
import com.szit.comment.service.ContentTypeService;
import com.szit.comment.service.EventTypeService;
import com.szit.comment.service.ReplyService;
import com.szit.comment.service.ReportService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller("reportAction")
@Scope("prototype")
public class ReportAction extends BaseAction<Report, ReportQuery>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8624113753155021107L;
	@Autowired
	private ReportService service;
	@Autowired
	private ReplyService replyService;
	
	@Override
	public ReportService getService() {
		return service;
	}
	
	@Override
	protected void beforeQuery() {
		
		if (getLoginUser() == null) {
			if (getEntityQuery()==null) {
				setEntityQuery(new ReportQuery());
			}
			if ((getEntityQuery().getReportStatus()==null 
					|| getEntityQuery().getReportStatus()==ReportStatus.Submit)
					&& StringUtils.isEmpty(getEntityQuery().getReportUserId())
					) {
				getEntityQuery().setReportStatus(ReportStatus.Accept);
			}
			getEntityQuery().setIsPublic(true);
			if (!StringUtils.isEmpty(getEntityQuery().getReportUserId())) {
				if (getEntityQuery().getReportStatus()==null || getEntityQuery().getReportStatus()==ReportStatus.Accept || getEntityQuery().getReportStatus()==ReportStatus.Submit) {
					getEntityQuery().setReportStatus(null);
					getEntityQuery().setNotEqualReportStatus(ReportStatus.Dealed);
				}
				getEntityQuery().setIsPublic(null);
			}
		}
		
		super.beforeQuery();
	}
	
	/**
	 * app端保存报料
	 */
	public void saveReport(){
		if (getEntity() != null) {
			
			getEntity().setReportTime(new Date());
			
			List<File> files = new ArrayList<>();
			List<String> fileNames = new ArrayList<>();
			List<FileType> fileTypes = new ArrayList<>();
			if (imageFiles != null && imageFiles.size()>0) {
				for (File file : imageFiles) {
					files.add(file);
					fileNames.add("");
					fileTypes.add(FileType.Image);
				}
			}
			if (videoFiles != null && videoFiles.size()>0) {
				for (File file : videoFiles) {
					files.add(file);
					fileNames.add("");
					fileTypes.add(FileType.Video);
				}
			}
			
			if (audioFiles != null && audioFiles.size()>0) {
				for (File file : audioFiles) {
					files.add(file);
					fileNames.add("");
					fileTypes.add(FileType.Audio);
				}
			}
			
			try {
				service.save(getEntity(), files, fileNames, fileTypes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			outSuccessJson();
			return;
		}
		outFailJson("error", null);
	}
	
	public void getReportDetail(){
		initFiles();
		
		JSONObject jsonObject = new JSONObject();
		JSONArray imgArray = new JSONArray();
		if (images != null) {
			for (ObjectFile file : images) {
				JSONObject obj = new JSONObject();
				obj.element("id", file.getId());
				imgArray.add(obj);
			}
		}
		jsonObject.element("images", imgArray);
		
		JSONArray audioArray = new JSONArray();
		if (audios != null) {
			for(ObjectFile file : audios){
				JSONObject obj = new JSONObject();
				//objectName为转码后的文件ID
				if(!StringUtils.isEmpty(file.getObjectName())){
					obj.element("id", file.getObjectName());
				}else{
					obj.element("id", file.getId());
				}
				
				audioArray.add(obj);
			}
		}
		jsonObject.element("audios", audioArray);
		
		JSONArray videoArray = new JSONArray();
		if (videos != null) {
			for(ObjectFile file : videos){
				JSONObject obj = new JSONObject();
				obj.element("id", file.getId());
				if (!StringUtils.isEmpty(file.getObjectName())) {
					obj.element("url", file.getObjectName());
				}
				if (!StringUtils.isEmpty(file.getObjectType())) {
					obj.element("imageUrl", file.getObjectType());
				}
				videoArray.add(obj);
			}
		}
		jsonObject.element("videos", videoArray);
		
		
		JSONArray replyArray = new JSONArray();
		List<Reply> replies = replyService.getReplies(getId());
		if (replies != null) {
			for (Reply reply : replies) {
				JSONObject jo = new JSONObject();
				jo.element("replyTime", formatDateTime(reply.getReplyDate()));
				jo.element("content", reply.getContent());
				replyArray.add(jo);
			}
		}
		jsonObject.element("replies", replyArray);
		
		outJson(jsonObject.toString());
	}
	
	
	
	
	/**
	 * 初始化添加表单
	 */
	@Override
	protected void initAddForm() {
		initForm();
		
		super.initAddForm();
	}
	
	/**
	 * 初始化编辑表单
	 */
	@Override
	protected void initEditForm() {
		initForm();
		
		initFiles();
		
		super.initEditForm();
	}
	
	/**
	 * 删除文件
	 */
	public void delFile(){
		if (!StringUtils.isEmpty(fileId) && !StringUtils.isEmpty(getId())) {
			service.delReportFile(getId(), fileId);
			outSuccessJson();
		}
	}
	
	public String showReport(){
		
		initForm();
		
		return SUCCESS;
	}
	
	@Autowired
	private AreaService areaService;
	@Autowired
	private ContentTypeService contentTypeService;
	@Autowired
	private EventTypeService eventTypeService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	/**
	 * 初始化表单
	 */
	private void initForm(){
		areas = areaService.getEntities(null);
		contentTypes = contentTypeService.getEntities(null);
		eventTypes = eventTypeService.getEntities(null);
		categories = categoryService.getEntities(null);
	}
	
	/**
	 * 接收报料
	 */
	public void accept(){
		if (!StringUtils.isEmpty(getId())) {
			service.acceptReport(getId());
			outSuccessJson();
		}
	}
	
	/**
	 * 回复报料
	 */
	public void reply(){
		if (!StringUtils.isEmpty(getId())) {
			service.replyReport(getId(), replyContent, replyId, getLoginUser());
			outSuccessJson();
		}
	}
	
	@Override
	protected JSONObject getEntityJson(Report entity) {
		JSONObject jsonObject = super.getEntityJson(entity);
		
		jsonObject.element("reportUserName", entity.getReportUserName());
		jsonObject.element("reportUserId", entity.getReportUserId());
		jsonObject.element("reportTime", formatDateTime(entity.getReportTime()));
		jsonObject.element("title", entity.getTitle());
		jsonObject.element("content", entity.getContent());
		jsonObject.element("acceptTime", formatDateTime(entity.getAcceptTime()));
		jsonObject.element("replyContent", entity.getReplyContent());
		jsonObject.element("replyTime", formatDateTime(entity.getReplyTime()));
		jsonObject.element("reportStatus", entity.getReportStatus());
		jsonObject.element("reportStatusName", entity.getReportStatus()!=null?entity.getReportStatus().getValue():"未处理");
		jsonObject.element("areaName", entity.getArea()!=null?entity.getArea().getName():"");
		jsonObject.element("contentTypeName", entity.getContentType()!=null?entity.getContentType().getName():"");
		jsonObject.element("categoryName", entity.getCategory()!=null?entity.getCategory().getName():"");
		jsonObject.element("eventTypeName", entity.getEventType()!=null?entity.getEventType().getName():"");
		jsonObject.element("defaultFileId", entity.getDefaultFileId());
		jsonObject.element("userImgUrl", entity.getUserImgUrl());
		jsonObject.element("defaultFileType", entity.getDefaultFileType());
		jsonObject.element("isPublic", entity.getIsPublic());
		jsonObject.element("fileId2", entity.getFileId2());
		jsonObject.element("fileId3", entity.getFileId3());
		jsonObject.element("imageAmount", entity.getImageAmount());
		
		
		return jsonObject;
	}
	
	/**
	 * 查看报料
	 * @return
	 */
	public String view(){
		setEntity(service.getById(getId()));
		
		initFiles();
		
		replies = replyService.getReplies(getId());
		
		initForm();
		
		return SUCCESS;
	}
	
	@Autowired
	private ObjectFileService objectFileService;
	public String video(){
		if (!StringUtils.isEmpty(getId())) {
			ObjectFile objectFile = objectFileService.getById(getId());
			if (objectFile!=null && !StringUtils.isEmpty(objectFile.getObjectName())) {
				videoUrl = objectFile.getObjectName();
			}
		}
		
		return SUCCESS;
	}
	private String videoUrl;
	
	public String getVideoUrl() {
		return videoUrl;
	}

	private void initFiles(){
		List<ObjectFile> files = service.getReportFiles(getId());
		
		images = new ArrayList<>();
		audios = new ArrayList<>();
		videos = new ArrayList<>();
		
		for (ObjectFile objectFile : files) {
			if (objectFile.getFileType()==FileType.Image) {
				images.add(objectFile);
			}else if (objectFile.getFileType()==FileType.Video) {
				videos.add(objectFile);
			}else if (objectFile.getFileType()==FileType.Audio) {
				audios.add(objectFile);
			}
		}
	}
	
	private List<ObjectFile> images;
	private List<ObjectFile> audios;
	private List<ObjectFile> videos;
	private List<Reply> replies;
	private String fileId;
	
	private List<File> imageFiles;
	private List<File> audioFiles;
	private List<File> videoFiles;
	
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public List<ObjectFile> getVideos() {
		return videos;
	}

	public List<ObjectFile> getImages() {
		return images;
	}

	public List<ObjectFile> getAudios() {
		return audios;
	}
	


	public void setImageFiles(List<File> imageFiles) {
		this.imageFiles = imageFiles;
	}

	public void setAudioFiles(List<File> audioFiles) {
		this.audioFiles = audioFiles;
	}

	public void setVideoFiles(List<File> videoFiles) {
		this.videoFiles = videoFiles;
	}

	private String replyContent;
	private String replyId;
	
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}

	private List<Area> areas;
	private List<ContentType> contentTypes;
	private List<EventType> eventTypes;
	private List<Category> categories;

	public List<Area> getAreas() {
		return areas;
	}


	public List<ContentType> getContentTypes() {
		return contentTypes;
	}


	public List<EventType> getEventTypes() {
		return eventTypes;
	}


	public List<Category> getCategories() {
		return categories;
	}
	


	
}
