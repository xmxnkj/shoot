package com.szit.arbitrate.mediation.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.common.collect.Maps;
import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.mediation.entity.LegalDoc;
import com.szit.arbitrate.mediation.entity.LegalDocComments;
import com.szit.arbitrate.mediation.entity.LegalDocDetail;
import com.szit.arbitrate.mediation.entity.enumvo.DocTypeEnum;
import com.szit.arbitrate.mediation.entity.query.LegalDocCommentsQuery;
import com.szit.arbitrate.mediation.entity.query.LegalDocDetailQuery;
import com.szit.arbitrate.mediation.entity.query.LegalDocQuery;
import com.szit.arbitrate.mediation.service.LegalDocCommentsService;
import com.szit.arbitrate.mediation.service.LegalDocDetailService;
import com.szit.arbitrate.mediation.service.LegalDocService;

import net.sf.json.JSONObject;

@Namespace("/admin/mediation/legaldoc")
@Controller("legalDocAction")
public class LegalDocAction extends BaseJsonAction<LegalDoc, LegalDocQuery>{

	private static final long serialVersionUID = 5202340130527002828L;
	
	@Autowired
	private LegalDocService service;
	
	@Autowired
	private LegalDocCommentsService legalDocCommentsService;
	
	@Autowired
	private LegalDocDetailService legalDocDetailService;

	public LegalDocService getService() {
		return service;
	}

	public void setService(LegalDocService service) {
		this.service = service;
	}
	
	@Override
	protected void beforeQuery() {
		LegalDocQuery query = this.getEntityQuery();
		String classic = this.getRequest().getParameter("classic");
		if(StringUtils.isNotEmpty(classic) && classic.equals("true")){
			query.setDocType(DocTypeEnum.valueOf("ClassicCase"));
			query.setClassic(true);
		}else{
			query.setDocType(DocTypeEnum.valueOf("ClassicCase"));
			query.setClassic(false);
		}
		this.setEntityQuery(query);
	}

	//文档id
	private String legaldocid;
	
	public String getLegaldocid() {
		return legaldocid;
	}

	public void setLegaldocid(String legaldocid) {
		this.legaldocid = legaldocid;
	}

	@Action(
			value="legaldocformpage", 
			results={
			   @Result(name="success", location="/WEB-INF/jsp/mobile/news/legaldocform.jsp")}
		   )
	public String getLegalDocById() {
		if(StringUtils.isEmpty(legaldocid)){
			this.getRequest().setAttribute("legaldocid", "");
		    this.getRequest().setAttribute("error","非法参数!");
		}else{
			this.getRequest().setAttribute("legaldocid", legaldocid);
			this.getRequest().setAttribute("error","");
			logger.debug("legaldocid:{}",this.getRequest().getAttribute("legaldocid").toString());
		}
		return SUCCESS;
	}
	
	
	/**
	    * 
	   * @Title: upAndDownShelve 
	   * @Description: 文档上下架操作
	   * @return void 
	   * @throws
	    */
	@Action(value = "upAndDownShelve")
	public void upAndDownShelve(){
		JSONObject json = new JSONObject();
		try {
			String legaldocid = this.getRequest().getParameter("id");
			String oper = this.getRequest().getParameter("oper");
			Map<String, Object> params = Maps.newHashMap();
			params.put("legaldocid", legaldocid);
			params.put("oper", oper);
			service.upAndDownShelve(params);
			json.put("success", true);
			json.put("message", "操作成功!");
			json.put("data", "");
		} catch (BizException biz) {
			json.put("success", false);
			json.put("message", biz.getMessage());
			json.put("data", "");
		}catch (ErrorException error) {
			json.put("success", false);
			json.put("message", error.getMessage());
			json.put("data", "");
		}
		outJson(json.toString());
	}
	/**
	 * 
	* @Title: saveLegalDoc  
	* @Description: 保存文档
	* @param     设定文件  
	* @return void    返回类型  
	* @throws
	 */
	@Action(value = "saveLegalDoc")
	public void saveLegalDoc(){
		try{
			JSONObject json = new JSONObject();
			String legaldocid = this.getRequest().getParameter("id");
			String docType = this.getRequest().getParameter("docType");
			String title = this.getRequest().getParameter("title");
			String publishUnit = this.getRequest().getParameter("publishUnit");
			String publishTime = this.getRequest().getParameter("publishTime");
			String content = this.getRequest().getParameter("content");
			String orderdisplay = this.getRequest().getParameter("orderdisplay");
			try {
				service.savaLegalDoc(legaldocid, title, docType, content, publishUnit, publishTime,Integer.parseInt(orderdisplay));
				json.put("success", true);
				json.put("message", "操作成功!");
				json.put("data", "");
			} catch (BizException biz) {
				json.put("success", false);
				json.put("message", biz.getMessage());
				json.put("data", "");
			} catch (ErrorException error) {
				json.put("success", false);
				json.put("message", error.getMessage());
				json.put("data", "");
			}
			outJson(json.toString());
		}catch(Exception e){
			
		}
	}
	
	//获取文案
	@Action(value = "getLegalDoc")
	public void getLegalDoc(){
		JSONObject json = new JSONObject();
		String legaldocid = this.getRequest().getParameter("legaldocid");
		try{
			Map<String, Object> map = service.getLegalDocDetailById(legaldocid);
			json.put("success", true);
			json.put("message", "操作成功!");
			json.put("data", map);
		}catch(Exception e){
			json.put("success", false);
			json.put("message", e.getMessage());
			json.put("data", "");
		}
		outJson(json.toString());
	}
	
