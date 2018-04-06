<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>    
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	pageContext.setAttribute("path",path,pageContext.REQUEST_SCOPE); 
	pageContext.setAttribute("basePath",basePath,pageContext.REQUEST_SCOPE);
	response.setHeader("Access-Control-Allow-Origin", "*"); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>聊天室主页面</title>
</head>
<body>

<audio id="chatAudio">
    <source src="<s:property value="#attr.basePath" />content/notify.mp3" type="audio/mpeg">
</audio>

<style type="text/css">
.send {
	position: relative;
	width: 250px;
	background: #F8C301;
	border-radius: 5px; /* 圆角 */
	font-size: 12px;
	line-height: 30px;
	text-align: center;
	word-wrap: break-word;
    word-break: break-all;
	margin-left: 20px;
}

.send .arrow {
	position: absolute;
	top: 5px;
	left: -16px; /* 圆角的位置需要细心调试哦 */
	width: 0;
	height: 0;
	font-size: 0;
	border: solid 8px;
	border-color: #4D4948 #F8C301 #4D4948 #4D4948;
}

.send .arrow1 {
	position: absolute;
	top: 5px;
	right: -16px; /* 圆角的位置需要细心调试哦 */
	width: 0;
	height: 0;
	font-size: 0;
	border: solid 8px;
	border-color: #4D4948 #4D4948 #4D4948 #F8C301;
}

.imgDiv {
	padding-top: 10px;
	padding-left: 10px;
	line-height: 30px;
	text-align: right;
	width: 20px;
	display: inline;
}

.imgDiv1 {
	padding-top: 10px;
	padding-left: 10px;
	line-height: 30px;
	text-align: right;
	width: 20px;
}

.leftDiv {
	width: 250px;
	display: inline;
}

.rightDiv {
	float: right;
	padding-right: 30px;
}

.button {
	height: 66px;
	width: 60px;
	font-size: 16px;
	font-family: "微软雅黑";
}

.bottom-table {
	position: absolute;
	bottom: 0;
	border: solid 1px;
}

.area {
	height: 66px;
}
</style>

<input type="hidden" id="chatuserid" name="chatuserid"/>

<div class="easyui-layout" data-options="fit:true">
  <div data-options="region:'west',title:'咨询用户',split:false,border:true" style="width:400px;">
     <table id="dgchatclient" ></table>
  </div>
  <div data-options="region:'center',border:false">
     <div class="easyui-layout" data-options="fit:true,border:false">
         <!-- 聊天内容 -->
         <div data-options="region:'center',border:false">
			<div id="messageTable" width="100%" style="position:relative; height:95%;overflow:auto;padding-top:20px;">
			</div>
         </div>
         <!-- 发送信息工具栏 -->
         <div data-options="region:'south',split:false,border:true" style="height:120px;">
            <div style="margin-left:5px;margin-bottom:5px;margin-top:5px">
               <input id="messagecontent" name="messagecontent" class="easyui-textbox"  style="width:90%;height:26px;margin-right:5px;">
               <a id="sendmessage" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:113px">发送消息</a>
               <!-- <a id="sendface" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-reload'" style="width:90px">发送表情</a> -->
            </div>
         </div>
     </div>
  </div>
</div>
<script src="<s:property value="#attr.basePath" />content/scripts/jquery.timers-master/jquery.timers.min.js"></script>
<script src="<s:property value="#attr.basePath" />adminresource/js/chat/chatroom.js"></script>	
</body>
</html>