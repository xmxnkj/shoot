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
	<script src="<s:url value="/content/scripts/Uace/actorSelectBox.js" />"></script>
	<script>
		var roleId="<s:property value="entityQuery.roleId"/>";
		$(function() {
			$('#tbl').datagrid({
				fit : true,
				url : '<s:url action="listjson" namespace="/uac/user"/>?entityQuery.roleId=<s:property value="entityQuery.roleId"/>',
				singleSelect : false,
				pagination: true,
				pageSize: PAGE_SIZE
			});
		});
		
		
		var userUrl = "<s:url action="select" namespace="/uac/user" />";
		var asb = ActorSelectBox.create();
		asb.actors.userIds="";
		asb.actors.userNames="";
		asb.initConfig.selectActorUrl = "<s:url action="selectactor" namespace="/uac/user" />";
		asb.initConfig.isSelectRole = false;
		asb.initConfig.isSelectPosition = false;
		asb.initConfig.isSelectActivityUserSelector = false;
		asb.initConfig.dialogCaption="选择角色用户";
		asb.actorSelectedCallback = userSelected;
		function add() {
			asb.selectActor();
		}
		
		function userSelected(actors){
			var data = "roleId=" + roleId + "&userIds=" + actors.userIds;
			$.ajax({url : '<s:url action="addroleusersjson" namespace="/uac/role" />',
				type : 'post',
				data:data,
				timeout : TIMEOUT,
				success : function(data) {
					if (dealJsonResult(
							data, "添加用户成功！")) {
						refreshData();
					}
				},
				error : function() {
					$.messager.alert(WINDOW_CAPTION,
									'网络原因导致用户添加失败！',
									'error');
				}
			});
			
			asb.clear();
		}

		function getIds(){
			var ids = "";
			var rows = $("#tbl").datagrid("getSelections");
			if (rows != null && rows.length>0) {
				for(var i=0;i<rows.length; i++){
					if(ids!="")
						ids+=";";
					ids+=rows[i].id;
				}
			}
			return ids;
		}
		
		function del() {
			//var row = $('#tbl').datagrid('getSelected');
			var rows = $("#tbl").datagrid("getSelections");
			if (rows != null && rows.length>0) {
				$.messager.confirm('提示信息',
								'您确认要移除角色用户吗？',
								function(data) {
									var postData = "roleId="+roleId+"&userIds="+getIds();
									if (data) {$.ajax({url : '<s:url action="deleteroleusersjson" namespace="/uac/role" />',
													type : 'post',
													data:postData,
													timeout : TIMEOUT,
													success : function(data) {
														if (dealJsonResult(
																data, "角色用户移除成功！")) {
															refreshData();
														}
													},
													error : function() {
														$.messager.alert(WINDOW_CAPTION,
																		'网络原因导致用户移除失败！',
																		'error');
													}
												});
									}
								});
			}
		}

		function refreshData() {
			var params = {};
			
			$("#tbl").datagrid("reload", params);
		}

		function stateFormatter(value) {
			return value == "0" ? "正常" : "禁用";
		}

		function genderFormatter(value) {
			return value ? "男" : "女";
		}
				
	</script>
	<div class="easyui-layout" style="overflow-y: hidden; width: 100%; height: 100%;" fit="true">
		<div region="north" style="height: 33px" border="false">
			<div class="menu">
				<a id="btn" href="#" onclick="add(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加</a> 
				<a id="btn" href="#" onclick="del(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-remove'>删除</a>
			</div>
		</div>
		<div data-options="region:'center', border:false">
			<table id="tbl" data-options="fit:true, border:false">
				<thead>
					<tr>
						<th data-options="checkbox:true"></th>
						<th data-options="field:'name', width:100">姓名</th>
						<th data-options="field:'department', width:150">部门</th>
						<th data-options="field:'loginAccount', width:100">登录帐号</th>
						<th data-options="field:'email', width:150">邮箱</th>
						<th
							data-options="field:'gender', width:80, formatter:genderFormatter">性别</th>
						<th data-options="field:'bornDate', width:100">出生日期</th>
						<th
							data-options="field:'userState', width:80, formatter:stateFormatter">用户状态</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>