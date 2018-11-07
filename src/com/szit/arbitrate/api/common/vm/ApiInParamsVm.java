/**
 * 
 */
package com.szit.arbitrate.api.common.vm;


/**
 * 
* @ProjectName:
* @ClassName: ApiInParamsVm
* @Description:API接口传入参数VM
* @author Administrator
* @date 2017年3月20日 下午2:53:34
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
public class ApiInParamsVm {

	//模块
	private String module;
    //业务代码
	private String bizcode;
	//业务方法
	private String bizmethod;
	//数据源
    private String datasource;
    
    
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	public String getBizcode() {
		return bizcode;
	}
	public void setBizcode(String bizcode) {
		this.bizcode = bizcode;
	}
	public String getBizmethod() {
		return bizmethod;
	}
	public void setBizmethod(String bizmethod) {
		this.bizmethod = bizmethod;
	}

    
    
}

