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
	<script>
		 $(function(){
			 
			 $("#ulTree").tree({
		            url: "<s:url action="listjson" namespace="/uac/operationcategory"/>",
		            loadFilter: function (rows) {
		                return convert(rows, "操作");
		            },
		            onDblClick: function (node) {
		                
		            },
		            checkbox:true,
		            onLoadSuccess:function(){
		            	setAces();
		            }
		        });
		}); 
		 
		 function setAces(){
			 var actorId = "<s:property value="actorId" />";
			 var actorType = "<s:property value="actorType" />";
			 $.ajax({url : '<s:url action="listjson" namespace="/uac/ace" />?entityQuery.actorId=' + actorId+"&entityQuery.actorType=" + actorType+"&rnd=" + Math.random(),
					type: 'get',
					timeout : 1000,
					success : function(data) {
						var result = $.parseJSON(data);
						if(result.rows!=null && result.rows.length>0){
							for(var i=0;i<result.rows.length;i++){
								var node = $("#ulTree").tree("find", result.rows[i].operationId);
								if(node != null){
									$("#ulTree").tree("check", node.target);
								}
							}
						}
					},
					error : function() {
						//top.$.messager.alert(WINDOW_CAPTION, "网络原因，保存失败，请重试！", "error");
					},
					async : false
				});
		 }
		 
		 function save(){
			 
			 var actorId = "<s:property value="actorId" />";
			 var actorType = "<s:property value="actorType" />";
			 var operationIds = getSelectedIds();
			 var param = "actor.id=" + actorId + "&actor.actorType="+actorType+"&operationIds=" + operationIds;
			 $.ajax({url : '<s:url action="saveoperationsjson" namespace="/uac/ace" />',
					type: 'post',
					data:param,
					timeout : 1000,
					success : function(data) {
						var result = $.parseJSON(data);
						if(result.isSuccess)
							top.$.messager.alert(WINDOW_CAPTION, "保存成功！", "info");
						else
							top.$.messager.alert(WINDOW_CAPTION, "保存失败:" + result.resultDescription, "error");
					},
					error : function() {
						top.$.messager.alert(WINDOW_CAPTION, "网络原因，保存失败，请重试！", "error");
					},
					async : false
				});
		
			}
		 
		 
		 function getSelectedIds(){
			 var nodes = $("#ulTree").tree("getChecked");
			 var ids = "";
			 if(nodes != null && nodes.length>0){
				 for(var i=0;i<nodes.length; i++){
					 if($("#ulTree").tree("isLeaf", nodes[i].target)){
						 if(ids != "")
							 ids+=";";
						 ids+=nodes[i].id;
					 }
				 }
			 }
			 return ids;
		 }
		
	</script>
	<div style="height: 100%; padding: 1px; background: #eee; overflow-y: hidden;" class="easyui-layout" fit="true">    
	    <div region="center">
	        <ul id="ulTree"></ul>
	    </div>
	    <div data-options="region:'south'" style="text-align: center; padding-top:10px; vertical-align: middle; height:50px">
	    	<input type="button" value="保存" onclick="save();"/>
	    	<input type="button" value="取消" onclick="top.closeDialog();"/>
	    </div>
	</div>
</body>
</html>