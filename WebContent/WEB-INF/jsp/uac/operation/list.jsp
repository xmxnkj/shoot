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
		 $(function(){
			$('#tbl').datagrid({
				fit:true,
				url:'<s:url action="listjson" namespace="/uac/operation"/>',
				onDblClickRow:edit,
				singleSelect: true
			});
			initCategoryTree();
		}); 
		function add(){
			top.openDialog('<s:url action="edit" namespace="/uac/operation" />',
					"添加操作",
					550, 450
					);
		}
		
		function edit(){
			var row = $('#tbl').datagrid('getSelected');
			if(row != null){
				url = '<s:url action="edit" namespace="/uac/operation" />?id=' + row.id;
				top.openDialog(url,
						"修改操作",
						550, 450
						);
			}else{
				$.messager.alert('提示', '请选择一行进行修改！');
			}
		}
		
		function del(){
			var row = $('#tbl').datagrid('getSelected');
			if(row != null){
				$.messager.confirm('提示信息', '您确认要删除吗？', function (data) {
                    if (data) {
                        $.ajax({
                            url: '<s:url action="delete" namespace="/uac/operation" />?id=' + row.id+ "&rnd="+ Math.random(),
                            type: 'get',
                            timeout: 1000,
                            success: function (data) {
                                if(dealJsonResult(data, "操作删除成功！")){
                                	$('#tbl').datagrid('reload');	
                                }
                            },
                            error: function () {
                                $.messager.alert(WINDOW_CAPTION, '网络原因导致操作删除失败！', 'error');
                            }
                        });
                    }
                });
			}
		}
		
		function refreshData(){
			var node = $("#ulTree").tree("getSelected");
			
			var params = {};
			if(node != null){
				params["entityQuery.categoryId"] = node.id;
			}
			
			$("#tbl").datagrid("reload", params);
		}
		
		function initCategoryTree(){
			$("#ulTree").tree({
	            url: "<s:url action="listjson" namespace="/uac/operationcategory"/>",
	            loadFilter: function (rows) {
	                return convert(rows, "操作类别");
	            },
	            onClick:function(node){
	            	refreshData(node.id);
	            }
	        });
		}
		
	</script>
	<div>
		
	</div>
	<div class="easyui-layout" style="overflow-y: hidden; width: 100%; height: 100%;" fit="true">
		<div region="north" style="height: 33px" border="false">
			<div class="menu">
				<a id="btn" href="#" onclick="add(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加</a> 
				<a id="btn" href="#" onclick="edit(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-edit'>修改</a> 
				<a id="btn" href="#" onclick="del(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-remove'>删除</a>
			</div>
		</div>
		<div style="padding: 10px" data-options="region:'west', border:true, width:250">
			<ul id="ulTree"></ul>
		</div>
		<div data-options="region:'center', border:false">
	<table id="tbl" data-options="fit:true">
		<thead>
			<tr>
				<th data-options="field:'name', width:200">操作名称</th>
				<th data-options="field:'description', width:200">备注说明</th>
				<th data-options="field:'displayOrder', width:80">显示顺序</th>
			</tr>
		</thead>
	</table>
	</div>
	</div>
</body>
</html>