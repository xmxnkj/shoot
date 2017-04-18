/**   
* @Title: NewBaseAction.java
* @Package com.hsit.common.actions
* @Description: TODO
* @author XUJC 
* @date 2017年11月15日 上午11:23:46
* @version V1.0   
*/


package com.hsit.common.actions;

import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.kfbase.entity.Paging;
import com.hsit.common.utils.JsonFormatUtil;
import com.hsit.common.utils.JsonMapper;
import com.hsit.common.utils.PageDataBean;
import com.hsit.common.utils.SystemConfig;

/**
 * @ProjectName:sizt-coupons
 * @ClassName: NewBaseAction
 * @Description:新的底层Struts Action
 * @author XUJC
 * @date 2017年3月15日 上午11:23:46
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司.
 * @versions:1.0
 */
public abstract class BaseJsonAction<T extends DomainEntity, Q extends EntityQueryParam> extends BaseAction<T, Q> {

	private static final long serialVersionUID = -7182224862893532078L;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected static JsonMapper jsonMapper = JsonMapper.getInstance();
	
	private String sort;
	private String order;
	
	//API接口是否调试 默认false
	private boolean isDebug =false;
	
	@Autowired
	private SystemConfig systemConfig;
	
	
	@Action(value = "saveJson")
	public void saveJson()throws Exception{
		JSONObject json = new JSONObject();
		try {
			if (getService() == null) {
				json.put("success", false);
				json.put("message", "业务类对象空,请开发人员核查!");
				json.put("data", "");
			}else{
				this.initAddForm();
				T entity = this.getEntity();
				getService().save(entity);
				json.put("success", true);
				json.put("message", "提交成功!");
				json.put("data", entity);
			}
		} catch (Exception e) {
			logger.error("执行initAddFormJson异常错误",e);
			json.put("success", false);
			json.put("message", "异常错误,请联系系统管理员!");
			json.put("data", "");
		}
		if(isDebug()){
			JsonFormatUtil.printJson("saveJson结果:",json.toString());
		}
		outJson(json.toString());
	}
	

	
	/**
	 * 
	* @Title: findDataBindDg 
	* @Description: 查询绑定
	* @param @throws Exception
	* @return void 
	* @throws
	 */
	@Action(value = "findDataBindDg")
	public void findDataBindDg() throws Exception{
		try {
			if (getService() == null) {
				outFailJson(null);
				return;
			}
			beforeQuery();
			Q query = this.getEntityQuery();
			if(StringUtils.isNotEmpty(sort)){
				if(order.equals("asc")){
				  query.addOrder(sort,false);
				}else{
				  query.addOrder(sort,true);
				}
			}
			List<T> list = getService().getEntities(query);
			Paging  paging  = this.getEntityQuery().getPaging();
			PageDataBean<T> pageList = new PageDataBean<T>(paging,list);
			String jsonResult = jsonMapper.toJson(pageList);
			if(isDebug()){
			    JsonFormatUtil.printJson("分页JSON", jsonResult);
			}
			outJson(jsonResult);
		} catch (Exception e) {
			logger.error("执行findDataBindDg异常错误",e);
			outFailJson(e);
		}
	}
	
	/**
	 * 
	* @Title: initAddFormJson 
	* @Description:初始化添加表单JSON
	* @param @throws Exception
	* @return void 
	* @throws
	 */
	@Action(value = "initAddFormJson")
	public void initAddFormJson() throws Exception{
			try {
				this.initAddForm();
				String jsonResult = jsonMapper.toJson(this.getEntity());
				if(isDebug()){
					 JsonFormatUtil.printJson("Entity JSON", jsonResult);		
				}
				outJson(jsonResult);
			} catch (Exception e) {
				logger.error("执行initAddFormJson异常错误",e);
				outFailJson(e);
			}
	}
	
	/**
	 * 
	* @Title: loadJsonById 
	* @Description: 根据ID加载实体JSON数据
	* @param @throws Exception
	* @return void 
	* @throws
	 */
	@Action(value = "loadJsonById")
	public void loadJsonById() throws Exception{
			try {
				if (getService() == null) {
					outFailJson(null);
					return;
				}
				T endtiy = getService().getById(this.getId());
				String jsonResult = jsonMapper.toJson(endtiy);
				if(isDebug()){
					  JsonFormatUtil.printJson("Entity JSON", jsonResult);
				}
				outJson(jsonResult);
			} catch (Exception e) {
				logger.error("执行loadJsonById异常错误",e);
				outFailJson(e);
			}
	}
	
	
	@Action(value = "loadEntityJson")
	public void loadEntityJson()throws Exception{
		try {
			if (getService() == null) {
				outFailJson(null);
				return;
			}
			beforeQuery();
			T endtiy = getService().getEntity(this.getEntityQuery());
			String jsonResult = jsonMapper.toJson(endtiy);
			if(isDebug()){
				JsonFormatUtil.printJson("Entity JSON", jsonResult);
			}
			outJson(jsonResult);
		} catch (Exception e) {
			logger.error("执行loadEntityJson异常错误",e);
			outFailJson(e);
		}
	}

	/**
	 * 
	* @Title: dispatcherEditPage 
	* @Description: 分发到编辑页面
	* @param @return
	* @return String 
	* @throws
	 */
	public String dispatcherEditPage(){
		return SUCCESS;
	}
	
	/**
	 * 
	* 方法描述:list列表跳转页面
	* 创建人: Administrator
	* 创建时间：2017年1月16日
	* @return
	 */
	public String list(){
	   return SUCCESS;
	}

	/**
	 * 
	* 方法描述:form跳转页面
	* 创建人: Administrator
	* 创建时间：2017年1月16日
	* @return
	 */
	public String form(){
	   return SUCCESS;
	}


	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
    public boolean isDebug() {
		if(systemConfig.getGlobaldebug()){
			return systemConfig.getIsDebug();
		}else{
			return  isDebug;
		}
	}
	public void setDebug(boolean isDebug) {
		this.isDebug =  isDebug;
	}
	

}
