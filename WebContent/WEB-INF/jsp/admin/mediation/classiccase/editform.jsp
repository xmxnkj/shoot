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
		
		<div id="newsform_tabs" class="easyui-tabs" data-options="fit:true,border:false,plain:true">
			
			<div title="基本信息" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="editform" class="easyui-form" method="post">
				<table class="formtable">
				   文章id:<input type="hidden" id="id" name="entity.id" data-fname="id" style="width:300px">
				   员id:<input type="hidden" id="mediatorClientId" name="entity.mediatorClientId" data-fname="mediatorClientId" style="width:300px">
					<input id="image" name="entity.image" data-fname="image" style="width:300px">
				<tr>
				<td class="tdHeader" style="width: 150px">文档类型<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input readonly id="docType" class="easyui-combobox" required="true" missingMessage="必填，请输入！" data-fname="docType" name="entity.docType" />
					 </td>
				</tr>
				<td class="tdHeader" style="width: 150px">经典案例类型<label class="required">*</label>:</td>
					<td class="tdContent">
					   <input readonly id="classicCase" class="easyui-combobox" required="true" missingMessage="必填，请输入！" data-fname="classicCase" name="entity.classicCase" />
					 </td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">机构:</td>
					<td class="tdContent">
					<input readonly id="mediatorAgency" class="easyui-combobox" data-fname="mediatorAgency" name="entity.mediatorAgency" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">员:</td>
					<td class="tdContent">
					<input readonly id="mediatorClient" class="easyui-combobox" data-fname="mediatorClient" name="entity.mediatorClient" />
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">标题<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="title" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="title" name="entity.title" style="width:300px;"/>
					</td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">案例简介<label class="required">*</label>:</td>
					<td class="tdContent">
						<textarea rows="3" 
					          id="docDescription" 
					          class="easyui-validatebox"
					          required="true" 
					          missingMessage="必填，请输入！"
					          data-fname="docDescription"
					          name="entity.docDescription"
						      cssClass="formtext">
					    </textarea>
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
					<td class="tdHeader" style="width: 150px">作者<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="publishUnit" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="publishUnit" name="entity.publishUnit" style="width:300px;"/>
					</td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">发布时间<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="publishTime" data-fname="publishTime" class="easyui-datetimebox" name="entity.publishTime" />
				</tr>

			</table>
		</form>
	</div>
	<div title="配图信息" style="overflow-y:auto;overflow-x:hidden;padding:10px">
			<table class="formtable">
				<tr>
					<td class="tdHeader" style="width:150px">
						<input type="file" name="imgFile" id="imgFile"/>
 					</td>
					<td class="tdContent">
						 <div id="divres">
			    	    	<img id="resource" width="250" height="200" src="#"/>
			    	   	 </div>
			    	   	 <span style="color:red;font-weight:bold;margin-left: 5px;">图片格式限制为jpg,jpeg,png!尺寸建议不小于750*420,且大小不超过2MB</span>
					</td>
				</tr>
			</table>
	</div>
	
	</div>
	</div>
</div>
	 	<link rel="stylesheet" href="<s:property value="#attr.basePath" />/content/scripts/kindEditor/themes/default/default.css">
 	<script src="<s:property value="#attr.basePath" />/content/scripts/kindEditor/kindeditor-min.js"></script>
	<script src="<s:property value="#attr.basePath" />/content/scripts/kindEditor/lang/zh_CN.js"></script>
<%-- <script src="<s:property value="#attr.basePath" />/content/scripts/ck/ckeditor.js"></script>
 --%>
 <script src="<s:property value="#attr.basePath" />adminresource/js/mediation/classiccase/editform.js?v=2017041805"></script>
</body>
</html>