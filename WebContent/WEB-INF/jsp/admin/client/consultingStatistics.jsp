<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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


 <div class="container">          
        <h1 align="center" style="font-weight:bold; font-size:30px; color:#000000;">用户咨询统计</h1>  
        <p align="center"><span id="today" style="font-size:18px; color:#A52A2A;"></span><span id="now" style="font-size:18px; color:#A52A2A;"></span></p><br />       
       <div class="row">  
            <div class="col-xs-6" style = "padding-right: 0px; padding-left: 0px;">  
                <div align="center" style="font-weight:bold; font-size:12px; color:#000000"></div>  
                <div align="center" id="chart" style="height:400px"></div>  
            </div>  
        </div>       
    </div> 
    

<script src="<s:property value="#attr.basePath" />adminresource/js/client/consultingStatistics.js?v=20170212001"></script>
<script src="<s:property value="#attr.basePath" />adminresource/js/echarts.min.js?v=20170212001"></script>
 <script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>  
    <link href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">  
    <!-- ECharts单文件引入 -->  
    <script src="http://echarts.baidu.com/build/echarts-plain.js"></script>  
</body>
</html>