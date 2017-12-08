package com.szit.arbitrate.mediation.entity.enumvo;

public enum CaseAllocationStateEnum {
	
	MediationCenterNotAccepted,//调解中心未受理
	MediationCenterAccepted,//调解中心已受理未分配
	MediationCenterAllocated,//调解中心已分配
	AgencyManagerAccepted,//调解机构管理员已受理未分配
	AgencyManagerAllocated,//调解机构管理员已分配
	MediatorAccepted,//普通调解员
	Refuse;//拒绝

}
