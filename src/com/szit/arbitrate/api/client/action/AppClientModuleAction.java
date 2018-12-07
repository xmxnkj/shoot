
package com.szit.arbitrate.api.client.action;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.exceptions.BizException;
import com.hsit.common.exceptions.ErrorException;
import com.hsit.common.kfbase.entity.DomainEntity;
import com.hsit.common.kfbase.entity.EntityQueryParam;
import com.hsit.common.service.AppBaseService;
import com.hsit.common.utils.JsonFormatUtil;
import com.hsit.common.utils.JsonMapper;
import com.szit.arbitrate.api.client.factory.ClientModuleBizFactory;
import com.szit.arbitrate.api.common.ApiTools;
import com.szit.arbitrate.api.common.ProjectModuleEnum;
import com.szit.arbitrate.api.common.vm.ApiInParamsVm;
import com.szit.arbitrate.api.common.vm.ApiOutParamsVm;


/**
 * 
* @ClassName: AppClientModuleAction
* @Description:App接口会员中心模块Action
* @author Administrator
* @date 2017年3月20日 下午2:45:04
* @UpdateUser:
* @UpdateDate:   
* @UpdateRemark:
* @Copyright: 2017 厦门西牛科技有限公司
* @versions:1.0
 */
@Controller("appClientModuleAction")
@Scope("prototype")
public class AppClientModuleAction extends BaseAction<DomainEntity,EntityQueryParam>{

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(AppClientModuleAction.class);
	private static JsonMapper jsonMapper = JsonMapper.getInstance();
	
	@Override
	protected AppBaseService<DomainEntity, EntityQueryParam> getService() {
		return null;
	}
	
	//业务代码
	private String code;
	//输入参数
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

	/**
	 * 
	* @Title: apiExecution 
	* @Description:API业务接口执行
	* @param 
	* @return void 
	* @throws
	 */
	public void apiBizExecution(){
		ApiOutParamsVm outVm = null;
		ApiInParamsVm apiInVm = null;
		try {
			logger.debug("会员中心模块-API业务接口接收的参数值-code:{},inbo:{}",code,inbo);
			if(StringUtils.isEmpty(code)){
				throw new BizException("code接收参数不能为空,请认真核查!");	
			}
			if(StringUtils.isEmpty(inbo)){
				throw new BizException("inbo接收参数不能为空,请认真核查!");	
			}
			apiInVm = new ApiInParamsVm();
			apiInVm.setModule(ProjectModuleEnum.client.name());
			apiInVm.setDatasource(inbo);
			String[] sBcodes = code.split("_");
			if(sBcodes==null&sBcodes.length<2){
				throw new BizException("非法参数,code参数符合规则,请认真核查!");	
			}else{
				apiInVm.setBizcode(sBcodes[0]);
				apiInVm.setBizmethod(sBcodes[1]);
			}
			outVm = ClientModuleBizFactory.bulidBiz(apiInVm,this.getRequest(),this.getResponse());
		} catch (BizException biz) {
			logger.debug("会员中心模块-API业务接口执行出现业务错误",biz.getMessage());
			outVm = ApiTools.bulidOutFail(biz.getMessage());
			
		} catch (ErrorException error) {
			logger.error("会员中心模块-API业务接口执行出现异常错误<1>",error);
			outVm = ApiTools.bulidOutFail("API系统接口异常错误,请联系系统管理员!");
			
		} catch (Exception e) {
			logger.error("会员中心模块-API业务接口执行出现异常错误<2>",e);
			outVm = ApiTools.bulidOutFail("API系统接口异常错误,请联系系统管理员!");
		}
		String outjson = jsonMapper.toJson(outVm);
		JsonFormatUtil.printJson("会员中心模块-API业务接口输出JSON",outjson);
		this.outJson(outjson);
	}
	
	
}
