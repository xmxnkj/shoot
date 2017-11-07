package com.szit.arbitrate.mediation.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;

public class LegalDocDetailQuery extends EntityQueryParam{
	
	private static final long serialVersionUID = 3105521299201530465L;
	
	private String legalDocId;//法规ID 
	
	public String getLegalDocId() {
		return legalDocId;
	}
	public void setLegalDocId(String legalDocId) {
		this.legalDocId = legalDocId;
	}

}
