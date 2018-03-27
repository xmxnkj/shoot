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
<title>调解列表</title>
</head>
<body>
<div id="mediatorapplylist_layout" class="easyui-layout" data-options="fit:true,border:false,plain:true">
	
		<div id="mediatorapplylist_tabs"  data-options="fit:true,border:false,plain:true">
			
				    <!--<span>会员昵称:</span>
					    <input id="nickName01" name="nickName" data-options="width:150"/>
					    <a id="btnSearch" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-search'>查询</a>
					     <a id="btnAdd" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加会员</a>	    -->
					  	 <a id="btnEdit"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-edit'>审核详情</a> 
					
				</div> 
	
			<table id="mediatorapplylist" ></table>

		</div>
		
	
<script src="<s:property value="#attr.basePath" />adminresource/js/client/mediatorapplylist.js"></script>	
</body>
</html>