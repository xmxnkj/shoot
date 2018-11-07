/**   
* @Title: PostParams.java
* @Package com.szit.cowell.api.common.utils
* @Description: TODO
* @author XUJC
* @date 2017年10月30日 上午8:44:04
* @version V1.0   
*/


package com.szit.arbitrate.api.common.utils;

/**
 * 
* @ClassName: HttpPostParams
* @Description:
* @author Administrator
* @date 2017年3月20日 下午2:51:23
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public class HttpPostParams {

	private String code;
	private String inbo;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInbo() {
		return inbo;
	}
	public void setInbo(String inbo) {
		this.inbo = inbo;
	}
	
	public static HttpPostParams bulidParams(String code,String inbo){
		HttpPostParams httppostparams = new HttpPostParams();
		httppostparams.setCode(code);
		httppostparams.setInbo(inbo);
		return httppostparams;
	}
	
}
