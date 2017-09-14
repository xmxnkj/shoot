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
		<script type="text/javascript">
		var spliter = ";";
		
		var actors = {};

		var isRoleLoaded = false;
		var isUserLoaded = false;
		var isActivitySelectorLoaded = false;
		var isDepartmentActorLoaded = false;
		var isSingleSelect = false;
		var hasLoaded = false;
		
		
		var roleUrl = "<s:url action="select" namespace="/uac/role" />";
		var userUrl = "<s:url action="select" namespace="/uac/user" />";
		var positionUrl = "<s:url action="select" namespace="/uac/position" />";
		var activitySelectorUrl = "<s:url action="select" namespace="/workflow/activityuserselector" />";
		var departmentActorUrl = "<s:url action="selectdepartmentactor" namespace="/uac/department" />";
		$(function() {
			var asb = top.extData;
			if(asb==null){
				if (typeof (top.$("#ifrDlg")[0].contentWindow.asb) == "object") {
					asb = top.$("#ifrDlg")[0].contentWindow.asb;
				}else if(typeof(top.$("#divFrame")[0].contentWindow.asb)=="object"){
					asb = top.$("#divFrame")[0].contentWindow.asb;
				}
			}
			if(asb != null){
				actors = asb.actors;
				isSingleSelect = asb.initConfig.isSingleSelect;
				setActors();
				if(asb.initConfig.isSelectPosition)
					$("#ifrPosition").attr("src", positionUrl);
				else if(asb.initConfig.isSelectUser){
					$("#ifrUser").attr("src", userUrl);
				}
			}
			
			
			hasLoaded = true;
		});

		function switchTabs(title, index) {
			if(hasLoaded){
				if (index == 1 && !isRoleLoaded) {
					$("#ifrRole").attr("src", roleUrl);
					isRoleLoaded = true;
				} else if (index == 2 && !isUserLoaded) {
					$("#ifrUser").attr("src", userUrl);
					isUserLoaded = true;
				} else if(index == 3 && !isActivitySelectorLoaded){
					$("#ifrActivitySelector").attr("src", activitySelectorUrl);
					isActivitySelectorLoaded = true;
				}else if(index == 4 && !isDepartmentActorLoaded){
					$("#ifrDepartmentActor").attr("src", departmentActorUrl);
					isDepartmentActorLoaded=true;
				}
			}
		}

		function confirm() {
			if (typeof (top.$("#ifrDlg")[0].contentWindow.asb) == "object") {
				top.$("#ifrDlg")[0].contentWindow.asb.actorSelected(actors);
			}else if (typeof (top.$("#ifrDlg2")[0].contentWindow.asb) == "object") {
				top.$("#ifrDlg2")[0].contentWindow.asb.actorSelected(actors);
			}else if (top.$("#divFrame").length>0 && typeof(top.$("#divFrame")[0].contentWindow)=='object' && typeof(top.$("#divFrame")[0].contentWindow.asb)=="object"){
				top.$("#divFrame")[0].contentWindow.asb.actorSelected(actors);
			}else if (typeof(top.asb)=='object'){
				top.asb.actorSelected(actors);
			}
			top.closeDialog2();
		}

		function setActors() {
			var actorIds = actors.positionIds, actorNames = actors.positionNames;
			if (actorIds == "") {
				actorIds = actors.roleIds;
				actorNames = actors.roleNames;
			} else {
				actorIds += (actors.roleIds == "" ? "" : spliter + actors.roleIds);
				actorNames += (actors.roleNames == "" ? "" : spliter + actors.roleNames);
			}

			if (actorIds == "") {
				actorIds = actors.userIds;
				actorNames = actors.userNames;
			} else {
				actorIds += (actors.userIds == "" ? "" : spliter + actors.userIds);
				actorNames += (actors.userNames == "" ? "" : spliter + actors.userNames);
			}
			
			actorIds += (actorIds != "" && actors.activitySelectorIds != "") ? spliter + actors.activitySelectorIds : actors.activitySelectorIds;
			actorNames += (actorNames != "" && actors.activitySelectorNames != "") ? spliter + actors.activitySelectorNames : actors.activitySelectorNames;
			
			actorIds += (actorIds != "" && actors.departmentChargeLeaderIds != "") ? spliter + actors.departmentChargeLeaderIds : actors.departmentChargeLeaderIds;
			actorNames += (actorNames != "" && actors.departmentChargeLeaderNames != "") ? spliter + actors.departmentChargeLeaderNames : actors.departmentChargeLeaderNames;
			actorIds += (actorIds != "" && actors.departmentPartChargeLeaderIds != "") ? spliter + actors.departmentPartChargeLeaderIds : actors.departmentPartChargeLeaderIds;
			actorNames += (actorNames != "" && actors.departmentPartChargeLeaderNames != "") ? spliter + actors.departmentPartChargeLeaderNames : actors.departmentPartChargeLeaderNames;
			actorIds += (actorIds != "" && actors.departmentLeaderIds != "") ? spliter + actors.departmentLeaderIds : actors.departmentLeaderIds;
			actorNames += (actorNames != "" && actors.departmentLeaderNames != "") ? spliter + actors.departmentLeaderNames : actors.departmentLeaderNames;
			actorIds += (actorIds != "" && actors.departmentDeputyLeaderIds != "") ? spliter + actors.departmentDeputyLeaderIds : actors.departmentDeputyLeaderIds;
			actorNames += (actorNames != "" && actors.departmentDeputyLeaderNames != "") ? spliter + actors.departmentDeputyLeaderNames : actors.departmentDeputyLeaderNames;
			
			$("#selectedActor").val(actorNames);
		}

		function setUsers(ids, names) {
			actors.userIds = ids;
			actors.userNames = names;
			setActors();
		}

		function getSelectedUsers() {
			return actors.userIds;
		}

		function getSelectedUserNames() {
			return actors.userNames;
		}

		function setPositions(ids, names) {
			actors.positionIds = ids;
			actors.positionNames = names;
			setActors();
		}

		function getSelectedPositions() {
			return actors.positionIds;
		}

		function getSelectedPositionNames() {
			return actors.positionNames;
		}

		function setRoles(ids, names) {
			actors.roleIds = ids;
			actors.roleNames = names;
			setActors();
		}

		function getSelectedRoles() {
			return actors.roleIds;
		}

		function getSelectedRoleNames() {
			return actors.roleNames;
		}
		
		function setActivitySelectors(ids, names){
			actors.activitySelectorIds = ids;
			actors.activitySelectorNames = names;
			setActors();
		}
		
		function getSelectedActivitySelectors(){
			return actors.activitySelectorIds;
		}
		
		function getSelectedActivitySelectorNames(){
			return actors.activitySelectorNames;
		}
	</script>
	<div class="easyui-layout" fit="true">
		<div data-options="region:'center', border:false">
			<div class="easyui-tabs"
				data-options="fit:true, border:false, onSelect:switchTabs">
				<s:set var="isFirstSelectUser" value="true"/> 
				<s:if test="isSelectPosition.toString()=='true'.toString()">
				<s:set var="isFirstSelectUser" value="false"/>
				<div title="选择职务"><iframe id="ifrPosition" style="width: 100%; height: 100%"
						frameborder="0"
						src=""></iframe></div>
				</s:if>
				<s:if test="isSelectRole.toString()=='true'.toString()">
				<s:set var="isFirstSelectUser" value="false"/>
				<div title="选择角色"><iframe id="ifrRole" style="width: 100%; height: 100%"
						frameborder="0" src=""></iframe></div>
				</s:if>
				<s:if test="isSelectUser.toString()=='true'.toString()">
				<s:if test="#isFirstSelectUser.toString()=='true'.toString()">
				<div title="选择用户"><iframe id="ifrUser" style="width: 100%; height: 100%"
						frameborder="0" src=""></iframe></div>
				</s:if><s:else>
				<div title="选择用户"><iframe id="ifrUser" style="width: 100%; height: 100%"
						frameborder="0" src=""></iframe></div>
						</s:else>
				</s:if>
				<s:if test="isSelectActivityUserSelector.toString()=='true'.toString()">
				<div title="业务关联用户"><iframe id="ifrActivitySelector" style="width: 100%; height: 100%"
						frameborder="0" src=""></iframe></div>
				</s:if>
				<s:if test="isSelectDepartmentActor.toString()=='true'.toString()">
				<div title="部门角色"><iframe id="ifrDepartmentActor" style="width: 100%; height: 100%"
						frameborder="0" src=""></iframe></div>
				</s:if>
			</div>
		</div>
		<div data-options="region:'south', height:35">
			<table style="width: 99%">
				<tr>
					<td style="width: 75px; text-align: right">已选审批人：</td>
					<td><input type="text" id="selectedActor" class="formtext" /></td>
					<td style="width: 110px; padding-left: 5px"><input
						type="button" value="确定" onclick="confirm();" />&nbsp;<input type="button" value="取消" onclick="top.closeDialog2();" /></td>
				</tr>
			</table>
		</div>
	</div>

</body>
</html>