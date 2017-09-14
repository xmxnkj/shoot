<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<s:form id="frm" method="post" action="uploadfile" namespace="/uac/user" enctype="multipart/form-data">
		<div class="formdiv">
			<table class="formtable">
				<tr>
					<td class="tdHeader">
						上传头像
						<label class="required">*</label>
						：
					</td>
					<td class="tdContent">
						<s:file name="uploadedFile" />
					</td>
				</tr>
			</table>
		</div>
		<div class="buttonbox">
			<input type="submit" value="上传" onclick="$.messager.progress()" />
			<input type="button" value="取消" onclick="top.closeDialog2()" />
		</div>
	</s:form>
</body>
</html>