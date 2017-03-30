package com.hsit.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
* @ClassName: SpringBeanUtil
* @Description:Spring 全局工具类
* @author Administrator
* @date 2017年3月29日 下午4:21:04
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class SpringBeanUtil implements ApplicationContextAware{
	private static ApplicationContext appContext;
	public void setApplicationContext(ApplicationContext applicationContext)
	throws BeansException {
		appContext = applicationContext;
	}
	public static Object getBean(String beanId)
	{
		//String[] beans = appContext.getBeanDefinitionNames();
		return appContext.getBean(beanId);
	}
	public static ApplicationContext getAppContext() {
		return appContext;
	}
	public static void setAppContext(ApplicationContext appContext) {
		SpringBeanUtil.appContext = appContext;
	}
}
