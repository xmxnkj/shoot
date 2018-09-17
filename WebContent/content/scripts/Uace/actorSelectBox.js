var ActorSelectBox={
		
	create:function(){
		var asb = {
			constValue:{
				spliter:";"
			},
			initConfig:{
				isSelectUser:true,
				isSelectRole:true,
				isSelectPosition:true,
				isSelectActivityUserSelector:true,
				isSelectDepartmentActor:false,
				
				selectActorUrl:"",
				
				actorIdsCtl:"_actorIds",
				actorNamesCtl:"_actorNames",
				
				dialogCaption:"选择审批人",
				
				isSingleSelect:false
			},
			actors:{
				userIds:"",
				userNames:"",
				roleIds:"",
				roleNames:"",
				positionIds:"",
				positionNames:"",
				activitySelectorIds:"",
				activitySelectorNames:"",
				departmentActorIds:"",
				departmentActorNames:"",
				departmentChargeLeaderIds:"",
				departmentPartChargeLeaderIds:"",
				departmentLeaderIds:"",
				departmentDeputyLeaderIds:"",
				departmentChargeLeaderNames:"",
				departmentPartChargeLeaderNames:"",
				departmentLeaderNames:"",
				departmentDeputyLeaderNames:""
			},
			clear:function(){
				asb.actors.userIds = "";
				asb.actors.userNames = "";
				asb.actors.roleIds = "";
				asb.actors.roleNames = "";
				asb.actors.positionIds = "";
				asb.actors.positionNames = "";
				asb.actors.activitySelectorIds = "";
				asb.actors.activitySelectorNames = "";
				asb.departmentActorIds="";
				asb.departmentActorNames="";
				asb.departmentLeaderIds="";
				asb.departmentLeaderNames="";
				asb.departmentChargeLeaderIds="";
				asb.departmentChargeLeaderNames="";
				asb.departmentPartChargeLeaderIds="";
				asb.departmentPartChargeLeaderNames="";
				asb.departmentDeputyLeaderIds="";
				asb.departmentDeputyLeaderNames="";
			},
			selectActor:function (){
				
				var isSelect="";
				if(!asb.initConfig.isSelectUser){
					isSelect+="&isSelectUser=false";
				}
				if(!asb.initConfig.isSelectRole){
					isSelect+="&isSelectRole=false";
				}
				if(!asb.initConfig.isSelectPosition){
					isSelect+="&isSelectPosition=false";
				}
				if(!asb.initConfig.isSelectActivityUserSelector){
					isSelect+="&isSelectActivityUserSelector=false";
				}
				if(!asb.initConfig.isSelectDepartmentActor){
					isSelect+="&isSelectDepartmentActor=false";
				}
				
				
				var url = asb.initConfig.selectActorUrl 
					+ '?ids=' + $("#" + asb.initConfig.actorIdsCtl).val() 
					+ '&names=' + encodeURI(encodeURI($("#" + asb.initConfig.actorNamesCtl).val())) 
					+ isSelect;
				top.openDialog2(url, asb.initConfig.dialogCaption, 650, 400, asb);
			},
			actorSelected: function(actors
					){
				asb.actors = actors;
				$("#" + asb.initConfig.actorNamesCtl).val(asb.getActorNames());
				$("#" + asb.initConfig.actorIdsCtl).val(asb.getActorIds());
				if(asb.actorSelectedCallback != null && typeof(asb.actorSelectedCallback)=="function"){
					asb.actorSelectedCallback(actors);
				}
			},
			actorSelectedCallback:null,
			getActorNames:function (){
				var actorNames = asb.actors.positionNames;
				actorNames += (actorNames != "" && asb.actors.roleNames != "") ? asb.constValue.spliter + asb.actors.roleNames : asb.actors.roleNames;
				actorNames += (actorNames != "" && asb.actors.userNames != "") ? asb.constValue.spliter + asb.actors.userNames : asb.actors.userNames;
				actorNames += (actorNames != "" && asb.actors.activitySelectorNames != "") ? asb.constValue.spliter + asb.actors.activitySelectorNames : asb.actors.activitySelectorNames;
				
				actorNames += (actorNames != "" && asb.actors.departmentChargeLeaderNames != "") ? asb.constValue.spliter + asb.actors.departmentChargeLeaderNames : asb.actors.departmentChargeLeaderNames;
				actorNames += (actorNames != "" && asb.actors.departmentPartChargeLeaderNames != "") ? asb.constValue.spliter + asb.actors.departmentPartChargeLeaderNames : asb.actors.departmentPartChargeLeaderNames;
				actorNames += (actorNames != "" && asb.actors.departmentLeaderNames != "") ? asb.constValue.spliter + asb.actors.departmentLeaderNames : asb.actors.departmentLeaderNames;
				actorNames += (actorNames != "" && asb.actors.departmentDeputyLeaderNames != "") ? asb.constValue.spliter + asb.actors.departmentDeputyLeaderNames : asb.actors.departmentDeputyLeaderNames;
				return actorNames;
			},
			getActorIds:function(){
				var actorIds = asb.actors.positionIds;
				actorIds += (actorIds != "" && asb.actors.roleIds != "") ? asb.constValue.spliter + asb.actors.roleIds : asb.actors.roleIds;
				actorIds += (actorIds != "" && asb.actors.userIds != "") ? asb.constValue.spliter + asb.actors.userIds : asb.actors.userIds;
				actorIds += (actorIds != "" && asb.actors.activitySelectorIds != "") ? asb.constValue.spliter + asb.actors.activitySelectorIds : asb.actors.activitySelectorIds;
				
				actorIds += (actorIds != "" && asb.actors.departmentChargeLeaderIds != "") ? asb.constValue.spliter + asb.actors.departmentChargeLeaderIds : asb.actors.departmentChargeLeaderIds;
				actorIds += (actorIds != "" && asb.actors.departmentPartChargeLeaderIds != "") ? asb.constValue.spliter + asb.actors.departmentPartChargeLeaderIds : asb.actors.departmentPartChargeLeaderIds;
				actorIds += (actorIds != "" && asb.actors.departmentLeaderIds != "") ? asb.constValue.spliter + asb.actors.departmentLeaderIds : asb.actors.departmentLeaderIds;
				actorIds += (actorIds != "" && asb.actors.departmentDeputyLeaderIds != "") ? asb.constValue.spliter + asb.actors.departmentDeputyLeaderIds : asb.actors.departmentDeputyLeaderIds;
				return actorIds;
			},
			
			
			getActors:function (){
				return asb.actors;
			}
		};
		
		return asb;
	}
};