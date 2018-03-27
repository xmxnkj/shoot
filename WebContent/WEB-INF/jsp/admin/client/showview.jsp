<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>

<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
  		<div id="divres" style="text-align:center;">
  			
		</div>
	</div>
</div>
<script src="<s:property value="#attr.basePath" />adminresource/js/client/showview.js?v=2017051703"></script>
</body>
</html>