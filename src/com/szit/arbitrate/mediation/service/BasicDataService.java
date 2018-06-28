package com.szit.arbitrate.mediation.service;

import java.util.List;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.AppBaseService;
import com.szit.arbitrate.mediation.entity.BasicData;
import com.szit.arbitrate.mediation.entity.query.BasicDataQuery;

/**
 * 
* @ProjectName:
* @ClassName: BasicDataService
* @Description:基础数据
* @author Administrator
* @date 2017年5月24日 下午4:11:13
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface BasicDataService extends AppBaseService<BasicData, BasicDataQuery>{
	
	/**
	 * 
	* @Title: getBasicDataList  
	* @Description: 获取基础数据列表
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return List<BasicData>    返回类型  
	* @throws
	 */
	public List<BasicData> getBasicDataList() throws BizException,ErrorException;

	public List<String> getTypeClassify();

}
