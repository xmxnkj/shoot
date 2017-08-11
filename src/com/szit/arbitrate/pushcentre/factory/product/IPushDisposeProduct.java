
package com.szit.arbitrate.pushcentre.factory.product;

import java.util.Map;

import com.szit.arbitrate.pushcentre.exception.PushBizExcetion;
import com.szit.arbitrate.pushcentre.exception.PushErrorException;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;


/**
 * 
* @ClassName: IPushDisposeProduct
* @author Administrator
* @date 2017年5月11日 下午5:45:49
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public interface IPushDisposeProduct {
    /**
     * 
    * @Title: buildPushDispose 
    * @Description: 构建推送处理
    * @param @param params
    * @param @return
    * @param @throws PushBizExcetion
    * @param @throws PushErrorException
    * @return Map<String,Object> 
    * @throws
     */
	public void buildPushDispose(Map<String,Object> params,PushTypeEnum pushTypeEnum)throws PushBizExcetion,PushErrorException;	
}