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
			 
			 $("#ulTree").tree({
		            url: "<s:url action="listjson" namespace="/uac/operationcategory"/>",
		            loadFilter: function (rows) {
		                return convert(rows, "操作");
		            },
		            onDblClick: function (node) {
		                edit();
		            }
		        });
		}); 
		 
		function add(){
			top.openDialog('<s:url action="edit" namespace="/uac/operationcategory" />',
					"添加操作",
					500, 350
					);
		}
		
		function edit(){
			 var row = $('#ulTree').tree('getSelected');
			if(row != null){
				url = '<s:url action="edit" namespace="/uac/operationcategory" />?id=' + row.id;
				top.openDialog(url,
						"修改操作信息",
						500, 350
						);
			}else{
				$.messager.alert('提示', '请选择一个操作进行修改！');
			}
		}
		
		function del(){
			var row = $('#ulTree').tree('getSelected');
			if(row != null){
				$.messager.confirm('提示信息', '您确认要删除选中的操作吗？', function (data) {
                    if (data) {
                        $.ajax({
                            url: '<s:url action="delete" namespace="/uac/operationcategory" />?id=' + row.id+ "&rnd="+ Math.random(),
                            type: 'get',
                            timeout: 1000,
                            success: function (data) {
                                if(dealJsonResult(data, "操作删除成功！")){
                                	refreshData();
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
			$("#ulTree").tree("reload");
		}
	</script>
	<div style="height: 100%; padding: 1px; background: #eee; overflow-y: hidden;" class="easyui-layout" fit="true">    
    <div region="north" style="height: 33px" border="false">
        <div class="menu">
            <a id="btnAdd" href="#" onclick="add(); return false;" plain="true" class="easyui-linkbutton"
                iconcls='icon-add'>添加</a> <a id="btnEdit" href="#" onclick="edit(); return false;"
                    plain="true" class="easyui-linkbutton" iconcls='icon-edit'>修改</a> <a id="btnCel" href="#"
                        onclick="del(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-remove'>删除</a>
        </div>
    </div>
    <div region="center">
        <ul id="ulTree"></ul>
    </div>
</div>
</body>
</html>