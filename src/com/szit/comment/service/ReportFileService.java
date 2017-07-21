package com.szit.comment.service;

import java.io.File;

import com.szit.comment.entity.Report;

public interface ReportFileService {
	public void dealVideo(Report report, String fileId, File file);
	
	public void dealAudio(Report report, String fileId, File file);
}
