package com.hsit.common.voicetrans;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncoderProgressListener;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsit.common.voicetrans.exceptions.VoiceTransformBizException;
import com.hsit.common.voicetrans.exceptions.VoiceTransformErrorException;

/**
 * 
* @ClassName: VoiceTranscodingUtils
* @Description:语音转码工具
* @author Administrator
* @date 2017年5月31日 上午9:59:06
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class VoiceTranscodingUtils {
	
private static Logger logger = LoggerFactory.getLogger(VoiceTranscodingUtils.class);
	
	private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	
	/**
	 * 
	* @Title: voiceTransformThread 
	* @Description: 线程操作
	* @param @param voicefile
	* @param @param transformfilename
	* @param @param userid
	* @return void
	* @throws
	 */
	public static void voiceTransformThread(File voicefile,String transformfilename,String folderPath) throws VoiceTransformBizException,VoiceTransformErrorException{
		if(StringUtils.isEmpty(transformfilename)){
			throw new VoiceTransformBizException("转换的文件名不能为空");
		}
		if(voicefile==null){
			throw new VoiceTransformBizException("转换的源文件不能为空");
		}
		Mp3TransformThread mp3TransformThread = new Mp3TransformThread(voicefile,transformfilename,folderPath);
		cachedThreadPool.execute(mp3TransformThread);
	}
	
	/**
	 * 
	* @Title: voiceTransform 
	* @Description: 语音转换
	* @param @param voicefile
	* @param @param transformfilename
	* @param @throws VoiceTransformBizException
	* @param @throws VoiceTransformErrorException
	* @return void 
	* @throws
	 */
	public static void voiceTransform(File voicefile,String transformfilename,String folderPath) throws VoiceTransformBizException,VoiceTransformErrorException{
		if(StringUtils.isEmpty(transformfilename)){
			throw new VoiceTransformBizException("转换的文件名不能为空");
		}
		if(voicefile==null){
			throw new VoiceTransformBizException("转换的源文件不能为空");
		}
		
		mp3TransformCoding(voicefile,transformfilename,folderPath);
		
	}
	
	/**
	 * 
	* @Title: transformCoding 
	* @Description: MP3转码
	* @param @param voicefile
	* @param @param transformfilename
	* @param @param userid
	* @param @throws VoiceTransformErrorException
	* @return void 
	* @throws
	 */
	private static void mp3TransformCoding(File voicefile,String transformfilename, String folderPath)throws VoiceTransformErrorException{
		try {
			
			logger.debug("转码前的文件名:{}",voicefile.getName());
			if(!voicefile.exists()){
				logger.debug("转码前文件不存在:{}",voicefile.getAbsolutePath());
				throw new VoiceTransformErrorException("语音文件不存在,请联系系统管理员!");
			}
			
			
			
			File mp3FileCatalog = new File(folderPath);
			 //当这个路径的文件不存在，将在磁盘上新建改文件
	        if(!mp3FileCatalog.exists()){
	        	logger.debug("创建文件目录:{}",mp3FileCatalog.getAbsolutePath());
	        	mp3FileCatalog.mkdirs();
	        }
	        
			
			File mp3File = new File(folderPath+ File.separator+transformfilename);
			logger.debug("转码保存的文件路径:{}",mp3File.getPath());
			if(!mp3File.exists()){
				logger.debug("创建文件:{}",mp3File.getAbsolutePath());
				mp3File.createNewFile();
			}
	
			AudioAttributes audio = new AudioAttributes();
			audio.setCodec("libmp3lame");
			audio.setBitRate(new Integer(64000));
			audio.setChannels(new Integer(2));
			audio.setSamplingRate(new Integer(22050));
	
			EncodingAttributes attrs = new EncodingAttributes();
			attrs.setFormat("mp3");
			attrs.setAudioAttributes(audio);
			
			Encoder encoder = new Encoder();
			encoder.encode(voicefile, mp3File, attrs, new EncoderProgressListener() {
				@Override
				public void sourceInfo(MultimediaInfo arg0) {
					
				}
				@Override
				public void progress(int arg0) {
					
				}
				@Override
				public void message(String arg0) {
					logger.debug("MP3转码监听消息:" + arg0);
				}
			});
		} catch (Exception e) {
			logger.error("转码MP3异常错误",e);
			throw new VoiceTransformErrorException(e);
		}
	}
	
	
	   public static void changeToMp3(String sourcePath, String targetPath) throws VoiceTransformErrorException{  
	        File source = new File(sourcePath);  
	        File target = new File(targetPath);  
	        AudioAttributes audio = new AudioAttributes();  
	        Encoder encoder = new Encoder();  
	  
	        audio.setCodec("libmp3lame");  
	        EncodingAttributes attrs = new EncodingAttributes();  
	        attrs.setFormat("mp3");  
	        attrs.setAudioAttributes(audio);  
	  
	        try {  
	            encoder.encode(source, target, attrs);  
	        } catch (IllegalArgumentException e) {  
	            e.printStackTrace();  
	        } catch (InputFormatException e) {  
	            throw new VoiceTransformErrorException(e); 
	        } catch (EncoderException e) {  
	            throw new VoiceTransformErrorException(e);  
	        }  
	    }

}
