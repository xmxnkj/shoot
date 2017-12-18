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
<div id="wraper">
    <header class="text-center header"><p id="news_header"><!-- 厦门市最低工资标准调整 --></p></header>
    <div id="daau">
    	<div class='text-daau'>
    	<span>日期:</span>
    	<span>2018-06-02</span>
    	<span style='margin:0px 5px'>|</span><span>作者：</span>
    	<span></span>
    	</div>
    </div>
    <div class="content" id="news_content">
    </div>
    <div class="discuss">
        <div class="discuss-title" id="aa" hidden>
            <p><span>评论</span>（评论后台审核后方显示）</p>
        </div>
    </div>
</div>
    <script type="text/javascript">
    	$(document).ready(function(){
    		var newsId = '${requestScope.newsId}';
    		var clientId = '${requestScope.clientId}';
    		var type = '${requestScope.type}';
    		$.post($homebasepath+'admin/news/getNewsInformation.shtml',{"nid":newsId,"clientId":clientId},function(data){
    			data = eval('('+data+')');
        		$('#news_header').html(data.newsHeadInfo.articletitle);
        		$('#daau').html("<div class='text-daau'><span>日期:</span><span>"+data.newsHeadInfo.articledatetime+"</span><span style='margin:0px 5px'>|</span><span>作者：</span><span>"+(data.newsHeadInfo.authorName?data.newsHeadInfo.authorName:"")+"</span></div>");
        		if(data.newsDetails.length>0){
        			$('#news_content').html(data.newsDetails[0].newsContent);	//内容
        		}else{
        			$('#news_content').html(data.newsHeadInfo.newsContent);
        		}
        		var tmp = "";
        		if(type=='list'){
        			//评论
            		$.each(data.newsComments.rows,function(i,obj){
            			tmp += "<div class='discuss-user'>"
            					 +"<div class='uesr'>"
            						+"<div class='uesr_photo'><img src='"+$homebasepath+"uploads/client/"+obj.client.headImgFile+"' id='uese_logo'></div>"
            							+"<span class='float-left cor028' id='user_name'>"+obj.client.nickName+"</span>"
            							+"<span class='float-right corbbb'>"+obj.commentdatetime+"</span>"
            						+"</div>"
            						+"<p>"+obj.commentcontent+"</p>"
            					+"</div>";
            		});
            		$('#aa').append(tmp);
            		$('#aa').show();
        		}
    		});
    	});
    </script>
</body>
</html>