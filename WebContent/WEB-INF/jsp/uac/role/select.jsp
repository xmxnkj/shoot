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
		 $(function(){
			 initGridChecked(window.parent.getSelectedRoleNames, window.parent.getSelectedRoles);
				initGrid();
			
		}); 
		
		function refreshData(){
			$("#tbl").datagrid("reload");
		}
		
		
		function initGrid() {
	        $("#tbl").datagrid({
	            url: '<s:url action="listjson" namespace="/uac/role"/>',
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
	                setParentValues(parent.setRoles);
	            },
	            onUncheck: function (rowIndex, rowData) {
	            	uncheckRow(rowIndex, rowData);
	                setParentValues(parent.setRoles);
	            }
	        });
	    }
	</script>
	<div>
		
	</div>
	<table id="tbl" data-options="fit:true">
		<thead>
			<tr>
				<th data-options="checkbox:true"></th>
				<th data-options="field:'name', width:200">角色名称</th>
				<th data-options="field:'description', width:200">备注说明</th>
				<th data-options="field:'displayOrder', width:80">显示顺序</th>
			</tr>
		</thead>
	</table>
</body>
</html>