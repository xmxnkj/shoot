package com.szit.arbitrate.client.entity.enumvo;

public enum ClientStateEnum {
	
	Visitor,//临时游客
	Normal,//已注册未认证
	Certificated,//已实名认证
	/**
	 * 调解分发中心
	 */
	MediationCenter,
	/**
	 * 调解机构
	 */
	MediationAgency,
	/**
	 * 调解员
	 */
	Mediator;
}
