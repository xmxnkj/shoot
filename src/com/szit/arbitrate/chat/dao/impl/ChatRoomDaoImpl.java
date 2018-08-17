package com.szit.arbitrate.chat.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.hsit.common.dao.QueryParam;
import com.hsit.common.dao.impl.BaseHibernateDaoImpl;
import com.szit.arbitrate.chat.dao.ChatRoomDao;
import com.szit.arbitrate.chat.entity.ChatRoom;
import com.szit.arbitrate.chat.entity.query.ChatRoomQuery;

/**
 * 
* @ProjectName:
* @ClassName: ChatRoomDaoImpl
* @Description:会议室dao接口实现类
* @author Administrator
* @date 2017年4月18日 下午4:28:17
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Repository
public class ChatRoomDaoImpl extends BaseHibernateDaoImpl<ChatRoom, ChatRoomQuery> implements ChatRoomDao{

	@Override
	public List<QueryParam> buildQueryParams(ChatRoomQuery query) {
		List<QueryParam> qps = super.buildQueryParams(query);
		if(query!=null){
			if (!StringUtils.isEmpty(query.getCaseid())) {
				qps.add(new QueryParam("caseid", query.getCaseid()));
			}
		}
		return qps;
	}
	
}
