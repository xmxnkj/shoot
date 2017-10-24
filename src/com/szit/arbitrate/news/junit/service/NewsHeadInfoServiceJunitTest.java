package com.szit.arbitrate.news.junit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsit.common.utils.JsonFormatUtil;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.news.dao.NewsDetailsDao;
import com.szit.arbitrate.news.entity.MomentsResources;
import com.szit.arbitrate.news.entity.NewsDetails;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.service.MomentsResourcesService;
import com.szit.arbitrate.news.service.NewsClickLikeService;
import com.szit.arbitrate.news.service.NewsCommentService;
import com.szit.arbitrate.news.service.NewsDetailsService;
import com.szit.arbitrate.news.service.NewsHeadInfoService;

/**
 * 
 * 	@author chenpj
 * 	@date: 	下午4:22:35
 *	@Descript 	新闻头部业务测试类
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
public class NewsHeadInfoServiceJunitTest extends BaseApiJunitTest{
	
	@Autowired
	private NewsDetailsDao newsDetailsDao;
	
	@Autowired
	private NewsHeadInfoService newsHeadInfoService;
	
	@Autowired
	private NewsCommentService newsCommentService;

	@Autowired
	private NewsDetailsService newsDetailsService;
	
	@Autowired
	private NewsClickLikeService newsClickLikeService;
	
	@Autowired
	private MomentsResourcesService momentsResourcesService;
	
	
	private Logger logger = LoggerFactory.getLogger(NewsHeadInfoServiceJunitTest.class);

	//标题模糊查询新闻列表
	@Test
	public void queryByName(){

		Map<String,Object> resultMap = newsHeadInfoService.findNewsByTitleList("", 1, 0);
		
		JsonFormatUtil.printJson("resultMap",resultMap);
	}
	
	//获取新闻内容
	@Test
	public void getDetails(){
		
		List<NewsDetails> list = newsDetailsService.findNewsDetailsList("5f578cd9-41b4-43f8-a1f8-fe7acd7abfcc");
		
		JsonFormatUtil.printJson("list", list);
	}
	
	//save新闻评论
	@Test
	public void saveComments(){
		
		newsCommentService.saveComment("f1dac59c-b393-474a-abab-95103747e427", "54e46bb5-91ce-4663-beb2-db093504248c", "评论7","localhost");
		
	}
	//删除评论
	@Test
	public void deleteComments(){
		newsCommentService.deleteComment("d251a511-66cd-4ae1-a2c2-fc3142feafae", "4f490e21-aa78-456f-84cb-1e2865515e8e");
	}
	
	/*//对评论进行回复
	@Test
	public void saveCommentReply(){
		newsCommentReplyService.saveCommentReply("a8e3429b-a805-4bed-b4b8-442a65d95409", "54e46bb5-91ce-4663-beb2-db093504248c", "回复7", "localhost");
	}*/
	
	//通过文章id加载文章的评论以及回复
	@Test
	public void loadNewsAll(){
		Map<String,Object> resultMap = newsHeadInfoService.loadNewsInformation("d251a511-66cd-4ae1-a2c2-fc3142feafae","54e46bb5-91ce-4663-beb2-db093504248c");
		
		JsonFormatUtil.printJson("json", resultMap);
	}
	//点赞
	@Test
	public void saveClickLike(){
		newsClickLikeService.saveClickLike("f1dac59c-b393-474a-abab-95103747e427", "4c5f9913-8c38-4b6b-8822-b66b9e75dd1a", "localhost");
	}
	//取消赞
	@Test
	public void deleteClickLike(){
		newsClickLikeService.deleteClickLike("d251a511-66cd-4ae1-a2c2-fc3142feafae", "4c5f9913-8c38-4b6b-8822-b66b9e75dd1a");
	}
	
	@Test
	public void addMainImage(){
		NewsHeadInfo nhi = newsHeadInfoService.getById("049b9a67-f916-485c-8ae1-aa8dc0100f85");
		MomentsResources m = new MomentsResources();
		m.setRescreatedatatime(new Date());
		m.setUrl("路径");
		momentsResourcesService.save(m);
	}
	
	@Test
	public void test(){
		Map<String, Object> newsInformation = newsHeadInfoService.loadNewsInformation("1cebc6b0-1775-40c2-a7c6-bfd487f68548", "");
		JsonFormatUtil.printJson("json", newsInformation);
	}
	
	//解析html
	@Test
	public void test1(){
		Map<String, Object> mapResult= newsHeadInfoService.loadNewsInformation("3f2155f6-1b2e-484f-81fc-1b84328d804a","");
		List<NewsDetails> lists = (List<NewsDetails>) mapResult.get("newsDetails");
		String html = lists.get(0).getNewsContent();
		
		List<Map<String, Object>> listResult = new ArrayList<Map<String, Object>>();

		String[] list = html.split("<p");
		
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
		JsonFormatUtil.printJson("json", listResult);
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
					map.put("value", getTextFromHtml("<p"+list[i]).equals(""));
					listResult.add(map);
				}
			}
		}
	}
	
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
	        htmlStr = StringEscapeUtils.unescapeHtml4(htmlStr).trim();
	        return htmlStr;  
	   }
	  
	  public static void main(String[] args) {
		  
	}
}