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
		$(function() {
			initForm();
			initDeptComboTree();
			initDepartmentNameValid();
			$("#_name").focus();
			
			$("#_chargeLeaderName").attr("readonly", "readonly");
			$("#_partChargeLeaderName").attr("readonly", "readonly");
			$("#_deputyLeaderName").attr("readonly", "readonly");
			$("#_leaderName").attr("readonly", "readonly");
		});

		function initDepartmentNameValid() {
			$.extend($.fn.validatebox.defaults.rules,
							{nameValid : {
									validator : function(value, param) {
										var id = $("#" + param[0]).val();
										var parentId = $("#" + param[1]).val();
										var result = false;
										$.ajax({
													url : '<s:url action="validatename" namespace="/uac/department" />?id='
															+ id
															+ "&entity.parentId="
															+ parentId
															+ "&entity.name="
															+ encodeURI(encodeURI(value))
															+ "&rnd="
															+ Math.random(),
													type : 'get',
													timeout : 1000,
													success : function(data) {
														result = (data == "true");
													},
													error : function() {
														result = false;
													},
													async : false
												});
										//$("#_description").val(result);
										return result;
									},
									message : '部门已存在，请修改名称！'
								}
							});
		}

		function initForm() {
			$("#frm").form({
				url : "<s:url action="save" namespace="/uac/department" />",
				onSubmit : function() {
					var isValid = $(this).form('validate');
					if (isValid) {
						$.messager.progress();
					}
					return isValid;
				},
				success : function(data) {
					$.messager.progress('close');
					if (dealJsonResult(data, "部门保存成功！", function() {
						$("#_name").focus();
					})) {
						clearForm();
						top.refreshContent();
						reloadDepartrmentTree();
					}
				}
			});

			$("#_name").validatebox({
				required : true,
				validType : 'length[0, 100]',
				missingMessage : '部门名称必填，请输入！',
				invalidMessage : '部门名称长度不超过100个汉字！',
				deltaX : -200
			});
			$("#_name").validatebox({
				validType : "nameValid['_id', '_parentId']",
				deltaX : -200,
				invalidMessage : "部门已存在，请修改名称！"
			});

			$("#_description").validatebox({
				validType : 'length[0, 500]',
				invalidMessage : '备注说明长度不超过500个汉字！',
				deltaX : -200
			});
		}

		function reloadDepartrmentTree() {
			$("#sltParentId").combotree("reload");
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
					var selectedId = $('#_parentId').val();
					setTreeNode("sltParentId", selectedId);
					treeLoaded = true;
				},
				editable : false,
				width : 350,
				panelHeight : 180,
				onClick : function(node) {
					$("#_parentId").val(node.id);
				}
			});
			
			$("#sltManageId").combotree({
				url : "<s:url action="listjson" namespace="/uac/department"/>",
				loadFilter : function(rows) {
					return convert(rows, "部门");
				},
				onLoadError : function(arguments) {
					$.messager.alert("部门", arguments);
				},
				onLoadSuccess : function(node, data) {
					var selectedId = $('#_manageId').val();
					setTreeNode("sltManageId", selectedId);
					treeLoaded = true;
				},
				editable : false,
				width : 350,
				panelHeight : 180,
				onClick : function(node) {
					$("#_manageId").val(node.id);
				}
			});
			hasInit = true;
		}

		function clearForm() {
			var selectedId = $('#_parentId').val();
			$("#frm").form("clear");
			setTreeNode("sltParentId", selectedId);
			$('#_parentId').val(selectedId);
		}

		var asb = null;

		var leaderAsb = ActorSelectBox.create();
		leaderAsb.actors.userIds="<s:property value="entity.leader.id" />"; 
		leaderAsb.actors.userNames="<s:property value="entity.leader.name" />"; 
		leaderAsb.initConfig.actorIdsCtl = "_leaderId";
		leaderAsb.initConfig.actorNamesCtl="_leaderName";
		leaderAsb.initConfig.isSelectRole=false;
		leaderAsb.initConfig.isSelectPosition=false;
		leaderAsb.initConfig.isSelectActivityUserSelector=false;
		leaderAsb.initConfig.isSingleSelect=true;
		
		leaderAsb.initConfig.selectActorUrl = "<s:url action="selectactor" namespace="/uac/user" />";
		
		
		var chargeLeaderAsb = ActorSelectBox.create();
		chargeLeaderAsb.actors.userIds="<s:property value="entity.chargeLeader.id" />"; 
		chargeLeaderAsb.actors.userNames="<s:property value="entity.chargeLeader.name" />"; 
		chargeLeaderAsb.initConfig.actorIdsCtl = "_chargeLeaderId";
		chargeLeaderAsb.initConfig.actorNamesCtl="_chargeLeaderName";
		chargeLeaderAsb.initConfig.isSelectRole=false;
		chargeLeaderAsb.initConfig.isSelectPosition=false;
		chargeLeaderAsb.initConfig.isSelectActivityUserSelector=false;
		chargeLeaderAsb.initConfig.isSingleSelect=true;
		
		chargeLeaderAsb.initConfig.selectActorUrl = "<s:url action="selectactor" namespace="/uac/user" />";
		
		var partChargeLeaderAsb = ActorSelectBox.create();
		partChargeLeaderAsb.actors.userIds="<s:property value="partChargeLeaderIds" />"; 
		partChargeLeaderAsb.actors.userNames="<s:property value="partChargeLeaderNames" />"; 
		partChargeLeaderAsb.initConfig.actorIdsCtl = "_partChargeLeaderId";
		partChargeLeaderAsb.initConfig.actorNamesCtl="_partChargeLeaderName";
		partChargeLeaderAsb.initConfig.isSelectRole=false;
		partChargeLeaderAsb.initConfig.isSelectPosition=false;
		partChargeLeaderAsb.initConfig.isSelectActivityUserSelector=false;
		partChargeLeaderAsb.initConfig.isSingleSelect=false;
		
		partChargeLeaderAsb.initConfig.selectActorUrl = "<s:url action="selectactor" namespace="/uac/user" />";
		
		var deputyLeaderAsb = ActorSelectBox.create();
		deputyLeaderAsb.actors.userIds="<s:property value="deputyLeaderIds" />"; 
		deputyLeaderAsb.actors.userNames="<s:property value="deputyLeaderNames" />"; 
		deputyLeaderAsb.initConfig.actorIdsCtl = "_deputyLeaderId";
		deputyLeaderAsb.initConfig.actorNamesCtl="_deputyLeaderName";
		deputyLeaderAsb.initConfig.isSelectRole=false;
		deputyLeaderAsb.initConfig.isSelectPosition=false;
		deputyLeaderAsb.initConfig.isSelectActivityUserSelector=false;
		deputyLeaderAsb.initConfig.isSingleSelect=false;
		
		deputyLeaderAsb.initConfig.selectActorUrl = "<s:url action="selectactor" namespace="/uac/user" />";
	</script>
	<s:form id="frm" method="post">
		<div class="formdiv">
			<table class="formtable">
				<tr>
					<td class="tdHeader">部门名称<label class="required">*</label>:
					</td>
					<td class="tdContent"><s:textfield id="_name"
							name="entity.name" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">上级部门:</td>
					<td class="tdContent"><select id="sltParentId"
						class="formselect"></select>
						<s:hidden name="entity.manageDepartmentId" id="_manageId" />
				</tr>
				<tr>
					<td class="tdHeader">显示顺序:</td>
					<td class="tdContent"><s:textfield id="_displayOrder"
							name="entity.displayOrder" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">备注:</td>
					<td class="tdContent"><s:textarea id="_description"
							name="entity.description" cssClass="formtextarea" /></td>
				</tr>
			</table>
			<s:hidden name="entity.id" />
			<s:hidden name="entity.parentId" id="_parentId" />
			<s:hidden id="_id" name="id" />
		</div>
		<div class="buttonbox">
			<input type="submit" value="保存" /> <input type="button" value="取消"
				onclick="top.closeDialog()" />
		</div>
	</s:form>
</body>
</html>