package com.szit.comment.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;

public class CommentQuery extends EntityQueryParam{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5933237404707463282L;

	private String reportId;

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}
}
