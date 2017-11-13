package com.szit.arbitrate.mediation.entity.query;

import com.hsit.common.kfbase.entity.EntityQueryParam;

public class StreetQuery extends EntityQueryParam{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String streetNname;

	public String getStreetNname() {
		return streetNname;
	}

	public void setStreetNname(String streetNname) {
		this.streetNname = streetNname;
	}	

}
