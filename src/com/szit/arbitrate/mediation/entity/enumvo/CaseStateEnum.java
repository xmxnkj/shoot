package com.szit.arbitrate.mediation.entity.enumvo;

public enum CaseStateEnum {
	
	Init,//初始化，未受理,申请中
	Allocation,//分配中
	Mediating,//调节中
	WaitSign,//待签署
	WaitComplete,//待结案
	Closed,//已关闭
	Refused,//已拒绝
	Completed,//调解员点击结案，已结案
	GiveUp;//用户放弃调解
}
