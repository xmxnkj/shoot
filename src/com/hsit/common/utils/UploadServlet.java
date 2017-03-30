package com.hsit.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsit.common.voicetrans.TransformSilkToMp3;
import com.hsit.common.voicetrans.VoiceTranscodingUtils;
import com.szit.arbitrate.mediation.entity.enumvo.CaseResTypeEnum;

public class UploadServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(UploadServlet.class);

    public UploadServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
    	JSONObject json = new JSONObject();
    	request.setCharacterEncoding("utf-8");//设置编码
        //获得磁盘文件条目工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        String folderPath = request.getSession().getServletContext().getRealPath("");
        //获取文件需要上传到的路径
        
        FileItem fileItem = null;
        String restype = "";
        String clientid = "";
        String newfilename = "";
        String uploadPrefix = "";
        
        String path = request.getRealPath("/upload");
        //如果没以下两行设置的话，上传大的 文件 会占用 很多内存，
        //设置暂时存放的 存储室 , 这个存储室，可以和 最终存储文件 的目录不同
        /**
         * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上，
         * 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tem 格式的
         * 然后再将其真正写到 对应目录的硬盘上
         */
        factory.setRepository(new File(path));
        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
        factory.setSizeThreshold(1024*1024) ;
        //高水平的API文件上传处理
        ServletFileUpload upload = new ServletFileUpload(factory);
        try {
            //可以上传多个文件
            @SuppressWarnings("unchecked")
			List<FileItem> list = (List<FileItem>)upload.parseRequest(request);
            for(FileItem item : list){
                //获取表单的属性名字
                String name = item.getFieldName();
                logger.info("name:", name);
                logger.info("item:", item);
                //如果获取的 表单信息是普通的 文本 信息
                if(item.isFormField()){
                    //获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
                	 String value = item.getString("utf-8");
                	 if(name.equals("clientid")){
                		 clientid = value; 
                	 }else if(name.equals("restype")){
                		 restype = value;
                	 }
                }else {
                	if("file".equals(name)){
                		fileItem = item;
                    }
                }
            }
            String filename = fileItem.getName();
            logger.info("filename:",filename);
            if(!filename.equals("")){
    			uploadPrefix = filename.substring(filename.lastIndexOf(".")+1);
    		}
            
            newfilename= UUID.randomUUID().toString().replace("-", "")+"."+uploadPrefix;
            json.put("newfilename", newfilename);
            //截取 上传文件的 字符串名字，加1是 去掉反斜杠，
            String catalog = "";
            if(restype.equals("identifyImgFile")){
            	catalog = "\\uploads\\client";
            }else if(restype.equals("Image")){
            	catalog = "\\uploads\\chat\\image";
            }else if(restype.equals("Voice")){
            	catalog = "\\uploads\\chat\\voice";
            }else if(restype.equals("Video")){
            	catalog = "\\uploads\\chat\\video";
            }else if(restype.equals("File")){
            	catalog = "\\uploads\\chat\\file";
            }
            String temppath = folderPath+catalog;
            File tempfile=new File(temppath);
            if(!tempfile.exists()){
            	tempfile.mkdirs();
            }
            
            folderPath+=catalog+File.separator+newfilename;
            logger.info("文件存储的路径:",folderPath);
            File file=new File(folderPath);
            if(!file.exists()){
            	file.createNewFile();
            }
            OutputStream out = new FileOutputStream(file);
            InputStream in = fileItem.getInputStream() ;
            int length = 0 ;
            byte [] buf = new byte[1024] ;
            logger.info("文件大小:",fileItem.getSize());
            while( (length = in.read(buf) ) != -1){
            	out.write(buf, 0, length);
            }
            in.close();
            out.close();
            
            if(restype.equals(CaseResTypeEnum.Voice.toString()) && !uploadPrefix.equals("mp3") 
            		&& !uploadPrefix.equals("silk")){//语音如果不是mp3进行mp3转码
	        	String exportfilename = UUID.randomUUID().toString().replace("-", "")+".mp3";
	        	VoiceTranscodingUtils.voiceTransformThread(file, exportfilename, temppath);
	        	json.put("newfilename", exportfilename);
	        }
            if(restype.equals(CaseResTypeEnum.Voice.toString()) && uploadPrefix.equals("silk") ){//语音如果不是mp3进行mp3转码
            	String exportfilename = UUID.randomUUID().toString().replace("-", "");
            	String silk = folderPath;
            	 //String silk = "E:\\1a5e5f36334f41eb8992f4f27debd1a6.silk";
            	String pcm = "D:\\apache-tomcat-7.0.72\\webapps\\arbitrate\\uploads\\chat\\voice\\"+exportfilename+".pcm";
                String mp3 = "D:\\apache-tomcat-7.0.72\\webapps\\arbitrate\\uploads\\chat\\voice\\"+exportfilename+".mp3";
            	boolean b = TransformSilkToMp3.getPcm(silk, pcm);
            	if (b){
            		boolean success = TransformSilkToMp3.getMp3(pcm, mp3);
            		if(success){
            			json.put("message", "转码结果成功");
            			json.put("newfilename", exportfilename+".mp3");
            		}else{
                		json.put("message:", "转码出现错误");
                	}
            	}
            }
            
            
        } catch (FileUploadException e) {
            throw new IOException(e);
        }
        PrintWriter out = response.getWriter(); 
        json.put("success", true);
        out.write(json.toString()); 
        out.close();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
	
}
