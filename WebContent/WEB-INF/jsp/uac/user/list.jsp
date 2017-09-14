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
	<script>
		$(function() {
			$('#tbl').datagrid({
				fit : true,
				url : '<s:url action="listjson" namespace="/uac/user"/>',
				onDblClickRow : edit,
				singleSelect : true,
				pagination: true,
				pageSize: PAGE_SIZE
			});
			
			initDepartmentTree();
		});
		function add() {
			top.openDialog('<s:url action="edit" namespace="/uac/user" />',
					"添加用户", 500, 540);
		}

		function edit() {
			var row = $('#tbl').datagrid('getSelected');
			if (row != null) {
				url = '<s:url action="edit" namespace="/uac/user" />?id='
						+ row.id;
				top.openDialog(url, "修改用户信息", 500, 540);
			} else {
				$.messager.alert('提示', '请选择一个用户进行修改！');
			}
		}

		function del() {
			var row = $('#tbl').datagrid('getSelected');
			if (row != null) {
				$.messager
						.confirm(
								'提示信息',
								'您确认要删除用户吗？',
								function(data) {
									if (data) {$.ajax({
													url : '<s:url action="delete" namespace="/uac/user" />?id='
															+ row.id + "&rnd=" + Math.random(),
													type : 'get',
													timeout : TIMEOUT,
													success : function(data) {
														if (dealJsonResult(
																data, "用户删除成功！")) {
															$('#tbl').datagrid(
																	'reload');
														}
													},
													error : function() {
														$.messager
																.alert(
																		WINDOW_CAPTION,
																		'网络原因导致用户删除失败！',
																		'error');
													}
												});
									}
								});
			}
		}

		function refreshData(departmentId) {
			var node = $("#ulTree").tree("getSelected");
			
			var params = {};
			params["entityQuery.nameOrPinYin"] = $("#query_name").val();
			if(node != null){
				params["entityQuery.departmentId"] = node.id;
			}
			params["entityQuery.departmentName"] = $("#query_departmentName").val();
			
			$("#tbl").datagrid("reload", params);
		}

		function stateFormatter(value) {
			return value == "0" ? "正常" : "禁用";
		}

		function genderFormatter(value) {
			return value ? "男" : "女";
		}
		
		function initDepartmentTree(){
			$("#ulTree").tree({
	            url: "<s:url action="listjson" namespace="/uac/department"/>",
	            loadFilter: function (rows) {
	                return convert(rows, "部门");
	            },
	            onClick:function(node){
	            	refreshData(node.id);
	            }
	        });
		}
		
		function setAce(){
			 var row = $('#tbl').datagrid('getSelected');
			if(row != null){
			 	top.openDialog("<s:url action="select" namespace="/uac/operationcategory" />?actorType=User&actorId=" + row.id, "设置权限", 500, 600);
			}else{
				top.$.messager.alert(WINDOW_CAPTION, "请选择一个用户进行设置权限！", "error");
			}
		 }
		
		function openSearch(){
			$("#dlg").dialog("open");
		}
		
		function search(){
			refreshData();
			$("#dlg").dialog("close");
		}
		
		function closeSearch(){
			$("#dlg").dialog("close");
		}
		
		function changeLeader(){
			var row = $('#tbl').datagrid('getSelected');
			if (row != null) { 
				top.openDialog("<s:url action="showchangeleader" namespace="/uac/user"/>?entity.id="+row.id, "更改直接领导", 500, 400);
			}else{
				top.$.messager.alert(WINDOW_CAPTION, "请选择一个直接领导进行更改！")
			}
		}
	</script>
	<div class="easyui-layout" style="overflow-y: hidden; width: 100%; height: 100%;" fit="true">
		<div region="north" style="height: 33px" border="false">
			<div class="menu">
				<a id="btn" href="#" onclick="add(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加</a> 
				<a id="btn" href="#" onclick="edit(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-edit'>修改</a> 
				<a id="btn" href="#" onclick="del(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-remove'>删除</a>
				<a id="btn" href="#" onclick="openSearch(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-search'>查找</a>
				
			</div>
		</div>
		<div style="padding: 10px" data-options="region:'west', border:true, width:300">
			<ul id="ulTree"></ul>
		</div>
		<div data-options="region:'center', border:false">
			<table id="tbl" data-options="fit:true, border:false">
				<thead>
					<tr>
						<th data-options="field:'name', width:100">姓名</th>
						<th data-options="field:'department'">部门</th>
						<th data-options="field:'loginAccount', width:100">登录帐号</th>
						<th data-options="field:'userState', width:80, formatter:stateFormatter">用户状态</th>
					</tr>
				</thead>
			</table>
		</div>
		<div id="dlg" class="easyui-dialog" closed="true" modal="true" title="窗口" style="width: 400px; height: 180px; overflow: visible; padding:10px">
        	<table class="formtable">
				<tr>
					<td class="tdHeader">姓名：</td>
					<td class="tdContent"><input type="text" id="query_name" name="entityQuery.name" class="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">部门：</td>
					<td class="tdContent"><input type="text" id="query_departmentName" name="entityQuery.departmentName" class="formtext" /></td>
				</tr>
			</table>    
			<div class="buttonbox">
				<input type="button" value="查找" onclick="search()" />&nbsp;&nbsp;<input type="button" value="取消" onclick="closeSearch();" />
			</div>
    	</div>
	</div>
</body>
</html>