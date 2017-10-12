package com.szit.arbitrate.news.action;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Maps;
import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.utils.CommonUtil;
import com.hsit.common.utils.CompressPicUtil;
import com.hsit.common.utils.PageDataBean;
import com.hsit.common.utils.UploadAccResourceUtil;
import com.szit.arbitrate.news.entity.MomentsResources;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.entity.query.MomentsResourcesQuery;
import com.szit.arbitrate.news.service.MomentsResourcesService;
import com.szit.arbitrate.news.service.NewsDetailsService;
import com.szit.arbitrate.news.service.NewsHeadInfoService;

@Namespace("/admin/news/momentsResourcesAction")
@Controller("momentsResourcesAction")
public class MomentsResourcesAction extends BaseJsonAction<MomentsResources, MomentsResourcesQuery>{

	@Autowired
	private MomentsResourcesService service;
	
	@Autowired
	private NewsHeadInfoService newsHeadInfoService;
	
	@Autowired
	private NewsDetailsService newsDetailsService;

	public MomentsResourcesService getService() {
		return service;
	}

	public void setService(MomentsResourcesService service) {
		this.service = service;
	}
	
	private File imgFile;
	private String id;//实体id
	private String imgFileFileName; // 上传文件的真实名字
	private String imgFileContentType;
	
