package com.szit.arbitrate.mediation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.service.AppBaseServiceImpl;
import com.szit.arbitrate.mediation.dao.LegalDocDetailDao;
import com.szit.arbitrate.mediation.entity.LegalDocDetail;
import com.szit.arbitrate.mediation.entity.query.LegalDocDetailQuery;
import com.szit.arbitrate.mediation.service.LegalDocDetailService;
import com.szit.arbitrate.mediation.service.LegalDocService;

/**
 * 
* @ProjectName:
* @ClassName: LegalDocMomentsServiceImpl
* @Description:法规文档详情业务实现类
* @author Administrator
* @date 2017年3月28日 下午4:07:13
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class LegalDocDetailServiceImpl extends AppBaseServiceImpl<LegalDocDetail, LegalDocDetailQuery>
	implements LegalDocDetailService{

	@Autowired
	private LegalDocService legalDocService;
	@Autowired
	private LegalDocDetailDao dao;

	public LegalDocDetailDao getDao() {
		return dao;
	}

	public void setDao(LegalDocDetailDao dao) {
		this.dao = dao;
	}
	
	/*@Override
	public void addLegalDocMoments(String clientid, String legaldocid,
			String momentscontent, String ip) throws BizException,
			ErrorException {
		if(StringUtils.isEmpty(legaldocid) || StringUtils.isEmpty(clientid) 
				|| StringUtils.isEmpty(ip) || StringUtils.isEmpty(momentscontent)){
			throw new BizException("参数不能为空!");
		}
		//1.添加评论实体
		LegalDocMoments entity = new LegalDocMoments();
		entity.setCommentClientId(clientid);
		entity.setCommentip(ip);
		entity.setLegalDocId(legaldocid);
		entity.setCommentcontent(momentscontent);
		entity.setCommentdatetime(new Date());
		this.save(entity);
		
		//2.给文档评论数+1
		LegalDoc legalDoc = legalDocService.getById(legaldocid);
		if(legalDoc == null){
			throw new BizException("文档不存在!");
		}
		legalDoc.setCommentNums(legalDoc.getCommentNums()+1);
		legalDocService.save(legalDoc);
	}

	@Override
	public Map<String,Object> getLegalDocMomentsList(String legaldocid,int pageNum) throws BizException,
			ErrorException {
		Map<String,Object> resultMap = new HashMap<String,Object>();
		Paging paging = new Paging();
		paging.setPage(pageNum);
		paging.setPageSize(PagingBean.DEFAULT_PAGE_SIZE);
		LegalDocMomentsQuery legalDocMomentsQuery = new LegalDocMomentsQuery();
		legalDocMomentsQuery.setAudit(true);
		legalDocMomentsQuery.setAudit(true);//已审核
		List<LegalDocMoments> list = this.getEntities(legalDocMomentsQuery);
		resultMap.put("rows", list);
		resultMap.put("total", paging.getRecordCount());
		resultMap.put("pageCount", paging.getPageCount());
		return resultMap;
	}*/
	
}
