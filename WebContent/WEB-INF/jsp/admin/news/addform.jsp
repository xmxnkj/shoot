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
	<div id="clientlist_layout" class="easyui-layout" data-options="fit:true,border:false,plain:true">
	<div data-options="region:'center',border:false" title="">
		<div id="newsform_tabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			<div title="基本信息" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="addform" class="easyui-form" method="post">
				<table class="formtable">
	
				<input id="id" name="entity.id" data-fname="id" style="width:300px">				
				<tr>
				<td class="tdHeader" style="width: 150px">新闻标题<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input id="articletitle" class="easyui-validatebox" data-fname="articletitle" name="entity.articletitle" style="width:100%"/>
					 </td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">新闻单位<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="newsUnit" data-fname="newsUnit" class="easyui-validatebox" name="entity.newsUnit" style="width:100%" />
				</tr>
				
				<tr>
					<td class="tdHeader" style="width: 150px">作者<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="authorName" data-fname="authorName" class="easyui-validatebox" name="entity.authorName" />
				</tr>
				
				<tr>
					<td class="tdHeader" style="width: 150px">发布时间:</td>
					<td class="tdContent">
					<input id="articledatetime" readonly data-fname="articledatetime" class="easyui-validatebox" name="entity.articledatetime" />
				</tr>
				
				<tr>
					<td class="tdHeader" style="width: 150px">新闻简介<label class="required">*</label>:</td>
					<td class="tdContent">
					<textarea id="simpleDesc" rows="5" class="easyui-validatebox" required="true" missingMessage="必填，请输入！"
							          data-fname="simpleDesc"
							          name="entity.simpleDesc"
								      cssClass="formtext">
					</textarea>
					</td>
				</tr>
				
				<tr>
					<td class="tdHeader" style="width: 150px">内容<label class="required">*</label>：</td>
					<td class="tdContent">
 						<textarea id="newsContent" name="entity.newsContent" data-fname="newsContent" class="easyui-validatebox" required="true" missingMessage="必填，请输入！"></textarea>
 						<!-- <textarea id="newsContent" name="entity.newsContent" data-fname="newsContent" class="easyui-validatebox" required="true" missingMessage="必填，请输入！"></textarea> -->
 					</td>
				</tr>

				</table>
				</form>
			</div>
			
			<div title="主图信息" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="mainImgeditform" class="easyui-form" method="post">
				<table class="formtable">
					<tr>
						<td class="tdHeader" style="width: 150px">主图<label class="required">*</label>：</td>
						<td class="tdContent">
	 						<img id="imgFile" name="imgFile" src="#" width="250" height="200"/>
	 						<!-- <input type="button" id="btn_uploadres" value="上传资源"/> -->
	 						<a href="javascript(0);" id="btn_uploadres" class="easyui-linkbutton">上传资源</a>
	 						<span style="color:red;font-weight:bold;margin-left: 5px;">图片格式限制为jpg,jpeg,png!</span>
	 					</td>
					</tr>
				</table>
				</form>
			</div>
			
		</div>
		</div>
	</div>

 	<%-- <script src="<s:property value="#attr.basePath" />/content/scripts/ck/ckeditor.js"></script> --%>
 	<link rel="stylesheet" href="<s:property value="#attr.basePath" />/content/scripts/kindEditor/themes/default/default.css">
 	<script src="<s:property value="#attr.basePath" />/content/scripts/kindEditor/kindeditor-min.js"></script>
	<script src="<s:property value="#attr.basePath" />/content/scripts/kindEditor/lang/zh_CN.js"></script>
	<script src="<s:property value="#attr.basePath" />adminresource/js/news/addform.js?v=2017031004"></script>

</body>
</html>