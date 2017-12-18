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

String newsId = request.getAttribute("newsId").toString();
System.out.print(newsId);
String error = request.getAttribute("error").toString();

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="<s:property value="#attr.basePath"/>pluginresource/jqweui/lib/weui.min.css">
<link rel="stylesheet" href="<s:property value="#attr.basePath"/>pluginresource/jqweui/css/jquery-weui.min.css">
<link rel="stylesheet" type="text/css" href="<s:property value="#attr.basePath"/>mobileresource/css/clean-up.css">
<link rel="stylesheet" type="text/css" href="<s:property value="#attr.basePath"/>mobileresource/css/style.css">
<link rel="stylesheet" type="text/css" href="<s:property value="#attr.basePath"/>mobileresource/css/Regulatory-documents.css">
<title>法规文档</title>

</head>
<body>
<script src="<s:property value="#attr.basePath" />pluginresource/jqweui/lib/jquery-2.1.4.js"></script>
<script src="<s:property value="#attr.basePath" />pluginresource/jqweui/js/jquery-weui.min.js"></script>
<script src="<s:property value="#attr.basePath" />pluginresource/jsviews/jsrender.min.js"></script>
<%-- <script src="<s:property value="#attr.basePath" />mobileresource/js/legaldoc.js"></script> --%>
<script src="<s:property value="#attr.basePath" />pluginresource/plugins/jquery.plugin.apiajax.js"></script>
<script src="<s:property value="#attr.basePath" />pluginresource/plugins/jquery.plugin.utils.js"></script>

<div>
	<input type="hidden" id="newsId" value="<%=newsId %>"/>
	<input type="hidden" id="error" value="<%=error %>"/>
	<input type="hidden" id="homeBasePath" value="<%=basePath %>"/>
	<input type="hidden" id="homePath" value="<%=path %>"/>
</div>

<!-- 新闻标题 -->
<s:property value="#request.newsInformation.newsHeadInfo.articletitle"></s:property></br>
<!-- 新闻主图 -->
<div id="mainImg">
	<s:if test="#request.newsInformation.newsHeadInfo.momentsResources!=null">
		<img src="<s:property value="#attr.basePath" />uploads/news/<s:property value="#request.newsInformation.newsHeadInfo.momentsResources.url" />">
	</s:if>
</div>
		</br>
		</br>
		</br>
		</br>

<!-- 新闻内容 -->
<div id="content">
	<s:iterator value="#request.newsInformation.newsDetails" status="statu" id="item">
		<s:if test="momentsResources!=null">                                                                                        
			<img src="<s:property value="#attr.basePath" />uploads/news/<s:property value="momentsResources.url" />">
		</s:if>  
		<p><s:property value="newsContent" /> </p>	<br/>
	</s:iterator>
</div>

		</br>
		</br>
		</br>
		</br>
		
		
<script>
	
	var pageSize = <s:property value="#request.newsInformation.newsComments.pageSize" />;
	var pageNum = <s:property value="#request.newsInformation.newsComments.pageNum" />;
	function previous(){
		pageNum=(pageNum==1?1:pageNum-1);
		loadComment();
	}
	function next(){
		pageNum=(pageNum==pageSize?pageSize:pageNum+1);
		loadComment();
	}
	function loadComment(){
		var newsId = $("#newsId").val();
		$.post($homebasepath+'admin/news/newsComments/comments.shtml',{"newsId":newsId,"page":pageNum},function(data){
			data = eval('('+data+')');
			var tmp = "";
			$.each(data.rows,function(index,obj){
				tmp+="评论人:<p>"+obj.client.nickName+"</p>"
						+"评论人ID:<p>"+obj.client.id+"</p>"
						+"评论时间:<p>"+obj.commentdatetime+"</p>"
						+"评论内容:<p>"+obj.commentcontent+"</p></br>";
			})
			$("#comments").html(tmp);
		});
	}
</script>

<!-- 新闻评论 -->
<div id="comments">
	<s:iterator value="#request.newsInformation.newsComments.rows" status="statu" id="item">
		评论人:<p><s:property value="client.nickName" /></p>
		评论人ID:<p><s:property value="client.id" /></p>
		<s:if test="client.headImgFile !=null"> 
			评论人头像:<p><s:property value="client.headImgFile" /></p>
		</s:if>
		评论时间:<p><s:property value="commentdatetime" /></p>
		评论内容:<p><s:property value="commentcontent" /></p>
		</br>
	</s:iterator>
</div>

<s:if test="#request.error==''">
	<input type="button" value="上一页" onclick="previous()">
	<input type="button" value="下一页" onclick="next()">
</s:if>
<s:if test="#request.error!=''">
	参数有误
</s:if>

</body>
</html>