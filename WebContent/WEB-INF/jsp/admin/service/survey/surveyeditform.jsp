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
<title>Insert title here</title>
</head>
<body>
<div class="easyui-layout" data-options="fit:true,border:false">
	<div data-options="region:'center',border:false" title="">
		<div id="editsurveyform_tabs" class="easyui-tabs" >
			<!-- 问卷选项卡1 -->
			<div title="问卷基本信息" style="overflow-y:auto;overflow-x:hidden;padding:10px">
				<form id="editform" class="easyui-form" method="post">
				<table class="formtable">
				   活动id:<input id="id" name="entity.id" data-fname="id" style="width:300px">
				<tr>
				<td class="tdHeader" style="width: 150px">调查活动标题 :</td>
					<td class="tdContent">
					   <input id="activitytitle" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="activitytitle" name="entity.activitytitle" style="width:300px"/>
					 </td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">调查活动人:</td>
					<td class="tdContent">
					<input id="activityman" class="easyui-validatebox" required="true" missingMessage="必填，请输入！" data-fname="activityman" name="entity.activityman" style="width:300px" />
					</td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">调查活动描述:</td>
					<td class="tdContent">
						<textarea rows="3" 
								          id="activitydesc" 
								          class="easyui-validatebox"
								          required="true" 
								          missingMessage="必填，请输入！"
								          data-fname="activitydesc"
								          name="entity.activitydesc"
									      cssClass="formtext"
									      style="width: 400px">
					    </textarea>	
				   </td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">开始时间:</td>
					<td class="tdContent">
					<input id="buliddatetime" class="easyui-datetimebox" required="true" missingMessage="必填，请输入！" data-fname="buliddatetime" name="entity.buliddatetime" style="width:300px" />
				   </td>
				</tr>
				<tr>
					<td class="tdHeader" style="width: 150px">结束时间:</td>
					<td class="tdContent">
					<input id="finishdatetime" class="easyui-datetimebox" required="true" missingMessage="必填，请输入！" data-fname="finishdatetime" name="entity.finishdatetime" style="width:300px" />
				   </td>
				</tr>
			</table>
			</form>
			</div>
			<!-- 问卷选项卡2 -->
			<div title="问卷调查题目" style="overflow-y:auto;height:580px;padding:10px">
				<div class="easyui-layout"  data-options="fit:true,border:false,plain:true">
					<div data-options="region:'center',split:true" title="题目列表"  >
						<div id="dgsurveyqueslist_toolbar" >
							<div>
								<a id="btn_surveyques_Add" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加题目</a>
								<a id="btn_surveyques_Edit" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-edit'>编辑题目</a>
								<a id="btn_surveyques_Del"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-remove'>删除题目</a>	   
							</div>
						</div> 
						<table id="dgsurveyqueslist" ></table>
					</div>
					<div data-options="region:'south',split:true,collapsible:false" title="题目答案" style="height:200px;">
						<div id="dgsurveyquesdetaillist_toolbar" >
							<div>
								<a id="btn_surveyquesdetail_Add" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-add'>添加答案</a>
								<a id="btn_surveyquesdetail_Edit" href="javascript:void(0);" plain="true" class="easyui-linkbutton" iconcls='icon-edit'>编辑答案</a>
								<a id="btn_surveyquesdetail_Del"  href="javascript:void(0);"  plain="true" class="easyui-linkbutton" iconcls='icon-remove'>删除答案</a>	   
							</div>
						</div> 
						<table id="dgsurveyquesdetaillist" ></table>
					</div>
				</div>
			</div>
		</div>
		<!-- 问卷调查属性选项卡END -->
	</div>
</div>
<script src="<s:property value="#attr.basePath" />adminresource/js/service/survey/surveyeditform.js?v=2017030301"></script>
</body>
</html>