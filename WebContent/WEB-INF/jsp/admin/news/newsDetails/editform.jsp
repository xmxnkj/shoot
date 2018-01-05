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
	<div class="formdiv">
		<form id="newsDetailsEditform">
			<table class="formtable">
			<input type="" id="id" name="entity.id" data-fname="id" style="width:300px">
			<input type="" id="newsId" name="entity.newsId" data-fname="newsId" style="width:300px">
				<tr>
				
					<td class="tdHeader">内容：</td>
					<td class="tdContent">
						<textarea name="entity.newsContent" data-fname="newsContent" id="newsContent"></textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<script src="<s:property value="#attr.basePath" />/content/scripts/ckeditor4/adapters/jquery.js"></script>
	<script src="<s:property value="#attr.basePath" />/content/scripts/ckeditor4/ckeditor.js"></script>
	<script src="<s:property value="#attr.basePath" />/content/scripts/ckeditor4/build-config.js"></script>
	<script src="<s:property value="#attr.basePath" />/content/scripts/ckeditor4/lang/zh-cn.js"></script>
	<script src="<s:property value="#attr.basePath" />/content/scripts/common/newsCategoryTree.js"></script>
	<script src="<s:property value="#attr.basePath" />adminresource/js/news/newsDetails/newsDetailsEditform.js?v=2017031004"></script>
</body>
</html>