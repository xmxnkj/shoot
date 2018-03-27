<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	pageContext.setAttribute("path",path,pageContext.REQUEST_SCOPE); 
	pageContext.setAttribute("basePath",basePath,pageContext.REQUEST_SCOPE);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="easyui-layout" style="overflow-y: hidden; width: 100%; height: 100%;" fit="true">
		<!-- <div region="north" style="height: 33px" border="false">
			<div class="menu">
				<a id="btn" href="#" onclick="add(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加</a> 
				<a id="btn" href="#" onclick="edit(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-edit'>修改</a> 
				<a id="btn" href="#" onclick="del(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-remove'>删除</a>
				<a id="btn" href="#" onclick="openSearch(); return false;" plain="true" class="easyui-linkbutton" iconcls='icon-search'>查找</a>
				
			</div>
		</div> -->
		<div style="padding: 10px" data-options="region:'west', border:true, width:300">
			<ul id="ulTree"></ul>
		</div>
		<div data-options="region:'center', border:false">
			<table id="tbl" data-options="fit:true, border:false">
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
<script src="<s:property value="#attr.basePath" />adminresource/js/client/authoritylist.js?v=2017041401"></script>
</body>
</html>