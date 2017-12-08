<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>    
<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=11;IE=10;IE=9;IE=8;IE=EDGE" />
<title>深圳壹报料</title>
    
    <link href="<s:url value="/web/css/style.2.0.css"/>" rel="stylesheet" />
    <link href="<s:url value="/web/css/styleM.css"/>" rel="stylesheet" />
    
    <script src="<s:url value="/content/scripts/jquery-1.9.1.js"/>"></script>
    <script src="<s:url value="/content/scripts/common/setting.js"/>"></script>
	<link rel="shortcut icon" type="image/x-icon" href="../web/images/favicon.ico" media="screen" />
    
</head>
<body>
	<script>
		function initExt(){
	    	Date.prototype.format = function(fmt){ 
		    	  var o = {   
		    	    "M+" : this.getMonth()+1,                 //月份   
		    	    "d+" : this.getDate(),                    //日   
		    	    "h+" : this.getHours(),                   //小时   
		    	    "m+" : this.getMinutes(),                 //分   
		    	    "s+" : this.getSeconds(),                 //秒   
		    	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
		    	    "S"  : this.getMilliseconds()             //毫秒   
		    	  };   
		    	  if(/(y+)/.test(fmt))   
		    	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
		    	  for(var k in o)   
		    	    if(new RegExp("("+ k +")").test(fmt))   
		    	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
		    	  return fmt;   
		    	}  
		    }
		    
		    function initTime(){
		    	setDateTime();
		    	setInterval(setDateTime, 1000);
		    }
		    
		    function setDateTime(){
		    	var dt = new Date();
		    	var str = ["星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"]
		    	$("#datediv").attr("innerHTML", dt.format("yyyy年MM月dd日") + "&nbsp;" + str[dt.getDay()]);
		    	$("#timediv").attr("innerHTML", dt.format("hh:mm:ss"));
		    }
		    
		    $(function(){
		    	initExt();
		    	initTime();
		    });
		    
		    function login(){
		    	$.ajax({
		    		url:'<s:url action="loginjson" namespace="/uac/user" />?entity.loginAccount='+$("#account").val()+"&entity.loginPasswd="+$("#passwd").val(),
		    		type:"post",
		    		timeout:60000,
		    		success:function(data){
		    			if(data=='fail'){
		    				alert("登录失败！");
		    				return;
		    			}
		    				
		    			var result = eval('(' + data + ')');
		    			if(result && result.success){
							$("#userInfo").show();
							$("#loginbox").hide();
							$("#userName").attr("innerHTML", result.userName);
							//location.href = '<s:url action="workBench" namespace="/web"/>';
		    			}else{
		    				alert("登录失败！");
		    			}
		    		},
		    		error:function(){
		    			alert("登录失败！");
		    		}
		    	});
		    }
		    
		    function logout(){
		    	$.ajax({
		    		url:'<s:url action="logoutjson" namespace="/uac/user" />',
		    		type:"post",
		    		timeout:60000,
		    		success:function(data){
		    			$("#account").val("");
		    			$("#passwd").val("");
		    			$("#userInfo").hide();
						$("#loginbox").show();
						location.href = '<s:url action="index" namespace="/web"/>?rnd='+Math.random();
						return false;
		    		},
		    		error:function(){
		    			//alert("登录失败！");
		    		}
		    	});
		    	
		    }
		    
		    function queryArticle(){
		    	if($("#nameKey").val() != ""){
		    		location.href='<s:url action="query" namespace="/web" />?query.nameKey=' + encodeURI(encodeURI($("#nameKey").val()));
		    		return false;
		    	}else{
		    		alert("请输入关键字进行搜索！");
		    	}
		    }
		    
	</script>
		<div class="top">
		<div class="header">
			<div class="header_1">
				<div class="log">
				<span class="header_time pdtop95" id="datediv">2013年11月25日&nbsp;星期二</span>
				<span class="header_time" id="timediv">16:56:56</span>
				</div>
				<div class="log_2">
				<span class="slogan">国家利益至上&nbsp;消费者利益至上</span>
				</div>
			</div>
			<div class="nav">
				<ul>
				<li class="nav_li"><a href="<s:url action="index" namespace="/web"/>" <s:if test="category==null">class="nav_a"</s:if> >网站首页</a></li>
				<s:iterator value="topCategories" status="row">
					
					<s:if test="#row.last">
						<s:if test="category.id==id">
							<li class="nav_li_last"><a class="nav_a" href="<s:url action="intoArticleCatalog" namespace="/web"/>?query.categoryId=<s:property value="id"/>" ><s:property value="name"/></a></li>
						</s:if><s:else>
							<li class="nav_li_last"><a href="<s:url action="intoArticleCatalog" namespace="/web"/>?query.categoryId=<s:property value="id"/>" ><s:property value="name"/></a></li>
						</s:else>
					</s:if>
					<s:else>
						<s:if test="category.id==id">
							<li class="nav_li"><a class="nav_a" href="<s:url action="intoArticleCatalog" namespace="/web"/>?query.categoryId=<s:property value="id"/>" ><s:property value="name"/></a></li>
						</s:if><s:else>
							<li class="nav_li"><a  href="<s:url action="intoArticleCatalog" namespace="/web"/>?query.categoryId=<s:property value="id"/>" ><s:property value="name"/></a></li>
						</s:else>
					</s:else>
				</s:iterator>
				</ul>
				<div class="clear"></div>
			</div>			
			</div>
		<div class="header_2">
				<div class="placard_bg"></div>
				<div class="placard">新版信息平台开始试运行，欢迎大家提出各种修改意见。<a href="<s:url action="showAddFeeback" namespace="/web"/>" >点此反馈</a></div>
				<div class="search">
				<span class="input_tt">站内检索：</span>
				<input type="text" id="nameKey" class="search_input" value="<s:property value="query.nameKey"/>" />
				<input type="button" onclick="queryArticle();" class="search_button"/>
				<a href="<s:url action="querycon" namespace="/web"/>" target="_blank">高级检索</a>
				</div>
				<div class="search_blank"></div>
				<div class="login">
					<s:if test="loginUser==null">
					<div id="loginbox">
						<span class="input_tt">用户登录：</span>
							<input name="" id="account" type="text" class="login_input" value="<s:property value="cookieUser.loginAccount"/>" />
							<input name="" id="passwd" type="password" class="login_input" value="<s:property value="cookieUser.loginPasswd"/>"/>
							<input name="" type="button"  class="login_button" value="" onclick="login();"/>
						</div>
					</s:if>
					<div class="user_info" id="userInfo" <s:if test="loginUser==null">style="display:none"</s:if>>						
						<span id="userName"><s:property value="loginUser.name"/></span><span style="margin-left:5px">欢迎登录</span>，<a href="#" onclick="logout();return false;">退出</a><span style="margin-left:10px"><a href="<s:url action="index" namespace="/"/>">进入工作台</a></span>
						<span style="margin-left:5px"><a href="<s:url action="edit" namespace="/web"/>">发布稿件</a></span>
					</div>
				</div>
					
				<div class="clear"></div>
			</div>	
	</div>
		<decorator:body />
	<div class="footer_all">	
		<div class="footer">
			<ul class="footer_nav">
				
			</ul>
		<div class="footer_bottom">该网站主管（办）单位：来宾市烟草专卖局专卖局（公司））办公室 未经许可，本网站包括图像、图标、文字在内的所有数据不得转载<br />
		版权所有：来宾市烟草专卖局（公司） 技术支持：信息科</div>	
		</div>
		<div class="page_bottom-bg"></div>
	</div>
</body>
</html>