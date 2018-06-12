/**
 * 
 */
package com.hsit.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsit.common.exceptions.ApplicationException;
import com.hsit.common.voicetrans.VoiceTranscodingUtils;
import com.szit.arbitrate.mediation.entity.enumvo.CaseResTypeEnum;



/**
 * @ClassName: UploadPicUtil
 * @Description:
 * @author XUJC
 * @date 2017年8月20日 下午9:55:03
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */

public class UploadAccResourceUtil {
	
	private static Logger logger = LoggerFactory.getLogger(UploadAccResourceUtil.class);
	
	//用户资源上传路径
	public static String CLIENT_RES_UPLOAD_FILEPATH = "uploads\\client";
	//资源上传路径
	public static String MEDIATION_RES_UPLOAD_FILEPATH = "uploads\\mediation";
	//用户资源上传路径
	public static String NEWS_RES_UPLOAD_FILEPATH = "uploads\\news";
	
	/**
	 * 
	* 方法描述:保存文件资源
	* 创建人: XUJC
	* 创建时间：2017年12月9日
	* @param file 文件对象
	* @param oriFileName 原文件名
	* @param catalog 目录
	* @return
	* @throws ApplicationException
	 */
	public static String saveFile(File file,String oriFileName,String catalog, String restype) throws ApplicationException{
		String filename = "";
		String folderPath = CommonUtil.getAccResourceUploadFolderPath(catalog);
		String uploadFileName = file.getName();
		String uploadPrefix = "";
		if(!oriFileName.equals("")){
			uploadPrefix = oriFileName.substring(oriFileName.lastIndexOf(".")+1);
		}else{
			uploadPrefix = uploadFileName.substring(uploadFileName.lastIndexOf(".")+1);
		}
		try{
			filename= UUID.randomUUID().toString().replace("-", "")+"."+uploadPrefix;
			FileInputStream inputStream = new FileInputStream(file);
			File newFile = new File(folderPath);
			logger.debug("上传目录:"+folderPath);
	        if(!newFile.exists()&& !newFile.isDirectory()){
	        	newFile.mkdir();
	        }
	        
	        logger.debug("上传目录文件:"+folderPath+File.separator+filename);
	        
	        File newFileAllPath = new File(folderPath+File.separator+filename);
	        
	        if(!newFileAllPath.exists()){
	        	newFileAllPath.createNewFile();
	        }
	        /*if(restype.equals(CaseResTypeEnum.Image.toString())){
	        	CompressPicUtil.getInstance().compressPic(file, folderPath, filename, 800, 600, true);
	        }*/
	        FileOutputStream outputStream = new FileOutputStream(newFileAllPath);
	        byte buffer[] = new byte[1024];
	        int c;
	        while((c = inputStream.read(buffer)) != -1) 
	            outputStream.write(buffer, 0, c);
	        outputStream.close();
	        inputStream.close();
	        if(restype.equals(CaseResTypeEnum.Voice.toString()) && !uploadPrefix.equals("mp3")){//语音如果不是mp3进行mp3转码
	        	filename = UUID.randomUUID().toString().replace("-", "")+".mp3";
	        	VoiceTranscodingUtils.voiceTransformThread(newFileAllPath, filename, folderPath);
	        }
		}catch(Exception e){
			throw new ApplicationException(e);
		}
		return filename;
	}
	
	public static String saveCompressFile(File file,String oriFileName,String catalog,int width, int height, boolean gp) throws ApplicationException{
		String filename = "";
		String folderPath = CommonUtil.getAccResourceUploadFolderPath(catalog);
		String uploadPrefix = oriFileName.substring(oriFileName.lastIndexOf(".")+1);
		try{
			filename= UUID.randomUUID().toString().replace("-", "")+"."+uploadPrefix;
			File newFile = new File(folderPath);
			logger.debug("上传目录:"+folderPath);
	        if(!newFile.exists()&& !newFile.isDirectory()){
	        	newFile.mkdir();
	        	logger.debug("目录不存在,创建目录:"+folderPath);
	        }
	        logger.debug("上传目录文件:"+folderPath+File.separator+filename);
	       String  sucstate =  CompressPicUtil.getInstance().compressPic(file, folderPath, filename, width, height, gp);
	       logger.debug("上传目录状态:{}",sucstate);
		}catch(Exception e){
			throw new ApplicationException(e);
		}
		return filename;
	}
	
	
	public static boolean isFileSizeOverflow(File file,boolean isimg, int maxsize)throws Exception{//maxsize 单位kb
		boolean flag = false;
		try {
			if(isimg){
				FileInputStream inputStream = new FileInputStream(file);
				if(inputStream.available()>maxsize*1024){
					flag = true;
				}
				inputStream.close();
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return flag;
	}
	
	public static void main(String[] args) {
		UploadAccResourceUtil.saveCompressFile(new File("e:\\" + "1.jpg")  , "111.jpg", "e:\\", 1280, 768, true);
	}
	
}
