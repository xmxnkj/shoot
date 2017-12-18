<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
response.setHeader("Pragma","No-cache");    
response.setHeader("Cache-Control","no-cache");    
response.setDateHeader("Expires", -10); 
String path = request.getContextPath();
System.out.print(path);
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
pageContext.setAttribute("path",path,PageContext.REQUEST_SCOPE); 
pageContext.setAttribute("basePath",basePath,PageContext.REQUEST_SCOPE);

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
</head>
<body>
    <header class="text-center header"><p id="news_header"></header>
    <div id="daau"></div>
    <div class="content" id="news_content">
    </div>
    <div class="discuss">
        <div class="discuss-title" id="aa" hidden>
            <p><span>评论</span>（评论后台审核后方显示）</p>
        </div>
    </div>
    <script type="text/javascript">
    	$(document).ready(function(){
    		var legaldocid = '${requestScope.legaldocid}';
    		$.post($homebasepath+'/admin/mediation/legaldoc/getLegalDoc.shtml',{"legaldocid":legaldocid},function(data){
    			data = eval('('+data+')');
    			if(data.success){
    				$('#news_header').html(data.data.LegalDoc.title);
    				$('#daau').html("<div class='text-daau'><span>发布时间:</span><span>"+formatDate(data.data.LegalDoc.publishTime)+"</span><span style='margin:0px 5px'>|</span><span>发布单位：</span><span>"+(data.data.LegalDoc.publishUnit)+"</span></div>");
    				$('#news_content').html(data.data.LegalDoc.content);	//内容
    				var tmp = "";
    				$.each(data.data.momentsList,function(i,obj){
    					tmp += "<div class='discuss-user'>"
       					 +"<div class='uesr'>"
       						+"<div class='uesr_photo'><img src='"+obj.commentsClientImage+"' id='uese_logo'></div>"
       							+"<span class='float-left cor028' id='user_name'>"+obj.commentsClientName+"</span>"
       							+"<span class='float-right corbbb'>"+obj.commentsTime+"</span>"
       						+"</div>"
       						+"<p>"+obj.commentsContent+"</p>"
       					+"</div>";
            		});
            		$('#aa').append(tmp);
            		$('#aa').show();
    			}
    		});
    	});
    	
    	function formatDate(obj){
    		 var d = new Date(obj.time);
    		 var   year = d.getFullYear();
             var   month = d.getMonth()+1>9?d.getMonth()+1:"0"+(d.getMonth()+1);
             var   date = d.getDate()<10?"0"+d.getDate():d.getDate();     
             var   hour = d.getHours()<10?"0"+d.getHours():d.getHours();     
             var   minute = d.getMinutes()<10?"0"+d.getMinutes():d.getMinutes();     
             var   second = d.getSeconds()<10?"0"+d.getSeconds():d.getSeconds();
             return   year+"-"+month+"-"+date+"   "+hour+":"+minute+":"+second;  
    	}
    </script>
</body>
</html>