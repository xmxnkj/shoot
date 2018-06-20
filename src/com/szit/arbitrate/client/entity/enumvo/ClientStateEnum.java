package com.szit.arbitrate.client.entity.enumvo;

public enum ClientStateEnum {
	
	Visitor,//临时游客
	Normal,//已注册未认证
	Certificated,//已实名认证
	/**
	 * 分发中心
	 */
	MediationCenter,
	/**
	 * 机构
	 */
	MediationAgency,
	/**
	 * 员
	 */
	Mediator;
}
