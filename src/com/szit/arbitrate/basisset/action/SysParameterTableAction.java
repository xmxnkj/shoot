/**   
* @Title: SysParameterTableAction.java
* @Package com.szit.cowell.basisset.action
* @Description: TODO
* @author XUJC
* @date 2017年1月17日 下午5:18:49
* @version V1.0   
*/


package com.szit.arbitrate.basisset.action;
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseJsonAction;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.szit.arbitrate.basisset.entity.SysParameterTable;
import com.szit.arbitrate.basisset.entity.query.SysParameterTableQuery;
import com.szit.arbitrate.basisset.service.SysParameterTableService;

/**
 * @ProjectName:cowell
 * @ClassName: SysParameterTableAction
 * @Description:
 * @author XUJC
 * @date 2017年1月17日 下午5:18:49
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
@Namespace("/admin/basisset")
@Controller("sysParameterTableAction")
public class SysParameterTableAction extends
		BaseJsonAction<SysParameterTable, SysParameterTableQuery> {
	/**
	* @Fields serialVersionUID : TODO
	*/
	
	private static final long serialVersionUID = 5984928554183093717L;
	@Autowired
	private SysParameterTableService service;

	public SysParameterTableService getService() {
		return service;
	}

	public void setService(SysParameterTableService service) {
		this.service = service;
	}

	/** 添加基础数据 */
	@Action(value = "saveSysParameterTable")
	public void saveSysParameterTable(){
		JSONObject json = new JSONObject();
		try {
			SysParameterTable entity = this.getEntity();
			service.save(entity);
			json.put("success", true);
			json.put("message", "提交成功!");
			json.put("data", entity);
		} catch (Exception e) {
			json.put("success", false);
			json.put("message", "异常错误,请联系系统管理员!");
			json.put("data", "");
		}
		outJson(json.toString());
	}
	/** 删除基础数据 */
	@Action(value = "deleteSysParameterTable")
	public void deleteSysParameterTable(){
		JSONObject json = new JSONObject();
		try {
			String entityid = this.getRequest().getParameter("id");
			service.deleteById(entityid);
			json.put("success", true);
			json.put("message", "删除成功!");
			json.put("data", "");
		} catch (BizException biz) {
			json.put("success", false);
			json.put("message", biz.getMessage());
			json.put("data", "");
		} catch (ErrorException error) {
			json.put("success", false);
			json.put("message", error.getMessage());
			json.put("data", "");
		}
		outJson(json.toString());
	}
	
	
	/**
	 * 
	* @Title: auditCertificate 
	* @Description:实名认证审核
	* @param 
	* @return void 
	* @throws
	 */
	@Action(value = "certificateSwitch")
	public void certificateSwitch(){
		JSONObject json = new JSONObject();
		try {
			String parameterid = this.getRequest().getParameter("id");
			String oper = this.getRequest().getParameter("oper");
			service.certificateSwitch(parameterid,oper);
			json.put("success", true);
			json.put("message", "操作成功!");
			json.put("data", "");
		} catch (BizException biz) {
			json.put("success", false);
			json.put("message", biz.getMessage());
			json.put("data", "");
		}catch (ErrorException error) {
			json.put("success", false);
			json.put("message", error.getMessage());
			json.put("data", "");
		}
		outJson(json.toString());
	}
	
}
