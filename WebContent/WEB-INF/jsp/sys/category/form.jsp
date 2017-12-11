<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<script>
	
		var saveUrl = "<s:url action="save" namespace="/sys/category" />";
		var serverValidateName = '<s:url action="validatename" namespace="/sys/category" />';
		
		var saveSucInfo = "栏目保存成功！";
		var nameRequire = true;
		var nameMaxLength = 100;
		var nameServerValidate = false;
		var descriptionLengthValidate = true;
		var descriptionMaxLength = 250;
		
		
	</script>
	<script src="<s:url value="/content/scripts/common/form.js"/>"></script>
	<s:form id="frm" method="post">
	<div class="formdiv">
			<table class="formtable">
				<tr>
					<td class="tdHeader">栏目名称<label class="required">*</label>：</td>
					<td class="tdContent"><s:textfield id="_name" name="entity.name" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">显示顺序：</td>
					<td class="tdContent"><s:textfield name="entity.displayOrder" cssClass="easyui-numberspinner" /></td>
				</tr>
				<tr>
					<td class="tdHeader">备注：</td>
					<td class="tdContent"><s:textarea id="_description" name="entity.description" cssClass="formtextarea"/></td>
				</tr>
			</table>
			<s:hidden name="entity.id" />
			<s:hidden id="_id" name="id" />
	</div>
	<div class="buttonbox">
		<input type="submit" value="保存" /> 
		<input type="button" value="取消" onclick="top.closeDialog()" />
	</div>
	</s:form>
</body>
</html>