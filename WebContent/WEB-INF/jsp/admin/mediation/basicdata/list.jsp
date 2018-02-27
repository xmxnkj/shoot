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
<div id="basicdatalist_layout" class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div data-options="region:'center'" style="width:1100px" title="参数类型">
		<div id="basicdatalist_toolbar" >
			<div>
			    <a id="btnAdd" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加参数</a>
			  	<a id="btnEdit"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-edit'>参数详情</a>
			  	<a id="btnDelete"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-remove'>删除</a>
			</div>
		</div> 
		<table id="dgbasicdatalistlist" ></table>
	</div> 
	<div data-options="region:'east'" style="width:700px" title="子类型">
		<div id="basicdatadetailist_toolbar" >
			<div>
			    <a id="btnAddBasicDataDetail" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加</a>
			  	<a id="btnEditBasicDataDetail"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-edit'>编辑</a>
			  	<a id="btnDeleteBasicDataDetail"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-remove'>删除</a>
			</div>
		</div> 
		<table id="dgbasicdatadetailist" ></table>
	</div> 
</div>	
<script src="<s:property value="#attr.basePath" />adminresource/js/mediation/basicdata/list.js?v=2017062309"></script>
</body>
</html>