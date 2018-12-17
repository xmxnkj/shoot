package com.szit.arbitrate.api.basisset.action;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.szit.arbitrate.api.base.ApiAction;
import com.szit.arbitrate.api.common.ProjectModuleEnum;

/**
 * 
* @ClassName: BasisSetAppAction
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:43:25
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Controller("basisSetAppAction")
@Scope("prototype")
public class BasisSetAppAction extends ApiAction<DomainEntity, EntityQueryParam>{
	
	@Override
	public void apiInit() {
	  this.setModule(ProjectModuleEnum.basisset);
	  this.setApiDeug(true);
	  
	}
}
