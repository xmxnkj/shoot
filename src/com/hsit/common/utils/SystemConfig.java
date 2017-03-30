package com.hsit.common.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 
* @ClassName: SystemConfig
* @Description:
* @author Administrator
* @date 2017
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Component("systemConfig")
public class SystemConfig {

	@Value("${system.isdebug}")
	private Boolean isDebug;
	
	@Value("${system.globaldebug}")
	private Boolean globaldebug;

	public Boolean getIsDebug() {
		return isDebug;
	}

	public void setIsDebug(Boolean isDebug) {
		this.isDebug = isDebug;
	}

	public Boolean getGlobaldebug() {
		return globaldebug;
	}

	public void setGlobaldebug(Boolean globaldebug) {
		this.globaldebug = globaldebug;
	}
	
	
	
	
}
