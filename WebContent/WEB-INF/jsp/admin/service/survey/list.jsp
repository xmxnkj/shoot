<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="<s:property value="#attr.basePath"/>pluginresource/jqweui/lib/weui.min.css">
<link rel="stylesheet" href="<s:property value="#attr.basePath"/>pluginresource/jqweui/css/jquery-weui.min.css">
<link rel="stylesheet" type="text/css" href="<s:property value="#attr.basePath"/>mobileresource/css/clean-up.css">
<link rel="stylesheet" type="text/css" href="<s:property value="#attr.basePath"/>mobileresource/css/style.css">
<title>Insert title here</title>
</head>
<body>

<script src="<s:property value="#attr.basePath" />adminresource/js/service/survey/list.js"></script>
<script src="<s:property value="#attr.basePath" />pluginresource/jsviews/jsrender.min.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false,plain:true">
	<!-- 布局west -->
	<div data-options="region:'west'" style="width:600px;"  title="">
		<table id="dgsurveyactivitylist" ></table>
	</div>
	<!-- 布局center -->
	<div data-options="region:'center',fit:true" style="width:100px;" title="">
		<table id="dgactivityclientlist" ></table>
	</div>
	<!-- 布局east -->
	<div data-options="region:'east'" style="width:900px;" title="">
		<form id="surveyformid" name="surveyform">
  			<div id="result"></div>
		</form>
	</div>
</div>
<script id="surveryTmpl" type="text/x-jsrender">
	<div id="wraper">
        <div class="main-body">
            {{:seqnum}}{{:surveytitle}}<br>
			{{for queslist ~parentid=id ~parentRespcontent=respcontent}}
				{{if surveytype==0}}
				     {{if ischeck==true}}
					  	<input  disabled="true" id="{{:~parentid}}_{{:id}}" type="radio" name="{{:~parentid}}" checked>
					 {{else ischeck==false}}
						<input  disabled="true" id="{{:~parentid}}_{{:id}}" type="radio" name="{{:~parentid}}">
					 {{else}}
				     {{/if}}
				      <span class="intro">
				           {{:surveycontent}} 
				      </span>
				      <br>
				{{else surveytype==2}}
					<div class="ovf">
					    <textarea id="{{:~parentid}}_{{>id}}" disabled="true">{{:~parentRespcontent}}</textarea>
					</div>
				{{else surveytype==1}}
				    <div class="ovf">
					<div class=" on_check fll mgr1">
						{{if ischeck==true}}
					  		<input  disabled="true" id="{{:~parentid}}_{{>id}}_{{>id}}" name="{{:~parentid}}_{{>id}}" type="checkbox" style="height:20px;width:20px;" class="input" value="1" checked>
					 	{{else ischeck==false}}
							<input  disabled="true" id="{{:~parentid}}_{{>id}}_{{>id}}" name="{{:~parentid}}_{{>id}}" type="checkbox" style="height:20px;width:20px;" class="input" value="1">
					 	{{else}}
				     	{{/if}}
				     	
				     {{>surveycontent}}
					 </div>
				</div>
				{{else}}
				{{/if}}
			{{/for}}
        </div>
    </div>
</script>


</body>
</html>