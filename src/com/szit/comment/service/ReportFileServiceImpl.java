package com.szit.comment.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.taglibs.standard.lang.jstl.test.beans.PublicBean1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsit.common.kfbase.entity.FileType;
import com.hsit.common.kfbase.entity.ObjectFile;
import com.hsit.common.kfbase.service.BaseSetting;
import com.hsit.common.kfbase.service.ObjectFileService;
import com.szit.comment.entity.Report;
import com.szit.comment.utils.HttpPost;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncoderProgressListener;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.FFMPEGLocator;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;
import net.sf.json.JSONObject;

@Service
public class ReportFileServiceImpl implements ReportFileService {
	//private String serverUrl = "http://uploads.cutv.com:8088/video/api_video.php";
	private String serverUrl = "http://uploads.cutv.com/video/api_video.php";
	public void dealVideo(Report report, String fileId, File file){
		VideoTask videoTask = new VideoTask(fileId, file);
		Thread thread = new Thread(videoTask);
		thread.start();		
	}
	
	public void dealAudio(Report report, String fileId, File file){
		AudioTask audioTask = new AudioTask(fileId, file);
		Thread thread = new Thread(audioTask);
		thread.start();
	}
	
	@Autowired
	private ObjectFileService objectFileService;
	@Autowired
	private ReportService reportService;
	private class VideoTask implements Runnable{
		
		public VideoTask(String fileId, File file){
			this.fileId = fileId;
			this.file = file;
		}
		
		private String fileId;
		private File file;
		
		@Override
		public void run() {
			try{
				Map<String, File> files = new HashMap<>();
				files.put("video", file);
				System.out.println("start convert video");
				String response = HttpPost.formUpload(serverUrl, null, files);
				System.out.println("cutvdebug video convert result:" + response);
				JSONObject jsonObject = JSONObject.fromObject(response);
				String status = jsonObject.optString("status");
				if ("ok".equals(status)) {
					JSONObject rst = jsonObject.optJSONObject("data");
					if (rst != null) {
						String duration = rst.optString("duration");
						String imageUrl = rst.optString("image");
						if(!StringUtils.isEmpty(imageUrl)){
							imageUrl = imageUrl.replace("http://uploads.cutv.com/", "http://uploads.cutv.com:8088/");
						}
						String fileUrl = rst.optString("encode_file");
						if (!StringUtils.isEmpty(fileUrl)) {
							fileUrl = fileUrl.replace("http://uploads.cutv.com/", "http://uploads.cutv.com:8088/");
						}
						ObjectFile objectFile = objectFileService.getById(fileId);
						if (objectFile != null) {
							objectFile.setDescription(duration);
							objectFile.setObjectName(fileUrl);
							objectFile.setObjectType(imageUrl);
							objectFileService.saveSimple(objectFile);
							
							Report report = reportService.getById(objectFile.getObjectId());
							if (report != null && report.getDefaultFileType()==FileType.Video) {
								report.setDefaultFileId(imageUrl);
								reportService.saveSimple(report);
							}
						}
						
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	private class AudioTask implements Runnable{
		
		public AudioTask(String fileId, File file){
			this.fileId = fileId;
			this.file = file;
		}
		
		private String fileId;
		private File file;
		
		@Override
		public void run() {
			try {
				System.out.println("source file length:" + file.length());
				File mp3File = new File(BaseSetting.getFileBasePath() + File.separator +  "temp" + File.separator + UUID.randomUUID().toString());
				AudioAttributes audio = new AudioAttributes();
				Encoder encoder = new Encoder();
				
				String[] strs = encoder.getSupportedDecodingFormats();
				for(int i=0; i<strs.length; i++){
					System.out.println("format:" + strs[i]);
				}
				strs=encoder.getAudioDecoders();
				for(int i=0; i<strs.length; i++){
					System.out.println("format:" + strs[i]);
				}
				
				audio.setCodec("libmp3lame");
				EncodingAttributes attrs = new EncodingAttributes();
				attrs.setFormat("mp3");
				attrs.setAudioAttributes(audio);
				encoder.encode(file, mp3File, attrs, new EncoderProgressListener() {
					
					@Override
					public void sourceInfo(MultimediaInfo arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void progress(int arg0) {
						// TODO Auto-generated method stub
						
					}
					
					@Override
					public void message(String arg0) {
						System.out.println("encoder listener message:" + arg0);
					}
				});
				System.out.println("mp3 file length:" + mp3File.length());
				
				//新文件不和report的关联
				String newId = objectFileService.saveFile(UUID.randomUUID().toString(), mp3File, "", FileType.Audio);
				
				ObjectFile objectFile = objectFileService.getById(fileId);
				if (objectFile != null) {
					objectFile.setObjectName(newId);
					objectFileService.saveSimple(objectFile);
				}				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InputFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (EncoderException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
