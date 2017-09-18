<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script>
	var asb = ActorSelectBox.create();
	asb.actors.userIds="<s:property value="userIds" />";
	asb.actors.userNames="<s:property value="userNames" />"; 
	asb.actors.roleIds="<s:property value="roleIds" />";
	asb.actors.roleNames="<s:property value="roleNames" />"; 
	asb.actors.positionIds="<s:property value="positionIds" />"; 
	asb.actors.positionNames="<s:property value="positionNames" />";
	asb.actors.activitySelectorIds="<s:property value="activityUserSelectorIds" />";
	asb.actors.activitySelectorNames="<s:property value="activityUserSelectorNames" />";
	
	asb.actors.departmentChargeLeaderIds="<s:property value="departmentChargeLeaderIds"/>";
	asb.actors.departmentPartChargeLeaderIds="<s:property value="departmentPartChargeLeaderIds"/>";
	asb.actors.departmentLeaderIds="<s:property value="departmentLeaderIds"/>";
	asb.actors.departmentDeputyLeaderIds="<s:property value="departmentDeputyLeaderIds"/>";
	asb.actors.departmentChargeLeaderNames="<s:property value="departmentChargeLeaderNames"/>";
	asb.actors.departmentPartChargeLeaderNames="<s:property value="departmentPartChargeLeaderNames"/>";
	asb.actors.departmentLeaderNames="<s:property value="departmentLeaderNames"/>";
	asb.actors.departmentDeputyLeaderNames="<s:property value="departmentDeputyLeaderNames"/>";
	
	asb.initConfig.selectActorUrl = "<s:url action="selectactor" namespace="/uac/user" />";
	asb.initConfig.isSelectDepartmentActor = true;
</script>
<table style="width: 100%" class="cleartable">
	<tr><td><s:textfield name="actorNames" id="_actorNames" cssClass="formtext" /></td>
		<td style="width: 20px; padding-left:5px">
		
		<input type="button" value="..." onclick="asb.selectActor();" /></td>
	</tr>
</table><s:hidden name="actorIds" id="_actorIds"/>