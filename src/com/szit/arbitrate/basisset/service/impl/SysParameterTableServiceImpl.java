package com.szit.arbitrate.basisset.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.service.impl.BaseServiceImpl;
import com.szit.arbitrate.basisset.dao.SysParameterTableDao;
import com.szit.arbitrate.basisset.entity.SysParameterTable;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;
import com.szit.arbitrate.basisset.entity.query.SysParameterTableQuery;
import com.szit.arbitrate.basisset.service.SysParameterTableService;
/**
 * 
* @ProjectName:arbitrate
* @ClassName: SysParameterTableServiceImpl
* @Description:基础-系统参数字典业务逻辑实现类
* @author Administrator
* @date 2017年3月20日 下午2:31:54
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Service
@Transactional
public class SysParameterTableServiceImpl extends BaseServiceImpl<SysParameterTable,SysParameterTableQuery>
		implements SysParameterTableService {
	
	@Autowired
	private SysParameterTableDao dao;

	
	public SysParameterTableDao getDao() {
		return dao;
	}
	public void setDao(SysParameterTableDao dao) {
		this.dao = dao;
	}
	
	@Override
	public SysParameterTable getSysParameterTable(ParameterTypeEnum parametertype,
			String parametercode) throws BizException, ErrorException {
		SysParameterTableQuery sysParameterTableQuery = new SysParameterTableQuery();
		sysParameterTableQuery.setParametertype(parametertype);
		sysParameterTableQuery.setParametercode(parametercode);
		SysParameterTable entity = this.getEntity(sysParameterTableQuery);
		return entity;
	}
	@Override
	public void certificateSwitch(String parameterid, String oper)
			throws BizException, ErrorException {
		SysParameterTable entity = this.getById(parameterid);
		if(entity == null){
			throw new BizException("找不到参数");
		}
		if(oper.equals("0")){//关闭验证
			if(entity.getParameterinitvla().equals("0")){
				throw new BizException("已经是关闭中");
			}
			entity.setParameterinitvla("0");
		}else if(oper.equals("1")){//开启验证
			if(entity.getParameterinitvla().equals("1")){
				throw new BizException("已经是关闭中");
			}
			entity.setParameterinitvla("1");
		}
		this.save(entity);
	}
	
	
}