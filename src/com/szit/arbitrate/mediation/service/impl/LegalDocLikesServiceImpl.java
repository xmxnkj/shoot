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
import com.szit.arbitrate.mediation.dao.LegalDocLikesDao;
import com.szit.arbitrate.mediation.entity.LegalDoc;
import com.szit.arbitrate.mediation.entity.LegalDocLikes;
import com.szit.arbitrate.mediation.entity.query.LegalDocLikesQuery;
import com.szit.arbitrate.mediation.service.LegalDocLikesService;
import com.szit.arbitrate.mediation.service.LegalDocService;

@Service
@Transactional
public class LegalDocLikesServiceImpl extends AppBaseServiceImpl<LegalDocLikes, LegalDocLikesQuery> 
	implements LegalDocLikesService{

	@Autowired
	private LegalDocLikesDao dao;
	@Autowired
	private ClientService clientService;
	@Autowired
	private LegalDocService legalDocService;

	public LegalDocLikesDao getDao() {
		return dao;
	}

	public void setDao(LegalDocLikesDao dao) {
		this.dao = dao;
	}
	
	@Override
	public void addLegalDocLikes(String legaldocid, String likesclientid)
			throws BizException, ErrorException {
		
		if(StringUtils.isEmpty(legaldocid) || StringUtils.isEmpty(likesclientid)){
			throw new BizException("参数不能为空!");
		}
		LegalDocLikesQuery query = new LegalDocLikesQuery();
		query.setLegalDocId(legaldocid);
		query.setLikeClientId(likesclientid);
		LegalDocLikes entity = this.getEntity(query);
		if(entity != null){
			throw new BizException("你已经点赞过该案例!");
		}
		Client client = clientService.getById(likesclientid);
		
		entity = new LegalDocLikes();
		entity.setLegalDocId(legaldocid);
		entity.setLikeClientId(likesclientid);
		entity.setLikeClientImage(client.getIdentifyName());
		entity.setLikeClientImage(client.getHeadImgFile());
		entity.setLikeTime(new Date());
		this.save(entity);
		
		LegalDoc legalDoc = legalDocService.getById(legaldocid);
		if(legalDoc == null){
			throw new BizException("找不到该案例!");
		}
		legalDoc.setLikeCounts(legalDoc.getLikeCounts()==null?0:legalDoc.getLikeCounts()+1);
		legalDocService.save(legalDoc);
		
	}
	
	@Override
	public void cancelLegalDocLikes(String legaldocid, String likesclientid)
			throws BizException, ErrorException {
		if(StringUtils.isEmpty(legaldocid) || StringUtils.isEmpty(likesclientid)){
			throw new BizException("参数不能为空!");
		}
		LegalDocLikesQuery query = new LegalDocLikesQuery();
		query.setLegalDocId(legaldocid);
		query.setLikeClientId(likesclientid);
		LegalDocLikes entity = this.getEntity(query);
		if(entity==null){
			throw new BizException("你没有点赞该案例,无法取消!");
		}
		this.deleteById(entity.getId());
		
		LegalDoc legalDoc = legalDocService.getById(legaldocid);
		if(legalDoc == null){
			throw new BizException("找不到该案例!");
		}
		legalDoc.setLikeCounts(legalDoc.getLikeCounts()<=0?0:legalDoc.getLikeCounts()-1);
		legalDocService.save(legalDoc);
	}
	
}
