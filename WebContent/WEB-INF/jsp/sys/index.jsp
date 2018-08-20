<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=10;IE=9;IE=8;" />
<title>Mobile 3D</title>
    <script src="<s:url value="/pluginresource/jeasyui-extensions/jquery-1.11.1.min.js"/>"></script>
    <script src="<s:url value="/content/scripts/jquery-easyui/jquery.easyui.min.js"/>"></script>
    <link href="<s:url value="/content/scripts/jquery-easyui/themes/icon.css"/>" rel="stylesheet" />
    <link href="<s:url value="/content/scripts/jquery-easyui/themes/default/easyui.css"/>" rel="stylesheet" />
    <link href="<s:url value="/content/scripts/jquery-easyui/themes/default/green1.css"/>" rel="stylesheet" />
    <link href="<s:url value="/content/scripts/jquery-easyui/themes/default/accordion.css"/>" rel="stylesheet" />
    <script src="<s:url value="/content/scripts/jquery-easyui/locale/easyui-lang-zh_CN.js"/>"></script>
    <link href="<s:url value="/content/styles/menu.css"/>" rel="stylesheet" type="text/css" />
    <link href="<s:url value="/content/styles/index.css"/>" rel="stylesheet" />
    <link href="<s:url value="/content/styles/Site.css"/>" rel="stylesheet" />
    
    <script src="<s:url value="/pluginresource/jeasyui-extensions/jquery.jdirk.min.js"/>"></script>
    <link href="<s:url value="/pluginresource/jeasyui-extensions/jeasyui.extensions.min.css"/>" rel="stylesheet" />
    <script src="<s:url value="/pluginresource/jeasyui-extensions/jeasyui.extensions.js"/>"></script>
    <script src="<s:url value="/pluginresource/jeasyui-extensions/jeasyui.extensions.all.min.js"/>"></script>
    <script src="<s:url value="/pluginresource/plugins/jquery.plugin.form.js"/>"></script>    
    <script src="<s:url value="/pluginresource/plugins/jquery.plugin.utils.js"/>"></script>
    <script src="<s:url value="/pluginresource/plugins/jquery.plugin.apiajax.js"/>"></script>
    
    
    <script src="<s:url value="/content/scripts/common/setting.js"/>" ></script>
    <script src="<s:url value="/content/scripts/common/loading.js"/>" ></script>
    <style type="text/css">
        a
        {
            text-decoration: none;
        }
    </style>

    <script type="text/javascript">

        function setLeft(typeName) {
        	expand();
        	setMenu(typeName);
            switch (typeName) {
                case "article":
                    setAllImageNormal();
                    $("#divarticle").attr("class", "topbtns");
                    break;
                case "setting":
                    setAllImageNormal();
                    $("#divsetting").attr("class", "topbtns");
                    break;
            }
        }

        function hideAll() {
            $("#divPlan").hide();
            $("#divCheck").hide();
            $("#divThree").hide();
            $("#divExamine").hide();
            $("#divArticle").hide();
            $("#divSetting").hide();
        }

        function setAllImageNormal() {
        	$("#divplan").attr("class", "topbtn");
        	$("#divcheck").attr("class", "topbtn");
        	$("#divproject").attr("class", "topbtn");
        	$("#divreview").attr("class", "topbtn");
        	$("#divarticle").attr("class", "topbtn");
        	$("#divsetting").attr("class", "topbtn");
        }

        function switchTree(id) {
            $("#divPlan").hide();
            $("#divCheck").hide();
            $("#divThree").hide();
            $("#divExamine").hide();
            $("#divArticle").hide();
            $("#divSetting").hide();
            $("#" + id).show();
        }

        function nodeClicked(node) {
        	//alert($(node.target)[0].id);
            $("#plContent").panel("setTitle", $(node.text).text());
            $("#divFrame").attr("src", $(node.text).attr("href"));
        }
        
        function itemSelected(title, index){
        	if(menuObj != null && menuObj.length>index){
        		var src="";
        		var tit="";
        		if(typeof(menuObj[index].defaultUrl)!="undefined"){
        			src = menuObj[index].defaultUrl;
        			tit=title;
        		}else{
        			if(typeof(menuObj[index].children[0].attributes)=="undefined"){
	        			src = menuObj[index].children[0].children[0].attributes.url;
	        			tit=menuObj[index].children[0].children[0].text;
	        		}else{
	        			src=menuObj[index].children[0].attributes.url;
	        			tit=menuObj[index].children[0].text;
	        		}
        		}
        		$("#divFrame").attr("src", src);
       		 	$("#plContent").panel("setTitle", tit);
        	}
        }
        
        var menuObj = null;
        function setMenu(menuName){
        	menuObj = menus[menuName];
        	var accId = "#divPlan";
        	var oldcount = $(accId).accordion("panels").length;
        	for(var i=0;i<menuObj.length;i++){
        		var item = {title:menuObj[i]["text"],content:generateContent(menuObj[i].children)};
        		if(i>0)
        			item["selected"]=false;
        		$(accId).accordion("add", item);
        	}
        	for(var i=0;i<oldcount;i++){
        		$(accId).accordion("remove", 0);
        	}
        	var url = $("#divFrame").attr("src");
        	//if(url != mainUrl){
        		var src="";
        		var title="";
        		if(menuObj[0].defaultUrl!="undefined"){
        			src=menuObj[0].defaultUrl;
        			var selectedPanel = $("#divPlan").accordion("getSelected");
        			if(selectedPanel != null){
        				var header = selectedPanel.panel("options")
        				if(header!=null)
        					title = header.title;
        			}
        		}else{
	        		if(typeof(menuObj[0].children[0].attributes)=="undefined"){
	        			src = menuObj[0].children[0].children[0].attributes.url;
	        			title=menuObj[0].children[0].children[0].text;
	        		}else{
	        			src=menuObj[0].children[0].attributes.url;
	        			title=menuObj[0].children[0].text;
	        		}
        		}
        		$("#divFrame").attr("src", src);
        		 $("#plContent").panel("setTitle", title);
        	//}
        }

        
        function generateContent(item){
        	var content = '<ul class="easyui-tree" data-options="lines:true,onClick:nodeClicked" style="padding:10px" >';
        	for(var i=0;i<item.length;i++){
        		if(typeof(item[i].attributes)=="undefined"){
        			content+="<li><span>" + item[i].text+"</span>";
        			content+=generateContent(item[i].children);
        			content+="</li>";
        		}else{
        			content+='<li><span><a href="' + item[i].attributes.url+ '" target="divFrame">' + item[i].text +'</a></span></li>';
        		}
        	}
        	
        	content+="</ul>";
        	return content;
        }
        
        var menus={};
        
        var menuArticle = [
                           {text:"用户咨询", children:[
                                                   		 {text:"用户咨询", attributes:{url:"<s:url action="chatroom" namespace="/admin/chat"/>"}},
                                                   		 {text:"咨询统计", attributes:{url:"<s:url action="consultingStatistics" namespace="/admin/client"/>"}},
                                                   ],
                                defaultUrl:"<s:url action="chatroom" namespace="/admin/chat"/>"
                        	   
                           },
                           {text:"账户管理", children:[
                                                   		 {text:"用户管理", attributes:{url:"<s:url action="clientlist" namespace="/admin/client"/>"}},
                                                   		 {text:"员管理", attributes:{url:"<s:url action="list" namespace="/admin/client/mediatorapply"/>"}},
                                                   		 {text:"权限组", attributes:{url:"<s:url action="list" namespace="/admin/client/authority"/>"}},
                                                   		 {text:"用户默认头像设置", attributes:{url:"<s:url action="defaultHeadImg" namespace="/admin/client"/>"}}
                                                   ],
                                defaultUrl:"<s:url action="clientlist" namespace="/admin/client"/>"
                        	   
                           },
                           {text:"管理", children:[
                                                   		 {text:"机构", attributes:{url:"<s:url action="list" namespace="/admin/mediation/mediationagency"/>"}},
                                                   		 {text:"管理", attributes:{url:"<s:url action="list" namespace="/admin/mediation/mediationcase"/>"}},
                                                   		 {text:"经典案例", attributes:{url:"<s:url action="classiclist" namespace="/admin/mediation/legaldoc"/>"}},
                                                   		 {text:"法规文档", attributes:{url:"<s:url action="list" namespace="/admin/mediation/legaldoc"/>"}},
                                                   		 {text:"基础数据录入", attributes:{url:"<s:url action="list" namespace="/admin/mediation/basicdata"/>"}},
                                                   		 {text:"街道录入", attributes:{url:"<s:url action="listJsp" namespace="/admin/mediation/street"/>"}},
                                                   		 {text:"类型统计数据", attributes:{url:"<s:url action="caseDistribution" namespace="/admin/mediation/mediationcase"/>"}}
                                                   		
                                                   	],
                                defaultUrl:"<s:url action="list" namespace="/admin/mediation/mediationcase"/>"
                        	   
                           },
                           {text:"新闻管理", children:[		
														 {text:"新闻广告管理", attributes:{url:"<s:url action="resourceList" namespace="/admin/news/MomentsResources"/>"}},
                                                   		 {text:"新闻管理", attributes:{url:"<s:url action="list" namespace="/admin/news"/>"}},
                                                   ],
                                defaultUrl:"<s:url action="list" namespace="/admin/news"/>"
                        	   
                           },
                          
                            
        					{text:"系统参数设置", children:[
      					                        {text:"基础数据设置", attributes:{url:"<s:url action="basissetlist" namespace="/admin/basisset" />"}}
                                                   ],
                         	defaultUrl:"<s:url action="basissetlist" namespace="/admin/basisset" />"
                       		}, 
        					/* {text:"服务管理", children:[
            					                        {text:"投诉建议", attributes:{url:"<s:url action="list" namespace="/admin/service/complain" />"}},
            					                        {text:"问卷调查", attributes:{url:"<s:url action="list" namespace="/admin/service/survey" />"}},
            					                        {text:"添加调查活动", attributes:{url:"<s:url action="newsurveylist" namespace="/admin/service/survey" />"}}
                                                     ],
                               	defaultUrl:"<s:url action="list" namespace="/admin/service/complain" />"
                            }, */
                            
                       		/* {text:"基础设置", children:[
          					                        {text:"地区设置", attributes:{url:"<s:url action="list" namespace="/sys/area" />"}},
          					                        {text:"栏目设置", attributes:{url:"<s:url action="list" namespace="/sys/category" />"}},
          					                        {text:"内容类型设置", attributes:{url:"<s:url action="list" namespace="/sys/contentType" />"}},
          					                        {text:"事件设置", attributes:{url:"<s:url action="list" namespace="/sys/eventType" />"}}
                                                       ],
                             	defaultUrl:"<s:url action="list" namespace="/sys/area" />"
                           		}, */
        					/* {text:"权限设置", children:[
	        					                          {text:"部门设置", attributes:{url:"<s:url action="list" namespace="/uac/department" />"}},
	                                                      {text:"用户设置", attributes:{url:"<s:url action="list" namespace="/uac/user" />"}},
	                                                      {text:"角色设置", attributes:{url:"<s:url action="list" namespace="/uac/role" />"}},
	                                                      {text:"操作类别设置(开发用)", attributes:{url:"<s:url action="list" namespace="/uac/operationcategory" />"}},
	                                                      {text:"操作设置(开发用)", attributes:{url:"<s:url action="list" namespace="/uac/operation" />"}}
                                                         ],
                               	defaultUrl:"<s:url action="list" namespace="/uac/department" />"
                             }, */
                             {text:"日志管理", children:[
                                               		 {text:"API接口日志", attributes:{url:"<s:url action="list" namespace="/admin/api/log"/>"}}
                                               ],
                                 defaultUrl:"<s:url action="list" namespace="/admin/api/log"/>"
                             },                             
                           ];
        menus["article"]=menuArticle;
        
        function cloapse(){
        	$("#lyMain").layout("collapse", "west");
        }
        
        function expand(){
        	if(isCollapse)
        		$("#lyMain").layout("expand", "west");
        }
        
        var isCollapse=true;
        function setCollapse(){
        	isCollapse=true;
        }
        function setExpand(){
        	isCollapse=false;
        }
        
        var mainUrl="";
        $(function(){
        	setMenu("article");
        	setInfoPos();
        	$(window).resize(setInfoPos);
        	$("#divFrame").attr("src","<s:url action="main" namespace="/sys" />");
        	//$("#lyMain").layout("collapse", "west");
        	mainUrl = $("#divFrame").attr("src");
        });
        
        function setInfoPos(){
        	var width = $(window).width();
        	$("#todo").css("left",width-$("#todo").width());
        }
        
    </script>
