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
	
	<script src="<s:url value="/content/scripts/common/list.js"/>"></script>
	<script>
		var listjsonUrl = '<s:url action="listjson" namespace="/sys/report"/>?entityQuery.reportStatus=<s:property value="entityQuery.reportStatus"/>';
		var editUrl = '<s:url action="edit" namespace="/sys/report" />';
		var deleteUrl = '<s:url action="delete" namespace="/sys/report" />';
		
		var addTitle="添加报料";
		var editTitle = "修改报料";
		var confirmDeleteTitle = "提示信息";
		var confirmDeleteInfo = "您确认要删除吗？";
		var deleteSuccess = "删除成功！";
		var dlgWidth = 800;
		var dlgHeight = 600;
		
		toolbars[0] = {iconCls:'icon-add', text:'添加', handler:add};
		toolbars[1] = {iconCls:'icon-edit', text:'查看', handler:viewReport};
		toolbars[2] = {iconCls:'icon-remove', text:'删除', handler:del}; 
		toolbars[3] = {iconCls:'icon-remove', text:'搜索', handler:showSearch}; 
		
		function viewReport(){
			var row = $('#tbl').datagrid('getSelected');
			if(row != null){
				var url = '<s:url action="view" namespace="/sys/report"/>?id=' + row.id;
				top.openDialog(url, "报料", 800, 600);
			}
		}
		
		function edit(){
			viewReport();
		}
		
		function nodeClicked(node){
			refreshData();
		}
		
		function refreshData(){
			
			var params={};
			var node = $("#ulTree").tree("getSelected");
			if(node!=null && node.id!=''){
				params["entityQuery.areaId"]=node.id;
			}else{
				if(node!=null && node.id==''){
					params["entityQuery.emptyArea"]=true;
				}
			}
			params["entityQuery.reportUserName"]=$("#rptUserName").val();
			params["entityQuery.reportTimeLower"]=$("#timeLower").datebox("getValue");
			params["entityQuery.reportTimeUpper"]=$("#timeUpper").datebox("getValue");
			params["entityQuery.categoryId"]=$("#category").val();
			params["entityQuery.eventTypeId"]=$("#eventType").val();
			params["entityQuery.contentTypeId"]=$("#contentType").val();
			
			$("#tbl").datagrid("reload", params);
		}
		
		function showSearch(){
			openDialog();
		}
		
		function search(){
			refreshData();
			closeDlg();
		}
		
		function openDialog(title, width, height, isMaxed) {
            var options = {};
            if (title) {
                options.title = title;
            }
            if (width) {
                options.width = width;
            }
            if (height) {
                options.height = height;
            }
            if(isMaxed)
            	options.maximized = true;
            else
            	options.maximized = false;
            $("#dlg").dialog(options);
            $("#dlg").dialog("open", options);
        }
		function closeDlg(){
			$("#dlg").dialog("close");
		}
		
		//var isPage=true;
	</script>
	
    <div class="easyui-layout" style="overflow-y: hidden; width: 100%; height: 100%;" fit="true">
    	<div style="padding: 10px" data-options="region:'west', border:true, width:150">
			<ul id="ulTree" class="easyui-tree" data-options="onClick:nodeClicked">
				<li data-options="id:''">未分区</li>
				<s:iterator value="areas">
					<li data-options="id:'<s:property value="id"/>'"><s:property value="name"/></li>
				</s:iterator>
			</ul>
		</div>
		<div data-options="region:'center', border:false">
			<table id="tbl" data-options="fit:true">
				<thead>
					<tr>
						<th data-options="field:'reportUserName'">报料人</th>
						<th data-options="field:'reportTime'">报料时间</th>
						<th data-options="field:'areaName'">地区</th>
						<th data-options="field:'contentTypeName'">内容类型</th>
						<th data-options="field:'eventTypeName'">事件类型</th>
						<th data-options="field:'categoryName'">栏目</th>
						<th data-options="field:'isPublic',formatter:function(value){return value?'公开':'私密'}">隐私类型</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
	<div id="dlg" class="easyui-dialog" closed="true" modal="true" title="搜索" style="width: 480px; height: 300px; overflow: visible">
		<div class="formdiv">
			<table class="formtable">
				<tr>
					<td class="tdHeader">报料人：</td>
					<td class="tdContent">
						<input type="text" id="rptUserName" class="formtext">
					</td>
				</tr>
				<tr>
					<td class="tdHeader">报料时间：</td>
					<td class="tdContent">
						<input type="text" id="timeLower" class="easyui-datebox" style="width:100px">-
						<input type="text" id="timeUpper" class="easyui-datebox" style="width:100px">
					</td>
				</tr>
				<tr>
					<td class="tdHeader">栏目：</td>
					<td class="tdContent">
						<s:select id="category" list="categories" listKey="id" listValue="name" headerKey="" headerValue="---请选择---"/>
					</td>
				</tr>
				<tr>
					<td class="tdHeader">内容类型：</td>
					<td class="tdContent">
						<s:select id="contentType" list="contentTypes" listKey="id" listValue="name" headerKey="" headerValue="---请选择---" />
					</td>
				</tr>
				<tr>
					<td class="tdHeader">事件类型：</td>
					<td class="tdContent">
						<s:select id="eventType" list="eventTypes" listKey="id" listValue="name" headerKey="" headerValue="---请选择---" />
					</td>
				</tr>
			</table>
			<div class="buttonbox">
				<input type="button" value="查找" onclick="search();">
				<input type="button" value="取消" onclick="closeDlg();">
			</div>
		</div>    
    </div>
</body>
</html>