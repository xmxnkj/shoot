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
<div id="newslist_layout" class="easyui-layout" data-options="fit:true">
	<div id="newslist_toolbar" >
		<div>
		    <span>新闻标题:</span>
		    <input id="articletitle" name="articletitle" data-options="width:150"/>
		    <a id="btnSearch" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-search'>查询</a>
		    
		    <a id="btnAdd" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-add'>新增</a>
		    
		  	<a id="btnEdit"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-edit'>新闻详情</a>
		  	
		  	<a id="btnRemove" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-remove'>移除</a>
		</div>
	</div>
	<table id="newslist" ></table>
</div>	
<script src="<s:property value="#attr.basePath" />adminresource/js/news/list.js?v=2017031006"></script>
</body>
</html>