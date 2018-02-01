package com.szit.arbitrate.mediation.docfactory.product.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsit.common.utils.DocUtil;
import com.szit.arbitrate.mediation.docfactory.exception.DocBizException;
import com.szit.arbitrate.mediation.docfactory.exception.DocErrorException;
import com.szit.arbitrate.mediation.docfactory.product.IDocDisposeProduct;
import com.szit.arbitrate.mediation.entity.LegalDoc;
import com.szit.arbitrate.mediation.entity.LegalDocDetail;

/**
 *  
* @ProjectName:调解项目app
* @ClassName: BuildLegalDocProduct
* @Description:导出法规文档doc产品类
* @author Administrator
* @date 2017年5月12日 下午3:27:53
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class BuildLegalDocProduct implements IDocDisposeProduct{

	@Override
	public void buildExportDocDispose(Map<String, Object> params)
			throws DocBizException, DocErrorException {
		LegalDoc entity = null;
		String template = "";
		String filename = "";
		String url = "";
		if(params.containsKey("url")){
			url = (String) params.get("url");
		}
		if(params.containsKey("entity")){
			entity = (LegalDoc) params.get("entity");
		}
		if(params.containsKey("template")){
			template = (String) params.get("template");
		}
		if(params.containsKey("filename")){
			filename = (String) params.get("filename");
		}
		//要填入模本的数据文件   
        Map<String, Object> dataMap=new HashMap<String, Object>();   
        dataMap.put("title", entity.getTitle());
        dataMap.put("publishUnit", entity.getPublishUnit());
        dataMap.put("publishTime", entity.getPublishTime());
        dataMap.put("content", entity.getContent());
        /*List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i = 0; i < detailsList.size(); i ++){
        	Map<String,Object> map = new HashMap<String,Object>(); 
        	map.put("content", detailsList.get(i).getContent()); 
        	list.add(map);
        }
        dataMap.put("list", list);*/
        String templateurl = "/com/hsit/common/template/legaldoc";
        DocUtil util = new DocUtil();
        util.createDoc(dataMap, templateurl, template, url, filename);
        
	}

}
