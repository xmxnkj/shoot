package com.szit.comment.entity;

import com.hsit.common.kfbase.entity.DomainEntity;

/**
 * 区域
 * @author linzf
 *
 */
public class Area extends DomainEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -453143841678521726L;
	private String parentId;
	private String code;
	private String enName;
	private Float lon;
	private Float lat;
	
	/**
	 * 经度
	 * @return
	 */
	public Float getLon() {
		return lon;
	}

	/**
	 * 经度
	 * @param lon
	 */
	public void setLon(Float lon) {
		this.lon = lon;
	}

	/**
	 * 纬度
	 * @return
	 */
	public Float getLat() {
		return lat;
	}

	/**
	 * 纬度
	 * @param lat
	 */
	public void setLat(Float lat) {
		this.lat = lat;
	}

	/**
	 * 英文名
	 * @return
	 */
	public String getEnName() {
		return enName;
	}

	/**
	 * 英文名
	 * @param enName
	 */
	public void setEnName(String enName) {
		this.enName = enName;
	}

	/**
	 * 编号
	 * @return
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 编号
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 上级ID
	 * @return
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 上级ID
	 * @param parentId
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
}
