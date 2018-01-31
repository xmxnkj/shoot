package com.szit.arbitrate.mediation.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.arbitrate.client.entity.Client;
import com.szit.arbitrate.client.service.ClientService;
import com.szit.arbitrate.mediation.dao.LegalDocCommentsDao;
import com.szit.arbitrate.mediation.entity.LegalDoc;
import com.szit.arbitrate.mediation.entity.LegalDocComments;
import com.szit.arbitrate.mediation.entity.query.LegalDocCommentsQuery;
import com.szit.arbitrate.mediation.service.LegalDocCommentsService;
import com.szit.arbitrate.mediation.service.LegalDocService;

@Service
@Transactional
public class LegalDocCommentsServiceImpl extends AppBaseServiceImpl<LegalDocComments, LegalDocCommentsQuery> 
	implements LegalDocCommentsService{

	@Autowired
	private LegalDocCommentsDao dao;
	@Autowired
	private ClientService clientService;
	@Autowired
	private LegalDocService legalDocService;

	public LegalDocCommentsDao getDao() {
		return dao;
	}

	public void setDao(LegalDocCommentsDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void addLegalDocComments(String legaldocid, String commentsclientid,
			String commentscontent) throws BizException, ErrorException {
		if(StringUtils.isEmpty(legaldocid) || StringUtils.isEmpty(commentsclientid) 
			|| StringUtils.isEmpty(commentscontent)){
			throw new BizException("参数不能为空!");
		}
		Client client = clientService.getById(commentsclientid);
		LegalDoc legalDoc = legalDocService.getById(legaldocid);
		if(legalDoc == null){
			throw new BizException("找不到该案例!");
		}
		
		//1.新增评论实体
		LegalDocComments entity = new LegalDocComments();
		entity.setAudit(false);
		entity.setCommentsClientId(commentsclientid);
		entity.setCommentsClientName(client.getIdentifyName());
		entity.setCommentsClientImage(client.getHeadImgFile());
		entity.setCommentsContent(commentscontent);
		entity.setCommentsTime(new Date());
		entity.setLegalDocId(legaldocid);
		this.save(entity);
		
		//2.案例评论数+1
		legalDoc.setCommentCounts(legalDoc.getCommentCounts()==null?0:legalDoc.getCommentCounts()+1);
		legalDocService.save(legalDoc);
		
	}
	
	@Override
	public void auditComments(String commentsid) throws BizException,
			ErrorException {
		LegalDocComments entity = this.getById(commentsid);
		if(entity == null){
			throw new BizException("找不到该评论!");
		}
		if(entity.isAudit()){
			throw new BizException("已审核过，不用再审核!");
		}
		entity.setAudit(true);
		this.save(entity);
	}
	
}
