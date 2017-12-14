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
		            },
		            checkbox:true
		        });
		}); 
		 
		 
		 function initValues(){
			 var url = '<s:url action="listjson" namespace="/uac/ace" />?entityQuery.actorId=' + $("#actorId").val()
					 + "&entityQuery.actorType=" + $("#actorType").val()+ "&rnd="+ Math.random()
					 ;
			 $.ajax({
                 url: url,
                 type: 'get',
                 timeout: 1000,
                 success: function (data) {
                     var result = $.parseJSON(data);
                     
                 },
                 error: function () {
                     $.messager.alert(WINDOW_CAPTION, '网络原因导致部门删除失败！', 'error');
                 }
             });
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
    <s:property id="actorId" value="actor.id"/> 
    <s:property id="actorType" value="actor.actorType"/>
</div>
</body>
</html>