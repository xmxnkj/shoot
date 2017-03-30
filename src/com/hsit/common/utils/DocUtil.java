package com.hsit.common.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsit.common.exceptions.BizException;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class DocUtil {
	
	private static Logger logger = LoggerFactory.getLogger(DocUtil.class);

	private Configuration configuration = null;   
    
    public DocUtil() {   
        configuration = new Configuration();   
        configuration.setDefaultEncoding("utf-8");   
    }   
    
    public void createDoc(Map<String, Object> dataMap, String templateurl, String template, 
    		String url, String filename) throws BizException{   
        //设置模本装置方法和路径,FreeMarker支持多种模板装载方法。可以重servlet，classpath，数据库装载，   
        //这里我们的模板是放在com.havenliu.document.template包下面   
        configuration.setClassForTemplateLoading(this.getClass(),templateurl);   
        Template t=null;  
        Writer out = null;
        OutputStreamWriter osw = null;
        try {   
            //template为要装载的模板文件   
            t = configuration.getTemplate(template);   
            //输出文档路径及名称   
            String folderPath = CommonUtil.getAccResourceUploadFolderPath(url);
            //String folderPath = System.getProperty("user.dir")+File.separator+url.replace("/", "\\");
            //String folderPath = "D:\\Program Files\\apache-tomcat-7.0.72\\apache-tomcat-7.0.72\\arbitratewebapps\\arbitrate"+File.separator+url;
            logger.debug("上传目录:"+folderPath);
            File tempfile=new File(folderPath);
            if(!tempfile.exists()){
            	tempfile.mkdirs();
            }
            File outFile = new File(folderPath+File.separator+filename+".doc"); 
            if(!outFile.exists()){
            	outFile.createNewFile();
            }
            osw = new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8");
            out = new BufferedWriter(osw); 
            t.process(dataMap, out);   
        } catch (IOException | TemplateException e) {   
            throw new BizException(e);   
        }finally{
        	try {
        		osw.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }  
    }   
}
