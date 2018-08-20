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
<div id="mediationagencylist_layout" class="easyui-layout" data-options="fit:true">
	<div data-options="region:'center'" style="height:600px" title="机构信息">
		<div id="mediationagencylist_toolbar" >
			<div>
			    <span>机构名称:</span>
			    <input id="agencyName" name="agencyName" data-options="width:150"/>
			    <a id="btnSearch" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-search'>查询</a>
			    <a id="btnAdd" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加机构</a>
			  	<a id="btnEdit"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-edit'>机构详情</a>
				<a  href="javascript:kill();"  plain="true" class="easyui-linkbutton" iconcls='icon-remove'>删除</a>
				
			</div>
		</div> 
		<table id="dgmediationagencylist" ></table>
		</div>
	<div data-options="region:'south'" style="height:400px" title="机构下辖员">
		<table id="dgmediatorlist" ></table>
	</div>
</div>	
<script src="<s:property value="#attr.basePath" />adminresource/js/mediation/mediationagency/list.js?v=2017041704"></script>
</body>
</html>