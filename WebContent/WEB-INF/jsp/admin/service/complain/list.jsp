<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<title>投诉</title>
</head>
<body>
<div id="complainlist_layout" class="easyui-layout" data-options="fit:true">
	<div id="complainlist_toolbar" >
		<div>
		    <span>投诉时间:</span>
		    <input id="beginDate" name="beginDate" class="easyui-datebox" data-options="width:150"/>-
		    <input id="endDate" name="endDate" class="easyui-datebox" data-options="width:150"/>
		    <a id="btnSearch" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-search'>查询</a>  
		</div>
	</div> 
	<table id="dgcomplain" ></table>
</div>	
<script src="<s:property value="#attr.basePath" />adminresource/js/service/complain/list.js?v=2017011801"></script>	
</body>
</html>