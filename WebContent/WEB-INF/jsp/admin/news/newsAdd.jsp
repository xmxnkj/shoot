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
		
		<form id="editAddform" class="easyui-form" method="post">
			<table class="formtable">
				<tr>
				<td class="tdHeader" style="width: 150px">新闻标题<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input id="articletitle" class="easyui-validatebox" data-fname="articletitle" required="true" missingMessage="必填，请输入！" name="entity.articletitle" />
					 </td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">新闻单位<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="newsUnit" data-fname="newsUnit" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" name="entity.newsUnit" />
				</tr>
				
				<tr>
					<td class="tdHeader" style="width: 150px">作者<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="authorName" data-fname="authorName" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" name="entity.authorName" />
				</tr>
				
				<tr>
					<td class="tdHeader" style="width: 150px">新闻简介<label class="required">*</label>:</td>
					<td class="tdContent">
					<textarea id="newsContent" rows="5" class="easyui-validatebox" required="true" missingMessage="必填，请输入！"
							          data-fname="simpleDesc"
							          name="entity.simpleDesc"
								      cssClass="formtext">
					</textarea>
					</td>
				</tr>
				
			</table>
			</form>
	</div>
</div>
	<script src="<s:property value="#attr.basePath" />adminresource/js/news/newsAdd.js?v=2017031004"></script>
</body>
</html>