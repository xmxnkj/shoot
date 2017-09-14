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
	<script src="<s:url value="/content/scripts/Uace/actorSelectBox.js"/>"></script>
	<script>
		$(function(){
			initForm();
		});
		
		function initForm(){
			$("#frm").form({
                url: "<s:url action="savepasswdjson" namespace="/uac/user" />",
                onSubmit: function () {
                    var isValid = $(this).form('validate');
                    if (isValid) {
                        $.messager.progress();
                    }
                    return isValid;
                },
                success: function (data) {
                    $.messager.progress('close');
                    if(dealJsonResult(data, "密码修改成功！", function(){$("#_name").focus();})){
                    	clearForm();
                    	top.closeDialog();
                    }
                }
            });	 
			
			$("#frmus").form({
                url: "<s:url action="saveUserSettingJson" namespace="/uac/user" />",
                onSubmit: function () {
                    var isValid = $(this).form('validate');
                    if (isValid) {
                        $.messager.progress();
                    }
                    return isValid;
                },
                success: function (data) {
                    $.messager.progress('close');
                    if(dealJsonResult(data, "设置保存成功！", function(){$("#_name").focus();})){
                    	clearForm();
                    	top.closeDialog();
                    }
                }
            });	
			
			$.extend($.fn.validatebox.defaults.rules, {
                passwdValid: {
                    validator: function (value, param) {
                        return value == $("#" + param[0]).val();
                    },
                    message: '两次输入密码不一致，请修改!'
                }
            });
			
			$("#oldPasswd").validatebox({
				required:true,
				missingMessage:"请输入原密码！",
				deltaX:-200
			});
			
			$("#passwd").validatebox({
				required:true,
				validType:'length[0, 30]',
				missingMessage:"请输入新密码！",
				invalidMessage:'登录密码长度不超过30个字符！',
				deltaX:-200
			});
			$("#passwd").validatebox({
                validType: 'passwdValid["passwdConfirm"]',
                invalidMessage: "两次输入密码不一致，请修改!",
                deltaX: -200
            });
			$("#passwdConfirm").validatebox({
                validType: 'passwdValid["passwd"]',
                invalidMessage: "两次输入密码不一致，请修改!",
                deltaX: -200
            });
			$("#passwdConfirm").change(function(){
				$("#frm").form('validate');
			});
			$("#passwd").change(function(){
				$("#frm").form('validate');
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
					<td class="tdHeader">原密码<label class="required">*</label>：</td>
					<td class="tdContent"><s:password id="oldPasswd" name="oldPasswd" cssClass="formtext" /></td>
				</tr>
				<tr>
					<td class="tdHeader">新密码：</td>
					<td class="tdContent"><s:password id="passwd" name="passwd" cssClass="formtext" />
				</tr>
				<tr>
					<td class="tdHeader">密码确认：</td>
					<td class="tdContent"><s:password id="passwdConfirm" name="passwdConfirm" cssClass="formtext" /></td>
				</tr>
			</table>
	</div>
	<div class="buttonbox">
		<input type="submit" value="修改" /> 
		<input type="button" value="取消" style="display:none" onclick="top.closeDialog()" />
	</div>
	
	</s:form>
</body>
</html>