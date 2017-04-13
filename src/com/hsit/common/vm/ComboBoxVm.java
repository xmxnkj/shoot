package com.hsit.common.vm;

public class ComboBoxVm {
	
	public ComboBoxVm() {

	}
	
	public ComboBoxVm(String id, String text) {
		super();
		this.id = id;
		this.text = text;
	}
	//@JsonProperty("lable")  
	private String id;
	//@JsonProperty("value")  
	private String text;
	//private boolean selected =false;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}

}
