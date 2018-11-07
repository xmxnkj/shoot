package com.szit.arbitrate.api.common.utils;

import java.io.File;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.util.EntityUtils;



public class HttpClientUpload{
	/**
	 *使用httpclient模拟form表单上传文件
	 * @param url 文件上传的目标地址
	 * @param filepath 要上传的文件路径
	 * @param mapParams 文字参数（采用键值对应）
	 */
	public static void upload(String url, String filepath,HashMap<String, String> mapParams) {
		HttpClient client = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION,HttpVersion.HTTP_1_1);
		client.getParams().setParameter(CoreProtocolPNames.HTTP_CONTENT_CHARSET, "utf-8");
		try {
			MultipartEntity entity = new MultipartEntity();//多个表单对象
			ContentBody fileBody = new FileBody(new File(filepath)); //表单文件域
			entity.addPart("upload", fileBody);
		    entity.addPart("path", new StringBody(mapParams.get("savePath")));	// 字符参数部分
			httpPost.setEntity(entity);
			HttpResponse response = client.execute(httpPost);//执行post操作，并返回response
			String jsonData = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println(jsonData);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String url ="http://localhost:8080/GoApp/uploadPro.action";
		String filepath="C:\\Users\\Administrator\\Desktop\\test.mkv";
		HashMap<String, String> mapParams = new HashMap<String, String>();
		mapParams.put("savePath", "\\gaopenghui");
		upload(url,filepath,mapParams);
	}
}