/**   
* @Title: ApiOutParamsVm.java
* @Package com.szit.cowell.common.api.vm
* @Description: TODO
* @author XUJC
* @date 2017年10月26日 上午10:39:18
* @version V1.0   
*/


package com.szit.arbitrate.api.common.vm;


/**
 * 
* @ProjectName:
* @ClassName: ApiOutParamsVm
* @Description:API传出参数VM
* @author Administrator
* @date 2017年3月20日 下午2:53:48
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */

public class ApiOutParamsVm {
    //结果状态 true表示成功,false表示失败
	private Integer resultstate;
	//结果类型
	private String resulttype;
	//结果消息说明
	private String message;
	//结构数据源
	private Object outbo;

	public Integer getResultstate() {
		return resultstate;
	}
	public void setResultstate(Integer resultstate) {
		this.resultstate = resultstate;
	}
	public String getResulttype() {
		return resulttype;
	}
	public void setResulttype(String resulttype) {
		this.resulttype = resulttype;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getOutbo() {
		return outbo;
	}
	public void setOutbo(Object outbo) {
		this.outbo = outbo;
	}


	
	
	
}
