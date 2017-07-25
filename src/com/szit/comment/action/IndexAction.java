package com.szit.comment.action;

import java.io.IOException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.hsit.common.actions.DomainAction;
import com.hsit.common.utils.JsonMapper;
import com.szit.arbitrate.mediation.entity.DifferentSubjects;

@Controller("indexAction")
@Scope("prototype")
public class IndexAction extends DomainAction {
	public String index(){
		return SUCCESS;
	}
	
	public String main(){
		return SUCCESS;
	}
	
	public void test() throws IOException{
//		MyUtils.print(getResponse(), DifferentSubjects.valuesToString());
	}
}
