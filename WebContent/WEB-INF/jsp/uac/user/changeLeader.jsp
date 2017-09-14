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
	<script src="<s:url value="/content/scripts/Uace/actorSelectBox.js"/>"></script>
	<script>
		$(function(){
			initForm();
			$("#_leaderName").attr("readonly", "readonly");
		});
		function initForm(){
			$("#frm").form({
                url: "<s:url action="changeleaderjson" namespace="/uac/user" />",
                onSubmit: function () {
                	var isValid = validate();
                    if (isValid) {
                        $.messager.progress();
                    }
                    return isValid;
                },
                success: function (data) {
                    $.messager.progress('close');
                    if(dealJsonResult(data, "直接领导更改成功！", function(){$("#_name").focus();})){
                    	top.refreshContent();
                    	top.closeDialog();
                    }
                }
            });	 
		}

		function validate(){
			var leaderId = $("#_leaderId").val();
			if (leaderId == null || leaderId==""){
				top.$.messager.alert(WINDOW_CAPTION, "请选择新领导！");
				return false;
			}
			return true;
		}
		
		
		var asb;
		var leaderAsb = ActorSelectBox.create();
		leaderAsb.actors.userIds=""; 
		leaderAsb.actors.userNames=""; 
		leaderAsb.initConfig.actorIdsCtl = "_leaderId";
		leaderAsb.initConfig.actorNamesCtl="_leaderName";
		leaderAsb.initConfig.isSelectRole=false;
		leaderAsb.initConfig.isSelectPosition=false;
		leaderAsb.initConfig.isSelectActivityUserSelector=false;
		leaderAsb.initConfig.isSingleSelect=false;
		
		leaderAsb.initConfig.selectActorUrl = "<s:url action="selectactor" namespace="/uac/user" />";
	</script>
	<s:form id="frm" method="post">
	<div class="formdiv">
			<table class="formtable">
				<tr>
					<td class="tdHeader" style="width:180px">原领导：</td>
					<td class="tdContent"><s:property value="entity.name"/> </td>
				</tr>
				<tr>
					<td class="tdHeader">现领导:</td>
					<td class="tdContent"><table style="width: 100%"
							class="cleartable">
							<tr>
								<td><s:textfield name="userLeaderNames" id="_leaderName"
										cssClass="formtext" /></td>
								<td style="width: 20px; padding-left: 5px"><input
									type="button" value="..." onclick="asb=leaderAsb; asb.selectActor();" /></td>
							</tr>
						</table>
						<%-- <s:hidden name="entity.leader.id" id="_leaderId" /> --%>
						<s:hidden name="userLeaderIds" id="_leaderId" />
						</td>
				</tr>
			</table>
			<s:hidden id="_entityId" name="entity.id" />
			<s:hidden id="_id" name="id" />
	</div>
	<div class="buttonbox">
		<input type="submit" value="更改" /> 
		<input type="button" value="取消" onclick="top.closeDialog()" />
	</div>
	
	</s:form>
</body>
</html>