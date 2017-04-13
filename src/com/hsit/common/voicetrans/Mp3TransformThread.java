package com.hsit.common.voicetrans;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Mp3TransformThread implements Runnable{

private static Logger logger = LoggerFactory.getLogger(Mp3TransformThread.class);
	
	private File voicefile;
	private String transformfilename;
	private String folderPath;
	
	public Mp3TransformThread(File voicefile,String transformfilename,String folderPath) {
        this.voicefile = voicefile;
        this.transformfilename = transformfilename;
        this.folderPath = folderPath;
    }
	
	
	public void run() {
		try {
			VoiceTranscodingUtils.voiceTransform(voicefile, transformfilename,folderPath);
		} catch (Exception e) {
			logger.error("MP3线程转码处理错误异常",e);
		}
		
	}

}
