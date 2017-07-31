package com.szit.arbitrate.pushcentre.common;


/**
 *
 * @version Revision: 1.0.0 Date: Nov 27, 2013
 */
public class ApnConfig{
	
	private String devCertification;

	private String devCertPassword;
	
	private String prodCertification;
	
	private String prodCertPassword;
	
	private boolean sendProductionMessage;
	

	public ApnConfig(){
		
	}
	
	public ApnConfig(String Executor_devCertification,String Executor_devCertPassword,String Executor_prodCertification,
			String Executor_prodCertPassword,boolean Executor_sendProductionMessage){
		devCertification = Executor_devCertification;
		devCertPassword = Executor_devCertPassword;
		prodCertification = Executor_prodCertification;
		prodCertPassword = Executor_prodCertPassword;
		sendProductionMessage = Executor_sendProductionMessage;
	}
	
	/**
	 *
	 * @return
	 */
	public String getDevCertification() {
		return devCertification;
	}

	/**
	 *
	 * @param devCertification
	 */
	public void setDevCertification(String devCertification) {
		this.devCertification = devCertification;
	}

	/**
	 *
	 * @return
	 */
	public String getDevCertPassword() {
		return devCertPassword;
	}

	/**
	 *
	 * @param devCertPassword
	 */
	public void setDevCertPassword(String devCertPassword) {
		this.devCertPassword = devCertPassword;
	}

	/**
	 *
	 * @return
	 */
	public String getProdCertification() {
		return prodCertification;
	}

	/**
	 *
	 * @param prodCertification
	 */
	public void setProdCertification(String prodCertification) {
		this.prodCertification = prodCertification;
	}

	/**
	 *
	 * @return
	 */
	public String getProdCertPassword() {
		return prodCertPassword;
	}

	/**
	 *
	 * @param prodCertPassword
	 */
	public void setProdCertPassword(String prodCertPassword) {
		this.prodCertPassword = prodCertPassword;
	}
	
	/**
	 *
	 * @return
	 */
	public boolean isSendProductionMessage() {
		return sendProductionMessage;
	}

	/**
	 *
	 * @param sendProductionMessage
	 */
	public void setSendProductionMessage(boolean sendProductionMessage) {
		this.sendProductionMessage = sendProductionMessage;
	}
	
}
