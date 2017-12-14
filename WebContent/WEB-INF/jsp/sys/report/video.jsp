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
<video controls="controls" autoplay="autoplay" loop="loop" preload="preload" type="video/mp4">
	<s:if test="videoUrl!=null && videoUrl!=''">
		<source src="<s:property value="videoUrl"/>"/>
	</s:if><s:else>
		<source src="<s:url action="download" namespace="/kfbase/objectfile"/>?id=<s:property value="id" />"/>
	</s:else>
</video>
</body>
</html>