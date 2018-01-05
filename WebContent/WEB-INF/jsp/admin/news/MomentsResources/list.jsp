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
<title>新闻广告图片</title>
</head>
<body>
<div id="newsResourceslist_layout" class="easyui-layout" data-options="fit:true">
	<div id="newsResourceslist_toolbar" >
		<div>
		    <a id="uploadSearch" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-search'>资源上传</a>
			<a id="uploadEdit" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-edit'>编辑</a>
			<a id="uploadRemove" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-remove'>删除</a>
		</div>
	</div>
	<table id="newsResourceslist"></table>
</div>	
<script src="<s:property value="#attr.basePath" />adminresource/js/news/MomentsResources/list.js"></script>
</body>
</html>