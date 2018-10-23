/**
 * 
 */
package com.szit.arbitrate.api.news.action;

import org.springframework.stereotype.Controller;

import com.szit.arbitrate.api.base.ApiAction;
import com.szit.arbitrate.api.common.ProjectModuleEnum;
import com.szit.arbitrate.news.entity.NewsHeadInfo;
import com.szit.arbitrate.news.entity.query.NewsHeadInfoQuery;

/**
 * 	@author Administrator
 * 	@date: 	上午9:39:41
 *	@Descript 
 * 	@UpdateUser:
 * 	@UpdateDate:   
 * 	@UpdateRemark:
 * 	@Copyright: 2017 厦门西牛科技有限公司
 * 	@versions:1.0
 *
 */
@Controller("newsModuleAction")
public class NewsModuleAction extends ApiAction<NewsHeadInfo,NewsHeadInfoQuery>{

	@Override
	public void apiInit() {
       this.setModule(ProjectModuleEnum.news);
       this.setApiDeug(true);
	}
	
}
