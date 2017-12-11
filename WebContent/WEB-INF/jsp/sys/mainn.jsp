<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=10;IE=9;IE=8;" />
<title>Insert title here</title>
<style type="text/css">
	html,body{
		overflow:hidden;
		min-height: 100%;
		_height:100%;
		margin:0px;
		padding:0px;
		font:12px;
	}
</style>
<script src="<s:url value="/content/scripts/jquery-easyui/jquery-1.7.2.min.js"/>"></script>
    <script src="<s:url value="/content/scripts/jquery-easyui/jquery.easyui.min.js"/>"></script>
    <link href="<s:url value="/content/scripts/jquery-easyui/themes/icon.css"/>" rel="stylesheet" />
    <link href="<s:url value="/content/scripts/jquery-easyui/themes/default/easyui.css"/>" rel="stylesheet" />
    <script src="<s:url value="/content/scripts/jquery-easyui/locale/easyui-lang-zh_CN.js"/>"></script>
    <link href="<s:url value="/content/scripts/jquery-easyui/themes/default/green1.css"/>" rel="stylesheet" />
<link href="<s:url value="/content/styles/layout.css"/>"
	rel="stylesheet" />
 <link href="<s:url value="/content/styles/Site.css"/>"
	rel="stylesheet" />
	<style type="text/css">
		.pl{
			padding: 10px; background: #e4f8ed;
		}
		.pli{
			background: #e4f8ed;
		}
	</style>
</head>
<body>
	<script>
		var hasLoad = false;
		$(function(){
			
			//$("#divReport").hide();
			
			//resizeContent();
			
			//$(window).resize(resizeContent);
			
			$("#todo").panel({left:200,doSize:true});
			hasLoad = true;
			var center = $("#ly").layout("panel", "center");
			var opt = center.panel("options");
			resizeContent(opt.width, opt.height);
			
			var todoCount = "<s:property value="toDoCount"/>";
			window.parent.setTodoCount(todoCount);

		});
		
		var isresizing=false;
		function resizeContent(w,height){
			return;
			if(!isresizing){
				isresizing=true;
				var width = $(window).width();
				var west = $("#ly").layout("panel", "west");
				var center = $("#ly").layout("panel", "center");
				var opt = west.panel("options");
				
				var tw = width;
				var pwidth = (tw-30)/2;
				var pheight = (height-20);
				
				west.panel("resize", {width:tw/2});
				center.panel("resize",{width:tw/2,left:tw/2});
				if(hasLoad){
					$("#todo").panel("resize", {width:pwidth, height:pheight,left:400});
					//$("#notice").panel("resize", {width:pwidth, height:pheight/2,top:pheight/2+20});
					//$("#split").panel("resize", {width:pwidth, height:(height-20)/3});
					//$("#report").panel("resize", {width:pwidth, height:(height-20)/3});
					$("#news").panel("resize", {width:pwidth, height:height-20});
				}
			isresizing=false;
			}
		}
		
		function showDoing(){
			$.messager.progress();
		}
		
	</script>
	<div class="easyui-layout" id="ly" data-options="fit:true,border:false">
		<div data-options="region:'center',width:500,border:false" style="padding:10px;overflow: hidden">
			<div id="todo" title="待办事项" class="pl" data-options="fit:true">
				 <s:if test="(flowItems != null && flowItems.size>0) || (toDoLists!=null && toDoLists.size>0)||(auditingNoFlowItems!=null&&auditingNoFlowItems.size>0)">
						<table width="100%" class="mainContent">
							<s:iterator value="flowItems">
							<tr class="dottr">
								<td style="width:20px; height:24px; text-align:center"><img src="content/styles/images/dot.jpg" align="asbmiddle"></td>
								<td><a onclick="showDoing();" href="<s:property value="request.contextPath" /><s:property value="pageUrl" />&id=<s:property value="objectId"/>&returnUrl=<s:property value="request.contextPath" />/main.html"><s:property value="title"/></a></td>
								<td style="width:70px">[<s:date name="applyTime" format="yyyy-MM-dd"/>]</td>
							</tr>
							</s:iterator>
							<s:iterator value="auditingNoFlowItems">
							<tr class="dottr">
								<td style="width:20px; height:24px; text-align:center"><img src="content/styles/images/dot.jpg" align="asbmiddle"></td>
								<td><a onclick="showDoing();" href="<s:property value="request.contextPath" /><s:property value="editUrl" />&id=<s:property value="objectId"/>&returnUrl=<s:property value="request.contextPath" />/main.html"><s:property value="title"/>&nbsp;&nbsp;被退回</a></td>
								<td style="width:70px">[<s:date name="applyTime" format="yyyy-MM-dd"/>]</td>
							</tr>
							</s:iterator>
							<s:iterator value="toDoLists">
								<tr class="dottr">
									<td style="width:20px; height:24px; text-align:center"><img src="content/styles/images/dot.jpg" align="asbmiddle"></td>
									<td colspan="2"><a onclick="showDoing();" href="<s:property value="request.contextPath" /><s:property value="url" />&returnUrl=<s:property value="request.contextPath" />/main.html"><s:property value="title"/></a></td>
								</tr>
							</s:iterator>
						</table>
						</s:if><s:else>
							没有待办事项
						</s:else>
			</div>
		</div>
	</div>
</body></html>