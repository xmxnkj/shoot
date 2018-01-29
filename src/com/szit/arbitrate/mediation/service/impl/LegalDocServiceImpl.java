package com.szit.arbitrate.mediation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseServiceImpl;
import com.hsit.common.utils.DateUtils;
import com.szit.arbitrate.mediation.dao.LegalDocDao;
import com.szit.arbitrate.mediation.docfactory.DocExecuteReflectFactory;
import com.szit.arbitrate.mediation.docfactory.product.impl.BuildLegalDocProduct;
import com.szit.arbitrate.mediation.entity.LegalDoc;
import com.szit.arbitrate.mediation.entity.LegalDocComments;
import com.szit.arbitrate.mediation.entity.LegalDocDetail;
import com.szit.arbitrate.mediation.entity.enumvo.DocTypeEnum;
import com.szit.arbitrate.mediation.entity.query.LegalDocCommentsQuery;
import com.szit.arbitrate.mediation.entity.query.LegalDocDetailQuery;
import com.szit.arbitrate.mediation.entity.query.LegalDocQuery;
import com.szit.arbitrate.mediation.service.LegalDocCommentsService;
import com.szit.arbitrate.mediation.service.LegalDocDetailService;
import com.szit.arbitrate.mediation.service.LegalDocService;

/**
 * 
* @ProjectName:调解项目app
* @ClassName: LegalDocServiceImpl
* @Description:法律文档业务实现类
* @author Administrator
* @date 2017年3月23日 下午1:51:29
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class LegalDocServiceImpl extends AppBaseServiceImpl<LegalDoc, LegalDocQuery> implements LegalDocService{
	
	@Autowired
	private LegalDocDetailService legalDocDetailService;
	@Autowired
	private LegalDocCommentsService legalDocCommentsService;
	@Autowired
	private LegalDocDao dao;

	public LegalDocDao getDao() {
		return dao;
	}

	public void setDao(LegalDocDao dao) {
		this.dao = dao;
	}
	
	@Override
	public List<LegalDoc> getLegalDocListByDocType(String doctype)
			throws BizException, ErrorException {
		if(StringUtils.isEmpty(doctype)){
			throw new BizException("文档类型不能为空!");
		}
		LegalDocQuery query = new LegalDocQuery();
		query.setDocType(DocTypeEnum.valueOf(doctype));
		return this.getEntities(query);
	}
	
	@Override
	public Map<String, Object> getLegalDocDetailById(String legaldocid)
			throws BizException, ErrorException {
		Map<String, Object> map = Maps.newHashMap();
		if(StringUtils.isEmpty(legaldocid)){
			throw new BizException("文档id不能为空!");
		}
		//1.获取文档实体对象
		LegalDoc entity = this.getById(legaldocid);
		if(entity == null){
			throw new BizException("文档不存在!");
		}
		map.put("LegalDoc", entity);
		
		//2.获取详情列表
		LegalDocCommentsQuery query = new LegalDocCommentsQuery();
		query.setLegalDocId(legaldocid);
		List<LegalDocComments> momentsList = legalDocCommentsService.getEntities(query);
		map.put("momentsList", momentsList);
		
		return map;
	}

	@Override
	public void savaLegalDoc(String docId, String title, String docType,
			String docContent, String publishUnit, String publishTime,Integer orderdisplay)
			throws BizException, ErrorException {
		if(StringUtils.isEmpty(title) || StringUtils.isEmpty(docType) || StringUtils.isEmpty(docContent)
				|| StringUtils.isEmpty(publishUnit) || StringUtils.isEmpty(publishTime)){
			throw new BizException("参数不能为空!");
		}
		//1.获取文档实体对象
		LegalDoc entity = this.getById(docId);
		if(entity == null){
			entity = new LegalDoc();
		}
		entity.setTitle(title);
		entity.setDocType(DocTypeEnum.valueOf(docType));
		entity.setContent(docContent);
		entity.setPublishUnit(publishUnit);
		entity.setPublishTime(DateUtils.parseToDate(publishTime, DateUtils.DATE_YMDHMS_STR));
		entity.setOrderdisplay(orderdisplay);
		this.save(entity);
	}

	@Override
	public void deleteLegalDoc(String ID) {
		
		StringBuffer str = new StringBuffer();
		str.append("DELETE FROM LegalDoc where Id=:Id");
		Map<String,Object> map=new HashMap<>();
		map.put("Id", ID);
		dao.executeUpdateHql(str.toString(), map);
		
	}
	
	@Override
	public void upAndDownShelve(Map<String, Object> params) throws BizException, ErrorException {
		String legaldocid = (String) params.get("legaldocid");
		String oper = (String) params.get("oper");
		LegalDoc entity = this.getById(legaldocid);
		if(entity==null){
			throw new BizException("文档对象不存在");
		}
		if(oper.equals("0")){//0下架操作
			entity.setDisplay(false);
		}else if(oper.equals("1")){//1上架操作,上架同时导出doc文档
			entity.setDisplay(true);
			
			//构造导出doc文档参数
			Map<String, Object> docparams = Maps.newHashMap();
			docparams.put("entity", entity);
			
			//2.获取详情列表
			LegalDocDetailQuery docMomentsQuery = new LegalDocDetailQuery();
			docMomentsQuery.setLegalDocId(legaldocid);
			/*List<LegalDocDetail> detailsList = legalDocDetailService.getEntities(docMomentsQuery);
			docparams.put("list", detailsList);*/
			String template = "legaldoc.ftl";//模板
			docparams.put("template", template);
			docparams.put("filename", legaldocid);//导出的文件名
			String url = "uploads/mediation/legaldoc";
			docparams.put("url", url);
			DocExecuteReflectFactory fac = new DocExecuteReflectFactory(docparams);
			fac.createProduct(BuildLegalDocProduct.class);
		}
		this.save(entity);
	}

}
