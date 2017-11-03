package com.szit.arbitrate.mediation.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsit.common.kfbase.entity.DomainEntity;

@JsonIgnoreProperties({"name","description","displayOrder","createTime"})
public class Street extends DomainEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 915794403642598974L;
	private String streetNname;

	public String getStreetNname() {
		return streetNname;
	}

	public void setStreetNname(String streetNname) {
		this.streetNname = streetNname;
	}	
}
