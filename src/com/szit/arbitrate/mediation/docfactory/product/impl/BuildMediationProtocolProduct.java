package com.szit.arbitrate.mediation.docfactory.product.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsit.common.utils.DocUtil;
import com.szit.arbitrate.client.entity.TempClient;
import com.szit.arbitrate.mediation.docfactory.exception.DocBizException;
import com.szit.arbitrate.mediation.docfactory.exception.DocErrorException;
import com.szit.arbitrate.mediation.docfactory.product.IDocDisposeProduct;
import com.szit.arbitrate.mediation.entity.MediationProtocol;

/**
 * 
* @ProjectName:
* @ClassName: BuildMediationProtocolProduct
* @Description:导出协议书doc产品类
* @author Administrator
* @date 2017年5月12日 下午3:37:18
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class BuildMediationProtocolProduct implements IDocDisposeProduct{
	
	
	@Override
	public void buildExportDocDispose(Map<String, Object> params)
			throws DocBizException, DocErrorException {
		
		MediationProtocol entity = null;
		List<TempClient> tempClientList = null;
		String template = "";
		String filename = "";
		String url = "";
		if(params.containsKey("entity")){
			entity = (MediationProtocol) params.get("entity");
		}
		if(params.containsKey("url")){
			url = (String) params.get("url");
		}
		if(params.containsKey("list")){
			tempClientList = (List<TempClient>) params.get("list");
		}
		if(params.containsKey("template")){
			template = (String) params.get("template");
		}
		if(params.containsKey("filename")){
			filename = (String) params.get("filename");
		}
		Map<String, Object> dataMap=new HashMap<String, Object>();   
        dataMap.put("serialNumber", entity.getSerialNumber());
        dataMap.put("name", entity.getIdentifyName());
        dataMap.put("externalDesc", entity.getExternalDesc());
        if(entity.isGender()){
        	dataMap.put("gender", "男");
        }else{
        	dataMap.put("gender", "女");
        }
        dataMap.put("nation", entity.getNation());
        dataMap.put("birth", entity.getBirth()==null?"":entity.getBirth());
        dataMap.put("profession", entity.getProfession());
        dataMap.put("tel", entity.getTel());
        dataMap.put("address", entity.getAddress());
        dataMap.put("identify", entity.getIdentify());
        dataMap.put("controversy", entity.getControversy());
        dataMap.put("execute", entity.getExecute());
        dataMap.put("content", entity.getContent());
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        for(int i = 0; i < tempClientList.size(); i ++){
        	TempClient tempClient = tempClientList.get(i);
        	Map<String,Object> map = new HashMap<String,Object>(); 
        	map.put("name", tempClient.getIdentifyName()); 
        	map.put("identify", tempClient.getIdentify()); 
        	map.put("tel", tempClient.getTel()); 
        	map.put("address", tempClient.getAddress()); 
        	map.put("nation", tempClient.getNation());
        	map.put("birth", tempClient.getBirth()==null?"":tempClient.getBirth());
        	map.put("profession", tempClient.getProfession());
        	if(tempClient.isGender()){
        		map.put("gender", "男");
            }else{
            	map.put("gender", "女");
            }
        	list.add(map);
        }
        dataMap.put("list", list);
        
        String templateurl = "/com/hsit/common/template";
        DocUtil util = new DocUtil();
        util.createDoc(dataMap, templateurl, template, url, filename);
	}

}