	private File imgFile;
	private String imgFileFileName; // 上传文件的真实名字
	private String imgFileContentType;
	
	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
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

	//附件上传
	@Action(value = "UploadDoc")
	public void UploadDoc(){
		String legaldocid = getRequest().getParameter("legaldocid");//文案id
		
		JSONObject jsonObject = new JSONObject();
		try{
			LegalDoc legalDoc = service.getById(legaldocid);
			if(imgFile!=null){
				String uploadPrefix = imgFileFileName.substring(imgFileFileName.lastIndexOf(".")+1);//后缀名
				String filename= UUID.randomUUID().toString().replace("-", "")+"."+uploadPrefix;
				String folderPath = "uploads/mediation/legaldoc";
				
				//基于myFile创建一个文件输入流  
				BufferedInputStream is = new BufferedInputStream(new FileInputStream(imgFile));
				// 设置上传文件目录  
				String uploadPath = ServletActionContext.getServletContext().getRealPath(folderPath);

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
	        
				legalDoc.setFilePath(filename);
				jsonObject.put("urlpath",filename);
			}
		
			service.save(legalDoc);
		
			jsonObject.put("state","OK");
			outJson(jsonObject);
	  } catch (Exception e) {
		  this.outFailJson("上传失败", e.getMessage());
	  }
	}
	
	//附件下载
	@Action(value = "DownLoadDoc")
	public void DownLoadDoc() throws UnsupportedEncodingException{
		String legalDocId = getRequest().getParameter("legaldocid");
		
		LegalDoc legalDoc = service.getById(legaldocid);
		
		JSONObject jsonObject = new JSONObject();
		
		if(legalDoc==null){
			
			jsonObject.put("result", "该文件不存在");
			outJson(jsonObject);
		}else{
			//附件后缀
			int length = legalDoc.getFilePath().split("\\.").length;
			String fileType = legalDoc.getFilePath().split("\\.")[length-1];
			getResponse().setHeader("content-disposition", 
					"attachment;filename=" + URLEncoder.encode("附件"+UUID.randomUUID(), "UTF-8")+"."+fileType);
			getResponse().setContentType("application/octet-stream");
			//路径
			String folderPath = "uploads/mediation/legaldoc/";
			String uploadPath = ServletActionContext.getServletContext().getRealPath(folderPath);
			File toFile = new File(uploadPath, legalDoc.getFilePath());
			OutputStream os = null;
			BufferedInputStream is = null;
			try {
				is = new BufferedInputStream(new FileInputStream(toFile));
				byte[] by = new byte[1024];
				os = getResponse().getOutputStream();
				int len = 0;
				while((len=is.read(by))!=-1){
					os.write(by);
				}
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					is.close();
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	//附件下载
	@Action(value = "uploadImg")
	public void uploadImg(){
		JSONObject jsonObject = new JSONObject();
		try{
		//	LegalDoc legalDoc = service.getById(legaldocid);
			if(imgFile!=null){
				String uploadPrefix = imgFileFileName.substring(imgFileFileName.lastIndexOf(".")+1);//后缀名
				String filename= UUID.randomUUID().toString().replace("-", "")+"."+uploadPrefix;
				String folderPath = "uploads/mediation/legaldoc";
				
				//基于myFile创建一个文件输入流  
				BufferedInputStream is = new BufferedInputStream(new FileInputStream(imgFile));
				// 设置上传文件目录  
				String uploadPath = ServletActionContext.getServletContext().getRealPath(folderPath);

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
	        
				//legalDoc.setImage(filename);
				jsonObject.put("urlpath",filename);
			}		
			//service.save(legalDoc);
			jsonObject.put("state","OK");
			outJson(jsonObject);
	  } catch (Exception e) {
		  this.outFailJson("上传失败", e.getMessage());
	  }
	}
	
	@Action(value = "deleteDocById")
	public void deleteDocById(){
		JSONObject jsonObject = new JSONObject();
		String legaldocId = getRequest().getParameter("legaldocId");
		try{
			if(!StringUtils.isEmpty(legaldocId)){
				LegalDoc legalDoc = service.getById(legaldocId);
				
				LegalDocDetailQuery docDetailQuery = new LegalDocDetailQuery();
				docDetailQuery.setLegalDocId(legaldocId);
				List<LegalDocDetail> list = legalDocDetailService.getEntities(docDetailQuery);
				for(LegalDocDetail detail:list){
					legalDocDetailService.deleteById(detail.getId());
				}
				LegalDocCommentsQuery commentsQuery = new LegalDocCommentsQuery();
				commentsQuery.setLegalDocId(legaldocId);
				List<LegalDocComments> comms = legalDocCommentsService.getEntities(commentsQuery);
				for(LegalDocComments comm:comms){
					legalDocCommentsService.deleteById(comm.getId());
				}
				service.deleteById(legalDoc.getId());
				jsonObject.put("state","OK");
			}
			jsonObject.put("state","NO");
		}catch(Exception e){
			jsonObject.put("state","NO");
		}
		outJson(jsonObject);
	}
}