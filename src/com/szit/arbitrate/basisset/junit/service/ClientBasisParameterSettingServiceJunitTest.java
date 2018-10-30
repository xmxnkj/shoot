package com.szit.arbitrate.basisset.junit.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsit.common.utils.JsonFormatUtil;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.basisset.entity.ClientBasisParameterSetting;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;
import com.szit.arbitrate.basisset.entity.query.ClientBasisParameterSettingQuery;
import com.szit.arbitrate.basisset.service.ClientBasisParameterSettingService;


/**
 * @ProjectName:
 * @ClassName: ClientBasisParameterSettingServiceJunit
 * @Description:基础参数设置业务逻辑单元测试类
 * @author 
 * @date
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
public class ClientBasisParameterSettingServiceJunitTest  extends BaseApiJunitTest{
	
	@Autowired
	private ClientBasisParameterSettingService clientbasisparametersettingService;
	
	private Logger logger = LoggerFactory.getLogger(ClientBasisParameterSettingServiceJunitTest.class);
	
	@Test
	public void save(){
           ClientBasisParameterSetting clientbasisparametersetting = new ClientBasisParameterSetting();
           //TODO请实现
		clientbasisparametersetting.setClientid("");
        clientbasisparametersetting.setParametertype(ParameterTypeEnum.messagenotify);
        clientbasisparametersetting.setSeq(0);
		clientbasisparametersetting.setParameterid("");
		clientbasisparametersetting.setParametersettingval("");
        clientbasisparametersetting.setSettingdatetime(new Date());
        clientbasisparametersettingService.save(clientbasisparametersetting);
	}


        @Test
	public void query(){
          ClientBasisParameterSettingQuery query = new ClientBasisParameterSettingQuery();
          //TODO请实现添加查询参数
       
          
          List<ClientBasisParameterSetting> list = clientbasisparametersettingService.getEntities(query);
	      for (ClientBasisParameterSetting entity : list) {
		     JsonFormatUtil.printJson("entityJson",entity);
	      } 
	}
	
	
}