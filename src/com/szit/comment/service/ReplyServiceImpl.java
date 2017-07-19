package com.szit.comment.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.comment.dao.ReplyDao;
import com.szit.comment.entity.Reply;
import com.szit.comment.entity.query.ReplyQuery;

@Service
public class ReplyServiceImpl extends AppBaseServiceImpl<Reply, ReplyQuery> implements ReplyService {
	@Autowired
	private ReplyDao dao;

	@Override
	public ReplyDao getDao() {
		return dao;
	}
	
	/**
	 * 查询某个报料的回复
	 * @param reportId
	 * @return
	 */
	public List<Reply> getReplies(String reportId){
		if (!StringUtils.isEmpty(reportId)) {
			ReplyQuery query = new ReplyQuery();
			query.setReportId(reportId);
			return getEntities(query);
		}
		
		return null;
	}
}
