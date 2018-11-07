/**   
* @Title: ApiFormTools.java
* @Package com.szit.cowell.api.common
* @Description: TODO
* @author XUJC
* @date 2017年10月29日 上午9:23:00
* @version V1.0   
*/


package com.szit.arbitrate.api.common;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.databind.JavaType;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.utils.JsonMapper;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;

/**
 * @ProjectName:cowell
 * @ClassName: ApiFormTools
 * @Description:Api业务对象转换工具
 * @author XUJC
 * @date 2017年10月29日 上午9:23:00
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */

public class ApiBoFormTools {
	
	private static Logger logger = LoggerFactory.getLogger(ApiBoFormTools.class);
	private static JsonMapper jsonMapper = JsonMapper.getInstance();
	
	private static JsonMapper jsonNonEmptyMapper = JsonMapper.getInstance();
	
	/**
	 * 
	* @Title: formInBo 
	* @Description: Json转换业务对象
	* @param @param inboJson
	* @param @param clazz
	* @param @return
	* @param @throws BaseBizException
	* @return T 
	* @throws
	 */
	public static <T> T formInBo(String inboJson,Class<T> clazz)throws BizException{
		logger.debug("inboJson:{}",inboJson);
		T t = jsonNonEmptyMapper.fromJson(inboJson,clazz);
		if(t==null){
			throw new BizException("亲!传入业务参(inbo)不符合规则标准!(请核实数据类型是否正确)");
		}
		return t;
	}
	
	/**
	 * 
	* @Title: formInBo 
	* @Description: 转换对象输入业务对象
	* @param @param inVm
	* @param @param clazz
	* @param @return
	* @return T 
	* @throws
	 */
	public static <T> T formInBo(ApiInParamsVm inVm,Class<T> clazz)throws BizException{
		logger.debug("inbo:{}",inVm.getDatasource());
		T t = jsonNonEmptyMapper.fromJson(inVm.getDatasource(),clazz);
		if(t==null){
			throw new BizException("亲!传入业务参(inbo)不符合规则标准!(请核实数据类型是否正确)");
		}
		return t;
	}
	
	/**
	 * 
	* @Title: formInBoToMap 
	* @Description: 转换Bo对象为Map
	* @param @param inVm
	* @param @return
	* @return Map<String,Object> 
	* @throws
	 */
	public static Map<String,Object> formInBoToMap(ApiInParamsVm inVm){
		JavaType javaType = jsonMapper.contructMapType(Map.class,String.class,Object.class);
		return jsonMapper.fromJson(inVm.getDatasource(),javaType);
	}
	
	/**
	 * 
	* @Title: formOutBo 
	* @Description: 输出业务对象
	* @param @param sucObject 源对象
	* @param @param outClazz 输出业务BO类
	* @param @throws Exception
	* @return void 
	* @throws
	 */
	public static <T> String formCopyOutToBoJson(Object sucObject,Class<T> outBoClazz,String[] ignoreProperties)throws Exception{
		Object outObj = outBoClazz.newInstance();
		BeanUtils.copyProperties(sucObject,outObj,ignoreProperties);
		return jsonMapper.toJson(outObj);
	}
	
	public static <T> String formCopyOutToBoJson(Object sucObject,Class<T> outBoClazz)throws Exception{
		Object outObj = outBoClazz.newInstance();
		BeanUtils.copyProperties(sucObject,outObj);
		return jsonMapper.toJson(outObj);
	}
	
	public static <T> Object formCopyOutToBo(Object sucObject,Class<T> outBoClazz)throws Exception{
		Object outBo = outBoClazz.newInstance();
		BeanUtils.copyProperties(sucObject,outBo);
		return outBo;
	}
	
	public static <T> Object formCopyOutToBo(Object sucObject,Class<T> outBoClazz,String[] ignoreProperties)throws Exception{
		Object outBo = outBoClazz.newInstance();
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
	public static <T> List<T> formCopyToBoList(List<? extends Object> poList, Class<T> boClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
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
