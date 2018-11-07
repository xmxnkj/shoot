/**   
* @Title: PageUtils.java
* @Package com.szit.cowell.api.common
* @Description: TODO
* @author XUJC
* @date 2017年10月30日 下午5:41:33
* @version V1.0   
*/


package com.szit.arbitrate.api.common.utils;



import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.kfbase.entity.Paging;

/**
 * 
* @ProjectName:
* @ClassName: PageUtils
* @Description:分页工具类
* @author Administrator
* @date 2017年3月20日 下午2:52:16
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public class PageUtils {

    
	public static <Q extends EntityQueryParam> Q setPages(int rows,int page,Class<Q> clazz){
		try {
			Q q = clazz.newInstance();
			if(q.getPaging()==null){
				q.setPaging(new Paging());
			}
			q.getPaging().setPageSize(rows);
			q.getPaging().setPage(page);
			return q;
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static int PAGE_SIZE = 10;
	
}
