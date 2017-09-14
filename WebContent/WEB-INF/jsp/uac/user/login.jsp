<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>    
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=10;IE=9;IE=8;" />
<title>海沧调解在线后台管理系统</title>
<script src="<s:url value="/content/scripts/jquery-easyui/jquery-1.7.2.min.js"/>"></script>
<link href="<s:url value="/content/styles/login.css"/>" rel="stylesheet" />
</head>
<body>
	<script type="text/javascript">
		$(function(){
			if(top.location.href!=location.href){
				top.location.href="<s:url action="login" namespace="/uac/user" />";
				return;
			}
			$("#account").focus();
			
			setSize();
			
			$(window).resize(setSize);
		});
		
		function setSize(){
			var w = $(window).width();
			var h = $(window).height();
			$("#lgcontainer").css("left", (w - $("#lgcontainer").width())/2);
			$("#lgcontainer").css("top", (h - $("#lgcontainer").height())/2);
		}
	</script>
	
	<s:form id="frm" method="post" action="login" namespace="/uac/user" >
	<div id="lgcontainer" style="text-align:center; margin:auto;position: fixed; left:0px;top:50px">
	<div class="loginbox">
		<div style="width:100%;text-align:left;padding-left:720px; padding-top:290px">
		<table style="width:100%">
			<tr><td style="padding-top:5px"><s:textfield id="account" name="entity.loginAccount" cssClass="inputbox"/></td></tr>
			<tr><td style="padding-top:28px;text-align:left;"><s:password cssClass="inputbox" name="entity.loginPasswd"/></td></tr>
			<tr><td style="padding-top:20px" ><input type="submit" value="" class="loginbtn" />
			</td></tr>
			<s:if test="loginFail">
			<tr><td style="color:red">
			<s:if test="entity==null||entity.loginAccount==''"><span style="display:none">登录失败：<s:property value="appException.message" /></span></s:if>
			<s:else>
				登录失败，请检查登录账号与密码！
				<s:if test="failCountSetting>failCount">
					<br/>剩余登录次数：<s:property value="leftCount"/>，超过后，账号将锁定！
				</s:if><s:else><s:if test="failCount>0">
					<br/>账号已被锁定，请联系管理员！</s:if>
				</s:else>
				</s:else>
				</td></tr>
				</s:if>
		</table>
		</div>
	</div>
	<div style="padding-top:20px;color:#fff">版本所有：海沧调解在线</div>
	</div>
	</s:form>
</body>
</html>