<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script src="<s:url value="/content/scripts/Uace/selectActor.js" />"></script>
	<script>
		var isSingle = false;
		$(function() {
			isSingle = window.parent.isSingleSelect;
			initDepartmentTree();
			initDepartmentActors();
			$("#chargeLeader").click(checkData);
			$("#partChargeLeader").click(checkData);
			$("#leader").click(checkData);
			$("#deputyLeader").click(checkData);
		});

		function initDepartmentTree(){
			$("#ulTree").tree({
	            url: "<s:url action="listjson" namespace="/uac/department"/>",
	            loadFilter: function (rows) {
	                return convert(rows, "部门");
	            },
	            onClick:function(node){
	            	refreshData(node);
	            }
	        });
		}
		
		function refreshData(node) {
			//var node = $("#ulTree").tree("getSelected");
			
			if(node != null && node.id != null && node.id != ""){
				$("#spDepartment").html("部门：" + node.text);
				if(isArrContainId(chargeLeaderIds, node.id)){
					$("#chargeLeader").prop("checked", true);
				}else{
					$("#chargeLeader").prop("checked", false);
				}
				if(isArrContainId(partChargeLeaderIds, node.id)){
					$("#partChargeLeader").prop("checked", true);
				}else{
					$("#partChargeLeader").prop("checked", false);
				}
				if(isArrContainId(leaderIds, node.id)){
					$("#leader").prop("checked", true);
				}else{
					$("#leader").prop("checked", false);
				}
				if(isArrContainId(deputyLeaderIds, node.id)){
					$("#deputyLeader").prop("checked", true);
				}else{
					$("#deputyLeader").prop("checked", false);
				}
			}else{
				$("#spDepartment").html("未选择部门，请选选择部后再选择相应部门的角色！");
			}
		}
		
		function checkData(){
			var node = $("#ulTree").tree("getSelected");
			if(node != null && node.id != null && node.id != ""){
				if($("#chargeLeader").prop("checked")){
					addId(chargeLeaderIds, chargeLeaderNames, node.id, node.text+"部门主管领导");
				}else{
					removeId(chargeLeaderIds, chargeLeaderNames, node.id);
				}
				if($("#partChargeLeader").prop("checked")){
					addId(partChargeLeaderIds, partChargeLeaderNames, node.id, node.text+"部门分管领导");
				}else{
					removeId(partChargeLeaderIds, partChargeLeaderNames, node.id);
				}
				if($("#leader").prop("checked")){
					addId(leaderIds, leaderNames, node.id, node.text+"部门负责人");
				}else{
					removeId(leaderIds, leaderNames, node.id);
				}
				if($("#deputyLeader").prop("checked")){
					addId(deputyLeaderIds, deputyLeaderNames, node.id, node.text+"部门负责人");
				}else{
					removeId(deputyLeaderIds, deputyLeaderNames, node.id);
				}
				
				window.parent.actors.departmentChargeLeaderIds = arrToString(chargeLeaderIds);
				window.parent.actors.departmentChargeLeaderNames = arrToString(chargeLeaderNames);
				window.parent.actors.departmentPartChargeLeaderIds = arrToString(partChargeLeaderIds);
				window.parent.actors.departmentPartChargeLeaderNames = arrToString(partChargeLeaderNames);
				window.parent.actors.departmentLeaderIds = arrToString(leaderIds);
				window.parent.actors.departmentLeaderNames = arrToString(leaderNames);
				window.parent.actors.departmentDeputyLeaderIds = arrToString(deputyLeaderIds);
				window.parent.actors.departmentDeputyLeaderNames = arrToString(deputyLeaderNames);
				window.parent.setActors();
			}
			
		}

		var actors = null;
		var chargeLeaderIds = [];
		var chargeLeaderNames = [];
		var partChargeLeaderIds = [];
		var partChargeLeaderNames = [];
		var leaderIds = [];
		var leaderNames = [];
		var deputyLeaderIds = [];
		var deputyLeaderNames = [];
		function initDepartmentActors(){
			actors = window.parent.actors;
			if(actors.departmentChargeLeaderIds!=null && actors.departmentChargeLeaderIds!=""){
				chargeLeaderIds = actors.departmentChargeLeaderIds.split(";");
				chargeLeaderNames = actors.departmentChargeLeaderNames.split(";");
			}
			if(actors.departmentPartChargeLeaderIds!=null && actors.departmentPartChargeLeaderIds!=""){
				partChargeLeaderIds = actors.departmentPartChargeLeaderIds.split(";");
				partChargeLeaderNames = actors.departmentPartChargeLeaderNames.split(";");
			}
			if(actors.departmentLeaderIds != null && actors.departmentLeaderIds!=""){
				leaderIds = actors.departmentLeaderIds.split(";");
				leaderNames = actors.departmentLeaderNames.split(";");
			}
			if(actors.departmentDeputyLeaderIds != null && actors.departmentDeputyLeaderIds != ""){
				deputyLeaderIds = actors.departmentDeputyLeaderIds.split(";");
				deputyLeaderNames = actors.departmentDeputyLeaderNames.split(";");
			}
		}
		
				
		function setDepartmentActors(){
			var node = $("#ulTree").tree("getSelected");
			if(node != null && node.id != null && node.id != ""){
				var actors = window.parent.actors;
				
			}
		}
				
	</script>
	<div class="easyui-layout" style="overflow-y: hidden; width: 100%; height: 100%;" fit="true">
		<div style="padding: 10px" data-options="region:'west', border:true, width:300">
			<ul id="ulTree"></ul>
		</div>
		<div data-options="region:'center', border:false" style="padding:10px">
			<div style="padding:5px">
				<span id="spDepartment">未选择部门，请选选择部后再选择相应部门的角色！</span>
			</div>
			<table id="tbl" data-options="fit:true, border:false, singleSelect:false">
				<tr>
					<td>
						<input type="checkbox" id="chargeLeader"/><label for="chargeLeader">部门主管领导</label> 
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" id="partChargeLeader"/><label for="partChargeLeader">部门分管领导</label> 
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" id="leader"/><label for="leader">部门负责人</label> 
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" id="deputyLeader"/><label for="deputyLeader">部门副职</label> 
					</td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>