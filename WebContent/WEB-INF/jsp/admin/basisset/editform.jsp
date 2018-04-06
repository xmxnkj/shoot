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
		
			<div title="基础数据信息" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="editform" class="easyui-form" method="post">
				<table class="formtable">
				   <input type="hidden" id="id" name="entity.id" data-fname="id" style="width:300px">
				<tr>
				<td class="tdHeader" style="width: 150px">参数名称 <label class="required">*</label>:</td>
					<td class="tdContent">
					   <input id="parametername" class="easyui-validatebox" required="true" missingMessage="参数名称必填，请输入！" data-fname="parametername" name="entity.parametername" />
					 </td>
				</tr>
				<td class="tdHeader" style="width: 150px">套餐英文名称:</td>
					<td class="tdContent">
					   <input id="parameterename" class="easyui-validatebox" data-fname="parameterename" name="entity.parameterename" />
					 </td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">参数序号<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="parameterseq" class="easyui-validatebox" required="true" missingMessage="参数序号必填，请输入！" data-fname="parameterseq" name="entity.parameterseq" />
					</td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">参数初始值<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="parameterinitvla" class="easyui-validatebox" required="true" missingMessage="参数初始值必填，请输入！" data-fname="parameterinitvla" name="entity.parameterinitvla" />
					</td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">生成时间<label class="required">*</label>:</td>
					<td class="tdContent">
					<input id="buliddatetime" data-fname="buliddatetime" name="entity.buliddatetime" />
					</td>
				</tr>
			</table>
			</form>
			</div>
		
	  </div>
   </div>
</div>
<script src="<s:property value="#attr.basePath" />adminresource/js/basisset/editform.js?v=2017030201"></script>
</body>
</html>