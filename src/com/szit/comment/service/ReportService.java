package com.szit.comment.service;

import com.szit.comment.entity.Report;
import com.szit.comment.entity.query.ReportQuery;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.hsit.common.kfbase.entity.FileType;
import com.hsit.common.kfbase.entity.ObjectFile;
import com.hsit.common.service.AppBaseService;
import com.hsit.common.uac.entity.User;

public interface ReportService extends AppBaseService<Report, ReportQuery>{
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
			List<FileType> fileTypes) throws IOException;
	
	
	/**
	 * 接收报料
	 * @param reportId
	 */
	public void acceptReport(String reportId);
	
	
	/**
	 * 回复报料
	 * @param reportId
	 * @param content
	 */
	public void replyReport(String reportId, String content, String replyId, User user);
	
	
	/**
	 * 查询报料附件
	 * @param reportId
	 * @return
	 */
	public List<ObjectFile> getReportFiles(String reportId);
	
	
	/**
	 * 删除报料附件
	 * @param fileId
	 */
	public void delReportFile(String reportId, String fileId);
	
	
	public void saveReportFile(String reportId, File file, String fileName, FileType fileType);
}
