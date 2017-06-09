package com.szit.neteasecloud.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;

public class HtmlCodeToText {
	

	 private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式  
	 private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式  
	 private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式  
	 private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符  
	
	 public static String delHTMLTag(String htmlStr) {  
	        Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);  
	        Matcher m_script = p_script.matcher(htmlStr);  
	        htmlStr = m_script.replaceAll(""); // 过滤script标签  
	  
	        Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);  
	        Matcher m_style = p_style.matcher(htmlStr);  
	        htmlStr = m_style.replaceAll(""); // 过滤style标签  
	  
	        Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);  
	        Matcher m_html = p_html.matcher(htmlStr);  
	        htmlStr = m_html.replaceAll(""); // 过滤html标签  
	  
	        Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);  
	        Matcher m_space = p_space.matcher(htmlStr);  
	        htmlStr = m_space.replaceAll(""); // 过滤空格回车标签  
	        return htmlStr.trim(); // 返回文本字符串  
	    }  
	
	  	public static String getTextFromHtml(String htmlStr){  
	        htmlStr = delHTMLTag(htmlStr);  
	        htmlStr = htmlStr.replaceAll("&nbsp;", "");
	        htmlStr = StringEscapeUtils.unescapeHtml4(htmlStr).trim();
	        return htmlStr;  
	  	}
	  	
	  	public static void getImg(String code,List<Map<String, Object>> listResult){
			String[] list =code.split("<img");
			for(int i = 0;i<list.length;i++){
				if(list[i].contains("src")){
					String url = list[i].split("src=")[1].split("\"")[1];
					Map<String,Object> mapImg = new TreeMap<String,Object>();
					mapImg.put("type", "img");
					mapImg.put("value", url.trim().replaceAll("\"", "").replaceAll("\'", ""));
					listResult.add(mapImg);
					if(!getTextFromHtml("<p"+list[i]).equals("")){
						Map<String,Object> map = new TreeMap<String,Object>();
						map.put("type", "text");
						map.put("value", getTextFromHtml("<p"+list[i]));
						listResult.add(map);
					}
				}
			}
		}
	  	
	  	public static List<Map<String, Object>> getText(String content) throws BizException,ErrorException {
			
			List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();

			String[] list = content.split("<p");
			
			for(int i = 0;i<list.length;i++){
				
				//包含图片
				if(list[i].contains("<img")){
					getImg(list[i],listResult);
				}else if(!list[i].equals("")){
					//文本P
					if(!(getTextFromHtml("<p"+list[i]).equals(""))){
						Map<String,Object> map = new HashMap<String,Object>();
						map.put("type", "text");
						map.put("value",  getTextFromHtml("<p"+list[i]));
						listResult.add(map);
					}
				}
			}
			return listResult;
		}
}
