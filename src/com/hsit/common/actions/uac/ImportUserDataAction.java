/**
 * File Name: ImportUserDataAction.java
 * Encoding UTF-8
 * Version: 1.0
 * History:
 */
package com.hsit.common.actions.uac;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Severity;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.hsit.common.actions.BaseAction;
import com.hsit.common.uac.entity.User;
import com.hsit.common.uac.entity.queryparam.UserQuery;
import com.hsit.common.uac.service.UserService;
import com.hsit.common.utils.PoiHelper;

/**
 * @ClassName:ImportUserDataAction
 * @date 2017-3-30 上午11:22:53
 * 
 */
@Component("importUserDataAction")
@Scope("prototype")
public class ImportUserDataAction extends BaseAction<User, UserQuery> {
	private UserService service;

	public UserService getService() {
		return service;
	}

	@Autowired
	public void setService(UserService service) {
		this.service = service;
	}
	
	
	private File file;
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void importLeaders(){
		try {
			POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(file.getPath()));
			
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			for(int i=1; i<workbook.getNumberOfSheets(); i++){
				HSSFSheet sheet = workbook.getSheetAt(i);
				if (sheet != null) {
					for(int j=4; j<=sheet.getLastRowNum(); j++){
						String userName = PoiHelper.getString(sheet, j, 1);
						User user = service.getEntity(userName);
						if (user != null) {
							String partDutyLeaderName = PoiHelper.getString(sheet, j, 5);
							if(!StringUtils.isEmpty(partDutyLeaderName)){
								String[] arr = partDutyLeaderName.split("、");
								List<String> ids = new ArrayList<>();
								for(int k=0;k<arr.length;k++){
									User partDutyUser = service.getEntity(arr[k]);
									if (partDutyUser!=null) {
										ids.add(partDutyUser.getId());
									}
								}
								service.setUserDeputyLeaders(user.getId(), ids);
							}
							String relaUserNames = PoiHelper.getString(sheet, j, 9);
							if (!StringUtils.isEmpty(relaUserNames)) {
								String[] arr = relaUserNames.split("、");
								List<String> ids = new ArrayList<>();
								for(int k=0;k<arr.length;k++){
									User partDutyUser = service.getEntity(arr[k]);
									if (partDutyUser!=null) {
										ids.add(partDutyUser.getId());
									}
								}
								service.setUserRelaUsers(user.getId(), ids);
							}
						}
					}
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
