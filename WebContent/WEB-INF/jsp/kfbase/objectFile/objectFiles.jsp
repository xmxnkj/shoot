<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
<!--
a:visited{text-decoration:underline; color:black}
a:hover{text-decoration:underline; color:black}
a:active{text-decoration:underline; color:black}
a{text-decoration:underline; color:black}
-->
</style>
<div id="files">
<s:iterator value="objectFiles">
	<s:if test="objectName!='content'">
		<div style="padding:5px">
		<a href="<s:url action="download" namespace="/kfbase/objectfile"/>?id=<s:property value="id" />">
		<s:property value="originalName"/>
		</a>&nbsp;&nbsp;
		<a href="#" onclick="deleteFile('<s:property value="id" />'); return false;">删除</a>
		</div>
	</s:if>
</s:iterator>
</div>
<script>
	function setFileLoaded(fileId, fileName, objectId, objectName) {
		if(objectName != "content"){
			var html = "";
			html+="<div>";
			
			html+="<a href='<s:url action="download" namespace="/kfbase/objectfile"/>?id=" + fileId + "'>"
				+ fileName
				+ "</a>&nbsp;&nbsp;"
				+ "<a href='#' onclick='deleteFile(\"" + fileId + "\"); return false;'>删除</a>";
			
			html+="</div>";
			$("#files").append(html);
		}
	}
	
	function deleteFile(fileId){
		top.$.messager.confirm("删除", "确定要删除文件吗？", function(r){
			if(r)
				doDeleteFile(fileId);
		});
	}
	
	function doDeleteFile(fileId){
		$.ajax({
	        url: '<s:url action="delete" namespace="/kfbase/objectfile" />?id=' + fileId,
	        type: 'post',
	        timeout: TIMEOUT,
	        success: function (data) {
	        	var result = JSON.parse(data);
	        	if(dealJsonResult(data, "文件删除成功！")){
					refreshObjectFiles(result.objectId, result.objectName);	        		
	        	}
	        },
	        error: function () {
	        	top.$.messager.alert("上传", "文件删除失败！", "error");
	        }
	    });
	}
	
	function refreshObjectFiles(objectId, objectName){
		//+ "&entityQuery.objectName=" + objectName + 
		$.ajax({
	        url: '<s:url action="listjson" namespace="/kfbase/objectfile" />?entityQuery.objectId=' + objectId + "&rnd=" + Math.random(),
	        type: 'post',
	        timeout: TIMEOUT,
	        success: function (data) {
	        	result = JSON.parse(data);
	        	setFiles(result);
	        },
	        error: function () {
	        	top.$.messager.alert("文件", "文件获取失败！", "error");
	        }
	    });
	}
	
	function setFiles(data){
		var html = "";
		if(data != null && data.rows != null){
			
			
			for(var i=0; i<data.rows.length; i++){
				if(!data.rows[i].objectName || data.rows[i].objectName!='content'){
					html+="<div>";
					
					html+="<a href='<s:url action="download" namespace="/kfbase/objectfile"/>?id=" + data.rows[i].id + "'>"
						+ data.rows[i].originalName
						+ "</a>&nbsp;&nbsp;"
						+ "<a href='#' onclick='deleteFile(\"" + data.rows[i].id + "\"); return false;'>删除</a>";
					
					html+="</div>";
				}
			}
			
			
		}
		$("#files").html(html);
	}
</script>