package com.szit.comment.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsit.common.kfbase.entity.FileType;
import com.hsit.common.kfbase.entity.ObjectFile;
import com.hsit.common.kfbase.service.BaseSetting;
import com.hsit.common.kfbase.service.ObjectFileService;
import com.hsit.common.service.AppBaseServiceImpl;
import com.hsit.common.uac.entity.User;
import com.szit.comment.dao.ReportDao;
import com.szit.comment.entity.Reply;
import com.szit.comment.entity.Report;
import com.szit.comment.entity.ReportStatus;
import com.szit.comment.entity.query.ReportQuery;
import com.szit.comment.utils.ImageUtil;

@Service
public class ReportServiceImpl extends AppBaseServiceImpl<Report, ReportQuery> implements ReportService{
	@Autowired
	private ReportDao dao;

	@Override
	public ReportDao getDao() {
		return dao;
	}
	
	@Autowired
	private ObjectFileService objectFileService;
	
	
	@Autowired
	private AppUserService appUserService;
	@Override
	public String save(Report report) {
		if (report!=null && report.getReportStatus()==null) {
			report.setReportStatus(ReportStatus.Submit);
		}
		if (report.getArea()!=null && StringUtils.isEmpty(report.getArea().getId())) {
			report.setArea(null);
		}
		if (report.getContentType()!=null && StringUtils.isEmpty(report.getContentType().getId())) {
			report.setContentType(null);
		}
		if (report.getEventType()!=null && StringUtils.isEmpty(report.getEventType().getId())) {
			report.setEventType(null);
		}
		if (report.getCategory()!=null && StringUtils.isEmpty(report.getCategory().getId())) {
			report.setCategory(null);
		}
		
		if (!StringUtils.isEmpty(report.getId())) {
			Report old = getById(report.getId());
			if (old!=null) {
				report.setReportStatus(old.getReportStatus());
				report.setDefaultFileId(old.getDefaultFileId());
				report.setDefaultFileType(old.getDefaultFileType());
				report.setUserImgUrl(old.getUserImgUrl());
				report.setReportUserId(old.getReportUserId());
				if(old.getAppUser()!=null){
					report.setAppUser(old.getAppUser());
				}
			}
		}
		if ((report.getAppUser()==null || report.getAppUser().getUserId()==null)
				&& !StringUtils.isEmpty(report.getReportUserId())) {
			try {
				Integer userId = Integer.parseInt(report.getReportUserId());
				if (userId!=null && userId>0) {
					report.setAppUser(appUserService.getAppUserFromLocalOrRemote(userId));
				}
				
			} catch (Exception e) {
			}
		}
		
		return super.save(report);
	}
	
	
	
	
	
	@Autowired
	private ReportFileService reportFileService;
	/**
	 * 保存报料及附件
	 * @param report
	 * @param files
	 * @param fileNames
	 * @param fileTypes
	 * @return
	 * @throws IOException
	 */
	public String save(Report report, 
			List<File> files, 
			List<String> fileNames,
			List<FileType> fileTypes) throws IOException{
		
		String reportId = save(report);
		
		if (files != null && files.size()>0) {
			boolean hasImage=false;
			boolean hasVideo=false;
			boolean hasAudio=false;
			boolean isImage = false;
			List<String> fileIds = new ArrayList<>();
			for(int i=0; i<files.size(); i++){
				File file = files.get(i);
				System.out.println("file size:" + file.length() + ", fileName:" + file.getAbsolutePath() );
				String fileId = objectFileService.saveFile(reportId, file, fileNames.get(i), fileTypes.get(i));
				System.out.println("fileId:" + fileId);
				//视频转码或音频转码
				if (fileTypes.get(i)==FileType.Video) {
					reportFileService.dealVideo(report, fileId, file);
				}else if (fileTypes.get(i)==FileType.Audio) {
					System.out.println("deal audio");
					reportFileService.dealAudio(report, fileId, file);
				}else if (fileTypes.get(i)==FileType.Image) {
					adjustImage(fileId);
					isImage = true;
				}
				
				fileIds.add(fileId);
				
				//return reportId;
//				if (!hasImage && !StringUtils.isEmpty(fileId) && fileTypes.get(i)== FileType.Image) {
//					report.setId(reportId);
//					report.setDefaultFileId(fileId);
//					report.setDefaultFileType(FileType.Image);
//					saveSimple(report);
//					hasImage=true;
//				}
				if (fileTypes.get(i)== FileType.Audio) {
					hasAudio=true;
				}
				if (fileTypes.get(i)==FileType.Video) {
					hasVideo=true;
				}
			}
			
			if (isImage) {
				report.setId(reportId);
				report.setDefaultFileType(FileType.Image);
				int pos = 0;
				int imageCount = 0;
				for(int i=0; i<files.size(); i++){
					if (fileTypes.get(i)==FileType.Image
							&& !StringUtils.isEmpty(fileIds.get(i))) {
						if (pos==0) {
							report.setDefaultFileId(fileIds.get(i));
						}else if (pos==1) {
							report.setFileId2(fileIds.get(i));
						}else if (pos==2) {
							report.setFileId3(fileIds.get(i));
						}
						pos++;
					}
				}
				report.setImageAmount(pos);
				saveSimple(report);
				hasImage=true;
			}
			
			
			if (!hasImage) {
				if (hasVideo) {
					report.setDefaultFileType(FileType.Video);
					saveSimple(report);
				}
				else if (hasAudio) {
					report.setDefaultFileType(FileType.Audio);
					saveSimple(report);
				}
			}
		}
		
		return reportId;
	}
	
