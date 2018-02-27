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
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="editform" class="easyui-form" method="post">
			<table class="formtable">
				   id:<input id="id" name="entity.id" data-fname="id" style="width:300px">
				<tr>
				<td class="tdHeader" style="width: 150px">参数类型<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input readonly id="dataType" data-fname="dataType" name="entity.dataType"/>
					 </td>
				</tr>
				<td class="tdHeader" style="width: 150px">类型描述<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input readonly id="dataTypeDesc" data-fname="dataTypeDesc" name="entity.dataTypeDesc"/>
					 </td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">参数值<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="dataValue" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="dataValue" name="entity.dataValue" style="width:300px;"/>
					</td>
				</tr>
				<input id="parentType" data-fname="parentType" name="entity.parentType" style="width:300px"/>
			</table>
		</form>
	</div>
</div>
<script src="<s:property value="#attr.basePath" />adminresource/js/mediation/basicdata/detaileditform.js?v=2017062303"></script>
</body>
</html>