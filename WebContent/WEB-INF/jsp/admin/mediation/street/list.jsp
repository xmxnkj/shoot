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
<title>街道</title>
</head>

<body>

<div id="basicdatalist_layout" class="easyui-layout" data-options="fit:true,border:false,split:true">
	<div data-options="region:'center'" style="width:1100px" title="街道">
		<div id="streetList_toolbar" >
			
		</div> 
		<table id="streetList" ></table>
	</div> 
	<div data-options="region:'east'" style="width:700px" title="在此处进行添加和修改">
		 <form id="editform" class="easyui-form" method="post">
			<table class="formtable">
				<input id="id" hidden name="entity.id" data-fname="id" style="width:300px">
				<tr>
				<td class="tdHeader" style="width: 150px">街道名称<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input id="streetName" data-fname="streetName" name="entity.streetName"/>
					 </td>
				</tr>
	
				<tr>
					<td class="tdHeader" style="width: 150px">操作<label class="required">*</label>:</td>
					<td class="tdContent">
					<input value="保存" type="button" onclick="editEntity()" style="width:200px;"/>
					</td>
				</tr>
			</table>
		</form>
	</div> 
</div>

<script src="<s:property value="#attr.basePath" />adminresource/js/mediation/street/list.js?v=2017031011"></script>
</body>
</html>