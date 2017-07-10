package com.szit.comment.appactions;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.hsit.common.actions.DomainAction;

public class ReportDataAction extends DomainAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6774560476965734301L;
	
	
	
	public String saveDataFile(){
		if (dataFile!=null) {
			
			try {
				String dest = getServletContext().getRealPath(getRequest().getRequestURI());
				Files.copy(dataFile.toPath(), java.nio.file.Paths.get(dest), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		return SUCCESS;
	}
	
	
	
	
	
	
	private File dataFile;

	public File getDataFile() {
		return dataFile;
	}

	public void setDataFile(File dataFile) {
		this.dataFile = dataFile;
	}
}