	public void saveReportFile(String reportId, File file, String fileName, FileType fileType){
		Report report = getById(reportId);
		if (report != null) {
			try {
				String fileId = objectFileService.saveFile(reportId, file, fileName, fileType);
				if (fileType==FileType.Video) {
					reportFileService.dealVideo(report, fileId, file);
				}else if (fileType==FileType.Audio) {
					System.out.println("deal audio");
					reportFileService.dealAudio(report, fileId, file);
				}else if (fileType==FileType.Image) {
					adjustImage(fileId);
				}
				if(report.getDefaultFileType()==null){
					if (fileType==FileType.Image) {
						report.setDefaultFileId(fileId);
					}
					report.setDefaultFileType(fileType);
					saveSimple(report);
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	private String smallFilePath = "small";
	private int width=500;
	private void adjustImage(String fileId){
		String sourcePath = BaseSetting.getFilePath(fileId);
		String desPath = BaseSetting.getFilePath(smallFilePath, fileId);
		try {
			ImageUtil.resizeImage(sourcePath, width, desPath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 删除报料附件
	 * @param fileId
	 */
	public void delReportFile(String reportId, String fileId){
		Report report = getById(reportId);
		if(report != null){
			ObjectFile file = objectFileService.getById(fileId);
			if(file!=null){
				objectFileService.deleteById(fileId);
				if (file.getFileType()==FileType.Video && report.getDefaultFileType()==FileType.Video) {
					report.setDefaultFileId(null);
					report.setDefaultFileType(null);
					setReportDefaultFile(report);
				}else if (file.getFileType()==FileType.Audio && report.getDefaultFileType()==FileType.Audio) {
					report.setDefaultFileId(null);
					report.setDefaultFileType(null);
					setReportDefaultFile(report);
				}else if (file.getFileType()==FileType.Image
						&& fileId.equals(report.getDefaultFileId())
						) {
					report.setDefaultFileId(null);
					report.setDefaultFileType(null);
					setReportDefaultFile(report);
				}
			}
		}
	}
	
	private void setReportDefaultFile(Report report){
		List<ObjectFile> files = getReportFiles(report.getId());
		if (files!=null && files.size()>0) {
			ObjectFile file = files.get(0);
			if (file.getFileType()==FileType.Audio
					&& !StringUtils.isEmpty(file.getObjectType())
					) {
				report.setDefaultFileId(file.getObjectType());
			}else if (file.getFileType()==FileType.Image) {
				report.setDefaultFileId(file.getId());
			}
			report.setDefaultFileType(file.getFileType());
			saveSimple(report);
		}
		
	}
	
	/**
	 * 接收报料
	 * @param reportId
	 */
	public void acceptReport(String reportId){
		Report report = getById(reportId);
		if (report != null) {
			report.setAcceptTime(new Date());
			report.setReportStatus(ReportStatus.Accept);
			saveSimple(report);
		}
	}
	
	
	@Autowired
	private ReplyService replyService;
	/**
	 * 回复报料
	 * @param reportId
	 * @param content
	 */
	public void replyReport(String reportId, String content, String replyId, User user){
		Report report = getById(reportId);
		if (report != null) {
			if(StringUtils.isEmpty(replyId)){
				report.setReplyContent(content);
				report.setReplyTime(new Date());
				report.setReportStatus(ReportStatus.Dealed);
				
				Reply reply = new Reply();
				reply.setContent(content);
				reply.setReplyUser(user);
				reply.setReplyDate(new Date());
				reply.setReportId(reportId);
				replyService.save(reply);
				
				saveSimple(report);
			}else{
				Reply reply = replyService.getById(replyId);
				if (reply != null) {
					reply.setContent(content);
					replyService.saveSimple(reply);
				}
			}
		}
	}
	
	
	
	
	
	/**
	 * 查询报料附件
	 * @param reportId
	 * @return
	 */
	public List<ObjectFile> getReportFiles(String reportId) {
		return objectFileService.getObjectFiles(reportId, null);
	}
	
}
