package com.szit.arbitrate.basisset.service;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.BaseService;
import com.szit.arbitrate.basisset.entity.SysParameterTable;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;
import com.szit.arbitrate.basisset.entity.query.SysParameterTableQuery;

/**
 * 
* @ProjectName:arbitrate
* @ClassName: SysParameterTableService
* @Description:基础-系统参数字典业务逻辑接口类
* @author Administrator
* @date 2017年3月20日 下午2:31:29
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public interface SysParameterTableService extends BaseService<SysParameterTable, SysParameterTableQuery> {

	/**
	 * 
	* @Title: getSysParameterTable  
	* @Description: 
	* @param @return
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return SysParameterTable    返回类型  
	* @throws
	 */
	public SysParameterTable getSysParameterTable(ParameterTypeEnum parametertype, String parametercode)throws BizException, ErrorException;
	
	/**
	 * 
	* @Title: certificateSwitch  
	* @Description: 修改参数
	* @param @param parameterid
	* @param @param oper
	* @param @throws BizException
	* @param @throws ErrorException    设定文件  
	* @return void    返回类型  
	* @throws
	 */
	public void certificateSwitch(String parameterid, String oper)throws BizException, ErrorException;
	
}