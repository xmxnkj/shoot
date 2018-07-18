package com.szit.arbitrate.mediation.entity.enumvo;

public enum CaseAllocationStateEnum {
	
	MediationCenterNotAccepted,//中心未受理
	MediationCenterAccepted,//中心已受理未分配
	MediationCenterAllocated,//中心已分配
	AgencyManagerAccepted,//机构管理员已受理未分配
	AgencyManagerAllocated,//机构管理员已分配
	MediatorAccepted,//普通员
	Refuse;//拒绝

}