</head>
<body>
	<div class="easyui-layout" style="overflow-y: hidden; width: 100%; height: 100%;" fit="true" id="lyMain">
        <div data-options="region:'north',border:false" class="top_color top_bg" style="height: 111px">
        	<div class="top_banner" style="width:100%;height:100%">
        		<div style="padding-top: 10px;padding-right:10px;float:right">
       	        
        	    </div>
        	 </div>
        </div>
        <div region="west" data-options="border:false,split:false,collapsed:false,onExpand:setExpand,onCollapse:setCollapse" style="width: 250px; ">
        	<div class="easyui-layout" data-options="fit:true">
        		<div region="north" data-options="height:34,border:false" class="nav"><a href="#" onclick="cloapse();"><img src="<s:url value="/content/styles/images/nav.jpg"/>"></a></div>
        		<div region="center" data-options="border:false,split:false">
	        	<div class="easyui-accordion" id="divPlan" data-options="fit:true,border:false,onSelect:itemSelected">
	        		
	        	</div>
	            </div>
	         </div>
        </div>
        <div title="Mobile 3D APP" region="center" style="overflow: visible; height: 100%;" data-options="border:true" id="plContent">
            <iframe id="divFrame" name="divFrame" style="width: 100%; height: 100%; border: 0;" frameborder="0" ></iframe>
        </div>
        <div data-options="region:'south',split:true" style="background-repeat: repeat-x; height:30px; text-align: center; font-size: small; color: #000;">
            <div style="padding-top:5px;">版权所有:</div>
        </div>
        <script type="text/javascript">
            function setContent(node) {
                var nodeLink = $(node.text);
                $("#divFrame").attr("src", $(node.text).attr("href"));
            }
            
            function setContentUrl(url){
            	$("#divFrame").attr("src", url);
            }

            function openDialog(url,title, width, height, isMaxed) {
            	
            	if(url.lastIndexOf("?")>=0)
            		url=url+"&rnd=" + Math.random();
            	else
            		url=url+"?rnd=" + Math.random();
            	
                $("#ifrDlg").attr("src", url);

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
                if(typeof($("#ifrDlg")[0].contentWindow)=="object"
        			&& typeof($("#ifrDlg")[0].contentWindow.$)=="function"
        			&& typeof($("#ifrDlg")[0].contentWindow.$.messager)=="object"
        			){
        			$("#ifrDlg")[0].contentWindow.$.messager.progress();
        	}
            }
            
            var extData = null;
            function openDialog2(url,title, width, height, extDlgData, isMaxed) {
            	if(url.lastIndexOf("?")>=0)
            		url=url+"&rnd=" + Math.random();
            	else
            		url=url+"?rnd=" + Math.random();
            	//$("#ifrDlg2").attr("src", "");
                $("#ifrDlg2").attr("src", url);

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
                $("#dlg2").dialog(options);
                $("#dlg2").dialog("open", options);
                
            	if(typeof($("#ifrDlg2")[0].contentWindow) == "object"
        			&& typeof($("#ifrDlg2")[0].contentWindow.$)=="function" 
        			&& typeof($("#ifrDlg2")[0].contentWindow.$.messager) == "object"){
        			$("#ifrDlg2")[0].contentWindow.$.messager.progress();
        		}
				extData = extDlgData;
            }
            
            function refreshContent(){
            	if(typeof($("#divFrame")[0].contentWindow.refreshData)=="function"){
            		$("#divFrame")[0].contentWindow.refreshData();
            	}
            }
            
            function refreshContentTree(){
            	if(typeof($("#divFrame")[0].contentWindow.refreshTree)=="function"){
            		$("#divFrame")[0].contentWindow.refreshTree();
            	}
            }
            
            function getContentWindow(func){
            	return $("#divFrame")[0].contentWindow;
            }
            
            function refreshDialogContent(){
            	var w = getDialogContentWindow();
            	if(typeof(w.refreshData)=="function"){
            		w.refreshData();
            	}
            }
            
            function getDialogContentWindow(){
            	return $("#ifrDlg")[0].contentWindow;
            }
            
            function getDialogContentWindow2(){
            	return $("#ifrDlg2")[0].contentWindow;
            }
            
            
            function closeDialog(){
            	$("#dlg").dialog('close');
            }
            
            function closeDialog2(){
            	$("#dlg2").dialog('close');
            }

            function setPasswd() {
                openDialog("<s:url action="setpasswd" namespace="/uac/user" />", "重置密码", 400, 300);
            }

            function setUserInfo() {
                openDialog("uace/setUserInfo.html", "个人信息设置", 400, 300);
            }
            
            function exit(){
            	$.messager.confirm(WINDOW_CAPTION, "确定要退出系统吗？", function(result){
            		if(result){
            			location.href="<s:url action="logout" namespace="/uac/user" />";
            			return false;
            		}
            	})
            }
            
            function setTodoCount(count){
            	//$("#todoCount").html(count);
            }
            
        </script>

        <div id="dlg" class="easyui-dialog" closed="true" modal="true" title="窗口" style="width: 880px; height: 600px; overflow: visible">
            <iframe id="ifrDlg" style="width: 100%; height: 100%; border: 0;" frameborder="0"></iframe>
        </div>
        <div id="dlg2" class="easyui-dialog" closed="true" modal="true" title="窗口" style="width: 880px; height: 600px; overflow: visible">
            <iframe id="ifrDlg2" style="width: 100%; height: 100%; border: 0;" frameborder="0"></iframe>
        </div>
    </div>
    <div id="todo" style="position:absolute;left:1800px; top:118px;width:300px">用户：<span class="infotext"><s:property value="loginUserName" /></span>
    <div style="display:none">&nbsp;&nbsp;待办<span id="todoCount" class="infotext"></span> 项</div>
    &nbsp;&nbsp;[<a href="#" onclick="exit();return false; ">退出登录</a>]&nbsp;&nbsp;[<a style="cursor:pointer" onclick="setPasswd()">修改密码</a>]
    &nbsp;[<a href="<s:url action="main" namespace="/sys"/>" target="divFrame">首页</a>]
    &nbsp;[<a onclick="history.back();return false;" href="#">返回</a>]
    </div>
</body>
</html>