package com.szit.arbitrate.client.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.hsit.common.utils.JsonMapper;
import com.szit.arbitrate.client.dao.NeteaseClientDao;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.NeteaseClient;
import com.szit.arbitrate.client.entity.query.NeteaseClientQuery;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.NeteaseClientService;
import com.szit.neteasecloud.config.NeteaseCloudConfig;
import com.szit.neteasecloud.utils.CheckSumBuilder;

/**
 * 
* @ClassName: NeteaseClientServiceImpl  
* @Description:网易云信账号业务实现类   
* @author   
* @date 2017年6月2日 上午10:09:48  
* @Copyright
* @versions:1.0 
*
 */
@Service
@Transactional
public class NeteaseClientServiceImpl extends BaseServiceImpl<NeteaseClient, NeteaseClientQuery> implements NeteaseClientService{

	
	@Autowired
	private ClientService clientService;
	@Autowired
	private NeteaseClientDao dao;

	public NeteaseClientDao getDao() {
		return dao;
	}

	public void setDao(NeteaseClientDao dao) {
		this.dao = dao;
	}
	
	@Override
	public NeteaseClient createNeteaseCloudAccount(String clientid) throws BizException,ErrorException{
		
		
		Client client = clientService.getById(clientid);
		if(client == null){
			throw new BizException("用户不存在,请检查!");
		}
		/*if(StringUtils.isNotEmpty(client.getNeteaseClientId())){
			String neteaseClientId = client.getNeteaseClientId();
			NeteaseClient entity = this.getById(neteaseClientId);
			return entity;
		}*/
		NeteaseClientQuery clientQuery = new NeteaseClientQuery();
		clientQuery.setClientId(clientid);
		NeteaseClient temp = getEntity(clientQuery);
		if(temp!=null){
			return temp;
		}
		
		CloseableHttpClient httpclient = HttpClients.createDefault();
        String url = "https://api.netease.im/nimserver/user/create.action";
        HttpPost httpPost = new HttpPost(url);

        String appKey = NeteaseCloudConfig.appKey;
        String appSecret = NeteaseCloudConfig.appSecret;
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        String strRandom = CheckSumBuilder.buildRandom(4) + "";
        String nonce = curTime.substring(8, curTime.length())+strRandom;
        String checkSum = CheckSumBuilder.getCheckSum(appSecret, nonce ,curTime);//参考 计算CheckSum的java代码

        // 设置请求的header
        httpPost.addHeader("AppKey", appKey);
        httpPost.addHeader("Nonce", nonce);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        /*nvps.add(new BasicNameValuePair("accid", client.getAccount()+"_"+client.getClientType().toString()));
        nvps.add(new BasicNameValuePair("name", client.getIdentifyName()));*/
        nvps.add(new BasicNameValuePair("accid", client.getAccount()+"_"+client.getClientType().toString()+"_"));
        nvps.add(new BasicNameValuePair("name", client.getIdentifyName()+"海"));
        try {
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));

			// 执行请求
			CloseableHttpResponse response = httpclient.execute(httpPost);
			response.close();

			// 打印执行结果
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			Map<String, String> resultMap = JsonMapper.getInstance().fromJson(result);
			
			String code = resultMap.get("code");
			String info = resultMap.get("info");
			if(code.equals("200")){
				Map<String, String> netease = JsonMapper.getInstance().fromJson(info);
				String token = netease.get("token");
				String accid = netease.get("accid");
				String name = netease.get("name");
				NeteaseClient neteaseClient = new NeteaseClient();
				neteaseClient.setClientId(clientid);
				neteaseClient.setAccid(accid);
				neteaseClient.setName(name);
				neteaseClient.setToken(token);
				String neteaseClientId = this.save(neteaseClient);
				client.setNeteaseClientId(neteaseClientId);
				clientService.save(client);
				
				return neteaseClient;
			}else{
				throw new BizException("创建失败!");
			}
			
			
		} catch (ParseException | IOException | JSONException e) {
			throw new BizException(e);
		}
		
	}
	
	@Override
	public NeteaseClient GetNeteaseCloudAccount(String clientid)
			throws BizException, ErrorException {
		Client client = clientService.getById(clientid);
		if(client == null){
			throw new BizException("用户不存在,请检查!");
		}
		if(StringUtils.isEmpty(client.getNeteaseClientId())){
			throw new BizException("用户还没有网易云信账号!");
		}
		String neteaseClientId = client.getNeteaseClientId();
		NeteaseClient entity = this.getById(neteaseClientId);
		return entity;
	}
	
}
