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
		$(function(){
			initForm();
			$("#_name").focus();
		});
		function initForm(){
			$("#frm").form({
                url: "<s:url action="save" namespace="/uac/positiongrade" />",
                onSubmit: function () {
                    var isValid = $(this).form('validate');
                    if (isValid) {
                        $.messager.progress();
                    }
                    return isValid;
                },
                success: function (data) {
                    $.messager.progress('close');
                    if(dealJsonResult(data, "职级保存成功！", function(){$("#_name").focus();})){
                    	clearForm();
                    	top.refreshContent();
                    }
                    /* var result = $.parseJSON(data);
                    if (result && result.isSuccess) {
                        $.messager.alert(WINDOW_CAPTION, '指标类型保存成功', 'info');
                        clearForm();
                        top.refreshContent();
                    } else if (result) {
                        $.messager.alert(WINDOW_CAPTION, result.resultDescription, 'error');
                    } else {
                        $.messager.alert(WINDOW_CAPTION, DEFAULT_ERROR, 'error');
                    } */
                }
            });	 
			
			
			$("#_name").validatebox({
				required:true,
				validType:'length[0, 100]',
				missingMessage:'职级名称必填，请输入！',
				invalidMessage:'职级名称长度不超过100个汉字！',
				deltaX:-200
			});
			$("#_name").validatebox({
				validType: "remote['<s:url action="validatename" namespace="/uac/positiongrade" />?id=" + $('#_id').val() + "', 'entity.name']",
				invalidMessage:'职级名称已存在，请修改名称！',
				deltaX:-200
			});
			
			$("#_description").validatebox({
				validType:'length[0, 250]',
				invalidMessage:'备注说明长度不超过250个汉字！',
				deltaX:-200
			});
		}
		
		function clearForm(){
			$("#frm").form("clear");
		}
	</script>
	<s:form id="frm" method="post">
	<div class="formdiv">
			<table class="formtable">
				<tr>
					<td class="tdHeader">职级名称<label class="required">*</label>：</td>
					<td class="tdContent"><s:textfield id="_name" name="entity.name" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">序号：</td>
					<td class="tdContent"><s:textfield id="_displayOrder" name="entity.displayOrder" cssClass="formtext"/></td>
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