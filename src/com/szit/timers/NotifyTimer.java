package com.szit.timers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hsit.common.utils.DateUtils;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.entity.ClientNotOnlineNotify;
import com.szit.arbitrate.client.entity.ClientToken;
import com.szit.arbitrate.client.entity.query.ClientNotOnlineNotifyQuery;
import com.szit.arbitrate.client.service.ClientNotOnlineNotifyService;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.client.service.ClientTokenService;
import com.szit.arbitrate.client.service.SmsVerifyRecordService;
import com.szit.arbitrate.pushcentre.service.PushCentreService;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;

@Component("notifyTimer") 
public class NotifyTimer {
	
	@Autowired
	private ClientTokenService clientTokenService;
	@Autowired
	private ClientNotOnlineNotifyService clientNotOnlineNotifyService;
	@Autowired
	private PushCentreService pushCentreService;
	@Autowired
	private SmsVerifyRecordService smsVerifyRecordService;
	@Autowired
	private ClientService clientService;
	
	public void doJob(){
		
		ClientNotOnlineNotifyQuery query = new ClientNotOnlineNotifyQuery();
		List<ClientNotOnlineNotify> list = clientNotOnlineNotifyService.getEntities(query);
		if(list != null && list.size() > 0){
			Date nowTime = new Date();
			for(int i = 0; i < list.size(); i ++){
				ClientNotOnlineNotify entity = list.get(i);
				String caseExplain = entity.getCaseExplain();
				String clientId = entity.getClientId();
				String alertMessage = "关于:"+caseExplain+",被申述人:"+entity.getClientName()
						+" 未回复已超过7天,请及时处理.";
				ClientToken clientToken = clientTokenService.getClientTokenByClientId(clientId);
				
				/*String templateid = "3053495";//网易短信模板id
				JSONArray  paramsarry = new JSONArray();
				paramsarry.put(entity.getCaseExplain());
				paramsarry.put(entity.getClientName());
				
				JSONArray  phonearry = new JSONArray();
				phonearry.put(mediatorClient.getTel());*/
				
				if(clientToken == null){
					//空，说明一直没有登录,判断当前时间是否超过召集时间7天
					Date endTime = DateUtils.addDate(entity.getGatherTime(), 7);
					if(nowTime.getTime() > endTime.getTime()){
						pushCentreService.pushUnifyMessage(PushTypeEnum.SevenDayNotReply, "", false, entity.getMediatorClientId(),
								"", alertMessage, null, 1);
						//通知完之后删除
						clientNotOnlineNotifyService.deleteById(entity.getId());
						
						//6.发送短信
						//smsVerifyRecordService.sendSmsMessage(phonearry.toString(), SmsBizModelEnum.sms, templateid, paramsarry.toString());
						
					}
				}else{
					//有登录，再判断当前时间是否超过最近登录时间7天
					Date lastLoginTime = clientToken.getLoginTime();
					Date endTime = DateUtils.addDate(lastLoginTime, 7);
					if(nowTime.getTime() > endTime.getTime()){//大于7天，通知并删除
						pushCentreService.pushUnifyMessage(PushTypeEnum.SevenDayNotReply, "", false, entity.getMediatorClientId(),
								"", alertMessage, null, 1);
						//通知完之后删除
					}
					clientNotOnlineNotifyService.deleteById(entity.getId());
					//6.发送短信
					//smsVerifyRecordService.sendSmsMessage(phonearry.toString(), SmsBizModelEnum.sms, templateid, paramsarry.toString());
				}
			}
		}
		
	}

}
