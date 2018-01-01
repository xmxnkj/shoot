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

<script src="<s:property value="#attr.basePath" />adminresource/js/service/survey/newsurveylist.js?v=2017030203"></script>
<script src="<s:property value="#attr.basePath" />pluginresource/jsviews/jsrender.min.js"></script>
<div class="easyui-layout" data-options="fit:true,border:false,plain:true">
	<!-- 布局west -->
	<div data-options="region:'west'" style="width:800px;"  title="">
		<div id="surveyactivity_toolbar" >
		<div>
			<a id="btnAddSurvey" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加问卷调查</a>
			<!-- <a id="btnDel"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-remove'>删除调查活动</a>	 -->   
		</div>
	</div> 
		<table id="dgsurveyactivitylist" ></table>
	</div>
	<!-- 布局center -->
	<div data-options="region:'center'" style="width:900px;" title="问卷调查详情">
		<form id="surveyformid" name="surveyform">
  			<div id="result"></div>
		</form>
	</div>
</div>
<script id="surveryTmpl" type="text/x-jsrender">
	<div id="wraper">
        <div class="main-body">
             {{for surveybasemains tmpl="#surveybasemainsTemplate" /}}
        </div>
    </div>
</script>
<script id="surveybasemainsTemplate" type="text/x-jsrender">
  <div class="function">
        <p>{{>seqnum}}.{{>surveytitle}}</p>
        <div class="ovf">
           {{for surveybasedetails ~parentid=id}}
				{{if surveytype=='radio'}}
				      
				         <input disabled="true" id="{{:~parentid}}_{{:id}}" type="radio" 	 name="{{:~parentid}}">
				     
				      <span class="intro">
				           {{:surveycontent}} 
				      </span>
				      <br>
				{{else surveytype=='questions'}}
					<div class="ovf">
					  <textarea disabled="true" id="{{:~parentid}}_{{>id}}" name="{{:~parentid}}_{{>id}}"></textarea>
					</div>
				{{else surveytype=='multi'}}
				    <div class="ovf">
					<div class=" on_check fll mgr1">
				     <input disabled="true" id="{{:~parentid}}_{{>id}}_{{>id}}" name="{{:~parentid}}_{{>id}}" type="checkbox" style="height:20px;width:20px;" class="input" value="1">
				    </div>
				    <span>
						{{>surveycontent}}
					</span>
				</div>
				{{else}}
				{{/if}}
           {{/for}}
        </div>
   </div>
</script>

</body>
</html>