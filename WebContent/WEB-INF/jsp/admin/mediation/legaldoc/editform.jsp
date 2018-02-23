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
	 <script src="<s:url value="/content/scripts/jquery-1.9.1.js"/>"></script>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<form id="editform" class="easyui-form" method="post">
			<table class="formtable">
				 <input type="hidden" id="id" name="entity.id" data-fname="id" style="width:300px">
				<tr>
				<td class="tdHeader" style="width: 150px">文档类型<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input id="docType" class="easyui-combobox" required="true" missingMessage="必填，请输入！" data-fname="docType" name="entity.docType" />
					 </td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">标题<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="title" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="title" name="entity.title" style="width:300px;"/>
					</td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">正文<label class="required">*</label>:</td>
					<td class="tdContent">
						<textarea rows="15" 
					          id="content" 
					          class="easyui-validatebox"
					          required="true" 
					          missingMessage="必填，请输入！"
					          data-fname="content"
					          name="entity.content"
						      cssClass="formtext">
					    </textarea>	
					</td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">发布单位<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="publishUnit" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="publishUnit" name="entity.publishUnit" style="width:300px;"/>
					</td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">发布时间<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="publishTime" data-fname="publishTime" class="easyui-datetimebox" name="entity.publishTime" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">显示顺序<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="orderdisplay" data-fname="orderdisplay" class="easyui-validatebox" name="entity.orderdisplay" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">附件上传<label class="required">*</label>:</td>
					<td class="tdContent">
						<input type="file" name="imgFile" id="imgFile"/>
						<a href="javascript:upload();" plain="true" class="easyui-linkbutton" iconcls='icon-save'>上传文档</a>
						<span id="hasFile"></span>
					</td>	
				</tr>
			</table>
		</form>
	</div>
</div>
 	<link rel="stylesheet" href="<s:property value="#attr.basePath" />/content/scripts/kindEditor/themes/default/default.css">
 	<script src="<s:property value="#attr.basePath" />/content/scripts/kindEditor/kindeditor-min.js"></script>
	<script src="<s:property value="#attr.basePath" />/content/scripts/kindEditor/lang/zh_CN.js"></script>
<%-- <script src="<s:property value="#attr.basePath" />/content/scripts/ck/ckeditor.js"></script> --%>
<script src="<s:property value="#attr.basePath" />adminresource/js/mediation/legaldoc/editform.js?v=2017030702"></script>
</body>
</html>