	private File upload;  //文件  
	private String uploadContentType;  //文件类型  
	private String uploadFileName;   //文件名  

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public File getImgFile() {
		return imgFile;
	}
	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}


	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}
	
	@Action(value = "lunboImgList")
	public void lunboImgList(){
		Paging  paging  = this.getEntityQuery().getPaging();
		MomentsResourcesQuery momentsResourcesQuery = new MomentsResourcesQuery();
		momentsResourcesQuery.setIsLunBo("0");
		momentsResourcesQuery.setPaging(paging);
		List<MomentsResources> list = service.getEntities(momentsResourcesQuery);
		PageDataBean<MomentsResources> pageList = new PageDataBean<MomentsResources>(paging,list);
		outJson(jsonMapper.toJson(pageList));
	}
	
	//上传新闻广告图
	@Action(value = "uploadResources")
	public void uploadGoodsResources(){
		Map<String,String> maps = Maps.newHashMap();
		try {
			String newId = getRequest().getParameter("newId");
			logger.debug("上传文件:"+imgFile.getAbsolutePath());
			String uploadPrefix = imgFileFileName.substring(imgFileFileName.lastIndexOf(".")+1);//后缀名
			String name = UUID.randomUUID().toString().replace("-", "");
			String filename= name+"."+uploadPrefix;
			String folderPath = MomentsResources.newsDir;
			logger.debug("上传目录:"+folderPath);
			//传入服务器
			filename = UploadAccResourceUtil.saveCompressFile(imgFile, imgFileFileName, folderPath ,1280 ,768 ,true);
			CompressPicUtil.getInstance().compressPic(imgFile, CommonUtil.getAccResourceUploadFolderPath(folderPath), filename.substring(0,filename.lastIndexOf("."))+"_max."+uploadPrefix, 1280, 200, true);
			maps.put("urlpath",filename);
			maps.put("state","OK");
			outSuccessJson(jsonMapper.toJson(maps));
		} catch (Exception e) {
			logger.error("上传附件异常错误",e);
			this.outFailJson("上传失败", e.getMessage());
		}
	}
	
	//上传新闻主图
	@Action(value = "uploadMainImg")
	public void uploadMainImg(){
		try {
			Map<String,String> maps = Maps.newHashMap();
			//判断主图是否存在
			MomentsResources momentsResources = new MomentsResources();
			NewsHeadInfo newsHeadInfo = newsHeadInfoService.getById(id);
			String uploadPrefix = imgFileFileName.substring(imgFileFileName.lastIndexOf(".")+1);//后缀名
			String filename= UUID.randomUUID().toString().replace("-", "")+"."+uploadPrefix;
			String folderPath = MomentsResources.newsDir;
			//传入服务器
			filename = UploadAccResourceUtil.saveCompressFile(imgFile, imgFileFileName, folderPath ,1280,768,true);
			momentsResources.setUrl(filename);
			momentsResources.setCreateTime(new Date());
			momentsResources.setIsLunBo("1");
			newsHeadInfo.setMomentsResources(momentsResources);
			newsHeadInfoService.save(newsHeadInfo);
			maps.put("urlpath", momentsResources.getUrl());
			maps.put("state","OK");
			maps.put("message","上传成功!");
			outSuccessJson(jsonMapper.toJson(maps));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//上传新闻内容图
	@Action(value = "uploadDetailsImg")
	public void uploadDetailsImg() throws IOException{
		//单图上传
		if(imgFile!=null){
			String resuploadfilepath = "";
			String uploadPrefix = imgFileFileName.substring(imgFileFileName.lastIndexOf(".")+1);//后缀名
			String filename= UUID.randomUUID().toString().replace("-", "")+"."+uploadPrefix;
			String folderPath = MomentsResources.newsDir;
			//传入服务器
			

			//基于myFile创建一个文件输入流  
	        BufferedInputStream is = new BufferedInputStream(new FileInputStream(imgFile));
	        // 设置上传文件目录  
	        String uploadPath = ServletActionContext.getServletContext()  
	                .getRealPath(folderPath);

	        File toFile = new File(uploadPath, filename); 
	        
	     // 创建一个输出流  
	        BufferedOutputStream os = new BufferedOutputStream(new FileOutputStream(toFile));  
			
	        //设置缓存  
	        byte[] buffer = new byte[1024];  
	  
	        int length = 0;  
	  
	        //读取myFile文件输出到toFile文件中  
	        while ((length = is.read(buffer)) > 0) {  
	            os.write(buffer, 0, length);  
	        }
	        os.flush();
	        //关闭输入流  
	        is.close();  
	        //关闭输出流  
	        os.close();  
			
			String basePath = getRequest().getScheme()+"://"+getRequest().getServerName()+":"+getRequest().getServerPort()+"/"+getRequest().getContextPath()+"/";
		
			JSONObject obj = new JSONObject();
			obj.put("error", 0);
			obj.put("url", basePath+folderPath+"/"+filename);
			PrintWriter out = getResponse().getWriter();
			out.println(obj.toString());
		}
	}
	String url;
	String newId;
	String title;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNewId() {
		return newId;
	}
	public void setNewId(String newId) {
		this.newId = newId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	//编辑或添加
	@Action(value = "getForm")
	public void getForm(){
		
		try{
			MomentsResources mrs = service.getById(id);
			
			if(mrs==null){
				mrs = new MomentsResources();
			}
			mrs.setRescreatedatatime(new Date());
			mrs.setUrl(url);
			mrs.setRescreatedatatime(new Date());
			mrs.setIsLunBo("0");
			mrs.setLink(newId);
			mrs.setTitle(title);
			service.save(mrs);
		}catch(Exception e){
			
		}
	}
	
	
	@Action(value = "removeById")
	public void removeById(){
		service.deleteById(getId());
	}
	
	//加入新闻链接
	@Action(value = "addNews")
	public void addNews(){
		Map<String,String> maps = Maps.newHashMap();
		String newsId = getRequest().getParameter("newsId");
		try{
			MomentsResources m = service.getById(id);
			if(m!=null){
				m.setLink(newsId);
			}
			service.save(m);
			maps.put("newsId",m.getLink());
			maps.put("state","OK");
			outSuccessJson(jsonMapper.toJson(maps));
		}catch(Exception e){
			maps.put("state","NO");
			outSuccessJson(jsonMapper.toJson(maps));
		}
	}
	
	//上传到工程路径
	public static void uploadToProject(String dir,String keepDir,String fileName){
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		 try {
			 File f = new File(keepDir);
				if(!f.exists()){
					f.mkdirs();
				}
			bis = new BufferedInputStream(new FileInputStream(new File(dir)));
			bos = new BufferedOutputStream(new FileOutputStream(new File(keepDir+"/"+fileName)));
			byte[] bt = new byte[1024];
			int len = 0;
			while((len=(bis.read(bt)))!=-1){
				bos.write(bt);
			}
			bos.flush();
		 } catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				bis.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}
}