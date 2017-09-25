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
				toolbar: [{iconCls:'icon-add', text:'添加', handler:add},
				          {iconCls:'icon-edit', text:'修改', handler:edit},
				          {iconCls:'icon-remove', text:'删除', handler:del}
				          ],
				fit:true,
				url:'<s:url action="listjson" namespace="/uac/position"/>',
				onDblClickRow:edit,
				singleSelect: true
			});
			
		}); 
		function add(){
			top.openDialog('<s:url action="edit" namespace="/uac/position" />',
					"添加职务",
					500, 300
					);
		}
		
		function edit(){
			var row = $('#tbl').datagrid('getSelected');
			if(row != null){
				url = '<s:url action="edit" namespace="/uac/position" />?id=' + row.id;
				top.openDialog(url,
						"修改职务",
						500, 300
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
                            url: '<s:url action="delete" namespace="/uac/position" />?id=' + row.id+ "&rnd="+ Math.random(),
                            type: 'get',
                            timeout: 1000,
                            success: function (data) {
                                if(dealJsonResult(data, "职务删除成功！")){
                                	$('#tbl').datagrid('reload');	
                                }
                            },
                            error: function () {
                                $.messager.alert(WINDOW_CAPTION, '网络原因导致职务删除失败！', 'error');
                            }
                        });
                    }
                });
			}
		}
		
		function refreshData(){
			$("#tbl").datagrid("reload");
		}
	</script>
	<div>
		
	</div>
	<table id="tbl" data-options="fit:true">
		<thead>
			<tr>
				<th data-options="field:'name', width:200">职务名称</th>
				<th data-options="field:'description', width:200">备注说明</th>
				<th data-options="field:'displayOrder', width:80">显示顺序</th>
			</tr>
		</thead>
	</table>
</body>
</html>