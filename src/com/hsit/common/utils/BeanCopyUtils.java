package com.hsit.common.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

/**
* @ProjectName:cowell
* @ClassName: BeanCopyUtils
* @Description:Bean拷贝工具
* @author 
* @date 
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司.
* @versions:1.0
*/

public class BeanCopyUtils {

	/**
	 * 
	* @Title: BeanUtilsCopy 
	* @Description: Bean拷贝工具
	* @param @param sucObject
	* @param @param outBoClazz
	* @param @return
	* @param @throws Exception
	* @return Object 
	* @throws
	 */
	public static <T> Object beanUtilsCopy(Object sucObject,Class<T> outClazz)throws Exception{
		Object outBo = outClazz.newInstance();
		BeanUtils.copyProperties(sucObject,outBo);
		return outBo;
	}
	
	/**
	 * 
	* @Title: beanUtilsCopy 
	* @Description: TODO
	* @param @param sucObject
	* @param @param outBoClazz
	* @param @param ignoreProperties
	* @param @return
	* @param @throws Exception
	* @return Object 
	* @throws
	 */
	public static <T> Object beanUtilsCopy(Object sucObject,Class<T> outClazz,String[] ignoreProperties)throws Exception{
		Object outBo = outClazz.newInstance();
		BeanUtils.copyProperties(sucObject,outBo,ignoreProperties);
		return outBo;
	}
	
	/**
	 * @throws InstantiationException 
	 * 
	* @Title: formCopyToBoList 
	* @Description: 
	* @param @param poList
	* @param @param boClass
	* @param @return
	* @param @throws IllegalAccessException
	* @param @throws InvocationTargetException
	* @return List<T> 
	* @throws
	 */
	public static <T> List<T> beanUtilsCopyTList(List<? extends Object> poList, Class<T> boClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
		List<T> boList = new ArrayList<T>();
		T boObj = null;
		for (Object poObj : poList) {
			boObj = boClass.newInstance();
			BeanUtils.copyProperties(poObj,boObj);
			boList.add(boObj);
		}
		return boList;
	}
}
