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
                url: "<s:url action="save" namespace="/kfbase/property" />",
                onSubmit: function () {
                    var isValid = $(this).form('validate');
                    if (isValid) {
                        $.messager.progress();
                    }
                    return isValid;
                },
                success: function (data) {
                    $.messager.progress('close');
                    if(dealJsonResult(data, "属性保存成功！", function(){$("#_name").focus();})){
                    	clearForm();
                    	top.refreshContent();
                    }
                }
            });	 
			
			initNameValid();
			
			$("#_name").validatebox({
				required:true,
				validType:'length[0, 200]',
				missingMessage:'属性名称必填，请输入！',
				invalidMessage:'属性名称长度不超过200个汉字！',
				deltaX:-200
			});
			$("#_name").validatebox({
				validType : "nameValid['_id']",
				deltaX : -200,
				invalidMessage : "属性已存在，请修改名称！"
			});
			
			$("#_description").validatebox({
				validType:'length[0, 500]',
				invalidMessage:'备注说明长度不超过500个汉字！',
				deltaX:-200
			});
		}
		
		function clearForm(){
			var objectId = $("#_objectId").val();
			$("#frm").form("clear");
			$("#_objectId").val(objectId);
			//alert($("#_objectId").val());
		}
		
		function initNameValid() {
			$.extend(
				$.fn.validatebox.defaults.rules,
				{
					nameValid : {
						validator : function(value, param) {
							var id = $("#" + param[0]).val();
							var objectId = $("#_objectId").val();
							var result = false;
							$.ajax({
								url : '<s:url action="validatename" namespace="/kfbase/property" />?id='
										+ id
										+ "&entity.objectId=" + objectId
										+ "&entity.name=" + encodeURI(encodeURI(value))
										+ "&rnd="
										+ Math.random(),
								type : 'get',
								timeout : 1000,
								success : function(data) {
									result = (data == "true");
								},
								error : function() {
									result = false;
								},
								async : false
							});
							//$("#_description").val(result);
							return result;
						},
						message : '属性已存在，请修改名称！'
					}
				});
		}
	</script>
	<s:form id="frm" method="post">
	<div class="formdiv">
			<table class="formtable">
				<tr>
					<td class="tdHeader">属性名称<label class="required">*</label>：</td>
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
			<s:hidden id="_objectId" name="entity.objectId" />
			<s:hidden id="_id" name="id" />
	</div>
	<div class="buttonbox">
		<input type="submit" value="保存" /> 
		<input type="button" value="取消" onclick="top.closeDialog()" />
	</div>
	</s:form>
</body>
</html>