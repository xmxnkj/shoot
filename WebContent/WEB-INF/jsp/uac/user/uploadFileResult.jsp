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
		function setFileLoaded(){
			var fileId = '<s:property value="id" />';
			if(typeof(top.getContentWindow().setFileLoaded)=="function")
				top.getContentWindow().setFileLoaded(fileId);
			else if(typeof(top.getDialogContentWindow().setFileLoaded)=="function"){
				
			}
				//top.getDialogContentWindow().setFileLoaded(fileId, fileName, objectId, objectName, objectType);
		}
		
		$(function(){
			setFileLoaded();
			try{
				
			}catch(e){
				
			}
			<s:property value="newsScript"/>
		});
	</script>
	<div style="padding:10px">
		<s:if test="isSuccess.toString()=='true'.toString()">
		文件上传成功，<a href="#" onclick="top.closeDialog2();return false;">关闭</a>
		</s:if><s:else>
		文件上传失败！<a href="<s:url action="showuploadfile" namespace="/kfbase/objectfile" />?entity.objectId=<s:property value="entity.objectId"/>&entity.objectName=<s:property value="entity.objectName" />&entity.objectType=<s:property value="entity.objectType"/>">
		重新上传</a> 或  <a href="#" onclick="top.closeDialog2(); return false;">关闭</a>
		</s:else>
	</div>
</body>
</html>