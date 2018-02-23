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
				文章id:<input id="legalDocId" name="entity.legalDocId" data-fname="legalDocId" style="width:300px">
				id:<input id="id" name="entity.id" data-fname="id" style="width:300px">
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
					<td class="tdHeader" style="width: 150px">显示排序:</td>
					<td class="tdContent">
					   <input id="seq" class="easyui-numberspinner" data-fname="seq" name="entity.seq">
					</td>
				</tr>
			</table>
		</form>
	</div>
</div>
<script src="<s:property value="#attr.basePath" />adminresource/js/mediation/legaldoc/docdetaileditform.js?v=2017041702"></script>
</body>
</html>