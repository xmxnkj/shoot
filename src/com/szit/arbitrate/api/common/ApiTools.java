/**   
* @Title: ApiTools.java
* @Package com.szit.cowell.common.api
* @Description: TODO
* @author XUJC
* @date 2017年10月26日 上午10:48:56
* @version V1.0   
*/


package com.szit.arbitrate.api.common;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.utils.JsonMapper;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;


/**
 * @ProjectName:xmszit-cowell-module-common
 * @ClassName: ApiTools
 * @Description:Api 操作工具类
 * @author XUJC
 * @date 2017年10月26日 上午10:48:56
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */

public class ApiTools {

	
	
	//模块枚举
	public enum MODULECODE {
		client,chat,mediation,news,basisset
	}
	
	public final static String GETDATASUCCEED="获取数据成功";
	
	private Logger logger = LoggerFactory.getLogger(ApiTools.class);
	
	private static JsonMapper jsonMapper = JsonMapper.getInstance();
	
	
	/**
	 * 
	* @Title: bulidInparam 
	* @Description: 构建输入参数
	* @param @param module
	* @param @param biztype
	* @param @param datasource
	* @param @return
	* @return ApiInParamsVm 
	* @throws
	 */
	public static ApiInParamsVm bulidInparam(MODULECODE module,String bizcode,String bizmethod,String datasource ){
		ApiInParamsVm apiInParamsVm = new ApiInParamsVm();
		apiInParamsVm.setBizcode(bizcode);
		apiInParamsVm.setBizmethod(bizmethod);
		apiInParamsVm.setModule(module.name());
		apiInParamsVm.setDatasource(datasource);
		return apiInParamsVm;
	}
	
	public static ApiInParamsVm bulidInparam(String module,String bizcode,String bizmethod,String datasource ){
		ApiInParamsVm apiInParamsVm = new ApiInParamsVm();
		apiInParamsVm.setBizcode(bizcode);
		apiInParamsVm.setBizmethod(bizmethod);
		apiInParamsVm.setModule(module);
		apiInParamsVm.setDatasource(datasource);
		return apiInParamsVm;
	}
	
	public static ApiOutParamsVm bulidOutParam(Integer result,String message,String resulttype,String outBo){
		ApiOutParamsVm apiOutParamsVm = new ApiOutParamsVm();
		apiOutParamsVm.setMessage(message);
		apiOutParamsVm.setResultstate(result);
		apiOutParamsVm.setResulttype(resulttype);
		apiOutParamsVm.setOutbo(outBo);
		return apiOutParamsVm;
	}
	
	/**
	 * 
	* @Title: bulidOutSucceed 
	* @Description: 构建成功
	* @param @param message
	* @param @param resulttype
	* @param @param datasource
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public static ApiOutParamsVm bulidOutSucceed(String message,String resulttype,Object outBo){
		ApiOutParamsVm apiOutParamsVm = new ApiOutParamsVm();
		apiOutParamsVm.setMessage(message);
		apiOutParamsVm.setResultstate(0);
		apiOutParamsVm.setResulttype(resulttype);
		apiOutParamsVm.setOutbo(outBo);
		return apiOutParamsVm;
	}
	
	public static ApiOutParamsVm bulidOutSucceed(String message,Object outBo){
		ApiOutParamsVm apiOutParamsVm = new ApiOutParamsVm();
		apiOutParamsVm.setMessage(message);
		apiOutParamsVm.setResultstate(0);
		apiOutParamsVm.setResulttype("succeed");
		apiOutParamsVm.setOutbo(outBo);
		return apiOutParamsVm;
	}
	
	public static ApiOutParamsVm bulidOutSucceed(String message){
		ApiOutParamsVm apiOutParamsVm = new ApiOutParamsVm();
		apiOutParamsVm.setMessage(message);
		apiOutParamsVm.setResultstate(0);
		apiOutParamsVm.setResulttype("succeed");
		apiOutParamsVm.setOutbo(null);
		return apiOutParamsVm;
	}
	
	/**
	 * 
	* @Title: bulidOutFail 
	* @Description: 构建输出失败
	* @param @param message
	* @param @return
	* @return ApiOutParamsVm 
	* @throws
	 */
	public static ApiOutParamsVm bulidOutFail(String message){
		ApiOutParamsVm apiOutParamsVm = new ApiOutParamsVm();
		apiOutParamsVm.setMessage(message);
		apiOutParamsVm.setResultstate(1);
		apiOutParamsVm.setResulttype("error");
		apiOutParamsVm.setOutbo(null);
		return apiOutParamsVm;
	}
	
	public static ApiOutParamsVm bulidOutFail(String message,String errorcode){
		ApiOutParamsVm apiOutParamsVm = new ApiOutParamsVm();
		apiOutParamsVm.setMessage(message);
		apiOutParamsVm.setResultstate(1);
		apiOutParamsVm.setResulttype(errorcode);
		apiOutParamsVm.setOutbo(null);
		return apiOutParamsVm;
	}
	
	
	
	/**
	 * 
	* @Title: fromInParams 
	* @Description: 序列化输入参数
	* @param @param injson
	* @param @return
	* @param @throws ApiErrorException
	* @return ApiInParamsVm 
	* @throws
	 */
	public static ApiInParamsVm fromInParams(String injson)throws ErrorException{
		return jsonMapper.fromJson(injson, ApiInParamsVm.class);
	}
	
	/**
	 * 
	* @Title: toInParamsJson 
	* @Description: 序列化输入参数
	* @param @param apiInParamsVm
	* @param @return
	* @param @throws ApiErrorException
	* @return String 
	* @throws
	 */
	public static String toInParamsJson(ApiInParamsVm apiInParamsVm)throws ErrorException{
		return jsonMapper.toJson(apiInParamsVm);
	}
	
	/**
	 * 
	* @Title: fromOutParams 
	* @Description: TODO
	* @param @param outjson
	* @param @return
	* @param @throws ApiErrorException
	* @return ApiOutParamsVm 
	* @throws
	 */
	public static ApiOutParamsVm fromOutParams(String outjson)throws ErrorException{
		return jsonMapper.fromJson(outjson, ApiOutParamsVm.class);
	}
	/**
	 * 
	* @Title: toOutParamsJson 
	* @Description: TODO
	* @param @param outJson
	* @param @return
	* @param @throws ApiErrorException
	* @return String 
	* @throws
	 */
	public static String toOutParamsJson(String outJson)throws ErrorException{
		return jsonMapper.toJson(outJson);
	}
	

	
}
