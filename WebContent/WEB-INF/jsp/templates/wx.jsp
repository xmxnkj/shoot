<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>    
<%@ taglib uri="/WEB-INF/sitemesh-decorator.tld" prefix="decorator"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=11;IE=10;IE=9;IE=8;IE=EDGE" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"/>
<meta name ="apple-mobile-web-app-capable" content="yes"/>
<meta content="telephone=no" name="format-detection"/>
<meta name="description" content="" />
<meta name="keywords" content="" />
<title>拜托有票</title>
    
	<script src="<s:url value="/content/scripts/jquery-easyui/jquery-1.7.2.min.js"/>"></script>
    <link href="<s:url value="/content/styles/buyTicketCss/phone_style.css"/>" rel="stylesheet"/>
	<link href="<s:url value="/content/styles/buyTicketCss/datepicker.css"/>" rel="stylesheet" type="text/css" />
	<script src="<s:url value="/pub/js/jquery-ui-datepicker.js"/>"></script>
	<link href="<s:url value="/content/styles/buyTicketCss/Site.css"/>" rel="stylesheet" type="text/css" />
	<script src="<s:url value="/content/scripts/common/setting.js"/>"></script>
	<script src="<s:url value="/content/scripts/qscan/qrcode.js"/>"></script>
	<script src="<s:url value="/content/scripts/qscan/jquery.qrcode.js"/>"></script>

    <style type="text/css">
    	body{
    		overflow: hidden;
    	}
    	html{
    		overflow:auto;
    	}
    </style>
    <script type="text/javascript">
    window.zhuge = window.zhuge || [];
    window.zhuge.methods = "_init debug identify track trackLink trackForm page".split(" ");
    window.zhuge.factory = function(b) {
        return function() {
            var a = Array.prototype.slice.call(arguments);
            a.unshift(b);
            window.zhuge.push(a);
            return window.zhuge;
        }
    };
    for (var i = 0; i < window.zhuge.methods.length; i++) {
        var key = window.zhuge.methods[i];
        window.zhuge[key] = window.zhuge.factory(key)
    };
    window.zhuge.load = function(b, x) {
        if (!document.getElementById("zhuge-js")) {
            var a = document.createElement("script");
            var verDate = new Date();
            var verStr = verDate.getFullYear().toString() 
                + verDate.getMonth().toString() + verDate.getDate().toString();
            a.type = "text/javascript";
            a.id = "zhuge-js";
            a.async = !0;
            a.src = "https://zgsdk.zhugeio.com/zhuge-lastest.min.js?v=" + verStr;
            var c = document.getElementsByTagName("script")[0];
            c.parentNode.insertBefore(a, c);
            window.zhuge._init(b, x)
        }
    };
    window.zhuge.load('71e6e2b8403c48e6bb8b4a0a84489f6a');
    
    function sztrack(name, obj){
    	try{
    		zhuge.track(name, obj);
    	}catch(e){
    		
    	}
    }
    </script>
</head>
<body>
		<decorator:body />
</body>
</html>