package com.szit.arbitrate.api.mediation.bo.in;


public class TClientInBo {
	
	private String id;//当事人id
	private String name;//姓名或公司名称
	private String tel;//联系方式
	private String address;//地址
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
