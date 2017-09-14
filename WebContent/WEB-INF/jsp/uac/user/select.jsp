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
			initGridChecked(window.parent.getSelectedUserNames, window.parent.getSelectedUsers);
			initGrid();
			initDepartmentTree();
			
			$("#query_name").keyup(refreshData);
			
		});

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
		
		function initGrid() {
	        $("#tbl").datagrid({
	            url: '<s:url action="listjson" namespace="/uac/user"/>',
	            success: function (data) {
	            },
	            onLoadSuccess: function (data) {
	                setGridChecked();
	            },
	            onLoadError: function () {
	            },
	            fit: true,
	            pagination: true,
	            pageSize: PAGE_SIZE,
	            singleSelect:isSingle,
	            onCheck: function (rowIndex, rowData) {
	                checkRow(rowIndex, rowData);
	                setParentValues(parent.setUsers);
	            },
	            onUncheck: function (rowIndex, rowData) {
	                uncheckRow(rowIndex, rowData);
	                setParentValues(parent.setUsers);
	            }
	        });
	    }
		
	</script>
	<div class="easyui-layout" style="overflow-y: hidden; width: 100%; height: 100%;" fit="true">
		<div style="padding: 10px" data-options="region:'west', border:true, width:160">
			<ul id="ulTree"></ul>
		</div>
		<div data-options="region:'center', border:false">
			<table >
				<tr>
					<td class="tdHeader">姓名|拼音首字：</td>
					<td class="tdContent"><input type="text" id="query_name" name="entityQuery.name" class="formtext" /></td>
					<td style="width:20px"><input type="button" value="查找" onclick="search()" /></td>
				</tr>
			</table>  
			<table id="tbl" data-options="fit:true, border:false, singleSelect:false">
				<thead>
					<tr>
						<th data-options="checkbox:true"></th>
						<th data-options="field:'name', width:100">姓名</th>
						<th data-options="field:'department', width:150">部门</th>
						<th data-options="field:'loginAccount', width:100">登录帐号</th>
						<th data-options="field:'email', width:150">邮箱</th>
						<th data-options="field:'gender', width:80, formatter:genderFormatter">性别</th>
						<th data-options="field:'bornDate', width:100">出生日期</th>
						<th data-options="field:'userState', width:80, formatter:stateFormatter">用户状态</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>