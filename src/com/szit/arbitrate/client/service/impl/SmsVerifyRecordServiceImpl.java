package com.szit.arbitrate.client.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.hsit.common.utils.sms.SMSSendUtil;
import com.szit.arbitrate.client.dao.SmsVerifyRecordDao;
import com.szit.arbitrate.client.entity.SmsVerifyRecord;
import com.szit.arbitrate.client.entity.enumvo.SmsBizModelEnum;
import com.szit.arbitrate.client.entity.query.SmsVerifyRecordQuery;
import com.szit.arbitrate.client.service.SmsVerifyRecordService;

@Service
@Transactional
public class SmsVerifyRecordServiceImpl extends BaseServiceImpl<SmsVerifyRecord, SmsVerifyRecordQuery>
	implements SmsVerifyRecordService{

	@Autowired
	private SmsVerifyRecordDao dao;

	public SmsVerifyRecordDao getDao() {
		return dao;
	}

	public void setDao(SmsVerifyRecordDao dao) {
		this.dao = dao;
	}
	/**
	 * phone 电话
	 * smsBizModelEnum 短信属性
	 * templateid 模板id
	 * params 参数
	 */
	@Override
	public void sendSmsMessage(String phone, SmsBizModelEnum smsBizModelEnum, String templateid, String params)
			throws BizException, ErrorException {
		String url = "https://api.netease.im/sms/sendtemplate.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("mobiles", phone));
        nvps.add(new BasicNameValuePair("templateid", templateid));
        nvps.add(new BasicNameValuePair("params", params));
		Map<String,String> smsResultMap = SMSSendUtil.getInstance().sendSmsMessage(url, nvps);
		if(!smsResultMap.get("code").equals("200")){
			SmsVerifyRecord smsverifyrecord = new SmsVerifyRecord();
			smsverifyrecord.setBizmodel(smsBizModelEnum);
			smsverifyrecord.setRecphone(phone);
			smsverifyrecord.setSendcontent(smsResultMap.get("msg"));
			smsverifyrecord.setSenddatetiem(new Date());
			smsverifyrecord.setSendstate(false);
			smsverifyrecord.setSmsapiresult(smsResultMap.get("code"));
			smsverifyrecord.setSmscheckcode(smsResultMap.get("obj"));
			this.save(smsverifyrecord);
			throw new BizException("短信发送失败,错误码:"+smsResultMap.get("code"));
		}else{
			SmsVerifyRecord smsverifyrecord = new SmsVerifyRecord();
			smsverifyrecord.setBizmodel(smsBizModelEnum);
			smsverifyrecord.setSendcontent(smsResultMap.get("msg"));
			smsverifyrecord.setRecphone(phone);
			smsverifyrecord.setSenddatetiem(new Date());
			smsverifyrecord.setSendstate(true);
			smsverifyrecord.setSmsapiresult(smsResultMap.get("msg"));
			this.save(smsverifyrecord);
		}
	}

	@Override
	public Map<String, Object> toGetSmsCode(String phone,
			SmsBizModelEnum smsBizModelEnum) throws BizException,
			ErrorException {
		String url = "https://api.netease.im/sms/sendcode.action";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("mobile", phone));
		Map<String,String> smsResultMap = SMSSendUtil.getInstance().sendSmsMessage(url, nvps);
		if(!smsResultMap.get("code").equals("200")){
			SmsVerifyRecord smsverifyrecord = new SmsVerifyRecord();
			smsverifyrecord.setBizmodel(smsBizModelEnum);
			smsverifyrecord.setRecphone(phone);
			smsverifyrecord.setSendcontent(smsResultMap.get("msg"));
			smsverifyrecord.setSenddatetiem(new Date());
			smsverifyrecord.setSendstate(false);
			smsverifyrecord.setSmsapiresult(smsResultMap.get("code"));
			smsverifyrecord.setSmscheckcode(smsResultMap.get("obj"));
			this.save(smsverifyrecord);
			throw new BizException("短信发送失败,错误码:"+smsResultMap.get("code"));
		}else{
			Map<String,Object> resultMap = Maps.newHashMap();
			SmsVerifyRecord smsverifyrecord = new SmsVerifyRecord();
			smsverifyrecord.setBizmodel(smsBizModelEnum);
			smsverifyrecord.setSendcontent(smsResultMap.get("msg"));
			smsverifyrecord.setRecphone(phone);
			smsverifyrecord.setSenddatetiem(new Date());
			smsverifyrecord.setSendstate(true);
			smsverifyrecord.setSmsapiresult(smsResultMap.get("msg"));
			smsverifyrecord.setSmscheckcode(smsResultMap.get("obj"));
			this.save(smsverifyrecord);
			resultMap.put("smscode", smsResultMap.get("obj"));
			return resultMap;
		}
	}

	@Override
	public Boolean verifySmsCode(String phone, String smscode)
			throws BizException, ErrorException {
		
		String url = "https://api.netease.im/sms/verifycode.action";
		 // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("mobile", phone));
        nvps.add(new BasicNameValuePair("code", smscode));
        
		Map<String,String> smsResultMap = SMSSendUtil.getInstance().sendSmsMessage(url, nvps);
		
        if(smsResultMap.get("code").equals("200")){
        	return true;
        }else{
        	return false;
        }
	}
	
	
}
