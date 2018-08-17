package com.szit.arbitrate.chat.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.QueryParam.ParamCompareType;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.chat.dao.ChatRoomClientDao;
import com.szit.arbitrate.chat.entity.ChatRoomClient;
import com.szit.arbitrate.chat.entity.query.ChatRoomClientQuery;

/**
 * 
* @ProjectName:
* @ClassName: ChatRoomClientDaoImpl
* @Description:聊天室-用户关联dao接口实现类
* @author Administrator
* @date 2017年4月20日 下午4:38:07
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class ChatRoomClientDaoImpl extends BaseHibernateDaoImpl<ChatRoomClient, ChatRoomClientQuery>
	implements ChatRoomClientDao{

	@Override
	public List<QueryParam> buildQueryParams(ChatRoomClientQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getCaseid())) {
				qps.add(new QueryParam("caseid", query.getCaseid()));
			}
			if (!StringUtils.isEmpty(query.getChatRoomId())) {
				qps.add(new QueryParam("chatRoomId", query.getChatRoomId()));
			}
			if (!StringUtils.isEmpty(query.getClientId())) {
				if(query.isEqual()){
					qps.add(new QueryParam("clientId", query.getClientId()));
				}else{
					qps.add(new QueryParam("clientId", query.getClientId(), ParamCompareType.NotEqual));
				}
			}
		}
		return qps;
	}
	
}
