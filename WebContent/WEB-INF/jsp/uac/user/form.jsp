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
			initUserAccountValid();
			initForm();
			initDeptComboTree();
			$("#_name").focus();
			$("#_leaderName").attr("readonly", "readonly");
		});
		function initForm(){
			$("#frm").form({
                url: "<s:url action="save" namespace="/uac/user" />",
                onSubmit: function () {
                    var isValid = $(this).form('validate');
                    if (isValid) {
                        $.messager.progress();
                    }
                    return isValid;
                },
                success: function (data) {
                    $.messager.progress('close');
                    if(dealJsonResult(data, "用户保存成功！", function(){$("#_name").focus();})){
                    	clearForm();
                    	top.refreshContent();
                    }
                }
            });	 
			
			
			
			$.extend($.fn.validatebox.defaults.rules, {
                passwdValid: {
                    validator: function (value, param) {
                        return value == $("#" + param[0]).val();
                    },
                    message: '两次输入密码不一致，请修改!'
                }
            });
			
			
			$("#_name").validatebox({
				required:true,
				validType:'length[0, 30]',
				missingMessage:'用户姓名必填，请输入！',
				invalidMessage:'用户姓名长度不超过30个汉字！',
				deltaX:-200
			});
			
			$("#_loginAccount").validatebox({
				validType:'length[0, 30]',
				invalidMessage:'登录帐号长度不超过30个汉字！',
				deltaX:-200
			});
			
			$("#_loginAccount").validatebox({
				validType:"accountValid['_id']",
				invalidMessage:'已有此帐号的用户，请修改！'
			});
			
			$("#_loginPasswd").validatebox({
				validType:'length[0, 30]',
				invalidMessage:'登录密码长度不超过30个字符！',
				deltaX:-200
			});
			
			$("#_loginPasswdConfirm").validatebox({
                validType: 'passwdValid["_loginPasswd"]',
                invalidMessage: "两次输入密码不一致，请修改!",
                deltaX: -200
            });
			
			$("#_loginPasswd").validatebox({
                validType: 'passwdValid["_loginPasswdConfirm"]',
                invalidMessage: "两次输入密码不一致，请修改!",
                deltaX: -200
            });
			
			$("#_email").validatebox({
				validType:'length[0, 250]',
				invalidMessage:'Email长度不超过250个汉字！',
				deltaX:-200
			});
			
			$("#_contact").validatebox({
				validType:'length[0, 200]',
				invalidMessage:'联系长度不超过200个汉字！',
				deltaX:-200
			});
			
			
			$("#_description").validatebox({
				validType:'length[0, 500]',
				invalidMessage:'备注说明长度不超过500个汉字！',
				deltaX:-200
			});
		}
		
		
		function initUserAccountValid(){
			$.extend($.fn.validatebox.defaults.rules, {
                accountValid: {
                    validator: function (value, param) {
                        var id = $("#" + param[0]).val();
                        var result = false;
                        $.ajax({
                            url: '<s:url action="validateaccount" namespace="/uac/user" />?id=' 
                            		+ id
                            		+ "&entity.loginAccount=" 
                            		+ encodeURI(encodeURI(value))
                            		+ "&rnd=" + Math.random()
                            		,
                            type: 'get',
                            timeout: 1000,
                            success: function (data) {
                                result = (data=="true");
                            },
                            error: function () {
                                result = false;
                            },
                            async:false
                        });
                        //$("#_description").val(result);
                        return result;
                    },
                    message: '用户帐号已存在，请修改！'
                }
            });
		}
		
		
		var treeLoaded = false;
		function initDeptComboTree() {
			$("#sltParentId").combotree({
				url : "<s:url action="listjson" namespace="/uac/department"/>",
				loadFilter : function(rows) {
					return convert(rows, "部门");
				},
				onLoadError : function(arguments) {
					$.messager.alert("部门", arguments);
				},
				onLoadSuccess : function(node, data) {
					var selectedId = $('#_departmentId').val();
					setTreeNode("sltParentId", selectedId);
					treeLoaded = true;
				},
				editable : false,
				width : 350,
				panelHeight : 180,
				onClick:function(node){$("#_departmentId").val(node.id);}
			});
			hasInit = true;
		}
		
		
		function clearForm(){
			var departmentId = $("#_departmentId").val();
			$("#frm").form("clear");
			$("#frm_entity_gendertrue").attr("checked", "checked");
			$("#frm_entity_userStateNormal").attr("checked", "checked");
			$("#_departmentId").val(departmentId);
			setTreeNode("sltParentId", departmentId);
		}
		
		function initPositionCombobox(){
			var positionId = "<s:property value="entity.position.id" />";
			$("#sltPositionId").combobox("select", positionId);
		}
		
		function initPositionGradeCombobox(){
			var positionGradeId = "<s:property value="entity.positionGrade.id" />";
			$("#sltPositionGradeId").combobox("select", positionGradeId);
		}
		
		var asb;
		var leaderAsb = ActorSelectBox.create();
		leaderAsb.actors.userIds="<s:property value="userLeaderIds" />"; 
		leaderAsb.actors.userNames="<s:property value="userLeaderNames" />"; 
		leaderAsb.initConfig.actorIdsCtl = "_leaderId";
		leaderAsb.initConfig.actorNamesCtl="_leaderName";
		leaderAsb.initConfig.isSelectRole=false;
		leaderAsb.initConfig.isSelectPosition=false;
		leaderAsb.initConfig.isSelectActivityUserSelector=false;
		leaderAsb.initConfig.isSingleSelect=false;
		
		leaderAsb.initConfig.selectActorUrl = "<s:url action="selectactor" namespace="/uac/user" />";
		
		var deputyLeaderAsb = ActorSelectBox.create();
		deputyLeaderAsb.actors.userIds="<s:property value="userDeputyLeaderIds" />"; 
		deputyLeaderAsb.actors.userNames="<s:property value="userDeputyLeaderNames" />"; 
		deputyLeaderAsb.initConfig.actorIdsCtl = "_deputyLeaderIds";
		deputyLeaderAsb.initConfig.actorNamesCtl="_deputyLeaderNames";
		deputyLeaderAsb.initConfig.isSelectRole=false;
		deputyLeaderAsb.initConfig.isSelectPosition=false;
		deputyLeaderAsb.initConfig.isSelectActivityUserSelector=false;
		deputyLeaderAsb.initConfig.isSingleSelect=false;
		
		deputyLeaderAsb.initConfig.selectActorUrl = "<s:url action="selectactor" namespace="/uac/user" />";
		
		var relaUserAsb = ActorSelectBox.create();
		relaUserAsb.actors.userIds="<s:property value="relaUserIds" />"; 
		relaUserAsb.actors.userNames="<s:property value="relaUserNames" />"; 
		relaUserAsb.initConfig.actorIdsCtl = "_relaUserIds";
		relaUserAsb.initConfig.actorNamesCtl="_relaUserNames";
		relaUserAsb.initConfig.isSelectRole=false;
		relaUserAsb.initConfig.isSelectPosition=false;
		relaUserAsb.initConfig.isSelectActivityUserSelector=false;
		relaUserAsb.initConfig.isSingleSelect=false;
		
		relaUserAsb.initConfig.selectActorUrl = "<s:url action="selectactor" namespace="/uac/user" />";
	</script>
	<s:form id="frm" method="post">
	<div class="formdiv">
			<table class="formtable">
				<tr>
					<td class="tdHeader" style="width:180px">姓名<label class="required">*</label>：</td>
					<td class="tdContent"><s:textfield id="_name" name="entity.name" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">部门：</td>
					<td class="tdContent"><select id="sltParentId"
						class="formselect"></select>
				</tr>
				<tr>
					<td class="tdHeader">登录帐号：</td>
					<td class="tdContent"><s:textfield id="_loginAccount" name="entity.loginAccount" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">登录密码：</td>
					<td class="tdContent"><s:password id="_loginPasswd" name="entity.loginPasswd" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">密码确认：</td>
					<td class="tdContent"><s:password id="_loginPasswdConfirm" name="loginPasswdConfirm" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">性别：</td>
					<td class="tdContent">
					<s:radio list="%{#{true:'男',false:'女' }}" name="entity.gender"></s:radio>
					</td>
				</tr>
				<tr>
					<td class="tdHeader">Email：</td>
					<td class="tdContent"><s:textfield id="_email" name="entity.email" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">联系方式：</td>
					<td class="tdContent"><s:textfield id="_contact" name="entity.contact" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">出生日期：</td>
					<td class="tdContent"><s:textfield id="_bornDate" name="entity.bornDate" cssClass="easyui-datebox" />
					</td>
				</tr>
				<tr>
					<td class="tdHeader">登录IP：</td>
					<td class="tdContent"><s:textfield id="_ip" name="entity.defaultLoginIp" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">显示顺序：</td>
					<td class="tdContent"><s:textfield id="_displayOrder" name="entity.displayOrder" cssClass="formtext"/></td>
				</tr>
				<tr>
					<td class="tdHeader">状态：</td>
					<td class="tdContent">
					<s:radio list="%{#{'Normal':'正常','Locked':'锁定' }}" name="entity.userState"></s:radio>
					</td>
				</tr>
				<tr>
					<td class="tdHeader">备注：</td>
					<td class="tdContent"><s:textarea id="_description" name="entity.description" cssClass="formtextarea"/></td>
				</tr>
			</table>
			<s:hidden id="_entityId" name="entity.id" />
			<s:hidden id="_departmentId" name="entity.department.id" />
			<s:hidden id="_position" name="entity.position.id" />
			<s:hidden id="_positionGrade" name="entity.positionGrade.id" />
			<s:hidden id="_id" name="id" />
	</div>
	<div class="buttonbox">
		<input type="submit" value="保存" /> 
		<input type="button" value="取消" onclick="top.closeDialog()" />
	</div>
	
	</s:form>
</body>
</html>