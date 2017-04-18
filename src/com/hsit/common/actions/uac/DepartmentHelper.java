/**
 * File Name: DepartmentHelper.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.uac;

import java.util.List;

import com.hsit.common.spring.ApplicationContextUtils;
import com.hsit.common.uac.entity.Department;
import com.hsit.common.uac.service.DepartmentService;

/**
 * @ClassName:DepartmentHelper
 * @date 2017-3-30 下午2:23:50
 * 
 */
public class DepartmentHelper {
	private static DepartmentService departmentService=null;
	public static List<Department> getDepartments(){
		if (departmentService==null) {
			departmentService = (DepartmentService)ApplicationContextUtils.getBean("departmentService");
		}
		return departmentService.getEntities(null);
	}
}
