package com.szit.arbitrate.mediation.entity.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class MediationCaseExcelDto implements Serializable{
	
	private String street;//街道
	private BigDecimal totalNum;//总数
	private BigDecimal people;//涉及人数
	private BigDecimal successNum;//成功数
	private BigDecimal specialNum;//疑难复杂数
	private BigDecimal money;
	private List<Map<String, Object>> hostNumList;//不同主题数
	private List<Map<String, Object>> sourceNumList;//来源数
	private List<Map<String, Object>> typeNumList;//分类数
	private List<Map<String, Object>> protocolNumList;//协议形式数
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public BigDecimal getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(BigDecimal totalNum) {
		this.totalNum = totalNum;
	}
	public BigDecimal getPeople() {
		return people;
	}
	public void setPeople(BigDecimal people) {
		this.people = people;
	}
	public BigDecimal getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(BigDecimal successNum) {
		this.successNum = successNum;
	}
	public BigDecimal getSpecialNum() {
		return specialNum;
	}
	public void setSpecialNum(BigDecimal specialNum) {
		this.specialNum = specialNum;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public List<Map<String, Object>> getHostNumList() {
		return hostNumList;
	}
	public void setHostNumList(List<Map<String, Object>> hostNumList) {
		this.hostNumList = hostNumList;
	}
	public List<Map<String, Object>> getSourceNumList() {
		return sourceNumList;
	}
	public void setSourceNumList(List<Map<String, Object>> sourceNumList) {
		this.sourceNumList = sourceNumList;
	}
	public List<Map<String, Object>> getTypeNumList() {
		return typeNumList;
	}
	public void setTypeNumList(List<Map<String, Object>> typeNumList) {
		this.typeNumList = typeNumList;
	}
	public List<Map<String, Object>> getProtocolNumList() {
		return protocolNumList;
	}
	public void setProtocolNumList(List<Map<String, Object>> protocolNumList) {
		this.protocolNumList = protocolNumList;
	}
	

}
