
package com.szit.arbitrate.pushcentre.factory;


import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.szit.arbitrate.pushcentre.factory.thread.PushCentreDisposeThread;
import com.szit.arbitrate.pushcentre.vm.PushTypeEnum;


/**
 * 
* @ClassName: PushMessageDisposeFactory
* @author Administrator
* @date 2017年5月11日 下午5:44:07
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class PushMessageDisposeFactory {
	
	private static ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
	
    /**
     * 
    * @Title: bulidPush 
    * @Description: 构建推送
    * @param @param pusBizTypeEnum
    * @param @param params
    * @return void 
    * @throws
     */
	public final static void bulidPush(PushTypeEnum pushTypeEnum,Map<String,Object> params){
		
		PushCentreDisposeThread pushcentredisposethread = new PushCentreDisposeThread(pushTypeEnum,params);
		cachedThreadPool.execute(pushcentredisposethread);
		
	}
}
