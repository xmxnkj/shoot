package com.szit.arbitrate.basisset.junit.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hsit.common.utils.JsonFormatUtil;
import com.szit.arbitrate.api.common.utils.BaseApiJunitTest;
import com.szit.arbitrate.basisset.entity.SysParameterTable;
import com.szit.arbitrate.basisset.entity.enumvo.ParameterTypeEnum;
import com.szit.arbitrate.basisset.entity.query.SysParameterTableQuery;
import com.szit.arbitrate.basisset.service.SysParameterTableService;


/**
 * @ProjectName:
 * @ClassName: SysParameterTableServiceJunit
 * @Description:基础-系统参数字典业务逻辑单元测试类
 * @author 
 * @date
 * @UpdateUser:
 * @UpdateDate:   
 * @UpdateRemark:
 * @Copyright: 2017 厦门西牛科技有限公司
 * @versions:1.0
 */
public class SysParameterTableServiceJunitTest  extends BaseApiJunitTest{

	@Autowired
	private SysParameterTableService sysparametertableService;

	private Logger logger = LoggerFactory.getLogger(SysParameterTableServiceJunitTest.class);

	@Test
	public void save(){
		SysParameterTable sysparametertable = new SysParameterTable();
		sysparametertable.setParametertype(ParameterTypeEnum.messagenotify);
		sysparametertable.setParametercode("accept_the_new_alerts");
		sysparametertable.setParametername("接收新消息通知");
		sysparametertable.setParameterseq(1);
		sysparametertable.setParameterinitvla("0");
		sysparametertable.setDeleteflag(false);
		sysparametertable.setBuliddatetime(new Date());
		sysparametertableService.save(sysparametertable);

		sysparametertable = new SysParameterTable();
		sysparametertable.setParametertype(ParameterTypeEnum.messagenotify);
		sysparametertable.setParametercode("donot_disturb_function_of_news");
		sysparametertable.setParametername("功能消息免打扰");
		sysparametertable.setParameterseq(2);
		sysparametertable.setParameterinitvla("0");
		sysparametertable.setDeleteflag(false);
		sysparametertable.setBuliddatetime(new Date());
		sysparametertableService.save(sysparametertable);

		sysparametertable = new SysParameterTable();
		sysparametertable.setParametertype(ParameterTypeEnum.messagenotify);
		sysparametertable.setParametercode("the_receiving_system_being_pushed");
		sysparametertable.setParametername("接收系统消息推送");
		sysparametertable.setParameterseq(3);
		sysparametertable.setParameterinitvla("0");
		sysparametertable.setDeleteflag(false);
		sysparametertable.setBuliddatetime(new Date());
		sysparametertableService.save(sysparametertable);

		sysparametertable = new SysParameterTable();
		sysparametertable.setParametertype(ParameterTypeEnum.messagenotify);
		sysparametertable.setParametercode("received_a_new_comment");
		sysparametertable.setParametername("收到新的评论");
		sysparametertable.setParameterseq(4);
		sysparametertable.setParameterinitvla("0");
		sysparametertable.setDeleteflag(false);
		sysparametertable.setBuliddatetime(new Date());
		sysparametertableService.save(sysparametertable);

		sysparametertable = new SysParameterTable();
		sysparametertable.setParametertype(ParameterTypeEnum.messagenotify);
		sysparametertable.setParametercode("received_a_new_praise");
		sysparametertable.setParametername("收到新的赞");
		sysparametertable.setParameterseq(5);
		sysparametertable.setParameterinitvla("0");
		sysparametertable.setDeleteflag(false);
		sysparametertable.setBuliddatetime(new Date());
		sysparametertableService.save(sysparametertable);


		sysparametertable = new SysParameterTable();
		sysparametertable.setParametertype(ParameterTypeEnum.privacysetting);
		sysparametertable.setParametercode("appear_near_people");
		sysparametertable.setParametername("出现在附近的人");
		sysparametertable.setParameterseq(1);
		sysparametertable.setParameterinitvla("0");
		sysparametertable.setDeleteflag(false);
		sysparametertable.setBuliddatetime(new Date());
		sysparametertableService.save(sysparametertable);

		sysparametertable = new SysParameterTable();
		sysparametertable.setParametertype(ParameterTypeEnum.privacysetting);
		sysparametertable.setParametercode("allow_wechat_find_me");
		sysparametertable.setParametername("允许微信找到我");
		sysparametertable.setParameterseq(2);
		sysparametertable.setParameterinitvla("0");
		sysparametertable.setDeleteflag(false);
		sysparametertable.setBuliddatetime(new Date());
		sysparametertableService.save(sysparametertable);

		sysparametertable = new SysParameterTable();
		sysparametertable.setParametertype(ParameterTypeEnum.privacysetting);
		sysparametertable.setParametercode("allow_QQ_find_me");
		sysparametertable.setParametername("允许QQ找到我");
		sysparametertable.setParameterseq(3);
		sysparametertable.setParameterinitvla("0");
		sysparametertable.setDeleteflag(false);
		sysparametertable.setBuliddatetime(new Date());
		sysparametertableService.save(sysparametertable);

		sysparametertable = new SysParameterTable();
		sysparametertable.setParametertype(ParameterTypeEnum.privacysetting);
		sysparametertable.setParametercode("allow_weibo_find_me");
		sysparametertable.setParametername("允许微博找到我");
		sysparametertable.setParameterseq(4);
		sysparametertable.setParameterinitvla("0");
		sysparametertable.setDeleteflag(false);
		sysparametertable.setBuliddatetime(new Date());
		sysparametertableService.save(sysparametertable);

		sysparametertable = new SysParameterTable();
		sysparametertable.setParametertype(ParameterTypeEnum.privacysetting);
		sysparametertable.setParametercode("allow_addressbook_find_me");
		sysparametertable.setParametername("允许通讯录找到我");
		sysparametertable.setParameterseq(5);
		sysparametertable.setParameterinitvla("0");
		sysparametertable.setDeleteflag(false);
		sysparametertable.setBuliddatetime(new Date());
		sysparametertableService.save(sysparametertable);
	}


	@Test
	public void query(){
		SysParameterTableQuery query = new SysParameterTableQuery();
		//TODO请实现添加查询参数


		List<SysParameterTable> list = sysparametertableService.getEntities(query);
		for (SysParameterTable entity : list) {
			JsonFormatUtil.printJson("entityJson",entity);
		} 
	}

	@Test
	public void addSysParameter(){
		SysParameterTable sysparametertable = new SysParameterTable();
		sysparametertable.setParametertype(ParameterTypeEnum.certificateswitch);
		sysparametertable.setParametercode("signProtocolSwitch");
		sysparametertable.setParametername("签署协议书实名认证开关");
		sysparametertable.setParameterseq(3);
		sysparametertable.setParameterinitvla("0");
		sysparametertable.setDeleteflag(false);
		sysparametertable.setBuliddatetime(new Date());
		sysparametertableService.save(sysparametertable);
	}

}